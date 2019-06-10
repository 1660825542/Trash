<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<!doctype html>
<html>
<head>
<title>修改派遣信息</title>
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
		$('#form1').Validform({
			tiptype:3
		});		
	});
	
	
	function selectEmp() {
		layer.open({
			type: 2,
			title: '选择外派员工',
			maxmin: false,
			shadeClose: true, //点击遮罩关闭层
			area : ['800px' , '700px'],
			content: '<%=request.getContextPath()%>/assemp/selectEmp.action?',
			skin: 'layui-layer-lan',
			shift: 5, //动画类型
			shade: 0.5,
			closeBtn:2
		});
	}
	
	function selectCom() {
		layer.open({
			type: 2,
			title: '选择对应公司',
			maxmin: false,
			shadeClose: true, //点击遮罩关闭层
			area : ['800px' , '700px'],
			content: '<%=request.getContextPath()%>/asscom/selectCom.action?',
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
		<h3 class="page-header">修改外派关系</h3>

		<form id="form1" class="form-horizontal" method="post" action="<%=request.getContextPath() %>
			/empcom/insertByEmpCom.action?pageNum=${param.pageNum}">

			<input type="hidden" class="form-control" value="${empcom.assId }" name="assId " id="assId"  placeholder="25">

			<div class="form-group" style="margin-top:10px">
				<label class="col-sm-2 control-label">员工姓名</label>
				<div class="col-sm-10">
					<input datatype="*" type="text" class="form-control" value="${empcom.assName}" onclick="selectEmp();" readonly="readonly" name="assName " id="assName" placeholder="陈鹏宇"></div>
			</div>


			<input type="hidden" class="form-control" value="${empcom.assComId}" name="assComId " id="assComId">

			<div class="form-group">
				<label class="col-sm-2 control-label">公司名称</label>
				<div class="col-sm-10">
					<input datatype="*" type="text" class="form-control" onclick="selectCom();" value="${empcom.comName}" readonly="readonly" name="comName " id="comName" placeholder="蓝翔">
				</div>
			</div>

			<script type="text/javascript">
				$(document).ready(function(e) {
					var selectedState = $('#getState').val();
					if(selectedState=="闲置中"){alert("aa");$("#radio1").attr("checked","checked");}
					if(selectedState=="外派中"){alert("bb");$("#radio2").attr("checked","checked");}
					if(selectedState=="已离职"){alert("cc");$("#radio3").attr("checked","checked");}
	     				});
		   </script>

			<div class="form-group">
				<label class="col-sm-2 control-label ">外派状态</label>
				<div class="radio col-sm-10">
					<label for="radio1" class="radio-inline">
						<input type="radio" id="radio1" name="available" value="闲置中">闲置中</label>

					<label for="radio2" class="radio-inline">
						<input type="radio" id="radio2" name="available" value="外派中" checked>外派中</label>

					<label for="radio3" class="radio-inline">
						<input type="radio" id="radio3" name="available" value="已离职">已离职</label>
				</div>
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
<jsp:include page="/include/footer.jsp"></jsp:include>
<!--网页底部结束-->
</body>

</html>