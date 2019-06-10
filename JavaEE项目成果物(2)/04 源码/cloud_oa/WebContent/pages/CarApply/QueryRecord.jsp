<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
	<title>车辆安排记录</title>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">

	</script>
	</head>

<body>

	<!-- 网页头部-->

	<jsp:include page="/include/header.jsp"></jsp:include>

	<!-- 每个模块的部分 -->	

	<div class="container">

	<!-- 左侧边栏 -->
	<div class="col-md-3" id="myScrollSpy" style="margin-top:30px;">
        <dropdown class="col-xs-12" style="margin-bottom:30px;"> 
		<input id="toggle1" type="checkbox" checked>
		<label for="toggle1"class="animate">车辆信息<i class="fa fa-bars float-right"></i></label>
		<ul class="animate">
			<a href="<%=request.getContextPath()%>/car/query.action">
				<li class="animate">车辆查看<i class="fa fa-code float-right"></i></li>
			</a>
			<a href="<%=request.getContextPath()%>/processcar/queryUserAllot.action">
				<li class="animate">派车待办<i class="fa fa-code float-right"></i></li>
			</a>
            <a href="<%=request.getContextPath()%>/carrecord/query.action">
            	<li class="animate actived">派车记录<i class="fa fa-code float-right"></i></li>
            </a>
		</ul>
	</dropdown>
	</div>
	<!-- 左侧边栏完成 -->

		<!-- 右侧内容-->	
		<div class="col-md-9">
			<h3 class="page-header">车辆安排记录</h3>

			<table  class="table table-striped table-bordered table-condensed table-hover">
				<thead>
					<tr>
						<th>员工编号</th>
						<th>员工姓名</th>
						<th>车辆编号</th>
						<th>路线</th>
						<th>开始时间</th>
						<th>结束时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="record">
						<tr>
							<td>${record.empId }</td>
							<td>${record.empName }</td>
							<td>${record.carId }</td>
							<td>${record.route }</td>
							<td><fmt:formatDate value="${record.startTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
							<td><fmt:formatDate value="${record.endTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		<div class="col-sm-offset-8">
			<form id="exportexcel" method="post" action="<%=request.getContextPath() %>/carrecord/exportExcel.action">
				<input type="submit" class="btn btn-success btn-block" value="导出EXCEL报表">
				<!-- 响应式布局，移动端才会有返回主页的按钮 -->
				<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
				<!-- / -->
			</form>
		</div>
		<!-- 包含分页 -->	
		<jsp:include page="/include/AdvancedPager.jsp"/>	
		</div>
		<!--右侧内容完 -->	

	</div>

	<!--网页底部-->	
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->	
	</body>

</html>