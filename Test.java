package com.GeneralUser.model;

import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		GeneralUserJDBCDAO DAO = new GeneralUserJDBCDAO();
//		//新增測試
//
//		GeneralUserVO generalUserVO = new GeneralUserVO();
//		generalUserVO.setRegisterStatus(0);
//		generalUserVO.setUserAccount("userAccount");
//		generalUserVO.setUserPassword("userPassword");
//		generalUserVO.setUserName("userName");
//		generalUserVO.setId("id");
//		generalUserVO.setEmail("email");
//		generalUserVO.setAddress("address");
//		generalUserVO.setPhone("129309815");
//		byte[] is = GeneralUserVO.getPictureByteArray("pics/generalUser.png");
//		generalUserVO.setProfilePic(is);
//		generalUserVO.setCreatedTime(java.sql.Timestamp.valueOf("2021-08-07 01:51:00"));
//		generalUserVO.setGender(1);
//		DAO.insert(generalUserVO);
		
		
		//修改測試
		GeneralUserVO generalUserVO2 = new GeneralUserVO();
		generalUserVO2.setUserId(1011);
		generalUserVO2.setRegisterStatus(0);
		generalUserVO2.setUserAccount("xxxx");
		generalUserVO2.setUserPassword("yyyy");
		generalUserVO2.setUserName("zzzzz");
		generalUserVO2.setId("fffff");
		generalUserVO2.setEmail("gggggg");
		generalUserVO2.setAddress("qqqqqqqq");
		generalUserVO2.setPhone("111111111");
		byte[] is2 = GeneralUserVO.getPictureByteArray("pics/generalUser.png");
		generalUserVO2.setProfilePic(is2);
		generalUserVO2.setCreatedTime(java.sql.Timestamp.valueOf("2021-08-09 01:51:00"));
		generalUserVO2.setGender(0);
		DAO.update(generalUserVO2);
		
		
		//刪除測試
		//DAO.delete(1011);
		
		
		//查詢測試
		
		System.out.println(DAO.findByPrimaryKey(1001));
		System.out.println("此行以上為查單筆，以下為查全部");
		
		GeneralUserJDBCDAO getall = new GeneralUserJDBCDAO();
		 List<GeneralUserVO> all = getall.getAll();
		 
		 for(GeneralUserVO each : all) {
			 System.out.println(each);
		 }
	}

}
