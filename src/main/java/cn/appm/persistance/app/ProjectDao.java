package cn.appm.persistance.app;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appm.base.BaseQueryObject;
import cn.appm.base.page.Pageable;
import cn.appm.services.app.model.AppType;
import cn.appm.services.app.model.AppVersion;
import cn.appm.services.app.model.Project;

/**
 * app工程
 * @author flatychen
 * @date 2014-6-17
 * @version 
 */
public interface ProjectDao {
	
	
	/**
	 * 分页查找所有工程
	 * @author flatychen
	 * @date 2014-6-17
	 * @param pageable
	 * @return
	 * @version 
	 */
	public List<Project> findProjectByPage(BaseQueryObject query);
	
	public int findProjectByPageCount(BaseQueryObject query);
	
	/**
	 * 添加一个工程
	 * @author flatychen
	 * @date 2014-6-17
	 * @param project
	 * @return
	 * @version 
	 */
	public boolean insertProject(Project project);
	
	
	
	/**
	 * 删除一个工程
	 * @author flatychen
	 * @date 2014-6-17
	 * @param project
	 * @return
	 * @version 
	 */
	public boolean delProject(Integer id);
	
	
	/**
	 * 删除一个工程所有APP
	 * @author flatychen
	 * @date 2014-6-17
	 * @param project
	 * @return
	 * @version 
	 */
	public boolean delProjectApp(Integer id);
	
	
	/**
	 * 修改一个工程
	 * @author flatychen
	 * @date 2014-6-17
	 * @param project
	 * @return
	 * @version 
	 */
	public boolean updateProject(Project project);
	
	
	/**
	 * 查看一个工程
	 * @author flatychen
	 * @date 2014-6-17
	 * @param project
	 * @return
	 * @version 
	 */
	public Project getProject(Integer id);
	
	/**
	 * 得到工程最新版APP
	 * @author flatychen
	 * @date 2014-6-27
	 * @param id
	 * @return
	 * @version 
	 */
	public AppVersion getLatestAppVersion(Integer projectId);
	
}
