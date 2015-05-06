package cn.appm.web.back;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.appm.base.wrapper.CommonDataWrapper;
import cn.appm.services.app.model.AppFile;
import cn.appm.services.app.service.AppVersionService;
import cn.appm.services.app.vo.QueryAppVersion;
import cn.appm.utils.web.file.SpringMultipartFileInfo;
import cn.appm.utils.web.file.UploadInfo;
import cn.appm.utils.web.file.UploadResult;
import cn.appm.web.WebConstants;

@Controller
@RequestMapping("/b/versions")
public class AppVersionsController extends BackBaseController {
	
	@Resource(name="imageUpload")
	protected cn.appm.utils.web.file.FileUpload FileUpload;
	
	@Resource(name="appUpload")
	protected cn.appm.utils.web.file.FileUpload appUpload;
	
	@Resource
	protected AppVersionService appVersionService;

	@RequestMapping("/query")
	public String query(Integer projectId,QueryAppVersion query,Model model){
		query.setProjectId(projectId);
		model.addAllAttributes(appVersionService.queryApp(query));
		model.addAttribute("appType", appVersionService.getAppTypeById(query.getAppType()));
		return "/views/back/versions/query";
	}
	
	@RequestMapping("/uploadImg")
	@ResponseBody
	public Map<String,Object> uploadImg(@RequestParam("imgFile") MultipartFile file){
		UploadInfo fileInfo = new SpringMultipartFileInfo(file);
		UploadResult ur = FileUpload.uploadFile(fileInfo);
		Map<String,Object> map = new HashMap<String, Object>(2);
		if(ur.getSuccess()){
			map.put("error", 0);
			map.put("url",super.getRequestAttribute(WebConstants.WEBAPP_PATH)+ur.getWebLinkPath()+ur.getFileNameWithExtForWeb());
		}else{
			map.put("error", 1);
			map.put("message", ur.getMessage());
		}
		return map;
	}
	
	@RequestMapping("/uploadApp")
	@ResponseBody
	public CommonDataWrapper uploadApp(@RequestParam("appFile") Long lastAppFileId,@RequestParam("file") MultipartFile file){
		UploadInfo fileInfo = new SpringMultipartFileInfo(file);
		UploadResult ur = appUpload.uploadFile(fileInfo);
		AppFile appFile = new AppFile();
		appFile.setAppSaveName(ur.getFileNameWithExtForWeb());
		appFile.setUploadName(ur.getUploadFileName());
		return appVersionService.addAppFile(lastAppFileId,appFile,ur);
	}
	
	@RequestMapping("/{id}/del")
	@ResponseBody
	public CommonDataWrapper del(@PathVariable Integer id){
		return appVersionService.delApp(id,super.getWebRootPath());
	}
	
	
	

}
