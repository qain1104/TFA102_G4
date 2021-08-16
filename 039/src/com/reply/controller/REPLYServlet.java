package com.reply.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.article.model.ARTICLEService;
import com.article.model.ARTICLEVO;
import com.reply.model.REPLYService;
import com.reply.model.REPLYVO;

public class REPLYServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String replyContent = req.getParameter("replyContent");
				if (replyContent == null || replyContent.trim().length() == 0) {
					errorMsgs.add("請輸入內文");
				}
				byte[] bytes = replyContent.getBytes("UTF-8");
				
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				Integer userId = new Integer(req.getParameter("userId").trim());

				REPLYService rSvc=new REPLYService();
				REPLYVO replyVO=rSvc.addReply(articleSN, userId, bytes);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/article/post.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
				res.sendRedirect(url);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/post.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("toeditr".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer replySN = new Integer(req.getParameter("replySN").trim());
				/*************************** 2.開始查詢資料 ****************************************/
				REPLYService rSvc = new REPLYService();
				REPLYVO replyVO = rSvc.getOneReply(replySN);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				req.setAttribute("replyVO", replyVO);
				/*************************** 3.準備轉交(Send the Success view) *************/
				String url ="/article/editreply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("前往修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("upeditr".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer replySN =new Integer(req.getParameter("replySN").trim());
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				String replyContent = req.getParameter("replyContent");
				if (replyContent == null || replyContent.trim().length() == 0) {
					errorMsgs.add("請輸入內文");
				}
				byte[] bytes = replyContent.getBytes("UTF-8");
				/*************************** 2.開始查詢資料 ****************************************/
				REPLYService rSvc=new REPLYService();
				REPLYVO replyVO=rSvc.getOneReply(replySN);
				
				

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				/*************************** 2.開始修改資料 *****************************************/
				replyVO=rSvc.editreply(replyVO, bytes);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("replyVO", replyVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
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
