<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
<!DOCTYPE html>
<html class="no-js" http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <head>
        <meta charset="utf-8">
        <title>系统未响应</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="myErrorPage">
        <meta name="author" content="">
        
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css">

		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function(e) {
		        
				//初始化弹出框的功能
				$('button[data-toggle="popover"]').popover();
				
				//显示弹出框
				$('#btn1').popover('show');
				
				//显示事件
				$('#btn2').on('shown.bs.popover',function(e){
					alert('弹出框被显示');	
				});
				
				//关闭事件
				$('#btn2').on('hidden.bs.popover',function(e){
					alert('弹出框被关闭');	
				});
		    });
		</script>
		
    </head>

    <body style="background-color:#304D5B">
    	<div id="photo" style="padding-top:15%;font-family:微软雅黑;margin:0 auto;width:700px">
	   		<div class="media" style="background-color:fff;border:5px solid #fff;padding:10 5 10 20px;border-radius:5px;height:280px">
	        	<a class="media-left pull-right">
	        		<img src="<%=request.getContextPath()%>/image/rocket.jpg" width="250" height="250">
	        	</a>
	            <div class="media-body">
	            	<h2>抱歉，这个页面出错了！</h2>
	            	<br>
	            	<h4>错误原因：</h4>
	            	<h4>1. 您进行了错误的操作</h4>
	            	<h4>2. 系统内部出现错误</h4>
	            	<br>
	            	<p>
	            		<a class="btn btn-warning"  onclick="javascript:document.location.href='<%=request.getContextPath()%>/pages/index.jsp'">返回主页</a>
	            		<button type="button" class="btn btn-warning" data-toggle="popover" data-content="管理员电话：13800138000" data-placement="right">
        					联系管理员
        				</button>
	            	</p>
	            </div>
	        </div>
   		</div>
   		
    </body>

</html>