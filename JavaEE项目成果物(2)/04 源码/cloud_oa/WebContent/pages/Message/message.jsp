<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.icss.oa.message.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>消息详情</title>
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/Message/js/kindeditor/themes/default/default.css" />
	<script charset="utf-8" src="<%=request.getContextPath()%>/pages/Message/js/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/pages/Message/js/kindeditor/zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function(e) 
	{ 
		//转换textarea到kindeditor，简化类型编辑器
		var editor = KindEditor.create('textarea', {
			resizeType : 0,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			width: '100%',
			readonlyMode: true,
			height:'200px',
			items : []
		}); 
		
		//设置不同的表单提交内容
		$("#forward").click(function(){
			$('textarea').attr("name","content");
			$('form').submit();
			parent.layer.closeAll();
 		});
		$("#answer").click(function(){
 			$('#recipientId').attr("name","recipientId");
 			$('#recipientName').attr("name","recipientName");
 			$('form').submit();
 			parent.layer.closeAll();
		});
	});
	</script>
	
</head>
<body>
    <div class="container">
    
    	<form class="form-horizontal" style="margin-top:30px;" target="_top" method="post" action="<%=request.getContextPath()%>/pages/Message/writeNewMessage.jsp#">
        	<div class="form-group"> 
            	<c:choose>
					<c:when test="${state==1}">
		                <label class="col-xs-2 control-label text-right">收件人</label>
		                <div class="col-xs-5">
		                	<input id="recipientName" type="text" class="form-control" readonly value="${recipientName}">
		                	<input id="recipientId" type="hidden" value="${message.recipientId}">            
		                </div>
		            </c:when>
		            <c:when test="${state==0}">
		           		<label class="col-xs-2 control-label text-right">发件人</label>
		                <div class="col-xs-5">
		                	<input id="recipientName" type="text" class="form-control" readonly value="${recipientName}">
		                	<input id="recipientId" type="hidden" value="${message.senderId}">            
		                </div>
		            </c:when>
		            <c:when test="${state==-1}">
		           		<label class="col-xs-2 control-label text-right">您自己</label>
		                <div class="col-xs-5">
		                	<input id="recipientName" type="text" class="form-control" readonly value="${recipientName}">
		                	<input id="recipientId" type="hidden" value="${message.senderId}">            
		                </div>
		            </c:when>
		        </c:choose>
            </div>
            
            <div class="form-group"> 
                <label class="col-xs-2 control-label text-right">时间</label>
                <div class="col-xs-5">
                	<input type="text" class="form-control" readonly value="${message.sendDate}">               
                </div>
            </div>
            
            <div class="form-group"> 
                <label class="col-xs-2 control-label text-right">内容</label>
                <div class="col-xs-9">
                	<textarea class="form-control">${message.content}</textarea>              
                </div>
            </div>  
            
            <div class="form-group">
            	<div class="col-xs-1 col-xs-offset-7">
            		<button id="answer" type="submit" class="btn btn-primary">  回  复  </button> 
                </div>
                <div class="col-xs-1 col-xs-offset-1">
            		<button id="forward" type="submit" class="btn btn-primary">  转  发  </button> 
                </div>
            </div>                 
        </form>
    </div>
</body>
</html>