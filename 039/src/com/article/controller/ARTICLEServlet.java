package com.article.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.article.model.*;
import com.sun.jmx.snmp.Timestamp;

public class ARTICLEServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/				
					Integer articleClass = null;
					try {
						articleClass = new Integer(req.getParameter("articleClass").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("請選擇分類.");
					}
					Integer articleType = null;
					try {
						articleType = new Integer(req.getParameter("articleType").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("請選擇類型.");
					}
					
					String articleTitle = req.getParameter("articleTitle");
					if (articleTitle == null || articleTitle.trim().length() == 0) {
						errorMsgs.add("請輸入標題");
					}
					
					String articleContent = req.getParameter("articleContent");
					if (articleContent == null || articleContent.trim().length() == 0) {
						errorMsgs.add("請輸入內文");
					}
					
					Integer userId = new Integer(req.getParameter("userId").trim());
					Integer articlePop = new Integer(req.getParameter("articlePop").trim());
					Integer articleLikes = new Integer(req.getParameter("articleLikes").trim());
					Integer articleStatus = new Integer(req.getParameter("articleStatus").trim());
					
					java.sql.Timestamp articleDate = new java.sql.Timestamp(new java.util.Date().getTime());
					java.sql.Timestamp articleUpDate = new java.sql.Timestamp(new java.util.Date().getTime());
					
					byte[] bytes = articleContent.getBytes();
					
					ARTICLEVO aVO1 = new ARTICLEVO();
					aVO1.setUserId(userId);
					aVO1.setArticleClass(articleClass);
					aVO1.setArticleType(articleType);
					aVO1.setArticleTitle(articleTitle);
					aVO1.setArticleContent(bytes);
					aVO1.setArticlePop(articlePop);
					aVO1.setArticleLikes(articleLikes);
					aVO1.setArticleDate(articleDate);
					aVO1.setArticleUpDate(articleUpDate);
					aVO1.setArticleStatus(articleStatus);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("aVO1", aVO1); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/article/post.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					ARTICLEService articleSvc = new ARTICLEService();
					aVO1 = articleSvc.addArticle(userId,articleClass,articleType,articleTitle,bytes,articlePop,articleLikes,articleDate,articleUpDate,articleStatus);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/article/article.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/article/post.jsp");
					failureView.forward(req, res);
				}
			}
		 
		 if("topost".equals(action)) {
			 String url = "/article/post.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);			
		 }
	}
}
