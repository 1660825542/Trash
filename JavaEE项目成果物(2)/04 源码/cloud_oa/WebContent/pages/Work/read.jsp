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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
<script type="text/javascript">

function view(taskId,pageNum) {
	layer.open({
		type: 2,
		title: '查看工作',
		maxmin: false,
		shadeClose: false, //点击遮罩关闭层
		area : ['790px' , '400px'],
		closeBtn:2,
		content: '<%=request.getContextPath()%>/work/view.action?taskId=' + taskId + '&pageNum=' + pageNum
	});
}
</script>
<title>查看工作</title>
</head>
<body>
	<c:if test="${taskList.size()>0 }">
	<table class="table table-hover table-condensed table-striped">
    	<tr style="font-size:14px;" align="center">
    		<th style="text-align:center">授予人</th>
    		<th style="text-align:center">接收人</th>
    		<th style="text-align:center">工作主题</th>
    		<th style="text-align:center">布置时间</th>    		
    		<th style="text-align:center">完成状态</th>
    		<th style="text-align:center">操作</th>
    	</tr>
         <c:forEach items="${taskList }" var="task">
           <tr style="font-size:14px;" align="center">
             <td>${task.MANAGER}</td>
             <td>${task.EMP}</td>
             <td>${task.TASK_NAME}</td>
             <td><fmt:formatDate value="${task.TASK_DATE}" pattern="yyyy-MM-dd"/></td>             
             <td>${task.workState}</td>
             <td>             	
				<a href="javascript:view(${task.TASK_ID},${pager.pageNum});" class="btn btn-xs btn-primary">查看工作</a>			
             </td>
           </tr>
         </c:forEach> 
	</table>
	<jsp:include page="/include/AdvancedPager.jsp"></jsp:include> 
	</c:if>
	
	<c:if test="${taskList.size()<=0 }">
        <div class="alert alert-info" role="alert">
      		当前没有工作安排
      	</div>
     </c:if>
   
	<div class="col-sm-10 col-sm-offset-1">
		<table class="table text-center" >
			<tr align="center">
				<td style="text-align:center">
				<a href="<%=request.getContextPath()%>/work/editMy.action" id="readMy" class="btn btn-primary">编辑我的任务</a>
				</td>
				<td style="text-align:center">
				<a href="<%=request.getContextPath()%>/work/editTask.action" id="readMy" class="btn btn-primary">编辑授予任务</a>
				</td>			
				<td style="text-align:center">
				<a href="<%=request.getContextPath()%>/work/readMy.action" id="readMy" class="btn btn-primary">查看我的任务</a>
				</td>
				<td style="text-align:center">
				<a href="<%=request.getContextPath()%>/work/readTask.action" id="readMy" class="btn btn-primary">查看他人任务</a>
				</td>	
			</tr>
		</table>
	</div>
</body>
</html>