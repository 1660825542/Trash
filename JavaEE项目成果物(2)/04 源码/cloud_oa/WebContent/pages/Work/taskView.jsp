<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.system.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<title>工作查看</title>
</head>
<body>
	<div class="container">
		<form name="form" id="form" class="form-horizontal">
		<div class="form-group"> 
             <label for="empName" class="col-sm-2 control-label">工作授予人</label>
             <div class="col-sm-4">
             	<input type="text" value="${task.MANAGER }" name="managerName" id="managerName" class="form-control">               
             </div>
              <label for="empName" class="col-sm-2 control-label">工作接受人</label>
             <div class="col-sm-4">
             	<input type="text" value="${task.EMP }" name="empName" id="empName" class="form-control">               
             </div>
		</div>
		<div class="form-group"> 
             <label for="empName" class="col-sm-2 control-label">开始时间</label>
             <div class="col-sm-4">
             	<input type="text"  value="<fmt:formatDate value="${task.START_TIME }" pattern="yyyy-MM-dd HH:mm"/>" name="startTime" id="start" class="form-control">               
             </div>
             <label for="empName" class="col-sm-2 control-label">结束时间</label>
             <div class="col-sm-4">
             	<input type="text" value="<fmt:formatDate value="${task.FINISH_TIME }" pattern="yyyy-MM-dd HH:mm"/>" name="finishTime" id="finish" class="form-control">               
             </div>
		</div>
		<div class="form-group"> 
             <label for="empName" class="col-sm-2 control-label">工作主题</label>
             <div class="col-sm-4">
             	<input type="text" value="${task.TASK_NAME }" name="taskName" id="taskName" class="form-control">               
             </div>
              <label for="empName" class="col-sm-2 control-label">布置时间</label>
             <div class="col-sm-4">
             	<input type="text" value="<fmt:formatDate value="${task.TASK_DATE}" pattern="yyyy-MM-dd"/>" name="taskName" id="taskName" class="form-control">               
             </div>
		</div>
		<div class="form-group"> 
             <label for="empName" class="col-sm-2 control-label">工作内容</label>
             <div class="col-sm-10">    
             	<textarea name="taskContent" id="taskContent" class="form-control" cols="40" rows="4">${task.TASK_CONTENT }</textarea>                        
             </div>            
		</div >
		</form>
	</div>
</body>
</html>