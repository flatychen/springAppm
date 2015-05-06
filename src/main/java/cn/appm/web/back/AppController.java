package cn.appm.web.back;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appm.services.app.bo.EnumAppType;
import cn.appm.services.app.service.AppService;
import cn.appm.services.app.vo.LatestApp;

@Controller
@RequestMapping("/b/apps")
public class AppController extends BackBaseController {
	
	@Resource
	private AppService appService;

	@RequestMapping("/latestApp")
	@ResponseBody
	public LatestApp latestApp(Integer projectId,EnumAppType appType,Model model){
		return  appService.getLatestApp(projectId, appType);
	}

}
