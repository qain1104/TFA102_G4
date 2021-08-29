package com.cartList.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
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


@WebServlet("/ProductImage")
public class ProductImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer productSN = new Integer(request.getParameter("productSN"));
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();
		
		// 利用dao拿到商品的第一張圖即可
		ProductImageService imageService = new ProductImageService();
		List<ProductImageVO> imageList = imageService.findByProduct(productSN);
		byte[] image = imageList.get(0).getProductImage();

		
		try {
			//註解部分為使用Blob去讀取資料
//			BufferedInputStream in = new BufferedInputStream(image.getBinaryStream());
//			System.out.println("image size: " + in.available());
//			byte[] buf = new byte[in.available()];
//			in.read(buf);	
//			response.setContentLength(buf.length); // 直接告訴tomcat圖片的大小
			out.write(image);	
//			in.close();
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
