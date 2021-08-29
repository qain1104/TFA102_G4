package com.CorpUser.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.GeneralUser.model.GeneralUserVO;

public class CorpUserService {

	private CorpUserDAO_interface dao;

	public CorpUserService() {
		dao = new CorpUserJDBCDAO();
	}

	public CorpUserVO addCorpUser(Integer registerStatus, String corpAccount, String corpPassword, String companyName,
			String ltdNo, String email, String phone, String address, byte[] profilePic, Timestamp createdTime) {

		CorpUserVO corpUserVO = new CorpUserVO();
		corpUserVO.setRegisterStatus(registerStatus);
		corpUserVO.setCorpAccount(corpAccount);
		corpUserVO.setCorpPassword(corpPassword);
		corpUserVO.setCompanyName(companyName);
		corpUserVO.setLtdNo(ltdNo);
		corpUserVO.setEmail(email);
		corpUserVO.setPhone(phone);
		corpUserVO.setAddress(address);
		corpUserVO.setProfilePic(profilePic);
		corpUserVO.setCreatedTime(createdTime);
		dao.insert(corpUserVO);

		return corpUserVO;
	}

	public CorpUserVO updateCorpUser(Integer corpUserId, Integer registerStatus, String corpAccount,
			String corpPassword, String companyName, String ltdNo, String email, String phone, String address,
			byte[] profilePic, Timestamp createdTime) {

		CorpUserVO corpUserVO = new CorpUserVO();
		corpUserVO.setCorpUserId(corpUserId);
		corpUserVO.setRegisterStatus(registerStatus);
		corpUserVO.setCorpAccount(corpAccount);
		corpUserVO.setCorpPassword(corpPassword);
		corpUserVO.setCompanyName(companyName);
		corpUserVO.setLtdNo(ltdNo);
		corpUserVO.setEmail(email);
		corpUserVO.setPhone(phone);
		corpUserVO.setAddress(address);
		corpUserVO.setProfilePic(profilePic);
		corpUserVO.setCreatedTime(createdTime);
		dao.update(corpUserVO);

		return corpUserVO;
	}

	public void deleteCorpUser(Integer corpUserId) {
		dao.delete(corpUserId);
	}

	public CorpUserVO getOneCorpUser(Integer corpUserId) {
		return dao.findByPrimaryKey(corpUserId);
	}

	public List<CorpUserVO> getAll() {
		return dao.getAll();
	}
	
	public CorpUserVO updateCorpPic(Integer corpUserId, byte[] profilePic) {

		CorpUserVO corpUserVO = dao.findByPrimaryKey(corpUserId);
		corpUserVO.setProfilePic(profilePic);
		dao.update(corpUserVO);
		return corpUserVO;

	}
	
	public CorpUserVO updateProfile(Integer corpUserId,String corpPassword,String phone,String address) {

		CorpUserVO corpUserVO = dao.findByPrimaryKey(corpUserId);
		corpUserVO.setCorpPassword(corpPassword);
		corpUserVO.setPhone(phone);
		corpUserVO.setAddress(address);
		dao.update(corpUserVO);
		return corpUserVO;

	}
	
	public CorpUserVO resetPassword(Integer corpUserId,String corpUserPassword) {

		CorpUserVO corpUserVO = dao.findByPrimaryKey(corpUserId);
		corpUserVO.setCorpPassword(corpUserPassword);
		dao.update(corpUserVO);
		return corpUserVO;
	}
	
	public List<CorpUserVO> getSameEmail(String email) {
		List<CorpUserVO> list = dao.getAll().stream().
				filter(cuVO -> cuVO.getEmail().equals(email))
				.collect(Collectors.toList());
		return list;
	}
	
	public void updateStatus(Integer corpUserId) {

		CorpUserVO corpUserVO = dao.findByPrimaryKey(corpUserId);
		corpUserVO.setRegisterStatus(1);
		dao.update(corpUserVO);
	}
}
