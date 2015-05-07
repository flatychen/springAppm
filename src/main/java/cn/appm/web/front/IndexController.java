package cn.appm.web.front;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.appm.base.BaseQueryObject;
import cn.appm.services.app.service.AppService;
import cn.appm.services.app.service.AppVersionService;
import cn.appm.services.app.service.ProjectService;
import cn.appm.services.app.vo.QueryAppVersion;
import cn.appm.web.BaseController;

/**
 * 前台
 * 
 * @author flatychen
 * @date 2014-4-17
 */
@Controller
@RequestMapping("/f")
public class IndexController extends BaseController {

	@Resource
	private ProjectService projectService;

	@Resource
	private AppService appService;

	@Resource
	private AppVersionService appVersionService;

	/**
	 * 前台首页
	 * 
	 * @author flatychen
	 * @date 2014-6-30
	 * @param query
	 * @param model
	 * @return
	 * @version
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(BaseQueryObject query, Model model) {
		model.addAllAttributes(projectService.queryFrontProject(query));
		return "/views/front/index";
	}

	/**
	 * 更多版本
	 * 
	 * @author flatychen
	 * @date 2014-6-30
	 * @param query
	 * @param model
	 * @return
	 * @version
	 */
	@RequestMapping(value = "/more", method = RequestMethod.GET)
	public String more(QueryAppVersion query, Model model) {
		model.addAttribute("appType",
				appVersionService.getAppTypeById(query.getAppType()));
		model.addAttribute("project",
				projectService.getProject(query.getProjectId()));
		model.addAllAttributes(appVersionService.queryApp(query));
		return "/views/front/more";
	}

	/**
	 * 详情页面
	 * 
	 * @author flatychen
	 * @date 2014-6-30
	 * @param id
	 * @param model
	 * @return
	 * @version
	 */
	@RequestMapping(value = "{id}/show", method = RequestMethod.GET)
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("projectApp", appService.getProjectApp(id));
		return "/views/front/show";
	}

}
