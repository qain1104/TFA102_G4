package com.WebManager.model;

import java.util.List;


public class Test {
	public static void main(String[] args) {
		
	WebManagerJDBCDAO DAO = new WebManagerJDBCDAO();
		// 新增測試
//		WebManagerVO webManagerVO = new WebManagerVO();
//		webManagerVO.setManagerName("ManagerName");
//		webManagerVO.setManagerEmail("ManagerEmail");
//		webManagerVO.setManagerAccount("ManagerAccount");
//		webManagerVO.setManagerPassword("ManagerPassword");
//		byte[] is = webManagerVO .getPictureByteArray("pics/manager.png");
//		webManagerVO.setManagerPic(is);
//		webManagerVO.setManagerStatus(0);
//		DAO.insert(webManagerVO );

		// 修改測試
		WebManagerVO webManagerVO2 = new WebManagerVO();
		webManagerVO2.setManagerId(3004);
		webManagerVO2.setManagerName("xxxxx");
		webManagerVO2.setManagerEmail("yyyyyyy");
		webManagerVO2.setManagerAccount("zzzzz");
		webManagerVO2.setManagerPassword("xxxxxx");
		byte[] is2 = webManagerVO2.getPictureByteArray("pics/manager.png");
		webManagerVO2.setManagerPic(is2);
		webManagerVO2.setManagerStatus(0);
		DAO.update(webManagerVO2);

		// 刪除測試
		 DAO.delete(3004);
		
		// 查詢測試
		
		System.out.println(DAO.findByPrimaryKey(3001));
		System.out.println("此行以上為查單筆，以下為查全部");
		List<WebManagerVO> list = DAO.getAll();

		for (WebManagerVO each : list) {
			System.out.println(each);
		}

	}
}
