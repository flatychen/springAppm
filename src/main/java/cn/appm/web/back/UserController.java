package cn.appm.web.back;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.appm.base.BaseQueryObject;
import cn.appm.services.app.model.AppVersion;
import cn.appm.services.app.service.AppService;
import cn.appm.services.app.service.AppVersionService;
import cn.appm.services.app.service.ProjectService;
import cn.appm.services.app.vo.QueryAppVersion;
import cn.appm.web.BaseController;

/**
 * 前台
 * @author flatychen
 * @date 2014-4-17
 */
@Controller
public class UserController extends BaseController{
	
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(BaseQueryObject query,Model model){
		return "/views/front/index";
	}
	
	
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String show(@PathVariable Integer id,Model model){
		return "/views/front/show";
	}
	
	

	
}
