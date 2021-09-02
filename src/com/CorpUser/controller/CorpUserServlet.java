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
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer corpUserId = (Integer) session.getAttribute("corpUserId");
				System.out.println("拿到"+corpUserId);
				if (corpUserId == null) {
					System.out.println("沒拿到");
					errorMsgs.add("沒抓到session的corpUserId");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/corpUser/corpProfile.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				session.setAttribute("currentC", corpUserVO); 
				System.out.println("資料庫取出的corpUserVO物件,存入session");
				String url = "/corpUser/corpProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				return;
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllCorpUser.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer corpUserId = new Integer(req.getParameter("corpUserId"));

				/*************************** 2.開始查詢資料 ****************************************/
				CorpUserService corpUserSvc = new CorpUserService();
				CorpUserVO corpUserVO = corpUserSvc.getOneCorpUser(corpUserId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("corpUserVO", corpUserVO); // 資料庫取出的corpUserVO物件,存入req
				String url = "/corpUser/update_corpUser_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_corpUser_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				/* 不能被更新的參數(起) */
				Integer corpUserId = new Integer(req.getParameter("corpUserId").trim());
				Integer registerStatus = new Integer(req.getParameter("registerStatus").trim());
				String corpAccount = req.getParameter("corpAccount").trim();
				String corpPassword = req.getParameter("corpPassword").trim(); /* 感覺要先接收原本的,如要更改再轉交給專門改密碼的 */
				String companyName = req.getParameter("companyName").trim();
				String ltdNo = req.getParameter("ltdNo").trim();
				String email = req.getParameter("email").trim();
				/* 不能被更新的參數(終) */

				/* 可以被更新的參數(起) */
				String phone = req.getParameter("phone");
				String phoneReg = "^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$";
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("連絡電話: 請勿空白");
				} else if (!phone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("連絡電話: 只能數字且長度必需為10");
				}

				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,30}$";
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if (!address.trim().matches(addressReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址: 只能是中、英文字母、數字和_ , 且長度必需在1到30之間");
				}

//				byte[] profilePic = req.getParameter("profilePic").getBytes();
				byte[] profilePic = null;
				/* 可以被更新的參數(終) */

				/* 用上面新的參數創建一個VO */
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
					req.setAttribute("corpUserVO", corpUserVO); // 含有輸入格式錯誤的corpUserVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/corpUser/corpUserEdit.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				CorpUserService corpUserSvc = new CorpUserService();
				corpUserVO = corpUserSvc.updateProfile(corpUserId,corpPassword,phone,address);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				session.setAttribute("currentC", corpUserVO); // 資料庫update成功後,正確的的corpUserVO物件,存入req
				String url = "/corpUser/corpProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/corpUser/corpUserEdit.jsp");
				failureView.forward(req, res);
			}
		}

		if ("CorpUserSignUp".equals(action)) { // 來自login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				/* 不能被更新的參數(起) */

				Integer registerStatus = new Integer(0);// 註冊一定預設是0
				String corpAccount = req.getParameter("corpAccount").trim();
				CorpUserService corpUserSvc2 = new CorpUserService();
				List<CorpUserVO> list = corpUserSvc2.getAll().stream()
						.filter(e -> e.getCorpAccount().contains(corpAccount)).collect(Collectors.toList());
				String corpAccountReg = "^[(a-zA-Z0-9)]{8,16}$";
				if (corpAccount == null || corpAccount.trim().length() == 0) {
					errorMsgs.add("帳號: 請勿空白");
				} else if (!corpAccount.trim().matches(corpAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("帳號: 只能是英文字母、數字 , 且長度必需在8到16之間");
				} else if (!list.isEmpty()) {
					errorMsgs.add("帳號重複，請更改");
				}

				String corpPassword = req.getParameter("corpPassword").trim();
				String corpPasswordReg = "^[(a-zA-Z0-9)]{8,16}$";
				if (corpPassword == null || corpPassword.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!corpPassword.trim().matches(corpPasswordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 只能是英文字母、數字 , 且長度必需在8到16之間");
				}

				String companyName = req.getParameter("companyName").trim();
				String companyNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,10}$";
				if (companyName == null || companyName.trim().length() == 0) {
					errorMsgs.add("公司名稱: 請勿空白");
				} else if (!companyName.trim().matches(companyNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("公司名稱: 只能是中文、英文、數字 , 且長度必需在1到10之間");
				}

				String ltdNo = req.getParameter("ltdNo").trim();
				String ltdNoReg = "^[(a-zA-Z0-9)]{10}$";
				if (ltdNo == null || ltdNo.trim().length() == 0) {
					errorMsgs.add("公司行號: 請勿空白");
				} else if (!ltdNo.trim().matches(ltdNoReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("公司行號: 只能是英文字母、數字 , 且長度必需為10");
				}

				String email = req.getParameter("email").trim(); // 用前端來判斷正確與否
				List<CorpUserVO> list2 = corpUserSvc2.getAll().stream()
						.filter(e -> e.getEmail().contains(email)).collect(Collectors.toList());
				if(!list2.isEmpty()) {
					errorMsgs.add("信箱已註冊");
				}
				
				Timestamp createdTime = new Timestamp(System.currentTimeMillis());
				byte[] profilePic = null;
				/* 不能被更新的參數(終) */

				/* 可以被更新的參數(起) */
				String phone = req.getParameter("phone");
				String phoneReg = "^[0-9]{10}$";
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("連絡電話: 請勿空白");
				} else if (!phone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("連絡電話: 只能數字且長度必需為10");
				}

				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,30}$";
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if (!address.trim().matches(addressReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址: 只能是中、英文字母、數字和_ , 且長度必需在1到30之間");
				}

				/* 可以被更新的參數(終) */

				/* 用上面新的參數創建一個VO */
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
					req.setAttribute("corpUserVO", corpUserVO); // 含有輸入格式錯誤的corpUserVO物件,也存入req
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始創建資料 *****************************************/
				CorpUserService corpUserSvc = new CorpUserService();
				corpUserVO = corpUserSvc.addCorpUser(registerStatus, corpAccount, corpPassword, companyName, ltdNo,
						email, phone, address, profilePic, createdTime);

				/*************************** 3.創建完成,準備轉交(Send the Success view) *************/
				req.setAttribute("currentC", corpUserVO); // 資料庫update成功後,正確的的corpUserVO物件,存入req
				
				String test = "qain1104@gmail.com"; // 上線前用這個測試

				String to = email;
				String link = "http://localhost:8081/Sportify/certification?email=" + to;

				System.out.println(to);
				String subject = "啟用Sportify會員";
				String messageText = "您好，點按連結已完成認證! " + link;
				MailService mailService = new MailService();
				mailService.sendMail(test, subject, messageText);
				
				String url = "/SignUpSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/login.jsp");
				failureView.forward(req, res);
			}
		}
		/***************************
		 * 企業會員註冊成功後，先跳轉到註冊成功頁面，再跳回登入頁面
		 *************************************/
		if ("backToLogin".equals(action)) { // 來自login.jsp的請求
			String url = "/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			session.removeAttribute("errorMsgs");
			successView.forward(req, res);
		}

		/**********************************************************************************/

		if ("edit".equals(action)) { // 來自login.jsp的請求
			CorpUserVO member = (CorpUserVO) req.getSession().getAttribute("currentC");
			CorpUserService corpUserSvc = new CorpUserService();
			CorpUserVO mem = corpUserSvc.getOneCorpUser(member.getCorpUserId());
			req.setAttribute("currentC", mem);
			String url = "/corpUser/corpUserEdit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("back".equals(action)) { // 來自login.jsp的請求
			String url = "/corpUser/corpUserMainPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("resetPassword".equals(action)) { // 來自login.jsp的請求
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
