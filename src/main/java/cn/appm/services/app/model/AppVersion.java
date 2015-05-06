package cn.appm.services.app.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class AppVersion  {
	
	
	protected Integer id;
	protected Integer appType;
	protected Integer projectId;
	protected String version;
	protected String projectName;
	protected String content;
	protected Long appFile;
	protected Date createDate;
	protected String iosAppend;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;
	protected String appSaveName;
	
	public String getAppSaveName() {
		return appSaveName;
	}
	public void setAppSaveName(String appSaveName) {
		this.appSaveName = appSaveName;
	}

	private AppFile app;
	
	public AppFile getApp() {
		return app;
	}
	public void setApp(AppFile app) {
		this.app = app;
	}
	public Long getAppFile() {
		return appFile;
	}
	public Integer getAppType() {
		return appType;
	}
	
	public String getContent() {
		return content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Integer getId() {
		return id;
	}

	public String getIosAppend() {
		return iosAppend;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public String getVersion() {
		return version;
	}

	public void setAppFile(Long appFile) {
		this.appFile = appFile;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIosAppend(String iosAppend) {
		this.iosAppend = iosAppend;
	}


	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	

}
