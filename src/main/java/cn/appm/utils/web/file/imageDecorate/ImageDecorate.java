package cn.appm.utils.web.file.imageDecorate;


import java.io.IOException;

import cn.appm.utils.web.file.upload.FileRepo;

/**
 * 图片后续处理接口，如压缩，水印
 * @author flatychen
 * @date 2014-5-27
 */
public interface ImageDecorate {
	
	
	 /**
	 * 对图片进行后续处理
	 * @author flatychen
	 * @date 2014-5-27
	 * @param FileRepo 文件上传处理
	 * @param imageUploadInfo 图片流包装类
	 * @param uploadResult  上传结果
	 * @return
	 * @throws IOException
	 */
	void decorate(FileRepo fileRepo,ImageStreamWraper imageStreamWraper,String fileAbsolutePath) throws IOException;

	
	
	String getSuffix();
	
}
