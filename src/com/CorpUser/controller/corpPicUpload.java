package com.CorpUser.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.CorpUser.model.CorpUserService;
import com.CorpUser.model.CorpUserVO;
import com.GeneralUser.model.GeneralUserService;
import com.GeneralUser.model.GeneralUserVO;

import sun.rmi.server.Dispatcher;

import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/corpUpload.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
// 當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
// 上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class corpPicUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "corpUser/profilePic"; // 上傳檔案的目的地目錄;
	// 將由底下的第26~30行用 java.io.File 於 ContextPath 之下, 自動建立目地目錄
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
		res.setContentType("text/html; charset=UTF-8");
		Integer corpUserId = new Integer(req.getParameter("corpUserId").trim());
		
		
		Part part = req.getPart("profilePic");
		InputStream in  = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		
		CorpUserService corpUserSvc = new CorpUserService();
		CorpUserVO corpUserVO = corpUserSvc.updateCorpPic(corpUserId, buf);
		req.setAttribute("corpUserVO", corpUserVO);
		RequestDispatcher dispacther = req.getRequestDispatcher("/corpUser/corpProfile.jsp");
		dispacther.forward(req, res);
			}
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition"); //官方原本定義的字串就是content-disposition
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();//其實就是取出tomcat.gif的意思
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}