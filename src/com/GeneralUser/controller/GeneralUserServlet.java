package com.GeneralUser.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

public class GeneralUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("getOne_For_Display".equals(action)) { // �d��@�@��|��

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer userId = (Integer) session.getAttribute("userId");
				System.out.println("����"+userId);
				if (userId == null) {
					System.out.println("�S����");
					errorMsgs.add("�S���session��userId");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/generalUser/generalProfile.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}


				/*************************** 2.�}�l�d�߸�� *****************************************/
				GeneralUserService generalUserSvc = new GeneralUserService();
				GeneralUserVO generalUserVO = generalUserSvc.getOneGeneralUser(userId);
				if (generalUserVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/generalUser/generalProfile.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				session.setAttribute("currentG", generalUserVO); 
				System.out.println("��Ʈw���X��generalUserVO����,�s�Jsession");
				String url = "/generalUser/generalProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
				return;
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				System.out.println("���b�o");
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/selectGeneralUser.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("getOne_For_Update".equals(action)) { // ��s�@��|�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer userId = new Integer(req.getParameter("userId"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				GeneralUserService generalUserSvc = new GeneralUserService();
				GeneralUserVO generalUserVO = generalUserSvc.getOneGeneralUser(userId);
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("generalUserVO", generalUserVO); // ��Ʈw���X��generalUserVO����,�s�Jreq
				String url = "/generalUser/update_generalUser_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
				return;

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/selectGeneralUser.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_generalUser_input.jsp���ШD
			System.out.println("���Y��action��update");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				/* ����Q��s���Ѽ�(�_) */
				Integer userId = new Integer(req.getParameter("userId").trim());
				System.out.println(userId);
				Integer registerStatus = new Integer(req.getParameter("registerStatus").trim());
				System.out.println(registerStatus);
				String userAccount = req.getParameter("userAccount").trim();
				System.out.println(userAccount);
				String userPassword = req.getParameter("userPassword").trim(); /* �Pı�n�������쥻��,�p�n���A��浹�M����K�X�� */
				System.out.println(userPassword);
				String userName = req.getParameter("userName").trim();
				System.out.println(userName);
				String id = req.getParameter("id").trim();
				String email = req.getParameter("email").trim();
				Timestamp createdTime = java.sql.Timestamp
						.valueOf(req.getParameter("createdTime"));/* ��String��Timestamp */
				Integer gender = new Integer(req.getParameter("gender").trim());
				/* ����Q��s���Ѽ�(��) */

				/* �i�H�Q��s���Ѽ�(�_) */
				String phone = req.getParameter("phone");
				String phoneReg = "^[0-9]{10}$"; // �O�ocheck
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

				byte[] profilePic = req.getParameter("profilePic").getBytes();

//				

				/* �i�H�Q��s���Ѽ�(��) */

				/* �ΤW���s���ѼƳЫؤ@��VO */
				System.out.println("170���Q����");
				GeneralUserVO generalUserVO = new GeneralUserVO();
				System.out.println("��new�X�@��VO");
				generalUserVO.setUserId(userId);
				generalUserVO.setRegisterStatus(registerStatus);
				generalUserVO.setUserAccount(userAccount);
				generalUserVO.setUserPassword(userPassword);
				generalUserVO.setUserName(userName);
				generalUserVO.setId(id);
				generalUserVO.setGender(gender);
				generalUserVO.setEmail(email);
				generalUserVO.setPhone(phone);
				generalUserVO.setAddress(address);
				generalUserVO.setProfilePic(profilePic);
				generalUserVO.setCreatedTime(createdTime); 
				System.out.println(generalUserVO); 
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("����");
					req.setAttribute("generalUserVO", generalUserVO); // �t����J�榡���~��corpUserVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/generalUser/generalUserEdit.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				GeneralUserService generalUserSvc = new GeneralUserService();
				generalUserVO = generalUserSvc.updateProfile(userId, userPassword, phone, address);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				session.setAttribute("currentG", generalUserVO); // ��Ʈwupdate���\��,���T����corpUserVO����,�s�Jreq
				String url = "/generalUser/generalProfile.jsp";
				System.out.println("�����,�ǳưe");
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,��^generalProfile
				successView.forward(req, res);
				return;
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				
				System.out.println(e.getMessage()); 
				RequestDispatcher failureView = req.getRequestDispatcher("/generalUser/generalUserEdit.jsp");
				failureView.forward(req, res);
				return; 
			}
		}

//����U���ʧ@�����ܦ� �s�W�@�Ӥ@��|��
		if ("GeneralUserSignUp".equals(action)) { // �Ӧ�login.jsp���ШD

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				/* ����Q��s���Ѽ�(�_) */

				Integer registerStatus = new Integer(0);// ���U�@�w�w�]�O0

				String userAccount = req.getParameter("userAccount").trim();
				String userAccountReg = "^[(a-zA-Z0-9)]{8,30}$";
				if (userAccount == null || userAccount.trim().length() == 0) {
					errorMsgs.put("userAccount", "�b��: �ФŪť�");
				} else if (!userAccount.trim().matches(userAccountReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("userAccount", "�b��:�u��O�^��B�Ʀr�A�B���ץ��ݦb8��30����");
				}

				String userPassword = req.getParameter("userPassword").trim();
				String userPasswordReg = "^[(a-zA-Z0-9)]{8,30}$";
				if (userPassword == null || userPassword.trim().length() == 0) {
					errorMsgs.put("userPassword", "�K�X: �ФŪť�");
				} else if (!userPassword.trim().matches(userPasswordReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("userPassword", "�K�X:�u��O�^��B�Ʀr �A�B���ץ��ݦb8��30����");
				}

				String userName = req.getParameter("userName").trim();
				String userNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z)]{2,10}$";
				if (userName == null || userName.trim().length() == 0) {
					errorMsgs.put("userName", "�m�W: �ФŪť�");
				} else if (!userName.trim().matches(userNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("userName", "�m�W:�u��O�^��B����A�B���ץ��ݦb2��10����");
				}

				String id = req.getParameter("id").trim();
				String idReg = "^[(a-zA-Z0-9)]{10}$";
				if (id == null || id.trim().length() == 0) {
					errorMsgs.put("id", "������: �ФŪť�");
				} else if (!id.trim().matches(idReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("id", "������:�u��O�^��r���B�Ʀr�A�B���ץ��ݬ�10");
				}

				String email = req.getParameter("email").trim(); // �Ϋe�ݨӧP�_���T�P�_
				Timestamp createdTime = new Timestamp(System.currentTimeMillis());// jsp��ܮɶ��ϥ�Simpleformat
				Integer gender = new Integer(req.getParameter("gender").trim());

				byte[] profilePic = null;
				/* ����Q��s���Ѽ�(��) */

				/* �i�H�Q��s���Ѽ�(�_) */
				String phone = req.getParameter("phone");
				String phoneReg = "^[0-9]{10}$";
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.put("phone", "�s���q��:�ФŪť�");
				} else if (!phone.trim().matches(phoneReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("phone", "�s���q��:�u��Ʀr�B���ץ��ݬ�10");
				}

				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,30}$";
				if (address == null || address.trim().length() == 0) {
					errorMsgs.put("address", "�a�}: �ФŪť�");
				} else if (!address.trim().matches(addressReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.put("address", "�a�}: �u��O���B�^��B�Ʀr�A�B���ץ��ݦb1��30����");
				}

				/* �i�H�Q��s���Ѽ�(��) */

				/* �ΤW���s���ѼƳЫؤ@��VO */
				GeneralUserVO generalUserVO = new GeneralUserVO();

				generalUserVO.setRegisterStatus(registerStatus);
				generalUserVO.setUserAccount(userAccount);
				generalUserVO.setUserPassword(userPassword);
				generalUserVO.setUserName(userName);
				generalUserVO.setId(id);
				generalUserVO.setEmail(email);
				generalUserVO.setPhone(phone);
				generalUserVO.setAddress(address);
				generalUserVO.setProfilePic(null);
				generalUserVO.setCreatedTime(createdTime);
				generalUserVO.setGender(gender);

				System.out.println(generalUserVO);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("generalUserVO", generalUserVO); // �t����J�榡���~��corpUserVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/generalUser/generalProfile.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�Ыظ�� *****************************************/
				GeneralUserService generalUserSvc = new GeneralUserService();
				generalUserVO = generalUserSvc.addGeneralUser(registerStatus, userAccount, userPassword, userName, id,
						email, address, phone, profilePic, createdTime, gender);
				/*************************** 3.�Ыا���,�ǳ����(Send the Success view) *************/
				req.setAttribute("generalUserVO", generalUserVO); // ��Ʈwupdate���\��,���T����corpUserVO����,�s�Jreq
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
				return;

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.put("reason", "���U����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/generalUser/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		/***************************
		 * ���~�|�����U���\��A���������U���\�����A�A���^�n�J����
		 *************************************/
		if ("backToLogin".equals(action)) { // �Ӧ�login.jsp���ШD
			String url = "/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/*************************** �ѰO�K�X,�o�H�����U�H�c *************************************/

		if ("edit".equals(action)) { 
			GeneralUserVO member = (GeneralUserVO) session.getAttribute("currentG");
			GeneralUserService userSvc = new GeneralUserService();
			GeneralUserVO mem = userSvc.getOneGeneralUser(member.getUserId());
			req.setAttribute("currentG", mem);
			String url = "/generalUser/generalUserEdit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;

		}

		if ("back".equals(action)) { // �Ӧ�login.jsp���ШD

			String url = "/generalUser/generalUserMainPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;

		}

		if ("resetPassword".equals(action)) { // �Ӧ�login.jsp���ШD
			Integer userId = new Integer(req.getParameter("userId").trim());
			String newPassword = req.getParameter("userPassword");

			GeneralUserService userSvc = new GeneralUserService();
			userSvc.resetPassword(userId, newPassword);
			String url = "/resetPwSuccess.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;

		}

	}
}
