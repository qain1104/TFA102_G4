package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_watch_list.model.Product_watch_listService;
import com.product_watch_list.model.Product_watch_listVO;
import com.productimage.model.ProductImageJDBCDAO;
import com.productimage.model.ProductImageVO;
import com.productspec.model.ProductSpecService;
import com.productspec.model.ProductSpecVO;


public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShopController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String category = request.getParameter("category");
		Integer userId = (Integer)session.getAttribute("userId"); // �|��Id
		ProductService productService = new ProductService();
		ProductSpecService productSpecService = new ProductSpecService();

		
		// ajax�A�惡�ӫ~�l�ܩΨ����l��(�ӫ~�������s)
		if("tracking".equals(action)) {
			Integer productSN = new Integer(request.getParameter("productSN"));
			// �P�_���L�n�J
			if(userId != null) {
				Product_watch_listService watchListService = new Product_watch_listService();
				List<Product_watch_listVO> userList =  watchListService.getWatchListByUser(userId);

				// ���P��ϥΪ̬O�_�����ӫ~
				for(Product_watch_listVO vo : userList) {
					if(vo.getProductSN().intValue() == productSN.intValue()) {
						response.getWriter().write("Item exists.");
						return;
					}
				} 
				watchListService.addWatchList(productSN, userId);
				response.getWriter().write("Item added successfully.");
			} else {
				response.getWriter().write("Please sign in first.");
			}
		}
		
		// �j�M�ӫ~�A�Y�S����J�h�����i�J�Ҧ��ӫ~����
		if("search".equals(action)) {
			String query = request.getParameter("query");
			if(query == null || query.trim().length() == 0) {
				List<ProductVO> shopAllproductList = productService.getAll();
				request.getSession().setAttribute("shopAllproductList", shopAllproductList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shop.jsp");
				dispatcher.forward(request, response);
			} else {
				List<ProductVO> shopAllproductList = 
						productService.getAll()
									  .stream()
								      .filter(p -> p.getProductName().contains(query))
								      .collect(Collectors.toList());
				request.getSession().setAttribute("shopAllproductList", shopAllproductList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shop.jsp");
				dispatcher.forward(request, response);
			}
		}	
		
		// �i�J��ӫ~����������
		if("shop".equals(action)) {
			List<ProductVO> shopAllproductList = productService.getAll();
			request.getSession().setAttribute("shopAllproductList", shopAllproductList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shop.jsp");
			dispatcher.forward(request, response);
			
			//"<%= request.getContextPath() %>/shopping/SportifyShop.do?action=shop"
		}
		
		// �i�J��@�ӫ~
		if("shopsingle".equals(action)) {
			Integer productSN = new Integer(request.getParameter("productSN"));
			ProductVO shopSingleProduct = productService.getOneProduct(productSN);
			// ���o�Ĥ@�Ӱӫ~���Ӫ�����
			List<ProductSpecVO> specList =  productSpecService.getProductSpec(productSN);
			Integer shopSinglePrice = specList.get(0).getProductPrice();
			// ���o�Ӱӫ~���e3�i�Ϥ�
			ProductImageJDBCDAO dao = new ProductImageJDBCDAO();
			List<ProductImageVO> imageList = dao.findByProduct(productSN);
			List<Integer> shopSingleImageSN = new ArrayList<Integer>();
			
			if(imageList.size() < 3) {
				for(int i = 0; i < shopSingleImageSN.size(); i++) {
					shopSingleImageSN.add(imageList.get(i).getProductImageSN());
				}	
				
			} else {
				
				for(int i = 0; i < 3; i++) {
					shopSingleImageSN.add(imageList.get(i).getProductImageSN());
				}	
			}
			
			// �Ӳ��~������
			Order_listService listService = new Order_listService();
			List<Order_listVO> rateReview = listService.productRateReview(productSN);
			if(rateReview != null) {
				request.getSession().setAttribute("rateReview", rateReview);
			}
			
			// ���o�������~list
			List<ProductVO> relatedProductList = 
					productService.getAll()
								  .stream()
							      .filter(p -> p.getProductBrand().contains(shopSingleProduct.getProductBrand()))
							      .collect(Collectors.toList());
			Map<ProductVO, Integer> relatedMap = new HashMap<ProductVO, Integer>();
			for(ProductVO product : relatedProductList) {
				relatedMap.put(product, productSpecService.getProductSpec(product.getProductSN()).get(0).getProductPrice());
			}

			
			request.getSession().setAttribute("shopSingleProduct", shopSingleProduct);
			request.getSession().setAttribute("specList", specList);
			request.getSession().setAttribute("shopSinglePrice", shopSinglePrice);
			request.getSession().setAttribute("shopSingleImageSN", shopSingleImageSN);
			request.getSession().setAttribute("relatedMap", relatedMap);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shop-single.jsp");
			dispatcher.forward(request, response);
		}
		
		// ���~����
		if(category != null) {
			
			if(1 == new Integer(category)) {
				List<ProductVO> productList = productService.getProductCategory(1);
				request.getSession().setAttribute("productList", productList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shop.jsp");
				dispatcher.forward(request, response);
			}
			
			if(0 == new Integer(category)) {
				List<ProductVO> productList = productService.getProductCategory(0);
				request.getSession().setAttribute("productList", productList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shop.jsp");
				dispatcher.forward(request, response);
			}
			
			if(2 == new Integer(category)) {
				List<ProductVO> productList = productService.getProductCategory(2);
				request.getSession().setAttribute("productList", productList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shop.jsp");
				dispatcher.forward(request, response);
			}
			
			if(3 == new Integer(category)) {
				List<ProductVO> productList = productService.getProductCategory(3);
				request.getSession().setAttribute("productList", productList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shopping/shop.jsp");
				dispatcher.forward(request, response);
			}
			
		} else {
			return;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
