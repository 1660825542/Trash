<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>

<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
	
	
	<script type="text/javascript">
	$(document).ready(function(){
		$('#index').submit(function(){			
			if ($('#keyword').val().replace(/\s/g, "") == '') {
				layer.msg("请输入检索关键字",{icon: 2});
				return false;
			}			
		});
		
	});
	</script>
	<title>检索他人信息</title>
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
				<label for="toggle1"class="animate">个人中心<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/empInfo/query.action">
						<li class="animate">员工信息维护<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/empInfo/queryAll.action">
						<li class="animate actived">检索他人信息<i class="fa fa-code float-right"></i></li>
					</a>
	                <shiro:lacksRole name="manager">
	                <a href="<%=request.getContextPath()%>/pages/EmpInfo/apply.jsp">
	                	<li class="animate">个人申请中心<i class="fa fa-code float-right"></i></li>
	                </a>	              
	                </shiro:lacksRole>	              
				</ul>
			</dropdown>
		</div>           
         <!-- 侧边栏完成 -->
         
         <!-- 右侧内容-->
		<div class="col-md-7">
			<h3 class="page-header">
				员工信息列表
			</h3>
			
			<form id="index" action="<%=request.getContextPath()%>/empInfo/queryByIndex.action" method="post">
				<div class="input-group">                	
	               	<input type="text" name="queryStr" id="keyword" placeholder="请输入员工的姓名或部门名称" class="form-control">
	                   <span class="input-group-btn">
	                   		<input type="submit" class="btn btn-primary" value="全文检索">
	                   </span>
	                   
	               </div>
	               <input type="hidden" id="pageNum" name="pageNum" value="1">
	        </form>	
	        <br/>
			
			<table  class="table table-striped table-condensed table-hover">
	    		<thead>
				<tr>
					<th style="text-align:center">
						员工编号
					</th>
					<th style="text-align:center">
						员工姓名
					</th>
					<th style="text-align:center">
						员工部门
					</th>
					<th style="text-align:center">
						员工职位
					</th>
					<th style="text-align:center">
						员工电话
					</th>
					<th style="text-align:center">
						操作
					</th>
				</tr>	
				</thead>	
				<tbody>
				<c:forEach items="${empList}" var="emp">
		           <tr style="font-size:14px;" align="center">
		             <td>${emp.EMP_ID}</td>
		             <td>${emp.EMP_NAME}</td>
		             <td>${emp.DEPT_NAME}</td>
		             <td>${emp.POS_NAME}</td>
		             <td>${emp.PHONE}</td>		         
		             <td>
		             <div class="dropdown">
						<button type="button" class="btn btn-default btn-xs dropdown-toggle" id="dropdownMenu" data-toggle="dropdown">
							操作
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li>		             		
								<a href="<%=request.getContextPath()%>/empInfo/view.action?empId=${emp.EMP_ID}" >查看详细</a>	
							</li>
							<li>		             		
								<a href="<%=request.getContextPath()%>/pages/cloud/sharefolder.jsp?empId=${emp.EMP_ID}" >查看云共享</a>	
							</li>
						</ul>
					</div>												
		             </td>
		           </tr>
		         </c:forEach> 
	             </tbody>
	    	</table>
	    	
	    	<div class="col-sm-offset-8">
       		<!-- 响应式布局，移动端才会有返回主页的按钮 -->
       		<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
       		<!-- / --> 	    	
	    	</div>
	    		    	
	    	<!-- 包含分页 -->
	    	<jsp:include page="/include/AdvancedPager.jsp"/>
	    	
	     </div> 
		 <!--右侧内容完 -->
	</div>

	<!--网页底部-->
		<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>

</html>