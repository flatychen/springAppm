package cn.appm.utils.web.file;

import java.io.InputStream;

/**
 * 文件信息接口，适用于不用的MVC框架
 * <p>
 * 	spring mvc : {@link SpringMultipartFileInfo}
 * </p>
 *  <p>
 * 	struts : {@link StrutsFileUploadInfo}
 * </p>
 * @author flatychen
 * @date 2014-4-21
 */
public interface UploadInfo {

	public long getFileSize();

	public String getFileContentType();

	public String getOriginalFileName();
	
	public String getFormName();
	
	public boolean isEmpty();
	
	public InputStream getInputStream();

	
	/**
	 * 得到web工程访问URL路径
	 * @return
	 */
	public String getServletContextPath();
	
	public void setFileInputStream(InputStream in);
	
}
