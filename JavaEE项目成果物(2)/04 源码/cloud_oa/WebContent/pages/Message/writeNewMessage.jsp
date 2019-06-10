<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.icss.oa.message.pojo.*"%>
<%@ page import="com.icss.oa.system.pojo.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>在线消息</title>
	<meta name="viewport" charset="width=device-width, initial-scale=1"	user-scalable="no">
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/pages/Message/js/kindeditor/themes/default/default.css" />
	<script charset="utf-8" src="<%=request.getContextPath()%>/pages/Message/js/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="<%=request.getContextPath()%>/pages/Message/js/kindeditor/zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/Validform/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		$(document).ready(function(e) {
			//转换textarea到kindeditor，简化类型编辑器
	    	var editor = KindEditor.create('textarea', {
	    		resizeType : 0,
	    		allowPreviewEmoticons : true,
	    		allowImageUpload : false,
	    		height:'300px',
	    		items : [
	    			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
	    			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	    			'insertunorderedlist', '|', 'emoticons', 'link'],
	    		afterChange : function(){	
	    			if(!this.isEmpty()){
						$("#craft").removeAttr("disabled");
						if($('input[name="recipientIdList"]').length!=1){
							$("#send").removeAttr("disabled");
						}
					}else{
						$("#craft").attr("disabled","disabled");
						$("#send").attr("disabled","disabled");
					}}
	    	}); 
			
			function changeButton(){
				if(!editor.isEmpty()){
					$("#craft").removeAttr("disabled");
					if($('input[name="recipientIdList"]').length!=1){
						$("#send").removeAttr("disabled");
					}
				}else{
					$("#craft").attr("disabled","disabled");
					$("#send").attr("disabled","disabled");
				}
			}
			changeButton();
			
			//form分开草稿和发送消息
			$("#craft").click(function(){
		    	editor.sync();
				$("#Message").attr("action","<%=request.getContextPath()%>/message/saveAsDraft.action#");
	    		$("#Message").submit();
			});
			$("#send").click(function(){
		    	editor.sync();
				$("#Message").attr("action","<%=request.getContextPath()%>/message/sendMessage.action#");
				$("#Message").submit();
			});
			
			$('#selectRecipient').click(function(){
				layer.open({
					type: 2,
					area: ['420px', '620px'],
					title: '请选择收件人',
					closeBtn:2,
					shadeClose: true, //点击遮罩关闭
					content: '<%=request.getContextPath()%>/message/toSelectReceiver.action',
					end: function(){
						$('#recipientName').val("");
						$('input[name="recipientName"]').each(function (){
							$('#recipientName').val($('#recipientName').val()+$(this).val());
						});
					}
				}); 
				changeButton();
			});
			
			//传入的message
			if(${message.recipientId!=null}||${param.recipientId!=null}){
				$('#recipientIdList').after('<input name="recipientIdList" value="${param.recipientId}${message.recipientId}" type="hidden"/><input name="recipientName" type="hidden" value="${param.recipientName}${recipientName}; ">');
				$('#recipientName').val("");
				$('input[name="recipientName"]').each(function (){
					$('#recipientName').val($('#recipientName').val()+$(this).val());
				});
			}
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
		<div class="col-md-3" id="myScrollSpy" style="margin-top: 30px;">
			<dropdown class="col-xs-12" style="margin-bottom:30px;"> 
				<input id="toggle1" type="checkbox" checked>
				<label for="toggle1"class="animate">在线消息<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/message/toSendMessage.action">
						<li class="animate actived">发送新消息<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/message/showNotRead.action">
						<li class="animate">未阅消息<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath()%>/message/showSendedMessage.action">
	                	<li class="animate">发件箱<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/message/showDraft.action">
	                	<li class="animate">草稿箱<i class="fa fa-code float-right"></i></li>
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
			<h3 class="page-header" style="margin-top: 0px;">发送新消息</h3>

			<form id="Message" method="post">
				<table class="table">
					<tr>
						<td class="col-md-2" style="text-align:center;">收件人</td>
						<td class="col-md-10">
							<input id="recipientName" class="btn-block form-control" type="text" readonly/>
							<br>
							<input id="selectRecipient" value="选择收件人" type="button"	class="btn btn-primary btn-sm" />
							<input id="recipientIdList" name="recipientIdList" type="hidden"/>
							<input name="messageId" value="0${message.messageId}" type="hidden"/>
							<input name="senderId" value="<%=(int) ((Employee)request.getSession().getAttribute("queryemp")).getEmpId()%>" type="hidden" />
						</td>
					</tr>
					<tr>
						<td style="text-align:center;">内容</td>
						<td><textarea id="content" class="form-control" maxlength="1000" rows="12" style="width: 100%" name="content" autofocus="autofocus">${message.content}${param.content}</textarea></td>
					</tr>
				</table>

				<input value="发送" type="submit" class="btn btn-primary pull-right"	id="send" disabled="disabled" />
				<div class="pull-right col-md-1"></div>
				<input value="保存到草稿" type="submit" class="btn btn-primary pull-right" id="craft" disabled="disabled" />
		
			</form>
		</div>
		<!-- 右侧内容完成 -->

	</div>
	<hr>
	<!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>
</html>