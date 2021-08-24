package util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CorpUser.model.CorpUserService;
import com.GeneralUser.model.GeneralUserService;
import com.WebManager.model.WebManagerService;

@WebServlet("/Readerpic")
public class Readerpic extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("img/gif");
		ServletOutputStream out = res.getOutputStream();

		String id = req.getParameter("userId");
		String corpId = req.getParameter("corpUserId");
		String webId = req.getParameter("managerId");

		if (id != null) {
			Integer userId = Integer.parseInt(id);
			GeneralUserService guSvc = new GeneralUserService();
			byte[] b = guSvc.getOneGeneralUser(userId).getProfilePic();

			if (b == null || b.length == 0) {
				InputStream in = getServletContext().getResourceAsStream("/assets/img/default.png");
				byte[] d = new byte[in.available()];
				in.read(d);
				out.write(d);
			} else {
				out.write(b);
			}

		}
		
		if (corpId != null) {
			Integer corpUserId = Integer.parseInt(corpId);
			CorpUserService cuSvc = new CorpUserService();
			byte[] b = cuSvc.getOneCorpUser(corpUserId).getProfilePic();

			if (b == null || b.length == 0) {
				InputStream in = getServletContext().getResourceAsStream("/assets/img/default.png");
				byte[] d = new byte[in.available()];
				in.read(d);
				out.write(d);
			} else {
				out.write(b);
			}

		}
		
		if (webId != null) {
			Integer managerId = Integer.parseInt(webId);
			WebManagerService wmSvc = new WebManagerService();
			byte[] b = wmSvc.getOneWebManager(managerId).getManagerPic();

			if (b == null || b.length == 0) {
				InputStream in = getServletContext().getResourceAsStream("/assets/img/default.png");
				byte[] d = new byte[in.available()];
				in.read(d);
				out.write(d);
			} else {
				out.write(b);
			}

		}

	}

}
