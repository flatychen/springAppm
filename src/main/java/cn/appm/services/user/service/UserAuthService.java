package cn.appm.services.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appm.base.wrapper.CommonDataWrapper;
import cn.appm.persistance.user.UserDao;
import cn.appm.services.user.bo.login.LoginSuccessable;
import cn.appm.services.user.bo.login.Loginable;
import cn.appm.services.user.service.support.AuthAfterProcessor;
import cn.appm.services.user.service.support.AuthBeforeProcessor;
import cn.appm.web.WebConstants;


@Service("userAuthService")
public class UserAuthService {
	
	@Resource
	private UserDao userDao;
	
	@Autowired(required=false)
	private List<AuthBeforeProcessor> beforeProcessors;
	
	@Autowired(required=false)
	private List<AuthAfterProcessor> afterProcessors;
	
	
	public CommonDataWrapper AuthBaseInfo(Loginable login) {
		CommonDataWrapper wrapper = new CommonDataWrapper();
		
		// 验证码检查
		if(!login.checkCaptcha()){
			wrapper.setSuccess(false);
			wrapper.setMessage("验证码不匹配");
			return wrapper;
		}
		
		// 登陆前置拦截
		if(beforeProcessors != null && beforeProcessors.size() > 0 ){
			for (AuthBeforeProcessor authBeforeProcessor : beforeProcessors) {
				authBeforeProcessor.beforeProcessor(login, wrapper);
				return wrapper;
			}
		}
		
		// 检查用户与密码
		LoginSuccessable u = this.userDao.getUserByUserName(login.getLoginName());
		if( u == null){
			wrapper.setSuccess(false);
			wrapper.setMessage("用户不存在");
			return wrapper;
		}else{
			if( !u.checkLoginUser(login)){
				wrapper.setSuccess(false);
				wrapper.setMessage("用户名与密码不匹配");
				return wrapper;
			}
		}
		// 检查用户名成功 
		// 登陆后置拦截
		if(beforeProcessors != null && beforeProcessors.size() > 0 ){
			for (AuthAfterProcessor authAfterProcessor : afterProcessors) {
				authAfterProcessor.afterProcessor(u, wrapper);
			}
		}
		
		wrapper.setSuccess(true);
		wrapper.buildWrapper(2).getData().put(WebConstants.USER_SESSION, u);
		return wrapper;
	}
	

}
