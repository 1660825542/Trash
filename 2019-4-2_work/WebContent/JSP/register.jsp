<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BTS网 | 会员中心 &gt; 会员注册</title>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/base.css">

	<script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-1.12.4.min.js"></script>	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/JS/app.min.js"></script>
	
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-2.1.4.js"></script>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/1.js"></script>
	
</head>
<body>
		<div class="topper">
		<div class="wrap">
			<div class="left">
				<a onclick="app.tools.addFavorite();" href="javascript:;" title="收藏BTS网 (haohua.com)">收藏BTS网 (BTSgifts.com)</a>
				<a href="javascript:;">
					关注微信
					<div class="topperpop">
						<div class="fqrcode">
							<p class="note">回复“礼物”有惊喜！" 活动多多，在BTS遇见幸福</p>
							<p class="img"><img src="//www.haohua.com/theme/haohua.com/default/static/img/qrcode.png" width="128" height="130" /></p>
							<p class="gzh">公众号</p>
						</div>
					</div>
				</a>
			</div>
			<div class="right">
				<a class="iconbounce login" href="#" title="登录 / 注册"><i class="icon icon-login"></i>登录 / 注册</a>
				
			</div>
		</div>
	</div>

	<div class="header2brt">
		<div class="wrap header ">
			<div class="logobanner clearfix">
				<div class="logo">
					<a href="//www.haohua.com/" title="【BTS网】专注鲜花电商，只做品质鲜花！"><img src="${pageContext.request.contextPath}/img/logo.png" width="248" height="41" alt="BTS网" /></a>
				</div>
	
			</div>
			
		</div>
	</div>

	<div class="full-login">
		<div class="wrap pos-rel">
			<div class="login-box-outer">
				<div class="login-box">
					<div class="box-bar">
						<ul class="clearfix">
							<li><a href="${pageContext.request.contextPath}/JSP/login.jsp" >登录</a></li>
							<li class="active"><a href="javascript:;">注册</a></li>
						</ul>
					</div>
					<div class="loginfrm mt30 regfrm">
						<form action="${pageContext.request.contextPath}/User_Servlet" method="post">
						<ul class="loginfrm-body">
							<li>
								<div class="B1">
									<input type="hidden" name="method" value="register">
									<input type="text" class="B2"  name="phonenum" value="" placeholder="请输入手机号码" id="phonenum" autocomplete="off">
								</div>
							</li>
							<li>
								<div class="B1">
									<input type="password" class="B2"  name="password" value="" placeholder="请设置登录密码" autocomplete="off"/>
								</div>
							</li>
							<li>
								<div class="B1">
									<input type="text" class="B2"  name="name" value="" placeholder="请输入姓名" autocomplete="off"/>
								</div>
							</li>
							<li>
								<div class="B1">
									<input type="text" class="B2"  name="address" value="" placeholder="请输入送花地址" autocomplete="off"/>
								</div>
							</li>
							<li>
								<div class="B1">
									<select name="question" class="B2">
										<option value="请选择问题" selected="selected">---请选择问题---</option>
										<c:forEach items="${ques}" var="que">
											<option value="${que.question}">${que.question}</option>
										</c:forEach>
									</select>
								</div>
							</li>
							<li>
								<div class="B1">
									<input type="name" class="B2"  name="answer" value="" placeholder="请输密保问题答案" autocomplete="off"/>
								</div>
							</li>
							<li>
								<div class="loginfrm-group">
									<p class="loginfrm-loading" id="error"></p>
								</div>
							</li>
							<li>
								<div class="B1">
									
									<input  class="button-big button-block mobile-submit" type="submit" value="立即注册"/ >
								</div>
							</li>
						</ul>
						</form>
					</div>
				</div>
				
			</div>
		</div>
	</div>

	<div class="wrap tc">
		<img src="${pageContext.request.contextPath}/img/sbj.jpg" width="1069" height="116" />
		<div class="fcopyright">
			<p class="note">网站声明：本站由软工七班最帅6人组 共同完成</p>
		</div>
		<div class="h30"></div>
    </div>

</body>
</html>