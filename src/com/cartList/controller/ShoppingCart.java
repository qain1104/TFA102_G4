package com.cartList.controller;

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
import com.cartList.model.CartListDAO;
import com.cartList.model.CartListService;
import com.cartList.model.CartListVO;
import com.product.model.ProductJDBCDAO;
import com.product.model.ProductVO;
import com.productspec.model.ProductSpecJDBCDAO;
import com.productspec.model.ProductSpecService;
import com.productspec.model.ProductSpecVO;

//@WebServlet("/ShoppingCart")

public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShoppingCart() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		Integer userId = (Integer)session.getAttribute("userId"); // 會員Id
		
		// 返回繼續購買
		if("backToShop".equals(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shop");
			dispatcher.forward(request, response);
			return;
		}
		
		// 購買鍵
		if("buy".equals(action)) {
			// 點選購買會進入購物車頁面，先抓有沒有該會員的購物車清單，若沒有直接列出該項
			// 若有，先抓購物車清單再把該項加入
			String productSpecString = request.getParameter("productSpecId"); // 產品明細id
			Integer itemQuantity = Integer.valueOf(request.getParameter("itemQuantity")); // 數量
			String productSN = request.getParameter("productSN"); // 產品名稱
			// 更改為service
			ProductSpecJDBCDAO productSpecDAO = new ProductSpecJDBCDAO();
			
			List<String> errorMsgs = new ArrayList<String>(); // 錯誤訊息
			request.setAttribute("errorMsgs", errorMsgs);
			
			if(productSpecString.trim().length() == 0) {
				errorMsgs.add("請選擇尺寸");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
				dispatcher.forward(request, response);
				return;
			}
			
			Integer productSpecId = Integer.valueOf(productSpecString); // 產品明細id
			Integer stock = productSpecDAO.findByPrimaryKey(productSpecId).getProductStock(); // 庫存數量
			
			try {
				// 數量必須小於庫存量
				if(stock >= itemQuantity) {
					// 確認是否為會員
					if(userId != null) {
						
						CartListService cartListService = new CartListService();
						List<CartListVO> cartList = cartListService.getCartList(userId);
						
						if(cartList == null) {
							
							cartList = new ArrayList<CartListVO>();
							cartListService.updateCartList(userId, productSpecId, itemQuantity);
							cartList.add(new CartListVO(userId, productSpecId, itemQuantity));
							// 若shoppingcart.jsp是從MySQL撈資料，就不需要再session set資料
//						session.setAttribute("cartList", cartList);
							request.setAttribute("productSN", productSN);
							request.setAttribute("productSpecId", productSpecId);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
							dispatcher.forward(request, response);
							return;
							
						} else {
							
							cartListService.updateCartList(userId, productSpecId, itemQuantity);
							List<CartListVO> cartListNew = cartListService.getCartList(userId);
							// 若shoppingcart.jsp是從MySQL撈資料，就不需要再session set資料
//						session.setAttribute("cartList", cartListNew); 
							request.setAttribute("productSN", productSN);
							request.setAttribute("productSpecId", productSpecId);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
							dispatcher.forward(request, response);
							return;
							
						}
					} else {
						// 若不為會員時，先進入登入頁面
						// 登入成功後再從userId中看購物車內有沒有商品；若有，則加進購物車清單中，在進入購物車頁面
						// 若沒有，將商品加進購物車中，接著進入購物車頁面
						response.sendRedirect("login");
						
					}
				} else {

					errorMsgs.add("庫存數量不足，請修改數量");
					request.setAttribute("itemQuantity", itemQuantity);
					RequestDispatcher failureView = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
					failureView.forward(request, response);
					return;
				}
			} catch(Exception e) {
				
				errorMsgs.add("無法購買此商品  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
				failureView.forward(request, response);
				return;
				
			}
		}
		
		// 加進購物車
		if("addtocart".equals(action)) {
			// 先判斷使用者有沒有登入；若有，直接將物品加進使用者的購物車
			// 若沒有，在session new一個list將商品加進去
			String productSpecString = request.getParameter("productSpecId"); // 產品明細id
			Integer itemQuantity = Integer.valueOf(request.getParameter("itemQuantity")); // 數量
			String productSN = request.getParameter("productSN"); // 產品名稱
			ProductSpecService productSpecService = new ProductSpecService();
			List<String> errorMsgs = new ArrayList<String>(); // 錯誤訊息
			request.setAttribute("errorMsgs", errorMsgs);
			
			if(productSpecString.trim().length() == 0) {
				errorMsgs.add("請選擇尺寸");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
				dispatcher.forward(request, response);
				return;
			}
			
			Integer productSpecId = Integer.valueOf(productSpecString); // 產品明細id
			Integer stock = productSpecService.getOneProduct(productSpecId).getProductStock(); // 庫存數量
			
			try {
				// 購買數量需小於庫存量
				if(stock >= itemQuantity) {
					
					// 使用者登入的狀態下
					if(userId != null) {
						
						CartListService cartListService = new CartListService();
						cartListService.updateCartList(userId, productSpecId, itemQuantity);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
						dispatcher.forward(request, response);
						return;
						
					} else {
						// 未登入狀態下，先在session取cartList
						// 取不到則new一個
						List<CartListVO> cartList = (List<CartListVO>)session.getAttribute("cartList");
						
						if(cartList == null) {
							
							cartList = new ArrayList<CartListVO>();
							CartListVO cartListVO = new CartListVO();
							cartListVO.setProductSpecId(productSpecId);
							cartListVO.setItemQuantity(itemQuantity);
							cartList.add(cartListVO);
							session.setAttribute("cartList", cartList);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
							dispatcher.forward(request, response);
							return;
							
						} else {
							
							for(CartListVO vo : cartList) {
								// 若是有相同物件，直接加上數量即可
								if(productSpecId.intValue() == vo.getProductSpecId()) {
									itemQuantity = vo.getItemQuantity() + itemQuantity;
									CartListVO cartListVO = new CartListVO();
									cartListVO.setProductSpecId(productSpecId);
									cartListVO.setItemQuantity(itemQuantity);
									cartList.remove(vo);
									cartList.add(cartListVO);
//									session.setAttribute("cartList", cartList);
									RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
									dispatcher.forward(request, response);
									return;
									
								} 
							}
							// 若沒有相同物件，則在list中新增一筆
							CartListVO cartListVO = new CartListVO();
							cartListVO.setProductSpecId(productSpecId);
							cartListVO.setItemQuantity(itemQuantity);
							cartList.add(cartListVO);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
							dispatcher.forward(request, response);
							return;
							
							// 登入後將session的購物車物件放進自己的購物車，並移除session的購物車
//							List<CartListVO> cartList = (List<CartListVO>)session.getAttribute("cartList");
//							CartListService cartListService = new CartListService();
//							if(cartList != null) {
//								for(CartListVO cartListVO : cartList) {
//									cartListService.updateCartList(userId, cartListVO.getProductSpecId(), cartListVO.getItemQuantity());
//								}
//								session.removeAttribute("cartList");
//							}
							
						}
					}	
				} else {
					
					errorMsgs.add("庫存數量不足，請修改數量");
					request.setAttribute("itemQuantity", itemQuantity);
					RequestDispatcher failureView = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
					failureView.forward(request, response);
					return;
				}				
			} catch(Exception e) {
				errorMsgs.add("無法加入此商品至購物車  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
				failureView.forward(request, response);
				return;
			}
		}
		
		// 刪除，透過ajax
		if("delete".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>(); // 錯誤訊息
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				CartListService cartListService = new CartListService();
				Integer productSpecId = new Integer(request.getParameter("productSpecId"));
				Integer itemQuantity = new Integer(request.getParameter("itemQuantity"));
				cartListService.deleteCartListSingle(userId, productSpecId, itemQuantity);
				response.getWriter().write("delet complete");
				return;
				
			} catch(Exception e) {
				
				errorMsgs.add("無法刪除此商品  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
				failureView.forward(request, response);
				return;
			}
			
		}
		// 減少一件，透過ajax
		if("deleteOnePiece".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>(); // 錯誤訊息
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Integer productSpecId = new Integer(request.getParameter("productSpecId"));
				Integer itemQuantity = new Integer(request.getParameter("itemQuantity"));
				// 數量大於一件，才能減少
				if(1 < itemQuantity) {
					
					CartListService cartListService = new CartListService();
					cartListService.deleteOnePiece(userId, productSpecId, itemQuantity);
					response.getWriter().write("delet complete");
					return;
					
				} else {
					
					errorMsgs.add("數量為1，無法再減少");
					RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
					failureView.forward(request, response);
					return;
					
				}
				
			} catch(Exception e) {
				
				errorMsgs.add("無法減少此商品數量  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
				failureView.forward(request, response);
				return;
				
			}
		}
		
		// 增加一件，透過ajax
		if("addOnePiece".equals(action)) {
			
			List<String> errorMsgs = new ArrayList<String>(); // 錯誤訊息
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 要更改為Service
				Integer productSpecId = new Integer(request.getParameter("productSpecId"));
				Integer itemQuantity = new Integer(request.getParameter("itemQuantity"));
				// 確認庫存量
				ProductSpecJDBCDAO productSpecDAO = new ProductSpecJDBCDAO();
				ProductSpecVO productSpec = productSpecDAO.findByPrimaryKey(productSpecId);
				// 庫存量必須大於購買數量
				if(productSpec.getProductStock() > itemQuantity) {
					
					CartListService cartListService = new CartListService();
					cartListService.addOnePiece(userId, productSpecId, itemQuantity);
					response.getWriter().write("add complete");
					return;
					
				} else {
					errorMsgs.add("庫存數量不足，請修改數量");
					request.setAttribute("itemQuantity", itemQuantity);
					RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
					failureView.forward(request, response);
					return;
				}
				
			} catch(Exception e) {
				
				errorMsgs.add("無法增加此商品數量  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
				failureView.forward(request, response);
				return;
			}
		}
		
		if("nextStep".equals(action)) {
			
			// 取出該會員的購物車內容至填寫資料頁面
			CartListService cartListService = new CartListService();
			List<CartListVO> cartList = cartListService.getCartList(userId);
			session.setAttribute("cartList", cartList);
			RequestDispatcher nextPage = request.getRequestDispatcher("/shopping/paying-delivery.jsp");
			nextPage.forward(request, response);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
