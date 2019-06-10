<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全部商品展示</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style3.css" />
</head>
<body>

<fieldset>
	<legend>员工信息</legend>
    
	<table id="customers">
		<tr>
            <th colspan="5">
               	全部商品展示
            </th>
        </tr>
		<tr>
			<th>商品图片</th>
			<th>商品名称</th>
			<th>商品价格</th>
			<th>商品库存量</th>
			<th>商品分组编号</th>
		</tr>
		<c:forEach items="${good}" var="good">
            <tr>
                <td><img src="${good.picture}"></img></td>
                <td>${good.name}</td>
                <td>${good.price}</td>
                <td>${good.stock}</td>
                <td>${good.attri}</td>
            </tr>
        </c:forEach>s
	</table>

</fieldset>
	

</body>
</html>