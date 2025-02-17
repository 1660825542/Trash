<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.system.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.css"/>

<script>
var index = parent.layer.getFrameIndex(window.name);

$(document).ready(function(){
	//一行代码搞定，提交表单的时候会自动触发验证程序
	$('#form').Validform({
		tiptype:3,
		datatype:{'finishTime':function(gets,obj,curform,regxp){				
				
				var start = $('#start').val();
				//在判断工资范围，返回false表示验证失败，使用标签的默认提示信息
				if (gets <= start) {
					return false;									
				}
				
				return true;
			}
		},	
		ajaxPost:true,
		callback:function(data){
			parent.layer.msg('修改成功');
			
			parent.location.reload();//刷新父窗口
			
			parent.layer.close(index);//关闭当前层
		}
	});
	
	function getDate() {
		var date =new Date();
		return date;
	}	
	
	$('#start').datetimepicker({lang:'ch',
		step:5,
		timepicker:true,
		minTime:'6:00',
		maxTime:'20:00',
		minDate:0,
		format:'Y-m-d H:i:00'
	});
	
	$('#finish').datetimepicker({lang:'ch',
		step:5,
		timepicker:true,
		minTime:'6:00',
		maxTime:'20:00',
		minDate:0,
		format:'Y-m-d H:i:00'
	});
	
});
</script>
<title>编辑</title>
</head>
<body>
	<div class="container">
	<form name="form" id="form" class="form-horizontal" method="post" action="<%=request.getContextPath()%>/work/edit.action">
		<div class="form-group"> 
             <label for="empName" class="col-sm-2 control-label">工作授予人</label>
             <div class="col-sm-4">
             	<input type="text" value="${task.MANAGER }" name="managerName" id="managerName" class="form-control" readonly="readonly">               
             </div>
              <label for="empName" class="col-sm-2 control-label">工作接受人</label>
             <div class="col-sm-4">
             	<input type="text" value="${task.EMP }" name="empName" id="empName" class="form-control" readonly="readonly">               
             </div>
		</div>
		<div class="form-group"> 
             <label for="empName" class="col-sm-2 control-label">开始时间</label>
             <div class="col-sm-4">
             	<input type="text"  datatype="*" value="<fmt:formatDate value="${task.START_TIME }" pattern="yyyy-MM-dd HH:mm"/>" name="startTime" id="start" class="form-control" placeholder="请选择开始时间">               
             </div>
             <label for="empName" class="col-sm-2 control-label">结束时间</label>
             <div class="col-sm-4">
             	<input type="text" datatype="finishTime" errormsg="结束时间不合理" value="<fmt:formatDate value="${task.FINISH_TIME }" pattern="yyyy-MM-dd HH:mm"/>" name="finishTime" id="finish" class="form-control" placeholder="请选择结束时间">               
             </div>
		</div>
		<div class="form-group"> 
             <label for="empName" class="col-sm-2 control-label">工作主题</label>
             <div class="col-sm-10">
             	<input type="text" datatype="*1-50" errormsg="最多16字" value="${task.TASK_NAME }" name="taskName" id="taskName" class="form-control" placeholder="请输入工作名">               
             </div>
		</div>
		<div class="form-group"> 
             <label for="empName" class="col-sm-2 control-label">工作内容</label>
             <div class="col-sm-10">
             	<textarea name="taskContent" datatype="*1-60" errormsg="最多60字" id="taskContent" class="form-control" cols="40" rows="4" datatype="*" nulmsg="请输入工作内容">${task.TASK_CONTENT }</textarea>                              
             </div>            
		</div >
		<br/>
		<div class="form-group" align="center">
			<div class="col-sm-10 col-sm-offset-2">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<input type="hidden" name="state" value="0">
			<input type="hidden" name="taskDate" value="<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd"/>">
			<input type="hidden" name="taskId" value="${task.TASK_ID}"> 
				<input type="submit" class="btn btn-primary btn-block" value="修改">
			</div>
		</div>
	</form>
	</div>
</body>
</html>