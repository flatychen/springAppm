package cn.appm.persistance.app;

import java.util.List;

import cn.appm.base.BaseQueryObject;
import cn.appm.services.app.bo.EnumAppType;
import cn.appm.services.app.model.AppType;
import cn.appm.services.app.model.AppVersion;
import cn.appm.services.app.vo.QueryAppVersion;

public interface AppVersionDao {
	
	
	/**
	 * 分页查找某平台的最新APP
	 * @author flatychen
	 * @date 2014-6-19
	 * @param type app类型
	 * @return
	 * @version 
	 */
	public List<AppVersion> findLatestAppVersionByPage(EnumAppType type);
	
	
	
	
	/**
	 * 分页查找所有版本
	 * @author flatychen
	 * @date 2014-6-17
	 * @param page
	 * @return
	 * @version 
	 */
	public List<AppVersion> findAllAppVersionByPage(QueryAppVersion query);
	
	
	
	
	public int findAllAppVersionByPageCount(QueryAppVersion query);
	
	/**
	 * 添加一个app
	 * @author flatychen
	 * @date 2014-6-17
	 * @param project
	 * @return
	 * @version 
	 */
	public boolean insertApp(AppVersion app);
	
	
	
	/**
	 * 删除一个app
	 * @author flatychen
	 * @date 2014-6-17
	 * @param project
	 * @return
	 * @version 
	 */
	public boolean delApp(Integer id);
	
	
	/**
	 * 修改一个app
	 * @author flatychen
	 * @date 2014-6-17
	 * @param project
	 * @return
	 * @version 
	 */
	public boolean updateApp(AppVersion app);
	
	

	/**
	 * 得到一个app
	 * @author flatychen
	 * @date 2014-6-17
	 * @param project
	 * @return
	 * @version 
	 */
	public AppVersion getApp(int id);
	
	
}
