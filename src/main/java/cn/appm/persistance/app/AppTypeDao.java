package cn.appm.persistance.app;

import java.util.List;

import cn.appm.base.BaseQueryObject;
import cn.appm.services.app.bo.EnumAppType;
import cn.appm.services.app.model.AppType;
import cn.appm.services.app.model.AppVersion;
import cn.appm.services.app.vo.QueryAppVersion;

public interface AppTypeDao {
	
	/**
	 * 所有app类型
	 * @author flatychen
	 * @date 2014-6-17
	 * @return
	 * @version 
	 */
	public List<AppType> findAllAppType();
	
	/**
	 * 得到APP类型
	 * @author flatychen
	 * @date 2014-6-26
	 * @param id
	 * @return
	 * @version 
	 */
	public AppType getAppType(Integer id);
	
	
	
	
}
