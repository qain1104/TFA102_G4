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
import com.reply.model.*;
import com.report.model.*;

public class REPORTServlet extends HttpServlet{
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
				Integer floor =new Integer(req.getParameter("floor").trim());
				Integer replySN=null;
				if(floor!=1) {
					replySN =new Integer(req.getParameter("replySN"));
				}else {
					replySN=0;
				}
				/*************************** 2.開始查詢資料 ****************************************/
				ARTICLEService artSvc = new ARTICLEService();
				ARTICLEVO articleVO = artSvc.getOneArticle(articleSN);
				
				if(floor!=1) {
					REPLYService rSvc=new REPLYService();
					REPLYVO replyVO=rSvc.getOneReply(replySN);
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
				String url ="/article/report.jsp";
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
				Integer articleSN =null;
				try {
					articleSN = new Integer(req.getParameter("articleSN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有articleSN.");
				}
				Integer floor =null;
				try {
					floor = new Integer(req.getParameter("floor").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有floor.");
				}
				Integer replySN =null;
				if(floor!=1) {
					try {
						replySN = new Integer(req.getParameter("replySN").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add(floor+"沒有replySN.");
					}
				}
				Integer userId =null;
				try {
					userId = new Integer(req.getParameter("userId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有userId.");
				}
				Integer reportClass =null;
				try {
					reportClass = new Integer(req.getParameter("reportClass").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有reportClass.");
				}
				String reportContent =null;
				try {
					reportContent = new String(req.getParameter("reportContent").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有reportContent.");
				}
				java.sql.Timestamp reportDate =new java.sql.Timestamp(new java.util.Date().getTime());
				Integer reportStatus=0;
				Integer managerId=null;
				java.sql.Timestamp reportAuditDate=null;
				
				ARTICLEService aSvc=new ARTICLEService();
				ARTICLEVO articleVO=aSvc.getOneArticle(articleSN);
				if(floor!=1) {
					REPLYService rSvc=new REPLYService();
					REPLYVO replyVO=rSvc.getOneReply(replySN);
					req.setAttribute("replyVO", replyVO);
				}
				
				
				req.setAttribute("floor", floor);
				req.setAttribute("articleVO", articleVO);
				req.setAttribute("replySN", replySN);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/article/report.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				REPORTService reportSvc =new REPORTService();
				reportSvc.addReport(articleSN, replySN, userId, reportClass, reportContent, reportDate, reportStatus, managerId, reportAuditDate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
				res.sendRedirect(url);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/report.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
