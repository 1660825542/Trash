<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html>
<head>
	<title>车辆派遣</title>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">

	<script type="text/javascript">		
		var startTime;
		var endTime;
		
		$(document).ready(function(e){		
			
			startTime = $('#getStart').text();
			endTime = $('#getEnd').text();
			
			$('#startTime').val(startTime);
			$('#endTime').val(endTime);
			
			$('#carapply').submit(function(){
				var url = '<%=request.getContextPath() %>/carrecord/insert.action';			
				$.post(url,{carapplyId:$('#carapplyId').val(),carId:$('#carId').val()});
			});
			
		});

		function selectCar() {
			layer.open({
				type: 2,
				title: '选择车辆',
				maxmin: false,
				shadeClose: true, //点击遮罩关闭层
				area : ['600px' , '580px'],
				content: ['<%=request.getContextPath()%>/carrecord/selectCar.action?startTime=' + startTime + '& endTime=' + endTime],
				skin: 'layui-layer-lan',
				shift: 5, //动画类型
				shade: 0.5,
				closeBtn:2
			});
		}
	  	
	</script>
<title>请假待办</title>
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
            	<li class="animate">派车记录<i class="fa fa-code float-right"></i></li>
            </a>
		</ul>
	</dropdown>
	</div>
	<!-- 左侧边栏完成 -->
         
         <!-- 右侧内容-->
			<div class="col-md-9">
			
				<h3 class="page-header">
					批准用车
				</h3>
		<form id="carapply" class="form-horizontal" method="post" action="<%=request.getContextPath()%>/processcar/handleTask.action">	
				<input type="hidden" value="${carapply.carapplyId }" readonly="readonly" datatype="*" name="carapplyId" id="carapplyId" class="form-control">

				<div class="form-group">
					<label for="stuBirthdate" class="col-sm-2 control-label">员工姓名</label>
					<div class="col-sm-10">
						<input datatype="*" value="${carapply.empName }" readonly="readonly" type="text" name="empName" id="empName" class="form-control">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">用车路线</label>
					<div class="col-sm-10">
						<input value="${carapply.route }" datatype="*" type="text" name="route" id="route" class="form-control" readonly="readonly">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">用车原因</label>
					<div class="col-sm-10">
						<input value="${carapply.reason }" datatype="*" type="text" name="reason" id="reason" class="form-control" readonly="readonly">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">选择车辆</label>
					<div class="col-sm-10">
						<input onclick="selectCar();" value="" datatype="*" type="text" name="carId" id="carId" class="form-control" readonly="readonly">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">申请时间</label>
					<div class="col-sm-10">
					
						<input type="hidden" name="startTime" id="startTime">
						<input type="hidden" name="endTime" id="endTime">
						
						<span id="getStart"><fmt:formatDate value="${carapply.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						~
						<span id="getEnd"><fmt:formatDate value="${carapply.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					
					</div>
				</div>
				<input type="hidden" name="action" id="action" value="4"> 
				<input type="hidden" name="recordid" id="recordid" value="${param.recordid}"> 
				<input type="hidden" name="taskid" id="taskid" value="${param.taskid}">
				<input type="hidden" name="procinsid" id="procinsid" value="${param.procinsid}"> 
				
				<div class="form-group">
					<div class="col-sm-10 col-sm-offset-2">
						<input type="submit" class="btn btn-primary btn-block" id="transmit" value="确定派遣"></div>
				</div>
			</form>
                
		     </div>  
		 <!--右侧内容完 -->
	</div>
	
	<!--图片显示界面模态层-->
	<div class="modal fade" id="PhotoShow" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" id="clostPhoto" data-dismiss="modal">&times;</button>
					<h4 id="carTypeName" class="text-center">选择车辆</h4>
				</div>

				<div class="modal-body">
					<div id="CarInfo">
					</div>
				</div>


			</div>
		</div>
	</div>
	<!--/图片界面模态层-->

	<!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>

</html>