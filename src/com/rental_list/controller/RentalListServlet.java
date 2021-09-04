package com.rental_list.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.rental_list.model.*;
@MultipartConfig
public class RentalListServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("rentalListSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入租借單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rental_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer rentalListSN = null;
				try {
					rentalListSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("租借單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rental_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RentalListService rentalListSvc = new RentalListService();
				RentalListVO rentalListVO = rentalListSvc.getOneRentalList(rentalListSN);
				if (rentalListVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rental_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rentalListVO", rentalListVO); // 資料庫取出的empVO物件,存入req
				String url = "/rental_list/listOneRentalList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rental_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer rentalListSN = new Integer(req.getParameter("rentalListSN"));
				
				/***************************2.開始查詢資料****************************************/
				RentalListService rentalListSvc = new RentalListService();
				RentalListVO rentalListVO = rentalListSvc.getOneRentalList(rentalListSN);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("rentalListVO", rentalListVO);         // 資料庫取出的empVO物件,存入req
				String url = "/rental_list/update_RentalList_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rental_list/listAllRentalList.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rentalListSN = new Integer(req.getParameter("rentalListSN").trim());
				
				
				Integer venueSN = null;
				try {
					venueSN = new Integer(req.getParameter("venueSN").trim());
				} catch (NumberFormatException e) {
					venueSN = 19001;
					errorMsgs.add("請填入場地編號");
				}
				
				Integer userId = null;
				try {
					userId = new Integer(req.getParameter("userId").trim());
				} catch (NumberFormatException e) {
					userId = 1001;
					errorMsgs.add("請填入會員編號");
				}
				
//				Integer returnStatus = null;
//				try {
//					returnStatus = new Integer(req.getParameter("returnStatus").trim());
//				} catch (NumberFormatException e) {
//					returnStatus = 1;
//					errorMsgs.add("請填入歸還狀態");
//				}
				Integer returnStatus = new Integer(req.getParameter("returnStatus"));
				if(returnStatus != 1) {
					returnStatus = 0;
				}
				
				java.sql.Timestamp rentalDate = null;
				try {
					rentalDate = java.sql.Timestamp.valueOf(req.getParameter("rentalDate").trim()+" 00:00:00");
				} catch (IllegalArgumentException e) {
					rentalDate=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String venueReview = req.getParameter("venueReview").trim();
				if (venueReview == null || venueReview.trim().length() == 0) {
					errorMsgs.add("場地評論請勿空白");
				}	
				
				String rentalTime = req.getParameter("rentalTime").trim();
				if (rentalTime == null || rentalTime.trim().length() == 0) {
					errorMsgs.add("租借時段請勿空白");
				}	
				
				Part part = req.getPart("beforeUse");
				InputStream in  = part.getInputStream();
				RentalListService rentalListSvc = new RentalListService();
				byte[] BU = null;
				if(in.available() > 0) {
					 BU = new byte[in.available()];
					in.read(BU);
				}else {
					RentalListVO rentalListVO = rentalListSvc.getOneRentalList(rentalListSN);
					BU = rentalListVO.getBeforeUse();
				}
				
				Part part1 = req.getPart("afterUse");
				InputStream in1  = part1.getInputStream();
				RentalListService rentalListSvc1 = new RentalListService();
				byte[] AU = null;
				if(in1.available() > 0) {
					 AU = new byte[in1.available()];
					in1.read(AU);
				}else {
					RentalListVO rentalListVO = rentalListSvc1.getOneRentalList(rentalListSN);
					AU = rentalListVO.getAfterUse();
				}
//				Part part1 = req.getPart("afterUse");
//				InputStream in1  = part1.getInputStream();
//				byte[] AU = new byte[in1.available()];
//				in1.read(AU);
				
				RentalListVO rentalListVO = new RentalListVO();
				rentalListVO.setRentalListSN(rentalListSN);
				rentalListVO.setVenueSN(venueSN);
				rentalListVO.setUserId(userId);
				rentalListVO.setReturnStatus(returnStatus);
				rentalListVO.setRentalDate(rentalDate);
				rentalListVO.setVenueReview(venueReview);
//				byte[] BU = rentalListVO.getBeforeUse();
//				rentalListVO.setBeforeUse(BU);
//				byte[] AU = rentalListVO.getAfterUse();
//				rentalListVO.setAfterUse(AU);
				rentalListVO.setBeforeUse(BU);
				rentalListVO.setAfterUse(AU);
				rentalListVO.setRentalTime(rentalTime);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentalListVO", rentalListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rental_list/update_RentalList_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				rentalListVO = rentalListSvc.updateRentalList(rentalListSN, venueSN, userId, returnStatus, rentalDate, 
						venueReview, BU, AU, rentalTime);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rentalListVO", rentalListVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/rental_list/listOneRentalList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rental_list/update_RentalList_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer rentalListSN = new Integer(req.getParameter("rentalListSN"));
				
				Integer venueSN = null;
				try {
					venueSN = new Integer(req.getParameter("venueSN").trim());
				} catch (NumberFormatException e) {
//					venueSN = 19001;
					errorMsgs.add("請填入場地編號");
				}
				
				Integer userId = null;
				try {
					userId = new Integer(req.getParameter("userId").trim());
				} catch (NumberFormatException e) {
//					userId = 1001;
					errorMsgs.add("請填入會員編號");
				}
				
//				Integer returnStatus = null;
//				try {
//					returnStatus = new Integer(req.getParameter("returnStatus").trim());
//				} catch (NumberFormatException e) {
//					returnStatus = 1;
//					errorMsgs.add("請填入歸還狀態");
//				}
				
//				Integer returnStatus = new Integer(req.getParameter("returnStatus"));
//				if(returnStatus != 1) {
//					returnStatus = 0;
//				}
				
				Integer returnStatus = 0;
				
				java.sql.Timestamp rentalDate = null;
				try {
					rentalDate = java.sql.Timestamp.valueOf(req.getParameter("rentalDate").trim()+" 00:00:00");
				} catch (IllegalArgumentException e) {
					rentalDate=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String venueReview = req.getParameter("venueReview").trim();
				if (venueReview == null || venueReview.trim().length() == 0) {
					errorMsgs.add("場地評論請勿空白");
				}	
				
				String rentalTime = req.getParameter("rentalTime").trim();
				if (rentalTime == null || rentalTime.trim().length() == 0) {
					errorMsgs.add("租借時段請勿空白");
				}	
				
				Part part = req.getPart("beforeUse");
				InputStream in  = part.getInputStream();
				byte[] BU = new byte[in.available()];
				in.read(BU);
				
				Part part1 = req.getPart("afterUse");
				InputStream in1  = part1.getInputStream();
				byte[] AU = new byte[in1.available()];
				in1.read(AU);
				
				RentalListVO rentalListVO = new RentalListVO();
				rentalListVO.setVenueSN(venueSN);
				rentalListVO.setUserId(userId);
				rentalListVO.setReturnStatus(returnStatus);
				rentalListVO.setRentalDate(rentalDate);
				rentalListVO.setVenueReview(venueReview);
				rentalListVO.setBeforeUse(BU);
				rentalListVO.setAfterUse(AU);
				rentalListVO.setRentalTime(rentalTime);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentalListVO", rentalListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rental_list/addRentalList.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RentalListService rentalListSvc = new RentalListService();
				rentalListVO = rentalListSvc.addRentalList(venueSN, userId, returnStatus, rentalDate, 
						venueReview, BU, AU, rentalTime);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/venue/listAllVenue.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rental_list/addRentalList.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer rentalListSN = new Integer(req.getParameter("rentalListSN"));
				
				/***************************2.開始刪除資料***************************************/
				RentalListService rentalListSvc = new RentalListService();
				rentalListSvc.deleteRentalList(rentalListSN);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/rental_list/listAllRentalList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rental_list/listAllRentalList.jsp");
				failureView.forward(req, res);
			}
		}
		if ("NewRentalList".equals(action)) {
			Integer venueSN = new Integer(req.getParameter("venueSN"));
			req.getSession().setAttribute("venueSN", venueSN);
			String location = "/rental_list/addRentalList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(location);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		if ("NewVWL".equals(action)) {
			Integer venueSN = new Integer(req.getParameter("venueSN"));
			req.getSession().setAttribute("venueSN", venueSN);
			String location = "/venue_watchlist/addVenueWatchList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(location);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}