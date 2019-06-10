<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.possession.pojo.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加消耗记录</title>
<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popwin.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>

	<script type="text/javascript">
	
	function closeWin(){
		$(window.parent.document).find('#popWinClose').click();
	}
	function returnPopValue() {		
		
		$(window.parent.document).find('#possId3').val(possId);
		$(window.parent.document).find('#possName3').val(possName);	
		$(window.parent.document).find('#possCate3').val(possCate);
		var url = window.parent.document.getElementById('borNum').getAttribute('ajaxurl') + possId;
		window.parent.document.getElementById('borNum').setAttribute('ajaxurl', url);
		$(window.parent.document).find('#popWinClose').click();
		$(window.parent.document).find('#borNum').removeAttr("readonly");
		
	}
	$(document).ready(function(e) {
		$('input[name="ids"]').click(function(){
			possId = $.trim( $(this).parent().parent().find('td').eq(1).text() );
			possName = $.trim( $(this).parent().parent().find('td').eq(2).text() );		
			possCate = $.trim( $(this).parent().parent().find('td').eq(3).text() );	
			 $('#subBtn').prop('disabled',false); 
	        });
		while ($('tr[name="addtr"]').length<9)
			
		{
			$('tr[name="addtr"]').last().after('<tr name="addtr"><td>&nbsp</td><td> </td><td> </td><td> </tr>');
			
		}
	
	});
	</script>
</head>
<body>

<table class=" table ">
	<thead>
		<tr>
			<th>选择</th>
			<th>库存编号</th>
			<th>库存名称</th>
			<th>库存类别</th>
		</tr>
	</thead>

	<c:forEach items="${requestScope.list }" var="house">
		<tr name="addtr">
			<td>
				<input name="ids" type="radio"></td>
			<td height="24" >${house.possId }</td>
			<td height="24">
				<c:out value="${house.possName }"/>
			</td>
			<td height="24">
				<c:out value="${house.possCate }"/>
			</td>

		</tr>
	</c:forEach>

</table>
<div class="text-center">
	<input type="button" id="subBtn" value="确定" class="btn btn-primary" disabled="disabled" onclick="returnPopValue();">
	<input type="button" value="取消" class="btn" onclick="closeWin();"></div>
<!--    <table>
<tr>
	<td colspan="2" style="text-align: center">
		<input name="button" id="insertBtn1"  value="确定" class="btn btn-primary" type="submit">
		<input name="button2" id="cancleBtn"  value="取消" class="btn btn-primary" type="submit"></td>
</tr>
</table>
-->
<jsp:include page="/pages/possinfo/pagers.jsp"></jsp:include>
</body>
</html>
		
		  
		    
		    	
