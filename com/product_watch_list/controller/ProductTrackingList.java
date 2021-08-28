package com.product_watch_list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product_watch_list.model.Product_watch_listService;
import com.product_watch_list.model.Product_watch_listVO;


//@WebServlet("/ProductTrackingList")
public class ProductTrackingList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductTrackingList() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String tracking = request.getParameter("tracking");
		Integer userId = (Integer)session.getAttribute("userId"); // 會員編號
		
		if("trackingManagement".equals(action)) {
			Product_watch_listService trackingService = new Product_watch_listService();
			List<Product_watch_listVO> userTracking = trackingService.getWatchListByUser(userId);
			request.setAttribute("userTracking", userTracking);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/shopping/merchandise-trackinglist.jsp");
			dispatcher.forward(request, response);
		}
		
		// ajax，對此商品退追蹤(查看追蹤明細)
		if("cancle".equals(tracking)) {
			
			List<String> errorMsgs = new ArrayList<String>(); // 錯誤訊息
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {		
				Integer pwlSN = new Integer(request.getParameter("pwlSN"));
				Product_watch_listService trackingService = new Product_watch_listService();
				trackingService.deleteWatchList(pwlSN);
				out.write("cancle complete");
				return;
				
			} catch (Exception e) {
				errorMsgs.add("取消追蹤失敗  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
				failureView.forward(request, response);
				return;
			}
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
