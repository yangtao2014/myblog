package cn.yt.dao;

import java.util.List;

import cn.yt.beans.Arcitle;

/**
 * 
 * @author TC
 * 文章dao层，对文章进行增删改查
 *
 */
public interface IArcitleDao {
	void insertArcitle(Arcitle arcitle);
	int selectUserAllArcitleCount(String username);
	List<Arcitle> selectUserAllArcitle(String username);
	List<Arcitle> selectUserPageArcitle(String username,int pc,int ps);
	Arcitle selectUserArcitleById(int id);
	List<Arcitle> selectAllUserSomeArcitle();
	void updateArcitlehasread(int hasread,int aid);
	List<Arcitle> selectRandomArcitle(int page);
	int selectArcitleImageindex(int aid);
	String selectArcitleImagesrc(int index,int aid);
	void updataArcitleImageurl(String imageurl,int aid);
}
