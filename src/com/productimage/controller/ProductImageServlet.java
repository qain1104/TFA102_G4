package com.productimage.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.productimage.model.ProductImageJDBCDAO;
import com.productimage.model.ProductImageService;
import com.productimage.model.ProductImageVO;

@WebServlet("/ProductImageServlet")
public class ProductImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductImageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer productSN = new Integer(request.getParameter("productSN"));
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();

		ProductImageService imageService = new ProductImageService();
		List<ProductImageVO> imageList = imageService.findByProduct(productSN);
		
		byte[] image = imageList.get(0).getProductImage();

		try {
			out.write(image);
			out.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/assets/images/none3.jpg");
			byte[] error = new byte[in.available()];
			in.read(error);
			out.write(error);
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
