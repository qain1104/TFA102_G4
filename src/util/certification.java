package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CorpUser.model.*;
import com.GeneralUser.model.*;

@WebServlet("/certification")
public class certification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String email = req.getParameter("email");
		PrintWriter out = res.getWriter();

		GeneralUserService guSvc = new GeneralUserService();
		List<GeneralUserVO> guList = guSvc.getSameEmail(email);

		CorpUserService cuSvc = new CorpUserService();
		List<CorpUserVO> cuList = cuSvc.getSameEmail(email);

		if (guList.isEmpty() && cuList.isEmpty()) {
			out.print("<script> alert(\"查無該帳號,請先註冊!\"); </script>");
			out.println("<a href=\"" + req.getContextPath() + "/login.jsp" + "\">前往註冊</a>");

		} else if (!guList.isEmpty()) {
			guSvc.updateStatus(guList.get(0).getUserId());	
			String url = req.getContextPath()+"/statusSuccess.jsp";
			res.sendRedirect(url);

		} else {
			cuSvc.updateStatus(cuList.get(0).getCorpUserId());
			String url = req.getContextPath()+"/statusSuccess.jsp";
			res.sendRedirect(url);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
