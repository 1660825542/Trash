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
<form id="form1" method="post" action="<%=request.getContextPath()%>/role/addPermToRole.action?roleId=${roleId }">
	<div class="row">
		<div class="well">
		<c:if test="${haveList.size()>0 }">已拥有权限：
			<c:forEach items="${haveList }" var="have">
				${have.permInfo }
			</c:forEach>
		</c:if>
			<c:if test="${list.size()>0 }">
			<h5>未拥有权限如下</h5>
	        <div id="perms" class="row">
	       		<c:forEach items="${list}" var="perm">	
	        	<div class="col-xs-4">
		        	<div class="checkbox">
		             	<label for="checkbox1">
		                	<input type="checkbox" id="${perm.permId }" name="permId" value="${perm.permId }">${perm.permInfo }
		                </label>             
		            </div>
	        	</div>
	        	</c:forEach>
			</div>
			</c:if>
			<c:if test="${list.size()<=0 }">
				该角色已拥有所有权限！
			</c:if>
		</div>
	</div>
</form> 
	<div class="row">
        <!-- 包含分页 -->
	   	<jsp:include page="/include/pager.jsp"/>   
	</div>
    <div class="text-center">
    	<input id="yes" type="button" value="确定" class="btn btn-primary">
		<input id="no" type="button" value="取消" class="btn">
    </div>           
</body>

</html>