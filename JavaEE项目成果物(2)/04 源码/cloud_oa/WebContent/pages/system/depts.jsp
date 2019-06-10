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
	function del(deptId) {
		layer.load();
		var deptName = $('#'+deptId).parent().parent().parent().find('td').eq(1).text();
		$.get('<%=request.getContextPath()%>/dept/queryAjaxHaveChild.action?deptId='
			+ deptId,function(data,e){
			layer.closeAll('loading');
			if(data==0){
				layer.confirm('你确定要删除部门"'+deptName+'"吗?', {
					btn: ['确定','取消'], //按钮
					closeBtn:2,
					icon: 3,
					shade: false
				}, function(){
					layer.load();
					$.get( '<%=request.getContextPath()%>/dept/delete.action?pageNum=${pageNum}&deptId=' + deptId);
					$('#'+deptId).parent().parent().parent().remove();
					layer.closeAll('loading');
					layer.msg('删除成功', {icon: 1});
				});
			}
			
			if(data==1){
				layer.msg(deptName+'有下属员工，不可删除！', {icon: 0, shift:6});
			}
			
		});
			
	}

	
	function update(deptId){
		layer.load();
		var deptName = $('#'+deptId).parent().parent().parent().find('td').eq(1).text();
		var ID = $('#'+deptId).parent().parent().parent().find('td').eq(0).text();
		var Info = $('#'+deptId).parent().parent().parent().find('td').eq(2).text();
		$('#deptNameDiv').removeClass("has-success has-feedback");
		$('#deptNameDiv').removeClass("has-error has-feedback");
		$.get('<%=request.getContextPath()%>/dept/queryAjaxHaveChild.action?deptId='
			+ ID,function(data,e){
			if(data==0){
				layer.closeAll('loading');
				$('#deptInfo').modal({
						backdrop:'static', /*点击黑色区域不关闭*/
						keyboard:false     /*ESC键不关闭*/
					});
				$('#addBtn').hide();
				$('#setBtn').show();
				$('#deptInfo h4').text("修改部门信息");
				$('#deptId').val(ID);
				$('#deptName').val(deptName);
				$('#deptInformation').val(Info);
			
				$('#closeBtn').click(function(){
					$('#deptId').val('');
					$('#deptName').val('');
					$('#deptInformation').val('');
				});
				
				$('#deptSet').click(function(){
					layer.load();
					if($('#deptName').val()==null || $('#deptName').val()=='' || $('#deptName').val().length>16 ||  $('#deptInformation').val().length>66)
					{
						layer.closeAll('loading');
						$('#deptNameDiv').addClass("form-group has-error has-feedback");
					}
					else if($('#deptName').val()!=null  || $('#deptName').val().length>16 ||  $('#deptInformation').val().length>66){
						$('#deptNameDiv').addClass("form-group has-success has-feedback");
						$.post('<%=request.getContextPath()%>/dept/update.action',{deptId:$('#deptId').val(),deptName:$('#deptName').val(),deptInfo:$('#deptInformation').val()});
						var ID = $('#deptId').val();
						$('#'+ID).parent().parent().parent().find("td").eq(1).text($('#deptName').val());
						$('#'+ID).parent().parent().parent().find("td").eq(2).text($('#deptInformation').val());
						$('#closeBtn').click();
						layer.closeAll('loading');
						layer.msg('修改成功', {icon: 1});
					}
				});
			}
			if(data==1){
				layer.closeAll('loading');
				layer.msg(deptName+'有下属员工，不可修改！', {icon: 0, shift:6});
			}
		});
	}
	
	function add(){
		$('#deptNameDiv').removeClass("has-success has-feedback");
		$('#deptNameDiv').removeClass("has-error has-feedback");
		$('#deptInfo').modal({
			backdrop:'static', /*点击黑色区域不关闭*/
			keyboard:false     /*ESC键不关闭*/
		});
	
		$('#deptInfo h4').text("增加部门信息");
		$('#setBtn').hide();
		$('#addBtn').show();
		
		$('#closeBtn').click(function(){
			$('#deptId').val('');
			$('#deptName').val('');
			$('#deptInformation').val('');
		});
		
		$('#deptAdd').click(function(){
			layer.load();
			if($('#deptName').val()==null || $('#deptName').val()=='')
			{
				layer.closeAll('loading');
				$('#deptNameDiv').addClass("form-group has-error has-feedback");
			}
			else if($('#deptName').val()!=null){
				$('#deptNameDiv').addClass("form-group has-success has-feedback");
				var deptNameForm=$('#deptName').val();
				var deptInfoForm=$('#deptInformation').val();
				$.post('<%=request.getContextPath()%>/dept/insert.action',{deptName:deptNameForm,deptInfo:deptInfoForm},
						function(data){
					$('tbody').append('<tr><td>'+data+'</td><td>'+deptNameForm+
							'</td><td>'+deptInfoForm+'</td><td><a href="javascript:update('+data+
									');" data-original-title title><input class="btn btn-default btn-xs" type="button" value="修改" >'
									+'</a><a href="javascript:del('+data+');" data-original-title title><input id="'+data+'" class="btn btn-default btn-xs" type="button" value="删除"></a></td></tr>'); 
					
				});
				layer.closeAll('loading');
				$('#closeBtn').click();
				layer.msg('增加成功', {icon: 1});
			}
		});
	}
