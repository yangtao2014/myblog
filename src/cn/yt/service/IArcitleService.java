package cn.yt.service;

import java.util.List;

import cn.yt.beans.Arcitle;

/**
 * 
 * @author TC
 * 文章service层，提供服务
 */
public interface IArcitleService {
	void addArcitle(Arcitle acritle);
	int findUserAllArcitleCount(String username);
	List<Arcitle> findUserAllArcitle(String username);
	Arcitle findUserArcitleById(int id);
	List<Arcitle> findUserPageArcitle(String username,int pc,int ps);
	List<Arcitle> findAllUserSomeArcitle();
	void modifyArcitlehasread(int hasread,int aid);
	List<Arcitle> findRandomArcitle(int page);
	int findArcitleImageindex(int aid);
	String findArcitleImagesrc(int index,int aid);
	void modifyArcitleImageurl(String imageurl,int aid);
	int findArcitleNumber(int aid);
}
