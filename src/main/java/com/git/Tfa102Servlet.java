package com.git;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Tfa102Servlet")
public class Tfa102Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.getWriter().write("Hello Kyle01!! ~~");
	}

}
