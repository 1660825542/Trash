<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
	<title>他人信息</title>
</head>

<body>

	<!-- 网页头部-->
	<jsp:include page="/include/header.jsp"></jsp:include>
	<!-- 网页头部完成-->
	
	<!-- 每个模块的部分 -->
	<div class="container">
	
	<!-- 左侧边栏 -->     
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
    <!-- 左侧边栏完成 -->

         <!-- 右侧内容-->
            <div class="col-md-9">
	            <h3 class="page-header">
					员工信息查看
				</h3>
	    		<div class="col-md-11">
		    	<table class="table table-striped">	      	      	  
  	    	  		<tbody>
  	    	  			<tr>
			                <td style="text-align:center" width="40%"><label class="control-label">员工编号</label></td>
			                <td>
			                	<label class="control-label">${emp.empNum}</label>		                	
			                </td>
              			</tr>	   	     
	      	  			<tr>
                			<td style="text-align:center"><label class="control-label">姓名</label></td>
                			<td>
                				<label class="control-label">${emp.empName}</label>
                			</td>
                		</tr>
                		<tr>
                			<td style="text-align:center"><label class="control-label">部门</label></td>
                			<td>
                				<label class="control-label">${department}</label>
                			</td>
                		</tr>
                		<tr>
                			<td style="text-align:center"><label class="control-label">职位</label></td>
                			<td>
                				<label class="control-label">${position}</label>
                			</td>
                		</tr>	      	  			      	    		
	   	      			<tr>
	      	    			<td style="text-align:center"><label class="control-label">手机号码</label></td>
	      	    			<td>
	      	    				<label class="control-label">${emp.phone}</label>
	   	      				</td>
	   	      			</tr>
	   	      			<tr>
	      	    			<td style="text-align:center"><label class="control-label">邮箱</label></td>
	      	    			<td>
	      	    				<label class="control-label">${emp.email}</label>
	   	      				</td>
	   	      			</tr>
	      	  			<tr>
	      	    			<td style="text-align:center"><label class="control-label">QQ</label></td>
	      	    			<td>
	      	    				<label class="control-label">${emp.qq}</label>
	   	      				</td>
	   	      			</tr>
	   	      			<tr>
	      	    			<td style="text-align:center"><label class="control-label">介绍</label></td>
	      	    			<td>
	      	    				<textarea class="form-control" cols="50" rows="4" readonly>${emp.introduction}</textarea> 
	   	      				</td>
	   	      			</tr>
	   	    		</tbody>
	   	    	</table>
	   	    	</div>			    		
		     </div>
	<!--右侧内容完 -->
	</div>
	
	<!--网页底部-->
	<footer>
		<jsp:include page="/include/footer.jsp"></jsp:include>
	</footer>
	<!--网页底部结束-->
</body>

</html>