package cn.appm.utils.web.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;

import cn.appm.utils.web.file.upload.FileRepo;
import cn.appm.utils.web.file.upload.FileUploadUtil;

/**
 * 
 * <b>依赖于spring ServletContextAware</b>
 * <br/>
 * <br/>
 * 不要使用此类，使用 {@link FileUpload} 接口<br>
 *
 * <p>
 * {@link AbstractFileUpload#uploadPath} 属性必须存在
 * </p>
 * 
 * @author flatychen
 * @date 2014-4-21
 */
@WebListener
public class AbstractFileUpload extends FileValidChecker implements FileUpload,ServletContextAware{
	

	private Logger log = LoggerFactory.getLogger(AbstractFileUpload.class);
	
	public AbstractFileUpload() {
		super();
	}
	
	protected static String webRootPath = null;
	
	/**
	 * 文件仓库，实际管理文件，如本地，FTP，etc.. LocalFileUpload.java
	 */
	protected FileRepo fileRepo;

	public void setFileRepo(FileRepo fileRepo) {
		this.fileRepo = fileRepo;
	}

	/**
	 * 上传文件路径
	 * <p>
	 * 绝路路径使用 absolute:为前缀 如，absolute:C:/temp/
	 * </p>
	 * <p>
	 * 相对路径使用 realitive:为前缀 如，relative: /temp/;相对是是在工程目录下面;
	 * </p>
	 * <p>
	 * 同一web容器使用 webapps:为前缀 如，webapps: /temp/;表示为在同一web容器下的另一工程，为方便布暑;
	 * </p>
	 * <p>
	 * ftp..hadoop..等直接写地址，由其实现解析
	 * </p>
	 */
	protected String uploadPath;

	/**
	 * 解析后的真实路径,即 <磁盘路径 + uploadPath> ，不含文件名
	 */
	protected String realPath;

	

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	/**
	 * 图片 web访问前缀,主要用于文件服务器,故与uploadPath不一定相同
	 * <p>
	 * 相对路径：等同于uploadPath 变量值
	 * </p>
	 * <p>
	 * 绝对路径：视文件服务器
	 * </p>
	 */
	protected String webPrefix;

	public void setWebPrefix(String webPrefix) {
		this.webPrefix = webPrefix;
	}

	
	
	/**
	 * 上传文件实现
	 * 
	 * @author flatychen
	 * @date 2014-4-30
	 * @param file
	 * @return 上传文件结果 UploadResult
	 * @see UploadResult
	 */
	public final UploadResult uploadFile(UploadInfo file) {
		// 检查文件上传是否合法,主要为文件大小与文件类型
		UploadResult upt = new UploadResult();
		if (!checkFileSize(file)) {
			upt.setSuccess(false);
			upt.setMessage(checkFileSize_MESSAGE);
			if (log.isWarnEnabled()) {
				log.warn(MessageFormat.format("---->>>> upload fail: {0}",
						checkFileSize_MESSAGE));
			}
			return upt;
		}
		if (!checkContentType(file)) {
			upt.setSuccess(false);
			upt.setMessage(checkContentType_MESSAGE);
			if (log.isWarnEnabled()) {
				log.warn(MessageFormat.format("---->>>> upload fail: {0}",
						checkContentType_MESSAGE));
			}
			return upt;
		}


		// 初始化上传结果基础信息
		UploadResult uploadResult = this.initBasicUploadResult(file);
//		
		
		// 解析真实上传路径
		try {
			this.checkRealPath();
		} catch (IOException e) {
			upt.setSuccess(false);
			upt.setMessage(e.getMessage());
			if (log.isWarnEnabled()) {
				log.warn(MessageFormat.format("---->>>> upload fail: {0}",
						checkContentType_MESSAGE));
			}
			e.printStackTrace();
			return upt;
		}

		// 开始上传
		try {
			// 根据解析的实际路径 + 文件路径
			String _fileAbsolutePath = this.realPath + File.separator
					+ uploadResult.getFileName() + "."
					+ uploadResult.getFileExtName();
			// 上传
			this.writeFile(file.getInputStream(), _fileAbsolutePath);
		} catch (IOException e) {
			uploadResult.setSuccess(false);
			uploadResult.setMessage(e.getMessage());
			if (log.isWarnEnabled()) {
				log.warn(MessageFormat.format("---->>>> upload fail: {0}",
						e.getMessage()));
			}
			e.printStackTrace();
		}
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format("---->>>> upload success: {0}",
					uploadResult.getUploadFileName()));
		}
		uploadResult.setSuccess(true);
		uploadResult.setWebLinkPath(FilenameUtils.normalize(webPrefix, true));
		return uploadResult;

	}

	
	protected void writeFile(InputStream inputStream, String fileAbsolutePath) throws IOException{
		this.fileRepo.writeFile(inputStream, fileAbsolutePath);
	}
	
	/**
	 * 上传多个文件
	 * 
	 * @author flatychen
	 * @date 2014-4-30
	 * @param files
	 * @return
	 */
	public final List<UploadResult> uploadFiles(UploadInfo files[]) {
		List<UploadResult> uploadResults = new ArrayList<UploadResult>();
		for (int i = 0; i < files.length; i++) {
			UploadResult uploadResult = uploadFile(files[i]);
			if (uploadResult != null) {
				uploadResults.add(uploadResult);
			}
		}
		return uploadResults;
	}

	@Override
	public InputStream downloadFile(String webRootPath, String saveFilePath) {
		try {
			this.checkRealPath();
			// 根据解析的实际路径 + 文件路径
						String _fileAbsolutePath = this.realPath + File.separator
								+ saveFilePath;
			return fileRepo.readUploadedFile(_fileAbsolutePath);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 初始化文件上传结果
	 * 
	 * @author flatychen
	 * @date 2014-4-30
	 * @param file
	 * @return
	 */
	private final UploadResult initBasicUploadResult(UploadInfo file) {
		UploadResult ur = new UploadResult(file.getFormName(),
				file.getOriginalFileName());
		ur.setFileName(FileUploadUtil.genRandomFileName(""));
		return ur;
	}

	/**
	 * 检查真实路径是否解析
	 * @param webRootPath
	 * @throws IOException
	 */
	private void checkRealPath() throws IOException {
		if (StringUtils.isBlank(this.realPath)) {
			this.realPath = this.fileRepo.resolveUploadPath(webRootPath,
					uploadPath);
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		webRootPath = servletContext.getRealPath("");
	}


}
