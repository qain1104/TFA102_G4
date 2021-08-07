//¥»ªí¥ÑTFA10201¶À¹©Á¾­t³d
package com.WebManager.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;

public class WebManagerVO implements java.io.Serializable {
	private Integer managerId;
	private String managerName;
	private String managerEmail;
	private String managerAccount;
	private String managerPassword;
	private byte[] managerPic;
	private Integer managerStatus;

	public WebManagerVO() {
		super();
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getManagerAccount() {
		return managerAccount;
	}

	public void setManagerAccount(String managerAccount) {
		this.managerAccount = managerAccount;
	}

	public String getManagerPassword() {
		return managerPassword;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

	public byte[] getManagerPic() {
		return managerPic;
	}

	public void setManagerPic(byte[] managerPic) {
		this.managerPic = managerPic;
	}

	public Integer getManagerStatus() {
		return managerStatus;
	}

	public void setManagerStatus(Integer managerStatus) {
		this.managerStatus = managerStatus;
	}

	@Override
	public String toString() {
		return "WebManagerVO [managerId=" + managerId + ", managerName=" + managerName + ", managerEmail="
				+ managerEmail + ", managerAccount=" + managerAccount + ", managerPassword=" + managerPassword
				+ ", managerPic=" + managerPic + ", managerStatus=" + managerStatus + "]";
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

}
