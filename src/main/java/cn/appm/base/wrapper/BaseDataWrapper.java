package cn.appm.base.wrapper;

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
		if( StringUtils.isBlank(message)){
			if(this.success){
				this.message = "成功！";
			}else{
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
	
	
}
