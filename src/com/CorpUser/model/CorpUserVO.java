//本表由TFA10201黃鼎謙負責
package com.CorpUser.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CorpUserVO implements java.io.Serializable {
	private Integer corpUserId;
	private Integer registerStatus;
	private String corpAccount;
	private String corpPassword;
	private String companyName;
	private String ltdNo;
	private String email;
	private String phone;
	private String address;
	private byte[] profilePic;
	private Timestamp createdTime;

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

	public String getLtdNo() {
		return ltdNo;
	}

	public void setLtdNo(String ltdNo) {
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

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {// 方便測試用
		return "CorpUserVO [corpUserId=" + corpUserId + ", registerStatus=" + registerStatus + ", corpAccount="
				+ corpAccount + ", corpPassword=" + corpPassword + ", companyName=" + companyName + ", ltdNo=" + ltdNo
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", profilePic=" + profilePic
				+ ", createdTime=" + createdTime + "]";
	}

	public static byte[] getPictureByteArray(String path) {
		FileInputStream fis = null;
		byte[] buffer = null;
		try {
			fis = new FileInputStream(path);
			buffer = new byte[fis.available()];
			fis.read(buffer);
			return buffer;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer;
	}

	public String showcreatedTime() {
		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return myFmt1.format(createdTime);
	}
}