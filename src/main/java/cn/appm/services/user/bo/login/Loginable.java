package cn.appm.services.user.bo.login;

public interface Loginable {
	
	public String getLoginName();
	
	public String getLoginPwd();
	
	public String getEncryptLoginPwd();
	
	public boolean checkCaptcha();	

}
