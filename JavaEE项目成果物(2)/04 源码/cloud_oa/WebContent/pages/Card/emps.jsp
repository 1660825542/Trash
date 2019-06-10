<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">

	<script type="text/javascript">
	
	$(document).ready(function(){
		$('#export').submit(function(){			
			if ($('#empName').val().replace(/\s/g, "") == '') {
				layer.msg("请输入检索关键字",{icon: 2});
				return false;
			}			
		});
		
	});

	</script>
    <script type="text/javascript">
    
    
	</script>
<title>公司通讯录</title>
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
				<label for="toggle1"class="animate">名片夹系统<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/Card/query.action">
						<li class="animate">联系人查询</li>
					</a>
					<a href="<%=request.getContextPath()%>/CardCategory/query1.action">
						<li class="animate">名片夹分类</li>
					</a>
	                <a href="<%=request.getContextPath()%>/Card/queryAll.action">
	                	<li class="animate actived">公司通讯录</li>
	                </a>
				</ul>
			</dropdown>
		</div>            
         <!-- 侧边栏完成 -->
         
         <!-- 右侧内容-->
			<div class="col-md-offset-3">
			
				<h3 class="page-header">
					公司通讯录
				</h3>
					   
				<form name="export" id="export" style="margin-top:5px;" action="<%=request.getContextPath() %>/Card/queryByConditionEmp.action" method="get" style="display:blobk">
				  <div class="input-group">	
					<input type="text" name="empName" id="empName" class="form-control" placeholder="请输入员工姓名" value="${card.cardName }">
					  <span class="input-group-btn">
					      <input type="submit" value="普通搜索" class="btn btn-primary">
					  </span>
				  </div>
					<input type="hidden" name="pageNum" id="pageNum" value="${pager.pageNum }">
				  	
				</form>	
				
                <br />
                <table class="table table-striped table-bordered table-condensed table-hover">
                    <thead>
                    <tr>
                        <th width="80">
                        	员工编号
                        </th>
                        <th>
                        	员工姓名
                        </th>
                       	<th>
                         	 职务
                        </th>	
                        <th>
                         	 所属部门
                        </th>				
                        <th>
                        	联系电话
                        </th>
                    </tr>	
                    </thead>	
                    <tbody>		
                    <c:forEach items="${list}" var="emp">									
		                <tr>
		                  	<td>${emp.get("EMP_ID") }</td>
		                  	<td>${emp.get("EMP_NAME") }</td>
		                  	<td>${emp.get("POS_NAME") }</td>
		                  	<td>${emp.get("DEPT_NAME") }</td>
		                  	<td>${emp.get("PHONE") }</td>
		                </tr>		                
		                </c:forEach>
                     </tbody>
                </table>
                
			    <div class="col-sm-offset-9">
			        <a id="exportBtn" href="../Card/exportExcel.action"class="btn btn-success btn-block">导出报表</a>
	       			<!-- 响应式布局，移动端才会有返回主页的按钮 -->
	       			<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
	       			<!-- / --> 			    		
	       		</div>
       		
		    	<!-- 包含分页 -->
		    	<jsp:include page="/include/pager.jsp"/>   	   		    		    	  
		     </div>  
		 <!--右侧内容完 -->
	</div>

	<!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
	
</body>

</html>