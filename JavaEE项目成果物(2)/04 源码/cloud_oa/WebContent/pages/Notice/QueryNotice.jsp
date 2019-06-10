<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!doctype html>
<html>
<head>
<title>公告查询</title>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/gotoTop.js"></script>
			
	<style type="text/css">
		a.backToTop{width:60px; height:55px; border-radius:5px; opacity:0.5; background:#eaeaea url(../image/top.png) no-repeat -51px 0; text-indent:-999em}
		a.backToTop:hover{background-position:-113px 0}
	</style>

	<script type="text/javascript">
	 
    var pageNow=2;
    var pageCount;
	
	$(document).ready(function(e){
		
		/*向上滑动按钮*/
		$(".backToTop").goToTop();
		$(window).bind('scroll resize',function(){
			$(".backToTop").goToTop();
		});	
	
		/*给每一个置顶的公告添加样式*/
		 $('.isTop1').each(function(index, isTop1) {
			 $(this).removeClass('panel-success').addClass('panel-primary');
		 });
		 		 
		 //当文件滑动条滑动到底部的时候自动进行DIV的加载
		 $(window).scroll(function() {
             if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
            	 
            	 pageCount = $('#pageCount').val();

        		 if(pageNow<=pageCount){
         		 	var url = '<%=request.getContextPath()%>/notice/queryByPager2.action?pageNum=' + pageNow ;
           		 	$('#wholeNotice').append('<div class="newpanel' + pageNow + '"></div>');
        		 	$('.newpanel'+pageNow).load(url);
        		 	pageNow = pageNow + 1;
        			layer.msg('加载成功', {icon: 1});
        		 }
        		 else {
        			 layer.msg('没有更多了', {shift: 6});
        		 }
        		 
             }
         });
		
	});
	
	/*修改公告模态层修改*/
	function update(noticeId) {
		layer.open({
			type: 2,
			title: '修改公告',
			maxmin: false,
			shadeClose: false, //点击遮罩关闭层
			closeBtn:2,
			area : ['600px' , '580px'],
			content: ['<%=request.getContextPath()%>/notice/updateNotice.action?noticeId='+ noticeId,'no']
		});	
	}
	
	/*添加新公告模态层添加*/
	function insert() {
		layer.open({
			type: 2,
			title: '添加公告',
			maxmin: false,
			shadeClose: false, //点击遮罩关闭层
			closeBtn:2,
			area : ['600px' , '580px'],
			skin: 'layui-layer-lan',
			content: ['<%=request.getContextPath()%>/pages/Notice/AddNotice.jsp','no']
		});	
	}
	
	/*删除公告*/
	function delNotice(noticeId,btnObj) {			
		//询问框
			layer.confirm('确定要删除该数据吗', {
			btn: ['确定','取消'], //按钮
			closeBtn:2,
			shade: false //显示遮罩
		}, function(){
			/*异步删除*/
			var url = '<%=request.getContextPath()%>/notice/delete.action?noticeId=' + noticeId ;
			$.get(url,function(data){
				$(btnObj).parent().parent().parent().parent().parent().delay(300).slideUp("fast");
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
	<!-- 网页头部完成-->
	
	<!-- 每个模块的部分 -->
	<div class="container">
		<div class="col-md-12">
		
		<div>
		<h3 class="page-header">
			公告栏 
			
			<!-- 添加新的公告，通过shiro拦截只有管理员才可以操作 -->
			<shiro:hasRole name="superadmin">
				<a class="btn btn-primary pull-right" 
				href="javascript:void(0)" onclick="insert();">增加新公告</a>
			</shiro:hasRole>
			<!-- 通过shiro进行拦截操作完  -->
			
		</h3>
		
		</div>
		<input id="pageCount" type="hidden" value="${pager.pageCount }">
		
		<div id="wholeNotice">
			<c:forEach items="${list}" var="list">
	          	<div class="panel panel-success isTop${list.isTop}">
	        		<div class="panel-heading">
	            		${list.noticeTitle }
						<a href="#two${list.noticeId }" data-toggle="collapse" class="panel-title pull-right">展开/折叠</a>
	            	</div>
	            	<div>
		            	<div id="two${list.noticeId }" class="panel-collapse collapse in">
			        		<div class="panel-body">
			        			<fmt:formatDate value="${list.time}" pattern="yyyy-MM-dd"/>
			        			<br>
			        			
			        			${list.content }
			        			
				        		<!-- 通过shiro拦截 只有管理员才可以看到-->
				        		<shiro:hasRole name="superadmin">
				        			<hr>
				        			<div class="pull-right">	        			
					        			<a class="btn btn-default" href="javascript:void(0)" onclick="update(${list.noticeId});">修改</a>
					        			<a class="btn btn-default" href="javascript:void(0)" onclick="delNotice(${list.noticeId },this);">删除</a>
				        			</div>
			        			</shiro:hasRole>
			        			<!-- 通过shiro拦截 修改操作完成-->
	
			        		</div>
		        		</div>
		        	</div>	
	        	</div>  
			</c:forEach>
		
		</div>	
	  </div> 	 
	</div>
	
	<!--网页底部-->
	<footer>
		<jsp:include page="/include/footer.jsp"></jsp:include>
	</footer>
	<!--网页底部结束-->
</body>

</html>