package cn.appm.utils.web.file;

import org.apache.commons.io.FilenameUtils;

/**
 * 上传结果
 * 
 * @author flatychen
 * @date 2014-4-21
 * 
 */
public class UploadResult {
	
	public UploadResult() {
	}
	

	public UploadResult(String uploadFormName, String uploadFileName) {
		super();
		this.uploadFormName = uploadFormName;
		this.uploadFileName = uploadFileName;
		this.fileExtName = FilenameUtils.getExtension(uploadFileName);
	}

	
	
	/**
	 * 上传成功标志
	 */
	private boolean success;

	/**
	 * 上传消息
	 */
	private String message;

	/**
	 * 上传input表单名称
	 */
	private String uploadFormName;

	/**
	 * 文件上传时在表单中名字,包含扩展名
	 */
	private String uploadFileName;

	/**
	 * web引用路径前缀
	 */
	private String webLinkPath;
	
	/**
	 * 文件名，不含扩展名
	 */
	private String fileName;

	/**
	 * 文件扩展名
	 */
	private String fileExtName;

	public String getUploadFileName() {
		return uploadFileName;
	}

	public String getMessage() {
		if (this.success) {
			this.message = "上传成功";
		}
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 得到上传文件名表单名称
	 * @author flatychen
	 * @date 2014-5-27
	 * @return
	 */
	public String getUploadFormName() {
		return uploadFormName;
	}

	public void setUploadFormName(String uploadFormName) {
		this.uploadFormName = uploadFormName;
	}


	/**
	 * 文件扩展名
	 * @author flatychen
	 * @date 2014-5-27
	 * @return
	 */
	public String getFileExtName() {
		return fileExtName;
	}

	/**
	 * 得到WEB形式（unix文件分隔'/'）真实文件名称，无扩展名
	 * @author flatychen
	 * @date 2014-5-27
	 * @return
	 */
	public String getFileNameWithOutExtForWeb() {
		return FilenameUtils.normalize(fileName, true);
	}

	/**
	 * 得到WEB形式（unix文件分隔'/'）真实文件名称，有扩展名
	 * @author flatychen
	 * @date 2014-5-27
	 * @return
	 */
	public String getFileNameWithExtForWeb() {
		return getFileNameWithOutExtForWeb()+"."+this.getFileExtName();
	}
	
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName ;
	}

	/**
	 * 同{@link AbstractFileUpload#webPrefix}变量
	 * @author flatychen
	 * @date 2014-5-27
	 * @return
	 */
	public String getWebLinkPath() {
		return webLinkPath;
	}

	public void setWebLinkPath(String webLinkPath) {
		this.webLinkPath = webLinkPath;
	}

}
