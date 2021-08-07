package com.SportsNews.model;

import java.util.List;



public class Test {
	public static void main(String[] args) {
		SportsNewsJDBCDAO DAO = new SportsNewsJDBCDAO();
//		//新增測試
//		SportsNewsVO sportsNewsVO = new SportsNewsVO();
//		sportsNewsVO.setManagerId(3001);
//		sportsNewsVO.setTitle("台灣國手摘8面金牌");
//		sportsNewsVO.setContent("痛打中國痛打中國痛打中國痛打中國痛打中國痛打中國痛打中國痛打中國");
//		sportsNewsVO.setNewsDate(java.sql.Timestamp.valueOf("2021-08-06 00:00:00"));
//		sportsNewsVO.setNewsSource("yahoo.com.tw");
//		sportsNewsVO.setNewsType(1);
//		DAO.insert(sportsNewsVO);
		
		
		//修改測試
		SportsNewsVO sportsNewsVO2 = new SportsNewsVO();
		sportsNewsVO2.setNewsSn(23001);
		sportsNewsVO2.setManagerId(3001);
		sportsNewsVO2.setTitle("台灣國手摘12面金牌");
		sportsNewsVO2.setContent("痛打中國痛打中國痛打中國痛打中國痛打中國痛打中國痛打中國痛打中國");
		sportsNewsVO2.setNewsDate(java.sql.Timestamp.valueOf("2021-08-07 00:00:00"));
		sportsNewsVO2.setNewsSource("yahoo.com.tw");
		sportsNewsVO2.setNewsType(1);
		DAO.update(sportsNewsVO2);
		
		
//		刪除測試
//		DAO.delete(23006);
		
		
		//查詢測試
		
		System.out.println(DAO.findByPrimaryKey(23001));
		System.out.println("此行以上為查單筆，以下為查全部");
		
		SportsNewsJDBCDAO getall = new SportsNewsJDBCDAO();
		 List<SportsNewsVO> all = getall.getAll();
		 
		 for(SportsNewsVO each : all) {
			 System.out.println(each);
		 }
	}



}

