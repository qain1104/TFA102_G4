package com.WebManager.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
import com.WebManager.model.WebManagerService;
import com.WebManager.model.WebManagerVO;

import Mail.MailService;
import sun.font.CreatedFontTracker;

public class WebManagerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String check = req.getParameter("class");
		HttpSession session = req.getSession();
		/*************************************************************************************************************/
		if ("login".equals(action) && "general".equals(check)) { // �n�J

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String inputAccount = req.getParameter("inputAccount");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("�п�J�b���ΫH�c");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String inputPassword = req.getParameter("inputPassword");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("�п�J�K�X");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				// ����@��|��
				GeneralUserService generalUserSvc = new GeneralUserService();
				List<GeneralUserVO> list = generalUserSvc.getAll();
				GeneralUserVO temp = null;
				if (list == null) {
					errorMsgs.add("��Ʈw�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				for (GeneralUserVO each : list) {
					if (each.getUserAccount().equals(inputAccount) || each.getEmail().equals(inputAccount)) {
						if (each.getUserPassword().equals(inputPassword)) {
							System.out.println("�n�J���\");
							temp = each;
							/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
							session.setAttribute("tempAccount", inputAccount);
							session.setAttribute("tempPassword", inputPassword);
							session.setAttribute("generalUserVO", temp);

							String url = "/generalUser/generalUserMainPage.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
							successView.forward(req, res);

						} else {
							errorMsgs.add("�b���αK�X���~");
							String url = "/login.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
							successView.forward(req, res);
							return;
						}
					}
					/*************************** ��L�i�઺���~�B�z *************************************/
				}
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("login".equals(action) && "corp".equals(check)) { // �n�J

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 2.�}�l�d�߸�� *****************************************/
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String inputAccount = req.getParameter("inputAccount");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("�п�J�b���ΫH�c");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String inputPassword = req.getParameter("inputPassword");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("�п�J�K�X");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				// �A����~�|��
				CorpUserService corpUserSvc = new CorpUserService();
				List<CorpUserVO> list = corpUserSvc.getAll();
				CorpUserVO temp = null;
				if (list == null) {
					errorMsgs.add("��Ʈw�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				for (CorpUserVO each : list) {
					if (each.getCorpAccount().equals(inputAccount) || each.getEmail().equals(inputAccount)) {
						if (each.getCorpPassword().equals(inputPassword)) {
							System.out.println("�n�J���\");
							temp = each;
							/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
							session.setAttribute("tempAccount", inputAccount);
							session.setAttribute("tempPassword", inputPassword);
							session.setAttribute("corpUserVO", temp);

							String url = "/corpUser/corpUserMainPage.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
							successView.forward(req, res);
						} else {
							errorMsgs.add("�b���αK�X���~");
							String url = "/login.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
							successView.forward(req, res);
							return;
						}
					}
				}
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
				failureView.forward(req, res);
				return;
			}

		}
		/*************************************************************************************************************/
		if ("managerIn".equals(action)) { // �n�J

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 2.�}�l�d�߸�� *****************************************/
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String inputAccount = req.getParameter("inputAccount");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("�п�J�b���ΫH�c");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/managerLogin.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String inputPassword = req.getParameter("inputPassword");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("�п�J�K�X");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/managerLogin.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				// �A����~�|��
				WebManagerService webManagerSvc = new WebManagerService();
				List<WebManagerVO> list = webManagerSvc.getAll();
				WebManagerVO temp = null;
				if (list == null) {
					errorMsgs.add("��Ʈw�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/managerLogin.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				for (WebManagerVO each : list) {
					if (each.getManagerAccount().equals(inputAccount) || each.getManagerEmail().equals(inputAccount)) {
						if (each.getManagerPassword().equals(inputPassword)) {
							System.out.println("�n�J���\");
							temp = each;
							/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
							session.setAttribute("tempAccount", inputAccount);
							session.setAttribute("tempPassword", inputPassword);
							session.setAttribute("webManagerVO", temp);

							String url = "/webManager/webManagerMainPage.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
							successView.forward(req, res);
						} else {
							errorMsgs.add("�b���αK�X���~");
							String url = "/managerLogin.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
							successView.forward(req, res);
							return;
						}
					}
				}
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/managerLogin.jsp");
				failureView.forward(req, res);
				return;
			}

		}

		if ("getMembers".equals(action)) {

			if (session.getAttribute("webManagerVO") != null) {
				String url = "/webManager/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else {
				session.invalidate();
				String url = "/managerLogin.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}

		}
		/*************************************************************************************************************/
		if ("getOne_generalUser".equals(action)) { // �d�浧�@��|��

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("userId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�@��|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneGeneralUser.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer userId = null;
				try {
					userId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneGeneralUser.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("generalUserVO", generalUserVO); // ��Ʈw���X��generalUserVO����,�s�Jreq
				String url = "/webManager/listOneGeneralUser.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/selectGeneralUser.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("getOne_corpUser".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("corpUserId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���~�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneCorpUser.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer corpUserId = null;
				try {
					corpUserId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneCorpUser.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("corpUserVO", corpUserVO); // ��Ʈw���X��corpUserVO����,�s�Jreq
				String url = "/webManager/listOneCorpUser.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/webManager/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getManagers".equals(action)) {
			if (session.getAttribute("webManagerVO") != null) {
				String url = "/webManager/managers_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else {
				session.invalidate();
				String url = "/managerLogin.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("getOne_manager".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("managerId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�޲z���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneManager.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer managerId = null;
				try {
					managerId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneManager.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				WebManagerService webManagerSvc = new WebManagerService();
				WebManagerVO webManagerVO = webManagerSvc.getOneWebManager(managerId);
				if (webManagerVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("webManagerVO", webManagerVO); // ��Ʈw���X��corpUserVO����,�s�Jreq
				String url = "/webManager/listOneManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/webManager/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("insert".equals(action)) { // �Ӧ�login.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				/* ����Q��s���Ѽ�(�_) */

				Integer managerStatus = new Integer(0);// ���U�@�w�w�]�O0

				String managerAccount = req.getParameter("managerAccount").trim();
				String managerAccountReg = "^[(a-zA-Z0-9)]{8,30}$";
				if (managerAccount == null || managerAccount.trim().length() == 0) {
					errorMsgs.add("�b��: �ФŪť�");
				} else if (!managerAccount.trim().matches(managerAccountReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�b��: �u��O�^��r���B�Ʀr , �B���ץ��ݦb8��30����");
				}

				String managerPassword = req.getParameter("managerPassword").trim();
				String managerPasswordReg = "^[(a-zA-Z0-9)]{8,30}$";
				if (managerPassword == null || managerPassword.trim().length() == 0) {
					errorMsgs.add("�K�X: �ФŪť�");
				} else if (!managerPassword.trim().matches(managerPasswordReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�K�X: �u��O�^��r���B�Ʀr , �B���ץ��ݦb8��30����");
				}

				String managerName = req.getParameter("managerName").trim();
				String managerNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,10}$";
				if (managerName == null || managerName.trim().length() == 0) {
					errorMsgs.add("�޲z���W��: �ФŪť�");
				} else if (!managerName.trim().matches(managerNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�޲z���W��: �u��O����B�^��B�Ʀr , �B���ץ��ݦb1��10����");
				}

				String managerEmail = req.getParameter("managerEmail").trim(); // �Ϋe�ݨӧP�_���T�P�_
				byte[] managerPic = null;
				/* ����Q��s���Ѽ�(��) */

				/* �ΤW���s���ѼƳЫؤ@��VO */
				WebManagerVO webManagerVO = new WebManagerVO();

				webManagerVO.setManagerName(managerName);
				webManagerVO.setManagerAccount(managerAccount);
				webManagerVO.setManagerEmail(managerEmail);
				webManagerVO.setManagerPassword(managerPassword);
				webManagerVO.setManagerPic(managerPic);
				webManagerVO.setManagerStatus(managerStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("webManagerVO", webManagerVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/addManager.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�Ыظ�� *****************************************/
				WebManagerService webManagersvc = new WebManagerService();
				webManagerVO = webManagersvc.addWebManager(managerName, managerEmail, managerAccount, managerPassword,
						managerPic, managerStatus);
				System.out.println(webManagerVO);
				/*************************** 3.�Ыا���,�ǳ����(Send the Success view) *************/
				req.setAttribute("webManagerVO", webManagerVO); // ��Ʈwupdate���\��,���T����corpUserVO����,�s�Jreq
				String url = "/webManager/managers_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�s�W��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/webManager/addManager.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("managerId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�޲z���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("573");
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/webManagerMainPage.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer managerId = null;
				try {
					managerId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/webManagerMainPage.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				WebManagerService webManagerSvc = new WebManagerService();
				WebManagerVO webManagerVO = webManagerSvc.getOneWebManager(managerId);
				System.out.println(webManagerVO);
				if (webManagerVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("602");
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/webManagerMainPage.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("webManagerVO", webManagerVO); // ��Ʈw���X��corpUserVO����,�s�Jreq
				String url = "/webManager/managerProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/webManager/managerProfile.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("edit".equals(action)) { // �Ӧ�login.jsp���ШD
			WebManagerVO manager = (WebManagerVO) req.getSession().getAttribute("webManagerVO");
			WebManagerService managerSvc = new WebManagerService();
			WebManagerVO temp = managerSvc.getOneWebManager(manager.getManagerId());
			req.setAttribute("webManagerVO", temp);
			String url = "/webManager/managerEdit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		/*************************************************************************************************************/
		if ("back".equals(action)) { // �Ӧ�login.jsp���ШD

			String url = "/webManager/webManagerMainPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		/*************************************************************************************************************/
		if ("update".equals(action)) { // �Ӧ�update_generalUser_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				Integer managerStatus = new Integer(0);// ���U�@�w�w�]�O0
				Integer managerId = new Integer(req.getParameter("managerId"));
				String managerAccount = req.getParameter("managerAccount").trim();
				String managerAccountReg = "^[(a-zA-Z0-9)]{6,30}$";
				if (managerAccount == null || managerAccount.trim().length() == 0) {
					errorMsgs.add("�b��: �ФŪť�");
				} else if (!managerAccount.trim().matches(managerAccountReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�b��: �u��O�^��r���B�Ʀr , �B���ץ��ݦb6��30����");
				}

				String managerPassword = req.getParameter("managerPassword").trim();
				String managerPasswordReg = "^[(a-zA-Z0-9)]{6,30}$";
				if (managerPassword == null || managerPassword.trim().length() == 0) {
					errorMsgs.add("�K�X: �ФŪť�");
				} else if (!managerPassword.trim().matches(managerPasswordReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�K�X: �u��O�^��r���B�Ʀr , �B���ץ��ݦb6��30����");
				}

				String managerName = req.getParameter("managerName").trim();
				String managerNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				if (managerName == null || managerName.trim().length() == 0) {
					errorMsgs.add("�޲z���W��: �ФŪť�");
				} else if (!managerName.trim().matches(managerNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�޲z���W��: �u��O����B�^��B�Ʀr , �B���ץ��ݦb1��20����");
				}

				String managerEmail = req.getParameter("managerEmail").trim(); // �Ϋe�ݨӧP�_���T�P�_
				byte[] managerPic = req.getParameter("managerPic").getBytes();
				/* ����Q��s���Ѽ�(��) */

				/* �ΤW���s���ѼƳЫؤ@��VO */
				WebManagerVO webManagerVO = new WebManagerVO();
				webManagerVO.setManagerName(managerName);
				webManagerVO.setManagerAccount(managerAccount);
				webManagerVO.setManagerEmail(managerEmail);
				webManagerVO.setManagerPassword(managerPassword);
				webManagerVO.setManagerPic(managerPic);
				webManagerVO.setManagerStatus(managerStatus);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("webManagerVO", webManagerVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/managerEdit.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				WebManagerService webManagerSvc = new WebManagerService();
				webManagerVO = webManagerSvc.updateProfile(managerId, managerPassword);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("webManagerVO", webManagerVO); // ��Ʈwupdate���\��,���T����corpUserVO����,�s�Jreq
				String url = "/webManager/managerProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/webManager/managerEdit.jsp");
				failureView.forward(req, res);
			}
		}

		/*************************** �ѰO�K�X,�o�H�����U�H�c *************************************/
		if ("forgetPassword".equals(action)) { // �Ӧ�login.jsp���ШD
			String test = "qain1104@gmail.com"; // �W�u�e�γo�Ӵ���

			String to = req.getParameter("resetEmail");
			String link = "http://localhost:8081/Sportify/forgetPassword?resetEmail=" + to;

			System.out.println(to);
			String subject = "�Э��s�]�w�K�X";
			String messageText = "Hello!�I�����s�]�w�K�X " + link;
			MailService mailService = new MailService();
			mailService.sendMail(test, subject, messageText);
		}
		
		if ("backToLogin".equals(action)) { // �Ӧ�login.jsp���ШD
			String url = "/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
