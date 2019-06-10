<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.system.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>组织机构</title>
</head>
<body>
	<div class="container-fluid">
		<div class="col-md-12">
			<div class="list-group" align="center" style="font-size:14px;">
		   		<a target="content" href="<%=request.getContextPath()%>/agent/queryAll.action" class="list-group-item">全体员工</a>
		   		<c:forEach items="${deptList }" var="dept">
		   			<a target="content" href="<%=request.getContextPath()%>/agent/queryEmpByDept.action?departmentId=${dept.deptId }" class="list-group-item">
		   			${dept.deptName}
		   			</a>
		   		</c:forEach>
	   		</div>           
	      </div>	      
      </div>
</body>
</html>