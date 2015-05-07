package cn.appm.web.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appm.services.user.model.User;
import cn.appm.utils.web.FastJsonUtils;
import cn.appm.web.BaseController;
import cn.appm.web.WebConstants;

/**
 * 
 * @author flatychen
 * @date 2014-4-17
 */
@Controller
public class CommonController extends BaseController {

	protected String getSecurityCode() {
		return (String) super.getSessionAttribute(WebConstants.SECURIYT_CODE);
	}

	@RequestMapping(value = "/checkCode", produces = "application/json")
	@ResponseBody
	protected String checkSecurityCode(String clientCaptcha) {
		String serverCode = (String) super
				.getSessionAttribute(WebConstants.SECURIYT_CODE);
		Map<String, Object> json = new HashMap<String, Object>(2);
		if (serverCode.equalsIgnoreCase(clientCaptcha)) {
			json.put("ok", "");
		} else {
			json.put("error", "   ");
		}
		return FastJsonUtils.toJsonString(json);
	}

	protected User getSessionUser() {
		Object o = super.getSessionAttribute(WebConstants.USER_SESSION);
		if (o != null) {
			return (User) o;
		}
		return null;
	}
}
