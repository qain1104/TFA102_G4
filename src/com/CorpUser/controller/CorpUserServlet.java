package com.CorpUser.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.CorpUser.model.CorpUserService;
import com.CorpUser.model.CorpUserVO;
import com.GeneralUser.model.GeneralUserService;
import com.GeneralUser.model.GeneralUserVO;

import Mail.MailService;
import sun.font.CreatedFontTracker;

public class CorpUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer corpUserId = (Integer) session.getAttribute("corpUserId");
				System.out.println("����"+corpUserId);
				if (corpUserId == null) {
					System.out.println("�S����");
					errorMsgs.add("�S���session��corpUserId");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/corpUser/corpProfile.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}


				/*************************** 2.�}�l�d�߸�� *****************************************/
				CorpUserService corpUserSvc = new CorpUserService();
				CorpUserVO corpUserVO = corpUserSvc.getOneCorpUser(corpUserId);
				if (corpUserVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				session.setAttribute("currentC", corpUserVO); 
				System.out.println("��Ʈw���X��corpUserVO����,�s�Jsession");
				String url = "/corpUser/corpProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
				return;
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllCorpUser.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer corpUserId = new Integer(req.getParameter("corpUserId"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				CorpUserService corpUserSvc = new CorpUserService();
				CorpUserVO corpUserVO = corpUserSvc.getOneCorpUser(corpUserId);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("corpUserVO", corpUserVO); // ��Ʈw���X��corpUserVO����,�s�Jreq
				String url = "/corpUser/update_corpUser_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_corpUser_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				/* ����Q��s���Ѽ�(�_) */
				Integer corpUserId = new Integer(req.getParameter("corpUserId").trim());
				Integer registerStatus = new Integer(req.getParameter("registerStatus").trim());
				String corpAccount = req.getParameter("corpAccount").trim();
				String corpPassword = req.getParameter("corpPassword").trim(); /* �Pı�n�������쥻��,�p�n���A��浹�M����K�X�� */
				String companyName = req.getParameter("companyName").trim();
				String ltdNo = req.getParameter("ltdNo").trim();
				String email = req.getParameter("email").trim();
				/* ����Q��s���Ѽ�(��) */

				/* �i�H�Q��s���Ѽ�(�_) */
				String phone = req.getParameter("phone");
				String phoneReg = "^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$";
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("�s���q��: �ФŪť�");
				} else if (!phone.trim().matches(phoneReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�s���q��: �u��Ʀr�B���ץ��ݬ�10");
				}

				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,30}$";
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				} else if (!address.trim().matches(addressReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�a�}: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb1��30����");
				}

//				byte[] profilePic = req.getParameter("profilePic").getBytes();
				byte[] profilePic = null;
				/* �i�H�Q��s���Ѽ�(��) */

				/* �ΤW���s���ѼƳЫؤ@��VO */
				CorpUserVO corpUserVO = new CorpUserVO();
				corpUserVO.setCorpUserId(corpUserId);
				corpUserVO.setRegisterStatus(registerStatus);
				corpUserVO.setCorpAccount(corpAccount);
				corpUserVO.setCorpPassword(corpPassword);
				corpUserVO.setCompanyName(companyName);
				corpUserVO.setLtdNo(ltdNo);
				corpUserVO.setEmail(email);
				corpUserVO.setPhone(phone);
				corpUserVO.setAddress(address);
				byte[] is = corpUserVO.getProfilePic();
				corpUserVO.setProfilePic(is);
//				corpUserVO.setCreatedTime(createdTime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("corpUserVO", corpUserVO); // �t����J�榡���~��corpUserVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/corpUser/corpUserEdit.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				CorpUserService corpUserSvc = new CorpUserService();
				corpUserVO = corpUserSvc.updateProfile(corpUserId,corpPassword,phone,address);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				session.setAttribute("currentC", corpUserVO); // ��Ʈwupdate���\��,���T����corpUserVO����,�s�Jreq
				String url = "/corpUser/corpProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/corpUser/corpUserEdit.jsp");
				failureView.forward(req, res);
			}
		}

		if ("CorpUserSignUp".equals(action)) { // �Ӧ�login.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				/* ����Q��s���Ѽ�(�_) */

				Integer registerStatus = new Integer(0);// ���U�@�w�w�]�O0
				String corpAccount = req.getParameter("corpAccount").trim();
				CorpUserService corpUserSvc2 = new CorpUserService();
				List<CorpUserVO> list = corpUserSvc2.getAll().stream()
						.filter(e -> e.getCorpAccount().contains(corpAccount)).collect(Collectors.toList());
				String corpAccountReg = "^[(a-zA-Z0-9)]{8,16}$";
				if (corpAccount == null || corpAccount.trim().length() == 0) {
					errorMsgs.add("�b��: �ФŪť�");
				} else if (!corpAccount.trim().matches(corpAccountReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�b��: �u��O�^��r���B�Ʀr , �B���ץ��ݦb8��16����");
				} else if (!list.isEmpty()) {
					errorMsgs.add("�b�����ơA�Ч��");
				}

				String corpPassword = req.getParameter("corpPassword").trim();
				String corpPasswordReg = "^[(a-zA-Z0-9)]{8,16}$";
				if (corpPassword == null || corpPassword.trim().length() == 0) {
					errorMsgs.add("�K�X: �ФŪť�");
				} else if (!corpPassword.trim().matches(corpPasswordReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�K�X: �u��O�^��r���B�Ʀr , �B���ץ��ݦb8��16����");
				}

				String companyName = req.getParameter("companyName").trim();
				String companyNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,10}$";
				if (companyName == null || companyName.trim().length() == 0) {
					errorMsgs.add("���q�W��: �ФŪť�");
				} else if (!companyName.trim().matches(companyNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���q�W��: �u��O����B�^��B�Ʀr , �B���ץ��ݦb1��10����");
				}

				String ltdNo = req.getParameter("ltdNo").trim();
				String ltdNoReg = "^[(a-zA-Z0-9)]{10}$";
				if (ltdNo == null || ltdNo.trim().length() == 0) {
					errorMsgs.add("���q�渹: �ФŪť�");
				} else if (!ltdNo.trim().matches(ltdNoReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���q�渹: �u��O�^��r���B�Ʀr , �B���ץ��ݬ�10");
				}

				String email = req.getParameter("email").trim(); // �Ϋe�ݨӧP�_���T�P�_
				List<CorpUserVO> list2 = corpUserSvc2.getAll().stream()
						.filter(e -> e.getEmail().contains(email)).collect(Collectors.toList());
				if(!list2.isEmpty()) {
					errorMsgs.add("�H�c�w���U");
				}
				
				Timestamp createdTime = new Timestamp(System.currentTimeMillis());
				byte[] profilePic = null;
				/* ����Q��s���Ѽ�(��) */

				/* �i�H�Q��s���Ѽ�(�_) */
				String phone = req.getParameter("phone");
				String phoneReg = "^[0-9]{10}$";
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("�s���q��: �ФŪť�");
				} else if (!phone.trim().matches(phoneReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�s���q��: �u��Ʀr�B���ץ��ݬ�10");
				}

				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,30}$";
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				} else if (!address.trim().matches(addressReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�a�}: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb1��30����");
				}

				/* �i�H�Q��s���Ѽ�(��) */

				/* �ΤW���s���ѼƳЫؤ@��VO */
				CorpUserVO corpUserVO = new CorpUserVO();

				corpUserVO.setRegisterStatus(registerStatus);
				corpUserVO.setCorpAccount(corpAccount);
				corpUserVO.setCorpPassword(corpPassword);
				corpUserVO.setCompanyName(companyName);
				corpUserVO.setLtdNo(ltdNo);
				corpUserVO.setEmail(email);
				corpUserVO.setPhone(phone);
				corpUserVO.setAddress(address);
				corpUserVO.setProfilePic(null);
				corpUserVO.setCreatedTime(createdTime);

				System.out.println(corpUserVO);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("corpUserVO", corpUserVO); // �t����J�榡���~��corpUserVO����,�]�s�Jreq
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�Ыظ�� *****************************************/
				CorpUserService corpUserSvc = new CorpUserService();
				corpUserVO = corpUserSvc.addCorpUser(registerStatus, corpAccount, corpPassword, companyName, ltdNo,
						email, phone, address, profilePic, createdTime);

				/*************************** 3.�Ыا���,�ǳ����(Send the Success view) *************/
				req.setAttribute("currentC", corpUserVO); // ��Ʈwupdate���\��,���T����corpUserVO����,�s�Jreq
				
				String test = "qain1104@gmail.com"; // �W�u�e�γo�Ӵ���

				String to = email;
				String link = "http://localhost:8081/Sportify/certification?email=" + to;

				System.out.println(to);
				String subject = "�ҥ�Sportify�|��";
				String messageText = "�z�n�A�I���s���w�����{��! " + link;
				MailService mailService = new MailService();
				mailService.sendMail(test, subject, messageText);
				
				String url = "/SignUpSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�s�W��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
				failureView.forward(req, res);
			}
		}
		/***************************
		 * ���~�|�����U���\��A���������U���\�����A�A���^�n�J����
		 *************************************/
		if ("backToLogin".equals(action)) { // �Ӧ�login.jsp���ШD
			String url = "/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			session.removeAttribute("errorMsgs");
			successView.forward(req, res);
		}

		/**********************************************************************************/

		if ("edit".equals(action)) { // �Ӧ�login.jsp���ШD
			CorpUserVO member = (CorpUserVO) req.getSession().getAttribute("currentC");
			CorpUserService corpUserSvc = new CorpUserService();
			CorpUserVO mem = corpUserSvc.getOneCorpUser(member.getCorpUserId());
			req.setAttribute("currentC", mem);
			String url = "/corpUser/corpUserEdit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("back".equals(action)) { // �Ӧ�login.jsp���ШD
			String url = "/corpUser/corpUserMainPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("resetPassword".equals(action)) { // �Ӧ�login.jsp���ШD
			Integer corpUserId = new Integer(req.getParameter("corpUserId").trim());
			String  newPassword = req.getParameter("corpPassword");
			
			CorpUserService cuSvc = new CorpUserService();
			cuSvc.resetPassword(corpUserId, newPassword);
			String url = "/resetPwSuccess.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
	}
}
