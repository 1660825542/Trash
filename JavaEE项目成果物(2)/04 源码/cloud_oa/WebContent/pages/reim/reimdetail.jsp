<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div>
	<div class="row">
		<div class="col-sm-4 col-sm-offset-1">
			<label class="control-label">申请员工：</label>
			${reim.empName }
		</div>
		<div class="col-sm-4 col-sm-offset-1">
			<label class="control-label">部门经理：</label>
			${reim.leaderName }
		</div>
	</div>
	<div class="row">
		<div class="col-sm-10 col-sm-offset-1">
			<label class="control-label">报销金额：</label>
			${reim.reimSum }
		</div>
	</div>
	<div class="row">
		<div class="col-sm-10 col-sm-offset-1">
			<label class="control-label">报销用途：</label>
			${reim.reimReason }
		</div>
	</div>
	<div class="row">
		<div class="col-sm-5 col-sm-offset-1">
			<label class="control-label">申请日期：</label>
			<fmt:formatDate value="${reim.reimTime}" pattern="yyyy-MM-dd HH:mm"/>
		</div>
		<div class="col-sm-4 col-sm-offset-1">
			<c:if test="${reim.endstatus==1}">
	    		<span class="label label-primary">审核中</span>
	    	</c:if>
	    	<c:if test="${reim.endstatus==2}">
	    		<span class="label label-success">已批准</span>
	    	</c:if>
	    	<c:if test="${reim.endstatus==3}">
	    		<span class="label label-danger">未通过</span>
	    	</c:if>
	    	<c:if test="${reim.endstatus==4}">
	    		<span class="label label-info">已报销</span>
	    	</c:if>
	    	<c:if test="${reim.endstatus==5}">
	    		<span class="label label-warning">待报销</span>
	    	</c:if>
		</div>
	</div>
	<div class="row"><div class="col-xs-3"><h6 class="text-center">审批流程日志</h6></div><div class="col-xs-9"><hr></div></div>
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>执行人</th>
				<th width=40%>审批意见</th>
				<th>操作结果</th>
				<th>执行时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${logList}" var="log" varStatus="sta">
				<tr>
					<td>${sta.count }</td>
					<td>${log.username }</td>
					<td>${log.opinion }</td>
					<td class="text-center">
					<c:if test="${log.action==1}">
						<span class="label label-primary">待审</span>
					</c:if>
					<c:if test="${log.action==2}">
						<span class="label label-success">通过</span>
					</c:if>
					<c:if test="${log.action==3}">
						<span class="label label-danger">拒绝</span>
					</c:if>
					<c:if test="${log.action==4}">
						<span class="label label-info">已付</span>
					</c:if>
					</td>
					<td>
						<fmt:formatDate value="${log.logtime}" pattern="yyyy-MM-dd HH:mm"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>