package cn.appm.web.front.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import cn.appm.web.WebConstants;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object o = WebUtils.getSessionAttribute(request, WebConstants.USER_SESSION);
		if( o == null){
			String base = (String) request.getAttribute(WebConstants.BASE);
			response.sendRedirect(base + "/f/login");
			return false;
		}
		return true;
	}



}
