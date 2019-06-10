<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.meeting.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/Validform/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>


</head>

<body>

	<!-- 网页头部-->

	<jsp:include page="/include/header.jsp"></jsp:include>
	
	<!-- 每个模块的部分 -->
	<div class="container">
	
	<!-- 侧边栏 -->     
        <div class="col-md-3" id="myScrollSpy" style="margin-top:30px;">
            <dropdown class="col-xs-12" style="margin-bottom:30px;"> 
				<input id="toggle1" type="checkbox" checked>
				<label for="toggle1"class="animate">会议管理<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath() %>/room/query.action">
						<li class="animate">会议室信息<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath() %>/meeting/query.action">
						<li class="animate">会议预约情况<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath() %>/meeting/queryByEmpId.action">
	                	<li class="animate actived">我要参加的会议<i class="fa fa-code float-right"></i></li>
	                </a>
	                <shiro:hasPermission name="apply">
	                <a href="<%=request.getContextPath() %>/meeting/queryByOriginator.action">
	                	<li class="animate">我发布的会议<i class="fa fa-code float-right"></i></li>
	                </a>
	                </shiro:hasPermission>
				</ul>
			</dropdown>
		</div>                
         <!-- 侧边栏完成 -->
        <div class="col-md-7">
			<h3 class="page-header">
				会议信息列表
			</h3>


			<table  class="table table-striped table-bordered table-condensed table-hover">
	    		<thead>
				<tr>
					<th>
						会议发起人
					</th>
					<th>
						会议地点
					</th>
					<th>
						会议开始时间
					</th>
					<th>
						会议结束时间
					</th>					
					<th>
						会议主题
					</th>
					<th>
						审批状态
					</th>					
				</tr>	
				</thead>	
				<tbody>
												
				<c:forEach items="${list}" var="meeting">
					<tr>
						<td>${meeting.EMP_NAME}</td>
						<td>${meeting.ROOM_PLACE}</td>
						<td>	
							<fmt:formatDate value="${meeting.MEET_DATEBEGIN}"/>
							<fmt:formatDate value="${meeting.MEET_DATEBEGIN}" pattern=" HH:mm:ss"/> 
						</td>
						<td>	
							<fmt:formatDate value="${meeting.MEET_DATEEND}"/>
							<fmt:formatDate value="${meeting.MEET_DATEEND}" pattern=" HH:mm:ss"/> 
						</td>
						<td>${meeting.THEME}</td>
						<td>
						<c:if test="${meeting.MEET_STATE ==1}">审批成功</c:if>
						<c:if test="${meeting.MEET_STATE ==0}">审批中</c:if>
						<c:if test="${meeting.MEET_STATE ==2}">审批失败</c:if>		
						</td>
					</tr>
				</c:forEach> 
				
	             </tbody>
	    	</table>

		
	    	
	    	<div class="col-sm-offset-8">

       		<!-- 响应式布局，移动端才会有返回主页的按钮 -->
       		<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
       		<!-- / --> 	    	
	    	</div>
	    		    	
	    	<!-- 包含分页 -->
	    	<jsp:include page="/include/pager.jsp"/>
	    	
	     </div> 
		 <!--右侧内容完 -->
	</div>

	<!--网页底部-->
		<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>

</html>