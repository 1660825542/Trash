<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no">
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/js/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/zTree/js/jquery.ztree.all-3.5.js"></script>
	<link rel="stylesheet" type="text/css" href="../../css/common.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">

<script type="text/javascript">
	var setting = {
		view: {
				selectedMulti: false
			},
		edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
		data: {
			keep: {
				parent:true,
				leaf:true
			},
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			onClick: showContent
		}
	};

	function showContent(event, treeId, treeNode, clickFlag) {
		$('#currentFolder').load("<%=request.getContextPath()%>/cloud/folderDetail.action?folderId="+treeNode.id);
	}	
		
	function editFolder(folderId){
		$('#folderNameDiv').removeClass("has-success has-feedback");
		$('#folderNameDiv').removeClass("has-error has-feedback");
		
		$('#folderInfo').modal({
			backdrop:'static', /*点击黑色区域不关闭*/
			keyboard:false     /*ESC键不关闭*/
		});
		
		$('#addBtn').hide();
		$('#setBtn').show();
		$('#folderInfo h4').text("修改目录信息");
		$('#folderId').val(folderId);
		$('#folderName').val($('#oriFolderName').val());
		$('#folderDescription').val($('#oriFolderDescription').val());
		if($('#oriFolderShare').val()==0){
			$('#fs1').removeProp("selected");   
			$('#fs0').prop("selected",true);   
		}else{
			$('#fs0').removeProp("selected");   
			$('#fs1').prop("selected",true);    
		}
		
		
		
		$('#folderSet').click(function(){
			layer.load();
			if($('#folderName').val()==null || $('#folderName').val()=='' || $('#folderName').val().length>255)
			{
				layer.closeAll('loading');
				$('#folderNameDiv').addClass("form-group has-error has-feedback");
			}
			else if($('#folderName').val()!=null &&  $('#folderName').val().length<=255){
				$('#folderNameDiv').addClass("form-group has-success has-feedback");
				var folderName=$('#folderName').val();
				$.post('<%=request.getContextPath()%>/cloud/update.action',{folderId:$('#folderId').val(),folderName:$('#folderName').val(),folderDescription:$('#folderDescription').val(),folderShare:$('#folderShare').val()},
					function(){
					var zTree = $.fn.zTree.getZTreeObj("tree");
					nodes = zTree.getSelectedNodes(),treeNode = nodes[0];
					treeNode.name=folderName;
					zTree.updateNode(treeNode);
					loadDetail($('#oriFolderId').val());
					layer.closeAll('loading');
					layer.msg('修改成功', {icon: 1});
				});
				$('#closeBtn').click();
				$('#folderSet').unbind('click');
			}
		});
	}
	
	
	function editChildFolder(folderId){
		$('#folderNameDiv').removeClass("has-success has-feedback");
		$('#folderNameDiv').removeClass("has-error has-feedback");
		
		$('#folderInfo').modal({
			backdrop:'static', /*点击黑色区域不关闭*/
			keyboard:false     /*ESC键不关闭*/
		});
		
		var folderName = $('#folder'+folderId).prev().prev().prev().val();
		var folderDescription = $('#folder'+folderId).prev().val();
		var folderShare = $('#folder'+folderId).prev().prev().val();
		$('#addBtn').hide();
		$('#setBtn').show();
		$('#folderInfo h4').text("修改目录信息");
		$('#folderId').val(folderId);
		$('#folderName').val(folderName);
		$('#folderDescription').val(folderDescription);
		if(folderShare==0){
			$('#fs1').removeProp("selected");   
			$('#fs0').prop("selected",true);   
		}else{
			$('#fs0').removeProp("selected");   
			$('#fs1').prop("selected",true);    
		}
		
		$('#folderSet').click(function(){
			layer.load();
			if($('#folderName').val()==null || $('#folderName').val()=='' ||  $('#folderName').val().length>255)
			{
				layer.closeAll('loading');
				$('#folderNameDiv').addClass("form-group has-error has-feedback");
			}
			else if($('#folderName').val()!=null &&  $('#folderName').val().length<=255){
				$('#folderNameDiv').addClass("form-group has-success has-feedback");
				id=$('#folderId').val();
				folderName= $('#folderName').val();
				$.post('<%=request.getContextPath()%>/cloud/update.action',{folderId:$('#folderId').val(),folderName:$('#folderName').val(),folderDescription:$('#folderDescription').val(),folderShare:$('#folderShare').val()},
					function(){
					var zTree = $.fn.zTree.getZTreeObj("tree");
					treeNode = zTree.getNodeByParam('id', id);
					treeNode.name=folderName;
					zTree.updateNode(treeNode);
					loadDetail($('#oriFolderId').val());
					layer.closeAll('loading');
					layer.msg('修改成功', {icon: 1});
				});
				
				$('#closeBtn').click();
				$('#folderSet').unbind('click');
			}
		});
	}
	
	function loadDetail(folderId){
		layer.load();
		$('#currentFolder').load('<%=request.getContextPath()%>/cloud/folderDetail.action?folderId='+folderId,
				function(){
			var zTree = $.fn.zTree.getZTreeObj("tree");
			zTree.selectNode(zTree.getNodeByParam('id', folderId),true);
			layer.closeAll('loading');
		});
	}
	function delFolder(folderId){
		layer.confirm('你确定要删除此目录以及其下所有目录和文件吗？', {
			btn: ['确定','取消'], //按钮
			closeBtn:2,
			icon: 3,
			shade: false
		}, function(){
			layer.load();
			$.get('<%=request.getContextPath()%>/cloud/delete.action?folderId='+ folderId,
				function(data){
				var zTree = $.fn.zTree.getZTreeObj("tree");
				treeNode = zTree.getNodeByParam('id', folderId);
				zTree.removeNode(treeNode);
				loadDetail(data);
				layer.closeAll('loading');
				layer.msg('删除成功！', {icon: 1});
			});
		});
	}
	
	function editShare(folderId){
		layer.confirm('你确定要修改此目录及其子目录共享状态吗？', {
			btn: ['确定','取消'], //按钮
			closeBtn:2,
			icon: 3,
			shade: false
		}, function(){
			layer.load();
			$.get('<%=request.getContextPath()%>/cloud/changeShare.action?folderShare='+$('#oriFolderShare').val()+'&folderId=' + folderId,
				function(){
				$('#currentFolder').load('<%=request.getContextPath()%>/cloud/folderDetail.action?folderId='+$('#oriFolderId').val(),
						function(){
					layer.closeAll('loading');
					layer.msg('设置成功！', {icon: 1});
				});
			});
		});
	}
	function addFolder(folderId){
		$('#folderNameDiv').removeClass("has-success has-feedback");
		$('#folderNameDiv').removeClass("has-error has-feedback");
		
		$('#folderInfo').modal({
			backdrop:'static', /*点击黑色区域不关闭*/
			keyboard:false     /*ESC键不关闭*/
		});
		
		$('#folderInfo h4').text("增加新目录");
		$('#folderId').val(folderId);
		$('#setBtn').hide();
		$('#addBtn').show();
		
		
	}


	function download(fileId){
		$('#download'+fileId).submit();
	}
	
	function editFile(fileId){
		$('#fileNameDiv').removeClass("has-success has-feedback");
		$('#fileNameDiv').removeClass("has-error has-feedback");
		
		$('#fileInfo').modal({
			backdrop:'static', /*点击黑色区域不关闭*/
			keyboard:false     /*ESC键不关闭*/
		});
		
		var fileName = $('#file'+fileId).prev().val();
		var index = fileName.lastIndexOf('.');
		$('#fileId').val(fileId);
		$('#fileName').val(fileName.substr(0,index));
		$('#fileType').val(fileName.substring(index,fileName.length));
	}
	
	function deleteFile(fileId){
		var fileName = $('#file'+fileId).prev().val();
		
		layer.confirm('你确定要删除'+fileName+'吗？', {
			btn: ['确定','取消'], //按钮
			closeBtn:2,
			icon: 3,
			shade: false
		}, function(){
			layer.load();
			$.get('<%=request.getContextPath()%>/file/deleteFile.action?fileId='+ fileId,
				function(){
				loadDetail($('#oriFolderId').val());
				layer.closeAll('loading');
				layer.msg('删除成功！', {icon: 1});
			});
		});
	}
	
	var finished = false; //上传是否结束
	
	function showStatus(){
		if(finished){
			layer.load();
			layer.msg('正在处理中，请稍候');
			finishActivity();
		} else{
			$.get('<%=request.getContextPath()%>/file/getProgress.action',function(data){
				$('#bar').attr('style','width:'+data+'%');
				$('#bar').attr('aria-valuenow',data);
				$('#progressBar span').html(data+'%');
				if((data+'%')=='100%'){
					finished = true;
				}
				setTimeout(showStatus(),1000);
			});
		}
	}
	var ok=false;
	function finishActivity(){
		if(ok){
			layer.closeAll('loading');
			$('#closeBtn3').click();
			layer.msg('上传成功！', {icon: 1});
			loadDetail($('#fileFolder').val());
		}else{
			$.get('<%=request.getContextPath()%>/file/getOK.action',function(data){
				if(data=="ok"){
					ok=true;
				}
				setTimeout(finishActivity,1000);
				
			});
		}
		
	}
	
	function upload(folderId){
		finished=false;
		$('#fileFolder').val(folderId);
		$('#data').remove();
		$('#files').append('<input type="file" name="data" id="data" class="form-control">');
		$('#closeBtn3').show();
		$('#progressBar').hide();
		$('#fileDiv').show();
		$('#fileUpload').modal({
			backdrop:'static', /*点击黑色区域不关闭*/
			keyboard:false     /*ESC键不关闭*/
		});
		$('#uploadForm').attr('action','<%=request.getContextPath()%>/file/upload.action?fileFolder='+folderId);
		
	}
	
	function uploadClick(){
		var file_field = document.getElementById('data');
		if(file_field.value=="" || file_field.value==null){
			layer.msg('请选择上传的文件！', {icon: 0});
			return false;
		}
        
		var filesize = file_field.files[0].size;
		if(filesize<=52428800){
			$.get('<%=request.getContextPath()%>/cloud/getLeftSize.action',function(data){
				if(data>=filesize){
					$('#bar').attr('style','width:0%');
					$('#bar').attr('aria-valuenow',0);
					$('#progressBar span').html('0%');
					$('#closeBtn3').hide();
					$('#fileDiv').hide();
					$('#progressBar').show();
					ok=false;
					finished=false;
					$('#uploadForm').ajaxComplete(
					showStatus());
				} else{
					layer.msg('您的剩余空间已经不足！', {icon: 0});
					return false;
				}
			});
			
		}else {
			layer.msg('请不要上传超过50M的文件，请重新选择！', {icon: 0});
			return false;
		}
	}

	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		return true;
	}
	
	$(document).ready(function(){
		$.getJSON('<%=request.getContextPath()%>/cloud/getFolderTree.action',function(data){
			$.fn.zTree.init($("#tree"), setting, data);	
			$('#tree_1_a').click();
		});
		
		$('#closeBtn').click(function(){
			$('#folderId').val('');
			$('#folderName').val('');
			$('#folderDescription').val('');
			$('#fs0').attr("selected");   
			$('#fs1').removeAttr("selected");   
		});
		
		$('#folderAdd').click(function(){
			layer.load();
			if($('#folderName').val()==null || $('#folderName').val()=='' ||  $('#folderName').val().length>255)
			{
				layer.closeAll('loading');
				$('#folderNameDiv').addClass("form-group has-error has-feedback");
			}
			else if($('#folderName').val()!=null &&  $('#folderName').val().length<=255){
				$('#folderNameDiv').addClass("form-group has-success has-feedback");
				id=$('#folderId').val();
				folderName= $('#folderName').val();
				$.post('<%=request.getContextPath()%>/cloud/insert.action',{folderParent:$('#folderId').val(),folderName:$('#folderName').val(),folderDescription:$('#folderDescription').val(),folderShare:$('#folderShare').val()},
						function(data){
					var zTree = $.fn.zTree.getZTreeObj("tree");
					treeNode = zTree.getNodeByParam('id', id);
					treeNode = zTree.addNodes(treeNode, {id:data, pId:id, isParent:true, name:folderName});
					loadDetail(data);
					layer.closeAll('loading');
					layer.msg('新增成功！', {icon: 1});
				});
				$('#closeBtn').click();
			}
		});
		
		$('#closeBtn2').click(function(){
			$('#fileId').val('');
			$('#fileName').val('');
			$('#fileType').val('');
		});
		
		$('#setFileBtn').click(function(){
			layer.load();
			if($('#fileName').val()==null || $('#fileName').val()=='' || $('#fileName').val().length>255)
			{
				layer.closeAll('loading');
				$('#fileNameDiv').addClass("form-group has-error has-feedback");
			}
			else if($('#fileName').val()!=null && $('#fileName').val().length<=255){
				$('#fileNameDiv').addClass("form-group has-success has-feedback");
				$.post('<%=request.getContextPath()%>/file/updateFile.action',{fileId:$('#fileId').val(),fileName:$('#fileName').val()+$('#fileType').val()},
						function(){
					loadDetail($('#oriFolderId').val());
					layer.closeAll('loading');
					layer.msg('修改成功', {icon: 1});
				});
				$('#closeBtn2').click();
			}
		});
		
		$('#uploadBtn').click(uploadClick);
	});
