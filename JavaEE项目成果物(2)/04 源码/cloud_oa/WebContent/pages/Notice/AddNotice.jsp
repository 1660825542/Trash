<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加公告</title>
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/js/summernote/summernote.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/summernote/summernote.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/summernote/summernote-zh-CN.js"></script>

<script type="text/javascript">

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	var comReqId;
	var posReq;
	var typeOfWork;
	var salaryPermonth;
	
	//获得当前的时间
	var d = new Date();
	var currentdate = d.getFullYear()+ "-" + (d.getMonth()+1)+ "-" + d.getDate();
	
	$(document).ready(function(){
		//表单验证
		$('#updateNotice').Validform({
			tiptype:2,
			ajaxPost:true,
			callback:function(data){
				parent.layer.msg('修改成功');
				parent.location.reload();
				parent.layer.close(index);
			}
		});

		//通过传来的参数给单选框设置默认值
		var selectedState = $('#getState').val();
		
		if(selectedState=="1"){$("#radio1").attr("checked","checked");}
		if(selectedState=="0"){$("#radio2").attr("checked","checked");}

		//富文本编辑器设置
		$('.summernote').summernote({
		    height: 250,
		    tabsize: 2,
		  	lang: 'zh-CN', // default: 'en-US'
				toolbar: [
		   		 //[groupname, [button list]]		      
		   		 ['style', ['bold', 'italic', 'underline', 'clear']],
		   		 ['font', ['strikethrough']],
		  		 ['fontsize', ['fontsize']],
		    	 ['color', ['color']],
		    	 ['para', ['ul', 'ol', 'paragraph']],
		    	 ['height', ['height']],
		    	 ['misc', ['undo','redo','fullscreen']],				    
		  		]
		});
		
		//将富文本编辑器内容传入隐藏域的文本框中
	  	$('#transmit').bind('click',function(){
		   var textContent = $('.note-editable').html();
		   $('#content').val(textContent);   
	   	});
		
		//获得当前时间并传递给隐藏域的文本框中
		$('#time').val(currentdate);
	   
	});

</script>
</head>

<body>

<div class="container-fluid" style="margin:10px">
	<div class="row">
		<form id="updateNotice" class="form-horizontal" method="post" action="<%=request.getContextPath()%>/notice/insert.action">
			<input type="hidden" value="" name="time" id="time" class="form-control">
			<div id="getTime" style="display:none"><fmt:formatDate value="${notice.time}" pattern="yyyy-MM-dd"/></div>
			
			<div class="form-group">
				<label for="noticeTitle" class="col-sm-2 control-label">公告标题</label>
				<div class="col-sm-10">
					<input type="text" datatype="*" placeholder="请输入公告标题" name="noticeTitle" id="noticeTitle" class="form-control">
				</div>
			</div>
					
			<input type="hidden" id="getState" value="${notice.isTop}">
									
			<div class="form-group">
				<label class="col-sm-2 control-label">是否置顶</label>
				<div class="radio col-sm-10" id="state">
					<label for="radio1" class="radio-inline">
						<input type="radio" id="radio1" name="isTop" value="1">置顶</label>
					<label for="radio2" class="radio-inline">
						<input type="radio" id="radio2" name="isTop" value="0" checked="checked">不置顶</label>
				</div>
			</div>

			<div class="form-group">
				<label for="NoticeContent" class="col-sm-2 control-label">公告内容</label>
				<div class="col-sm-10">
					<textarea class="summernote">${notice.content }</textarea>
				</div>
			</div>

			<input type="hidden" name="content" id="content" value="">

			<div class="form-group">
				<div class="col-sm-10 col-sm-offset-2">
					<input type="submit" class="btn btn-primary btn-block" id="transmit" value="确定添加">
				</div>
			</div>
		</form>			
	</div>
</div>

</body>
</html>