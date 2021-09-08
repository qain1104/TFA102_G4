package com.venue.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.venue.model.*;

@MultipartConfig
public class VenueServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("venueSN");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入場地編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/venue/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				Integer venueSN = null;
				try {
					venueSN = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("場地編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/venue/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				VenueService venueSvc = new VenueService();
				VenueVO venueVO = venueSvc.getOneVenue(venueSN);
				if (venueVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/venue/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("venueVO", venueVO); 
				String url = "/venue/listOneVenue.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer venueSN=null;
			try {
				venueSN = new Integer(req.getParameter("venueSN"));

				VenueService venueSvc = new VenueService();
				VenueVO venueVO = venueSvc.getOneVenue(venueSN);

				req.setAttribute("venueVO", venueVO); 
				String url = "/venue/update_venue.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/venue/listAllVenue.jsp?venueSN="+venueSN);
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer venueSN = new Integer(req.getParameter("venueSN"));

				VenueService venueSvc = new VenueService();
				venueSvc.deleteVenue(venueSN);

				String url = "/venue/listAllVenue.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/venue/listAllVenue.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer corpUserId = null;
				try {
					corpUserId = new Integer(req.getParameter("corpUserId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("會員請填數字.");
				}

				Integer venueStatus = 0;

				String venueName = req.getParameter("venueName");
				String venueNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (venueName == null || venueName.trim().length() == 0) {
					errorMsgs.add("場地名稱: 請勿空白");
				} else if (!venueName.trim().matches(venueNameReg)) {
					errorMsgs.add("場地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer venueClass = new Integer(req.getParameter("venueClass"));
				
				String venueInfo = req.getParameter("venueInfo").trim();
				if (venueInfo == null || venueInfo.trim().length() == 0) {
					errorMsgs.add("場地介紹請勿空白");
				}
				
				Integer venuePrice = null;
				try {
					venuePrice = new Integer(req.getParameter("venuePrice").trim());
				} catch (NumberFormatException e) {
					venuePrice = 0;
					errorMsgs.add("場地金額請填數字.");
				}
				
				String venueAddress = req.getParameter("venueAddress").trim();
				if (venueAddress == null || venueAddress.trim().length() == 0) {
					errorMsgs.add("場地地址請勿空白");
				}
				
				Integer venueAccommodate = null;
				try {
					venueAccommodate = new Integer(req.getParameter("venueAccommodate").trim());
				} catch (NumberFormatException e) {
					venueAccommodate = 0;
					errorMsgs.add("場地容納人數請填數字.");
				}
				
				String venuePhone = req.getParameter("venuePhone").trim();
				String phoneReg = "^09[0-9]{8}$";
				if (venuePhone == null || venuePhone.trim().length() == 0) {
					errorMsgs.add("場地電話: 請勿空白");
				} else if (!venuePhone.trim().matches(phoneReg)) {
					errorMsgs.add("場地電話: 必須為09開頭且共10個數字");
				}

				Part part = req.getPart("venuePic");
				InputStream in = part.getInputStream();
				byte[] b = new byte[in.available()];
				in.read(b);

				VenueVO venueVO = new VenueVO();
				venueVO.setCorpUserId(corpUserId);
				venueVO.setVenueStatus(venueStatus);
				venueVO.setVenueName(venueName);
				venueVO.setVenueClass(venueClass);
				venueVO.setVenueInfo(venueInfo);
				venueVO.setVenuePrice(venuePrice);
				venueVO.setVenueAddress(venueAddress);
				venueVO.setVenuePic(b);
				venueVO.setVenueAccommodate(venueAccommodate);
				venueVO.setVenuePhone(venuePhone);
				System.out.println(venueVO);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("venueVO", venueVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/venue/addVenue.jsp");
					failureView.forward(req, res);
					return;
				}

				VenueService venueSvc = new VenueService();
				venueVO = venueSvc.addVenue(corpUserId, venueStatus, venueName, venueClass, venueInfo, venuePrice,
						venueAddress, b, venueAccommodate, venuePhone);

				req.setAttribute("venueVO", venueVO);
				String url = "/venue/listAllVenue.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/venue/addVenue.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				Integer venueSN = new Integer(req.getParameter("venueSN"));

				Integer corpUserId = null;
				try {
					corpUserId = new Integer(req.getParameter("corpUserId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("會員編號請填數字.");
				}
				
				String venueName = req.getParameter("venueName");
				String venueNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (venueName == null || venueName.trim().length() == 0) {
					errorMsgs.add("場地名稱: 請勿空白");
				} else if (!venueName.trim().matches(venueNameReg)) {
					errorMsgs.add("場地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer venueClass = new Integer(req.getParameter("venueClass").trim());

				Integer venueStatus = new Integer(req.getParameter("venueStatus"));
				if (venueStatus != 1) {
					venueStatus = 0;
				}

				String venueInfo = req.getParameter("venueInfo").trim();
				if (venueInfo == null || venueInfo.trim().length() == 0) {
					errorMsgs.add("場地介紹請勿空白");
				}

				Integer venuePrice = null;
				try {
					venuePrice = new Integer(req.getParameter("venuePrice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("場地金額請填數字.");
				}
				
				String venueAddress = req.getParameter("venueAddress").trim();
				if (venueAddress == null || venueAddress.trim().length() == 0) {
					errorMsgs.add("場地地址請勿空白");
				}

				Integer venueAccommodate = null;
				try {
					venueAccommodate = new Integer(req.getParameter("venueAccommodate").trim());
				} catch (NumberFormatException e) {
					venueAccommodate = 0;
					errorMsgs.add("場地容納人數請填數字.");
				}

				String venuePhone = req.getParameter("venuePhone").trim();
				String phoneReg = "^09[0-9]{8}$";
				if (venuePhone == null || venuePhone.trim().length() == 0) {
					errorMsgs.add("場地電話: 請勿空白");
				} else if (!venuePhone.trim().matches(phoneReg)) {
					errorMsgs.add("場地電話: 必須為09開頭且共10個數字");
				}

				Part part = req.getPart("venuePic");
				InputStream in = part.getInputStream();
				VenueService venueSvc = new VenueService();
				byte[] b = null;
				if (in.available() > 0) {
					b = new byte[in.available()];
					in.read(b);
				} else {
					VenueVO venueVO = venueSvc.getOneVenue(venueSN);
					b = venueVO.getVenuePic();
				}
				
				VenueVO venueVO = new VenueVO();
				venueVO.setVenueSN(venueSN);
				venueVO.setCorpUserId(corpUserId);
				venueVO.setVenueStatus(venueStatus);
				venueVO.setVenueName(venueName);
				venueVO.setVenueClass(venueClass);
				venueVO.setVenueInfo(venueInfo);
				venueVO.setVenuePrice(venuePrice);
				venueVO.setVenueAddress(venueAddress);
				venueVO.setVenuePic(b);
				venueVO.setVenueAccommodate(venueAccommodate);
				venueVO.setVenuePhone(venuePhone);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("venueVO", venueVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/venue/update_venue.jsp");
					failureView.forward(req, res);
					return;
				}

				venueVO = venueSvc.updateVenue(venueSN, corpUserId, venueStatus, venueName, venueClass, venueInfo,
						venuePrice, venueAddress, b, venueAccommodate, venuePhone);

				String url = "/venue/listAllVenue.jsp";
				req.setAttribute("venueVO", venueVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/venue/update_venue.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getMyVenue".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer corpUserId = new Integer(req.getParameter("corpUserId"));

				VenueService venueSvc = new VenueService();
				VenueVO venueVO = (VenueVO) venueSvc.getMyVenue(corpUserId);

				req.setAttribute("venueVO", venueVO); 
				String url = "/venue/update_venue.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/venue/listAllVenue.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
