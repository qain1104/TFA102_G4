package com.venue_watchlist.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.venue_watchlist.model.VenueWatchListService;
import com.venue_watchlist.model.*;

public class VenueWatchListServlet extends HttpServlet{
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
				String str = req.getParameter("venueWatchListSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入場地追蹤明細編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/venue_watchlist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer venueWatchListSN = null;
				try {
					venueWatchListSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("追蹤明細編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/venue_watchlist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
			
				/***************************2.開始查詢資料*****************************************/
				VenueWatchListService venueWatchListSvc = new VenueWatchListService();
				VenueWatchListVO venueWatchListVO = venueWatchListSvc.getOneVenueWatchList(venueWatchListSN);
				if (venueWatchListVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/venue_watchlist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("venueWatchListVO", venueWatchListVO); // 資料庫取出的empVO物件,存入req
				String url = "/venue_watchlist/listOneVenueWatchList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/venue_watchlist/select_page.jsp");
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
				Integer venueWatchListSN = new Integer(req.getParameter("venueWatchListSN"));
				
				/***************************2.開始查詢資料****************************************/
				VenueWatchListService venueWatchListSvc = new VenueWatchListService();
				VenueWatchListVO venueWatchListVO = venueWatchListSvc.getOneVenueWatchList(venueWatchListSN);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("venueWatchListVO", venueWatchListVO);         // 資料庫取出的empVO物件,存入req
				String url = "/venue_watchlist/update_VenueWatchList_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/venue_watchlist/listAllVenueWatchList.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer venueWatchListSN = new Integer(req.getParameter("venueWatchListSN").trim());
				
				
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


				VenueWatchListVO venueWatchListVO = new VenueWatchListVO();
				venueWatchListVO.setVenueWatchListSN(venueWatchListSN);
				venueWatchListVO.setVenueSN(venueSN);
				venueWatchListVO.setUserId(userId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("venueWatchListVO", venueWatchListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/venue_watchlist/update_VenueWatchList_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				VenueWatchListService venueWatchListSvc = new VenueWatchListService();
				venueWatchListVO = venueWatchListSvc.updateVenueWatchList(venueWatchListSN, venueSN, userId);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("venueWatchListVO", venueWatchListVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/venue_watchlist/listOneVenueWatchList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/venue_watchlist/update_VenueWatchList_input.jsp");
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
				

				VenueWatchListVO venueWatchListVO = new VenueWatchListVO();
				venueWatchListVO.setVenueSN(venueSN);
				venueWatchListVO.setUserId(userId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("venueWatchListVO", venueWatchListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/venue_watchlist/addVenueWatchList.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				VenueWatchListService venueWatchListSvc = new VenueWatchListService();
				venueWatchListVO = venueWatchListSvc.addVenueWatchList(venueSN, userId);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/venue_watchlist/listAllVenueWatchList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/venue_watchlist/addVenueWatchList.jsp");
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
				Integer venueWatchListSN = new Integer(req.getParameter("venueWatchListSN"));
				
				/***************************2.開始刪除資料***************************************/
				VenueWatchListService VWLSvc = new VenueWatchListService();
				VWLSvc.deleteVenueWatchList(venueWatchListSN);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/venue_watchlist/listAllVenueWatchList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/venue_watchlist/listAllVenueWatchList.jsp");
				failureView.forward(req, res);
			}
		}
       }
	}