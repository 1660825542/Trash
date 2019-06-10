<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>

<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);
$(document).ready(function(){
	parent.layer.msg('请选择！', {icon: 0});
	window.history.back(-1);
});
</script>
</head>

<body>

	

</html>