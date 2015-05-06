package cn.appm.web.back;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.appm.base.wrapper.CommonDataWrapper;
import cn.appm.services.app.model.AppVersion;
import cn.appm.services.app.service.AppVersionService;
import cn.appm.services.app.vo.QueryAppVersion;
import cn.appm.utils.web.file.SpringMultipartFileInfo;
import cn.appm.utils.web.file.UploadInfo;
import cn.appm.utils.web.file.UploadResult;
import cn.appm.web.WebConstants;

@Controller
@RequestMapping("/b/versions/android")
public class AndroidVersionsController extends AppVersionsController {
	

	@RequestMapping("/query")
	public String query(Integer projectId,QueryAppVersion query,Model model){
		query.setProjectId(projectId);
		model.addAllAttributes(appVersionService.queryApp(query));
		model.addAttribute("appType", appVersionService.getAppTypeById(query.getAppType()));
		return "/views/back/versions/android/query";
	}
	
	@RequestMapping("/new")
	public String news(Integer appType,Model model){
		model.addAttribute("appType", appVersionService.getAppTypeById(appType));
		model.addAttribute("projects", projectService.queryAllProject());
		return "/views/back/versions/android/new";
	}
	
	@RequestMapping(value = "/create"  , method = RequestMethod.POST)
	@ResponseBody
	public CommonDataWrapper create(AppVersion appversion){
		return  appVersionService.addApp(appversion);
	}
	
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable Integer id,Model model){
		model.addAttribute("appType", appVersionService.getAppTypeByVersionId(id));
		model.addAttribute("version", appVersionService.getApp(id));
		return "/views/back/versions/android/edit";
	}
	
	@RequestMapping(value = "/{id}/update" , method = RequestMethod.POST)
	@ResponseBody
	public CommonDataWrapper update(@PathVariable Integer id,AppVersion appversion){
		appversion.setId(id);
		return appVersionService.editApp(appversion);
	}
	
	
	

}
