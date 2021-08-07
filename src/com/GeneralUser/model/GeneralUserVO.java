//本表由TFA10201黃鼎謙負責
package com.GeneralUser.model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
//VO的目的：用在client端與server端間傳遞資料
import java.sql.Date;
import java.sql.Timestamp;
//貼上Serializalbe標籤才能讓物件被傳輸出去
public class GeneralUserVO implements java.io.Serializable {
	private Integer userId;
	private Integer registerStatus;
	private String  userAccount;
	private String  userPassword;
	private String  userName;
	private String  id;
	private String  email;
	private String  address;
	private String  phone;
	private byte[]  profilePic;
	private Timestamp createdTime;
	private Integer gender;
	
	public GeneralUserVO() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(Integer registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "GeneralUserVO [userId=" + userId + ", registerStatus=" + registerStatus + ", userAccount=" + userAccount
				+ ", userPassword=" + userPassword + ", userName=" + userName + ", id=" + id + ", email=" + email
				+ ", address=" + address + ", phone=" + phone + ", profilePic=" + profilePic + ", createdTime="
				+ createdTime + ", gender=" + gender + "]";
	}
	
	public static byte[] getPictureByteArray(String path)  {
		FileInputStream fis =null;
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
}
