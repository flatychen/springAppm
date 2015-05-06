package cn.appm.utils.web.file.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地文件上传实现
 * 
 * @author flatychen
 * @date 2014-4-30
 */
public class LocalFileUpload implements FileRepo {

	private static Logger log = LoggerFactory.getLogger(LocalFileUpload.class);

	@Override
	public void writeFile(InputStream in, String realFileAbsoluteName)
			throws IOException {

		String _normal_filePath = FilenameUtils.normalize(realFileAbsoluteName);
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"---->>>> file real upload path: {0}", _normal_filePath));
		}
		File file = new File(_normal_filePath);
		FileUtils.copyInputStreamToFile(in, file);
	}

	@Override
	public String resolveUploadPath(String webRootPath, String uploadPath)
			throws IOException {
		// 相对路径
		String _relative_path = null;
		String _webappPath = webRootPath;
		if (StringUtils.startsWith(uploadPath, FileUploadUtil.REALITIVE_PREFIX)) {
			_relative_path = StringUtils.substringAfter(uploadPath,
					FileUploadUtil.REALITIVE_PREFIX);
		}// 同一web容器的相对路径，即平行于本工程的路径
		else if (StringUtils.startsWith(uploadPath,
				FileUploadUtil.WEBAPPS_PREFIX)) {
			_webappPath = FileUploadUtil.getParentPath(webRootPath);
			_relative_path = StringUtils.substringAfter(uploadPath,
					FileUploadUtil.WEBAPPS_PREFIX);
		} else if (StringUtils.startsWith(uploadPath,
				FileUploadUtil.ABSOLUTE_PREFIX)) {
			_relative_path = "";
		} else {
			throw new IOException("---->uploadPath 前缀不能适用于LocalFileUpload ");
		}
		// 否则，绝对路径直接返回
		return FilenameUtils.normalize(_webappPath + File.separator
				+ _relative_path + File.separator);

	}

	@Override
	public InputStream readUploadedFile(String filePath )
			throws IOException {
		File file = new File(FilenameUtils.normalize(filePath));
		return FileUtils.openInputStream(file);

	}

}
