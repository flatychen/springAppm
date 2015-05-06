package cn.appm.web.back;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appm.base.BaseQueryObject;
import cn.appm.base.wrapper.CommonDataWrapper;
import cn.appm.services.app.model.Project;

@Controller
@RequestMapping("/b/projects")
public class ProjectsController extends BackBaseController {
	

	@RequestMapping("/query")
	public String query(BaseQueryObject query,Model model){
		model.addAllAttributes(projectService.queryProject(query));
		return "/views/back/projects/query";
	}
	
	@RequestMapping("/new")
	public String news(){
		return "/views/back/projects/new";
	}
	
	@RequestMapping(value = "/create"  , method = RequestMethod.POST)
	@ResponseBody
	public CommonDataWrapper create(@Valid Project p){
		return projectService.addProject(p);
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable Integer id,Model model){
		model.addAttribute("project", projectService.getProject(id));
		return "/views/back/projects/edit";
	}
	
	@RequestMapping(value = "/{id}/update" , method = RequestMethod.POST)
	@ResponseBody
	public CommonDataWrapper update(@PathVariable Integer id ,@Valid Project p){
		p.setId(id);
		return projectService.editProject(p);
	}
	
	@RequestMapping("/{id}/del")
	@ResponseBody
	public CommonDataWrapper del(@PathVariable Integer id){
		return projectService.delProject(id,super.getWebRootPath());
	}
	
	
}
