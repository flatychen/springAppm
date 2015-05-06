package cn.appm.web.back;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;

import cn.appm.services.app.model.AppType;
import cn.appm.services.app.service.ProjectService;
import cn.appm.web.BaseController;

public class BackBaseController extends BaseController {
	
	@Resource
	protected ProjectService projectService;

	@ModelAttribute("appTypes")
	private List<AppType> allAppTypes(){
		return projectService.queryAllAppTypes();
	}
	
}
