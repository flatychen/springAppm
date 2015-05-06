package cn.appm.base.wrapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 常用数据包装器
 * 
 * @author flatychen
 *
 */
public class CommonDataWrapper  extends BaseDataWrapper{


	/**
	 * 调用返回数据
	 */
	private Map<String,Object> data;


	/**
	 * 初始化数据包装器大小
	 * @author flatychen
	 * @date 2014-7-11
	 * @param mapSize
	 * @version 
	 */
	public CommonDataWrapper buildWrapper(int mapSize){
		if(this.data != null){
			throw new IllegalArgumentException("----> map 重复初始化! ");
		}
		this.data = new HashMap<String, Object>(mapSize);
		return this;
	}

	public Map<String, Object> getData() {
		return data;
	}


	public void setData(Map<String, Object> data) {
		this.data = data;
	}


	
	
	
}
