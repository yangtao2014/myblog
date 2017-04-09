package cn.yt.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.yt.beans.User;
import cn.yt.service.IUserService;
/**
 * 
 * @author TC
 * 处理器，接收用户请求，处理相应事务
 */
@Controller
@RequestMapping("/account")
public class UserController{
	@Autowired
	@Qualifier("userService")
	private IUserService service;

	public void setService(IUserService service) {
		this.service = service;
	}
	
	/**
	 * 用户注册模块
	 * @param username
	 * @param password
	 * @param telphone
	 * @return
	 */
	@RequestMapping("/regist.do")
	public ModelAndView regist(User form,HttpServletRequest request){
			form.setAvatar("avatar/defaultavatar.png");
			User user = service.findUserByName(form.getUsername());
			ModelAndView mv = new ModelAndView();
			if(user == null){
				service.addUser(form);
				request.setAttribute("message", "注册成功");
				mv.setViewName("/login.jsp");
				return mv;
			}else{
				request.setAttribute("message","用户名已存在");
				mv.setViewName("/login.jsp");
				return mv;
			}
	}
	/**
	 * 用户登陆模块
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request,String username,String password){
		User user = service.findUserByName(username);
		ModelAndView mv = new ModelAndView();
		if(user != null){
			if(password.equals(user.getPassword())){
				request.getSession().setAttribute("user", user);
				mv.setViewName("redirect:/");
				return mv;
			}else{
				request.setAttribute("message", "密码错误");
				mv.setViewName("/login.jsp");
				return mv;
			}
		}else{
			request.setAttribute("message", "用户不存在");
			mv.setViewName("/login.jsp");
			return mv;
		}
	}
	
	/**
	 * 用户注销
	 * @param session
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/logout.do")
	public void logout(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws IOException{
		session.invalidate();
		//request.getContextPath()得到的是项目名称
		response.sendRedirect(request.getContextPath()+"/");
	}
}
