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
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<script type="text/javascript">

//删除确认
function del(taskId,pageNum) {
	var flag = window.confirm("确定要删除此记录吗");
	if (flag) {
		location.href = "<%=request.getContextPath()%>/work/deleteTask.action?taskId=" + taskId + "&pageNum=" + pageNum;
	}
}	

function edit(taskId,pageNum) {
	layer.open({
		type: 2,
		title: '工作',
		maxmin: false,
		closeBtn:2,
		shadeClose: false, //点击遮罩关闭层
		area : ['790px' , '400px'],
		content: '<%=request.getContextPath()%>/work/reEdit.action?taskId=' + taskId + '&pageNum=' + pageNum					
	});
}

function finish(taskId,pageNum) {
	location.href = "<%=request.getContextPath()%>/work/finish.action?taskId=" + taskId + "&pageNum=" + pageNum;
}

</script>
<title>编辑工作</title>
</head>
<body>
	<c:if test="${taskList.size()>0 }">
	<table class="table table-hover table-condensed table-striped ">
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
             	<div class="dropdown">
					<button type="button" class="btn btn-default btn-xs dropdown-toggle" id="dropdownMenu" data-toggle="dropdown">
						操作
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li>
							<a href="javascript:edit(${task.TASK_ID},${pager.pageNum});">编辑工作</a>
						</li>
						<li>
							<a href="javascript:finish(${task.TASK_ID},${pager.pageNum});">完成工作</a>
						</li>
						<li>
							<a href="javascript:del(${task.TASK_ID},${pager.pageNum});">删除工作</a>
						</li>
					</ul>
				</div>		
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