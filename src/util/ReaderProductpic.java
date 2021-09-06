package util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productimage.model.ProductImageService;

@WebServlet("/ReaderProductpic")
public class ReaderProductpic extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("img/gif");
		ServletOutputStream out = res.getOutputStream();

		
		String pISN = req.getParameter("productImageSN");

		if (pISN != null) {
			Integer productImageId= Integer.parseInt(pISN);
			ProductImageService pISvc = new ProductImageService();
			byte[] b = pISvc.getOneProduct(productImageId).getProductImage();

			if (b == null || b.length == 0) {
				InputStream in = getServletContext().getResourceAsStream("/assets/images/logo.png");
				byte[] d = new byte[in.available()];
				in.read(d);
				out.write(d);
			} else {
				out.write(b);
			}

		}
		

	}

}
