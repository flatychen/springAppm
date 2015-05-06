package cn.appm.services.user.model;

import org.hibernate.validator.constraints.NotBlank;

import cn.appm.services.user.bo.login.LoginSuccessable;
import cn.appm.services.user.bo.login.Loginable;


public class User  implements LoginSuccessable {

	protected Long id;
	@NotBlank
	protected String userName;
	@NotBlank
	protected String pwd;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public Long getUserId() {
		return this.id;
	}
	
	@Override
	public boolean checkLoginUser(Loginable u) {
		if (u == null) return false;
		if(u.getLoginName().equals(this.userName) && u.getEncryptLoginPwd().equals(this.pwd)){
			return true;
		}else{
			return false;
		}
	}
	

}
