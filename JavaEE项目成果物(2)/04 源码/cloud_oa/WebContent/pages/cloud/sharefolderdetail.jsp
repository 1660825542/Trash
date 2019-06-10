<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="panel panel-info" >
	<div class="panel-heading" >
		<input type=hidden id="oriFolderId" value="${folder.folderId }">
	<b>
	<c:if test="${fn:length(folder.folderName)>36 }">${fn:substring(folder.folderName,0,35) }...</c:if>
	<c:if test="${fn:length(folder.folderName)<=36 }">${folder.folderName }</c:if></b>
	</div>
   	
   	<div class="panel-body">
   		<c:if test="${folder.folderDescription!=null }"><p>文件夹描述：${folder.folderDescription }</c:if>
   		<c:if test="${folder.folderDescription==null }"><p>文件夹描述：无</c:if>
   		<div class="panel panel-default" >
			<div class="panel-heading">共享子目录和文件</div>
			<div style="height:300px;overflow-x:visible;overflow-y:auto">
			<c:if test="${fileList.size()>0 }">
			   	<table class="table table-striped">
				   	<c:forEach items="${fileList }" var="files">
					   	<tr>
					   		
				   			<td width="5%" style="background:url(../../image/file.png);background-size: cover;background-size: contain;background-repeat:no-repeat;">
					   		</td>
					   		<td width="65%">
					   		<c:if test="${fn:length(files.fileName)>36 }">${fn:substring(files.fileName,0,35) }...</c:if>
					   		<c:if test="${fn:length(files.fileName)<=36 }">${files.fileName }</c:if>
					   		</td>
					   		<td width="10%">
				              	<form id="download${files.fileId }" class="form-horizontal" enctype="multipart/form-data" method="post" action="<%=request.getContextPath() %>/file/download.action" target="hiddenUpload">
			            	        <input type="hidden" value="${files.fileId }" name="fileId">
								</form>
					        	<a href="javascript:download(${files.fileId })" class="btn btn-default btn-xs">下载 <span class="glyphicon glyphicon-download-alt"></span>
					        	</a>
				   			</td>
					   		<td width="15%">
					   			<fmt:formatNumber type="number" value="${files.fileSize/1048576 }" maxFractionDigits="3" />MB
					   		</td>
					   	</tr>
				   	</c:forEach>
			   	</table>
			   	</c:if>
				<c:if test="${folderList.size()>0 }">
				<table class="table table-striped">
  					<c:forEach items="${folderList }" var="folders">
				   	<tr>
				   		<td width="5%" style="background:url(../../image/folder.png);background-size: cover;background-size: contain;background-repeat:no-repeat;">
				   		</td>
				   		<td width="65%"><a href="javascript:loadChild(${folders.folderId })">
				   			<c:if test="${fn:length(folders.folderName)>36 }">${fn:substring(folders.folderName,0,35) }...</c:if>
					   		<c:if test="${fn:length(folders.folderName)<=36 }">${folders.folderName }</c:if>
				   			</a>
				   		</td>
				   		<td width="10%">
					   			<span class="label label-primary">已共享</span>
				   		</td>
				   		<td width="15%">
				   			<fmt:formatNumber type="number" value="${folders.folderSize/1048576 }" maxFractionDigits="3" />MB
				   		</td>
				   	</tr>
			   	</c:forEach>
			   	</table>
			   	</c:if>
				
			<c:if test="${fileList.size()==0 && folderList.size()==0 }"><div class="list-group-item">空文件夹！</div></c:if>
		</div>
		</div>
   	</div>
   	<div class="panel-footer">
		共${fileList.size()}个共享文件，${folderList.size()}个共享文件夹
   	</div>
</div>
      	