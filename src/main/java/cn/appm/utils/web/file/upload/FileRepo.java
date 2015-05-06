package cn.appm.utils.web.file.upload;

import java.io.IOException;
import java.io.InputStream;

/**
 * 本地文件上传实现
 * 
 * @author flatychen
 * @date 2014-4-30
 */
public interface FileRepo {
	
	public void writeFile(InputStream in, String realFileAbsoluteName) throws IOException;

	public String resolveUploadPath(String webRootPath, String uploadPah) throws IOException ;
	
	public InputStream readUploadedFile(String filePath) throws IOException;

}
