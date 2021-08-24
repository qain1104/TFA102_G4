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

		System.out.println(resetEmail);

		PrintWriter out = res.getWriter();

		GeneralUserService guSvc = new GeneralUserService();
		CorpUserService cuSvc = new CorpUserService();
		List<GeneralUserVO> guList = guSvc.getAll();
		List<CorpUserVO> cuList = cuSvc.getAll();

		for (GeneralUserVO each : guList) {
			try {
				if (each.getEmail().equals(resetEmail)) {
					req.setAttribute("generalUserVO", each);
					String url = "/generalUser/generalResetPassword.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (CorpUserVO each : cuList) {
			try {
				if (each.getEmail().equals(resetEmail)) {
					req.setAttribute("corpUserVO", each);
					String url = "/corpUser/corpResetPassword.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		out.print("<script> alert(\"查無該帳號,請先註冊!\"); </script>");
		out.println("<a href=\"" + req.getContextPath() + "/login.jsp" + "\">前往註冊</a>");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
