package cn.appm.services.app.vo;

import javax.validation.constraints.NotNull;

import cn.appm.base.BaseQueryObject;

public class QueryAppVersion extends BaseQueryObject {
	
	@NotNull
	private Integer projectId;
	
	@NotNull
	private Integer appType;

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	
	

}
