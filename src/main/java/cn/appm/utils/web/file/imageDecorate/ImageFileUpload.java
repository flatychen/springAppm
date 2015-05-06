package cn.appm.utils.web.file.imageDecorate;

import cn.appm.utils.web.file.DownloadResult;
import cn.appm.utils.web.file.FileUpload;

/**
 * 图片上传接口，使用时使用接口，不要使用 {@link FileToImageUploadAdapter} 实现
 * @author flatychen
 * @date 2014-5-30
 * @version 
 */
public interface ImageFileUpload extends FileUpload {

	DownloadResult clipExistImage(String imagePath,ImageClipInfo clipInfo);
	
	
}
