<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.room.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#inroom').Validform({
			tiptype:3	
		});
	});
	
	//关闭当前窗口
	function closeWin(){
		$(window.parent.document).find('#popWinClose').click();
	}
	
</script>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="room1" style="min-height: 400px;">
			<form name="inroom" id="inroom" method="post" action="<%=request.getContextPath()%>
				/room/insert.action">
				<table  class="table table-striped">
					<tr>
						<td width="30%">会议室名称</td>
						<td width="70%">
							<input type="text" datatype="s1-10" errormsg="会议室名称最多10字符" name="roomName" id="roomName"></td>
					</tr>
					<tr>
						<td>会议室地点</td>
						<td>
							<input type="text" datatype="*1-10" errormsg="会议室地点最多10字符" name="roomPlace" id="roomPlace"></td>
					</tr>
					<tr>
						<td>空调情况(若未选则视为无空调)</td>
						<td>
							<input type="radio" name="radio1" id="radio1"value="1" datatype="*" errormsg="请选择空调状态">
							&nbsp;有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input
										type="radio" name="radio1" id="radio2"value="0" datatype="*" errormsg="请选择容纳人数">&nbsp;无</td>
					</tr>
					<tr>
						<td>容纳人数</td>
						<td>
							<select name="accomNum" datatype="*" errormsg="请选择容纳人数">
								<option value="">容纳人数</option>
								<option value="10">10</option>
								<option value="30">30</option>
								<option value="50">50</option>
							</select>
							<input type="hidden" name="meetState" value="0"></td>
					</tr>

				</table>

				<div class="text-center">
					<input type="submit" value="确定" class="btn btn-primary" onclick="closeWin();">
					<input type="button" value="取消" class="btn" onclick="closeWin();">
				</div>
			</form>

		</div>
	</div>
</div>

</body>
</html>