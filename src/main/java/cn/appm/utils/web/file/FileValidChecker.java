package cn.appm.utils.web.file;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件合法检查
 * 
 * @author flatychen
 * @date 2014-4-30
 */
public class FileValidChecker {

	private final static Logger log = LoggerFactory
			.getLogger(FileValidChecker.class);

	public final static int MB = 1024 * 1024;

	public final static int UNLIMIT = -1;

	public final static String checkFileSize_MESSAGE = "文件大小不符！";
	public final static String checkContentType_MESSAGE = "文件类型不符！";

	private long allowFileSize = UNLIMIT;

	private String allowTypes = null;

	public void setAllowFileSizeOfMB(long allowFileSizeOfMB) {
		this.allowFileSize = allowFileSizeOfMB * MB;
	}

	public void setAllowFileSize(long allowFileSize) {
		this.allowFileSize = allowFileSize;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	/**
	 * 检查文件类型匹配
	 * 
	 * @author flatychen
	 * @date 2014-4-18
	 * @param mfile
	 * @return
	 */
	protected final boolean checkContentType(UploadInfo fileInfo) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format("---->>>> file mime: {0}",
					fileInfo.getFileContentType()));
		}
		if (StringUtils.isBlank(allowTypes))
			return true;
		if (fileInfo.getFileContentType() != null) {
			String types[] = allowTypes.split(",");
			for (int i = 0; i < types.length; i++) {
				if (StringUtils.equalsIgnoreCase(fileInfo.getFileContentType(),
						types[i])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 检查文件大小匹配
	 * 
	 * @author flatychen
	 * @date 2014-4-18
	 * @param mfile
	 * @return
	 */
	protected final boolean checkFileSize(UploadInfo fileInfo) {
		if (!fileInfo.isEmpty()) {
			if (log.isInfoEnabled()) {
				log.info(MessageFormat.format("---->>>> file size: {0}KB",
						fileInfo.getFileSize() / 1024.0 ));
			}
			if (allowFileSize == FileValidChecker.UNLIMIT
					|| allowFileSize >= fileInfo.getFileSize()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
