<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员首页</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style2.css" />
</head>
<body>
	<ul>

			<li class="active">
				
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/JSP/AdministratorSave.jsp"><i> 添加商品</i></a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/JSP/AdministratorDelete.jsp"><i>删除商品信息</i> </a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/JSP/AdministratorUpdate.jsp"><i>修改商品</i> </a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/GoodSup_Servlet?method=selectAll"><i>查看全部商品</i> </a>
					</li>
				</ul>
			</li>
		</ul>

	<div>
		<h1 align="center">${rootId}</h1>
		<h1 align="center">${succer}</h1>
	</div>


</body>
</html>