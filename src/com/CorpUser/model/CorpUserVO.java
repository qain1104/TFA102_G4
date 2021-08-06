//¥»ªí¥ÑTFA10201¶À¹©Á¾­t³d
package com.CorpUser.model;

import java.sql.Blob;
import java.sql.Date;

public class CorpUserVO implements java.io.Serializable {
	private Integer	corpUserId;
	private Integer registerStatus;
	private String  corpAccount;
	private String  corpPassword;
	private String	companyName;
	private Integer ltdNo;
	private String	email;
	private String	phone;
	private String	address;
	private Blob	profilePic;
	private Date	createdTime;
	
	public CorpUserVO() {
		super();
	}

	public Integer getCorpUserId() {
		return corpUserId;
	}

	public void setCorpUserId(Integer corpUserId) {
		this.corpUserId = corpUserId;
	}

	public Integer getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(Integer registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getCorpAccount() {
		return corpAccount;
	}

	public void setCorpAccount(String corpAccount) {
		this.corpAccount = corpAccount;
	}

	public String getCorpPassword() {
		return corpPassword;
	}

	public void setCorpPassword(String corpPassword) {
		this.corpPassword = corpPassword;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getLtdNo() {
		return ltdNo;
	}

	public void setLtdNo(Integer ltdNo) {
		this.ltdNo = ltdNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Blob getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(Blob profilePic) {
		this.profilePic = profilePic;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}
