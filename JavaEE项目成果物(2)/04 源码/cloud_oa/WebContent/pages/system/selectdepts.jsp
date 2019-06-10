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
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
	
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name);
	
		$(document).ready(function(){
			var deptId;
			var deptName;
			
			
			$('input[name="deptID"]').click(function(){
				deptId = $(this).parent().parent().find('td').eq(1).text();
				deptName = $(this).parent().parent().find('td').eq(2).text();	
				
			});
			
			$('#yes').on('click', function(){
				parent.$('#departmentId').val(deptId);
				parent.$('#deptName').val(deptName);
				parent.layer.close(index);
			});	
			
			$('#no').on('click', function(){
				parent.layer.close(index);
			});
		});
	
	</script>
</head>

<body>

	
	<div>
      	<table class="table table-striped table-bordered table-condensed table-hover">
          	<thead>
                	<tr>
                		<th width="88">选择</th>
                    	<th width="80">部门编号 </th>
                    	<th width="100">部门名称</th>
                    	<th>部门描述</th>					
                	</tr>	
           	</thead>	
              <tbody>		
               	<c:forEach items="${list}" var="dept">
              	<tr>
              		<td>
                 		<input class="radio" name="deptID" type="radio" >
                 	</td>
                 	<td>${dept.deptId }</td>
                 	<td>${dept.deptName }</td>
                 	<td>${dept.deptInfo }</td>
              	</tr>		                
             	</c:forEach>
             	</tbody>
      	</table>
            
   	<!-- 包含分页 -->
   	<jsp:include page="/include/pager.jsp"/>   	   		    		    	  
    </div>  
    <div class="text-center">
    	<input id="yes" type="button" value="确定" class="btn btn-primary">
		<input id="no" type="button" value="取消" class="btn">
    </div>
    			
</body>

</html>