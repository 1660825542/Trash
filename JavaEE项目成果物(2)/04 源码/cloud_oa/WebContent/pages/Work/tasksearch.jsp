<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.system.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.css"/>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
<script>
$(document).ready(function(){
	
	//日历框
	$('#getDate').datetimepicker({		
		lang:'ch',
		timepicker:false,
		format:'Y-m-d'
	});
});

function check(taskId,pageNum,empId){
	var url = '<%=request.getContextPath()%>/agent/compareAgent.action';
	$.get(url,{empId:empId},function(data){
		  if(data==1){
			  layer.open({
					type: 2,
					title: '查看工作',
					maxmin: false,
					shadeClose: false, //点击遮罩关闭层
					area : ['790px' , '400px'],
					content: '<%=request.getContextPath()%>/work/view.action?taskId=' + taskId + '&pageNum=' + pageNum
				});
			 
		  }else
			  alert("您无权查看此记录");
		});
}
</script>
<title>综合查询</title>
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
				<label for="toggle1"class="animate">工作安排<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/pages/Work/task.jsp">
						<li class="animate">日程安排<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/work/queryAll.action">
						<li class="animate actived">综合查询<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath()%>/pages/Work/calendar.jsp">
	                	<li class="animate">日历查看<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/pages/Work/agent.jsp">
	                	<li class="animate">代读设置<i class="fa fa-code float-right"></i></li>
	                </a>
	                <shiro:hasAnyRoles name="superadmin,caradmin,deptadmin,manager,money,adminstration">
	                <a href="<%=request.getContextPath()%>/pages/Work/approvetask.jsp">
	                	<li class="animate">待办审批<i class="fa fa-cog float-right"></i></li>
	                </a>
	                </shiro:hasAnyRoles>
				</ul>
			</dropdown>
		</div>       
    <!-- 左侧边栏完成 -->
    
     <!-- 右侧内容-->
     <div class="col-md-9">
	    <h3 class="page-header">
			综合查询
		</h3>
    
     	<form id="form1" class="form-horizontal" method="post" action="<%=request.getContextPath()%>/work/multipleQuery.action?">
        	<div class="form-group"> 
                <label for="managerName" class="col-sm-2 control-label">工作授予人</label>
                <div class="col-sm-4">
                	<input type="text" name="manager" id="manager" class="form-control" placeholder="请输入授予人姓名" value="${manager }">               
                </div>
                <label for="managerName" class="col-sm-2 control-label">工作接受人</label>
                <div class="col-sm-4">
                	<input type="text" name="emp" id="emp" class="form-control" placeholder="请输入接受人姓名" value="${emp }">               
                </div>
		    </div>
		  
            <div class="form-group"> 
                <label for="managerName" class="col-sm-2 control-label">布置时间</label>
                <div class="col-sm-10">
                	<input type="text"  name="date" id="getDate" class="form-control"  value="<fmt:formatDate value="${date }" pattern="yyyy-MM-dd"/>" placeholder="请选择日期">                 	             
                </div>
		    </div>
        	<div class="form-group">
            	<div class="col-sm-10 col-sm-offset-2">
            		<input type="submit" class="btn btn-primary btn-block" value="查询" > 
                </div>
		    </div> 
		    <input type="hidden" name="pageNum" id="pageNum" value="${pageNum }">               
        </form>
    	<h3 class="page-header">
			查询结果
		</h3> 
		<!-- 查询结果 -->
		<c:if test="${list.size()>0 }">
		<table class="table table-condensed table-striped">
              <tr style="font-size:14px;">
                <th style="text-align:center">接受人</th>
                <th style="text-align:center">授予人</th>
                <th style="text-align:center">工作主题</th>
                <th style="text-align:center">开始日期</th>
                <th style="text-align:center">结束日期</th> 
                <th style="text-align:center">查看详细</th>              
              </tr>
             <c:forEach items="${list }" var="task">
              <tr style="font-size:14px;" align="center">
                <td>${task.EMP }</td>
                <td>${task.MANAGER }</td>
                <td>${task.TASK_NAME }</td>
                <td><fmt:formatDate value="${task.START_TIME }" pattern="yyyy-MM-dd HH:mm"/></td> 
                <td><fmt:formatDate value="${task.FINISH_TIME }" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><a href="javascript:check(${task.TASK_ID},${pageNum },${task.EMP_ID});" class="btn btn-xs btn-primary">查看详细</a></td>               
              </tr>
              </c:forEach>
            </table>  
		<jsp:include page="/include/AdvancedPager.jsp"></jsp:include>
		</c:if>
	
		<c:if test="${list.size()<=0 }">
	        <div class="alert alert-info" role="alert">
	      		当前没有工作安排
	      	</div>
	     </c:if>
	</div>			
	
	<!--右侧内容完 -->
    
    </div>    
    
    <!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>

</html>