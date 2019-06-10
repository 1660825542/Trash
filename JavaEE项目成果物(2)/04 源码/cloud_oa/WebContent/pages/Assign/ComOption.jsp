<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.icss.oa.assign.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司选择</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../js/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../js/datetimepicker/jquery.datetimepicker.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>

<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" rel="stylesheet" type="text/css">

<script type="text/javascript">
//获取窗口索引
var index = parent.layer.getFrameIndex(window.name);

var assId;
var assName;

$(document).ready(function(){
	$('input[name="ids"]').click(function(){
		assComId = $.trim( $(this).parent().parent().find('td').eq(1).text() );
		comName = $.trim( $(this).parent().parent().find('td').eq(2).text() );
	});
	
	//给父页面传值
	$('#transmit').on('click', function(){
	    $(window.parent.document).find('#assComId').val(assComId);
		$(window.parent.document).find('#comName').val(comName);

	    parent.layer.close(index);
	});
});

//关闭当前窗口
function closeWin(){
	$(window.parent.document).find('#popWinClose').click();
}
</script>
</head>

<body>
<div style="margin:20px;">
	<table  class="table table-striped table-bordered table-condensed table-hover">
		<thead>
			<tr>
				<th>选择公司</th>
				<th>公司编号</th>
				<th>公司名称</th>
				<th>联系人姓名</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list}" var="asscom">
				<tr>
					<td>
						<input type="radio" name="ids"></td>
					<td>${asscom.assComId }</td>
					<td>${asscom.comName }</td>
					<td>${asscom.contactName }</td>
					<td>${asscom.remarks }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<input type="button"  id="transmit" class="btn btn-primary" value="确定">
	<!-- 包含分页 -->
	<jsp:include page="/include/AdvancedPager.jsp"/>
</div>
</body>

</html>