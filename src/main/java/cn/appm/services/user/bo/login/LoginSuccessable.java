package cn.appm.services.user.bo.login;

public interface LoginSuccessable {
	
	public Long getUserId();
	
	public boolean checkLoginUser(Loginable login);

}
