<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.meeting.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="../pages/Meeting/js/jquery.form.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/Validform/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript">
	
	function delete1(meetId,btnObj) {	
		
		var flag = window.confirm("你确定取消预约吗?");
		
		if (flag) {
		
			var url = '<%=request.getContextPath()%>/meeting/deleteById.action?meetId=' + meetId ;
			
			$.get(url,function(data){				
				$(btnObj).parent().parent().remove();		
			}) ;
		}
	}
	
	function del(meetId) {	

		var url = '<%=request.getContextPath()%>/record/query.action?meetId=' + meetId ;
		$.get(url,function(data){	
			if(data==0){
				alert("会议记录不存在，无法删除");
			}
			else{
			 	location.assign("<%=request.getContextPath()%>/record/del.action?meetId=" + meetId );
			}
		}) ;
	}
	
	function query(meetId){
		var url = '<%=request.getContextPath()%>/record/query.action?meetId=' + meetId ;
		$.get(url,function(data){	
			if(data==0){
				alert("会议记录不存在，无法下载");
			}
			else{
			 	location.assign("<%=request.getContextPath()%>/record/download.action?meetId=" + meetId );
			}
		}) ;
	}

$(document).ready(function(e) {
		
		$('.submitBtn').click(function(){
			
			var file = $(this).parent().find('#file');
			
			if( file.val() == ''){
				alert("您未选择文件");
				return false;
				
			}
			
			var frm = $(this).parent();
			
			$(frm).ajaxSubmit({
				
		        success: function (result, status) {
		            
		            alert(result);
		        }
			
			});
			
		});       
		
	});
	
	</script>
</head>

