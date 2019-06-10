<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="list-group">
	<a href="<%=request.getContextPath()%>/pages/Work/task.jsp" class="list-group-item active" style="font-size:15px;font-weight:bolder">工作安排</a>
	<a href="<%=request.getContextPath()%>/pages/Work/task.jsp" class="list-group-item">日程安排</a>
	<a href="<%=request.getContextPath()%>/work/queryAll.action" class="list-group-item">综合查询</a>
	<a href="<%=request.getContextPath()%>/pages/Work/calendar.jsp" class="list-group-item">日历查看</a>
	<a href="<%=request.getContextPath()%>/pages/Work/agent.jsp" class="list-group-item">代读设置</a>
</div>
