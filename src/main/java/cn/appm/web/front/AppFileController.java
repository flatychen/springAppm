package cn.appm.web.front;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.appm.services.app.model.AppFile;
import cn.appm.services.app.service.AppService;
import cn.appm.services.app.service.AppVersionService;
import cn.appm.services.app.service.ProjectService;
import cn.appm.web.BaseController;

/**
 * 下载APP
 * 
 * @author flatychen
 * @date 2014-4-17
 */
@Controller
@RequestMapping("/f/d/")
public class AppFileController extends BaseController {

	@Resource(name = "appUpload")
	private cn.appm.utils.web.file.FileUpload appUpload;

	@Resource
	private AppVersionService appVersionService;

	@RequestMapping("/{id}")
	public void download(@PathVariable Long id, HttpServletResponse response) throws IOException {
		AppFile appFile = appVersionService.getAppFile(id);
		response.setContentType("application/vnd.android.package-archive");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-disposition","attachment;filename=" + appFile.getUploadFileName());
		IOUtils.copy(appUpload.downloadFile(super.getWebRootPath(), FilenameUtils.normalize(appFile.getAppSaveName())), response.getOutputStream());
	}

	
}
