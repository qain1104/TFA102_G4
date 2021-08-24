package com.WebManager.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.GeneralUser.model.GeneralUserService;
import com.GeneralUser.model.GeneralUserVO;
import com.WebManager.model.WebManagerService;
import com.WebManager.model.WebManagerVO;

import sun.rmi.server.Dispatcher;

import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/managerUpload.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
// ��ƾڶq�j��fileSizeThreshold�ȮɡA���e�N�Q�g�J�Ϻ�
// �W�ǹL�{���L�׬O��Ӥ��W�LmaxFileSize�ȡA�Ϊ̤W�Ǫ��`�q�j��maxRequestSize �ȳ��|�ߥXIllegalStateException ���`
public class managerPicUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "manager/managerPic"; // �W���ɮת��ت��a�ؿ�;
	// �N�ѩ��U����26~30��� java.io.File �� ContextPath ���U, �۰ʫإߥئa�ؿ�
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // �B�z�����ɦW
		res.setContentType("text/html; charset=UTF-8");
		Integer managerId = new Integer(req.getParameter("managerId").trim());
		
		
		Part part = req.getPart("managerPic");
		InputStream in  = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		WebManagerService webManagerService = new WebManagerService();
		WebManagerVO webManagerVO = webManagerService.updateManagerPic(managerId, buf);
		req.setAttribute("webManagerVO", webManagerVO);
		RequestDispatcher dispacther = req.getRequestDispatcher("/webManager/managerProfile.jsp");
		dispacther.forward(req, res);
	
			}
		
	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition"); //�x��쥻�w�q���r��N�Ocontent-disposition
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();//���N�O���Xtomcat.gif���N��
		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}