</script>
<title>云目录</title>

</head>

<body>

	<!-- 网页头部-->

	<jsp:include page="/include/header.jsp"></jsp:include>
	
	<!-- 每个模块的部分 -->
	<div class="container">
		<h2 class="page-header">
			云文件目录		
		</h2>	  
		<!-- 侧边栏树形结构-->     
		<div class="col-md-3"> 
	        <div class="panel panel-primary" >
	        	<div class="panel-heading" style="font-weight:bolder">
	        		我的云目录
	            </div>
	            <div id="folderTree" class="panel-body">
	            	<ul id="tree" class="ztree"></ul>
				</div>
			</div>    
			<div class="panel panel-primary">
				<div class="panel-heading" style="font-weight:bolder">
	        		<a style="color:white;" href="cloudshare.jsp">我的云共享</a>
	            </div>  
	       	</div>
		</div>
        <!-- 侧边栏完成    -->
         
        <!-- 右侧内容-->
		<div id="currentFolder" class="col-md-offset-3">
			
	    </div>  
		<!--右侧内容完 -->
	</div>	
<iframe name="hiddenUpload" width="0" height="0"></iframe>
	<!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
	
	<!--文件夹模态层-->
    <div class="modal fade" id="folderInfo" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-header">
                	<button id="closeBtn" type="button" class="close" data-dismiss="modal">&times;</button>
                	<h4 class="text-center"></h4>
                </div>
            	<div class="modal-body">
                	<div id="folderForm" class="form-horizontal">
			        	<div class="form-group"> 
			                <div class="col-sm-6">
			                	<input type="hidden" name="folderId" id="folderId" class="form-control">               
			                </div>
			            </div>
			            
			            <div id="folderNameDiv" class="form-group">
			                <label class="col-sm-4 control-label">目录名称</label>
			                <div class="col-sm-6">
			                	<input type="text" name="folderName" id="folderName" class="form-control" placeholder="请输入文件夹名称 ">              
			                </div>
			            </div>
			            
			            <div class="form-group">
			                <label class="col-sm-4 control-label">目录描述</label>
			                <div class="col-sm-6">
			                	<textarea name="folderDescription" id="folderDescription" class="form-control" placeholder="请输入文件夹描述 " ></textarea>              
			                </div>
			            </div>

						<div class="form-group">
			                <label class="col-sm-4 control-label">设置共享</label>
		                	<div class="col-sm-6">
		                  		<select class="form-control" name="folderShare" id="folderShare" >
           							<option id="fs0" value="0">不共享</option>
               						<option id="fs1" value="1">共享</option>
           						</select>
		                	</div>
			            </div>
			            
			            <div id="setBtn" class="form-group">
			            	<div class="col-sm-2 col-sm-offset-8">
			            		<button id="folderSet" class="btn btn-primary btn-block">修改</button> 		            		
			                </div>
			            </div>
			            <div id="addBtn" class="form-group">
			            	<div class="col-sm-2 col-sm-offset-8">
			            		<button id="folderAdd" class="btn btn-primary btn-block">新增</button> 		            		
			                </div>
			            </div>
		        	</div>  
                </div>
            </div>
        </div> 
    </div>
    <!-- 重命名模态层 -->
    <div class="modal fade" id="fileInfo" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-body">
            	    <button id="closeBtn2" type="button" class="close" data-dismiss="modal">&times;</button>
                	<div id="fileForm" class="form-horizontal">
			        	<div class="form-group"> 
			                <div>
			                	<input type="hidden" name="fileId" id="fileId" class="form-control">               
			                </div>
			            </div>
			            
			            <div id="fileNameDiv" class="form-group">
			                <label class="col-sm-2 control-label">重命名</label>
			                <div class="col-sm-6">
			                	<input type="text" name="fileName" id="fileName" class="form-control" placeholder="请输入文件名 ">              
			                </div>
			                <div class="col-sm-2">
			                	<input type="text" name="fileType" id="fileType" class="form-control" readonly>              
			                </div>
			                <div class="col-sm-2">
			            		<button id="setFileBtn" class="btn btn-primary btn-block">修改</button> 		            		
			                </div>
			            </div>
		        	</div>  
                </div>
            </div>
        </div> 
    </div>
    <!-- 上传模态层 -->
    <div class="modal fade" id="fileUpload" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-body">
                	<form id="uploadForm" class="form-horizontal" enctype="multipart/form-data" method="post" action="#" target="hiddenUpload">
			            <div id="fileDiv" class="form-group">
			                <div id="files" class="col-sm-6 col-sm-offset-2">
			                	<input type="file" name="data" id="data" class="form-control">              
			                </div>
			                <div class="col-sm-2">
			            		<button id="uploadBtn" class="btn btn-primary btn-block">上传</button> 		            		
			                </div>
			                <div class="col-sm-2">
			                	<button id="closeBtn3" type="button" class="close" data-dismiss="modal">&times;</button>
			                </div>
			            </div>
			            
		        	</form>  
		        	<div id="progressBar" class="progress">
				        	<div id="bar" class="progress-bar progress-bar-primary progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:0%">
				            	<span>0%</span>            	
				            </div> 
				            <input type="hidden" id="fileFolder" value="">
				    </div>
                </div>
            </div>
        </div> 
    </div>
</body>

</html>