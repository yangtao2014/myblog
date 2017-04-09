package cn.yt.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yt.beans.Critique;
import cn.yt.service.ICritiqueService;
/**
 * 评论处理类
 * @author TC
 *
 */
@Controller
@RequestMapping("/cri")
public class CritiqueController {
	@Autowired
	@Qualifier("critiqueService")
	private ICritiqueService service;

	public void setService(ICritiqueService service) {
		this.service = service;
	}
	/**
	 * 查找该文章所有评论数
	 * @param aid
	 * @return
	 */
	@RequestMapping("/fac.do")
	@ResponseBody
	public Object findAllCount(int aid){
		return service.findAllCount(aid);
	}
	/**
	 * 显示文章评论
	 * @param aid
	 * @return
	 */
	@RequestMapping("/facq.do")
	@ResponseBody
	public Object findAllCritique(int aid){
		List<Critique> cl = service.findAllCritique(aid);
		return cl;
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public Object AddCritique(Critique form){
		form.setDate(new Date());
		service.addCritique(form);
		return true;
	}
}
