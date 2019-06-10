<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>修改公司信息</title>
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

	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
	$(document).ready(function(e) {
        
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#form1').Validform({
			tiptype:1
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
		<h3 class="page-header">合作公司详情</h3>

		<form id="form1" class="form-horizontal" method="post" action="<%=request.getContextPath() %>
			/asscom/update.action?pageNum=${param.pageNum}">
			<div class="form-group">
				<label class="col-sm-2 control-label float-left">公司编号</label>
				<div class="col-sm-10">
					<input type="text" value="${asscom.assComId }" name="assComId" id="assComId" class="form-control col-sm-5"readonly="readonly"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label float-left">公司名称</label>
				<div class="col-sm-10">
					<input type="text"class="form-control col-sm-5" name="comName" id="comName" value="${asscom.comName}" readonly="readonly"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label float-left">联系人姓名</label>
				<div class="col-sm-10">
					<input type="text" value="${asscom.contactName }" name="contactName" id="contactName" class="form-control col-sm-5"readonly="readonly"></div>
			</div>

			<div class="form-group">
				<label for="stuBirthdate" class="col-sm-2 control-label">联系人电话</label>
				<div class="col-sm-10">
					<input value="${asscom.contactPhone}" datatype="/1[345678]\d{9}/" type="text" class="form-control" name="contactPhone" id="contactPhone" placeholder="请输入电话"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">联系人邮箱</label>
				<div class="col-sm-10">
					<input type="text" value="${asscom.contactQq }" datatype="e" class="form-control" name="contactQq" id="contactQq" placeholder="联系人邮箱"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label ">备注</label>
				<div class="col-sm-10">
					<input type="text" value="${asscom.remarks}" class="form-control" name="remarks " id="remarks" placeholder="这里是备注"></div>
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