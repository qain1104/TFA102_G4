package com.SportsNews.model;

import java.util.List;



public class Test {
	public static void main(String[] args) {
		SportsNewsJDBCDAO DAO = new SportsNewsJDBCDAO();
//		//�s�W����
//		SportsNewsVO sportsNewsVO = new SportsNewsVO();
//		sportsNewsVO.setManagerId(3001);
//		sportsNewsVO.setTitle("�x�W���K8�����P");
//		sportsNewsVO.setContent("�h������h������h������h������h������h������h������h������");
//		sportsNewsVO.setNewsDate(java.sql.Timestamp.valueOf("2021-08-06 00:00:00"));
//		sportsNewsVO.setNewsSource("yahoo.com.tw");
//		sportsNewsVO.setNewsType(1);
//		DAO.insert(sportsNewsVO);
		
		
		//�ק����
		SportsNewsVO sportsNewsVO2 = new SportsNewsVO();
		sportsNewsVO2.setNewsSn(23001);
		sportsNewsVO2.setManagerId(3001);
		sportsNewsVO2.setTitle("�x�W���K12�����P");
		sportsNewsVO2.setContent("�h������h������h������h������h������h������h������h������");
		sportsNewsVO2.setNewsDate(java.sql.Timestamp.valueOf("2021-08-07 00:00:00"));
		sportsNewsVO2.setNewsSource("yahoo.com.tw");
		sportsNewsVO2.setNewsType(1);
		DAO.update(sportsNewsVO2);
		
		
//		�R������
//		DAO.delete(23006);
		
		
		//�d�ߴ���
		
		System.out.println(DAO.findByPrimaryKey(23001));
		System.out.println("����H�W���d�浧�A�H�U���d����");
		
		SportsNewsJDBCDAO getall = new SportsNewsJDBCDAO();
		 List<SportsNewsVO> all = getall.getAll();
		 
		 for(SportsNewsVO each : all) {
			 System.out.println(each);
		 }
	}



}

