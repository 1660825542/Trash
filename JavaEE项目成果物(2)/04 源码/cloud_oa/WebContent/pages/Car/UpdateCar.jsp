<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<title>修改车辆信息</title>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
	$(document).ready(function(e) {
        
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#updateCar').Validform({
			tiptype:3
		});
		
    });
	</script>
	
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
	<div class="col-md-7">
		<h3 class="page-header">添加个人信息</h3>

		<form id="updateCar" class="form-horizontal" method="post" enctype="multipart/form-data"
		action="<%=request.getContextPath() %>/car/update.action?pageNum=${param.pageNum}">
			<div class="form-group">
				<label class="col-sm-2 control-label float-left">车辆编号</label>
				<div class="col-sm-10">
					<input type="text" datatype="*" class="form-control col-sm-5" name="carId" id="carId" value="${car.carId }" readonly="readonly"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label float-left">车辆类型</label>
				<div class="col-sm-10">
					<input type="text" datatype="*" value="${car.carType }" name="carType" id="carType" class="form-control col-sm-5"></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label float-left">车辆牌照</label>
				<div class="col-sm-10">
					<input type="text" datatype="*" value="${car.license }" name="license" id="license" class="form-control col-sm-5"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label ">修改图片</label>
				<div class="col-sm-10">
					<input type="file"  class="form-control" name="data" id="data"></div>
			</div>

			<div class="col-sm-offset-8">
				<input type="submit" class="btn btn-primary col-sm-4 btn-block" value="确定添加">
				<input type="button" class="btn btn-success col-sm-4 btn-block" value="返回上一页" onclick="history.back();">
				<!-- 响应式布局，移动端才会有返回主页的按钮 -->
				<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
				<!-- / -->
			</div>
		</form>
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