</script>
<title>部门管理</title>

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
						<li class="animate actived">部门管理<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/pos/query.action">
						<li class="animate">职务管理<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath()%>/emp/query.action">
	                	<li class="animate">员工管理<i class="fa fa-code float-right"></i></li>
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
					部门管理
				</h3>	        
                <table class="table table-striped table-bordered table-condensed table-hover">
                    <thead>
                    <tr>
                        <th width="80">
                        	部门编号
                        </th>
                        <th width="100">
                        	部门名称
                        </th>
                        <th>
                         	 部门描述
                        </th>					
                        <th width="88">
                        	操作
                        </th>
                    </tr>	
                    </thead>	
                    <tbody>		
                    <c:forEach items="${list}" var="dept">									
		                <tr>
		                  	<td>${dept.deptId }</td>
		                  	<td>${dept.deptName }</td>
		                  	<td>${dept.deptInfo }</td>
		                  	<td>
		                  		<a href="javascript:update(${dept.deptId });">
		                  			<input id="${dept.deptId }" class="btn btn-default btn-xs" type="button" value="修改" >
		                  		</a>
		                  		<c:if test="${dept.deptId!=0 }">
		                  		<a href="javascript:del(${dept.deptId });">
		                  			<input class="btn btn-default btn-xs" type="button" value="删除">
		                  		</a>
		                  		</c:if>
		                  	</td>
		                </tr>		                
		                </c:forEach>
                     </tbody>
                </table>
                
			    <div class="col-sm-offset-9">
			    	<a href="javascript:add();" class="btn btn-primary btn-block">
						增加新部门
		    		</a>
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
	
	<!--模态层-->
    <div class="modal fade" id="deptInfo" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-header">
                	<button id="closeBtn" type="button" class="close" data-dismiss="modal">&times;</button>
                	<h4 class="text-center"></h4>
                </div>
            	<div class="modal-body">
                	<div id="deptForm" class="form-horizontal">
			        	<div class="form-group"> 
			                <div class="col-sm-6">
			                	<input type="hidden" datatype="*" name="deptId" id="deptId" class="form-control">               
			                </div>
			            </div>
			            
			            <div id="deptNameDiv" class="form-group">
			                <label class="col-sm-4 control-label">部门名称</label>
			                <div class="col-sm-6">
			                	<input datatype="*" type="text" name="deptName" id="deptName" class="form-control" placeholder="请输入部门名称 ">              
			                </div>
			            </div>
			            
			            <div class="form-group">
			                <label class="col-sm-4 control-label">部门描述</label>
			                <div class="col-sm-6">
			                	<textarea ignore="ignore" type="text" name="deptInfo" id="deptInformation" class="form-control" placeholder="请输入部门描述 " ></textarea>              
			                </div>
			            </div>

			            <div id="setBtn" class="form-group">
			            	<div class="col-sm-2 col-sm-offset-8">
			            		<button id="deptSet" class="btn btn-primary btn-block">修改</button> 		            		
			                </div>
			            </div>
			            <div id="addBtn" class="form-group">
			            	<div class="col-sm-2 col-sm-offset-8">
			            		<button id="deptAdd" class="btn btn-primary btn-block">新增</button> 		            		
			                </div>
			            </div>
		        	</div>  
                </div>
            </div>
        </div> 
    </div>
	
</body>

</html>