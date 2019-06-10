<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>

<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$('#birthday').datetimepicker({		
			lang:'ch',
			timepicker:false,
			format:'Y-m-d'
		});
		
		$('#form').Validform({
			tiptype:3,
			ajax:true
		});
	});
	
	</script>
<title>修改信息</title>
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
				<label for="toggle1"class="animate">个人中心<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/empInfo/query.action">
						<li class="animate actived">员工信息维护<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/empInfo/queryAll.action">
						<li class="animate">检索他人信息<i class="fa fa-code float-right"></i></li>
					</a>
					<shiro:lacksRole name="manager">
	                <a href="<%=request.getContextPath()%>/pages/EmpInfo/apply.jsp">
	                	<li class="animate">个人申请中心<i class="fa fa-code float-right"></i></li>
	                </a>	              
	                </shiro:lacksRole>
				</ul>
			</dropdown>
		</div>            
    <!-- 左侧边栏完成 -->

         <!-- 右侧内容-->
            <div class="col-md-9">
	            <h3 class="page-header">
					个人信息修改
				</h3>
	    
		    	<form id="form" class="form-horizontal" method="post" action="<%=request.getContextPath()%>/empInfo/edit.action">
		        	<div class="form-group"> 
		                <label class="col-sm-2 control-label float-left">员工编号</label>
		                <div class="col-sm-8">
		                	<input type="text" class="col-sm-8 form-control" value="${emp.empNum}" readonly>
		                	<input type="hidden" name="empNum" value="${emp.empNum}">		                	
		                	<input type="hidden" name="empId" value="${emp.empId}">               
		                </div>
		            </div>
		            <div class="form-group"> 
		                <label class="col-sm-2 control-label float-left">员工姓名</label>
		                <div class="col-sm-8">
		                	<input type="text" class="col-sm-8 form-control" value="${emp.empName}" readonly> 
		                	<input type="hidden" name="empName" value="${emp.empName}">              
		                </div>		                
		            </div>		              
		            
		            <div class="form-group"> 
		                <label class="col-sm-2 control-label float-left">手机号码</label>
		                <div class="col-sm-8">
		                	<input type="text" name="phone" value="${emp.phone}" datatype="/1[345678]\d{9}/" errormsg="手机格式不正确" class="form-control col-sm-2">              
		                </div>
		            </div> 		           		    
		            
		            <div class="form-group"> 
		                <label class="col-sm-2 control-label float-left">邮箱</label>
		                <div class="col-sm-8">
		                	<input type="text" name="email" value="${emp.email}" datatype="*" class="form-control col-sm-2">            
		                </div>
		            </div>
		            <div class="form-group"> 
		                <label class="col-sm-2 control-label float-left">QQ</label>
		                <div class="col-sm-8">
		                	<input type="text" name="qq" value="${emp.qq}" datatype="n5-11" ignore="ignore" errormsg="QQ格式不正确" class="form-control col-sm-2">             
		                </div>
		            </div>
		              <div class="form-group"> 
		                <label class="col-sm-2 control-label float-left">介绍</label>
		                <div class="col-sm-8">
		                	<textarea name="introduction" datatype="*0-60" class="form-control" errormsg="请输入少于60个字！" cols="40" rows="4">${emp.introduction}</textarea>             
		                </div>
		            </div>
		            <div class="form-group">
		            	<div class="col-sm-offset-2 col-sm-10">
		            		<input type="hidden" name="departmentId" value="${emp.departmentId }">
							<input type="hidden" name="positionId" value="${emp.positionId}">
							<input type="hidden" name="gender" value="${emp.gender}">
							<input type="hidden" name="birthday" value="${emp.birthday}">
		            		<input type="submit" value="确认修改" id="confirm" class="btn btn-primary col-md-4"/>
		            		<a href="<%=request.getContextPath()%>/empInfo/query.action" id="changePwd" class="btn btn-primary col-md-4 col-sm-offset-2">返回</a>
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
	<footer>
		<jsp:include page="/include/footer.jsp"></jsp:include>
	</footer>
	<!--网页底部结束-->
</body>

</html>