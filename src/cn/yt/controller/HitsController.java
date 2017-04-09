package cn.yt.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.yt.beans.Hits;
import cn.yt.service.IHitsService;


@Controller
@RequestMapping("/hits")
public class HitsController {
	
	@Autowired
	@Qualifier("hitsService")
	private IHitsService service;
	
	public void setService(IHitsService service) {
		this.service = service;
	}
	
	/**
	 * 添加点击量
	 * @param request
	 * @param aid
	 * @return
	 */
	@RequestMapping("/addhit.do")
	public ModelAndView hit(HttpServletRequest request,int aid){
		Hits hits = new Hits();
		hits.setAid(aid);
		hits.setIp(request.getRemoteAddr());
		hits.setTime(new Date());
		service.addHits(hits);
		return new ModelAndView("redirect:/hits/showcount.do?aid="+aid);
	}
	
	@RequestMapping("/showcount.do")
	public ModelAndView showcount(int aid){
		int hasread = service.findArcitleHitsCount(aid);
		return new ModelAndView("redirect:/individual/modifyHasread.do?hasread="+hasread+"&aid="+aid);
	}
}
