package com.sportsgroup.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.morder.util.DateTransform;
import com.participant.model.ParticipantService;
import com.participant.model.ParticipantVO;
import com.sportsgroup.model.SportsGroupService;
import com.sportsgroup.model.SportsGroupVO;

import Mail.MailService;


public class SportsGroupServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("sportsGroupSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���νs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer sportsGroupSN = null;
				try {
					sportsGroupSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���νs���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				/*************************** 2.�}�l�d�߸�� *****************************************/
				SportsGroupService sportsGroupSvc = new SportsGroupService();
				SportsGroupVO sportsGroupVO = sportsGroupSvc.getOneSportsGroup(sportsGroupSN);
				if (sportsGroupVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("sportsGroupVO", sportsGroupVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/sportsGroup/listOneSportsGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
				return;
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

		}



		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			try {

				Integer userId = new Integer(req.getParameter("userId").trim());
				System.out.println(userId);

				String sportsType = req.getParameter("sportsType");

				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (sportsType == null || sportsType.trim().length() == 0) {
					errorMsgs.add("�B�������ФŪť�");
				} else if (!sportsType.trim().matches(enameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�B������ �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				System.out.println(sportsType);

				String sportsLocation = req.getParameter("sportsLocation").trim();
				if (sportsLocation == null || sportsLocation.trim().length() == 0) {
					errorMsgs.add("�B�ʦa�I�ФŪť�");
				}
				System.out.println(sportsLocation);

				Timestamp exerciseTime = null;
				try {
					exerciseTime = Timestamp.valueOf(req.getParameter("exerciseTime").trim() + ":00");
				} catch (IllegalArgumentException e) {
					exerciseTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				System.out.println(req.getParameter("exerciseTime").trim() + ":00");

				String nUL = req.getParameter("numberUpLimit");
				if (nUL == null || (nUL.trim()).length() == 0) {
					errorMsgs.add("�п�J�H�ƤW��");
				}
				System.out.println(nUL);

				String nLL = req.getParameter("numberLowLimit");
				if (nLL == null || (nLL.trim()).length() == 0) {
					errorMsgs.add("�п�J�H�Ƴִ̤X�H");
				}

				Integer numberUpLimit = null;
				try {
					numberUpLimit = new Integer(nUL);
				} catch (NumberFormatException e) {
					errorMsgs.add("�H�ƤW���ж�Ʀr.");
				}

				Integer numberLowLimit = null;
				try {
					numberLowLimit = new Integer(nLL);
				} catch (NumberFormatException e) {

					errorMsgs.add("�}�ΤH�ƽж�Ʀr.");
				}

				if (numberUpLimit <= numberLowLimit) {
					errorMsgs.add("�H�ƤW�����i�p��H�ƤU��");
				}
				System.out.println(nLL);

//			���q�e������2021-08-14 00:00��2021-08-20 00:00
				String registTimeAndEnd = req.getParameter("registTimeAndEnd");
				System.out.println(registTimeAndEnd);
				System.out.println(registTimeAndEnd.substring(0, 16) + ":00");

				java.sql.Timestamp registTime = Timestamp.valueOf(registTimeAndEnd.substring(0, 16) + ":00");

				System.out.println(registTimeAndEnd.substring(17) + ":00");

				java.sql.Timestamp registTimeEnd = Timestamp.valueOf(registTimeAndEnd.substring(17) + ":00");
//				System.out.println(req.getParameter("registTime"));
//				java.sql.Timestamp registTimeEnd = Timestamp.valueOf(req.getParameter("registTimeAndEnd").substring(4));
//				java.sql.Timestamp registTimeEnd = Timestamp.valueOf(req.getParameter("registTimeEnd"));

				Timestamp issueDATE = new Timestamp(System.currentTimeMillis());

				Integer participantNumber = 1;

				Integer success = 0;
				System.out.println(success);

				String remarks = req.getParameter("remarks");
				System.out.println("�o��");
				/*************************** 2.�}�l�s�W��� ***************************************/
				SportsGroupService sportsGroupSvc = new SportsGroupService();

				SportsGroupVO sportsGroupVO = sportsGroupSvc.addSportGroup(userId, sportsType, sportsLocation,
						exerciseTime, numberUpLimit, numberLowLimit, registTime, registTimeEnd, issueDATE,
						participantNumber, success, remarks);
				System.out.println("�]��o�̤F");
				ParticipantService participantSvc = new ParticipantService();
				System.out.println(sportsGroupVO);
				System.out.println("jqjqjqjqjqjqjqjq");
				participantSvc.addParticipant(sportsGroupVO.getSportsGroupSN(), userId);
				System.out.println("�]��o�̤F");
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				successMsgs.add("�o�_���Φ��\");
				String url = "/sportsGroup/sportsGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);
				return;
				/*************************** ��L�i�઺���~�B�z **********************************/

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/sportsGroup.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("join".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer userId = new Integer(req.getParameter("userId").trim());
				Integer sportsGroupSN = new Integer(req.getParameter("sportsGroupSN"));
				Integer numberUpLimit = new Integer(req.getParameter("numberUpLimit"));
				Integer participantNumber = new Integer(req.getParameter("participantNumber"));

				System.out.println(userId);
				System.out.println(sportsGroupSN);
				System.out.println("numberUpLimit"+numberUpLimit);
				System.out.println("participantNumber"+participantNumber);
				
				

				ParticipantService participantSVC = new ParticipantService();
				List<ParticipantVO> list = participantSVC.getParticipant(sportsGroupSN);
				
				if (numberUpLimit.equals(participantNumber)) {
					errorMsgs.add("�H�Ƥw���п�ܨ�L���Χa");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sportsGroup/sportsGroup.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/*************************** 2.�}�l�ק��� *****************************************/
				if (list.stream().noneMatch(e -> userId.equals(e.getUserId()))) {
					participantSVC.addParticipant(sportsGroupSN, userId);
				} else {
					errorMsgs.add("�S�[�J���\�A�w�b���θ�");
				}
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				successMsgs.add("�ѥ[���Φ��\");
				String url = "/sportsGroup/sportsGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);
				return;
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/article/testerror.jsp");
				failureView.forward(req, res);
				return;
			}

		}
		

		if ("leave".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			List<String> successMsgs = new LinkedList<String>();
			req.setAttribute("successMsgs", successMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer userId = new Integer(req.getParameter("userId").trim());
				Integer sportsGroupSN = new Integer(req.getParameter("sportsGroupSN"));
				Integer suserId = new Integer(req.getParameter("suserId"));
                Integer whichClass = new Integer(req.getParameter("whichClass").trim());
				System.out.println("sportsGroupSN" + sportsGroupSN);
				System.out.println("userId" + userId);
				System.out.println("suserId" + suserId);
				System.out.println(userId.equals(suserId));
				if (userId.equals(suserId)) {
					errorMsgs.add("�A�ۤv�N�O���ΤH�L�k�h�X");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sportsGroup/sportsGroup.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				ParticipantService participantSvc = new ParticipantService();
				Integer participantId = participantSvc.getParticipantID(sportsGroupSN, userId);

				/*************************** 2.�}�l�R����� ***************************************/

				participantSvc.deleteParticipant(participantId);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = req.getContextPath() + "/sportsGroup/sportsGroup.jsp?whichClass=" + whichClass;
				
				res.sendRedirect(url);
				return;

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sportsGroup/sportsGroup.jsp");
				failureView.forward(req, res);
				return;
			}

		}
		
		
		if ("sendmail".equals(action)) {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String subject = req.getParameter("subject");
			String text = req.getParameter("message");
			System.out.println(name+email+subject+text);
			
			
			String to = "tfa102sportify@gmail.com";
			String title= "�����U�Ȥ������:" + subject;
			String messageText = "\n�H�H�H:"+name +"\n�Ȥ�H�c:"+ email +"\n����:" +text;
				
			MailService mailService = new MailService();
            mailService.sendMail(to, title, messageText);
			
			String sendbackto = email;
			String sendbacktitle = "�P�±z����Sportify���";
			String sendbacktext = "�ڭ̤w����A���ӫH�A�ȪA�H���|�ɧָ�z�pô�C";
			MailService mailService2 = new MailService();
            mailService2.sendMail(sendbackto, sendbacktitle, sendbacktext);
        	String url = req.getContextPath() + "/csresponse/Sucess.jsp";
    		
    		res.sendRedirect(url);
    		return;
		}
	

	}
}