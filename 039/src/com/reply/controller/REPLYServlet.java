package com.reply.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.reply.model.REPLYService;
import com.reply.model.REPLYVO;
import com.rlike.model.REPLY_LIKEService;
import com.rlike.model.REPLY_LIKEVO;

public class REPLYServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String replyContent = req.getParameter("replyContent");
				if (replyContent == null || replyContent.trim().length() == 0) {
					errorMsgs.add("�п�J����");
				}
				byte[] bytes = replyContent.getBytes("UTF-8");
				
				Integer articleSN = new Integer(req.getParameter("articleSN").trim());
				Integer userId = new Integer(req.getParameter("userId").trim());

				REPLYService rSvc=new REPLYService();
				REPLYVO replyVO=rSvc.addReply(articleSN, userId, bytes);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/article/post.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
				res.sendRedirect(url);

				/*************************** ��L�i�઺���~�B�z **********************************/
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
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer replySN = new Integer(req.getParameter("replySN").trim());
				Integer floor =new Integer(req.getParameter("floor").trim());
				/*************************** 2.�}�l�d�߸�� ****************************************/
				REPLYService rSvc = new REPLYService();
				REPLYVO replyVO = rSvc.getOneReply(replySN);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}
				req.setAttribute("floor", floor);
				req.setAttribute("replyVO", replyVO);
				/*************************** 3.�ǳ����(Send the Success view) *************/
				String url ="/article/editreply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�e���ק異��:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("upeditr".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer replySN =null;
				try {
					replySN =new Integer(req.getParameter("replySN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��replySN.");
				}
				Integer articleSN = null;
				try {
					articleSN =new Integer(req.getParameter("articleSN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��articleSN.");
				}
				String replyContent = req.getParameter("replyContent");
				if (replyContent == null || replyContent.trim().length() == 0) {
					errorMsgs.add("�п�J����");
				}
				byte[] bytes = replyContent.getBytes("UTF-8");
				/*************************** 2.�}�l�d�߸�� ****************************************/
				REPLYService rSvc=new REPLYService();
				REPLYVO replyVO=rSvc.getOneReply(replySN);
				req.setAttribute("replyVO", replyVO);
				

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/article/editreply.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}
				
				/*************************** 2.�}�l�ק��� *****************************************/
				replyVO=rSvc.editreply(replyVO, bytes);
				req.setAttribute("replyVO", replyVO);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				//req.setAttribute("replyVO", replyVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
				res.sendRedirect(url);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/editreply.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("upstatus".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer replySN =null;
				try {
					replySN =new Integer(req.getParameter("replySN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��replySN.");
				}
				Integer articleSN = null;
				try {
					articleSN =new Integer(req.getParameter("articleSN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��articleSN.");
				}
				/*************************** 2.�}�l�d�߸�� ****************************************/
				REPLYService rSvc=new REPLYService();
				REPLYVO replyVO=rSvc.getOneReply(replySN);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/article/editreply.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}
				
				/*************************** 2.�}�l�ק��� *****************************************/
				Integer articleStatus =1;
				rSvc.updateastatus(replyVO, articleStatus);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("replyVO", replyVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
				res.sendRedirect(url);
				
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/editreply.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("rlike".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer articleSN =null;
				try {
					articleSN = new Integer(req.getParameter("articleSN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��articleSN.");
				}
				Integer replySN =null;
				try {
					replySN = new Integer(req.getParameter("replySN").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��replySN.");
				}
				
				Integer loguserId =new Integer(req.getParameter("loguserId"));
				/*************************** 2.�}�l�d�߸�� ****************************************/
				REPLYService rSvc=new REPLYService();
				REPLYVO replyVO=rSvc.getOneReply(replySN);
				REPLY_LIKEService rlikeSvc=new REPLY_LIKEService();
				List<REPLY_LIKEVO> rlikelist=rlikeSvc.getReplylike(replySN);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}
				
				/*************************** 2.�}�l�ק��� *****************************************/
				if(rlikelist.stream().noneMatch(e -> loguserId.equals(e.getUserId()))) {
				rSvc.addlike(replyVO);
				rlikeSvc.addRlike(replySN, loguserId);
				}else {
					rSvc.reducelike(replyVO);
					Optional<REPLY_LIKEVO> alikeVO=rlikelist.stream().filter(e -> e.getUserId().equals(loguserId)).findFirst();
					rlikeSvc.deleteRlike(alikeVO.get().getReplyLikeSN());
				}
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				String url = req.getContextPath() + "/article/reply.jsp?sn=" + articleSN;
				res.sendRedirect(url);
				
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
