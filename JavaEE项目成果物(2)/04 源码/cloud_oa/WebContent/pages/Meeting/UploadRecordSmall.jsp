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
	//关闭当前窗口
	function closeWin(){
		$(window.parent.document).find('#popWinClose').click();
	}

</script>
</head>
<body>

<div class="container">
	<div class="row"> 
	    <div class="record" style="min-height: 400px;">
			 <form name="form" id="form" method="post"
				enctype="multipart/form-data"
				action="<%=request.getContextPath()%>/record/upload.action">
				选择文件
				<input type="file" name="data">
				<input type="submit" value="上传文件">
			</form>
  
		</div>
	</div>	
</div>

</body>
</html>