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
<title>代办查看</title>
</head>
<body>
	<table class="table table-hover table-condensed table-striped">
    	<thead>
	    	<tr style="font-size:14px;">
	    		<th style="text-align:center">姓名</th>
	    		<th style="text-align:center">部门</th>
	    		<th style="text-align:center">职位</th>
	    		<th style="text-align:center">代读状态</th>
	    	</tr>
    	</thead>
         <c:forEach items="${empList }" var="myemp">
           <tr style="font-size:14px;" align="center">
             <td>${myemp.EMP_NAME}</td>
             <td>${myemp.EMP_DEPT}</td>
             <td>${myemp.EMP_POS}</td>
             <td>${myemp.STATE}</td>
           </tr>
         </c:forEach> 
	</table>
	<jsp:include page="/include/AdvancedPager.jsp"></jsp:include> 
</body>
</html>