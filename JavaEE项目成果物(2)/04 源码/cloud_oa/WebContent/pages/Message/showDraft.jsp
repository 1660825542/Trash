<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.icss.oa.message.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>在线消息</title>
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no">
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/Message/js/message.js"></script>
	<link href="<%=request.getContextPath()%>/pages/Message/css/message.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/Validform/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="../css/style-bar.css">
	<script type="text/javascript">		
		$(document).ready(function(e) 
		{ 
			/*单击跳转到写新消息*/
	 		$('td').not($('td').has('input')).click(function(e){
	 			var urlStr = "<%=request.getContextPath()%>/message/toSendMessage.action"
					+"?"+"messageId="+$(this).siblings('td[name="messageId"]').text()+"#";
	 			location.assign(urlStr);
	 		});	
			//改变全文检索按钮的可用性
	 		function changeButton(){
				if($('input[name="keyword"]').val().replace(/\s/g, "") != ""){
					$("#search").removeAttr("disabled");
				}else{
					$("#search").attr("disabled","disabled");
				}
			}
			changeButton();
			//控制按钮可不可点
			$('input[name="keyword"]').keyup(function (){changeButton();});	
			$('input[name="keyword"]').change(function (){changeButton();});	

			$('input[type="checkbox"]').click(function(){checkAll();});		
		});
	</script>
</head>
<body>	
	<!-- 网页头部-->
	<jsp:include page="/include/header.jsp"></jsp:include>
	<!-- 网页头部完成-->
	
	<!-- 在线消息 -->
	<div class="container">
	
	<!-- 左侧边栏 -->     
        <div class="col-md-3" id="myScrollSpy" style="margin-top:30px;">
        	<dropdown class="col-xs-12" style="margin-bottom:30px;"> 
				<input id="toggle1" type="checkbox" checked>
				<label for="toggle1"class="animate">在线消息<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/message/toSendMessage.action">
						<li class="animate">发送新消息<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/message/showNotRead.action">
						<li class="animate">未阅消息<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath()%>/message/showSendedMessage.action">
	                	<li class="animate">发件箱<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/message/showDraft.action">
	                	<li class="animate actived">草稿箱<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/message/toMyMessage.action">
	                	<li class="animate">收件箱<i class="fa fa-cog float-right"></i></li>
	                </a>
				</ul>
			</dropdown>
		</div>
    <!-- 左侧边栏完成 -->

	<!-- 右侧内容-->
        <div class="col-md-9">
        	<h3 class="page-header" style="margin-top:0px;">
				我的草稿箱
			</h3>
			
			<!-- 操作列表  -->
			<div class="row">        	
            	<div class="col-sm-6">
					&nbsp;
		      		<input type="checkbox" name="selectAll" onclick="selectAll(this);">
		      		<label>全选</label>
		      		&nbsp;
		          	<input id="delete" type="button" value="删除" class="btn btn-primary btn-sm" onclick="submitList('deleteDraft.action');">
     			</div>
     			
     			<form method="post" action="<%=request.getContextPath()%>/message/queryDraft.action#" class="form-group">            	
            		<div class="input-group col-md-4"> 
	            		<input name="keyword" type="text" class="form-control" autofocus="autofocus" value="${keyword}">
	                	<span class="input-group-btn">
	                 		<button id="search" type="submit" class="btn btn-primary" disabled="disabled">搜索</button>
	                	</span>
                	</div>
               	</form>  
            </div>
			
			<!-- 展示表格  -->
			<table class="table table-hover">
				<tr>
					<th width="33px"></th>
					<th width="67px">收件人</th>
					<th width="549px">内容</th>		
					<th width="171px">时间</th>
				</tr>
				<c:forEach items="${list}" var="message">
					<tr style="cursor:pointer">
						<td><input name="selected" type="checkbox"></td>						
						<td hidden name="messageId"><c:out value="${message.messageId}"/></td>
						<td hidden name="recipientId"><c:out value="${message.recipientId}"/></td>
						<td name="empName"></td>
						<td>${message.summary}</td>
						<td><c:out value="${message.sendDate}"/></td>						
					</tr>
				</c:forEach>
			</table>
						
			<!-- 分页条 -->
			<jsp:include page="/pages/Message/include/AdvancedPager.jsp"></jsp:include>
			
		</div>
	<!-- 右侧内容完成 -->
	
	</div>
	<hr>
	<!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>
</html>