package cn.appm.services.user.service.support;

import cn.appm.base.wrapper.CommonDataWrapper;
import cn.appm.services.user.bo.login.LoginSuccessable;
import cn.appm.services.user.bo.login.Loginable;

public interface AuthAfterProcessor {
	
	public Object afterProcessor(LoginSuccessable loginSuccess,CommonDataWrapper wrapper);

}
