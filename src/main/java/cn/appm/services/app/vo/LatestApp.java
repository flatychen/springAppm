package cn.appm.services.app.vo;

import cn.appm.services.app.model.Project;

public class LatestApp extends Project {
	
	private int projectId;
	
	private Integer appVersionId;
	
	public Integer getAppVersionId() {
		return appVersionId;
	}

	public void setAppVersionId(Integer appVersionId) {
		this.appVersionId = appVersionId;
	}

	private String latestVersion;

	private Integer appType;
	
	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	
	

}
