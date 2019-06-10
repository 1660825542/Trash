<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.meeting.pojo.*" %>
<%@ page import="com.icss.oa.room.pojo.*" %>
<%@ page import="com.icss.oa.system.pojo.*" %>
<%@ page import="com.icss.oa.emp_meeting.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css">
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/Validform/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script type="text/javascript">


	//返回值到父窗口
	function returnPopValue() {	
		
		var parentIds = $(window.parent.document).find('#parentIds');
		
		parentIds.empty();
		
		$('input[name="ids"]:checked').each(function(index,obj){
			
			var strs = obj.value.split(',');
			
			parentIds.append('<input type="hidden" name="ids" value="' + strs[0] + '">');
			parentIds.append('<span>' + strs[1] + ';</span>');
		});
		
		
		$(window.parent.document).find('#popWinClose').click();
	}
	
	//关闭当前窗口
	function closeWin(){
		$(window.parent.document).find('#popWinClose').click();
	}


	
</script>
</head>
<body>

<div class="container">
	<div class="row"> 
	    <div class="emp" style="min-height: 400px;">
	    	<table class="table table-striped">
			  <tr>
			    <th width="100" height="24">选择</th>
			    <th width="129" height="24">员工编号</th>
			    <th width="177" height="24">员工姓名</th>   
			  </tr>
			
			  <c:forEach items="${list}" var="employee">
			  <tr>
			    <td height="24"><input type="checkbox" name="ids" value ="${employee.empId},${employee.empName}"></td>
			    <td height="24"><c:out value="${employee.empId}"/></td>
			    <td height="24"><c:out value="${employee.empName}"/></td>   
			  </tr>
			  </c:forEach>
			</table>
			<div class="text-center">			
				<input type="button" value="确定" class="btn btn-primary" onclick="returnPopValue();">
				<input type="button" value="取消" class="btn" onclick="closeWin();">
			</div>
  			<!-- 分页条 -->
	    	<jsp:include page="/include/pager.jsp"/>
		</div>
	</div>	
</div>

</body>
</html>