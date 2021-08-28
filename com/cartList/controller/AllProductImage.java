package com.cartList.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productimage.model.ProductImageService;
import com.productimage.model.ProductImageVO;

@WebServlet("/AllProductImage")
public class AllProductImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AllProductImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer productImageSN = new Integer(request.getParameter("productImageSN"));
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();
		ProductImageService imageService = new ProductImageService();
		ProductImageVO image = imageService.getOneProduct(productImageSN);
		byte[] imageByte = image.getProductImage();
		
		try {

			out.write(imageByte);	
			out.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/assets/img/oops.jpg");
			byte[] error = new byte[in.available()];
			in.read(error);
			out.write(error);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
