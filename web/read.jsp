<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <base href="<%=basePath%>">
    <title>read</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/index.css">
	<link rel="stylesheet" href="css/read.css">
	<link rel="stylesheet" href="css/github-markdown.css">
	<script src="js/jquery-3.0.0.js"></script>
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
	<div class="read">
		<div class="post">
			<div class="article">
				<h1>${al.title }</h1>
				<div class="author">by&nbsp;${al.username}&nbsp;日期:<fmt:formatDate type="both" value="${al.date}"/></div>
			</div>
			<div class="markdown-body">
			${al.content}
			</div>
		</div>
	</div>
  </body>
</html>
