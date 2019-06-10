<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">

	<script type="text/javascript">
		
	var index = parent.layer.getFrameIndex(window.name);
	
	$(document).ready(function(){
		
		$('#yes').on('click', function(){
			$('#form1').submit();
		});	
		
		$('#no').on('click', function(){
			parent.layer.close(index);
		});
	});
	</script>

</head>

<body>
<div class="container">
<form id="form1" method="post" action="<%=request.getContextPath()%>/emp/addRoleToEmp.action?empId=${empId }">
	<div class="row">
		
		<div class="alert alert-info">
		<c:if test="${listdelete.size()>0 }">
		<h4>已拥有角色如下，勾选可取消此员工该角色</h4>
	        <div id="perms" class="row">
	       		<c:forEach items="${listdelete}" var="role2">	
	        	<div class="col-xs-4">
		        	<div class="checkbox">
		             	<label for="checkbox1">
		                	<input type="checkbox" id="${role2.roleId }" name="roleIdDelete" value="${role2.roleId }">${role2.roleInfo }
		                </label>             
		            </div>
	        	</div>
	        	</c:forEach>
			</div>
			</c:if>
			<c:if test="${listdelete.size()<=0 }">
			<h4>该员工还尚未拥有任何角色！</h4>
			</c:if>
		</div>
	</div>
	<div class="row">
		
		<div class="alert alert-warning">
		<c:if test="${list.size()>0 }">
			<h4>未拥有角色如下，勾选可授予此员工该角色</h4>
	        <div id="perms" class="row">
	       		<c:forEach items="${list}" var="role">	
	        	<div class="col-xs-4">
		        	<div class="checkbox">
		             	<label for="checkbox1">
		                	<input type="checkbox" id="${role.roleId }" name="roleId" value="${role.roleId }">${role.roleInfo }
		                </label>             
		            </div>
	        	</div>
	        	</c:forEach>
			</div>
		</c:if>
		<c:if test="${list.size()<=0 }">
		<h4>当前员工已拥有全部角色！</h4>
		</c:if>
		</div>
	</div>
</form> 

    <div class="text-center">
    	<input id="yes" type="button" value="确定" class="btn btn-primary">
		<input id="no" type="button" value="取消" class="btn">
    </div>     
    </div>      
</body>

</html>