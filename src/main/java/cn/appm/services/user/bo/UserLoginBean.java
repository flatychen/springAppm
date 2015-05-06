package cn.appm.services.user.bo;

import cn.appm.services.user.bo.login.Loginable;
import cn.appm.services.user.model.User;
import cn.appm.utils.base.BaseSignUtils;


public class UserLoginBean extends User implements Loginable{
	
	private String serverCaptcha;
	private String clientCaptcha;
	
	public String getServerCaptcha() {
		return serverCaptcha;
	}

	public void setServerCaptcha(String serverCaptcha) {
		this.serverCaptcha = serverCaptcha;
	}

	public String getClientCaptcha() {
		return clientCaptcha;
	}

	public void setClientCaptcha(String clientCaptcha) {
		this.clientCaptcha = clientCaptcha;
	}

	
	

	public boolean checkCaptcha(){
		return this.serverCaptcha.equalsIgnoreCase(this.clientCaptcha);
	}

	@Override
	public String getLoginName() {
		return super.getUserName();
	}

	@Override
	public String getLoginPwd() {
		return super.getPwd();
	}

	@Override
	public String getEncryptLoginPwd() {
		return BaseSignUtils.encryptMd5(this.pwd);
	}


}
