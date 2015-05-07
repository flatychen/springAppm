package cn.appm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import cn.appm.base.wrapper.BaseDataWrapper;

/**
 * 
 * spring mvc 全局异常处理
 * 
 * @author flatychen
 * 
 */
@ControllerAdvice
public class DefalutExceptionHandler {

	private static Logger log = LoggerFactory
			.getLogger(DefalutExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request,
			HttpServletResponse response, Exception ex) {
		log.error("{} --> {}", ex.toString(), ex.getStackTrace()[0]);
		BaseDataWrapper data = new BaseDataWrapper();
		data.setSuccess(false);
		data.setMessage("500 EXCEPTION");
		return new ModelAndView("error/500", data.toMap());
	}

}
