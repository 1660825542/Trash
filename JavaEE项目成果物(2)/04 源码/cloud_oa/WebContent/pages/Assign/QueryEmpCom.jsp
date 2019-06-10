<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询派遣记录</title>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	
	
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" rel="stylesheet" type="text/css">

	<script type="text/javascript">
	
	/*删除确认*/
	function del(empComId,btnObj) {			
		//询问框
		layer.confirm('确定要删除该数据吗', {
			btn: ['确定','取消'], //按钮
			shade: false //显示遮罩
		}, function(){
			/*异步删除*/
			var url = '<%=request.getContextPath()%>/empcom/delete.action?empComId=' + empComId + '&pageNum=' + ${pager.pageNum};
			$.get(url,function(data){
				$(btnObj).parent().parent().parent().remove();
				layer.msg('删除成功', {icon: 1});
			}) ;
		}, function(){
			layer.msg('取消删除', {shift: 6});
		});			
	}
</script>
</head>

<body>

<!-- 网页头部-->

<jsp:include page="/include/header.jsp"></jsp:include>

<!-- 每个模块的部分 -->

<div class="container">

	<!-- 侧边栏 -->
	<div class="col-md-3" id="myScrollSpy" style="margin-top:30px;">
        <dropdown class="col-xs-12" style="margin-bottom:30px;"> 
		<input id="toggle1" type="checkbox" checked>
		<label for="toggle1"class="animate">外派人才库<i class="fa fa-bars float-right"></i></label>
		<ul class="animate">
			<a href="<%=request.getContextPath() %>/assemp/query.action">
				<li class="animate">外派人员列表<i class="fa fa-code float-right"></i></li>
			</a>
			<a href="<%=request.getContextPath() %>/asscom/query.action">
				<li class="animate">合作公司列表<i class="fa fa-code float-right"></i></li>
			</a>
            <a href="<%=request.getContextPath() %>/comreq/query.action">
            	<li class="animate">公司需求列表<i class="fa fa-code float-right"></i></li>
            </a>
            <a href="<%=request.getContextPath() %>/empcom/query.action">
            	<li class="animate actived">外派记录显示<i class="fa fa-code float-right"></i></li>
            </a>
		</ul>
	</dropdown>
	</div>
	<!-- 侧边栏完成 -->

	<!-- 右侧内容-->
	<div class="col-md-9">
		<h3 class="page-header">外派情况列表</h3>

		<table  style="text-align:center" class="table table-striped table-bordered table-condensed table-hover">
			<thead >
				<tr>
					<th style="text-align:center">外派编号</th>
					<th style="text-align:center">员工编号</th>
					<th style="text-align:center">公司编号</th>
					<th style="text-align:center">员工姓名</th>
					<th style="text-align:center">公司名称</th>
					<th style="text-align:center">操作</th>
				</tr>
			</thead>
			<tbody>
				<!-- 遍历输出数据 -->
				<c:forEach items="${list}" var="empcom">
					<tr>
						<td style="font-weight:bolder">${empcom.empComId }</td>
						<td>${empcom.assId }</td>
						<td>${empcom.assName }</td>
						<td>${empcom.assComId }</td>
						<td>${empcom.comName }</td>
						<td style="width:8%">
							<div class="btn-group btn-xs">
								<button type="button" onclick="del(${empcom.empComId },this);" class="btn btn-default btn-xs dropdown-toggle" id="dropdownMenu" data-toggle="dropdown">
									删除
								</button>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="col-sm-offset-8">
			<form id="exportexcel" method="post" action="<%=request.getContextPath() %>/empcom/exportExcel.action">
				<a href="<%=request.getContextPath() %>
					/pages/Assign/AddEmpCom.jsp?pageNum=${pager.pageCount}" class="btn btn-primary btn-block">指定新的外派
				</a>
				<input type="submit" class="btn btn-success btn-block" value="导出EXCEL报表">
				<!-- 响应式布局，移动端才会有返回主页的按钮 -->
				<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
				<!-- / -->
			</form>
		</div>
		<!-- 包含分页 -->
		<jsp:include page="/include/AdvancedPager.jsp"/>

	</div>
</div>
<!--右侧内容完 -->

<!--网页底部-->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!--网页底部结束-->
</body>



</html>