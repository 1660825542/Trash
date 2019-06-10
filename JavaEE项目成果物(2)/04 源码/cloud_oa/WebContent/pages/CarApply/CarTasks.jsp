<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html>
<head>
	<title>车辆申请待办</title>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">

	<script type="text/javascript">
		function handle(taskId,recordId,procinsId){
			layer.load();
			$('#recordid').val(recordId);
			$('#taskid').val(taskId);
			$('#procinsid').val(procinsId);
			$('#carapplyDetail').load('<%=request.getContextPath()%>/carapply/queryById.action?carapplyId='+recordId,
					function(){
				layer.closeAll('loading');
				$('#handleTask').modal({
					backdrop:'static', /*点击黑色区域不关闭*/
					keyboard:false     /*ESC键不关闭*/
				});
				
			});
		}
		
	  	function submitForm(action){
	  		$('#action').val(action);
	  		$('#handleForm').submit();
	  	}
	  	
	  	
	</script>
<title>请假待办</title>
</head>

<body>

	<!-- 网页头部-->

	<jsp:include page="/include/header.jsp"></jsp:include>
	
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
						<li class="animate">综合查询<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath()%>/pages/Work/calendar.jsp">
	                	<li class="animate">日历查看<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/pages/Work/agent.jsp">
	                	<li class="animate">代读设置<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/pages/Work/approvetask.jsp">
	                	<li class="animate actived">待办审批<i class="fa fa-cog float-right"></i></li>
	                </a>
				</ul>
			</dropdown>
	</div>
	<!-- 左侧边栏完成 -->
         
         <!-- 右侧内容-->
			<div class="col-md-9">
			
				<h3 class="page-header">
					待审批申请
				</h3>	   
				
				<c:if test="${list.size()>0 }">
                <table class="table table-striped table-bordered table-condensed table-hover">
                    <thead>
                    <tr>
                        <th>
                        	申请员工
                        </th>
                       	<th width='40%'>
                         	 申请原因
                        </th>	
                        <th>
                         	部门领导
                        </th>				
                        <th>
                        	当前审批流程
                        </th>
                        <th>
                        	申请提交日期
                        </th>
                        <th>
                        	审批
                        </th>
                    </tr>	
                    </thead>	
                    <tbody>		
                    <c:forEach items="${list}" var="task">									
		                <tr>
		                	<td>${task.username }</td>
    						<td>${task.reason }</td>
    						<td>${task.leadername }</td>
    						<td>${task.taskname }</td>
    						<td><fmt:formatDate value="${task.createtime }"/></td>    
		                  	<td>
			                	<a href="javascript:handle(${task.taskid},${task.recordid},${task.procinsid})" class="btn btn-warning btn-xs">审批</a>     
		                  	</td>
		                </tr>		                
		                </c:forEach>
                     </tbody>
                </table>
                </c:if>
                
                 <c:if test="${list.size()<=0 }">
                	<div class="alert alert-info" role="alert">
		        		当前没有待您审批的用车申请
		        	</div>
                </c:if>
                <div class="col-sm-offset-9">
                	<a class="btn btn-success btn-block" href="<%=request.getContextPath()%>/pages/Work/approvetask.jsp">返回申请待办中心</a>
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
	<div class="modal fade" id="handleTask" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-header">
                	<button id="closeBtn" type="button" class="close" data-dismiss="modal">&times;</button>
                	<h4 class="text-center">请假申请详请</h4>
                </div>
            	<div class="modal-body">
					<div id="carapplyDetail"></div>  
                </div>
                <div class="modal-footer">
                	<div id="handleOpinion">
                		<form id="handleForm" class="form-group" method="post" action="<%=request.getContextPath()%>/processcar/handleTask.action">
							<textarea class="form-control" placeholder="请输入审批意见!" name="opinion"></textarea>
							<input type="hidden" name="action" id="action" value="0"> 
							<input type="hidden" name="recordid" id="recordid" value="0"> 
							<input type="hidden" name="taskid" id="taskid" value="0">
							<input type="hidden" name="procinsid" id="procinsid" value="0"> 
							<div class="text-center" style="margin-top:15px">
								<a href="javascript:submitForm(2)" class="btn btn-success">通过</a>
								<a href="javascript:submitForm(3)" class="btn btn-danger">拒绝</a>
							</div>
							
						</form>
                	</div>
                </div>
            </div>
        </div> 
    </div>
</body>

</html>