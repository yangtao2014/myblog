package cn.yt.service;

import java.util.List;

import cn.yt.beans.Arcitle;
import cn.yt.dao.IArcitleDao;

public class ArcitleServiceImpl implements IArcitleService {
	private IArcitleDao dao;
	
	public void setDao(IArcitleDao dao) {
		this.dao = dao;
	}

	public void addArcitle(Arcitle arcitle) {
		dao.insertArcitle(arcitle);
	}

	public int findUserAllArcitleCount(String username) {
		return dao.selectUserAllArcitleCount(username);
	}

	public List<Arcitle> findUserAllArcitle(String username) {
		return dao.selectUserAllArcitle(username);
	}

	public Arcitle findUserArcitleById(int id) {
		return dao.selectUserArcitleById(id);
	}

	public List<Arcitle> findUserPageArcitle(String username, int pc, int ps) {
		return dao.selectUserPageArcitle(username,pc,ps);
	}

	public List<Arcitle> findAllUserSomeArcitle() {
		return dao.selectAllUserSomeArcitle();
	}
	
	public void modifyArcitlehasread(int hasread,int aid){
		dao.updateArcitlehasread(hasread, aid);
	}
	
	public List<Arcitle> findRandomArcitle(int page){
		return dao.selectRandomArcitle(page);
	}

	public int findArcitleImageindex(int aid) {
		return dao.selectArcitleImageindex(aid);
	}

	public String findArcitleImagesrc(int index,int aid) {
		return dao.selectArcitleImagesrc(index, aid);
	}

	public void modifyArcitleImageurl(String imageurl,int aid) {
		dao.updataArcitleImageurl(imageurl,aid);
	}

	public int findArcitleNumber(int aid) {
		return dao.selectArcitleNumberById(aid);
	}
}
