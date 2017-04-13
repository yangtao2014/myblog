package cn.yt.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;

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
	/**
	 * 
	 * @param request
	 * @param username
	 * @return
	 * @throws IOException
	 *//*
	@RequestMapping("/uploadavatar.do")
	@ResponseBody
	public Object uploadavatar(HttpServletRequest request) throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) request.getInputStream()));
			String path = request.getSession().getServletContext().getRealPath("avatar");
			String urlname =  UUID.randomUUID() + ".png";
			String newPath = path +"/"+ urlname;
			// 拿到输出流，同时重命名上传的文件
			FileOutputStream os = new FileOutputStream(newPath);
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString().substring(22));
			String allResult = sb.toString().substring(22);
			 //进行解密
			byte[] result = this.decodeBASE64(allResult); 
			
			os.write(result);
			os.flush();
			os.close();
			br.close();
			String avatarurl = "avatar/" + urlname;
			service.modifyAvatarByUsername(avatarurl, "yt");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("上传出错");
		}
		return 0;
	}*/
	//解密方法
	//FileOutSteam写文件传byte或者int,String转byte在写文件会不成功。
	public byte[] decodeBASE64(String str) throws Exception {
		if (str != null) {
			return new BASE64Decoder().decodeBuffer(str);
		}
		return null;
	}
	/**
	 * 
	 * @param multiRequest
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadavatar.do")
	@ResponseBody
	public Object uploadtest(MultipartHttpServletRequest multiRequest,HttpServletRequest request){
		System.out.println(multiRequest);
		try {
			String[] strusername = null;
			String path = request.getSession().getServletContext().getRealPath("avatar");
			String urlname =  UUID.randomUUID() + ".png";
			String newPath = path +"/"+ urlname;
			FileOutputStream os = new FileOutputStream(newPath);
	        DefaultMultipartHttpServletRequest defaultRequest = (DefaultMultipartHttpServletRequest) multiRequest;
	        Map<String, String[]> params = defaultRequest.getParameterMap();
	        if (params.size() > 0) {
	            strusername = params.get("username");
	            String[] strdata = params.get("data");
	            if(strdata.length > 0) {
	            	//System.out.println(strdata[0]);
	            	byte[] result = this.decodeBASE64(strdata[0].substring(22)); 
		            os.write(result);
		            os.flush();
	            }
	            if (strusername.length > 0){
	                //System.out.println(strusername[0]);
	                String avatarurl = "avatar/" + urlname;
	    			service.modifyAvatarByUsername(avatarurl, strusername[0]);
	    			User user = service.findUserByName(strusername[0]);
	    			request.getSession().setAttribute("user", user);
	    			return 1;
	            }
	        }
			os.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("上传出错");
		}
		return 0;
	}
	
}
