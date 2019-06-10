<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.icss.oa.assign.pojo.*" %>
<!doctype html>
<html>
<head>
	<title>查询车辆</title>
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
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">		
		function down(id){
			document.getElementById(id+'download').submit();
		}
		
		/*删除确认*/
		function del(carId,btnObj) {			
			//询问框
				layer.confirm('确定要删除该数据吗', {
				btn: ['确定','取消'], //按钮
				shade: false //显示遮罩
			}, function(){
				/*异步删除*/
				var url = '<%=request.getContextPath()%>/car/delete.action?carId=' + carId ;
				$.get(url,function(data){
					$(btnObj).parent().parent().parent().parent().parent().remove();
					layer.msg('删除成功', {icon: 1});
				}) ;
			}, function(){
				layer.msg('取消删除', {shift: 6});
			});			
		}
		
		//图片显示模态层
		function viewPho(carId,carType) {
			$('#PhotoShow').modal
			(
				{
					backdrop:'static', /*点击黑色区域不关闭*/
					keyboard:false     /*ESC键不关闭*/
				}
			);
			
			var url = '<%=request.getContextPath()%>/car/queryPic.action?carId=' + carId ;
			$('#PicState').load(url);
			
			$('#carTypeName').text(carType);
			
			$('#navigation').fadeOut("fast").addClass("fixed-top-changed");
		}
		
		$(document).ready(function(e) {
		/*关闭时回到原来尺寸*/
		$('#clostPhoto').click(function(e){
			$('#navigation').removeClass("fixed-top-changed").delay(300).fadeIn("fast");
			});
		});
		
	</script>
	</head>

<body>

<!-- 网页头部-->
<jsp:include page="/include/header.jsp"></jsp:include>
<!-- 每个模块的部分 -->
<div class="container">

	<!-- 左侧边栏 -->
	<div class="col-md-3" id="myScrollSpy" style="margin-top:30px;">
        <dropdown class="col-xs-12" style="margin-bottom:30px;"> 
		<input id="toggle1" type="checkbox" checked>
		<label for="toggle1"class="animate">车辆信息<i class="fa fa-bars float-right"></i></label>
		<ul class="animate">
			<a href="<%=request.getContextPath()%>/car/query.action">
				<li class="animate actived">车辆查看<i class="fa fa-code float-right"></i></li>
			</a>
			<a href="<%=request.getContextPath()%>/processcar/queryUserAllot.action">
				<li class="animate">派车待办<i class="fa fa-code float-right"></i></li>
			</a>
            <a href="<%=request.getContextPath()%>/carrecord/query.action">
            	<li class="animate">派车记录<i class="fa fa-code float-right"></i></li>
            </a>
		</ul>
	</dropdown>
	</div>
	<!-- 左侧边栏完成 -->

	<!--图片显示界面模态层-->
	<div class="modal fade" id="PhotoShow" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" id="clostPhoto" data-dismiss="modal">&times;</button>
					<h4 id="carTypeName" class="text-center">车辆图片</h4>
				</div>

				<div class="modal-body">
					<div id="PicState">
					</div>
				</div>


			</div>
		</div>
	</div>
	<!--/图片界面模态层-->

	<!-- 侧边栏完成 -->

	<!-- 右侧内容-->
	<div class="col-md-9">
		<h3 class="page-header">车辆信息列表</h3>
		<br>
		<table  class="table table-striped table-bordered table-condensed table-hover">
			<thead>
				<tr>
					<th style="width:17%">车辆编号</th>
					<th>车辆类型</th>
					<th>车辆牌照</th>
					<th style="width:10%;text-align:center">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="car">
					<tr>
						<td>${car.carId }</td>
						<td>${car.carType }</td>
						<td>${car.license }</td>
						<td style="text-align:center">
							<div class="btn-group btn-xs">
								<button type="button" class="btn btn-default btn-xs dropdown-toggle" id="dropdownMenu" data-toggle="dropdown">
									操作
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li>
										<a href="<%=request.getContextPath()%>
											/car/updateCar.action?carId=${car.carId }&pageNum=${pager.pageNum}">修改车辆信息
										</a>
									</li>
									<li>
										<a href="javascript:void(0)" onclick="viewPho(${car.carId },'${car.carType}');">查看车辆图片</a>
									</li>
									<li>
										<a href="javascript:void(0)" onclick="del(${car.carId },this);">删除</a>
									</li>
								</ul>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="col-sm-offset-8">
			<a href="<%=request.getContextPath() %>
				/pages/Car/AddCar.jsp?pageNum=${pager.pageCount}" class="btn btn-primary btn-block">增加新的车辆信息
			</a>
			<!-- 响应式布局，移动端才会有返回主页的按钮 -->
			<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
			<!-- / -->
		</div>
		<!-- 包含分页 -->
		<jsp:include page="/include/AdvancedPager.jsp"/>
	</div>
	<!--右侧内容完 -->

</div>

<!--网页底部-->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!--网页底部结束-->
</body>

</html>