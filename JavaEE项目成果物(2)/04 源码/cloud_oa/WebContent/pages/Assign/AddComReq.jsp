<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<!doctype html>
<html>
<head>
<title>添加公司需求</title>
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
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
	$(document).ready(function(e) {
		$('#insert').Validform({
			tiptype:3
		});
		
		$('#insert').submit(function(){
			if ($('#comName').val() == '') {
				layer.msg("请选择公司",{icon: 2});
				return false;
			}			
		});
	});
	
	function selectCom() {
		layer.open({
			type: 2,
			title: '选择对应公司',
			maxmin: false,
			shadeClose: true, //点击遮罩关闭层
			area : ['700px' , '500px'],
			content: ['<%=request.getContextPath()%>/asscom/selectCom.action?','no'],
			skin: 'layui-layer-lan',
			shift: 5, //动画类型
			shade: 0.5,
			closeBtn:2
		});
	}
	
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
		<h3 class="page-header">新增新的职位需求</h3>

		<form id="insert" class="form-horizontal" method="post" action="<%=request.getContextPath() %>
			/comreq/insert.action?pageNum=${param.pageNum}">
			
			<input type="hidden" class="form-control" name="assComId " id="assComId">

			<div class="form-group" style="margin-bottom:40px">
				<label class="col-sm-2 control-label">公司名称</label>
				<div class="col-sm-10">
					<input placeholder="公司名称" onclick="selectCom()" readonly="readonly" type="text" class="form-control" name="comName " id="comName">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">职位需求</label>
				<div class="col-sm-10">
					<input datatype="*" errormsg="不可为空" type="text" class="form-control" name="posReq " id="posReq" placeholder="职位需求">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">工作类型</label>
				<div class="col-sm-10">
					<input datatype="*" errormsg="不可为空" type="text" class="form-control" name="typeOfWork " id="typeOfWork" placeholder="工作类型">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">每月工资</label>
				<div class="col-sm-10">
					<input datatype="n" errormsg="请输入数字" type="text" class="form-control" name="salaryPermonth " id="salaryPermonth" placeholder="每月工资">
				</div>
			</div>

			<div class="col-sm-offset-8">
				<input type="submit" id="sub" class="btn btn-primary col-sm-4 btn-block" value="确定添加">
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
<jsp:include page="/include/footer.jsp"></jsp:include>
<!--网页底部结束-->
</body>
</html>