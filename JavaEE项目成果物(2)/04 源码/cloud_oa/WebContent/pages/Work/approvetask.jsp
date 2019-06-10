<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<%@ page import="com.icss.oa.system.pojo.*" %>
<!doctype html>
<html>
<head>
<title>审批待办</title>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">	
</head>

<body>

	<!-- 网页头部-->
	<jsp:include page="/include/header.jsp"></jsp:include>
	<!-- 网页头部完成-->
	
	<!-- 每个模块的部分 -->
	<div class="container">
	
	<!-- 左侧边栏 -->     
        <div class="col-md-3" id="myScrollSpy" style="margin-top:30px;">
        	<dropdown class="col-xs-12" style="margin-bottom:30px;"> 
				<input id="toggle1" type="checkbox" checked>
				<label for="toggle1"class="animate">工作安排<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/pages/Work/task.jsp">
						<li class="animate">日程安排<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/work/queryAll.action">
						<li class="animate">综合查询<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath()%>/pages/Work/calendar.jsp">
	                	<li class="animate">日历查看<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/pages/Work/agent.jsp">
	                	<li class="animate">代读设置<i class="fa fa-code float-right"></i></li>
	                </a>
	                <shiro:hasAnyRoles name="superadmin,caradmin,deptadmin,manager,money,adminstration">
	                <a href="<%=request.getContextPath()%>/pages/Work/approvetask.jsp">
	                	<li class="animate actived">待办审批<i class="fa fa-cog float-right"></i></li>
	                </a>
	                </shiro:hasAnyRoles>
				</ul>
			</dropdown>
		</div>                
    <!-- 左侧边栏完成 -->

         <!-- 右侧内容-->
            <div class="col-md-9">
	            <h3 class="page-header">
					审批待办中心
				</h3>
				<div class="text-center">
				<shiro:hasAnyRoles name="superadmin,deptadmin,manager">
					<div class="col-xs-12 col-md-4">
						<div>
							<img src="<%=request.getContextPath()%>/image/at1.png" class="img-rounded">
						</div>
						<p>
							<a class="btn btn-default btn-lg" style="margin-top:15px" href="<%=request.getContextPath()%>/processleave/queryUserTask.action" role="button">请假申请审批 &raquo;</a>
						</p>
					</div>
					</shiro:hasAnyRoles>
					<shiro:hasAnyRoles name="superadmin,deptadmin,manager,money">
					<div class="col-xs-12 col-md-4">
						<img src="<%=request.getContextPath()%>/image/at2.png" class="img-rounded">
						<p>
							<a class="btn btn-default btn-lg" style="margin-top:15px" href="<%=request.getContextPath()%>/processreim/queryUserTask.action" role="button">报销申请审批 &raquo;</a>
						</p>
					</div>
					</shiro:hasAnyRoles>
					<shiro:hasAnyRoles name="superadmin,adminstration">
					<div class="col-xs-12 col-md-4">
						<img src="<%=request.getContextPath()%>/image/at3.png" class="img-rounded">
						<p>
							<a class="btn btn-default btn-lg" style="margin-top:15px" href="<%=request.getContextPath()%>/processcar/queryUserTask.action" role="button">用车申请审批 &raquo;</a>
						</p>
					</div>
					</shiro:hasAnyRoles>
				</div>
            </div>
	<!--右侧内容完 -->
	</div>
	
	<!--网页底部-->
	<footer>
		<jsp:include page="/include/footer.jsp"></jsp:include>
	</footer>
	<!--网页底部结束-->
</body>

</html>