package com.WebManager.model;

import java.util.List;


public class Test {
	public static void main(String[] args) {
		
	WebManagerJDBCDAO DAO = new WebManagerJDBCDAO();
		// �s�W����
//		WebManagerVO webManagerVO = new WebManagerVO();
//		webManagerVO.setManagerName("ManagerName");
//		webManagerVO.setManagerEmail("ManagerEmail");
//		webManagerVO.setManagerAccount("ManagerAccount");
//		webManagerVO.setManagerPassword("ManagerPassword");
//		byte[] is = webManagerVO .getPictureByteArray("pics/manager.png");
//		webManagerVO.setManagerPic(is);
//		webManagerVO.setManagerStatus(0);
//		DAO.insert(webManagerVO );

		// �ק����
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

		// �R������
		 DAO.delete(3004);
		
		// �d�ߴ���
		
		System.out.println(DAO.findByPrimaryKey(3001));
		System.out.println("����H�W���d�浧�A�H�U���d����");
		List<WebManagerVO> list = DAO.getAll();

		for (WebManagerVO each : list) {
			System.out.println(each);
		}

	}
}
