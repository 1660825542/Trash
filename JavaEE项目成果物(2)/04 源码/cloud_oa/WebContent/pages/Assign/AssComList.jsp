<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.icss.oa.assign.pojo.*" %>
<!doctype html>
<html>
<head>
<title>对象公司列表</title>
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
		function del(assComId,btnObj) {	
			//询问框
			layer.confirm('确定要删除该数据吗', {
				closeBtn:2,
				btn: ['确定','取消'], //按钮
				shade: false //显示遮罩
			}, function(){
				/*异步删除*/
				var queryUrl = '<%=request.getContextPath()%>/asscom/queryExist.action?assComId=' + assComId;
				var url = '<%=request.getContextPath()%>/asscom/delete.action?assComId=' + assComId;
				
				$.get(queryUrl,function(data){
					if(data > 0){
						layer.confirm('该公司有'+data+'条人才需求，是否强制删除？',{
							closeBtn:2,
							btn:['确定','取消'],
							shade:false
						},function(){
							$.get(url,function(data){
								$(btnObj).parent().parent().parent().parent().parent().remove();
								layer.msg('删除成功', {icon: 1});
							})
						})
						
						
					}
					else {
						$.get(url,function(data){
							$(btnObj).parent().parent().parent().parent().parent().remove();
							layer.msg('删除成功', {icon: 1});
						})
					}
				});
				
				
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
				<li class="animate actived">合作公司列表<i class="fa fa-code float-right"></i></li>
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
			<h3 class="page-header">合作公司信息列表</h3>

			<table  class="table table-striped table-bordered table-condensed table-hover">
				<thead>
					<tr>
						<th>公司编号</th>
						<th>公司名称</th>
						<th>联系人姓名</th>
						<th>联系人电话</th>
						<th>联系人邮箱</th>
						<th>备注</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="asscom">
						<tr>
							<td>${asscom.assComId }</td>
							<td>${asscom.comName }</td>
							<td>${asscom.contactName }</td>
							<td>${asscom.contactPhone }</td>
							<td>${asscom.contactQq }</td>
							<td>${asscom.remarks }</td>
							<td class="text-center">
								<div class="btn-group btn-xs">
									<button type="button" class="btn btn-default btn-xs dropdown-toggle" id="dropdownMenu" data-toggle="dropdown">
										操作
										<span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li>
											<a href="<%=request.getContextPath()%>/asscom/queryByCondition.action?assComId=${asscom.assComId }">
											查看需求
											</a>
										</li>
										<li>
											<a href="<%=request.getContextPath()%>
												/asscom/toUpdate.action?assComId=${asscom.assComId }&pageNum=${pager.pageNum}">修改
											</a>
										</li>
										<li>
											<a href="#" onclick="del(${asscom.assComId },this);">删除</a>
										</li>
									</ul>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="col-sm-offset-8">
				<a href="<%=request.getContextPath() %>
					/pages/Assign/AddAssCom.jsp?pageNum=${pager.pageCount}" class="btn btn-primary btn-block">增加新的合作企业
				</a>
				<!-- 响应式布局，移动端才会有返回主页的按钮 -->	
				<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">	
				<!-- / -->	
			</div>
			<!-- 包含分页 -->	
			<jsp:include page="/include/AdvancedPager.jsp"/>	
		</div>
		<!--右侧内容完 -->	

	</div>

	<!--网页底部-->	
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->	
	</body>

</html>