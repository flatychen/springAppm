package cn.appm.services.user.service.support;

import cn.appm.services.user.bo.login.Loginable;

public interface AuthBeforeProcessor {

	public Object beforeProcessor(Loginable login);

}
