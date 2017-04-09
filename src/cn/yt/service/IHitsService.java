package cn.yt.service;

import cn.yt.beans.Hits;



public interface IHitsService {
	void addHits(Hits hits);
	int findArcitleHitsCount(int aid);
}
