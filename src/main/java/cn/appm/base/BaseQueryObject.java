package cn.appm.base;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import cn.appm.base.page.Pageable;


/**
 * 复杂查询基类，不可添加属性，
 * 继承之
 * @author flatychen
 *
 */
public class BaseQueryObject extends Pageable {
	
	/**
	 * 日期开始
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date beginDate;
	
	/**
	 * 日期结束
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date endDate;
	
	/**
	 * 关键字
	 */
	private String key;
	


	public String getKey() {
		return key;
	}

	public BaseQueryObject setKey(String key) {
		this.key = key;
		return this;
	}


	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String[] getKeyArray(){
		return StringUtils.split(key);
	}

}
