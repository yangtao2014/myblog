package cn.yt.dao;

import cn.yt.beans.User;

/**
 * 
 * @author TC
 * dao层，用来增删改查
 */
public interface IUserDao {
	void insertUser(User user);
	User selectUserByName(String username);
	void updateAvatarByUsenname(String avatar,String username);
}
