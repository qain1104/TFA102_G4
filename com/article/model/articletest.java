package com.article.model;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class articletest {

	public static void main(String[] args) {
		ARTICLEService artSvc=new ARTICLEService();
		ARTICLEJDBCDAO dao = new ARTICLEJDBCDAO();
		String search="買";
		List<ARTICLEVO> popVO=artSvc.getSearchArticle(search);
		for (ARTICLEVO aEmp : popVO) {
		System.out.println(aEmp.toString());
		}
		System.out.println("==================");
		Integer userId=1001;
		
				
//		byte[] bytes = "A byte array".getBytes();
//		Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		//		 新增
//			ARTICLEVO aVO1 = new ARTICLEVO();
//			aVO1.setUserId(1001);
//			aVO1.setArticleClass(0);
//			aVO1.setArticleContent(blob);
//			aVO1.setArticleType(0);
//			aVO1.setArticleTitle("標題");
//			aVO1.setArticlePop(0);
//			aVO1.setArticleLikes(0);
//			aVO1.setArticleDate(java.sql.Timestamp.valueOf("2021-07-19 09:20:00.0"));
//			aVO1.setArticleUpDate(null);
//			aVO1.setArticleStatus(0);
//			int updateCount_insert = dao.insert(aVO1);
//			System.out.println(updateCount_insert);
	    
				

//		 修改
//		popVO.setArticleSN(4001);
//		popVO.setArticlePop(popVO.getArticlePop()+1);
//		popVO.setArticleUpDate(new java.sql.Timestamp(new java.util.Date().getTime()));
//		aVO2.setUserId(1002);
//		aVO2.setArticleClass(0);
//		aVO2.setArticleContent(bytes);
//		aVO2.setArticleType(0);
//		aVO2.setArticleTitle("標題");
//		aVO2.setArticlePop(0);
//		aVO2.setArticleLikes(0);
//		aVO2.setArticleDate(java.sql.Timestamp.valueOf("2021-07-19 09:20:00.0"));
//		aVO2.setArticleUpDate(null);
//		aVO2.setArticleStatus(0);
//		 int updateCount_update = dao.update(popVO);
//		 System.out.println(updateCount_update);
				

		 //刪除
//		 int updateCount_delete = dao.delete(4007);
//		 System.out.println(updateCount_delete);

		// 查詢
//		ARTICLEVO aVO3 = dao.findByPrimaryKey(4001);
//		System.out.println(aEmp.toString());
//		System.out.println("---------------------");

		// 查詢
//		List<ARTICLEVO> list = dao.getAll();
//		for (ARTICLEVO aEmp : list) {
//			System.out.println(aEmp.toString());
//		}
		
//		System.out.println("======================");
//		ARTICLEVO apopVO = dao.findByPrimaryKey(4001);
//System.out.println(apopVO.toString());
//	}
//		ARTICLEVO popVO2=artSvc.getOneArticle(4001);
//		System.out.println(popVO2.toString());
	}
}
