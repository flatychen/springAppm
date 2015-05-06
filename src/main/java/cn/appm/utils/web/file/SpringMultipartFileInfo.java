package cn.appm.utils.web.file;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import cn.appm.utils.web.file.imageDecorate.ImageClipInfo;

/**
 * Spring 上传文件信息实现
 * 
 * @author flatychen
 * @date 2014-4-21
 */
public class SpringMultipartFileInfo extends ImageClipInfo implements
		UploadInfo {

	private MultipartFile file;

	private InputStream fileStream;

	public SpringMultipartFileInfo(MultipartFile file) {
		super();
		this.file = file;

		try {
			this.fileStream = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public long getFileSize() {
		return file.getSize();
	}

	@Override
	public String getFileContentType() {
		return file.getContentType();
	}

	@Override
	public String getOriginalFileName() {
		return file.getOriginalFilename();
	}

	@Override
	public String getFormName() {
		return file.getName();
	}

	@Override
	public boolean isEmpty() {
		return file.isEmpty();
	}

	@Override
	public InputStream getInputStream() {
		return this.fileStream;
	}

	@Override
	public void setFileInputStream(InputStream in) {
		this.fileStream = in;
	}

	@Override
	public String getServletContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
