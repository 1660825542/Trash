<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>图片上传</title>

<style type="text/css">
body{
   background-image: url(./img/tupian.png);
	background-repeat: repeat;
	background-size: 100%;
}
</style>
</head>
<body>
	<div id="contentWrapper">

	

		<div id="contentRight">
		<form action="${pageContext.request.contextPath}/PictureSave_Servlet" method="post" enctype="multipart/form-data">
			<table width="600" height="450" border="1" align="center">
				<tr>
					<td align="center"><h2>商品图片：</h2></td>
					<td>
						
						<input type="file" name="picture" style="width:200px;height:30px;" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="插入" style="width: 100px; height: 30px;"> 
						<input type="reset" value="重置" style="width: 100px; height: 30px;">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"> ${succer} </td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</body>
</html>