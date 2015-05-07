package cn.appm.base.wrapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 基础数据包装器
 * 
 * @author flatychen
 * 
 */
public class BaseDataWrapper {

	/**
	 * 调用是否成功
	 */
	private boolean success;

	/**
	 * 调用返回消息，如为成功可为空
	 */
	private String message;

	/**
	 * 版本
	 */
	private String version = "1.0";

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		// 默认添加message
		if (StringUtils.isBlank(message)) {
			if (this.success) {
				this.message = "成功！";
			} else {
				this.message = "失败！";
			}
		}
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> data = new HashMap<String, Object>(4);
		data.put("success", this.success);
		data.put("message", this.message);
		data.put("version", this.version);
		return data;
	}

}
