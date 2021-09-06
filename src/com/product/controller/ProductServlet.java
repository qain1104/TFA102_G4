package com.product.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.*;
import com.productimage.model.*;
import com.productspec.model.*;
import com.product.controller.*;
import com.productimage.controller.*;

@MultipartConfig
public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		System.out.println("ProductServlet 32 Row -> action = " + action);

		if ("insert".equals(action)) { // �s�W�ӫ~

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				Integer corpUserId = null;
				try {
					corpUserId = new Integer(req.getParameter("corpUserId").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��corpUserId");
				}

				String productName = req.getParameter("productName");
				String productnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,}$";

				if (productName == null || productName.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				} else if (!productName.trim().matches(productnameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ӫ~�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				Integer productClass = null;
				try {
					productClass = new Integer(req.getParameter("productClass").trim());

				} catch (NumberFormatException e) {
					errorMsgs.add("�S��productClass");
				}

				String productDetail = req.getParameter("productDetail").trim();
				if (productDetail == null || productDetail.trim().length() == 0) {
					errorMsgs.add("²���ФŪť�");
				}

				String productBrand = req.getParameter("productBrand").trim();
				if (productBrand == null || productBrand.trim().length() == 0) {
					errorMsgs.add("�~�P�ФŪť�");
				} 

				Integer productPrice = null;
				try {
					productPrice = new Integer(req.getParameter("productPrice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��productPrice");
				}

				String productSpec = req.getParameter("productSpec").trim();
				if (productSpec == null || productSpec.trim().length() == 0) {
					errorMsgs.add("�W��ФŪť�");
				}

				Integer productStock = null;
				try {
					productStock = new Integer(req.getParameter("productStock").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��productStock");
				}

				String productOnsaleStr = req.getParameter("productOnsale");
				java.sql.Timestamp productOnsale = null;
				java.sql.Date date = null;
				try {
					productOnsaleStr = productOnsaleStr.replace("T", " ");
					productOnsale = Timestamp.valueOf(productOnsaleStr);
				} catch (Exception e) {
					productOnsale = new Timestamp(System.currentTimeMillis());
				}

				Part part1 = req.getPart("productImage1");
				InputStream is1 = part1.getInputStream();
				byte[] productImage1 = new byte[is1.available()];
				is1.read(productImage1);
				is1.close();

				Part part2 = req.getPart("productImage2");
				InputStream is2 = part2.getInputStream();
				byte[] productImage2 = new byte[is2.available()];
				is2.read(productImage2);
				is2.close();

				Integer productStatus = null;

				try {
					productStatus = new Integer(req.getParameter("productStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��productStatus");
				}

				ProductVO productVO = new ProductVO();
				ProductSpecVO productSpecVO = new ProductSpecVO();
				ProductImageVO productImageVO = new ProductImageVO();

				productVO.setCorpUserId(corpUserId);
				productVO.setProductName(productName);
				productVO.setProductClass(productClass);
				productVO.setProductDetail(productDetail);
				productVO.setProductBrand(productBrand);
				productVO.setProductOnsale(productOnsale);
				productVO.setProductStatus(productStatus);

				productSpecVO.setProductPrice(productPrice);
				productSpecVO.setProductSpec(productSpec);
				productSpecVO.setProductStock(productStock);

				productImageVO.setProductImage(productImage1);
				productImageVO.setProductImage(productImage2);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // �t����J�榡���~��productVO����,�]�s�Jreq
					req.setAttribute("productSpecVO", productSpecVO);
					req.setAttribute("productImageVO", productImageVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ProductService productSvc = new ProductService();
				ProductSpecService productSpecSvc = new ProductSpecService();
				ProductImageService productImageSvc = new ProductImageService();
				productVO = productSvc.addProduct(corpUserId, productName, productClass, productDetail, productBrand,
						productOnsale, productStatus);

				productSpecSvc.addProductSpec(productVO.getProductSN(), productStock, productPrice, productSpec);
//				���s�W�P�_��
				productImageSvc.addProductImage(productVO.getProductSN(), productImage1);
				productImageSvc.addProductImage(productVO.getProductSN(), productImage2);

				List<ProductVO> corpProductList = productSvc.selectCorpUserId(corpUserId);
//				Collections.reverse(corpProductList);
				session.setAttribute("corpProductList", corpProductList);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/product/listMyProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listMyProduct.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				System.out.println("Insert Error Msg");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("check_update".equals(action)) { // ��s�ӫ~�T�{
			List<String> errorMsgs = new LinkedList<String>();

			try {

				Integer productSN = new Integer(req.getParameter("productSN"));
				System.out.println("productSevlet 101 " + productSN);
				req.getSession().setAttribute("productSN", productSN);

				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(productSN);

				ProductImageService productImageSvc = new ProductImageService();
//				ProductImageVO productImageVO = productImageSvc.getProductImageVO(productSN);
				List<ProductImageVO> productImageList = (List<ProductImageVO>) productImageSvc
						.getProductImageVO(productSN);

				req.setAttribute("productVO", productVO); // ��Ʈw���X��productVO����,�s�Jreq
				req.setAttribute("productImageList", productImageList);
				String url = "/product/update_product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("check_update 110");

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listMyProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // ��s�ӫ~
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer productSN = (Integer) req.getSession().getAttribute("productSN");
				Integer corpUserId = null;

				try {
					corpUserId = (Integer) req.getSession().getAttribute("corpUserId");
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��corpUserId");
				}
				String productName = req.getParameter("productName");
				String productnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,}$";

				if (productName == null || productName.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				} else if (!productName.trim().matches(productnameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ӫ~�W��: �u��O���B�^��r���B�Ʀr�M_");
				}

				Integer productClass = null;
				try {
					productClass = new Integer(req.getParameter("productClass"));

				} catch (NumberFormatException e) {
					errorMsgs.add("�S��productClass");
				}
				String productDetail = req.getParameter("productDetail").trim();
				if (productDetail == null || productDetail.trim().length() == 0) {
					errorMsgs.add("²���ФŪť�");
				}
				String productBrand = req.getParameter("productBrand").trim();
				if (productBrand == null || productBrand.trim().length() == 0) {
					errorMsgs.add("�~�P�ФŪť�");
				}
//				java.sql.Timestamp productOnsale = new java.sql.Timestamp(System.currentTimeMillis());
				String productOnsaleStr = req.getParameter("productOnsale");
				java.sql.Timestamp productOnsale = null;
				try {
					productOnsaleStr = productOnsaleStr.replace("T", " ");
//					System.out.println(productOnsaleStr);
//					date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.TAIWAN).parse(productOnsaleStr).getTime());
					productOnsale = Timestamp.valueOf(productOnsaleStr);
				} catch (Exception e) {
					productOnsale = new Timestamp(System.currentTimeMillis());
				}

				// �קﭶ���ثe�S���ק�Ϥ����ﶵ
//				Part part1 = req.getPart("productImage1");
//				InputStream is1 = part1.getInputStream();
//				byte[] productImage1 = new byte[is1.available()];
//				is1.read(productImage1);
//				is1.close();
//				
//				Part part2 = req.getPart("productImage2");
//				InputStream is2 = part2.getInputStream();
//				byte[] productImage2 = new byte[is2.available()];
//				is2.read(productImage2);
//				is2.close();

				Integer productStatus = null;

				try {
					productStatus = new Integer(req.getParameter("productStatus"));
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��productStatus");
				}
				// �n2�٬O0?
				// �o��n0�A�v�a���Q�k�O�p�G�f�֥��q�L���A��2�A�N�|�۰ʧ�s��0
				if (productStatus == 2) {
					productStatus = 0;
				}

				ProductVO productVO = new ProductVO();

				productVO.setProductSN(productSN);
				productVO.setCorpUserId(corpUserId);
				productVO.setProductName(productName);
				productVO.setProductClass(productClass);
				productVO.setProductDetail(productDetail);
				productVO.setProductBrand(productBrand);
				productVO.setProductOnsale(productOnsale);
				productVO.setProductStatus(productStatus);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // �t����J�榡���~��productVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				System.out.println("Servlet 314 row update ProductVO" + productVO);
				/*************************** 2.�}�l�ק��� *****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(productSN, corpUserId, productName, productClass, productDetail,
						productBrand, productOnsale, productStatus);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("productVO", productVO); // ��Ʈwupdate���\��,���T����productVO����,�s�Jreq

				List<ProductVO> corpProductList = productSvc.selectCorpUserId(corpUserId);
				session.setAttribute("corpProductList", corpProductList);
//				Collections.reverse(corpProductList);
				String url = "/product/listMyProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listMyProduct.jsp
				System.out.println("323��" + productVO);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listMyProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("check_updateProductSpec".equals(action)) { // ��s�ӫ~���ӽT�{
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer productSN = new Integer(req.getParameter("productSN"));
				Integer productSpecId = new Integer(req.getParameter("productSpecId"));
			
				ProductSpecService productSpecSvc = new ProductSpecService();
				ProductSpecVO productSpecVO = productSpecSvc.getOneProduct(productSpecId);

				System.out.println("350 row check_productSpec = " + productSpecVO);
				
				req.setAttribute("productSpecVO", productSpecVO); // ��Ʈw���X��productVO����,�s�Jreq
				String url = "/product/update_productSpec.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("356 row check_productSpec = " + productSpecVO);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸�ӫ~�W��:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("updateProductSpec".equals(action)) { // ��s�ӫ~����
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer productSN = new Integer(req.getParameter("productSN").trim());
				req.setAttribute("productSN", productSN);
				Integer productSpecId = new Integer(req.getParameter("productSpecId").trim());
				
				Integer productPrice = null;
				try {
					productPrice = new Integer(req.getParameter("productPrice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��productPrice");
				}

				String productSpec = req.getParameter("productSpec").trim();
				if (productSpec == null || productSpec.trim().length() == 0) {
					errorMsgs.add("�W��ФŪť�");
				}

				Integer productStock = null;
				try {
					productStock = new Integer(req.getParameter("productStock").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��productStock");
				}
				
				ProductSpecVO productSpecVO = new ProductSpecVO();
				productSpecVO.setProductSpecId(productSpecId);
				productSpecVO.setProductSN(productSN);
				productSpecVO.setProductPrice(productPrice);
				productSpecVO.setProductSpec(productSpec);
				productSpecVO.setProductStock(productStock);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productSpecVO", productSpecVO); // �t����J�榡���~��productVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				System.out.println("Servlet 410 row updatd ProductSpecVO" + productSpecVO);
				/*************************** 2.�}�l�ק��� *****************************************/
				ProductSpecService productSpecSvc = new ProductSpecService();
				productSpecVO = productSpecSvc.updateProductSpec(productSpecId, productSN, productPrice, productStock, productSpec);
				System.out.println("413��" + productSpecVO);
//				ProductService productSvc = new ProductService();
//				List<ProductVO> corpProductList = productSvc.selectCorpUserId(corpUserId);
//				session.setAttribute("corpProductList", corpProductList);
//				Collections.reverse(corpProductList);
				
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("productSpecVO", productSpecVO); // ��Ʈwupdate���\��,���T����productVO����,�s�Jreq

				String url = "/product/select_page.jsp?productSN="+productSN;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listMyProduct.jsp
				successView.forward(req, res);
System.out.println("425��" + productSpecVO);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getMy_Product".equals(action)) { // �d�ߥ��~�|���ӤH�������ӫ~
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer corpUserId = (Integer) session.getAttribute("corpUserId");
				/*************************** 2.�}�l�d�߸�� ****************************************/
				ProductService productSvc = new ProductService();
				List<ProductVO> corpProductList = productSvc.selectCorpUserId(corpUserId);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				session.setAttribute("corpProductList", corpProductList); // ��Ʈw���X��productVO����,�s�Jreq
				String url = "/product/listMyProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_product_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listMyProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { // �d�߰ӫ~����

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer productSN = new Integer(req.getParameter("productSN"));
				req.setAttribute("productSN", productSN);

				ProductService productSvc = new ProductService();
				ProductVO productList = productSvc.getOneProduct(productSN);

				ProductImageService productImageSvc = new ProductImageService();
				List<ProductImageVO> productImageList = (List<ProductImageVO>) productImageSvc
						.getProductImageVO(productSN);

				session.setAttribute("productList", productList); // ��Ʈw���X��productVO����,�s�Jreq
				req.setAttribute("productImageList", productImageList);
				String url = "/product/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("cancel".equals(action)) {
			res.sendRedirect(req.getContextPath() + "/product/select_page.jsp");
		}

		if ("back".equals(action)) {
			res.sendRedirect(req.getContextPath() + "/product/listMyProduct.jsp");
		}

		if ("down_product".equals(action)) { // �ӫ~�U�[
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer productSN = new Integer(req.getParameter("productSN"));
				Integer corpUserId = (Integer) req.getSession().getAttribute("corpUserId");
				Integer productStatus = 1;
				try {
					productStatus = 0;
				} catch (NumberFormatException e) {
					errorMsgs.add("�S��productStatus");
				}

				ProductVO productVO = new ProductVO();
				System.out.println("467 row " + productVO);
				productVO.setProductSN(productSN);
				productVO.setProductStatus(productStatus);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // �t����J�榡���~��productVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/product/listMyProduct.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProductStatusDown(productVO, productSN, productStatus);
				req.setAttribute("productVO", productVO);

				List<ProductVO> corpProductList = productSvc.selectCorpUserId(corpUserId);
				session.setAttribute("corpProductList", corpProductList);
//				Collections.reverse(corpProductList);
				String url = "/product/listMyProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listMyProduct.jsp
				System.out.println("486��" + productVO);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�ӫ~�U�[����:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listMyProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllProduct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer productSN = new Integer(req.getParameter("productSN"));
				System.out.println(productSN);

				/*************************** 2.�}�l�R����� ***************************************/
				ProductService productSvc = new ProductService();

				productSvc.deleteProduct(productSN);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
