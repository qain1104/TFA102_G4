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

		if ("getOne_For_Display".equals(action)) { // 查單一一般會員

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
					RequestDispatcher failureView = req.getRequestDispatcher("/generalUser/generalProfile.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/generalUser/generalProfile.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/selectGeneralUser.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("generalUserVO", generalUserVO); // 資料庫取出的generalUserVO物件,存入req
				String url = "/generalUser/generalProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/selectGeneralUser.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 更新一般會員資料

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer userId = new Integer(req.getParameter("userId"));

				/*************************** 2.開始查詢資料 ****************************************/
				GeneralUserService generalUserSvc = new GeneralUserService();
				GeneralUserVO generalUserVO = generalUserSvc.getOneGeneralUser(userId);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("generalUserVO", generalUserVO); // 資料庫取出的generalUserVO物件,存入req
				String url = "/generalUser/update_generalUser_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/selectGeneralUser.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_generalUser_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				/* 不能被更新的參數(起) */
				Integer userId = new Integer(req.getParameter("userId").trim());
				Integer registerStatus = new Integer(req.getParameter("registerStatus").trim());
				String userAccount = req.getParameter("userAccount").trim();

				String userPassword = req.getParameter("userPassword").trim(); /* 感覺要先接收原本的,如要更改再轉交給專門改密碼的 */

				String userName = req.getParameter("userName").trim();
				String id = req.getParameter("id").trim();
				String email = req.getParameter("email").trim();
				Timestamp createdTime = java.sql.Timestamp
						.valueOf(req.getParameter("createdTime"));/* 把String轉Timestamp */
				Integer gender = new Integer(req.getParameter("gender").trim());
				/* 不能被更新的參數(終) */

				/* 可以被更新的參數(起) */
				String phone = req.getParameter("phone");
				String phoneReg = "^[0-9]{10}$"; // 記得check
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

				byte[] profilePic = req.getParameter("profilePic").getBytes();

//				

				/* 可以被更新的參數(終) */

				/* 用上面新的參數創建一個VO */
				GeneralUserVO generalUserVO = new GeneralUserVO();
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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("generalUserVO", generalUserVO); // 含有輸入格式錯誤的corpUserVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/generalUser/generalUserEdit.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				GeneralUserService generalUserSvc = new GeneralUserService();
				generalUserVO = generalUserSvc.updateProfile(userId, userPassword, phone, address);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("generalUserVO", generalUserVO); // 資料庫update成功後,正確的的corpUserVO物件,存入req
				String url = "/generalUser/generalProfile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/generalUser/generalUserEdit.jsp");
				failureView.forward(req, res);
			}
		}

//把註冊的動作直接變成 新增一個一般會員
		if ("GeneralUserSignUp".equals(action)) { // 來自login.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.

			try {
				/*************************** 1.接收請求參數 ***************************************/
				/* 不能被更新的參數(起) */

				Integer registerStatus = new Integer(0);// 註冊一定預設是0

				String userAccount = req.getParameter("userAccount").trim();
				String userAccountReg = "^[(a-zA-Z0-9)]{8,30}$";
				if (userAccount == null || userAccount.trim().length() == 0) {
					errorMsgs.put("userAccount", "帳號: 請勿空白");
				} else if (!userAccount.trim().matches(userAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("userAccount", "帳號:只能是英文、數字，且長度必需在8到30之間");
				}

				String userPassword = req.getParameter("userPassword").trim();
				String userPasswordReg = "^[(a-zA-Z0-9)]{8,30}$";
				if (userPassword == null || userPassword.trim().length() == 0) {
					errorMsgs.put("userPassword", "密碼: 請勿空白");
				} else if (!userPassword.trim().matches(userPasswordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("userPassword", "密碼:只能是英文、數字 ，且長度必需在8到30之間");
				}

				String userName = req.getParameter("userName").trim();
				String userNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z)]{2,10}$";
				if (userName == null || userName.trim().length() == 0) {
					errorMsgs.put("userName", "姓名: 請勿空白");
				} else if (!userName.trim().matches(userNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("userName", "姓名:只能是英文、中文，且長度必需在2到10之間");
				}

				String id = req.getParameter("id").trim();
				String idReg = "^[(a-zA-Z0-9)]{10}$";
				if (id == null || id.trim().length() == 0) {
					errorMsgs.put("id", "身分證: 請勿空白");
				} else if (!id.trim().matches(idReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("id", "身分證:只能是英文字母、數字，且長度必需為10");
				}

				String email = req.getParameter("email").trim(); // 用前端來判斷正確與否
				Timestamp createdTime = new Timestamp(System.currentTimeMillis());// jsp顯示時間使用Simpleformat
				Integer gender = new Integer(req.getParameter("gender").trim());

				byte[] profilePic = null;
				/* 不能被更新的參數(終) */

				/* 可以被更新的參數(起) */
				String phone = req.getParameter("phone");
				String phoneReg = "^[0-9]{10}$";
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.put("phone", "連絡電話:請勿空白");
				} else if (!phone.trim().matches(phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("phone", "連絡電話:只能數字且長度必需為10");
				}

				String address = req.getParameter("address");
				String addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,30}$";
				if (address == null || address.trim().length() == 0) {
					errorMsgs.put("address", "地址: 請勿空白");
				} else if (!address.trim().matches(addressReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("address", "地址: 只能是中、英文、數字，且長度必需在1到30之間");
				}

				/* 可以被更新的參數(終) */

				/* 用上面新的參數創建一個VO */
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
					req.setAttribute("generalUserVO", generalUserVO); // 含有輸入格式錯誤的corpUserVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/generalUser/update_generalUser_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始創建資料 *****************************************/
				GeneralUserService generalUserSvc = new GeneralUserService();
				generalUserVO = generalUserSvc.addGeneralUser(registerStatus, userAccount, userPassword, userName, id,
						email, address, phone, profilePic, createdTime, gender);
				/*************************** 3.創建完成,準備轉交(Send the Success view) *************/
				req.setAttribute("generalUserVO", generalUserVO); // 資料庫update成功後,正確的的corpUserVO物件,存入req
				String url = "/SignUpSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.put("reason", "註冊失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/generalUser/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		/***************************
		 * 企業會員註冊成功後，先跳轉到註冊成功頁面，再跳回登入頁面
		 *************************************/
		if ("backToLogin".equals(action)) { // 來自login.jsp的請求
			String url = "/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		/*************************** 忘記密碼,發信給註冊信箱 *************************************/

		if ("edit".equals(action)) { // 來自login.jsp的請求
			GeneralUserVO member = (GeneralUserVO) req.getSession().getAttribute("generalUserVO");
			GeneralUserService userSvc = new GeneralUserService();
			GeneralUserVO mem = userSvc.getOneGeneralUser(member.getUserId());
			req.setAttribute("generalUserVO", mem);
			String url = "/generalUser/generalUserEdit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("back".equals(action)) { // 來自login.jsp的請求

			String url = "/generalUser/generalUserMainPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		
		if ("resetPassword".equals(action)) { // 來自login.jsp的請求
			Integer userId = new Integer(req.getParameter("userId").trim());
			String  newPassword = req.getParameter("userPassword");
			
			GeneralUserService userSvc = new GeneralUserService();
			userSvc.resetPassword(userId, newPassword);
			String url = "/resetPwSuccess.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

	}
}
