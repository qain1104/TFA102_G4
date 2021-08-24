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
		if ("login".equals(action) && "general".equals(check)) { // 登入

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String inputAccount = req.getParameter("inputAccount");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號或信箱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String inputPassword = req.getParameter("inputPassword");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				// 先找一般會員
				GeneralUserService generalUserSvc = new GeneralUserService();
				List<GeneralUserVO> list = generalUserSvc.getAll();
				GeneralUserVO temp = null;
				if (list == null) {
					errorMsgs.add("資料庫無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				for (GeneralUserVO each : list) {
					if (each.getUserAccount().equals(inputAccount) || each.getEmail().equals(inputAccount)) {
						if (each.getUserPassword().equals(inputPassword)) {
							System.out.println("登入成功");
							temp = each;
							/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
							session.setAttribute("tempAccount", inputAccount);
							session.setAttribute("tempPassword", inputPassword);
							session.setAttribute("generalUserVO", temp);

							String url = "/generalUser/generalUserMainPage.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
							successView.forward(req, res);

						} else {
							errorMsgs.add("帳號或密碼錯誤");
							String url = "/login.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
							successView.forward(req, res);
							return;
						}
					}
					/*************************** 其他可能的錯誤處理 *************************************/
				}
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("login".equals(action) && "corp".equals(check)) { // 登入

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 2.開始查詢資料 *****************************************/
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String inputAccount = req.getParameter("inputAccount");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號或信箱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String inputPassword = req.getParameter("inputPassword");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				// 再找企業會員
				CorpUserService corpUserSvc = new CorpUserService();
				List<CorpUserVO> list = corpUserSvc.getAll();
				CorpUserVO temp = null;
				if (list == null) {
					errorMsgs.add("資料庫無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				for (CorpUserVO each : list) {
					if (each.getCorpAccount().equals(inputAccount) || each.getEmail().equals(inputAccount)) {
						if (each.getCorpPassword().equals(inputPassword)) {
							System.out.println("登入成功");
							temp = each;
							/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
							session.setAttribute("tempAccount", inputAccount);
							session.setAttribute("tempPassword", inputPassword);
							session.setAttribute("corpUserVO", temp);

							String url = "/corpUser/corpUserMainPage.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
							successView.forward(req, res);
						} else {
							errorMsgs.add("帳號或密碼錯誤");
							String url = "/login.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
							successView.forward(req, res);
							return;
						}
					}
				}
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
				failureView.forward(req, res);
				return;
			}

		}
		/*************************************************************************************************************/
		if ("managerIn".equals(action)) { // 登入

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 2.開始查詢資料 *****************************************/
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String inputAccount = req.getParameter("inputAccount");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號或信箱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/managerLogin.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String inputPassword = req.getParameter("inputPassword");
				if (inputAccount == null || (inputAccount.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/managerLogin.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				// 再找企業會員
				WebManagerService webManagerSvc = new WebManagerService();
				List<WebManagerVO> list = webManagerSvc.getAll();
				WebManagerVO temp = null;
				if (list == null) {
					errorMsgs.add("資料庫無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/managerLogin.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				for (WebManagerVO each : list) {
					if (each.getManagerAccount().equals(inputAccount) || each.getManagerEmail().equals(inputAccount)) {
						if (each.getManagerPassword().equals(inputPassword)) {
							System.out.println("登入成功");
							temp = each;
							/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
							session.setAttribute("tempAccount", inputAccount);
							session.setAttribute("tempPassword", inputPassword);
							session.setAttribute("webManagerVO", temp);

							String url = "/webManager/webManagerMainPage.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
							successView.forward(req, res);
						} else {
							errorMsgs.add("帳號或密碼錯誤");
							String url = "/managerLogin.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
							successView.forward(req, res);
							return;
						}
					}
				}
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
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
		if ("getOne_generalUser".equals(action)) { // 查單筆一般會員

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("userId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入一般會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneGeneralUser.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer userId = null;
				try {
					userId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneGeneralUser.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				GeneralUserService generalUserSvc = new GeneralUserService();
				GeneralUserVO generalUserVO = generalUserSvc.getOneGeneralUser(userId);
				if (generalUserVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("generalUserVO", generalUserVO); // 資料庫取出的generalUserVO物件,存入req
				String url = "/webManager/listOneGeneralUser.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/selectGeneralUser.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("getOne_corpUser".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("corpUserId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入企業會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneCorpUser.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer corpUserId = null;
				try {
					corpUserId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneCorpUser.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CorpUserService corpUserSvc = new CorpUserService();
				CorpUserVO corpUserVO = corpUserSvc.getOneCorpUser(corpUserId);
				if (corpUserVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("corpUserVO", corpUserVO); // 資料庫取出的corpUserVO物件,存入req
				String url = "/webManager/listOneCorpUser.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
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
		if ("getOne_manager".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("managerId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneManager.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer managerId = null;
				try {
					managerId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/listOneManager.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				WebManagerService webManagerSvc = new WebManagerService();
				WebManagerVO webManagerVO = webManagerSvc.getOneWebManager(managerId);
				if (webManagerVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("webManagerVO", webManagerVO); // 資料庫取出的corpUserVO物件,存入req
				String url = "/webManager/listOneManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/webManager/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("insert".equals(action)) { // 來自login.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				/* 不能被更新的參數(起) */

				Integer managerStatus = new Integer(0);// 註冊一定預設是0

				String managerAccount = req.getParameter("managerAccount").trim();
				String managerAccountReg = "^[(a-zA-Z0-9)]{8,30}$";
				if (managerAccount == null || managerAccount.trim().length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				} else if (!managerAccount.trim().matches(managerAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號: 只能是英文字母、數字 , 且長度必需在8到30之間");
				}

				String managerPassword = req.getParameter("managerPassword").trim();
				String managerPasswordReg = "^[(a-zA-Z0-9)]{8,30}$";
				if (managerPassword == null || managerPassword.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!managerPassword.trim().matches(managerPasswordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 只能是英文字母、數字 , 且長度必需在8到30之間");
				}

				String managerName = req.getParameter("managerName").trim();
				String managerNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,10}$";
				if (managerName == null || managerName.trim().length() == 0) {
					errorMsgs.add("管理員名稱: 請勿空白");
				} else if (!managerName.trim().matches(managerNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員名稱: 只能是中文、英文、數字 , 且長度必需在1到10之間");
				}

				String managerEmail = req.getParameter("managerEmail").trim(); // 用前端來判斷正確與否
				byte[] managerPic = null;
				/* 不能被更新的參數(終) */

				/* 用上面新的參數創建一個VO */
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
					return; // 程式中斷
				}

				/*************************** 2.開始創建資料 *****************************************/
				WebManagerService webManagersvc = new WebManagerService();
				webManagerVO = webManagersvc.addWebManager(managerName, managerEmail, managerAccount, managerPassword,
						managerPic, managerStatus);
				System.out.println(webManagerVO);
				/*************************** 3.創建完成,準備轉交(Send the Success view) *************/
				req.setAttribute("webManagerVO", webManagerVO); // 資料庫update成功後,正確的的corpUserVO物件,存入req
				String url = "/webManager/managers_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/webManager/addManager.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("managerId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("573");
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/webManagerMainPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer managerId = null;
				try {
					managerId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/webManagerMainPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				WebManagerService webManagerSvc = new WebManagerService();
				WebManagerVO webManagerVO = webManagerSvc.getOneWebManager(managerId);
				System.out.println(webManagerVO);
				if (webManagerVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("602");
					RequestDispatcher failureView = req.getRequestDispatcher("/webManager/webManagerMainPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("webManagerVO", webManagerVO); // 資料庫取出的corpUserVO物件,存入req
				String url = "/webManager/managerProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/webManager/managerProfile.jsp");
				failureView.forward(req, res);
			}
		}
		/*************************************************************************************************************/
		if ("edit".equals(action)) { // 來自login.jsp的請求
			WebManagerVO manager = (WebManagerVO) req.getSession().getAttribute("webManagerVO");
			WebManagerService managerSvc = new WebManagerService();
			WebManagerVO temp = managerSvc.getOneWebManager(manager.getManagerId());
			req.setAttribute("webManagerVO", temp);
			String url = "/webManager/managerEdit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		/*************************************************************************************************************/
		if ("back".equals(action)) { // 來自login.jsp的請求

			String url = "/webManager/webManagerMainPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		/*************************************************************************************************************/
		if ("update".equals(action)) { // 來自update_generalUser_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer managerStatus = new Integer(0);// 註冊一定預設是0
				Integer managerId = new Integer(req.getParameter("managerId"));
				String managerAccount = req.getParameter("managerAccount").trim();
				String managerAccountReg = "^[(a-zA-Z0-9)]{6,30}$";
				if (managerAccount == null || managerAccount.trim().length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				} else if (!managerAccount.trim().matches(managerAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號: 只能是英文字母、數字 , 且長度必需在6到30之間");
				}

				String managerPassword = req.getParameter("managerPassword").trim();
				String managerPasswordReg = "^[(a-zA-Z0-9)]{6,30}$";
				if (managerPassword == null || managerPassword.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!managerPassword.trim().matches(managerPasswordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 只能是英文字母、數字 , 且長度必需在6到30之間");
				}

				String managerName = req.getParameter("managerName").trim();
				String managerNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				if (managerName == null || managerName.trim().length() == 0) {
					errorMsgs.add("管理員名稱: 請勿空白");
				} else if (!managerName.trim().matches(managerNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員名稱: 只能是中文、英文、數字 , 且長度必需在1到20之間");
				}

				String managerEmail = req.getParameter("managerEmail").trim(); // 用前端來判斷正確與否
				byte[] managerPic = req.getParameter("managerPic").getBytes();
				/* 不能被更新的參數(終) */

				/* 用上面新的參數創建一個VO */
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
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				WebManagerService webManagerSvc = new WebManagerService();
				webManagerVO = webManagerSvc.updateProfile(managerId, managerPassword);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("webManagerVO", webManagerVO); // 資料庫update成功後,正確的的corpUserVO物件,存入req
				String url = "/webManager/managerProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/webManager/managerEdit.jsp");
				failureView.forward(req, res);
			}
		}

		/*************************** 忘記密碼,發信給註冊信箱 *************************************/
		if ("forgetPassword".equals(action)) { // 來自login.jsp的請求
			String test = "qain1104@gmail.com"; // 上線前用這個測試

			String to = req.getParameter("resetEmail");
			String link = "http://localhost:8081/Sportify/forgetPassword?resetEmail=" + to;

			System.out.println(to);
			String subject = "請重新設定密碼";
			String messageText = "Hello!點此重新設定密碼 " + link;
			MailService mailService = new MailService();
			mailService.sendMail(test, subject, messageText);
		}
		
		if ("backToLogin".equals(action)) { // 來自login.jsp的請求
			String url = "/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
