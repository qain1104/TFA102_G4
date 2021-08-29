package com.CorpUser.model;

import java.util.List;

public class CorpUserTest {
	public static void main(String[] args) {
		CorpUserJDBCDAO DAO = new CorpUserJDBCDAO();
//		//新增測試
//				CorpUserVO corpUserVO = new CorpUserVO();
//				corpUserVO.setRegisterStatus(0);
//				corpUserVO.setCorpAccount("2020jhas");
//				corpUserVO.setCorpPassword("1234pp");
//				corpUserVO.setCompanyName("jhas.Ltd");
//				corpUserVO.setLtdNo("hh1234");
//				corpUserVO.setEmail("jhas@gmail.com.oo");
//				corpUserVO.setPhone("0921834213");
//				corpUserVO.setAddress("台中市沙鹿區沙鹿路4號5樓");
//				corpUserVO.setProfilePic(null);
//				corpUserVO.setCreatedTime(java.sql.Timestamp.valueOf("2021-08-10 21:21:39"));
//				DAO.insert(corpUserVO);
		
		//查詢測試
		CorpUserVO dd = DAO.findByPrimaryKey(2001);
		System.out.println(dd);
		System.out.println("此行以上為查單筆，以下為查全部");
		List<CorpUserVO> list = DAO.getAll();
		for(CorpUserVO each : list) {
			System.out.println(each);
		}
		
		//修改測試
//		CorpUserVO corp2 = new CorpUserVO();
//		corp2.setCorpUserId(2006);
//		corp2.setRegisterStatus(1);
//		corp2.setCorpAccount("2021jwas");
//		corp2.setCorpPassword("asdf122");
//		corp2.setCompanyName("Jwas.Ltd");
//		corp2.setLtdNo("LJD213");
//		corp2.setEmail("2021Jwas@hotmail.com");
//		corp2.setPhone("23452345");
//		corp2.setAddress("台北市復興南路2段601號8樓");
//		byte[] is = CorpUserVO.getPictureByteArray("pics/FC_Barcelona.png");
//		corp2.setProfilePic(is);
//		corp2.setCreatedTime(java.sql.Timestamp.valueOf("2021-08-07 21:58:20"));
//		DAO.update(corp2);
	}
}
		
//		刪除
//		for(int i = 2007; i<=2014; i++) {
//			DAO.delete(i);
//		}
	