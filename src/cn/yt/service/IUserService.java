package cn.yt.service;

import cn.yt.beans.User;

/**
 * 
 * @author TC
 * service层接口，用来屏蔽dao层，提供服务
 */
public interface IUserService {
	void addUser(User user);
	User findUserByName(String username);
	void modifyAvatarByUsername(String avatar,String username);
}
