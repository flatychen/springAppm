package cn.appm.web.front;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import cn.appm.base.wrapper.CommonDataWrapper;
import cn.appm.services.user.bo.UserLoginBean;
import cn.appm.services.user.service.UserAuthService;
import cn.appm.web.WebConstants;
import cn.appm.web.common.CommonController;

/**
 * 登陆
 * @author flatychen
 * @date 2014-4-17
 */
@Controller
@RequestMapping("/f/")
public class LoginController extends CommonController{
	
	@Resource
	protected UserAuthService userAuthService;
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(){
		return "/views/front/login";
	}

	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public CommonDataWrapper login(UserLoginBean login){
		login.setServerCaptcha(super.getSecurityCode());
		CommonDataWrapper json = userAuthService.AuthBaseInfo(login);
		if(json.isSuccess()){
			super.setSessionAttribute(WebConstants.USER_SESSION, json.getData().get(WebConstants.USER_SESSION));
		}
		return json;
	}
	
	@RequestMapping(value="/loginOut",method=RequestMethod.GET)
	public String loginOut(){
		super.getSession().removeAttribute(WebConstants.USER_SESSION);
		return "redirect:/f/index";
	}
	
}
