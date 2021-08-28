package com.morder.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.morder.model.MorderService;
import com.morder.model.MorderVO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;



//@WebServlet("/MorderManagement")
public class MorderManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MorderManagement() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String update = request.getParameter("update");
		String save = request.getParameter("save");
		Integer userId = (Integer)session.getAttribute("userId"); // 會員編號
		
		// 主訂單檢視
		if("morderManagement".equals(action)) {
			MorderService morderService = new MorderService();
			List<MorderVO> userMorder = morderService.getMorderByUser(userId);
			request.setAttribute("userMorder", userMorder);
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/generalorder/order-management.jsp");
			dispatcher.forward(request, response);
		}
		
		// 訂單明細檢視
		if("orderListManagement".equals(action)) {
			Integer orderSN = new Integer(request.getParameter("orderSN"));
			Order_listService listService = new Order_listService();
			List<Order_listVO> orderList = listService.getOrderListByOrder(orderSN);
			MorderService morderService= new MorderService();
			MorderVO morder = morderService.getMorder(orderSN);
			request.setAttribute("morder", morder);
			request.setAttribute("orderList", orderList);

			RequestDispatcher dispatcher = request.getRequestDispatcher
					("/generalorder/orderlist-management.jsp");
			dispatcher.forward(request, response);
		
		}
		
		// 商品評論檢視
		if("reviewManagement".equals(action)) {
			
			MorderService morderService = new MorderService();
			Order_listService orderListService = new Order_listService();
			Map<MorderVO, List<Order_listVO>> forReviewMap = new LinkedHashMap<MorderVO, List<Order_listVO>>();
			List<MorderVO> userMorder = morderService.getMorderByUser(userId);
			for(MorderVO morder : userMorder) {
				List<Order_listVO> orderList = 
						orderListService.getOrderListByOrder(morder.getOrderSN());
				forReviewMap.put(morder, orderList);
			}
			
			request.setAttribute("forReviewMap", forReviewMap);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/generalorder/merchandise-rate.jsp");
			dispatcher.forward(request, response);
		}
		
		// Ajax，商品評論編輯
		if("save".equals(save)) {
			String productFeedback = request.getParameter("productFeedback");
			Integer productRate = new Integer(request.getParameter("productRate"));
			Integer listSN = new Integer(request.getParameter("listSN"));
			Order_listService orderListService = new Order_listService();
			Order_listVO order_list = orderListService.getOneOrderList(listSN);
			orderListService.updateOrderList(listSN, order_list.getProductSpecId(), 
					order_list.getOrderSN(), order_list.getOrderCost(), 
					order_list.getPurchaseQuantity(), productRate, productFeedback);
			
			response.getWriter().write("review complete");
		}
		
	
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
