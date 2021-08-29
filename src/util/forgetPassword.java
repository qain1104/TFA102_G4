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

@WebServlet("/forgetPassword")
public class forgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String resetEmail = req.getParameter("resetEmail");
		PrintWriter out = res.getWriter();

		GeneralUserService guSvc = new GeneralUserService();
		List<GeneralUserVO> guList = guSvc.getSameEmail(resetEmail);

		CorpUserService cuSvc = new CorpUserService();
		List<CorpUserVO> cuList = cuSvc.getAll();

		if (guList.isEmpty() && cuList.isEmpty()) {
			out.print("<script> alert(\"查無該帳號,請先註冊!\"); </script>");
			out.println("<a href=\"" + req.getContextPath() + "/login.jsp" + "\">前往註冊</a>");

		} else if (!guList.isEmpty()) {
			req.setAttribute("generalUserVO", guList.get(0));
			String url = "/generalUser/generalResetPassword.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

		} else {
			req.setAttribute("currentC", cuList.get(0));
			String url = "/corpUser/corpResetPassword.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
