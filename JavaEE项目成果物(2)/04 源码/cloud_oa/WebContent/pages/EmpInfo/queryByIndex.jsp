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
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
	<script type="text/javascript">
	/*分页显示*/
	var pageNum =${pager.pageNum};//第几页
	var recordCount = ${empList.recordCount};//总记录数
	var pageSize = ${pager.pageSize };//每页多少条
	var pageCount = parseInt((recordCount - 1) / pageSize) + 1;//共几页
	
	$(document).ready(function(){
		$('#index').submit(function(){			
			if ($('#keyword').val().replace(/\s/g, "") == '') {
				layer.msg("请输入检索关键字",{icon: 2});
				return false;
			}			
		});
		
		$('#indexBtn').click(function(){	
			
			$('#pageNum').val(1);
			$('#index').submit();				
		});

		$('#prevBtn').click(function(){	
			if (pageNum <= 1 ) {
				layer.msg("已经是第一页了",{icon: 0, shift:6});
				return false;
			}
			
			$('#pageNum').val(pageNum - 1);
			$('#index').submit();				
		});
		
		$('#nextBtn').click(function(){
			
			if (pageNum >= pageCount) {
				layer.msg("已经是最后一页了",{icon: 0, shift:6});
				return false;
			}
			
			$('#pageNum').val(pageNum + 1);						
			$('#index').submit();
		});
		
		
	});
	</script>
	<title>检索结果</title>
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
	               	<input type="text" name="queryStr" id="keyword" placeholder="请输入员工的姓名或部门名称" value="${queryStr }" class="form-control">
	                   <span class="input-group-btn">
	                   	<input type="button" id="indexBtn" class="btn btn-primary" value="全文检索">
	                   </span>
	               </div>
	               <input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum }">
	               
	        </form>	
	        <br />
			<c:if test="${empList.list.size()>0 }">
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
				<c:forEach items="${empList.list}" var="emp">
		           <tr style="font-size:14px;" align="center">
		             <td>${emp.get("empId")}</td>
		             <td>${emp.empName}</td>
		             <td>${emp.deptName}</td>
		             <td>${emp.posName}</td>
		             <td>${emp.phone}</td>	         
		             <td>
		             <div class="dropdown">
						<button type="button" class="btn btn-default btn-xs dropdown-toggle" id="dropdownMenu" data-toggle="dropdown">
							操作
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li>		             		
								<a href="<%=request.getContextPath()%>/empInfo/view.action?empId=${emp.empId}" >查看详细</a>	
							</li>
							<li>		             		
								<a href="<%=request.getContextPath()%>/pages/cloud/sharefolder.jsp?empId=${emp.empId}" >查看云共享</a>	
							</li>
						</ul>
					</div>												
		             </td>
		           </tr>
		         </c:forEach> 
	             </tbody>	            	        
	    	</table>
	    		    	
	    	<nav class="text-center">
		    		<ul class="pagination">
		    			<li><a id="prevBtn" href="#">上页</a></li>
		    			<li><a>共检索出${empList.recordCount }记录，每页${pager.pageSize }条，当前是第${pager.pageNum }页</a></li>
		    			<li><a id="nextBtn" href="#">下页</a></li>
		    		</ul>
		    </nav>	
		    </c:if>
	
		<c:if test="${empList.list.size()<=0 }">
	        <div class="alert alert-info" role="alert">
	      		没有检索到员工
	      	</div>
	     </c:if>
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