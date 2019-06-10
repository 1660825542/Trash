<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.meeting.pojo.*" %>
<%@ page import="com.icss.oa.room.pojo.*" %>
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

	var roomId;
	var roomName;
	
	//返回值到父窗口
	function returnPopValue() {		
				
		$(window.parent.document).find('#roomId').val(roomId);
		$(window.parent.document).find('#roomName').val(roomName);	
		$(window.parent.document).find('#popWinClose').click();
	}
	
	//关闭当前窗口
	function closeWin(){
		$(window.parent.document).find('#popWinClose').click();
	}
	
	$(document).ready(function(){
		$('input[name="ids"]').click(function(){
			roomId = $.trim( $(this).parent().parent().find('td').eq(1).text() );
			roomName = $.trim( $(this).parent().parent().find('td').eq(2).text() );			
		});		
	});
</script>
</head>
<body>

<div class="container">
	<div class="row"> 
	    <div class="room1" style="min-height: 400px;">
	    	<table class="table table-striped">
			  <tr>
			    <th width="100" height="24">选择</th>
			    <th width="129" height="24">会议室编号</th>
			    <th width="177" height="24">会议室名称</th>   
			  </tr>
			
			  <c:forEach items="${requestScope.list }" var="room">
			  <tr>
			    <td height="24"><input type="radio" name="ids"></td>
			    <td height="24"><c:out value="${room.roomId }"/></td>
			    <td height="24"><c:out value="${room.roomName }"/></td>   
			  </tr>
			  </c:forEach>
			</table>
			<!-- 分页条 -->
			<jsp:include page="/include/pager.jsp"></jsp:include>	
				
			<div class="text-center">			
				<input type="button" value="确定" class="btn btn-primary" onclick="returnPopValue();">
				<input type="button" value="取消" class="btn" onclick="closeWin();">
			</div>

		</div>
	</div>	
</div>

</body>
</html>