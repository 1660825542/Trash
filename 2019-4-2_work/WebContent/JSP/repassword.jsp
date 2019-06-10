<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
	<title>BTSgifts | 找回密码</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/base.css" />

	<script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-1.12.4.min.js"></script>	
	

	<script type="text/javascript" src="${pageContext.request.contextPath}/JS/app.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-2.1.4.js"></script>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/1.js"></script>
	
	
</head><body>

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
				<a class="iconbounce login" href="${pageContext.request.contextPath}/JSP/suplogin.jsp" title="超级管理员登录"><i class="icon icon-login"></i>超级管理员登录</a>
				<a class="iconbounce" href="service.jsp" title="联系客服"><i class="icon icon-service"></i>联系客服</a>
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
					
					
						<form action="${pageContext.request.contextPath}/User_Servlet" method="post">
						<ul class="loginfrm-body">
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>
								<div class="loginfrm-group">
									<input type="hidden" name="method" value="repassword">
									<p class="loginfrm-loading"></p>
									<input type="text" class="input-big"  name="phonenum" id="repassword" placeholder="请输入注册时使用的手机号" autocomplete="off">
								</div>
							</li>
							<li>
								<div class="loginfrm-group">
									<p id="question"></p>
									<input type="text" class="input-big"   name="answer"  placeholder="请输入密保问题答案" autocomplete="off"/>
								</div>
							</li>
						
							
							<li>
								<div class="loginfrm-group">
									<p class="loginfrm-loading">${error}</p>
									<input class="button-big button-block login-submit" type="submit" value="验证身份"/>
								</div>
							</li>
							
						</ul>
						</form>
					</div>
					
				
				
			</div>
		</div>
	</div>

	<div class="wrap tc">
		<img src="${pageContext.request.contextPath}/img/sbj.jpg" width="1069" height="116" />
		<div class="fcopyright">
			<p class="note">网站声明：本站由软工七班的最帅六人组完成，涛总真滴帅！！！/p>
		</div>
		<div class="h30"></div>
    </div>

</body>
</html>

