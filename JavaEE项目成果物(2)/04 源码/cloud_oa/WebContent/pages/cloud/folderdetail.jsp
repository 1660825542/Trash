<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="panel panel-info" >
	<div class="panel-heading" ><b>
	<c:if test="${fn:length(folder.folderName)>36 }">${fn:substring(folder.folderName,0,35) }...</c:if>
	<c:if test="${fn:length(folder.folderName)<=36 }">${folder.folderName }</c:if></b>
		<input type=hidden id="oriFolderId" value="${folder.folderId }">
		<input type=hidden id="oriFolderName" value="${folder.folderName }">
		<input type=hidden id="oriFolderDescription" value="${folder.folderDescription }">
		<input type=hidden id="oriFolderShare" value="${folder.folderShare }">
		<div class="pull-right">
			<c:if test="${folder.canDelete==1 }">
				<a href="javascript:editFolder(${folder.folderId })" class="btn btn-xs btn-info">修改信息</a>
				<a href="javascript:delFolder(${folder.folderId })" class="btn btn-xs btn-info">删除目录</a>
			</c:if>
			<a href="javascript:addFolder(${folder.folderId })" class="btn btn-primary btn-xs">新建子目录</a>
			<a href="javascript:upload(${folder.folderId })" class="btn btn-primary btn-xs" >上传文件</a>
		</div>
	</div>
   	
   	<div class="panel-body">
   		<c:if test="${folder.folderDescription!=null }"><p>文件夹描述：${folder.folderDescription }</c:if>
   		<c:if test="${folder.folderDescription==null }"><p>文件夹描述：无</c:if>
   		<div class="panel panel-default" >
			<div class="panel-heading" >子目录和文件</div>
			<div style="height:300px;overflow-x:visible;overflow-y:auto">
				<c:if test="${folderList.size()>0 }">
				<table class="table table-striped">
  					<c:forEach items="${folderList }" var="folders">
					   	<tr>
					   	<td width="10%">
				   			<div class="btn-group btn-xs">
				            	<button  type="button" class="btn btn-default  btn-xs dropdown-toggle  input-xs" id="dropdownMenu" data-toggle="dropdown">
				                   	 操作
				                    <span class="caret"></span>
				                </button>
				                <input type="hidden" value="${folders.folderName }">
				                <input type="hidden" value="${folders.folderShare }">
				                <input type="hidden" value="${folders.folderDescription }">
				                <ul id="folder${folders.folderId }" class="dropdown-menu">
				                    <li><a href="javascript:addFolder(${folders.folderId })">新建子目录</a></li>
				                    <li><a href="javascript:upload(${folders.folderId })">上传文件</a></li>
				                   	<li><a href="javascript:editChildFolder(${folders.folderId })">修改信息</a></li>
				                   	<li><a href="javascript:delFolder(${folders.folderId })">删除目录</a></li>			                    
				                </ul>
				            </div>    
				   		</td>
				   		<td width="5%" style="background:url(../../image/folder.png);background-size: cover;background-size: contain;background-repeat:no-repeat;">
				   		</td>
				   		<td width="60%"><a href="javascript:loadDetail(${folders.folderId })">
				   			<c:if test="${fn:length(folders.folderName)>36 }">${fn:substring(folders.folderName,0,35) }...</c:if>
					   		<c:if test="${fn:length(folders.folderName)<=36 }">${folders.folderName }</c:if>
				   			</a>
				   		</td>
				   		<td width="10%">
					   		<c:if test="${folders.folderShare==0 }">
					   			<span class="label label-default">未共享</span>
					   		</c:if>
					   		<c:if test="${folders.folderShare==1 }">
					   			<span class="label label-primary">已共享</span>
					   		</c:if>
				   		</td>
				   		<td width="15%">
				   			<fmt:formatNumber type="number" value="${folders.folderSize/1048576 }" maxFractionDigits="3" />MB
				   		</td>
				   		
				   	</tr>
			   	</c:forEach>
			   	</table>
			   	</c:if>
				<c:if test="${fileList.size()>0 }">
			   	<table class="table table-striped">
				   	<c:forEach items="${fileList }" var="files">
					   	<tr>
						   	<td width="10%">
					   			<div class="btn-group btn-xs">
					            	<button  type="button" class="btn btn-default  btn-xs dropdown-toggle  input-xs" id="dropdownMenu" data-toggle="dropdown">
					                   	 操作
					                    <span class="caret"></span>
					                </button>
					               <form id="download${files.fileId }" class="form-horizontal" enctype="multipart/form-data" method="post" action="<%=request.getContextPath() %>/file/download.action" target="hiddenUpload">
					                    <input type="hidden" value="${files.fileId }" name="fileId">
									</form>
					                <input type="hidden" value="${files.fileName }">
					                <ul id="file${files.fileId }" class="dropdown-menu">
					                    <li><a href="javascript:download(${files.fileId })">下载</a></li>
					                   	<li><a href="javascript:editFile(${files.fileId })">重命名</a></li>
					                   	<li><a href="javascript:deleteFile(${files.fileId })">删除</a></li>			                    
					                </ul>
					            </div>    
				   			</td>
					   		<td width="5%" style="background:url(../../image/file.png);background-size: cover;background-size: contain;background-repeat:no-repeat;">
					   		</td>
					   		<td width="70%">
					   		<c:if test="${fn:length(files.fileName)>36 }">${fn:substring(files.fileName,0,35) }...</c:if>
					   		<c:if test="${fn:length(files.fileName)<=36 }">${files.fileName }</c:if>
					   		</td>
					   		<td width="15%">
					   			<fmt:formatNumber type="number" value="${files.fileSize/1048576 }" maxFractionDigits="3" />MB
					   		</td>
					   		
					   		
					   	</tr>
				   	</c:forEach>
			   	</table>
			   	</c:if>
			<c:if test="${fileList.size()==0 && folderList.size()==0 }"><div class="list-group-item">空文件夹!可以点击标题右上侧创建新目录或上传文件！</div></c:if>
		</div>
		</div>
   	</div>
   	<div class="panel-footer">
   		<c:if test="${folder.canDelete==0 }">已用/总容量：<fmt:formatNumber type="number" value="${(folder.folderSize-folder.folderLeftSize)/1048576 }" maxFractionDigits="3" />MB / <fmt:formatNumber type="number" value="${folder.folderSize/1048576 }" maxFractionDigits="3" />MB</c:if>
   		<c:if test="${folder.canDelete==1 }">文件夹大小：<fmt:formatNumber type="number" value="${folder.folderSize/1048576 }" maxFractionDigits="3" />MB</c:if>
   		
   		<div class="pull-right">
   		<c:if test="${folder.folderShare==0 }">
   		<span class="label label-default">未共享</span>
   			<a href="javascript:editShare(${folder.folderId })" >设为共享目录</a>
   		</c:if>
   		<c:if test="${folder.folderShare==1 }">
   		<span class="label label-primary">已共享</span>
   			<a href="javascript:editShare(${folder.folderId })" >取消共享</a>
   		</c:if>
   		</div>
   	</div>
</div>
      	