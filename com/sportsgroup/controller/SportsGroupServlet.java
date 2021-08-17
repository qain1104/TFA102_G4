package com.sportsgroup.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.sportsgroup.model.SportsGroupService;
import com.sportsgroup.model.SportsGroupVO;




public class SportsGroupServlet extends HttpServlet {

	public SportsGroupServlet() {
		super();

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String str = req.getParameter("sportsGroupSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				Integer sportsGroupSN = null;
				try {
					sportsGroupSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sportsGroup/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/***************************2.�}�l�d�߸��*****************************************/
				SportsGroupService sportsGroupSvc = new SportsGroupService();
				SportsGroupVO sportsGroupVO = sportsGroupSvc.getOneSportsGroup(sportsGroupSN);
				if (sportsGroupVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sportsGroup/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("sportsGroupVO", sportsGroupVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
			
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/sportsGroup/select_page.jsp");
				failureView.forward(req, res);
			}	
			
		}
		
		
	}

}
