<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加商品</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style2.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JS/1.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/AdministratorSave_Servlet" method="post" >
		<table width="600px" height="600px" border="1" align="center">
			<tr>
				<td colspan="2" align="center">
					<h1>欢迎您进入插入商品界面</h1>
				</td>
			</tr>
			
			
			<tr>
				<td align="center"><h2>商品编号：</h2></td>
				<td><input type="text" name="number" id="number" style="width:200px;height:30px;" autocomplete="off"/></td>
			</tr>
			
			
			<tr>
				<td align="center"><h2>商品名称：</h2></td>
				<td>
					<input type="hidden" name="method" value="form1">
					<input type="text" name="name" style="width:200px;height:30px;" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<td align="center"><h2>商品价格：</h2></td>
				<td><input type="text" name="price" style="width:200px;height:30px;" autocomplete="off"/></td>
			</tr>
			
			<tr>
				<td align="center"><h2>商品库存：</h2></td>
				<td><input type="text" name="stock" style="width:200px;height:30px;" autocomplete="off"/></td>
			</tr>
			
			<tr>
				<td align="center"><h2>商品分组编号：</h2></td>
				<td><input type="text" name="attri" style="width:200px;height:30px;" autocomplete="off"/></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="插入" style="width: 100px; height: 30px;"> 
					<input type="reset" value="重置" style="width: 100px; height: 30px;">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center" id="td1"><br/></td>
			</tr>
		</table>
	</form>

</body>
</html>