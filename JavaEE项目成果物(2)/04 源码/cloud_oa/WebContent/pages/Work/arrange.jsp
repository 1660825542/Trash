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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
<script type="text/javascript">
	
	function arr(empId,pageNum) {
		layer.open({
			type: 2,
			title: '安排工作',
			closeBtn:2,
			maxmin: false,
			shadeClose: false, //点击遮罩关闭层
			area : ['790px' , '400px'],
			content: '<%=request.getContextPath()%>/work/reInsert.action?empId=' + empId + '&pageNum=' + pageNum
		});	
	}
	
	function arrMy(empId){	
			layer.open({
				type: 2,
				title: '安排工作',
				maxmin: false,
				closeBtn:2,
				shadeClose: false, //点击遮罩关闭层
				area : ['790px' , '400px'],
				content: '<%=request.getContextPath()%>/work/reInsert.action?empId=' + empId + '&pageNum=' + 1
			});	
		}
</script>
<title>安排工作</title>
</head>
<body>
	<table class="table table-hover table-condensed table-striped">
    	<tr style="font-size:14px;" align="center">
    		<th style="text-align:center">姓名</th>
    		<th style="text-align:center">部门</th>
    		<th style="text-align:center">职位</th>
    		<th style="text-align:center">操作</th>
    	</tr>
         <c:forEach items="${arrList }" var="arr">
           <tr style="font-size:14px;" align="center">
             <td>${arr.EMP_NAME}</td>
             <td>${arr.EMP_DEPT}</td>
             <td>${arr.EMP_POS}</td>
             <td>
				<a href="javascript:arr(${arr.EMP_ID},${pager.pageNum});" class="btn btn-primary btn-xs">安排工作</a>			
             </td>
           </tr>
         </c:forEach> 
	</table>
	<jsp:include page="/include/AdvancedPager.jsp"></jsp:include> 
	<div align="center">
		<a href="javascript:arrMy(<%=((Employee)request.getSession().getAttribute("queryemp")).getEmpId()%>);" class="btn btn-primary col-sm-8 col-sm-offset-2">添加新的工作</a>
	</div>
</body>
</html>