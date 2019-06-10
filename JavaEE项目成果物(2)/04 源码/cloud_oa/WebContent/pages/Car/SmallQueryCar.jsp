<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.icss.oa.assign.pojo.*" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no">
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	
	<script type="text/javascript">

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	$(document).ready(function(e) {
	
		$('.pic').each(function(index, pic){
			
			var carId = $(this).parent().prev().children().text();
			
			var url = '<%=request.getContextPath()%>/car/queryPic2.action?carId=' + carId ;
			
			var picObj = $(this).find('img');
						
			$.get(url,function(data){
				
				picObj.prop("src","data:image/jpeg;base64,"+data);
				
			});
									
		});
	
	});
		
	function getCarId(carId){
		
		$(window.parent.document).find('#carId').val(carId);
		parent.layer.close(index);
		
	}
		
	</script>
	</head>

	<body>
		<nav class="navbar navbar-default navbar-fixed-top" style="height:30px">
			<table  class="table">
					<tr>
						<th style="width:50%;font-size:20px"><span style="margin-left:30px">车辆信息</span></th>
						<th style="font-size:20px"><span style="float:right;margin-right:140px">车辆图片</span></th>
					</tr>
			</table>
		</nav>
		<table style="margin-top:50px" class="table table-condensed table-hover">
			<tbody>
				<c:forEach items="${list}" var="car">
					<tr onclick="getCarId(${car.carId });">
						<td>
							<div style="margin:15px 0 0 30px">
								车辆编号：${car.carId }
								<br>
								<br>
								车辆类型：${car.carType } 
								<br>
								<br>
								车牌号：   ${car.license }
							</div>
						</td>
						<td><div style="display:none">${car.carId }</div></td>
						<td style="width:30%">
						<div id=${car.carId } class="pic">
							<img src="" alt="这张图片崩溃了" class="img-responsive" style="float:right">		
						</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>

	</html>