<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.possession.pojo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		
		$(window.parent.document).find('#empId').val(empId);
		$(window.parent.document).find('#empName').val(empName);	
		$(window.parent.document).find('#popWinClose').click();
	}
	$(document).ready(function(e) {
		$('input[name="ids"]').click(function(){
			empId = $.trim( $(this).parent().parent().find('td').eq(1).text() );
			empName = $.trim( $(this).parent().parent().find('td').eq(2).text() );	
			$('#subBtn1').prop('disabled',false);
		
			  
		
			   
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
			<th>员工编号</th>
			<th>员工姓名</th>
			<th>员工部门</th>

		</tr>
	</thead>

	<c:forEach items="${requestScope.list }" var="emp">
		<tr name="addtr">
			<td>
				<input name="ids" type="radio"></td>
			<td height="24" >${emp.EMP_ID }</td>
			<td height="24">
				<c:out value="${emp.EMP_NAME }"/>
			</td>
			<td height="24">
				<c:out value="${emp.DEPT_NAME }"/>
			</td>

		</tr>
	</c:forEach>

</table>
<div class="text-center">
	<input type="button" id="subBtn1" disabled="disabled" value="确定" class="btn btn-primary" onclick="returnPopValue();">
	<input type="button" value="取消" class="btn" onclick="closeWin();"></div>

<jsp:include page="/pages/possinfo/pagers.jsp"></jsp:include>
</body>

</body>
</html>