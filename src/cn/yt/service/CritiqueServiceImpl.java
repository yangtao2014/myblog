package cn.yt.service;

import java.util.List;

import cn.yt.beans.Critique;
import cn.yt.dao.ICritiqueDao;

/**
 * 评论服务实现类
 * @author TC
 *
 */
public class CritiqueServiceImpl implements ICritiqueService {
	private ICritiqueDao dao;
	
	public void setDao(ICritiqueDao dao) {
		this.dao = dao;
	}
	/**
	 * 查找所有评论数
	 */
	public int findAllCount(int aid) {
		return dao.selectAllCount(aid);
	}
	public List<Critique> findAllCritique(int aid) {
		return dao.selectAllCritique(aid);
	}
	public void addCritique(Critique critique) {
		dao.insertCritique(critique);
	}


}
