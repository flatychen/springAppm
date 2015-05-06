package cn.appm.services.app.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;

import cn.appm.base.page.PageBean;
import cn.appm.base.wrapper.CommonDataWrapper;
import cn.appm.persistance.app.AppFileDao;
import cn.appm.persistance.app.AppTypeDao;
import cn.appm.persistance.app.AppVersionDao;
import cn.appm.persistance.app.ProjectDao;
import cn.appm.services.app.model.AppFile;
import cn.appm.services.app.model.AppType;
import cn.appm.services.app.model.AppVersion;
import cn.appm.services.app.vo.QueryAppVersion;
import cn.appm.utils.web.file.UploadResult;
import cn.appm.web.WebConstants;

/**
 * 工程业务
 * 
 * @author flatychen
 * @date 2014-6-19
 * @version
 */
@Service("appVersionService")
public class AppVersionService {

	@Resource
	private AppVersionDao appVersionDao;

	@Resource
	private ProjectDao projectDao;

	@Resource
	private AppTypeDao appTypeDao;

	@Resource
	private AppFileDao AppFileDao;

	/**
	 * 查询工程
	 * 
	 * @author flatychen
	 * @date 2014-6-19
	 * @param query
	 * @return
	 * @version
	 */
	public Map<String, Object> queryApp(QueryAppVersion query) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		PageBean<AppVersion> pagebean = PageBean.genPageBean(appVersionDao.findAllAppVersionByPageCount(query), query,
				appVersionDao.findAllAppVersionByPage(query));
		map.put(WebConstants.PAGEBEANNAME, pagebean);
		return map;

	}

	public CommonDataWrapper delApp(int id,String webPath) {
		Validate.notNull(id, "---->id不可为空！");
		CommonDataWrapper json = new CommonDataWrapper();
		AppVersion appVersion = appVersionDao.getApp(id);
		if (appVersion != null) {
			AppFile appFile = AppFileDao.getAppFile(appVersion.getAppFile());
			FileUtils.deleteQuietly(new File(FilenameUtils.normalize(webPath +appFile.getAppPath()+ appFile.getAppSaveName())));
			AppFileDao.delAppFile(appFile.getId());
			AppFileDao.delOtherApp();
			json.setSuccess(appVersionDao.delApp(id));
		}
		return json;
	}

	public CommonDataWrapper addApp(AppVersion a) {
		CommonDataWrapper json = new CommonDataWrapper();
		a.setProjectName(projectDao.getProject(a.getProjectId()).getProjectName());
		json.setSuccess(appVersionDao.insertApp(a));
		return json;
	}

	public CommonDataWrapper editApp(AppVersion a) {
		Validate.notNull(a.getId(), "---->id不可为空！");
		Validate.notNull(a.getUpdateDate(), "---->修改App修改时间不可为空！");
		CommonDataWrapper json = new CommonDataWrapper();
		json.setSuccess(appVersionDao.updateApp(a));
		return json;
	}

	public AppVersion getApp(Integer id) {
		Validate.notNull(id, "---->id不可为空！");
		AppVersion app = appVersionDao.getApp(id);
		app.setApp(AppFileDao.getAppFile(app.getAppFile()));
		return app;
	}

	public AppType getAppTypeByVersionId(Integer id) {
		Validate.notNull(id, "---->id不可为空！");
		AppVersion version = this.getApp(id);
		return this.getAppTypeById(version.getAppType());
	}

	public AppType getAppTypeById(Integer id) {
		Validate.notNull(id, "---->id不可为空！");
		return appTypeDao.getAppType(id);
	}

	public CommonDataWrapper addAppFile(Long lastAppFileId, AppFile appFile, UploadResult ur) {
		CommonDataWrapper json = new CommonDataWrapper();
		if (ur.getSuccess()) {
			Long id = new Date().getTime();
			appFile.setId(id);
			AppFileDao.insertAppFile(appFile);
			if (lastAppFileId != null) {
				AppFileDao.delAppFile(lastAppFileId);
			}
			Map<String, Object> map = new HashMap<String, Object>(2);
			map.put("url", "/f/d/"+id);
			map.put("appFileId", id);
			json.setData(map);
		}
		json.setSuccess(ur.getSuccess());
		json.setMessage(ur.getMessage());
		return json;
	}

	public AppFile getAppFile(Long id) {
		Validate.notNull(id, "---->id不可为空！");
		return AppFileDao.getAppFile(id);
	}
}
