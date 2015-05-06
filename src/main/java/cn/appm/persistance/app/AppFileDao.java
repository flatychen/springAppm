package cn.appm.persistance.app;

import cn.appm.services.app.model.AppFile;

public interface AppFileDao {

	
	public Integer insertAppFile(AppFile appFile);
	
	public Integer delAppFile(Long id);

	public AppFile getAppFile(Long id);

	public boolean updateAppFile(AppFile appFile);
	
	public boolean delOtherApp();

}
