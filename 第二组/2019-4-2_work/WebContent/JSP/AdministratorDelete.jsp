<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>删除商品</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style2.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JS/1.js"></script>
<style type="text/css">

</style>
</head>
<body>

			<form action="${pageContext.request.contextPath}/GoodSup_Servlet"  method="post">
				<table width="800" height="500" border="1" align="center">
					<tr>
						<td colspan="2" align="center" style="height: 50px;">
							<h1>欢迎您进入删除界面</h1>
						</td>
					</tr>
					<tr>
						<td align="center"><h2>商品编号：</h2></td>
						<td align="center">
							<input type="hidden" name="method" value="delete">
							<input type="text" id="number" name="number" style="width:200px;height:30px;" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td align="center"><h2>验证商品是否存在</h2></td>
						<td align="center">
							<input type="button" value="验证" style="width: 100px; height: 30px;">
							
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" value="删除" style="width: 100px; height: 30px;"> 
							<input type="reset" value="重置" style="width: 100px; height: 30px;">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" id="td1"><br/>${error}</td>
					</tr>
				</table>
			</form>
		
</body>
</html>