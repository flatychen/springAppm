package cn.appm.services.user.service.support;

import cn.appm.services.user.bo.login.LoginSuccessable;

public interface AuthAfterProcessor {

	public Object afterProcessor(LoginSuccessable loginSuccess);

}
