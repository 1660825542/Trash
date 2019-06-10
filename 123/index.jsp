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
						<th>用户等级</th>
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
			<div class="col-md-6" id="meta_users_info">
				当前共有【】条数据，共有【】页，当前显示【/】页
			</div>
			<div class="col-md-6" id="nav_users_info">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				   <li>
				      <a href="#" >
				        <span aria-hidden="true">首页</span>
				      </a>
				    </li>
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
				    
				     <li>
				      <a href="#">
				        <span aria-hidden="true">尾页</span>
				      </a>
				    </li>
				    
				  </ul>
				</nav>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			toPage(1);
		});
		/* {"userid":1,"username":"刘备","sex":"M","email":"liubei@163.com","levelid":1,
			"userlevel":{"levelid":1,"levelname":"白金等级","leveltxt":"拥有白金等级的会员可以无限次品尝咖啡"}} */
		function toPage(cpage){
			$.get("users",{'cpage':cpage},function(data){
				/* 显示数据到table */
				show_user_data(data);
				/* 显示分页的元素数据 */
				create_users_dbmeta(data);
				/* 创建分页导航 */
				create_users_pagenav(data);
			},'json');
		}
		/*
		 *   专门用于显示用户的table数据
		 */
		function show_user_data(data){
			var db=data.dbinfo.pageInfo.list;
			/* alert(JSON.stringify(db)); */
			$('#table_users tbody').empty();
			//将这些数据放在table里显示出来
			$.each(db,function(index,json){
				/* alert(JSON.stringify(element)); */
				var chktd=$('<td><input type="checkbox"/></td>');
				var useridTd=$('<td></td>').append(json.userid);
				var usernameTd=$('<td></td>').append(json.username);
				var emailTd=$('<td></td>').append(json.email);
				var sexTd=$('<td></td>').append(json.sex=='F'?'女':'男');
				var userLevelTd=$('<td></td>').append(json.userlevel.levelname);
				/* 
				<button type="button" class="btn btn-primary btn-sm">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			编辑</button>
			<button type="button" class="btn btn-warning btn-sm">
				<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
			删除</button> */
				
				var editBtn=$('<button type="button"></button>').addClass('btn btn-primary btn-sm');
					editBtn.append($('<span></span>').addClass('glyphicon glyphicon-pencil').attr('aria-hidden','true'));
					editBtn.append('编辑');
					
				var delBtn=$('<button type="button"></button>').addClass('btn btn-warning btn-sm');
					delBtn.append($('<span></span>').addClass('glyphicon glyphicon-trash').attr('aria-hidden','true'));
					delBtn.append('删除');
				var btnTd=$('<td></td>').append(editBtn).append(delBtn);

				var eleTr=$('<tr></tr>').append(chktd).
					append(useridTd).
					append(usernameTd).
					append(emailTd).
					append(sexTd).
					append(userLevelTd).
					append(btnTd).appendTo($('#table_users tbody'));					
			});
		}
		/*
		 *显示分页元数据，更多效果自行考虑
		 */
		function create_users_dbmeta(data){
			/* console.log(data.dbinfo.pageInfo.total); */
			/* alert(data.dbinfo.pageInfo.total);
			alert(JSON.stringify(data)); */
			/* 共有多少行数据 */
			var total=data.dbinfo.pageInfo.total;
			var pageNum=data.dbinfo.pageInfo.pageNum;
			var pages=data.dbinfo.pageInfo.pages;
			console.log(""+total+"   "+pageNum+"    "+pages);
			$('#meta_users_info').empty();
			$('#meta_users_info').append('共有【'+total+'】条数据，'+'当前显示【'+pageNum+'/'+pages+'】页');
			
		}
		/*
		 *显示分页导航，更多效果自行考虑
		 */
		function create_users_pagenav(data){
			$('#nav_users_info')
			
		}
		
	</script>
</body>
</html>