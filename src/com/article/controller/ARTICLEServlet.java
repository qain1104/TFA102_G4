package com.article.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.article.model.*;
import com.alike.model.*;

public class ARTICLEServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");	
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.*************************/
				Integer articleClass = null;
				try {
					articleClass = new Integer(req.getParameter("articleClass").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�п�ܤ���");
				}
				Integer articleType = null;
				try {
					articleType = new Integer(req.getParameter("articleType").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�п������");
				}

				String articleTitle = req.getParameter("articleTitle");
				if (articleTitle == null || articleTitle.trim().length() == 0) {
					errorMsgs.add("�п�J���D");
				}

				String articleContent = req.getParameter("articleContent");
				if (articleContent == null || articleContent.trim().length() == 0) {
					errorMsgs.add("�п�J���e");
				}

				Integer userId = new Integer(req.getParameter("userId").trim());
				Integer articlePop = new Integer(req.getParameter("articlePop").trim());
				Integer articleLikes = new Integer(req.getParameter("articleLikes").trim());
				Integer articleStatus = new Integer(req.getParameter("articleStatus").trim());

				java.sql.Timestamp articleDate = new java.sql.Timestamp(new java.util.Date().getTime());
				java.sql.Timestamp articleUpDate = new java.sql.Timestamp(new java.util.Date().getTime());

				byte[] bytes = articleContent.getBytes("UTF-8");

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
					req.setAttribute("aVO1", aVO1);
					RequestDispatcher failureView = req.getRequestDispatcher("/article/post.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.***************************************/
				ARTICLEService articleSvc = new ARTICLEService();
				aVO1 = articleSvc.addArticle(userId, articleClass, articleType, articleTitle, bytes, articlePop,
						articleLikes, articleDate, articleUpDate, articleStatus);

				/*************************** 3.(Send the Success view) ***********/
				String url = "/article/article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** **********************************/
			} catch (Exception e) {
				errorMsgs.add("�o�奢��"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/post.jsp");
				failureView.forward(req, res);
			}
		}

		if ("topost".equals(action)) {
			String url = req.getContextPath() + "/article/post.jsp";
			res.sendRedirect(url);
		}

		if ("toedita".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.****************************************/
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				/*************************** 2. ****************************************/
				ARTICLEService artSvc = new ARTICLEService();
				ARTICLEVO articleVO = artSvc.getOneArticle(articleSN);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/article/post.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("articleVO", articleVO);
				/*************************** 3.(Send the Success view) *************/
				String url ="/article/editpost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************  *************************************/
			} catch (Exception e) {
				errorMsgs.add("�s�W����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/post.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("upedita".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.??????D??? ****************************************/
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				Integer articleClass = null;
				try {
					articleClass = new Integer(req.getParameter("articleClass").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�п�ܤ���");
				}
				Integer articleType = null;
				try {
					articleType = new Integer(req.getParameter("articleType").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�п������");
				}

				String articleTitle = req.getParameter("articleTitle");
				if (articleTitle == null || articleTitle.trim().length() == 0) {
					errorMsgs.add("�п�J���D");
				}

				String articleContent = req.getParameter("articleContent");
				if (articleContent == null || articleContent.trim().length() == 0) {
					errorMsgs.add("�п�J����");
				}
				byte[] bytes = articleContent.getBytes("UTF-8");
				/*************************** 2. ****************************************/
				ARTICLEService artSvc = new ARTICLEService();
				ARTICLEVO articleVO = artSvc.getOneArticle(articleSN);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/article/editpost.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2. *****************************************/
				artSvc.editArticle(articleVO, articleClass, articleType, articleTitle, bytes);
				/*************************** 3.(Send the Success view) *************/
				req.setAttribute("articleVO", articleVO); 
				String url = "/article/reply.jsp?sn=" + articleSN;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ??L?i?????~?B?z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/editpost.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("uppopa".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1. ****************************************/
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				/*************************** 2. ****************************************/
				ARTICLEService artSvc = new ARTICLEService();
				ARTICLEVO articleVO = artSvc.getOneArticle(articleSN);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); 
					RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath() + "/article/reply.jsp?sn=" + articleSN);
					failureView.forward(req, res);
					return; 
				}

				/*************************** 2. *****************************************/
				artSvc.updateapop(articleVO);
				/*************************** 3.(Send the Success view) *************/
				req.setAttribute("articleVO", articleVO); 
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
				res.sendRedirect(url);

				/*************************** *************************************/
			} catch (Exception e) {
				errorMsgs.add("�H��s�W����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("upstatus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1. ****************************************/
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				/*************************** 2.****************************************/
				ARTICLEService artSvc = new ARTICLEService();
				ARTICLEVO articleVO = artSvc.getOneArticle(articleSN);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/article/editpost.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2. *****************************************/
				Integer articleStatus =1;
				artSvc.updateastatus(articleVO, articleStatus);
				/*************************** 3.(Send the Success view) *************/
				req.setAttribute("articleVO", articleVO);
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
				res.sendRedirect(url);
				
				/*************************** *************************************/
			} catch (Exception e) {
				errorMsgs.add("�R������" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("alike".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Integer articleSN =null;
			try {
				/*************************** 1. ****************************************/
				articleSN =null;
				try {
					articleSN = new Integer(req.getParameter("articleSN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��articleSN.");
				}
				
				Integer loguserId =new Integer(req.getParameter("loguserId"));
				/*************************** 2. ****************************************/
				ARTICLEService artSvc = new ARTICLEService();
				ARTICLEVO articleVO = artSvc.getOneArticle(articleSN);
				ARTICLE_LIKEService alikeSvc=new ARTICLE_LIKEService();
				List<ARTICLE_LIKEVO> alikelist=alikeSvc.getArticlelike(articleSN);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath() + "/article/reply.jsp?sn=" + articleSN);
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2. *****************************************/
				if(alikelist.stream().noneMatch(e -> loguserId.equals(e.getUserId()))) {
				artSvc.addlike(articleVO);
				alikeSvc.addAlike(articleSN, loguserId);
				}else {
					artSvc.reducelike(articleVO);
					Optional<ARTICLE_LIKEVO> alikeVO=alikelist.stream().filter(e -> e.getUserId().equals(loguserId)).findFirst();
					alikeSvc.deleteAlike(alikeVO.get().getArticleLikeSN());
				}
				/*************************** 3.(Send the Success view) *************/
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
				res.sendRedirect(url);
				
				/***************************  *************************************/
			} catch (Exception e) {
				errorMsgs.add("���gor���^����" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath() + "/article/reply.jsp?sn=" + articleSN);
				failureView.forward(req, res);
			}
		}
	}
}
