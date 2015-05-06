package cn.appm.utils.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;
import org.springframework.web.util.WebUtils;

public class BaseWebUtils extends WebUtils {
	
	/**
	 * 移除session属性
	 * @author flatychen
	 * @date 2014-4-15
	 * @param request
	 * @param name
	 */
	public static void removeSessionAttribute(HttpServletRequest request, String name){
		Assert.notNull(request, "Request must not be null");
		HttpSession session = request.getSession(false);
		if(session != null){
			session.removeAttribute(name);
		}
	}

	
	/**
	 * 获得IP
	 * @author flatychen
	 * @date 2014-4-15
	 * @param request
	 * @return
	 */
	public static String getRequestId(HttpServletRequest request){
		Assert.notNull(request, "Request must not be null");
		return request.getRemoteAddr();
	}
	
}
