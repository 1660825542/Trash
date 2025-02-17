<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改公司需求</title>
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>

	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" rel="stylesheet" type="text/css">

<script type="text/javascript">

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	var comReqId;
	var posReq;
	var typeOfWork;
	var salaryPermonth;

	$(document).ready(function(){
		
		comReqId = $.trim($('input[name="comReqId"]').val());			
		
		$('#ComReq').submit(function(){
			posReq = $.trim($('input[name="posReq"]').val());
			typeOfWork = $.trim($('input[name="typeOfWork"]').val());
			salaryPermonth = $.trim($('input[name="salaryPermonth"]').val());
		});
		
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#ComReq').Validform({
			tiptype:1,
			ajaxPost:true,
			callback:function(data){
			    $(window.parent.document).find('#'+comReqId).next().next().text(posReq);
			    $(window.parent.document).find('#'+comReqId).next().next().next().text(typeOfWork);
			    $(window.parent.document).find('#'+comReqId).next().next().next().next().text(salaryPermonth);
			    $(window.parent.document).find('#fortest').val(salaryPermonth);
			    parent.layer.msg('修改成功', {icon: 1});
				parent.layer.close(index);
			}
		});
	});

</script>
</head>

<body>

<div class="container-fluid">

	<div class="row">

		<div class="col-md-12">

			<form id="ComReq" class="form-horizontal" method="post" action="<%=request.getContextPath() %>
				/comreq/update.action">
				<input type="hidden" value="${comreq.comReqId }" readonly="readonly" name="comReqId" id="comReqId" class="form-control">

				<div class="form-group">
					<label for="stuName" class="col-sm-2 control-label">公司编号</label>
					<div class="col-sm-10">
						<input type="text" value="${comreq.assComId }" readonly="readonly" datatype="*" name="assComId" id="assComId" class="form-control" placeholder="请输入公司编号"></div>
				</div>

				<div class="form-group">
					<label for="stuBirthdate" class="col-sm-2 control-label">公司名称</label>
					<div class="col-sm-10">
						<input datatype="*" value="${comreq.comName }" readonly="readonly" type="text" name="comName" id="comName" class="form-control" placeholder="请输入公司名称"></div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">职位需求</label>
					<div class="col-sm-10">
						<input value="${comreq.posReq}" datatype="*" type="text" name="posReq" id="posReq" class="form-control" placeholder="请输入职位需求"></div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">工作类型</label>
					<div class="col-sm-10">
						<input value="${comreq.typeOfWork }" datatype="*" type="text" name="typeOfWork" id="typeOfWork" class="form-control" placeholder="请输入工作类型"></div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">工资每月</label>
					<div class="col-sm-10">
						<input value="${comreq.salaryPermonth }" datatype="n" type="text" name="salaryPermonth" id="salaryPermonth" class="form-control" placeholder="请输入每月工资"></div>
				</div>

				<div class="form-group">
					<div class="col-sm-10 col-sm-offset-2">
						<input name="pageNum" value="${param.pageNum}" type="hidden">
						<input type="submit" class="btn btn-primary btn-block" id="transmit" value="确定添加"></div>
				</div>
			</form>

		</div>

	</div>

</div>

</body>
</html>