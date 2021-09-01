package com.sportsgroup.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.morder.util.DateTransform;
import com.participant.model.ParticipantService;
import com.participant.model.ParticipantVO;
import com.sportsgroup.model.SportsGroupService;
import com.sportsgroup.model.SportsGroupVO;

import Mail.MailService;


public class SportsGroupServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("sportsGroupSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer sportsGroupSN = null;
				try {
					sportsGroupSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("揪團編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				SportsGroupService sportsGroupSvc = new SportsGroupService();
				SportsGroupVO sportsGroupVO = sportsGroupSvc.getOneSportsGroup(sportsGroupSN);
				if (sportsGroupVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("sportsGroupVO", sportsGroupVO); // 資料庫取出的empVO物件,存入req
				String url = "/sportsGroup/listOneSportsGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				return;
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

		}



		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			try {

				Integer userId = new Integer(req.getParameter("userId").trim());
				System.out.println(userId);

				String sportsType = req.getParameter("sportsType");

				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (sportsType == null || sportsType.trim().length() == 0) {
					errorMsgs.add("運動類型請勿空白");
				} else if (!sportsType.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("運動類型 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				System.out.println(sportsType);

				String sportsLocation = req.getParameter("sportsLocation").trim();
				if (sportsLocation == null || sportsLocation.trim().length() == 0) {
					errorMsgs.add("運動地點請勿空白");
				}
				System.out.println(sportsLocation);

				Timestamp exerciseTime = null;
				try {
					exerciseTime = Timestamp.valueOf(req.getParameter("exerciseTime").trim() + ":00");
				} catch (IllegalArgumentException e) {
					exerciseTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				System.out.println(req.getParameter("exerciseTime").trim() + ":00");

				String nUL = req.getParameter("numberUpLimit");
				if (nUL == null || (nUL.trim()).length() == 0) {
					errorMsgs.add("請輸入人數上限");
				}
				System.out.println(nUL);

				String nLL = req.getParameter("numberLowLimit");
				if (nLL == null || (nLL.trim()).length() == 0) {
					errorMsgs.add("請輸入人數最少幾人");
				}

				Integer numberUpLimit = null;
				try {
					numberUpLimit = new Integer(nUL);
				} catch (NumberFormatException e) {
					errorMsgs.add("人數上限請填數字.");
				}

				Integer numberLowLimit = null;
				try {
					numberLowLimit = new Integer(nLL);
				} catch (NumberFormatException e) {

					errorMsgs.add("開團人數請填數字.");
				}

				if (numberUpLimit <= numberLowLimit) {
					errorMsgs.add("人數上限不可小於人數下限");
				}
				System.out.println(nLL);

//			先從前面拿到2021-08-14 00:00至2021-08-20 00:00
				String registTimeAndEnd = req.getParameter("registTimeAndEnd");
				System.out.println(registTimeAndEnd);
				System.out.println(registTimeAndEnd.substring(0, 16) + ":00");

				java.sql.Timestamp registTime = Timestamp.valueOf(registTimeAndEnd.substring(0, 16) + ":00");

				System.out.println(registTimeAndEnd.substring(17) + ":00");

				java.sql.Timestamp registTimeEnd = Timestamp.valueOf(registTimeAndEnd.substring(17) + ":00");
//				System.out.println(req.getParameter("registTime"));
//				java.sql.Timestamp registTimeEnd = Timestamp.valueOf(req.getParameter("registTimeAndEnd").substring(4));
//				java.sql.Timestamp registTimeEnd = Timestamp.valueOf(req.getParameter("registTimeEnd"));

				Timestamp issueDATE = new Timestamp(System.currentTimeMillis());

				Integer participantNumber = 1;

				Integer success = 0;
				System.out.println(success);

				String remarks = req.getParameter("remarks");
				System.out.println("這裡");
				/*************************** 2.開始新增資料 ***************************************/
				SportsGroupService sportsGroupSvc = new SportsGroupService();

				SportsGroupVO sportsGroupVO = sportsGroupSvc.addSportGroup(userId, sportsType, sportsLocation,
						exerciseTime, numberUpLimit, numberLowLimit, registTime, registTimeEnd, issueDATE,
						participantNumber, success, remarks);
				System.out.println("跑到這裡了");
				ParticipantService participantSvc = new ParticipantService();
				System.out.println(sportsGroupVO);
				System.out.println("jqjqjqjqjqjqjqjq");
				participantSvc.addParticipant(sportsGroupVO.getSportsGroupSN(), userId);
				System.out.println("跑到這裡了");
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				successMsgs.add("發起揪團成功");
				String url = "/sportsGroup/sportsGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				return;
				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/sportsGroup.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("join".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer userId = new Integer(req.getParameter("userId").trim());
				Integer sportsGroupSN = new Integer(req.getParameter("sportsGroupSN"));
				Integer numberUpLimit = new Integer(req.getParameter("numberUpLimit"));
				Integer participantNumber = new Integer(req.getParameter("participantNumber"));

				System.out.println(userId);
				System.out.println(sportsGroupSN);
				System.out.println("numberUpLimit"+numberUpLimit);
				System.out.println("participantNumber"+participantNumber);
				
				

				ParticipantService participantSVC = new ParticipantService();
				List<ParticipantVO> list = participantSVC.getParticipant(sportsGroupSN);
				
				if (numberUpLimit.equals(participantNumber)) {
					errorMsgs.add("人數已滿請選擇其他揪團吧");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sportsGroup/sportsGroup.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				if (list.stream().noneMatch(e -> userId.equals(e.getUserId()))) {
					participantSVC.addParticipant(sportsGroupSN, userId);
				} else {
					errorMsgs.add("沒加入成功你已在揪團裡");
				}
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				successMsgs.add("參加揪團成功");
				String url = "/sportsGroup/sportsGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				return;
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
				failureView.forward(req, res);
				return;
			}

		}
		

		if ("leave".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer userId = new Integer(req.getParameter("userId").trim());
				Integer sportsGroupSN = new Integer(req.getParameter("sportsGroupSN"));
				Integer suserId = new Integer(req.getParameter("suserId"));
                Integer whichClass = new Integer(req.getParameter("whichClass").trim());
				System.out.println("sportsGroupSN" + sportsGroupSN);
				System.out.println("userId" + userId);
				System.out.println("suserId" + suserId);
				System.out.println(userId.equals(suserId));
				if (userId.equals(suserId)) {
					errorMsgs.add("你自己就是揪團人無法退出");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sportsGroup/sportsGroup.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				ParticipantService participantSvc = new ParticipantService();
				Integer participantId = participantSvc.getParticipantID(sportsGroupSN, userId);

				/*************************** 2.開始刪除資料 ***************************************/

				participantSvc.deleteParticipant(participantId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = req.getContextPath() + "/sportsGroup/sportsGroup.jsp?whichClass=" + whichClass;
				
				res.sendRedirect(url);
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/sportsGroup.jsp");
				failureView.forward(req, res);
				return;
			}

		}
		
		
		if ("sendmail".equals(action)) {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String subject = req.getParameter("subject");
			String text = req.getParameter("message");
			System.out.println(name+email+subject+text);
			
			
			String to = "tfa102sportify@gmail.com";
			String title= "收到顧客反應表單:" + subject;
			String messageText = "\n寄信人:"+name +"\n客戶信箱:"+ email +"\n內文:" +text;
				
			MailService mailService = new MailService();
            mailService.sendMail(to, title, messageText);
			
			String sendbackto = email;
			String sendbacktitle = "感謝您提交Sportify表單";
			String sendbacktext = "我們已收到你的來信，客服人員會盡快跟您聯繫。";
			MailService mailService2 = new MailService();
            mailService2.sendMail(sendbackto, sendbacktitle, sendbacktext);
        	String url = req.getContextPath() + "/csresponse/Sucess.jsp";
    		
    		res.sendRedirect(url);
    		return;
		}
	

	}
}