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
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/style-bar.css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">

	<script type="text/javascript">
	/*删除确认*/
	function del(empId) {
		var empName = $('#'+empId).parent().parent().parent().find('td').eq(1).text();
		layer.confirm('你确定要删除员工"'+empName+'"吗?', {
			btn: ['确定','取消'], //按钮
			closeBtn:2,
			icon: 3,
			shade:false
		}, function(){
			layer.load();
			$.get( '<%=request.getContextPath()%>/emp/delete.action?pageNum=${pageNum}&empId=' + empId);
			$('#'+empId).parent().parent().parent().remove();
			layer.closeAll('loading');
			layer.msg('删除成功', {icon: 1});
		});
	}
	
	function setPerm(empId){
		layer.open({
			type: 2,
			title: '角色设置',
			maxmin: false,
			closeBtn:2,
			shadeClose: false, //点击遮罩关闭层
			area : ['700px' , '480px'],
			shift: 5,
			content: ['<%=request.getContextPath()%>/role/querySelectRole.action?empId='+empId,'no']
		});
	}
	
	var folderEmp;
	function setFolderSize(empId){
		folderEmp=empId;
		$('#sizeDiv').removeClass("has-success has-feedback");
		$('#sizeDiv').removeClass("has-error has-feedback");
		$.get('<%=request.getContextPath()%>/cloud/getSize.action?empId='+empId,function(data){
			$('#folderSize').val(data/(1024*1024*1024));
			$('#sizeInfo').modal({
				backdrop:'static', /*点击黑色区域不关闭*/
				keyboard:false     /*ESC键不关闭*/
			});
		});
		
		$('#closeBtn2').click(function(){
			$('#folderSize').val('');
		});
		
		$('#sizeBtn').click(function(){
			layer.load();
			if($('#folderSize').val()==null || $('#folderSize').val()<=0 || $('#folderSize').val()>10){
				$('#sizeDiv').addClass("form-group has-error has-feedback");
				layer.closeAll('loading');
				layer.msg('请输入合法数值，最少为1G,最大为10G!', {icon: 0});
			}else if($('#folderSize').val()!=null && $('#folderSize').val()>0 && $('#folderSize').val()<=10){
				$('#sizeDiv').removeClass("form-group has-error has-feedback");
				$('#sizeDiv').addClass("form-group has-success has-feedback");
				$.post('<%=request.getContextPath()%>/cloud/setSize.action',{empId:folderEmp,folderSize:$('#folderSize').val()},function(data){
					layer.closeAll('loading');
					layer.msg('修改成功', {icon: 1});
				});
				$('#closeBtn2').click();
			} 
				
		});
	}
	
	/*分页显示*/
	var pageNum =${pager.pageNum};//第几页
	var recordCount = ${queryResult.recordCount};//总记录数
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
<title>员工检索结果</title>
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
				<label for="toggle1"class="animate">系统管理<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/dept/query.action">
						<li class="animate">部门管理<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/pos/query.action">
						<li class="animate">职务管理<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath()%>/emp/query.action">
	                	<li class="animate actived">员工管理<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/role/query.action">
	                	<li class="animate">角色管理<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/perm/query.action">
	                	<li class="animate">权限管理<i class="fa fa-cog float-right"></i></li>
	                </a>
				</ul>
			</dropdown>
		</div>           
         <!-- 侧边栏完成 -->
         
         <!-- 右侧内容-->
			<div class="col-md-9">
			
				<h3 class="page-header">
					员工检索结果
				</h3>	   
				
				<form id="index" action="<%=request.getContextPath() %>/emp/queryByIndex.action" method="post">
					<div class="input-group">                	
	                	<input type="text" value="${keyword }" id="keyword" name="keyword" placeholder="请输入关键字" class="form-control">
	                    <span class="input-group-btn">
	                    	<input id="indexBtn" type="button" class="btn btn-primary" value="检索员工">
	                    </span>
	                </div>
	                <input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum }">
                </form>  
                <br />
                <c:if test="${queryResult.list.size()>0 }">
                <table class="table table-striped table-condensed table-hover">
                    <thead>
                    <tr>
                        <th width="80">
                        	员工编号
                        </th>
                        <th>
                        	员工姓名
                        </th>
                        <th>
                         	 所属部门
                        </th>				
                        <th>
                        	操作
                        </th>
                    </tr>	
                    </thead>	
                    <tbody>		
                    <c:forEach items="${queryResult.list}" var="emp">									
		                <tr>
		                  	<td>${emp.empId }</td>
		                  	<td>${emp.empName }</td>
		                  	<td>${emp.deptName }</td>
		                  	<td>
			                  	<div class="btn-group btn-xs">
					            	<button  type="button" class="btn btn-default  btn-xs dropdown-toggle  input-xs" id="dropdownMenu" data-toggle="dropdown">
					                   	 操作
					                    <span class="caret"></span>
					                </button>
					                <ul id="${emp.empId }" class="dropdown-menu">
					                    <li><a href="<%=request.getContextPath()%>/emp/detail.action?empId=${emp.empId}">查看/修改详细信息</a></li>
					                    <li><a href="javascript:setFolderSize(${emp.empId })">设置云存储空间</a></li>
					                    <li><a href="javascript:setPerm(${emp.empId })">设置系统角色</a></li>
					                    <c:if test="${emp.empId!=0 }">
					                   	<li><a href="javascript:del(${emp.empId });">删除员工</a></li>	
					                   	</c:if>		                    
					                </ul>
					            </div>    
		                  	</td>
		                </tr>		                
		                </c:forEach>
                     </tbody>
                </table>
                </c:if>
                <c:if test="${queryResult.list.size()<=0 }">
                	<div class="alert alert-info" role="alert">
		        		没有检索到员工！
		        	</div>
                </c:if>
                
			    <div class="col-sm-offset-9">
			    	<a href="<%=request.getContextPath()%>/emp/query.action">
		    			<input type="button" class="btn btn-success col-sm-4 btn-block" value="返回员工管理" >
		    		</a>
	       			<!-- 响应式布局，移动端才会有返回主页的按钮 -->
	       			<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
	       			<!-- / --> 			    		
	       		</div>
       		
		    	<!-- 页码 -->
		    	<nav class="text-center">
		    		<ul class="pagination">
		    			<li><a id="prevBtn" href="#">上页</a></li>
		    			<li><a>共检索出${queryResult.recordCount }记录，每页${pager.pageSize }条，当前是第${pager.pageNum }页</a></li>
		    			<li><a id="nextBtn" href="#">下页</a></li>
		    		</ul>
		    	</nav>		   		    		    	  
		     </div>  
		 <!--右侧内容完 -->
	</div>

	<!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
	
	<div class="modal fade" id="sizeInfo" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-body">
            		<div class="form-horizontal">
            		<button id="closeBtn2" type="button" class="close" data-dismiss="modal">&times;</button>
	            		<div class="form-group">
			                <label class="col-sm-3 col-sm-offset-1 control-label">文件夹大小(GB)</label>
			                <div id="sizeDiv" class="col-sm-3">
			                	<input type="number" id="folderSize" class="form-control">              
			                </div>
			                <div class="col-sm-2">
			            		<button id="sizeBtn" class="btn btn-primary btn-block">修改</button> 		            		
			                </div>
			            </div>
		            </div>
                </div>
            </div>
        </div> 
    </div>
</body>

</html>