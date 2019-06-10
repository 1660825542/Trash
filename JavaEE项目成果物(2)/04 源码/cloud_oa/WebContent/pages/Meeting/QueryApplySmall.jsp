<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.meeting.pojo.*" %>
<%@ page import="com.icss.oa.room.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

	function closeWin(){
		$(window.parent.document).find('#popWinClose').click();
	};
</script>
</head>
<body>

<div class="container">
	<div class="row"> 
	    <div class="apply1" style="min-height: 400px;">
	    	<table class="table table-striped ">
			  <tr>
					<th>
						会议发起人
					</th>
					<th>
						会议地点
					</th>
					<th>
						会议开始时间
					</th>
					<th>
						会议结束时间
					</th>					
			  </tr>
			
			  <c:forEach items="${list}" var="meeting">
			  <tr>
						<td>${meeting.EMP_NAME}</td>
						<td>${meeting.ROOM_PLACE}</td>
						<td>	
							<fmt:formatDate value="${meeting.MEET_DATEBEGIN}"/>
							<fmt:formatDate value="${meeting.MEET_DATEBEGIN}" pattern=" HH:mm:ss"/> 
						</td>
						<td>	
							<fmt:formatDate value="${meeting.MEET_DATEEND}"/>
							<fmt:formatDate value="${meeting.MEET_DATEEND}" pattern=" HH:mm:ss"/> 
						</td>
			  </tr>
			  </c:forEach>
			</table>
			
			<div class="text-center">			
				<input type="button" value="确定" class="btn btn-primary" onclick="closeWin();">
			</div>
			
			<!-- 分页条 -->
	    	<jsp:include page="/include/pager.jsp"/>	    
		</div>
	</div>	
</div>

</body>
</html>