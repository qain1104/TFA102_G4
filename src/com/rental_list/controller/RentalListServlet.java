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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("rentalListSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���ɳ�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rental_list/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer rentalListSN = null;
				try {
					rentalListSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���ɳ�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rental_list/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				RentalListService rentalListSvc = new RentalListService();
				RentalListVO rentalListVO = rentalListSvc.getOneRentalList(rentalListSN);
				if (rentalListVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rental_list/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("rentalListVO", rentalListVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/rental_list/listOneRentalList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rental_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer rentalListSN = new Integer(req.getParameter("rentalListSN"));
				
				/***************************2.�}�l�d�߸��****************************************/
				RentalListService rentalListSvc = new RentalListService();
				RentalListVO rentalListVO = rentalListSvc.getOneRentalList(rentalListSN);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("rentalListVO", rentalListVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/rental_list/update_RentalList_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
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
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer rentalListSN = new Integer(req.getParameter("rentalListSN").trim());
				
				
				Integer venueSN = null;
				try {
					venueSN = new Integer(req.getParameter("venueSN").trim());
				} catch (NumberFormatException e) {
					venueSN = 19001;
					errorMsgs.add("�ж�J���a�s��");
				}
				
				Integer userId = null;
				try {
					userId = new Integer(req.getParameter("userId").trim());
				} catch (NumberFormatException e) {
					userId = 1001;
					errorMsgs.add("�ж�J�|���s��");
				}
				
//				Integer returnStatus = null;
//				try {
//					returnStatus = new Integer(req.getParameter("returnStatus").trim());
//				} catch (NumberFormatException e) {
//					returnStatus = 1;
//					errorMsgs.add("�ж�J�k�٪��A");
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
					errorMsgs.add("�п�J���!");
				}
				
				String venueReview = req.getParameter("venueReview").trim();
				if (venueReview == null || venueReview.trim().length() == 0) {
					errorMsgs.add("���a���׽ФŪť�");
				}	
				
				String rentalTime = req.getParameter("rentalTime").trim();
				if (rentalTime == null || rentalTime.trim().length() == 0) {
					errorMsgs.add("���ɮɬq�ФŪť�");
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
					req.setAttribute("rentalListVO", rentalListVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rental_list/update_RentalList_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				rentalListVO = rentalListSvc.updateRentalList(rentalListSN, venueSN, userId, returnStatus, rentalDate, 
						venueReview, BU, AU, rentalTime);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("rentalListVO", rentalListVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/rental_list/listOneRentalList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rental_list/update_RentalList_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
//				Integer rentalListSN = new Integer(req.getParameter("rentalListSN"));
				
				Integer venueSN = null;
				try {
					venueSN = new Integer(req.getParameter("venueSN").trim());
				} catch (NumberFormatException e) {
//					venueSN = 19001;
					errorMsgs.add("�ж�J���a�s��");
				}
				
				Integer userId = null;
				try {
					userId = new Integer(req.getParameter("userId").trim());
				} catch (NumberFormatException e) {
//					userId = 1001;
					errorMsgs.add("�ж�J�|���s��");
				}
				
//				Integer returnStatus = null;
//				try {
//					returnStatus = new Integer(req.getParameter("returnStatus").trim());
//				} catch (NumberFormatException e) {
//					returnStatus = 1;
//					errorMsgs.add("�ж�J�k�٪��A");
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
					errorMsgs.add("�п�J���!");
				}
				
				String venueReview = req.getParameter("venueReview").trim();
				if (venueReview == null || venueReview.trim().length() == 0) {
					errorMsgs.add("���a���׽ФŪť�");
				}	
				
				String rentalTime = req.getParameter("rentalTime").trim();
				if (rentalTime == null || rentalTime.trim().length() == 0) {
					errorMsgs.add("���ɮɬq�ФŪť�");
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
					req.setAttribute("rentalListVO", rentalListVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rental_list/addRentalList.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				RentalListService rentalListSvc = new RentalListService();
				rentalListVO = rentalListSvc.addRentalList(venueSN, userId, returnStatus, rentalDate, 
						venueReview, BU, AU, rentalTime);
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/venue/listAllVenue.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rental_list/addRentalList.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer rentalListSN = new Integer(req.getParameter("rentalListSN"));
				
				/***************************2.�}�l�R�����***************************************/
				RentalListService rentalListSvc = new RentalListService();
				rentalListSvc.deleteRentalList(rentalListSN);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/rental_list/listAllRentalList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rental_list/listAllRentalList.jsp");
				failureView.forward(req, res);
			}
		}
		if ("NewRentalList".equals(action)) {
			Integer venueSN = new Integer(req.getParameter("venueSN"));
			req.getSession().setAttribute("venueSN", venueSN);
			String location = "/rental_list/addRentalList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(location);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
		}
		if ("NewVWL".equals(action)) {
			Integer venueSN = new Integer(req.getParameter("venueSN"));
			req.getSession().setAttribute("venueSN", venueSN);
			String location = "/venue_watchlist/addVenueWatchList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(location);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
		}
	}
}