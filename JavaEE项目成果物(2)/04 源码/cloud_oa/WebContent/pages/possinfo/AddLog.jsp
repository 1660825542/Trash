<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.possession.pojo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加消耗记录</title>
<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popwin.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function(e) {
		//选择资产模态窗口
		$('#selectBtn').click(function(){			
			popWin.showWin("600","550","选择资产","<%=request.getContextPath()%>/house/querySmall.action");	
		
		});
		
		$('#selectBtn2').click(function(){			
			popWin.showWin("600","550","选择员工","<%=request.getContextPath()%>/poss/queryEmpSmall.action");	
		
		});
		//日历框
		$('#borDate').datetimepicker({		
			lang:'ch',
			timepicker:false,
			format:'Y-m-d',
		    maxDate:0
		
		});
		$('#form7').Validform({
			tiptype:3
		});
		
		$('#empId').change(function(){
			$.get("<%=request.getContextPath()%>/poss/getEmpName.action",{empId:$('#empId').val()},
					function(data){
						$('#empName').val(data);
					}
			);
		
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
		<label for="toggle1"class="animate">资产管理<i class="fa fa-bars float-right"></i></label>
		<ul class="animate">
			<a href="<%=request.getContextPath()%>/house/query.action">
				<li class="animate">库存管理</li>
			</a>
            <a href="<%=request.getContextPath()%>/poss/query.action">
            	<li class="animate actived">办公用品消耗</li>
            </a>	              
		</ul>
	</dropdown>
</div>

	<!-- 左侧边栏完成 -->

	<!-- 右侧内容-->
	<div class="col-md-7">
		<h3 class="page-header">增加消耗记录</h3>

		<form id="form7" name="from7" class="form-horizontal" method="post" action="<%=request.getContextPath()%>
			/log/insert.action">
			<div class="form-group">
				<label   for="possId3" class="col-sm-2 control-label float-left">资产编号</label>

				<div class="col-sm-8">

					<input name="possId" id="possId3" readonly type="text" class="form-control col-sm-5"  placeholder="请单击右侧按钮选择资产"></div>
				<input name="button" id="selectBtn" value="选择资产" class="btn btn-primary" type="button"></div>

			<div class="form-group">
				<label for="possName3" class="col-sm-2 control-label">资产名称</label>
				<div class="col-sm-8">
					<input name="possName" id="possName3" datatype="*" readonly type="text" class="form-control" placeholder="请单击右侧按钮选择资产" ></div>
			</div>
			<div class="form-group">
				<label for="possCate3" class="col-sm-2 control-label">资产类别</label>
				<div class="col-sm-8">
					<input name="possCate" id="possCate3" readonly datatype="*" type="text" class="form-control" placeholder="请单击右侧按钮选择资产" ></div>
			</div>
			<div class="form-group">
				<label for="empId" class="col-sm-2 control-label">员工编号</label>

				<div class="col-sm-8">
					<input datatype="*"  name="empId" id="empId" readonly type="text" class="form-control" placeholder="请单击右侧按钮选择员工"></div>
				<input name="button" id="selectBtn2" value="选择员工" class="btn btn-primary" type="button"></div>
			<div class="form-group">
				<label for="empName" class="col-sm-2 control-label">员工名字</label>
				<div class="col-sm-8">
					<input datatype="*"   name="empName" id="empName" readonly  type="text" class="form-control" placeholder="请单击右侧按钮选择员工"></div>
			</div>

			<div class="form-group">
				<label for="borNum" class="col-sm-2 control-label">消耗数量</label>
				<div class="col-sm-8">
					<input datatype="n1-10" errormsg="请输入正确的数量"  readonly ajaxurl="<%=request.getContextPath()%>
					/poss/queryIsExist.action?possId="  name="borNum" id="borNum" type="text" class="form-control" >
				</div>
			</div>

			<div class="form-group">
				<label for="borDate"  class="col-sm-2 control-label ">消耗日期</label>
				<div class="col-sm-8">

					<input datatype="*" readonly type="text" name="borDate" id="borDate" class="form-control" placeholder="请选择日期"></div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label ">详述</label>
				<div class="col-sm-8">
					<textarea rows="4"  name="borDes" id="borDes" class="form-control"></textarea>
				</div>

			</div>

			<div class="col-sm-4 col-sm-offset-6">
				<input type="submit" name="button" id="insertBtn"  class="btn btn-primary col-sm-4 btn-block" value="增加新记录" >
				<input type="button" class="btn btn-success col-sm-4 btn-block" value="返回上一页" onclick="history.back();">
				
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