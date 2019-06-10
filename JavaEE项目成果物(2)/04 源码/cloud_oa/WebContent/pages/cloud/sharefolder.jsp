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
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			onClick: showContent
		}
	};

	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		return true;
	}
	
	function showContent(event, treeId, treeNode, clickFlag) {
		$('#currentFolder').load("<%=request.getContextPath()%>/cloud/shareFolderDetail.action?folderId="+treeNode.id);
	}	
		
	function download(fileId){
		$('#download'+fileId).submit();
	}
	
	function loadChild(folderId){
		layer.load();
		$('#currentFolder').load('<%=request.getContextPath()%>/cloud/shareFolderDetail.action?folderId='+folderId,
				function(){
			var zTree = $.fn.zTree.getZTreeObj("tree");
			zTree.selectNode(zTree.getNodeByParam('id', folderId),true);
			zTree.selectNode(zTree.getNodeByParam('id', $('#oriFolderId').val()),false);
			layer.closeAll('loading');
		});
	}
	
	$(document).ready(function(){
		layer.load();
		$.getJSON('<%=request.getContextPath()%>/cloud/getShareFolderTree.action?empId=${param.empId}',function(data){
				
			if(!jQuery.isEmptyObject(data)){
				$.fn.zTree.init($("#tree"), setting, data);
				layer.closeAll('loading');
				$('#tree_1_a').click();
			} else{
				layer.closeAll('loading');
				layer.alert('该用户没有共享目录！', {icon: 0,closeBtn:false} ,function(){
					window.history.back(-1);
				});
			}
		});
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
			云共享目录		
		</h2>	  
		<!-- 侧边栏树形结构-->     
		<div class="col-md-3"> 
	        <div class="panel panel-primary" >
	        	<div class="panel-heading" style="font-weight:bolder">
	        		云共享
	            </div>
	            <div id="folderTree" class="panel-body">
	            	<ul id="tree" class="ztree"></ul>
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
	
</body>

</html>