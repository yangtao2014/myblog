package cn.yt.dao;

import cn.yt.beans.Hits;

public interface IHitsDao {
	void insertHit(Hits hits);
	int selectArcitleHitsCount(int aid);
}
