package util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.GeneralUser.model.GeneralUserService;


@WebServlet("/Readerpic")
public class Readerpic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
    	
    	res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String id = req.getParameter("id").trim();
		Integer userId=Integer.parseInt(id);
		GeneralUserService guSvc=new GeneralUserService();
		byte[] b=guSvc.getOneGeneralUser(userId).getProfilePic();
		if(b==null || b.length==0) {
			InputStream in = getServletContext().getResourceAsStream("/assets/img/none.jpg");
			byte[] d= new byte[in.available()];
			in.read(d);
			out.write(d);
		}else {
			out.write(b);
		}

    }
}
