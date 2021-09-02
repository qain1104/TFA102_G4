package com.venue.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.venue.model.VenueService;


@WebServlet("/ReadVenuePic")
public class ReadVenuePic extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("img/gif");
		ServletOutputStream out = res.getOutputStream();

		String id = req.getParameter("venueSN");
		
		if (id != null) {
			Integer venueSN = Integer.parseInt(id);
			VenueService venueSvc = new VenueService();
			byte[] b = venueSvc.getOneVenue(venueSN).getVenuePic();

			if (b == null || b.length == 0) {
				InputStream in = getServletContext().getResourceAsStream("/assets/img/null2.jpg");
				byte[] d = new byte[in.available()];
				in.read(d);
				out.write(d);
			} else {
				out.write(b);
			}
		}
	}
}
