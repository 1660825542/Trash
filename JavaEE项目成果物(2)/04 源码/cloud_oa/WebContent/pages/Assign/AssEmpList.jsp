<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.icss.oa.assign.pojo.*" %>
<!doctype html>
<html>
<head>
<title>员工信息列表</title>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no">
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" rel="stylesheet" type="text/css">

	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
		$(document).ready(function(e) {
			
            $('.state').each(function(index, state) 
			{
                
				var txt = $(state).text();
				
				if(txt=="外派中")
				{
					$(this).addClass("label-danger");
				}
				
				if(txt=="闲置中")
				{
					$(this).addClass("label-success");
				}
				
				if(txt=="已离职")
				{
					$(this).addClass("label-warning");
				}
            });            
        });
		
		//下载简历
		function down(id){
			var url = '<%=request.getContextPath()%>/assemp/queryResume.action?assId=' + id ;
			$.post(url,function(data){
				if(data == 'exists'){
					document.getElementById(id+'download').submit();
				}
				if(data == 'notExists'){
					layer.msg("该用户没有简历",{icon: 2});
					return null;
				}
			});	
		}
		
		/*删除确认*/
		function del(assId,btnObj) {			
			//询问框
				layer.confirm('确定要删除该数据吗', {
				closeBtn:2,
				btn: ['确定','取消'], //按钮
				shade: false //显示遮罩
			}, function(){
				/*异步删除*/
				var url = '<%=request.getContextPath()%>/assemp/delete.action?assId=' + assId ;
				$.get(url,function(data){
					$(btnObj).parent().parent().parent().parent().parent().remove();
					layer.msg('删除成功', {icon: 1});
				}) ;
			}, function(){
				layer.msg('取消删除', {shift: 6});
			});			
		}
		
		$(document).ready(function(){
			$('#search').submit(function(){
				if ($('#keyword').val().replace(/\s/g, "") == '') {
					layer.msg("请输入检索关键字",{icon: 2});
					return false;
				}
				var reg = /^[\w\W]{0,10}$/
					if (!reg.test($('#keyword').val())) {
						layer.msg("输入字符不能大于10",{icon: 2});
						return false;
					}	
			});
			
		});
	</script>
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
		<label for="toggle1"class="animate">外派人才库<i class="fa fa-bars float-right"></i></label>
		<ul class="animate">
			<a href="<%=request.getContextPath() %>/assemp/query.action">
				<li class="animate actived">外派人员列表<i class="fa fa-code float-right"></i></li>
			</a>
			<a href="<%=request.getContextPath() %>/asscom/query.action">
				<li class="animate">合作公司列表<i class="fa fa-code float-right"></i></li>
			</a>
            <a href="<%=request.getContextPath() %>/comreq/query.action">
            	<li class="animate">公司需求列表<i class="fa fa-code float-right"></i></li>
            </a>
            <a href="<%=request.getContextPath() %>/empcom/query.action">
            	<li class="animate">外派记录显示<i class="fa fa-code float-right"></i></li>
            </a>
		</ul>
	</dropdown>
	</div>
	<!-- 侧边栏完成 -->

	<!-- 右侧内容-->
	<div class="col-md-9">
		<h3 class="page-header">外派人员信息列表</h3>

		<form id="search" name="search" action="<%=request.getContextPath() %>
			/assemp/queryByIndex.action?pageNum=1" method="post">
			<div class="input-group">
				<input type="text" id="keyword" name="keyword" placeholder="请输入员工的姓名或电话" class="form-control">
				<span class="input-group-btn">
					<input type="submit" class="btn btn-primary" value="全文检索"></span>
			</div>
		</form>
		<br>

		<table  class="table table-striped table-bordered table-condensed table-hover">
			<thead>
				<tr>
					<th>员工编号</th>
					<th>员工姓名</th>
					<th>教育经历</th>
					<th>工作经验</th>
					<th>技能特长</th>
					<th>户口</th>
					<th>工资/月</th>
					<th>人才状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="assemp">
					<tr>
						<td>${assemp.assId }</td>
						<td>${assemp.assName }</td>
						<td>${assemp.education }</td>
						<td>${assemp.workExp }</td>
						<td>${assemp.skill }</td>
						<td>${assemp.residentCity }</td>
						<td>${assemp.salary }</td>
						<td style="text-align:center">
							<span class="label state">${assemp.available }</span>
						</td>
						<td>
							<div class="btn-group btn-xs">
								<button type="button" class="btn btn-default btn-xs dropdown-toggle" id="dropdownMenu" data-toggle="dropdown">
									操作
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li>
										<a href="<%=request.getContextPath()%>
											/assemp/toUpdate.action?assemp.assId=${assemp.assId }&pageNum=${pager.pageNum}">修改个人信息
										</a>
									</li>
									<li>
										<form name="download" id="${assemp.assId }download" method="post" enctype="multipart/form-data"	action="<%=request.getContextPath()%>/assemp/download.action?assId=${assemp.assId }"></form>
										<a href="javascript:void(0)" onclick="down(${assemp.assId });">下载简历</a>
									</li>
									<li>
										<a href="javascript:void(0)" onclick="del(${assemp.assId },this);">删除</a>
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
				/pages/Assign/AddAssEmp.jsp?pageNum=${pager.pageCount}" class="btn btn-primary btn-block">增加新的外派人员
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