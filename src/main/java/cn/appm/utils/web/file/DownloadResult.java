package cn.appm.utils.web.file;

import org.apache.commons.io.FilenameUtils;

/**
 * 下载结果
 * 
 * @author flatychen
 * @date 2014-4-21
 */
public class DownloadResult {
	
	private boolean success;
	
	private String file;
	
	private String message;
	
	
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getMessage() {
		return message;
	}

	public String getfileNameForWeb() {
		return FilenameUtils.normalize(this.file, true);
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	
}
