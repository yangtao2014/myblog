package cn.yt.dao;

import java.util.List;

import cn.yt.beans.Critique;

/**
 * 评论dao层
 * @author TC
 *
 */
public interface ICritiqueDao {
	int selectAllCount(int aid);
	List<Critique> selectAllCritique(int aid);
	void insertCritique(Critique critique);
}
