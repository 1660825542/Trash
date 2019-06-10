<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.icss.oa.assign.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工选择</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../js/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../js/datetimepicker/jquery.datetimepicker.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>

<script type="text/javascript">
//获取窗口索引
var index = parent.layer.getFrameIndex(window.name);

var assId;
var assName;

$(document).ready(function(){
	$('input[name="ids"]').click(function(){
		assId = $.trim( $(this).parent().parent().find('td').eq(1).text() );
		assName = $.trim( $(this).parent().parent().find('td').eq(2).text() );
	});
	

	//给父页面传值
	$('#transmit').on('click', function(){
	    $(window.parent.document).find('#assId').val(assId);
		$(window.parent.document).find('#assName').val(assName);

	    parent.layer.close(index);
	});
});

//关闭当前窗口
function closeWin(){
	$(window.parent.document).find('#popWinClose').click();
}


//选择外派状态的样式
$(document).ready(function(e) {
	
    $('.state').each(function(index, state) 
	{
        
		var txt = $(state).text();
		
		if(txt=="外派中")
		{
			$(this).addClass("label-danger");
		}
		
		if(txt=="闲置中")
		{
			$(this).addClass("label-success");
		}
		
		if(txt=="已离职")
		{
			$(this).addClass("label-warning");
		}
    });
    
});
</script>
</head>

<body>
<div style="margin:20px;">
	<table  class="table table-striped table-bordered table-condensed table-hover">
		<thead>
			<tr>
				<th>员工选择</th>
				<th>员工编号</th>
				<th>员工姓名</th>
				<th>教育经历</th>
				<th>工作经验</th>
				<th>技能特长</th>
				<th>人才状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list}" var="assemp">
				<tr>
					<td>
						<input type="radio" name="ids"></td>
					<td>${assemp.assId }</td>
					<td>${assemp.assName }</td>
					<td>${assemp.education }</td>
					<td>${assemp.workExp }</td>
					<td>${assemp.skill }</td>
					<td style="text-align:center">
						<span class="label state">${assemp.available }</span>
					</td>
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