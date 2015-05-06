package cn.appm.services.app.bo;

import cn.appm.services.app.model.AppFile;
import cn.appm.services.app.model.AppType;
import cn.appm.services.app.model.AppVersion;
import cn.appm.services.app.model.Project;

public class ProjectApp{
	
	private Project project;
	
	private AppVersion app;
	
	private AppType appType;
	
	
	private AppFile appFile;


	public AppFile getAppFile() {
		return appFile;
	}

	public void setAppFile(AppFile appFile) {
		this.appFile = appFile;
	}

	public ProjectApp(Project project, AppVersion app, AppType appType,AppFile appFile) {
		super();
		this.project = project;
		this.app = app;
		this.appType = appType;
		this.appFile = appFile;
	}

	public AppType getAppType() {
		return appType;
	}

	public void setAppType(AppType appType) {
		this.appType = appType;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public AppVersion getApp() {
		return app;
	}

	public void setApp(AppVersion app) {
		this.app = app;
	}
	
	

}
