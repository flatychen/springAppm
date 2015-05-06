package cn.appm.web.back;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appm.base.wrapper.CommonDataWrapper;
import cn.appm.services.app.model.AppVersion;

@Controller
@RequestMapping("/b/versions/ios")
public class IosVersionsController extends AppVersionsController {

	@RequestMapping("/new")
	public String news(Integer appType,Model model){
		model.addAttribute("appType", appVersionService.getAppTypeById(appType));
		model.addAttribute("projects", projectService.queryAllProject());
		return "/views/back/versions/ios/new";
	}
	
	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	@ResponseBody
	public CommonDataWrapper create(AppVersion appversion){
		return  appVersionService.addApp(appversion);
	}
	
	
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable Integer id,Model model){
		model.addAttribute("appType", appVersionService.getAppTypeByVersionId(id));
		model.addAttribute("version", appVersionService.getApp(id));
		return "/views/back/versions/ios/edit";
	}
	
	@RequestMapping(value = "/{id}/update" , method = RequestMethod.POST)
	@ResponseBody
	public CommonDataWrapper update(@PathVariable Integer id,AppVersion appversion){
		appversion.setId(id);
		return appVersionService.editApp(appversion);
	}
	
	
	

}
