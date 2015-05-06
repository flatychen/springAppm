package cn.appm.persistance.user;

import java.util.List;

import cn.appm.services.user.model.User;
import cn.appm.services.user.model.UserPrivilege;

public interface UserPrivilegeDao {
	
	public User getUserRoleById(Integer id);
	
	public List<UserPrivilege> findUnitPrivilege(Integer uid);
	
	public List<UserPrivilege> findUserPrivileges(Integer uid);

}
