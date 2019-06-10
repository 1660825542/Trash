<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>在线消息</title>
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<link href="<%=request.getContextPath()%>/pages/Message/css/message.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
		
	<script type="text/javascript">
		//检查是否全选
		function checkAll(){
			if($('td>input').length==$("td>input:checked").length){
				$('th>input').prop("checked", true);
			}else{
				$('th>input').prop("checked", false);
			}
		}
		$(document).ready(function(e) 
		{ 
			//确定按钮，退出模态层
			$('button').click(function(){
				parent.layer.closeAll();
			});
			//空行补满
			while ($('tr').length<12)
			{
				$('tr').last().after('<tr><th>&nbsp</th><th></th><th></th><th></th></tr>');
			}
			//读取已选
			$('td[name="recipientId"]').each(function(){
				var key = this;
				parent.$('input[name="recipientIdList"]').each(function(){
					if($(key).text()==$(this).val()){
						$(key).prev().children().prop("checked", true);
						return;
					}
				});
			});
			checkAll();
			//选择添加或减少一个收件人
			$('td>input').click(function (){
				checkAll();
				var recipientId = $(this).parent().next('td[name="recipientId"]').text();
				var recipientName = $(this).parent().siblings('td[name="recipientName"]').text();
				if($(this).prop("checked") == true){
					parent.$('#recipientIdList').after('<input name="recipientIdList" value="'+recipientId+'" type="hidden"/><input name="recipientName" type="hidden" value="'+recipientName+'; ">');
				}else{
					parent.$('input[name="recipientIdList"]').each(function(){
						if($(this).val()==recipientId){
							$(this).next().remove();
							$(this).remove();
						}
					});
				}
			});
			//选择或取消所有
			$('th>input').click(function(){
				if($(this).prop("checked") == true){
					$('td>input').prop("checked", true);
					$('td[name="recipientId"]').each(function(){
						var key = this;
						var flag = 0;
						parent.$('input[name="recipientIdList"]').each(function(){
							if($(key).text()==$(this).val()){
								flag = 1;
								return;
							}
						});
						if(flag==0){
							parent.$('#recipientIdList').after('<input name="recipientIdList" value="'+$(this).text()+'" type="hidden"/><input name="recipientName" type="hidden" value="'+$(this).next().text()+'; ">');
						}
					});
				}else{
					$('td>input').prop("checked", false);
					$('td[name="recipientId"]').each(function(){
						var key = this;
						parent.$('input[name="recipientIdList"]').each(function(){
							if($(key).text()==$(this).val()){
								$(this).next().remove();
								$(this).remove();
								return;
							}
						});
					});
				}
			});
		});
	</script>
	
</head>
<body>
	<!-- 展示表格  -->
	<table class="table table-striped">
		<tr>
			<th>&nbsp;<input type="checkbox"></th>
			<th>姓名</th>
			<th>部门</th>					
			<th>职位</th>
		</tr>
		<c:forEach items="${list}" var="map">
			<tr style="cursor:pointer">
				<td>&nbsp;<input type="checkbox"></td>
				<td hidden name="recipientId">${map.EMP_ID}</td>
				<td name="recipientName"><c:out value="${map.EMP_NAME}"/></td>
				<td><c:out value="${map.DEPT_NAME}"/></td>
				<td><c:out value="${map.POS_NAME}"/></td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 分页条 -->
	<jsp:include page="/pages/Message/include/AdvancedPager.jsp"></jsp:include>
	
	<div class="text-center">
		<button type="button" class="btn btn-primary col-xs-4 col-xs-offset-4">确 &nbsp;&nbsp;定 </button> 
	</div>
</body>
</html>