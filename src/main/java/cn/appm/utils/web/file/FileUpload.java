package cn.appm.utils.web.file;

import java.io.InputStream;
import java.util.List;

/**
 * 文件上传接口
 * 
 * @author flatychen
 * @date 2014-5-27
 */
public interface FileUpload {

	/**
	 * 单文件上传
	 * 
	 * @param file 文件上传信息 {@link UploadInfo}
	 * @return
	 */
	public UploadResult uploadFile(UploadInfo file);

	/**
	 * 多文件上传
	 * @param files
	 * @return
	 */
	public List<UploadResult> uploadFiles(UploadInfo files[]);

	/**
	 * @param webRootPath web工程目录
	 * @param filePath 保存数据库中或别的路径中的文件名;
	 * @return
	 */
	public InputStream downloadFile(String webRootPath, String saveFilePath);

}
