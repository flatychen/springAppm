package cn.appm.services.app.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;

import cn.appm.base.BaseConstants;
import cn.appm.base.BaseQueryObject;
import cn.appm.base.page.PageBean;
import cn.appm.base.page.Pageable;
import cn.appm.base.wrapper.CommonDataWrapper;
import cn.appm.persistance.app.AppTypeDao;
import cn.appm.persistance.app.AppVersionDao;
import cn.appm.persistance.app.ProjectDao;
import cn.appm.services.app.bo.EnumAppType;
import cn.appm.services.app.model.AppType;
import cn.appm.services.app.model.AppVersion;
import cn.appm.services.app.model.Project;
import cn.appm.services.app.vo.QueryAppVersion;
import cn.appm.web.WebConstants;

/**
 * 工程业务
 * 
 * @author flatychen
 * @date 2014-6-19
 * @version
 */
@Service("projectService")
public class ProjectService extends AppVersionService{

	
	@Resource
	private AppVersionDao appVersionDao;
	
	@Resource
	private ProjectDao projectDao;


	@Resource
	private AppTypeDao appTypeDao;

	
	public List<AppType> queryAllAppTypes(){
		return appTypeDao.findAllAppType();
	}
	
	public List<Project> queryAllProject(){
		BaseQueryObject query = new BaseQueryObject();
		query.setPageNo(1);
		query.setPageSize(Pageable.QUERY_ALL);
		return projectDao.findProjectByPage(query);
	}
	
	/**
	 * 前台查询工程
	 * 
	 * @author flatychen
	 * @date 2014-6-19
	 * @param query
	 * @return
	 * @version
	 */
	public Map<String, Object> queryFrontProject(BaseQueryObject query) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		PageBean<Project> pagebean = PageBean.genPageBean(projectDao.findProjectByPageCount(query), query,
				projectDao.findProjectByPage(query));

		List<AppType> appTypes = appTypeDao.findAllAppType();
		map.put("allAppType", appTypeDao.findAllAppType());
		map.put(WebConstants.PAGEBEANNAME, pagebean);
		return map;

	}

	/**
	 * 查询工程
	 * 
	 * @author flatychen
	 * @date 2014-6-19
	 * @param query
	 * @return
	 * @version
	 */
	public Map<String, Object> queryProject(BaseQueryObject query) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		PageBean<Project> pagebean = PageBean.genPageBean(projectDao.findProjectByPageCount(query), query,
				projectDao.findProjectByPage(query));
		List<AppType> appTypes = appTypeDao.findAllAppType();
		map.put(WebConstants.PAGEBEANNAME, pagebean);
		map.put("appTypes", appTypes);

		return map;

	}

	public CommonDataWrapper delProject(int id,String webPath) {
		Validate.notNull(id, "---->id不可为空！");
		CommonDataWrapper json = new CommonDataWrapper();
		QueryAppVersion query = new QueryAppVersion();
		query.setProjectId(id);
		int count = appVersionDao.findAllAppVersionByPageCount(query);
		query.setPageSize(count);
		List<AppVersion> ProjectappVersion = appVersionDao.findAllAppVersionByPage(query);
		for (AppVersion appVersion : ProjectappVersion) {
			super.delApp(appVersion.getId(), webPath);
		}
		
		json.setSuccess(projectDao.delProject(id));
		return json;
	}

	public CommonDataWrapper addProject(Project p) {
		CommonDataWrapper json = new CommonDataWrapper();
		json.setSuccess(projectDao.insertProject(p));
		return json;
	}

	public CommonDataWrapper editProject(Project p) {
		Validate.notNull(p.getUpdateDate(), "---->修改工程修改时间不可为空！");
		CommonDataWrapper json = new CommonDataWrapper();
		json.setSuccess(projectDao.updateProject(p));
		return json;
	}
	
	public Project getProject(Integer id) {
		Validate.notNull(id, "---->id不可为空！");
		return projectDao.getProject(id);
	}
	

}
