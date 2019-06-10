<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.room.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/Validform/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	

	
	
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
						<li class="animate actived">会议室信息<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath() %>/meeting/query.action">
						<li class="animate">会议预约情况<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath() %>/meeting/queryByEmpId.action">
	                	<li class="animate">我要参加的会议<i class="fa fa-code float-right"></i></li>
	                </a>
	                <shiro:hasPermission name="apply">
	                <a href="<%=request.getContextPath() %>/meeting/queryByOriginator.action">
	                	<li class="animate">我发布的会议<i class="fa fa-code float-right"></i></li>
	                </a>
	                </shiro:hasPermission>
				</ul>
			</dropdown>
		</div>               
         <!-- 侧边栏完成 -->
         
         <!-- 右侧内容-->
		<div class="col-md-7">
			<h3 class="page-header">
				会议室信息列表
			</h3>
			
			<form name="form1" action="<%=request.getContextPath()%>/room/queryByCondition.action" method="get" style="display:blobk">
				<div class="input-group">                	
	               	<input type="text" name="roomPlace" id ="roomPlace" placeholder="亲 您还可以根据楼层模糊搜索哦" value ="${room.roomPlace}" class="form-control">
	                   <span class="input-group-btn">
	                   	<input type="submit" class="btn btn-primary" value="查询会议室">
	                   </span>
	                
	               </div>
	               <input type="hidden"
					name="pageNum" id="pageNum" value="${pager.pageNum }">
	            			<div class="col-md-6">

				<div class="checkbox">
					<label for="checkbox1" class="checkbox-inline"> <input
						type="checkbox" name="checkbox1" value="1" >空调
					</label>
				    <label for="checkbox2" class="checkbox-inline"> <input
						type="checkbox" name="checkbox2"  value="0">可以预约
					</label> 
					<div class="btn-group btn-xs">						
						<select name="accomNum">
							<option value="">容纳人数</option>
							<option value="10">10</option>
							<option value="30">30</option>
							<option value="50">50</option>
						</select>
					</div>
				</div>
			</div>
	        </form>	
	        
		
			<table  class="table table-striped table-bordered table-condensed table-hover">
	    		<thead>
				<tr>
					<th>
						会议室名称
					</th>
					<th>
						会议室地点
					</th>
					<th>
						空调情况
					</th>
					<th>
						容纳人数
					</th>
 					<th>
						会议室状态
					</th>
					<shiro:hasRole name="superadmin">
					<th>
						删除会议室
					</th>
					</shiro:hasRole>
				</tr>	
				</thead>	
				<tbody>
												
				<c:forEach items="${list}" var="room">
					<tr>
						<td>${room.roomName}</td>
						<td>${room.roomPlace}</td>
						<td>
							<c:if test="${room.airCon ==0 }">无空调</c:if>
							<c:if test="${room.airCon ==1 }">有空调</c:if>						
						</td>
						<td>${room.accomNum }</td>
						<td>
							<c:if test="${room.roomState==0 }">未使用</c:if>
							<c:if test="${room.roomState==1 }">正在使用</c:if>
						</td>
						<shiro:hasRole name="superadmin">
						<td style="text-align:center;">
							<input type ="button" value="删除" class="btn btn-primary" onclick="del(${room.roomId},this);">                            			                  
		               </td>
		             	</shiro:hasRole>
					</tr>
				</c:forEach> 
				
	             </tbody>
	    	</table>
	    	<!-- 分页条 -->
			<nav class="text-center">
				<ul class="pagination">
					<li><a href="#">页码：${pager.pageNum}/${pager.pageCount}</a></li>
			
					<c:if test="${pager.pageNum!=1}">
						<li><a href="?roomPlace=<my:encode value="${roomPlace}"/>&checkbox1=${checkbox1}&checkbox2=${checkbox2}&accomNum=${accomNum}&&pageNum=1">首页</a></li>
						<li><a href="?roomPlace=<my:encode value="${roomPlace}"/>&checkbox1=${checkbox1}&checkbox2=${checkbox2}&accomNum=${accomNum}&&pageNum=${pager.pageNum-1}">上页</a></li>
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
							<li><a href="?roomPlace=<my:encode value="${roomPlace}"/>&checkbox1=${checkbox1}&checkbox2=${checkbox2}&accomNum=${accomNum}&&pageNum=${i}">${i}</a></li>
						</c:if>
					</c:forEach>
			
					<c:if test="${pager.pageNum<pager.pageCount}">
						<li><a href="?roomPlace=<my:encode value="${roomPlace}"/>&checkbox1=${checkbox1}&checkbox2=${checkbox2}&accomNum=${accomNum}&&pageNum=${pager.pageNum+1}">下页</a></li>
						<li><a href="?roomPlace=<my:encode value="${roomPlace}"/>&checkbox1=${checkbox1}&checkbox2=${checkbox2}&accomNum=${accomNum}&&pageNum=${pager.pageCount}">末页</a></li>
					</c:if>
					<c:if test="${pager.pageNum>=pager.pageCount}">
						<li class="disabled"><a href="#}">下页</a></li>
						<li class="disabled"><a href="#">末页</a></li>
					</c:if>
				</ul>
			</nav>

			<div class="col-sm-offset-8">
       		<!-- 响应式布局，移动端才会有返回主页的按钮 -->
       		<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
       		<!-- / --> 	    	
	    	</div>
	    		    	

	    	
	     </div> 
		 <!--右侧内容完 -->
	</div>

	<!--网页底部-->
		<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>

</html>