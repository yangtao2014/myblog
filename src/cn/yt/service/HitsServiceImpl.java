package cn.yt.service;

import cn.yt.beans.Hits;
import cn.yt.dao.IHitsDao;

public class HitsServiceImpl implements IHitsService {
	private IHitsDao dao;
	
	public void setDao(IHitsDao dao) {
		this.dao = dao;
	}

	public void addHits(Hits hits) {
		dao.insertHit(hits);
	}

	public int findArcitleHitsCount(int aid) {
		return dao.selectArcitleHitsCount(aid);
	}

}
