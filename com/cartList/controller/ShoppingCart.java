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
		Integer userId = (Integer)session.getAttribute("userId"); // �|��Id
		
		// ��^�~���ʶR
		if("backToShop".equals(action)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shop");
			dispatcher.forward(request, response);
			return;
		}
		
		// �ʶR��
		if("buy".equals(action)) {
			// �I���ʶR�|�i�J�ʪ��������A���즳�S���ӷ|�����ʪ����M��A�Y�S�������C�X�Ӷ�
			// �Y���A�����ʪ����M��A��Ӷ��[�J
			String productSpecString = request.getParameter("productSpecId"); // ���~����id
			Integer itemQuantity = Integer.valueOf(request.getParameter("itemQuantity")); // �ƶq
			String productSN = request.getParameter("productSN"); // ���~�W��
			// ��אּservice
			ProductSpecJDBCDAO productSpecDAO = new ProductSpecJDBCDAO();
			
			List<String> errorMsgs = new ArrayList<String>(); // ���~�T��
			request.setAttribute("errorMsgs", errorMsgs);
			
			if(productSpecString.trim().length() == 0) {
				errorMsgs.add("�п�ܤؤo");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
				dispatcher.forward(request, response);
				return;
			}
			
			Integer productSpecId = Integer.valueOf(productSpecString); // ���~����id
			Integer stock = productSpecDAO.findByPrimaryKey(productSpecId).getProductStock(); // �w�s�ƶq
			
			try {
				// �ƶq�����p��w�s�q
				if(stock >= itemQuantity) {
					// �T�{�O�_���|��
					if(userId != null) {
						
						CartListService cartListService = new CartListService();
						List<CartListVO> cartList = cartListService.getCartList(userId);
						
						if(cartList == null) {
							
							cartList = new ArrayList<CartListVO>();
							cartListService.updateCartList(userId, productSpecId, itemQuantity);
							cartList.add(new CartListVO(userId, productSpecId, itemQuantity));
							// �Yshoppingcart.jsp�O�qMySQL����ơA�N���ݭn�Asession set���
//						session.setAttribute("cartList", cartList);
							request.setAttribute("productSN", productSN);
							request.setAttribute("productSpecId", productSpecId);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
							dispatcher.forward(request, response);
							return;
							
						} else {
							
							cartListService.updateCartList(userId, productSpecId, itemQuantity);
							List<CartListVO> cartListNew = cartListService.getCartList(userId);
							// �Yshoppingcart.jsp�O�qMySQL����ơA�N���ݭn�Asession set���
//						session.setAttribute("cartList", cartListNew); 
							request.setAttribute("productSN", productSN);
							request.setAttribute("productSpecId", productSpecId);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
							dispatcher.forward(request, response);
							return;
							
						}
					} else {
						// �Y�����|���ɡA���i�J�n�J����
						// �n�J���\��A�quserId�����ʪ��������S���ӫ~�F�Y���A�h�[�i�ʪ����M�椤�A�b�i�J�ʪ�������
						// �Y�S���A�N�ӫ~�[�i�ʪ������A���۶i�J�ʪ�������
						response.sendRedirect("login");
						
					}
				} else {

					errorMsgs.add("�w�s�ƶq�����A�Эק�ƶq");
					request.setAttribute("itemQuantity", itemQuantity);
					RequestDispatcher failureView = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
					failureView.forward(request, response);
					return;
				}
			} catch(Exception e) {
				
				errorMsgs.add("�L�k�ʶR���ӫ~  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
				failureView.forward(request, response);
				return;
				
			}
		}
		
		// �[�i�ʪ���
		if("addtocart".equals(action)) {
			// ���P�_�ϥΪ̦��S���n�J�F�Y���A�����N���~�[�i�ϥΪ̪��ʪ���
			// �Y�S���A�bsession new�@��list�N�ӫ~�[�i�h
			String productSpecString = request.getParameter("productSpecId"); // ���~����id
			Integer itemQuantity = Integer.valueOf(request.getParameter("itemQuantity")); // �ƶq
			String productSN = request.getParameter("productSN"); // ���~�W��
			ProductSpecService productSpecService = new ProductSpecService();
			List<String> errorMsgs = new ArrayList<String>(); // ���~�T��
			request.setAttribute("errorMsgs", errorMsgs);
			
			if(productSpecString.trim().length() == 0) {
				errorMsgs.add("�п�ܤؤo");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
				dispatcher.forward(request, response);
				return;
			}
			
			Integer productSpecId = Integer.valueOf(productSpecString); // ���~����id
			Integer stock = productSpecService.getOneProduct(productSpecId).getProductStock(); // �w�s�ƶq
			
			try {
				// �ʶR�ƶq�ݤp��w�s�q
				if(stock >= itemQuantity) {
					
					// �ϥΪ̵n�J�����A�U
					if(userId != null) {
						
						CartListService cartListService = new CartListService();
						cartListService.updateCartList(userId, productSpecId, itemQuantity);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
						dispatcher.forward(request, response);
						return;
						
					} else {
						// ���n�J���A�U�A���bsession��cartList
						// ������hnew�@��
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
								// �Y�O���ۦP����A�����[�W�ƶq�Y�i
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
							// �Y�S���ۦP����A�h�blist���s�W�@��
							CartListVO cartListVO = new CartListVO();
							cartListVO.setProductSpecId(productSpecId);
							cartListVO.setItemQuantity(itemQuantity);
							cartList.add(cartListVO);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
							dispatcher.forward(request, response);
							return;
							
							// �n�J��Nsession���ʪ��������i�ۤv���ʪ����A�ò���session���ʪ���
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
					
					errorMsgs.add("�w�s�ƶq�����A�Эק�ƶq");
					request.setAttribute("itemQuantity", itemQuantity);
					RequestDispatcher failureView = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
					failureView.forward(request, response);
					return;
				}				
			} catch(Exception e) {
				errorMsgs.add("�L�k�[�J���ӫ~���ʪ���  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/SportifyShop.do?action=shopsingle&productSN="+productSN);
				failureView.forward(request, response);
				return;
			}
		}
		
		// �R���A�z�Lajax
		if("delete".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>(); // ���~�T��
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				CartListService cartListService = new CartListService();
				Integer productSpecId = new Integer(request.getParameter("productSpecId"));
				Integer itemQuantity = new Integer(request.getParameter("itemQuantity"));
				cartListService.deleteCartListSingle(userId, productSpecId, itemQuantity);
				response.getWriter().write("delet complete");
				return;
				
			} catch(Exception e) {
				
				errorMsgs.add("�L�k�R�����ӫ~  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
				failureView.forward(request, response);
				return;
			}
			
		}
		// ��֤@��A�z�Lajax
		if("deleteOnePiece".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>(); // ���~�T��
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Integer productSpecId = new Integer(request.getParameter("productSpecId"));
				Integer itemQuantity = new Integer(request.getParameter("itemQuantity"));
				// �ƶq�j��@��A�~����
				if(1 < itemQuantity) {
					
					CartListService cartListService = new CartListService();
					cartListService.deleteOnePiece(userId, productSpecId, itemQuantity);
					response.getWriter().write("delet complete");
					return;
					
				} else {
					
					errorMsgs.add("�ƶq��1�A�L�k�A���");
					RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
					failureView.forward(request, response);
					return;
					
				}
				
			} catch(Exception e) {
				
				errorMsgs.add("�L�k��֦��ӫ~�ƶq  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
				failureView.forward(request, response);
				return;
				
			}
		}
		
		// �W�[�@��A�z�Lajax
		if("addOnePiece".equals(action)) {
			
			List<String> errorMsgs = new ArrayList<String>(); // ���~�T��
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// �n��אּService
				Integer productSpecId = new Integer(request.getParameter("productSpecId"));
				Integer itemQuantity = new Integer(request.getParameter("itemQuantity"));
				// �T�{�w�s�q
				ProductSpecJDBCDAO productSpecDAO = new ProductSpecJDBCDAO();
				ProductSpecVO productSpec = productSpecDAO.findByPrimaryKey(productSpecId);
				// �w�s�q�����j���ʶR�ƶq
				if(productSpec.getProductStock() > itemQuantity) {
					
					CartListService cartListService = new CartListService();
					cartListService.addOnePiece(userId, productSpecId, itemQuantity);
					response.getWriter().write("add complete");
					return;
					
				} else {
					errorMsgs.add("�w�s�ƶq�����A�Эק�ƶq");
					request.setAttribute("itemQuantity", itemQuantity);
					RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
					failureView.forward(request, response);
					return;
				}
				
			} catch(Exception e) {
				
				errorMsgs.add("�L�k�W�[���ӫ~�ƶq  : " + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/shopping/shoppingcart.jsp");
				failureView.forward(request, response);
				return;
			}
		}
		
		if("nextStep".equals(action)) {
			
			// ���X�ӷ|�����ʪ������e�ܶ�g��ƭ���
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
