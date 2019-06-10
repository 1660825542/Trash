<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<link rel="stylesheet" type="text/css" href="../../js/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../../js/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" type="text/css" href="../../js/Validform/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">

	<script type="text/javascript">
	$(document).ready(function(){
		
		
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#addForm').Validform({
			tiptype:3,
			datatype:{'money':function(gets,obj,curform,regxp){
				
				//先判断是否是数字，返回值可以直接作为提示信息
				var reg1 = /^\d+(\.\d{1,2})?$/;
				
				if (!reg1.test(gets)) {
					return "请输入正确金额格式！";
				}				
				
				//在判断工资范围，返回false表示验证失败，使用标签的默认提示信息
				if (gets.length < 1 || gets.length > 10 ) {
					return false;									
				}
				
				return true;
			}
		}
		});
		
	});
	
	</script>
	<title>报销申请</title>
</head>

<body>

	<!-- 网页头部-->

	<jsp:include page="/include/header.jsp"></jsp:include>
	
	<!-- 每个模块的部分 -->
	<div class="container">
	
	<!-- 侧边栏 -->     
        <div class="col-md-3" id="myScrollSpy" style="margin-top:30px;">
	        <dropdown class="col-xs-12" style="margin-bottom:30px;"> 
				<input id="toggle1" type="checkbox" checked>
				<label for="toggle1"class="animate">个人中心<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/empInfo/query.action">
						<li class="animate">员工信息维护<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/empInfo/queryAll.action">
						<li class="animate">检索他人信息<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath()%>/pages/EmpInfo/apply.jsp">
	                	<li class="animate actived">个人申请中心<i class="fa fa-code float-right"></i></li>
	                </a>	              
				</ul>
			</dropdown>
		</div>           
         <!-- 侧边栏完成 -->
         
         <!-- 右侧内容-->
		<div class="col-md-9">
			
            <h3 class="page-header">新增报销申请</h3>	 
              
            <form id="addForm" name="form1" method="post" action="<%=request.getContextPath()%>/reim/insert.action">
   				
   				<div class="row form-group"> 
       				<label class="col-md-offset-2 col-md-2 control-label float-left">报销用途<span style="color:red">*</span></label>
       				<div class="col-md-4">
                   		<textarea class="form-control" name="reimReason" id="reimReason" placeholder="请输入报销原因" datatype="*5-300" nullmsg="请输入报销原因！" errormsg="输入有效长度为5-300字！"></textarea>
       				</div>
   				</div>
   				
   				<div class="row form-group"> 
       				<label class="col-md-offset-2 col-md-2 control-label float-left">报销金额<span style="color:red">*</span></label>
       				<div class="col-md-4">
                   		<input class="form-control" name="reimSum" id="reimSum" placeholder="请输入报销金额" datatype="money" nullmsg="请输入报销金额！" errormsg="输入长度过长！"type="number">
       				</div>
   				</div>
	   				
                <div class="row">
	               	<div class="col-md-offset-4 col-md-4">
	               		<input type="submit" class="btn btn-primary btn-block" value="提交申请" >
	                    <input type="button" class="btn btn-success btn-block" value="返回" onclick="history.back();">
	                <!-- 响应式布局，移动端才会有返回主页的按钮 -->
	                 <input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
	                <!-- / --> 			    		
	            	</div>
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