package com.GeneralUser.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralUserService {

	private GeneralUserDAO_interface dao;

	public GeneralUserService() {
		dao = new GeneralUserJDBCDAO();
	}

	public GeneralUserVO addGeneralUser(Integer registerStatus, String userAccount, String userPassword,
			String userName, String id, String email, String address, String phone, byte[] profilePic,
			Timestamp createdTime, Integer gender) {

		GeneralUserVO generalUserVO = new GeneralUserVO();
		generalUserVO.setRegisterStatus(registerStatus);
		generalUserVO.setUserAccount(userAccount);
		generalUserVO.setUserPassword(userPassword);
		generalUserVO.setUserName(userName);
		generalUserVO.setId(id);
		generalUserVO.setEmail(email);
		generalUserVO.setAddress(address);
		generalUserVO.setPhone(phone);
		generalUserVO.setProfilePic(profilePic);
		generalUserVO.setCreatedTime(createdTime);
		generalUserVO.setGender(gender);

		dao.insert(generalUserVO);
		return generalUserVO;

	}

	public GeneralUserVO updateGeneralUser(Integer userId, Integer registerStatus, String userAccount,
			String userPassword, String userName, String id, String email, String address, String phone,
			byte[] profilePic, Timestamp createdTime, Integer gender) {

		GeneralUserVO generalUserVO = new GeneralUserVO();
		generalUserVO.setUserId(userId);
		generalUserVO.setRegisterStatus(registerStatus);
		generalUserVO.setUserAccount(userAccount);
		generalUserVO.setUserPassword(userPassword);
		generalUserVO.setUserName(userName);
		generalUserVO.setId(id);
		generalUserVO.setEmail(email);
		generalUserVO.setAddress(address);
		generalUserVO.setPhone(phone);
		generalUserVO.setProfilePic(profilePic);
		generalUserVO.setCreatedTime(createdTime);
		generalUserVO.setGender(gender);

		dao.update(generalUserVO);
		return generalUserVO;

	}

	public void deleteGeneralUser(Integer userId) {
		dao.delete(userId);
	}

	public GeneralUserVO getOneGeneralUser(Integer userId) {
		return dao.findByPrimaryKey(userId);
	}

	public List<GeneralUserVO> getAll() {
		return dao.getAll();
	}

	public GeneralUserVO updateGeneralPic(Integer userId, byte[] profilePic) {

		GeneralUserVO generalUserVO = dao.findByPrimaryKey(userId);
		generalUserVO.setProfilePic(profilePic);
		dao.update(generalUserVO);
		return generalUserVO;

	}

	public GeneralUserVO updateProfile(Integer userId, String userPassword, String phone, String address) {

		GeneralUserVO generalUserVO = dao.findByPrimaryKey(userId);
		generalUserVO.setUserPassword(userPassword);
		generalUserVO.setPhone(phone);
		generalUserVO.setAddress(address);
		dao.update(generalUserVO);
		return generalUserVO;

	}

	public GeneralUserVO resetPassword(Integer userId, String userPassword) {

		GeneralUserVO generalUserVO = dao.findByPrimaryKey(userId);
		generalUserVO.setUserPassword(userPassword);
		dao.update(generalUserVO);
		return generalUserVO;

	}

	public List<GeneralUserVO> getSameEmail(String email) {
		List<GeneralUserVO> list = dao.getAll().stream().filter(guVO -> guVO.getEmail().equals(email))
				.collect(Collectors.toList());
		return list;
	}

	public void updateStatus(Integer userId) {

		GeneralUserVO generalUserVO = dao.findByPrimaryKey(userId);
		generalUserVO.setRegisterStatus(1);
		dao.update(generalUserVO);
	}

}