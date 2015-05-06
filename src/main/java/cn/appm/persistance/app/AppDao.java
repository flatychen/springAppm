package cn.appm.persistance.app;

import org.apache.ibatis.annotations.Param;

import cn.appm.services.app.bo.EnumAppType;
import cn.appm.services.app.vo.LatestApp;
public interface AppDao {
	/**
	 * 
	 *  得到最新版本APP
	 *
	 * @author flatychen
	 * @date 2014-6-27
	 * @param projectId
	 * @param appType
	 * @return
	 * @version 
	 */
	public LatestApp getLatestApp(@Param("projectId") Integer projectId ,@Param("appType") EnumAppType appType);
}
