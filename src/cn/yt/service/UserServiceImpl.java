package cn.yt.service;

import cn.yt.beans.User;
import cn.yt.dao.IUserDao;

/**
 * 
 * @author TC
 * service实现类
 */
public class UserServiceImpl implements IUserService {
	private IUserDao dao;
	
	public void setDao(IUserDao dao) {
		this.dao = dao;
	}

	public void addUser(User user) {
		dao.insertUser(user);
	}

	public User findUserByName(String username) {
		return dao.selectUserByName(username);
	}

	public void modifyAvatarByUsername(String avatar,String username) {
		dao.updateAvatarByUsenname(avatar,username);
	}
	
}
