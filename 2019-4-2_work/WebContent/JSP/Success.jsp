<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<br/>
<br/>
<br/>
<br/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/my.css" />
<div class="container11">
	<div class="receipt">
		<h2 class="receipt__title">支付订单</h2>
		<p class="receipt__subtitle">具体清单:</p>
		<c:forEach items="${cartList}" var="cart">
		<ul class="receipt__lines">
			<li class="receipt__line">
				<span class="receipt__line__item">${cart.name} &nbsp;&nbsp;${cart.num}</span>

			</li>
		</ul>
		</c:forEach>
		<p class="receipt__total">
			<span class="receipt__total__item">合计：</span>
			<span class="receipt__total__price">￥ ${totalPrice}</span>
		</p>
		<p class="receipt__back">
			<a href="#">确认支付</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/User_Servlet?method=login&phonenum=${user}&password=${password}">取消支付并返回首页</a>
		</p>
	</div>
</div>


<br/>
<br/>
<br/>
<br/>

	
</body>
</html>