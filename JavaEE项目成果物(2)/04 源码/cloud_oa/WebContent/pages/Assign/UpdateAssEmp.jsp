<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>修改员工信息</title>
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

	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
	$(document).ready(function(e) {
        
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#form1').Validform({
			tiptype:3
		});
		
    });
	
	$(document).ready(function(e) {
		var selectedState = $('#getState').val();
		if(selectedState=="闲置中"){$("#radio1").attr("checked","checked");}
		if(selectedState=="外派中"){$("#radio2").attr("checked","checked");}
		if(selectedState=="已离职"){$("#radio3").attr("checked","checked");}
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
		<label for="toggle1"class="animate">外派人才库<i class="fa fa-bars float-right"></i></label>
		<ul class="animate">
			<a href="<%=request.getContextPath() %>/assemp/query.action">
				<li class="animate">外派人员列表<i class="fa fa-code float-right"></i></li>
			</a>
			<a href="<%=request.getContextPath() %>/asscom/query.action">
				<li class="animate">合作公司列表<i class="fa fa-code float-right"></i></li>
			</a>
            <a href="<%=request.getContextPath() %>/comreq/query.action">
            	<li class="animate">公司需求列表<i class="fa fa-code float-right"></i></li>
            </a>
            <a href="<%=request.getContextPath() %>/empcom/query.action">
            	<li class="animate">外派记录显示<i class="fa fa-code float-right"></i></li>
            </a>
		</ul>
	</dropdown>
	</div>
	<!-- 左侧边栏完成 -->

	<!-- 右侧内容-->
	<div class="col-md-7">
		<h3 class="page-header">修改个人信息</h3>

		<form id="form1" class="form-horizontal" method="post" enctype="multipart/form-data"
		    	 action="<%=request.getContextPath() %>
			/assemp/update.action?pageNum=${param.pageNum}">
			<div class="form-group">
				<label class="col-sm-2 control-label float-left">员工编号</label>
				<div class="col-sm-10">
					<input type="text"class="form-control col-sm-5" datatype="*" name="assemp.assId" id="assId" value="${assemp.assId }" readonly="readonly"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label float-left">员工姓名</label>
				<div class="col-sm-10">
					<input type="text" value="${assemp.assName }" datatype="*" name="assemp.assName" id="assName" class="form-control col-sm-5"readonly="readonly"></div>
			</div>

			<div class="form-group">
				<label for="stuBirthdate" class="col-sm-2 control-label">教育经历</label>
				<div class="col-sm-10">
					<input value="${assemp.education }" type="text" datatype="*" class="form-control" name="assemp.education " id="education"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">工作经验</label>
				<div class="col-sm-10">
					<input type="text" value="${assemp.workExp }" datatype="*" class="form-control" name="assemp.workExp " id="workExp"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label ">技能特长</label>
				<div class="col-sm-10">
					<input type="text" value="${assemp.skill }" datatype="*" class="form-control" name="assemp.skill " id="skill" ></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label ">户口所在</label>
				<div class="col-sm-10">
					<input type="text" value="${assemp.residentCity }" datatype="*" class="form-control" name="assemp.residentCity " id="residentCity" ></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label ">工资</label>
				<div class="col-sm-10">
					<input type="text" value="${assemp.salary }" datatype="n" class="form-control" name="assemp.salary " id="salary"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label ">上传文件</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" name="data" id="data">
				</div>
			</div>
			
			<input type="hidden" id="getState" value="${assemp.available }">
				
			<div class="form-group" style="margin-top:15px">
				<label class="col-sm-2 control-label">外派状态</label>
				<div class="radio col-sm-10" id="state">
					<label for="radio1" class="radio-inline">
						<input type="radio" id="radio1" name="assemp.available" value=" 闲置中">闲置中</label>

					<label for="radio2" class="radio-inline">
						<input type="radio" id="radio2" name="assemp.available" value="外派中">外派中</label>

					<label for="radio3" class="radio-inline">
						<input type="radio" id="radio3" name="assemp.available" value="已离职">已离职</label>
				</div>
			</div>

			<div class="col-sm-offset-8">
				<input type="submit" class="btn btn-primary col-sm-4 btn-block" value="确定修改">
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