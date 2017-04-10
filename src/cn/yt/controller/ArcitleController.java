package cn.yt.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.yt.beans.Arcitle;
import cn.yt.beans.PageBean;
import cn.yt.service.IArcitleService;

/**
 * 
 * @author TC
 * 文章处理器
 */
@Controller
@RequestMapping("/individual")
public class ArcitleController {
	@Autowired
	@Qualifier("arcitleService")
	private IArcitleService service;

	public void setService(IArcitleService service) {
		this.service = service;
	}
	/**
	 * 写博客方法
	 * @param form
	 * @return
	 */
	@RequestMapping("/writeblog.do")
	public ModelAndView writeBlog(Arcitle form){
		Date date = new Date();
		form.setDate(date);
		form.setHasread(0);
		form.setCommend(0);
		service.addArcitle(form);
		return new ModelAndView("/individual/loadimageindex.do?aid="+form.getAid());
	}
	/**
	 * 显示用户博客数量
	 * @param username
	 * @return
	 */
	@RequestMapping("/showblogcount.do")
	public ModelAndView showBlogcount(String username){
		int count = service.findUserAllArcitleCount(username);
		return new ModelAndView();
	}
	/**
	 * 分页显示用户博客
	 * @param username
	 * @return
	 */
	@RequestMapping("/showblogpage.do")
	public ModelAndView showBlogByPage(String username,int pc){
		int ps = 10;
		List<Arcitle> arcitlelist = service.findUserPageArcitle(username, (pc-1)*ps, ps);
		int count = service.findUserAllArcitleCount(username);
		PageBean<Arcitle> pb = new PageBean<Arcitle>();
		pb.setBeanList(arcitlelist);
		pb.setTr(count);
		pb.setPs(ps);
		pb.setPc(pc);
		ModelAndView mv = new ModelAndView();
		mv.addObject("pb", pb);
		mv.setViewName("/home.jsp");
		return mv;
	}
	/**
	 * 显示点击的文章
	 * @param id
	 * @return
	 */
	@RequestMapping("/showthis.do")
	public ModelAndView showUserBlog(int aid){
		Arcitle arcitle = service.findUserArcitleById(aid);
		int number = service.findArcitleNumber(aid);
		ModelAndView mv = new ModelAndView();
		mv.addObject("al",arcitle);
		mv.addObject("number",number);
		mv.setViewName("/read.jsp");
		return mv;
	}
	
	/**
	 * 显示推荐文章
	 * @return
	 */
	@RequestMapping("/showcommendblog.do")
	public ModelAndView showSomeBlog(){
		List<Arcitle> al = service.findAllUserSomeArcitle();
		ModelAndView mv = new ModelAndView();
		mv.addObject("al", al);
		mv.setViewName("/index.jsp");
		return mv;
	}
	/**
	 * 显示评论
	 * @param hasread
	 * @param aid
	 * @return
	 */
	@RequestMapping("/modifyHasread.do")
	public ModelAndView modifyArcitlehasread(int hasread,int aid){
		service.modifyArcitlehasread(hasread, aid);
		return new ModelAndView("/individual/showthis.do?aid="+aid);
	}
	/**
	 * 首页显示文章
	 * @param page
	 * @return
	 */
	@RequestMapping("/showmore.do")
	@ResponseBody
	public Object showmore(int page){
		return service.findRandomArcitle(page*10);
	}
	/**
	 * 查询是否有图片
	 * @param aid
	 * @return
	 */
	@RequestMapping("/loadimageindex.do")
	public ModelAndView loadimageindex(int aid){
		int index = service.findArcitleImageindex(aid);
		if(index > 0){	
			return new ModelAndView("/individual/loadimage.do?index="+index+"&aid="+aid);
		}
		return new ModelAndView("redirect:/");
	}
	/**
	 * 查询图片地址
	 * @param index
	 * @param length
	 * @param aid
	 * @return
	 */
	@RequestMapping("/loadimage.do")
	public ModelAndView loadimage(int index,int aid){
		String imageurl = service.findArcitleImagesrc(index+5,aid);
		int flag = imageurl.indexOf("\"");
		if(flag > -1){
			imageurl = imageurl.substring(0,flag);
		}
		service.modifyArcitleImageurl(imageurl,aid);
		return new ModelAndView("redirect:/");
	}
	
	
}
