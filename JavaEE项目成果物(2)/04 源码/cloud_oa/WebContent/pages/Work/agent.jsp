<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.system.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
<script>
$(document).ready(function(e) {
    
	//$('#myTab a[href="#java"').tab('show');
	//$('#myTab a:last').tab('show');
	//$('#myTab a:first').tab('show');
	//$('#myTab a:eq(3)').tab('show');
	
	//事件处理
	$('#myTab a[data-toggle="tab"').on('shown.bs.tab',function(e){
		if (e.target.id == 'agentmanage1') {			
			document.getElementById('selectManager').contentWindow.location.reload();
		}
		if (e.target.id == 'organization1') {			
			document.getElementById('queryAll').contentWindow.location.reload();
		}
		if (e.target.id == 'agentview1') {			
			document.getElementById('selectEmp').contentWindow.location.reload();
		}
	});
	
});
</script>
<title>代读设置</title>
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
	                	<li class="animate  actived">代读设置<i class="fa fa-code float-right"></i></li>
	                </a>
	                <shiro:hasAnyRoles name="superadmin,caradmin,deptadmin,manager,money,adminstration">
	                <a href="<%=request.getContextPath()%>/pages/Work/approvetask.jsp">
	                	<li class="animate">待办审批<i class="fa fa-cog float-right"></i></li>
	                </a>
	                </shiro:hasAnyRoles>
				</ul>
			</dropdown>
		</div>             
    <!-- 左侧边栏完成 -->
    
     <!-- 右侧内容-->
     <div class="col-md-9">
            <h3 class="page-header">
				代读设置
			</h3>
	    <!-- 选择 -->
	      <ul id="myTab" class="nav nav-tabs">
	        <li class="span active col-md-4"><a id="organization1" href="#organization" data-toggle="tab" style="font-size:18px;" align="center">组织机构</a></li>
	        <li class="span col-md-4"><a id="agentmanage1" href="#agentmanage" data-toggle="tab" style="font-size:18px;" align="center">代读管理</a></li> 
	        <li class="span col-md-4"><a id="agentview1" href="#agentview" data-toggle="tab" style="font-size:18px;" align="center">代读查看</a></li>   
	      </ul>
	      <br>	
	      <div class="tab-content" >
		    <div class="tab-pane active" id="organization" target="content" >
		    	<div class="col-md-3" style="min-height:300px">			       						   
			      	<iframe src="<%=request.getContextPath()%>/agent/initial.action" width="100%" height="650" frameborder="0">				      
	      			</iframe>	
	      		</div>
	      		<div class="col-md-9"  style="min-height:300px"> 
				      <iframe id="queryAll" name="content" src="<%=request.getContextPath()%>/agent/queryAll.action" width="100%" height="650" frameborder="0">				      
				      </iframe>					                 
			    </div>
			</div>
			<div class="tab-pane" id="agentmanage" target="content2">
			   	<iframe id="selectManager" name="content2" src="<%=request.getContextPath()%>/agent/selectManager.action" width="100%" height="650" frameborder="0">				      
	      		</iframe>
			</div>
			<div class="tab-pane" id="agentview" target="content3">
		   		<iframe id="selectEmp" name="content3" src="<%=request.getContextPath()%>/agent/selectEmp.action" width="100%" height="650" frameborder="0">				      
	      		</iframe>
			</div>
		</div>   
	</div>
	<!--右侧内容完 -->
    
    </div>
    <!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>
</html>