package com.WebManager.model;

import java.util.List;

import com.GeneralUser.model.GeneralUserVO;

public class WebManagerService {

	private WebManagerDAO_interface dao;

	public WebManagerService() {
		dao = new WebManagerJDBCDAO();
	}

	public WebManagerVO addWebManager(String managerName, String managerEmail, String managerAccount,
			String managerPassword, byte[] managerPic, Integer managerStatus) {

		WebManagerVO webManagerVO = new WebManagerVO();
		webManagerVO.setManagerName(managerName);
		webManagerVO.setManagerEmail(managerEmail);
		webManagerVO.setManagerAccount(managerAccount);
		webManagerVO.setManagerPassword(managerPassword);
		webManagerVO.setManagerPic(managerPic);
		webManagerVO.setManagerStatus(managerStatus);
		dao.insert(webManagerVO);

		return webManagerVO;

	}

	public WebManagerVO updateWebManager(Integer managerId, String managerName, String managerEmail,
			String managerAccount, String managerPassword, byte[] managerPic, Integer managerStatus) {

		WebManagerVO webManagerVO = new WebManagerVO();
		webManagerVO.setManagerId(managerId);
		webManagerVO.setManagerName(managerName);
		webManagerVO.setManagerEmail(managerEmail);
		webManagerVO.setManagerAccount(managerAccount);
		webManagerVO.setManagerPassword(managerPassword);
		webManagerVO.setManagerPic(managerPic);
		webManagerVO.setManagerStatus(managerStatus);
		dao.update(webManagerVO);

		return webManagerVO;

	}

	public void deleteWebManager(Integer managerId) {
		dao.delete(managerId);
	}

	public WebManagerVO getOneWebManager(Integer managerId) {
		return dao.findByPrimaryKey(managerId);
	}

	public List<WebManagerVO> getAll() {
		return dao.getAll();
	}

	public WebManagerVO updateManagerPic(Integer managerId, byte[] buf) {

		WebManagerVO webManagerVO = dao.findByPrimaryKey(managerId);
		webManagerVO.setManagerPic(buf);
		dao.update(webManagerVO);
		return webManagerVO;

	}
	
	public WebManagerVO updateProfile(Integer managerId,String managerPassword) {

		WebManagerVO webManagerVO = dao.findByPrimaryKey(managerId);
		webManagerVO.setManagerPassword(managerPassword);		
		dao.update(webManagerVO);
		return webManagerVO;

	}

}
