package com.sportsgroup.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.morder.util.DateTransform;
import com.sportsgroup.model.SportsGroupService;
import com.sportsgroup.model.SportsGroupVO;

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
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/select_page.jsp");
				failureView.forward(req, res);
			}

		}

//		if ("getOne_For_Update".equals(action)) { // 來自listAllSportsGroup.jsp的請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				Integer sportsGroupSN = new Integer(req.getParameter("sportsGroupSN"));
//				/*************************** 2.開始查詢資料 ****************************************/
//				SportsGroupService sportsGroupSvc = new SportsGroupService();
//
//				SportsGroupVO sportsGroupVO = sportsGroupSvc.getOneSportsGroup(sportsGroupSN);
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("sportsGroupVO", sportsGroupVO);
//				String url = "/sportsGroup/update_sportsGroup_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/listAllSportsGroup.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			try {
				        
				Integer userId = new Integer(req.getParameter("userId").trim());
				System.out.println(userId);
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				
				       
				String sportsType = req.getParameter("sportsType").trim();
				if (sportsType == null || sportsType.trim().length() == 0) {
					errorMsgs.add("運動類型請勿空白");
				}else if(!sportsType.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
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
					exerciseTime = Timestamp.valueOf(req.getParameter("exerciseTime").trim()+":00");
				}catch (IllegalArgumentException e) {
					exerciseTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}	
				System.out.println(req.getParameter("exerciseTime").trim()+":00");
				          
				Integer numberUpLimit = new Integer(req.getParameter("numberUpLimit").trim());
				if(numberUpLimit == null) {
					errorMsgs.add("人數上限ㄅㄨ");
				};
				System.out.println(numberUpLimit);

				Integer numberLowLimit = new Integer(req.getParameter("numberLowLimit").trim());
				System.out.println(numberLowLimit);
//			先從前面拿到2021-08-14 00:00至2021-08-20 00:00
				String registTimeAndEnd = req.getParameter("registTimeAndEnd");
				System.out.println(registTimeAndEnd);
				System.out.println(registTimeAndEnd.substring(0,16)+":00");
				
				java.sql.Timestamp registTime = Timestamp.valueOf(registTimeAndEnd.substring(0,16)+":00");
				System.out.println();
				System.out.println(registTimeAndEnd.substring(17)+":00");
				
				java.sql.Timestamp registTimeEnd = Timestamp.valueOf(registTimeAndEnd.substring(17)+":00");
//				System.out.println(req.getParameter("registTime"));
//				java.sql.Timestamp registTimeEnd = Timestamp.valueOf(req.getParameter("registTimeAndEnd").substring(4));
//				java.sql.Timestamp registTimeEnd = Timestamp.valueOf(req.getParameter("registTimeEnd"));
				System.out.println("registTimeEnd"+registTimeEnd);
				Timestamp issueDATE = new Timestamp(System.currentTimeMillis());
				System.out.println("issueDATE"+issueDATE);
				Integer participantNumber = 0;
				System.out.println(participantNumber);
				Integer success = 0;
				System.out.println(success);
			
				String remarks = req.getParameter("remarks");
				System.out.println(remarks);
				/*************************** 2.開始新增資料 ***************************************/
				SportsGroupService sportsGroupSvc = new SportsGroupService();

				sportsGroupSvc.addSportGroup(userId, sportsType, sportsLocation, exerciseTime, numberUpLimit,
						numberLowLimit, registTime, registTimeEnd, issueDATE, participantNumber, success, remarks);
				System.out.println("跑到這裡了");
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/sportsGroup/sportsGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/sportsGroup/sportsGroup.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
