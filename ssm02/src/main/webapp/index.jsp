<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%
    	String path=this.getServletContext().getContextPath();
    	pageContext.setAttribute("APP_PATH",path);
    %>
    <!-- 在主页面中引入Bootstrap前端框架 -->
    <link rel="stylesheet" type="text/css" href="${APP_PATH }/comm/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script type="text/javascript" src="${APP_PATH }/comm/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="${APP_PATH }/comm/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h2>SSM整合-Trainner</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button type="button" class="btn btn-danger">【删除】</button>
				<button type="button" class="btn btn-success">【新增】</button>
			</div>
		</div>
		<!-- 第三行用于显示所有的用户信息的行 -->
		<div class="row">
			<table class="table table-hover table-striped" id="table_users">
				<thead>
					<tr>
						<th><input type="checkbox" id="sle-all"></th>
						<th>#</th>
						<th>用户姓名</th>
						<th>邮箱地址</th>
						<th>用户性别</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" id="sle-all"></td>
						<td>#</td><td>用户姓名</td><td>邮箱地址</td><td>用户性别</td><td>
						<button type="button" class="btn btn-primary btn-sm">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						编辑</button>
						<button type="button" class="btn btn-warning btn-sm">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
						删除</button>
					</td></tr>
					
					<tr>
						<td>
							<input type="checkbox" id="sle-all">
						</td>
					<td>#</td><td>用户姓名</td><td>邮箱地址</td><td>用户性别</td><td>
						<button type="button" class="btn btn-primary btn-sm">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						编辑</button>
						<button type="button" class="btn btn-warning btn-sm">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
						删除</button>
					</td></tr>
					
					<tr>
						<td>
							<input type="checkbox" id="sle-all">
						</td>
					<td>#</td><td>用户姓名</td><td>邮箱地址</td><td>用户性别</td><td>
						<button type="button" class="btn btn-primary btn-sm">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						编辑</button>
						<button type="button" class="btn btn-warning btn-sm">
							<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
						删除</button>
					</td></tr>
					
				</tbody>
			</table>
		</div>
		<!-- 显示分页数据 -->
		<div class="row">
			<div class="col-md-6">
				当前共有【】条数据，共有【】页，当前显示【/】页
			</div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				    <li>
				      <a href="#" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    <li><a href="#">1</a></li>
				    <li><a href="#">2</a></li>
				    <li><a href="#">3</a></li>
				    <li><a href="#">4</a></li>
				    <li><a href="#">5</a></li>
				    <li><a href="#">6</a></li>
				    <li><a href="#">7</a></li>
				    <li><a href="#">8</a></li>
				    <li>
				      <a href="#" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			showData(1);
		});
		function showData(cpage){
			$.get("users",{'cpage':cpage},function(data){
				//将这些数据放在table里显示出来
				$.each(data,function(index,element){
					var chktd=$('<td><input type="checkbox"/></td>');
				};);
			},'json');
		}
	</script>
</body>
</html>