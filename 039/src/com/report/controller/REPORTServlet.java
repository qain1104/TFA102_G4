package com.report.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.*;
import com.product.model.*;
import com.reply.model.*;
import com.report.model.*;
import com.venue.model.*;

public class REPORTServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("toreport".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				Integer floor = new Integer(req.getParameter("floor").trim());
				Integer replySN = null;
				if (floor != 1) {
					replySN = new Integer(req.getParameter("replySN"));
				} else {
					replySN = 0;
				}
				/*************************** 2.開始查詢資料 ****************************************/
				ARTICLEService artSvc = new ARTICLEService();
				ARTICLEVO articleVO = artSvc.getOneArticle(articleSN);

				if (floor != 1) {
					REPLYService rSvc = new REPLYService();
					REPLYVO replyVO = rSvc.getOneReply(replySN);
					req.setAttribute("replyVO", replyVO);
				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				req.setAttribute("floor", floor);
				req.setAttribute("articleVO", articleVO);
				req.setAttribute("replySN", replySN);
				/*************************** 3.準備轉交(Send the Success view) *************/
				String url = "/article/report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("前往修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insertreport".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer articleSN = null;
				try {
					articleSN = new Integer(req.getParameter("articleSN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有articleSN.");
				}
				Integer floor = null;
				try {
					floor = new Integer(req.getParameter("floor").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有floor.");
				}
				Integer replySN = null;
				if (floor != 1) {
					try {
						replySN = new Integer(req.getParameter("replySN").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add(floor + "沒有replySN.");
					}
				}
				Integer userId = null;
				try {
					userId = new Integer(req.getParameter("userId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有userId.");
				}
				Integer reportClass = null;
				try {
					reportClass = new Integer(req.getParameter("reportClass").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有reportClass.");
				}
				String reportContent = null;
				try {
					reportContent = new String(req.getParameter("reportContent").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有reportContent.");
				}
				java.sql.Timestamp reportDate = new java.sql.Timestamp(new java.util.Date().getTime());
				Integer reportStatus = 0;
				Integer managerId = null;
				java.sql.Timestamp reportAuditDate = null;

				ARTICLEService aSvc = new ARTICLEService();
				ARTICLEVO articleVO = aSvc.getOneArticle(articleSN);
				if (floor != 1) {
					REPLYService rSvc = new REPLYService();
					REPLYVO replyVO = rSvc.getOneReply(replySN);
					req.setAttribute("replyVO", replyVO);
				}

				req.setAttribute("floor", floor);
				req.setAttribute("articleVO", articleVO);
				req.setAttribute("replySN", replySN);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/article/report.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				REPORTService reportSvc = new REPORTService();
				reportSvc.addReport(articleSN, replySN, userId, reportClass, reportContent, reportDate, reportStatus,
						managerId, reportAuditDate);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
				res.sendRedirect(url);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/report.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updatereport".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer floor = null;
				try {
					floor = new Integer(req.getParameter("floor").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有floor.");
				}
				Integer articleSN = null;
				Integer replySN = null;
				if (floor == 0) {
					try {
						articleSN = new Integer(req.getParameter("articleSN").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("沒有articleSN.");
					}
				} else {
					try {
						replySN = new Integer(req.getParameter("replySN").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("沒有replySN.");
					}
				}
				Integer reportSN = null;
				try {
					reportSN = new Integer(req.getParameter("reportSN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有reportSN.");
				}
				Integer managerId = null;
				try {
					managerId =new Integer(req.getParameter("managerId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有managerId.");
				}
				Integer reportStatus = null;
				try {
					reportStatus = new Integer(req.getParameter("reportStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有reportStatus.");
				}

				/*************************** 2.開始查詢資料 ****************************************/
				REPORTService reportSvc = new REPORTService();
				List<REPORTVO> list = null;
				if (floor == 0) {
					list = reportSvc.getReportSameArticle(articleSN);
				} else if (floor == 1) {
					list = reportSvc.getReportSameReply(replySN);
				}

				/*************************** 2.開始修改資料 *****************************************/
				for (REPORTVO reportVO : list) {
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("reportVO", reportVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}
					reportVO = reportSvc.updateManagerStatus(reportVO, reportStatus, managerId);
				}

				if (reportStatus == 1 && floor == 0) {
					ARTICLEService aSvc = new ARTICLEService();
					ARTICLEVO articleVO = aSvc.getOneArticle(articleSN);
					aSvc.updateastatus(articleVO, 1);
				} else if (reportStatus == 1 && floor == 1) {
					REPLYService rSvc = new REPLYService();
					REPLYVO replyVO = rSvc.getOneReply(replySN);
					rSvc.updateastatus(replyVO, 1);
				}
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/article/audit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("updateProduct".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer productSN = null;
				try {
					productSN = new Integer(req.getParameter("productSN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有reportSN.");
				}
				Integer productStatus = null;
				try {
					productStatus = new Integer(req.getParameter("productStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有reportSN.");
				}
				Integer whichClass = null;
				try {
					whichClass = new Integer(req.getParameter("whichClass").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有whichClass.");
				}
				/*************************** 2.開始查詢資料 ****************************************/
				ProductService pdtSvc=new ProductService();
				ProductVO pdtVO=pdtSvc.getOneProduct(productSN);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pdtVO", pdtVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/article/audit.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				/*************************** 2.開始修改資料 *****************************************/
				pdtSvc.updateProductStatus(pdtVO, productStatus);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = req.getContextPath() + "/article/audit.jsp?whichClass=" + whichClass;
				res.sendRedirect(url);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/audit.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("updateVenue".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer venueSN = null;
				try {
					venueSN = new Integer(req.getParameter("venueSN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有venueSN.");
				}
				Integer venueStatus = null;
				try {
					venueStatus = new Integer(req.getParameter("venueStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有venueStatus.");
				}
				Integer whichClass = null;
				try {
					whichClass = new Integer(req.getParameter("whichClass").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有whichClass.");
				}
				/*************************** 2.開始查詢資料 ****************************************/
				VenueService veSvc=new VenueService();
				VenueVO venueVO=veSvc.getOneVenue(venueSN);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("venueVO", venueVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				/*************************** 2.開始修改資料 *****************************************/
				veSvc.updateVenueStatus(venueVO, venueStatus);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = req.getContextPath() + "/article/audit.jsp?whichClass=" + whichClass;
				res.sendRedirect(url);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
