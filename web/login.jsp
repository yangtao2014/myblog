<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>login</title>
	<link rel="stylesheet" href="<c:url value='/css/lr.css'/>">
	<script src="<c:url value='/js/lr.js'/>"></script>
</head>
<body>
	<div class="lr-main">         <!-- lr是login和register取首字母 -->
		<div class="lr-main-body">
			<div class="lr-header">
				<h1 class="logo"></h1>
				<h2 class="subtitle">记录生活学习点点滴滴</h2>
			</div>
			<div class="sign-flow clearfix">
				<div class="lr-tab-navs">
					<div class="lr-slider">
						<!-- 写两个##页面不会刷新 -->
						<a href="##signup" id="signup" class="active">注册</a>
						<a href="##signin" id="signin" class="">登录</a>
						<span class="lr-slider-bar" id="lr-slider-bar"></span>
					</div>
					<c:if test="${! empty message}">
						<p style="color:red;">${message }</p>
					</c:if>
				</div>
				<div class="view" id="view-signin" style="display: none;">
					<form action="<c:url value='/account/login.do'/>" method="POST">
						<div class="group-inputs">
							<div class="input-wrapper">
								<input type="text" name="username" placeholder="用户名" required="required">
							</div>
							<div class="input-wrapper">
								<input type="password" name="password" placeholder="密码" required="required">
							</div>
						</div>
						<div class="button-wrapper">
							<button class="sign-button" type="submit">登录</button>
						</div>
					</form>
				</div>
				<div class="view" id="view-signup" style="display: block;">
					<form action="<c:url value='/account/regist.do'/>" method="POST">
						<div class="group-inputs">
							<div class="input-wrapper">
								<input type="text" name="username" placeholder="用户名" required="required">
							</div>
							<div class="input-wrapper">
								<input type="password" name="password" placeholder="密码" required="required">
							</div>
						</div>
						<div class="button-wrapper">
							<button class="sign-button" type="submit">注册</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<span class="footer-mobile-show">2017博客</span>
		<span class="dot footer-mobile-show">·</span>
		<span class="footer-mobile-show">记录每天灵感</span><br>
		<span>CopyRight</span>
		<span class="dot">·</span>
		<span>yt</span>
	</div>
	<div id="particles-js"></div>
	<script src="<c:url value='/js/particles.js'/>"></script>
	<script src="<c:url value='/js/app.js'/>"></script>
</body>
</html>