<body>

	<!-- 网页头部-->

	<jsp:include page="/include/header.jsp"></jsp:include>
	
	<!-- 每个模块的部分 -->
	<div class="container">
	
	<!-- 侧边栏 -->     
        <div class="col-md-3" id="myScrollSpy" style="margin-top:30px;">
            <dropdown class="col-xs-12" style="margin-bottom:30px;"> 
				<input id="toggle1" type="checkbox" checked>
				<label for="toggle1"class="animate">会议管理<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath() %>/room/query.action">
						<li class="animate">会议室信息<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath() %>/meeting/query.action">
						<li class="animate">会议预约情况<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath() %>/meeting/queryByEmpId.action">
	                	<li class="animate">我要参加的会议<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath() %>/meeting/queryByOriginator.action">
	                	<li class="animate actived">我发布的会议<i class="fa fa-code float-right"></i></li>
	                </a>
				</ul>
			</dropdown>
		</div>                
         <!-- 侧边栏完成 -->
         		<div class="col-md-7">
			<h3 class="page-header">
				我发布的会议信息
			</h3>        
		
			<table  class="table table-striped table-bordered table-condensed table-hover">
	    		<thead>
				<tr>
					<th>
						会议发起人
					</th>
					<th>
						会议地点
					</th>
					<th>
						会议开始时间
					</th>
					<th>
						会议结束时间
					</th>					
					<th>
						会议主题
					</th>
					<th>
						审批状态
					</th>
					<th>
						取消预约
					</th>	
					<th>
						会议记录
					</th>							
				</tr>	
				</thead>	
				<tbody>
												
				<c:forEach items="${list}" var="meeting">
					<tr>
						<td>${meeting.EMP_NAME}</td>
						<td>${meeting.ROOM_PLACE}</td>
						<td>	
							<fmt:formatDate value="${meeting.MEET_DATEBEGIN}"/>
							<fmt:formatDate value="${meeting.MEET_DATEBEGIN}" pattern=" HH:mm:ss"/> 
						</td>
						<td>	
							<fmt:formatDate value="${meeting.MEET_DATEEND}"/>
							<fmt:formatDate value="${meeting.MEET_DATEEND}" pattern=" HH:mm:ss"/> 
						</td>
						<td>${meeting.THEME}</td>
						<td>
						<c:if test="${meeting.MEET_STATE ==1}">审批成功</c:if>
						<c:if test="${meeting.MEET_STATE ==0}">审批中</c:if>
						<c:if test="${meeting.MEET_STATE ==2}">审批失败</c:if>		
						</td>
						<td>
						<c:if test="${meeting.MEET_STATE ==1}"><input type ="button" value="取消" class="btn btn-primary"disabled></c:if>
						<c:if test="${meeting.MEET_STATE ==0}"><input type ="button" value="取消" class="btn btn-primary" onclick="delete1(${meeting.MEET_ID},this);"></c:if>
						<c:if test="${meeting.MEET_STATE ==2}"><input type ="button" value="取消" class="btn btn-primary"disabled></c:if>		
						</td>
						<td>
							<div class="btn-group btn-xs">
				            	<button type="button" class="btn btn-default  btn-sm dropdown-toggle  input-sm" id="dropdownMenu" data-toggle="dropdown">
				                   	 会议记录
				                    <span class="caret"></span>
				                </button>
				                <ul class="dropdown-menu">
				                    <li>
										 <form name="recform" id="recform" method="post"
											 enctype="multipart/form-data"
											 action="<%=request.getContextPath()%>/record/upload.action">
											
											<input type="file" name="data" id="file" size=15>
											<input type="hidden" name="meetId" value="${meeting.MEET_ID}" >
											<input type="button" class="submitBtn" id="btn1" value="上传文件">
										 </form>
									</li>
				                   	<li><a href="javascript:void(0)" onclick="query(${meeting.MEET_ID});">下载会议记录</a></li>	
				                   	<li><a href="javascript:void(0)" onclick="del(${meeting.MEET_ID});">删除会议记录</a></li>		                    
				                </ul>
				            </div>                  			                  
		               </td>
					</tr>
				</c:forEach> 
				
	             </tbody>
	    	</table>

		
	    	
	    	<div class="col-sm-offset-8">    	
	    	<a href="<%=request.getContextPath() %>
						/pages/Meeting/ApplyMeeting.jsp" class="btn btn-large btn-block btn-primary">点击预约会议
			</a>

       		<!-- 响应式布局，移动端才会有返回主页的按钮 -->
       		<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
       		<!-- / --> 	    	
	    	</div>
	    		    	
	    	<!-- 包含分页 -->
	    	<nav class="text-center">
	<ul class="pagination">
		<li><a href="#">页码：${pager.pageNum}/${pager.pageCount}</a></li>

		<c:if test="${pager.pageNum!=1}">
			<li><a href="?originator=${originator}&&pageNum=1">首页</a></li>
			<li><a href="?originator=${originator}&&pageNum=${pager.pageNum-1}">上页</a></li>
		</c:if>
		<c:if test="${pager.pageNum==1}">
			<li class="disabled"><a href="#">首页</a></li>
			<li class="disabled"><a href="#">上页</a></li>
		</c:if>

		<c:forEach var="i" begin="1" end="${pager.pageCount }">
			<c:if test="${pager.pageNum==i}">
				<li class="active"><a href="#">${i}</a></li>
			</c:if>
			<c:if test="${pager.pageNum!=i}">
				<li><a href="?originator=${originator}&&pageNum=${i}">${i}</a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pager.pageNum<pager.pageCount}">
			<li><a href="?originator=${originator}&&pageNum=${pager.pageNum+1}">下页</a></li>
			<li><a href="?originator=${originator}&&pageNum=${pager.pageCount}">末页</a></li>
		</c:if>
		<c:if test="${pager.pageNum>=pager.pageCount}">
			<li class="disabled"><a href="#}">下页</a></li>
			<li class="disabled"><a href="#">末页</a></li>
		</c:if>
	</ul>
</nav>
	    	
	     </div> 
		 <!--右侧内容完 -->
	</div>

	<!--网页底部-->
		<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>

</html>