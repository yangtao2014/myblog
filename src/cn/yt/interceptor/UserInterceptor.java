package cn.yt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.yt.beans.User;

/**
 * 
 * @author TC
 * 拦截器，判断用户是否登陆，防止用户未登陆进入私人界面
 */
public class UserInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("执行拦截器,判断用户是否登录");
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			request.getRequestDispatcher("/WEB-INF/jsps/fail.jsp").forward(request, response);
		}
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
