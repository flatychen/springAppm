package cn.appm.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 基础控制器
 * @author flatychen
 *
 */
public class BaseController   {

	/**
	 * 得到WEB工程根路径
	 * 
	 * @author flatychen
	 * @date 2014-4-18
	 * @return
	 */
	protected final String getWebRootPath() {
		return getRequest().getServletContext().getRealPath("");
	}

	/**
	 * 得到request
	 * 
	 * @author flatychen
	 * @date 2014-4-28
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
	}

	/**
	 * 取得HttpSession
	 * 
	 * @return
	 * @author flatychen
	 * @date 2014-5-7
	 */
	protected HttpSession getSession() {
		return getRequest().getSession(true);
	}

	/**
	 * 得到request属性
	 * 
	 * 
	 * @date 2014-4-28
	 * @param attributeName
	 * @return
	 */
	protected Object getRequestAttribute(String name) {
		return RequestContextHolder.currentRequestAttributes().getAttribute(
				name, RequestAttributes.SCOPE_REQUEST);
	}

	/**
	 * 设置request属性
	 * 
	 * 
	 * @date 2014-4-28
	 * @param name
	 * @return
	 */
	protected void setRequestAttribute(String name, Object value) {
		RequestContextHolder.currentRequestAttributes().setAttribute(name,
				value, RequestAttributes.SCOPE_REQUEST);
	}

	/**
	 * 得到sesssion属性
	 * 
	 * @author flatychen
	 * @date 2014-4-28
	 * @param name
	 * @return
	 */
	protected Object getSessionAttribute(String name) {
		return RequestContextHolder.currentRequestAttributes().getAttribute(
				name, RequestAttributes.SCOPE_SESSION);
	}

	/**
	 * 设置sesssion属性
	 * 
	 * @author flatychen
	 * @date 2014-7-25
	 * @param name
	 * @param value
	 * @return
	 * @version
	 */
	protected void setSessionAttribute(String name, Object value) {
		this.getSession().setAttribute(name, value);
	}

	/**
	 * 得到ServletContext
	 * 
	 * @author flatychen
	 * @date 2014-4-28
	 * @param attributeName
	 * @return
	 */
	protected ServletContext getServletContext() {
		return this.getRequest().getServletContext();
	}

	/**
	 * 得到ServletContext
	 * 
	 * @param name
	 * @return
	 * @author flatychen
	 */
	protected Object getServletContextAttritube(String name) {
		return this.getRequest().getServletContext().getAttribute(name);
	}

	/**
	 * 
	 * 设置ServletContext
	 * 
	 * @param name
	 * @param value
	 * @author flatychen
	 */
	protected void setServletContextAttritube(String name, Object value) {
		this.getRequest().getServletContext().setAttribute(name, value);
	}
}
