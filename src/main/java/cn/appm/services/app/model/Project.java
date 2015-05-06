package cn.appm.services.app.model;

import java.util.Date;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class Project {

	protected int id;
	
	@NotBlank
	protected String projectName;
	@NotBlank
	protected String intro;
	protected String icon;
	protected Date createDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;


	// ID
	public int getId() {
		return id;
	}

	public void setId(int value) {
		this.id = value;
	}

	// 名称 名称
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String value) {
		this.projectName = value;
	}

	// 描述 描述
	public String getIntro() {
		return intro;
	}

	public void setIntro(String value) {
		this.intro = value;
	}

	// 图标 图标
	public String getIcon() {
		return icon;
	}

	public void setIcon(String value) {
		this.icon = value;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date value) {
		this.createDate = value;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date value) {
		this.updateDate = value;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + ", intro=" + intro + ", icon=" + icon
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	

}
