<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
	<fieldset>
		<legend>欢迎您登录本系统</legend>
		<form action="UserAction">
			<input name="method" type="hidden" value="login">
			<table>
				<tr>
					<td>登录ＩＤ：
						
					</td>
					<td>
						<input type="text" name="userid"> 
						${msg}
					</td>
				</tr>
				<tr>
					<td>登录密码：</td>
					<td>
						<input type="password" name="password">
					</td>
				</tr>
				<tr>
					<td colspan=2>
						<input type="submit" value="【登录】">
					</td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>