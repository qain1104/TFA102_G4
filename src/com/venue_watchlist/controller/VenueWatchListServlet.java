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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("venueWatchListSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���a�l�ܩ��ӽs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/venue_watchlist/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer venueWatchListSN = null;
				try {
					venueWatchListSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�l�ܩ��ӽs���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/venue_watchlist/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
			
				/***************************2.�}�l�d�߸��*****************************************/
				VenueWatchListService venueWatchListSvc = new VenueWatchListService();
				VenueWatchListVO venueWatchListVO = venueWatchListSvc.getOneVenueWatchList(venueWatchListSN);
				if (venueWatchListVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/venue_watchlist/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("venueWatchListVO", venueWatchListVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/venue_watchlist/listOneVenueWatchList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/venue_watchlist/select_page.jsp");
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
				Integer venueWatchListSN = new Integer(req.getParameter("venueWatchListSN"));
				
				/***************************2.�}�l�d�߸��****************************************/
				VenueWatchListService venueWatchListSvc = new VenueWatchListService();
				VenueWatchListVO venueWatchListVO = venueWatchListSvc.getOneVenueWatchList(venueWatchListSN);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("venueWatchListVO", venueWatchListVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/venue_watchlist/update_VenueWatchList_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/venue_watchlist/listAllVenueWatchList.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer venueWatchListSN = new Integer(req.getParameter("venueWatchListSN").trim());
				
				
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


				VenueWatchListVO venueWatchListVO = new VenueWatchListVO();
				venueWatchListVO.setVenueWatchListSN(venueWatchListSN);
				venueWatchListVO.setVenueSN(venueSN);
				venueWatchListVO.setUserId(userId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("venueWatchListVO", venueWatchListVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/venue_watchlist/update_VenueWatchList_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				VenueWatchListService venueWatchListSvc = new VenueWatchListService();
				venueWatchListVO = venueWatchListSvc.updateVenueWatchList(venueWatchListSN, venueSN, userId);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("venueWatchListVO", venueWatchListVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/venue_watchlist/listOneVenueWatchList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/venue_watchlist/update_VenueWatchList_input.jsp");
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
				

				VenueWatchListVO venueWatchListVO = new VenueWatchListVO();
				venueWatchListVO.setVenueSN(venueSN);
				venueWatchListVO.setUserId(userId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("venueWatchListVO", venueWatchListVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/venue_watchlist/addVenueWatchList.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				VenueWatchListService venueWatchListSvc = new VenueWatchListService();
				venueWatchListVO = venueWatchListSvc.addVenueWatchList(venueSN, userId);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/venue_watchlist/listAllVenueWatchList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/venue_watchlist/addVenueWatchList.jsp");
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
				Integer venueWatchListSN = new Integer(req.getParameter("venueWatchListSN"));
				
				/***************************2.�}�l�R�����***************************************/
				VenueWatchListService VWLSvc = new VenueWatchListService();
				VWLSvc.deleteVenueWatchList(venueWatchListSN);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/venue_watchlist/listAllVenueWatchList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/venue_watchlist/listAllVenueWatchList.jsp");
				failureView.forward(req, res);
			}
		}
       }
	}