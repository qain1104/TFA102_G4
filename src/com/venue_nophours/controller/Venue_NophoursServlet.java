package com.venue_nophours.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;
import com.venue.model.VenueService;
import com.venue.model.VenueVO;
import com.venue_nophours.model.VenueNophoursService;
import com.venue_nophours.model.VenueNophoursVO;

public class Venue_NophoursServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Integer nowSN = (Integer) req.getSession().getAttribute("venueSN");
		System.out.println(action);
		if ("insertNopDate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String venueDate = req.getParameter("venueDate");
				List<String> chooseDateList = new ArrayList<String>();
				if (venueDate == null || venueDate.trim().length() == 0) {
					errorMsgs.add("場地不開放時間請勿空白");
				} else {
					String[] strs = venueDate.split(",");
					for (int i = 0; i < strs.length; i++) {
						chooseDateList.add(strs[i]);
					}

					if (!errorMsgs.isEmpty()) {
						req.setAttribute("chooseDateList", chooseDateList); // 含有輸入格式錯誤的VenueVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/venue/addNopDate.jsp");
						failureView.forward(req, res);
						return;
					}
//				/*************************** 2.開始新增資料 ***************************************/
//				
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
					req.getSession().setAttribute("chooseDateList", chooseDateList);
					String url = "/venue/addNopHours.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllVenue.jsp
					successView.forward(req, res);
				}
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/venue/addNopDate.jsp");
				failureView.forward(req, res);
			}

		}
		if ("insertNopHours".equals(action)) {
			List<String> chooseDateList = (List<String>) req.getSession().getAttribute("chooseDateList");
			VenueNophoursVO venueNophoursVO = new VenueNophoursVO();
			VenueNophoursService vnpSvc = new VenueNophoursService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (String date : chooseDateList) {
				int vnp = 0;
				Timestamp ts = null;
				Date d = null;
				String[] noOpenValues = req.getParameterValues(date);
				if (noOpenValues != null) {
					try {
						d = sdf.parse(date);
						ts = new Timestamp(d.getTime());
						System.out.println(ts);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					System.out.println(d + "-------");
					for (int i = 0; i < noOpenValues.length; i++) {
						vnp += Integer.parseInt(noOpenValues[i]);
					}
					
					vnpSvc.addVenueNophours(nowSN, ts, String.valueOf(vnp));
				
				} else {
					try {
						d = sdf.parse(date);
						ts = new Timestamp(d.getTime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					vnpSvc.addVenueNophours(nowSN, ts, String.valueOf(vnp));
				}
			}

			String location = req.getContextPath() + "/venue/listAllVenueNop.jsp?venueSN=" + nowSN;
			res.sendRedirect(location);
		}

		if ("newNopDate".equals(action)) {
			System.out.println("有吃到");
			Integer venueSN = new Integer(req.getParameter("venueSN"));
			req.getSession().setAttribute("venueSN", venueSN);
			String location = req.getContextPath() + "/venue/addNopDate.jsp";
			res.sendRedirect(location);
		}

		if ("updateNopDate".equals(action)) {
			System.out.println("有吃到");
			Integer venueNophoursSN = new Integer(req.getParameter("venueNophoursSN"));
			req.getSession().setAttribute("venueNophoursSN", venueNophoursSN);
			String location = req.getContextPath() + "/venue/updateNop.jsp";
			res.sendRedirect(location);
		}

		if ("get_one_update".equals(action)) {
			Integer venueNophoursSN = new Integer(req.getParameter("venueNophoursSN"));
			Integer whichpage = new Integer(req.getParameter("whichPage"));
			VenueNophoursService vnpSvc = new VenueNophoursService();
			VenueNophoursVO venueNophoursVO = vnpSvc.getOneVenueNophours(venueNophoursSN);

			System.out.println("179");

			req.getSession().setAttribute("venueNophoursVO", venueNophoursVO);
			req.getSession().setAttribute("whichPage", whichpage);
			String url = "/venue/updateNop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("updateNopHours".equals(action)) {
			Integer venueNophoursSN = new Integer(req.getParameter("venueNophoursSN"));
			Integer whichpage = new Integer(req.getParameter("whichPage"));
			System.out.println(venueNophoursSN);
			VenueNophoursVO venueNophoursVO = new VenueNophoursVO();
			VenueNophoursService vnpSvc = new VenueNophoursService();
			String[] noOpenValues = req.getParameterValues("venueNophours");
			int vnp = 0;

			if (noOpenValues != null) {
				for (int i = 0; i < noOpenValues.length; i++) {
					vnp += Integer.parseInt(noOpenValues[i]);
				}

			}

			venueNophoursVO = vnpSvc.getOneVenueNophours(venueNophoursSN);

			venueNophoursVO.toString();

			Integer venueSN = venueNophoursVO.getVenueSN();

			vnpSvc.updateTime(venueNophoursVO, String.valueOf(vnp));

			String location = req.getContextPath() + "/venue/listAllVenueNop.jsp?whichPage=" + whichpage + "&venueSN="
					+ venueSN;
			res.sendRedirect(location);

		}
	}
}
