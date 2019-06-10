<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link rel="stylesheet" type="text/css" href="../../css/common.css">
	<link rel="stylesheet" type="text/css" href="../../js/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../../js/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" type="text/css" href="../../js/Validform/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="../../css/style-bar.css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">

<script type="text/javascript">
	$(document).ready(function(){
		
		
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#addForm').Validform({
			tiptype:3
		});
		
		//日历框
		$('#birthday').datetimepicker({		
			lang:'ch',
			format:'Y-m-d',
			timepicker:false,
			maxDate:0
		});
		
		$('#chooseDept').on('click', function(){
			layer.open({
				type: 2,
				title: '选择部门',
				maxmin: false,
				closeBtn:2,
				shadeClose: false, //点击遮罩关闭层
				area : ['700px' , '500px'],
				shift: 5,
				content: '<%=request.getContextPath()%>/dept/querySelectDept.action'
			});
		});	
		
		$('#choosePos').on('click', function(){
			layer.open({
				type: 2,
				title: '选择职务',
				maxmin: false,
				closeBtn:2,
				shadeClose: false, //点击遮罩关闭层
				area : ['700px' , '500px'],
				shift: 5,
				content: '<%=request.getContextPath()%>/pos/querySelectPos.action'
			});
		});	
		
	});
	
	

</script>
<title>增加新员工</title>
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
				<label for="toggle1"class="animate">系统管理<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/dept/query.action">
						<li class="animate">部门管理<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/pos/query.action">
						<li class="animate">职务管理<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath()%>/emp/query.action">
	                	<li class="animate actived">员工管理<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/role/query.action">
	                	<li class="animate">角色管理<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/perm/query.action">
	                	<li class="animate">权限管理<i class="fa fa-cog float-right"></i></li>
	                </a>
				</ul>
			</dropdown>
		</div>           
         <!-- 侧边栏完成 -->
         
         <!-- 右侧内容-->
		<div class="col-md-9">
			
            <h3 class="page-header">
                新员工信息
            </h3>	   
		   	
            <form id="addForm" name="form1" method="post" action="<%=request.getContextPath()%>/emp/add.action?pageNum=${param.pageNum}">
				<table class="table table-striped table-condensed">
					<tr class="hidden-lg">
						<td>
			            	<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">员工编号</label>
			       				<div class="col-md-4">
			       					<input name="empId" id="empId" class="form-control" placeholder="编号自动生成，无需填写" readonly="readonly" type="text">
			       				</div>
			   				</div>
			   			</td>
		   			</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">姓名<span style="color:red">*</span></label>
			       				<div class="container col-md-4">
			       					<input class="form-control col-xs-4" name="empName" id="empName" placeholder="请输入姓名" datatype="s2-20" nullmsg="请输入员工姓名！" errormsg="请输入2-20位文字！" type="text">
			       				</div>
			   				</div>
		   				</td>
		   			</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">登录用户名<span style="color:red">*</span></label>
			       				<div class="col-md-4">
			                    	<input class="form-control" name="empNum" id="empNum" placeholder="请输入用户名" ajaxurl="<%=request.getContextPath()%>/emp/queryIsExist.action" datatype="s6-12" nullmsg="请输入用户名！" errormsg="请输入6-12位字母！" type="text">
			       				</div>
			   				</div>
		   				</td>
	   				</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">密码<span style="color:red">*</span></label>
			       				<div class="col-md-4">
			                    	<input class="form-control" name="password" id="password" placeholder="请输入密码" datatype="*5-20" nullmsg="请输入密码！" errormsg="请输入5-20位字符！" type="text">
			       				</div>
			   				</div>
		   				</td>
	   				</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">性别<span style="color:red">*</span></label>
			       				<div class="col-md-4">
			       					<div class="radio">
				                        <label for="radio1" class="radio-inline">
				                            <input type="radio" id="genderMale" name="gender" value="1" checked>男</label>
				                        <label for="radio2" class="radio-inline">
				                            <input type="radio" id="genderFamale" name="gender" value="0">女</label>
				                    </div>
			       				</div>
			   				</div>
		   				</td>
		   			</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">生日<span style="color:red">*</span></label>
			       				<div class="col-md-4">
			                   		<input class="form-control" name="birthday" id="birthday" placeholder="点击选择生日" datatype="*" nullmsg="请选择生日！" type="text" readonly>
			       				</div>
			   				</div>
		   				</td>
		   			</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">电话<span style="color:red">*</span></label>
			       				<div class="col-md-4">
			                    	<input class="form-control" name="phone" id="empPhone" placeholder="请输入电话" datatype="/^1[345678]\d{9}$/" errormsg="手机格式不正确！" nullmsg="请输入电话！" type="tel">
			       				</div>
			   				</div>
		   				</td>
		   			</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">电子邮件<span style="color:red">*</span></label>
			       				<div class="col-md-4">
			                    	<input class="form-control" name="email" id="email" datatype="e" placeholder="请输入电子邮箱" errormsg="邮箱格式不正确！" nullmsg="请输入邮箱！" type="text">
			       				</div>
			   				</div>
		   				</td>
		   			</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">QQ</label>
			       				<div class="col-md-4">
			                    	<input class="form-control" name="qq" id="qq" datatype="n5-10" ignore="ignore" placeholder="请输入QQ" errormsg="QQ格式不正确！" type="tel">
			       				</div>
			   				</div>
		   				</td>
		   			</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">所属部门<span style="color:red">*</span></label>
			       				<div class="col-md-4">
			 						<input class="form-control" name="deptName" id="deptName" datatype="*" placeholder="点击选择部门"  nullmsg="请选择部门！" type="text" readonly>
			                        <input name="departmentId" id="departmentId" type="hidden">
			                    </div>
			                    <div class="col-md-2">
			                    	<input id="chooseDept" value="选择部门" class="btn btn-primary" type="button">
			       				</div>
			   				</div>
		   				</td>
		   			</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">职务<span style="color:red">*</span></label>
			       				<div class="col-md-4">
									<input class="form-control" name="posName" id="posName" datatype="*" placeholder="点击选择职务"  nullmsg="请选择职务！" type="text" readonly>
			                        <input name="positionId" id="positionId" type="hidden">
			                    </div>
			                    <div class="col-md-2">
			                       	<input id="choosePos" value="选择职务" class="btn btn-primary" type="button">
			                    </div>
			   				</div>
		   				</td>
		   			</tr>
				</table>
                    
                <div class="col-sm-offset-9">
                    <input type="submit" class="btn btn-primary btn-block" value="增加" >
                    <input type="button" class="btn btn-success btn-block" value="返回" onclick="history.back();">
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