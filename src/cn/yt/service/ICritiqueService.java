package cn.yt.service;

import java.util.List;

import cn.yt.beans.Critique;

/**
 * 评论服务接口层
 * @author TC
 *
 */
public interface ICritiqueService {
	int findAllCount(int aid);
	List<Critique> findAllCritique(int aid);
	void addCritique(Critique critique);
}
