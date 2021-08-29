//�����TFA10201�������t�d
package com.GeneralUser.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
//VO���ت��G�Φbclient�ݻPserver�ݶ��ǻ����
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

//�K�WSerializalbe���Ҥ~��������Q�ǿ�X�h
public class GeneralUserVO implements java.io.Serializable {
	private Integer userId; // �����
	private Integer registerStatus; // ���U�� �w�]��0
	private String userAccount; // �����
	private String userPassword; // �i�H��
	private String userName; // �����
	private String id; // �����
	private String email; // �����
	private String address; // �i�H��
	private String phone; // �i�H��
	private byte[] profilePic; // ���U����W�� �i�H�b�ӤH�����ק�
	private Timestamp createdTime; // ���U�� ���U�ɶ�
	private Integer gender; // �����

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
