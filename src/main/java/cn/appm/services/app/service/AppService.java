package cn.appm.services.app.service;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;

import cn.appm.persistance.app.AppDao;
import cn.appm.persistance.app.AppFileDao;
import cn.appm.persistance.app.AppTypeDao;
import cn.appm.persistance.app.AppVersionDao;
import cn.appm.persistance.app.ProjectDao;
import cn.appm.services.app.bo.EnumAppType;
import cn.appm.services.app.bo.ProjectApp;
import cn.appm.services.app.model.AppFile;
import cn.appm.services.app.model.AppType;
import cn.appm.services.app.model.AppVersion;
import cn.appm.services.app.model.Project;
import cn.appm.services.app.vo.LatestApp;

/**
 * 
 * @author flatychen
 * @date 2014-6-19
 * @version
 */
@Service("appService")
public class AppService {

	@Resource
	private AppDao appDao;
	
	@Resource
	private AppVersionDao appVersionDao;
	
	@Resource
	private ProjectDao projectDao;
	
	@Resource
	private AppFileDao appFileDao;
	
	
	@Resource
	private AppTypeDao appTypeDao;
	
	public LatestApp getLatestApp(Integer projectId , EnumAppType appType){
		Validate.notNull(projectId,"---->projectId 不能为空");
		Validate.notNull(appType,"---->appType 类型不能为空");
		LatestApp app = this.appDao.getLatestApp(projectId, appType);
		return app == null ? new LatestApp():app;
	}

	
	public ProjectApp getProjectApp(Integer id){
		AppVersion version = appVersionDao.getApp(id);
		Project project = projectDao.getProject(version.getProjectId());
		AppType apptype = appTypeDao.getAppType(version.getAppType());
		AppFile appFile = appFileDao.getAppFile(version.getAppFile());
		ProjectApp projectApp = new ProjectApp(project, version, apptype,appFile);
		projectApp.setApp(appVersionDao.getApp(id));
		return projectApp;
	}
	
	
}
