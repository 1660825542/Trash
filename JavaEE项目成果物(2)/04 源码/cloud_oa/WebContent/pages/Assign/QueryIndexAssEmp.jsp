<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<title>员工检索结果</title>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" rel="stylesheet" type="text/css">

	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
	/*删除确认*/
	
	$(document).ready(function(e) {
		
	});
	
	/*分页显示*/
	var pageNum =${pager.pageNum};//第几页
	var recordCount = ${queryResult.recordCount};//总记录数
	var pageSize = ${pager.pageSize };//每页多少条
	var pageCount = parseInt((recordCount - 1) / pageSize) + 1;//共几页
	
	$(document).ready(function(){
		
		$('#form1').submit(function(){			
			if ($('#keyword').val().replace(/\s/g, "") == '') {
				layer.msg("请输入检索关键字",{icon: 2});
				return false;
			}
			var reg = /^[\w\W]{0,10}$/
			if (!reg.test($('#keyword').val())) {
				layer.msg("输入字符不能大于10",{icon: 2});
				return false;
			}
		});
		
		$('#submitBtn').click(function(){			
			$('#pageNum').val(1);
			$('#form1').submit();				
		});
		
		$('#prevBtn').click(function(){	
			if (pageNum <= 1 ) {
				layer.msg('已经是第一页了', {shift: 5});
				return false;
			}
			
			$('#pageNum').val(pageNum - 1);
			$('#form1').submit();				
		});
		
		$('#nextBtn').click(function(){
			
			if (pageNum >= pageCount) {
				layer.msg('已经是最后一页了', {shift: 5});
				return false;
			}
			
			$('#pageNum').val(pageNum + 1);						
			$('#form1').submit();
		});
	});
	
	/*删除确认*/
	function del(assId,btnObj) {			
		//询问框
			layer.confirm('确定要删除该数据吗', {
			closeBtn:2,
			btn: ['确定','取消'], //按钮
			shade: false //显示遮罩
		}, function(){
			/*异步删除*/
			var url = '<%=request.getContextPath()%>/assemp/delete.action?assId=' + assId ;
			$.get(url,function(data){
				$(btnObj).parent().parent().parent().parent().parent().remove();
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
            	<li class="animate">外派记录显示<i class="fa fa-code float-right"></i></li>
            </a>
		</ul>
	</dropdown>
	</div>
	<!-- 侧边栏完成 -->

	<!-- 右侧内容-->
	<div class="col-md-9">

		<h3 class="page-header">检索结果</h3>
		<form id="form1" action="<%=request.getContextPath() %>
			/assemp/queryByIndex.action" method="post">
			<div class="input-group">
				<input type="text" value="${keyword}" id="keyword" name="keyword" placeholder="请输入姓名或电话" class="form-control">
				<span class="input-group-btn">
					<input id="submitBtn" type="button" class="btn btn-primary" value="全文检索"></span>
			</div>
			<input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum }"></form>
	</br>
	
	<c:if test="${queryResult.list.size()>0 }">
	<table  class="table table-striped table-bordered table-condensed table-hover">
		<thead>
			<tr>
				<th>员工编号</th>
				<th>员工姓名</th>
				<th>员工学历</th>
				<th>员工技能</th>
				<th>工作经历</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<!-- 遍历输出数据 -->
			<c:forEach items="${queryResult.list}" var="assemp">
				<tr>
					<td class="select">${assemp.assId }</td>
					<td class="select">${assemp.assName }</td>
					<td>${assemp.education }</td>
					<td>${assemp.skill }</td>
					<td>${assemp.workExp }</td>
					<td>
						<div class="btn-group btn-xs">
							<button type="button" class="btn btn-default  btn-xs dropdown-toggle" id="dropdownMenu" data-toggle="dropdown">
								操作
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li>
									<a href="<%=request.getContextPath()%>
										/assemp/toUpdate.action?assemp.assId=${assemp.assId }&pageNum=${pager.pageNum}">修改
									</a>
								</li>
								<li>
									<a href="javascript:void(0)" onclick="del(${assemp.assId },this);">删除</a>
								</li>
							</ul>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
	
	<c:if test="${queryResult.list.size()<=0 }">
        <div class="alert alert-info" role="alert">
       		没有搜索结果
       	</div>
   </c:if>

	<div class="col-sm-offset-9">
		<a class="btn btn-success col-sm-4 btn-block" href="<%=request.getContextPath() %>/assemp/query.action"> 返回外派人才详情</a>
		<!-- 响应式布局，移动端才会有返回主页的按钮 -->
		<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
		<!-- / -->
	</div>

	<nav class="text-center">
		<ul class="pagination">
			<li>
				<a id="prevBtn" href="#">上页</a>
			</li>
			<li>
				<a>
					共检索出${queryResult.recordCount }记录，每页${pager.pageSize}条，当前是第${pager.pageNum }页
				</a>
			</li>
			<li>
				<a id="nextBtn" href="#">下页</a>
			</li>
		</ul>
	</nav>
</div>
<!--右侧内容完 -->
</div>

<!--网页底部-->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!--网页底部结束-->
</body>

</html>