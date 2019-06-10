<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="comm/css/main.css"/>
</head>
<body>

<fieldset>
	<legend>所有员工信息如下</legend>
	
	
	<table id="customers">
		<tr>
			<th>员工编号</th>
			<th>员工姓名</th>
			<th>工作性质</th>
			<th>直接领导</th>
			<th>入职时间</th>
			<th>基本工资</th>
			<th>月度奖金</th>
			<th>所在部门</th>
			<th>当行操作</th>
		</tr>
		<c:forEach items="${emps }" var="e">
			<tr>
				<td>${e.empno }</td>
				<td>${e.ename }</td>
				<td>${e.job }</td>
				<td>${e.mgr }</td>
				<td>${e.hiredate}</td>
				<td>${e.sal }</td>
				<td>${e.comm }</td>
				<td>${e.dname }</td>
				<td>更新，删除</td>
			</tr>
		</c:forEach>
	</table>
</fieldset>
</body>
</html>