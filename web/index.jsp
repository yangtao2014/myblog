<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>首页</title>
	<link rel="stylesheet" href="css/index.css">
	<script src="js/jquery-3.0.0.js"></script>
	<script src="js/scrollAjax.js"></script>
	<script src="js/formatdate.js"></script>
	<script src="js/index.js"></script>
</head>
<body>
	<nav class="navbar">
		<div class="width-limit">
			<a href="" class="logo">
				<img src="images/logo-index.png">
			</a>
	<c:choose>
		<c:when test="${! empty user }">
			<a href="write.jsp" class="write-btn">
				<i class="iconfont ic-write"></i>写文章
			</a>
			<div class="user">
				<div data-hover="dropdown">
					<a class="avatar" href=""><img src="${user.avatar}" /></a>
				</div>
				<ul class="dropdown-menu">
					<li>
						<a href="">
							<i class="iconfont ic-profile"></i>
							<span>我的主页</span>
						</a>
					</li>
					<li>
						<a href="account/logout.do">
							<i class="iconfont ic-exit"></i>
							<span>退出</span>
						</a>
					</li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>	
			<a href="login.jsp" class="write-btn">
				<i class="iconfont ic-write"></i>写文章
			</a>
			<a href="login.jsp" class="signup">注册</a>
			<a href="login.jsp" class="signin">登录</a>
		</c:otherwise>
	</c:choose>
			<div class="navbar-container">
				<ul class="navbar-nav">
					<li>
						<a href="">首页</a>
					</li>
					<li>
						<span>记录生活学习点点滴滴</span>
					</li>
					<li class="search">
						<form action="">
							<input type="text" name="q" id="q" value="" placeholder="搜索" class="search-input">
							<a class="search-btn" href="javascript:void(null)"><i class="iconfont ic-search"></i></a>
						</form>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="index">
		<div class="row">
			<div class="slide">
				<img src="images/item.png" />
			</div>
			<div class="main">
				<div class="split-line"></div>
				<div class="list-container">
					<ul class="note-list">
						<c:forEach var="al" items="${al}">
							<c:choose>
								<c:when test="${!empty al.imageurl}">
									<li class="have-img">
										<a href="individual/showthis.do?aid=${al.aid}" class="wrap-img">
											<img src="${al.imageurl }" width="150" height="120"/>
										</a>
								</c:when>
								<c:otherwise>
									<li>
								</c:otherwise>
							</c:choose>
										<div class="note-content">
											<div class="author">${al.username}</div>
											<a href="individual/showthis.do?aid=${al.aid}" class="title">${al.title}</a>
											<p class="abstract">${al.content}
												<c:if test="${fn:length(al.content)==100 }">
												... 
												</c:if>
											</p>
											<div class="meta"><fmt:formatDate type="both" value="${al.date}"/></div>
										</div>
									</li>
						</c:forEach>
					</ul>
				</div>
				<a class="load-more" data-page="3">加载更多</a>
				<p class="load-none">————没有了————</p>
			</div>
			<div class="aside">	
				<p class="title">项目公告</p>
				<div class="decription">
					<p>我的博客记录我的感想，有关学习上的事情，分享自己对专业的认识，和总结学习过的知识。</p>
					<p>不闻不若闻之，闻之不若见之，见之不若知之，知之不若行之，学至于行而止矣，行之，明也。    ——荀子</p>
				</div>
			</div>
		</div>
	</div>
	<div class="side-tool">
		<a href="#top" class="back-top"><i class="iconfont ic-backtop"></i></a>
	</div>
	<div class="footer">
		<span class="footer-mobile-show">2017博客</span>
		<span class="dot footer-mobile-show">·</span>
		<span class="footer-mobile-show">记录每天灵感</span><br>
		<span>CopyRight</span>
		<span class="dot">·</span>
		<span>yt</span>
	</div>
</body>
</html>