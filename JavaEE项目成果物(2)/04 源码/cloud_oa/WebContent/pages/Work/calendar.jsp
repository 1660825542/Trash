<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no">
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/js/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/js/fullcalendar/fullcalendar.print.css" rel="stylesheet" type="text/css" media="print" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/fullcalendar/moment.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/fullcalendar/fullcalendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
<script>

	$(document).ready(function() {
		var myDate = new Date();
		$('#calendar').fullCalendar({
			header: {			
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaDay'
			},
			monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
			buttonText:{
				month:"每月安排",
				agendaDay:"每日安排",
				today:"今日安排",
			},
			titleFormat:{
			    month: 'YYYY'+'年MMMM',                           
			    day: 'YYYY'+'年MMMM'+'DD日'               
			},
			minTime:'6:00',
			maxTime:'20:00',
			defaultDate: myDate,
			editable: true,
			eventLimit: true,
			timeFormat: 'HH:mm',
			axisFormat: 'H:mm',
			events: {
                url: "<%=request.getContextPath()%>/work/query.action",
                    type: 'POST'              
                }			
		});		
	});

</script>
<style>

	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}

</style>
<title>日历查看</title>
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
	                	<li class="animate actived">日历查看<i class="fa fa-code float-right"></i></li>
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
			日历查看
		</h3>
	    <div id='calendar'></div>
		    	
	</div>
	<!--右侧内容完 -->
    
    <form>
    <input type="hidden" id="recJson">
    </form>
    
    
    </div>
    <!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>
</html>