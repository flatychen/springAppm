package cn.appm.persistance.user;

import cn.appm.services.user.model.User;

public interface UserDao {
	
	public User getUserByUserName(String userName);

}
