//�����TFA10201�������t�d
package com.WebManager.model;

import java.sql.Blob;

public class WebManagerVO implements java.io.Serializable {
	private Integer	managerId;
	private String	managerName;
	private String	managerEmail;
	private String	managerAccount;
	private String	managerPassword;
	private Blob	managerPic;
	private Integer	managerStatus;
	
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
	public Blob getManagerPic() {
		return managerPic;
	}
	public void setManagerPic(Blob managerPic) {
		this.managerPic = managerPic;
	}
	public Integer getManagerStatus() {
		return managerStatus;
	}
	public void setManagerStatus(Integer managerStatus) {
		this.managerStatus = managerStatus;
	}


}
