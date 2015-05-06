package cn.appm.services.app.model;

import org.apache.commons.io.FilenameUtils;


public class AppFile {

	protected Long id;
	protected String uploadName;
	protected String appPath;
	protected String appSaveName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUploadName() {
		return uploadName;
	}
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	public String getAppPath() {
		return appPath;
	}
	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	public String getAppSaveName() {
		return appSaveName;
	}
	public void setAppSaveName(String appSaveName) {
		this.appSaveName = appSaveName;
	}

	public String getUploadFileName(){
		return FilenameUtils.getName(this.uploadName);
	}
}
