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
<script type="text/javascript">
	//删除确认
	function del(uaId) {
		var flag = window.confirm("确定要删除此员工吗");
		if (flag) {
			location.href = "<%=request.getContextPath()%>/agent/deleteAgent.action?uaId=" + uaId;
		}
	}	
</script>
<title>代办管理</title>
</head>
<body>
<c:if test="${managerList.size()>0 }">
	<table class="table table-hover table-condensed table-striped">
    	<tr style="font-size:14px;" align="center">
    		<th style="text-align:center">姓名</th>
    		<th style="text-align:center">部门</th>
    		<th style="text-align:center">职位</th>
    		<th style="text-align:center">代读状态</th>
    		<th style="text-align:center">操作</th>
    	</tr>
         <c:forEach items="${managerList }" var="manager">
           <tr style="font-size:14px;" align="center">
             <td>${manager.MANAGER_NAME}</td>
             <td>${manager.MANAGER_DEPT}</td>
             <td>${manager.MANAGER_POS}</td>
             <td>${manager.STATE}</td>
             <td>		
				<a href="javascript:del(${manager.UA_ID});" class="btn btn-primary btn-xs">删除</a>			
             </td>
           </tr>
         </c:forEach> 
	</table>
	<jsp:include page="/include/AdvancedPager.jsp"></jsp:include> 
</c:if>
<c:if test="${managerList.size()<=0 }">
	<div class="alert alert-info" role="alert">
	      		当前没有代读人员
   	</div>
</c:if>
</body>
</html>