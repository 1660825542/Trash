<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.system.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/work.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">


<title>部门员工</title>
</head>
<body>
	<c:if test="${list.size()>0 }">
	<table class="table table-hover table-striped">
     	<thead>
	     	<tr style="font-size:14px;">
	     		<th style="text-align:center">姓名</th>
	     		<th style="text-align:center">部门</th>
	     		<th style="text-align:center">职位</th>
	     		<th style="text-align:center">操作</th>
	     	</tr>
     	</thead>
          <c:forEach items="${list }" var="emp">
            <tr style="font-size:14px;" align="center">
              <td>${emp.EMP_NAME}</td>
              <td>${emp.DEPT_NAME}</td>
              <td>${emp.POS_NAME}</td>
              <td>
              	<div class="dropdown">
				<button type="button" class="btn btn-default btn-xs dropdown-toggle" id="dropdownMenu" data-toggle="dropdown">
					操作
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li>
						<a href="<%=request.getContextPath()%>/agent/setAgent.action?empId=${emp.EMP_ID }&departmentId=${emp.DEPT_ID }&state=0">设置为读者</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/agent/setAgent.action?empId=${emp.EMP_ID }&departmentId=${emp.DEPT_ID }&state=1">设置为代办</a>
					</li>
				</ul>
			</div>				          	
              </td>
            </tr>
          </c:forEach> 
   </table>
   <nav class="text-center">
		<ul class="pagination">
	
			<c:if test="${pager.pageNum==1 }">
				<li class="disabled"><a href="#">上页</a></li>
				<li class="active"><a href="#">1</a></li>
			</c:if>
			
			<c:if test="${pager.pageNum!=1 }">
				<li><a href="?pageNum=${pager.pageNum-1}&departmentId=${list[0].DEPT_ID}">上页</a></li>
				<li><a href="?pageNum=1&departmentId=${list[0].DEPT_ID}">1</a></li>
			</c:if>
			
			<!-- 总页数在10页以内 -->
			<c:if test="${pager.pageCount<=10 }">
			
				<c:if test="${pager.pageCount>1 }">
				
					<c:forEach var="i" begin="2" end="${pager.pageCount }">
						<c:if test="${pager.pageNum==i }">
							<li class="active"><a href="#">${i}</a></li>
						</c:if>
						<c:if test="${pager.pageNum!=i }">
							<li><a href="?pageNum=${i}&departmentId=${list[0].DEPT_ID}">${i}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
				
				<c:if test="${pager.pageNum<pager.pageCount}">
					<li><a href="?pageNum=${pager.pageNum+1}&departmentId=${list[0].DEPT_ID}">下页</a></li>
				</c:if>
				
				<c:if test="${pager.pageNum>=pager.pageCount}">
					<li class="disabled"><a href="#">下页</a></li>
				</c:if>
				
			</c:if>
			
			
			<!-- 总页数大于10页以 -->
			<c:if test="${pager.pageCount>10 }">
			
				<!-- 两侧均省略 -->
				<c:if test="${pager.pageNum>5 && pager.pageNum<pager.pageCount-4 }">
					<li><a href="#">...</a></li>
				
					<c:forEach var="i" begin="${pager.pageNum-2 }" end="${pager.pageNum+2 }">
						<c:if test="${pager.pageNum==i }">
							<li class="active"><a href="#">${i}</a></li>
						</c:if>
						<c:if test="${pager.pageNum!=i }">
							<li><a href="?pageNum=${i}&departmentId=${list[0].DEPT_ID}">${i}</a></li>
						</c:if>
					</c:forEach>
					
					<li><a href="#">...</a></li>								
				</c:if>
				
				<!-- 省略右侧 -->
				<c:if test="${pager.pageNum<=5}">
					<c:forEach var="i" begin="2" end="7">
						<c:if test="${pager.pageNum==i }">
							<li class="active"><a href="#">${i}</a></li>
						</c:if>
						<c:if test="${pager.pageNum!=i }">
							<li><a href="?pageNum=${i}&departmentId=${list[0].DEPT_ID}">${i}</a></li>
						</c:if>
					</c:forEach>
					
					<li><a href="#">...</a></li>								
				</c:if>
				
				<!-- 省略左侧 -->
				<c:if test="${pager.pageNum>=pager.pageCount-4}">
				
					<li><a href="#">...</a></li>								
				
					<c:forEach var="i" begin="${pager.pageCount-7 }" end="${pager.pageCount-1 }">
						<c:if test="${pager.pageNum==i }">
							<li class="active"><a href="#">${i}</a></li>
						</c:if>
						<c:if test="${pager.pageNum!=i }">
							<li><a href="?pageNum=${i}&departmentId=${list[0].DEPT_ID}">${i}</a></li>
						</c:if>
					</c:forEach>
					
				</c:if>
				
				<c:if test="${pager.pageNum<pager.pageCount}">
					<li><a href="?pageNum=${pager.pageCount }&departmentId=${list[0].DEPT_ID}">${pager.pageCount }</a></li>
					<li><a href="?pageNum=${pager.pageNum+1}&departmentId=${list[0].DEPT_ID}">下页</a></li>
				</c:if>
				<c:if test="${pager.pageNum>=pager.pageCount}">
					<li class="active"><a href="#">${pager.pageCount }</a></li>
					<li class="disabled"><a href="#">下页</a></li>
				</c:if>
			</c:if>
			<li class="disabled"><a href="#">共${pager.pageCount }页</a></li>
		</ul>
	</nav>
	</c:if>
	
	<c:if test="${list.size()<=0 }">
        <div class="alert alert-info" role="alert">
      		当前部门没有员工
      	</div>
    </c:if>
</body>
</html>