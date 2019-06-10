<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
	
	//事件处理
	$('#myTab a[data-toggle="tab"').on('shown.bs.tab',function(e){
		if (e.target.id == 'arrange1') {			
			document.getElementById('insert').contentWindow.location.reload();
		}
		if (e.target.id == 'editTask1') {			
			document.getElementById('edit').contentWindow.location.reload();
		}
	});
	
});
</script>
<title>日程安排</title>
</head>
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
						<li class="animate actived">日程安排<i class="fa fa-code float-right"></i></li>
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
					日程安排
				</h3>
	    	<!-- 选择 -->
	      <ul id="myTab" class="nav nav-tabs">
	        <li class="span active col-md-6"><a href="#arrange" id="arrange1" data-toggle="tab" style="font-size:18px;" align="center">安排工作</a></li>
	        <li class="span col-md-6"><a href="#editTask" id="editTask1" data-toggle="tab" style="font-size:18px;" align="center">编辑工作</a></li> 
	      </ul>
	      <br>	
	     <div class="tab-content" >
		    <div class="tab-pane active" id="arrange" target="content" >      		 
			      <iframe id="insert" name="content" src="<%=request.getContextPath()%>/work/arrange.action" width="100%" height="650" frameborder="0">				      
			      </iframe>					                 
			</div>
			<div class="tab-pane" id="editTask" target="content2">					
			   	<iframe id="edit" name="content2" src="<%=request.getContextPath()%>/work/editMy.action" width="100%" height="650" frameborder="0">				      
	      		</iframe>
			</div>
		</div>
		    	
	</div>
	<!--右侧内容完 -->
		
    </div>
    
    <!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
<body>
</body>
</html>
