package cn.appm.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.appm.web.WebConstants;

public class BaseInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		this.addBaseContext(request); // 添加web上下文路径
		return true;
	}

	private void addBaseContext(HttpServletRequest request) {
		String base = request.getContextPath();
		String servletPath = request.getServletPath();
		request.setAttribute(WebConstants.BASE,request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + base);
		request.setAttribute(WebConstants.WEBAPP_PATH,request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		request.setAttribute(WebConstants.CURRENT_PATH, request.getAttribute(WebConstants.BASE) + servletPath);
	}


}
