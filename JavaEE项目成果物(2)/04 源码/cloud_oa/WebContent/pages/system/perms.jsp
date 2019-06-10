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
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">

<script type="text/javascript">
	
	function update(permId){
		layer.load();
		var permName = $('#'+permId).parent().parent().parent().find('td').eq(2).text();
		var IDD = $('#'+permId).parent().parent().parent().find('td').eq(0).text();
		var permInfo = $('#'+permId).parent().parent().parent().find('td').eq(1).text();
		$('#permNameDiv').removeClass("has-success has-feedback");
		$('#permNameDiv').removeClass("has-error has-feedback");
		$('#permInfoDiv').removeClass("has-success has-feedback");
		$('#permInfoDiv').removeClass("has-error has-feedback");
		
		$('#closeBtn').click(function(){
			$('#permId').val('');
			$('#permName').val('');
			$('#permInformation').val('');
		});
		
		function edit(){
			$('#permInfo').modal({
					backdrop:'static', /*点击黑色区域不关闭*/
					keyboard:false     /*ESC键不关闭*/
				});
			$('#addBtn').hide();
			$('#setBtn').show();
			$('#permInfo h4').text("修改权限信息");
			$('#permId').val(IDD);
			$('#permName').val(permName);
			$('#permInformation').val(permInfo);
			
			
			$('#permSet').click(function(){
				layer.load();
				if($('#permName').val()==null || $('#permName').val()=='') 
				{
					layer.closeAll('loading');
					$('#permNameDiv').addClass("form-group has-error has-feedback");
				}else{
					$('#permNameDiv').removeClass("has-error has-feedback");
					$('#permNameDiv').addClass("form-group has-success has-feedback");
				}
				if($('#permInformation').val()==null || $('#permInformation').val()==''){
					layer.closeAll('loading');
					$('#permInfoDiv').addClass("form-group has-error has-feedback");
				}else{
					$('#permInfoDiv').removeClass("has-error has-feedback");
					$('#permInfoDiv').addClass("form-group has-success has-feedback");
				}
				
				if($('#permName').val()!=null && $('#permName').val()!='' && $('#permInformation').val()!=null && $('#permInformation').val()!=''){
					
					$.post('<%=request.getContextPath()%>/perm/edit.action',{permId:$('#permId').val(),permName:$('#permName').val(),permInfo:$('#permInformation').val()});
					var ID = $('#permId').val();
					$('#'+ID).parent().parent().parent().find("td").eq(2).text($('#permName').val());
					$('#'+ID).parent().parent().parent().find("td").eq(1).text($('#permInformation').val());
					$('#closeBtn').click();
					layer.closeAll('loading');
					layer.msg('修改成功', {icon: 1});
				}
			});
		}
		
		$.get('<%=request.getContextPath()%>/perm/queryIsToRole.action?permId='
			+ IDD,function(data,e){
			layer.closeAll('loading');
			if(data==1){
				layer.confirm('"'+permInfo+'"已经分配给角色，你确定要修改吗?', {
					btn: ['确定','取消'], //按钮
					closeBtn:2,
					icon: 3,
					shade: false
				}, function(){
					layer.closeAll();
					edit();
					});
			}
			if(data==0){
				edit();
			}
		});
	}
	
	function add(){
		$('#permNameDiv').removeClass("has-success has-feedback");
		$('#permNameDiv').removeClass("has-error has-feedback");
		$('#permInfoDiv').removeClass("has-success has-feedback");
		$('#permInfoDiv').removeClass("has-error has-feedback");
		$('#permInfo').modal({
			backdrop:'static', /*点击黑色区域不关闭*/
			keyboard:false     /*ESC键不关闭*/
		});
	
		$('#permInfo h4').text("增加新权限");
		$('#setBtn').hide();
		$('#addBtn').show();
		
		$('#closeBtn').click(function(){
			$('#permId').val('');
			$('#permName').val('');
			$('#permInformation').val('');
		});
		
		$('#permAdd').click(function(){
			layer.load();
			if($('#permName').val()==null || $('#permName').val()=='') 
			{
				layer.closeAll('loading');
				$('#permNameDiv').addClass("form-group has-error has-feedback");
			}else{
				$('#permNameDiv').removeClass("has-error has-feedback");
				$('#permNameDiv').addClass("form-group has-success has-feedback");
			}
			if($('#permInformation').val()==null || $('#permInformation').val()==''){
				layer.closeAll('loading');
				$('#permInfoDiv').addClass("form-group has-error has-feedback");
			}else{
				$('#permInfoDiv').removeClass("has-error has-feedback");
				$('#permInfoDiv').addClass("form-group has-success has-feedback");
			}
			
			if($('#permName').val()!=null && $('#permName').val()!='' && $('#permInformation').val()!=null && $('#permInformation').val()!=''){
				
				$('#permNameDiv').addClass("form-group has-success has-feedback");
				$('#permInfoDiv').addClass("form-group has-success has-feedback");
				var permNameForm=$('#permName').val();
				var permInfoForm=$('#permInformation').val();
				$.post('<%=request.getContextPath()%>/perm/add.action',{permName:permNameForm,permInfo:permInfoForm},
						function(data){
					$('tbody').append('<tr><td>'+data+'</td><td>'+permInfoForm+
							'</td><td>'+permNameForm+'</td><td><a href="javascript:update('+data+');"><input id="'+data
							+'" class="btn btn-default btn-xs" type="button" value="修改" ></a></td></tr>'); 
				});
				layer.closeAll('loading');
				$('#closeBtn').click();
				layer.msg('增加成功', {icon: 1});
			}
		});
	}
</script>
<title>权限管理</title>

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
	                	<li class="animate">员工管理<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/role/query.action">
	                	<li class="animate">角色管理<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath()%>/perm/query.action">
	                	<li class="animate actived">权限管理<i class="fa fa-cog float-right"></i></li>
	                </a>
				</ul>
			</dropdown>
		</div>           
         <!-- 侧边栏完成 -->
         
         <!-- 右侧内容-->
		<div class="col-md-9">
			
			<h3 class="page-header">
				权限管理
			</h3>	 
			
			 <table class="table table-striped table-bordered table-condensed table-hover">
                    <thead>
                    <tr>
                        <th width="80">
                        	权限编号
                        </th>
                        <th>
                        	权限描述
                        </th>
                        <th>
                         	 权限名
                        </th>					
                        <th width="50">
                        	操作
                        </th>
                    </tr>	
                    </thead>	
                    <tbody>		
                    <c:forEach items="${list}" var="perm">									
		                <tr>
		                  	<td>${perm.permId }</td>
		                  	<td>${perm.permInfo }</td>
		                  	<td>${perm.permName }</td>
		                  	<td>
		                  		<a href="javascript:update(${perm.permId });">
		                  			<input id="${perm.permId }" class="btn btn-default btn-xs" type="button" value="修改" >
		                  		</a>
		                  	</td>
		                </tr>		                
		                </c:forEach>
                     </tbody>
                </table>
                
			    <div class="col-sm-offset-9">
			    	<a href="javascript:add();" class="btn btn-primary btn-block" >
		    			增加新权限
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
    <div class="modal fade" id="permInfo" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-header">
                	<button id="closeBtn" type="button" class="close" data-dismiss="modal">&times;</button>
                	<h4 class="text-center"></h4>
                </div>
            	<div class="modal-body">
                	<div id="permForm" class="form-horizontal">
			        	<div class="form-group"> 
			                <div class="col-sm-6">
			                	<input type="hidden" name="permId" id="permId" class="form-control">               
			                </div>
			            </div>
			            
			            <div id="permInfoDiv" class="form-group">
			                <label class="col-sm-4 control-label">权限描述</label>
			                <div class="col-sm-6">
			                	<input type="text" name="permInfo" id="permInformation" class="form-control" placeholder="请输入权限中文描述 " >            
			                </div>
			            </div>
			            
			            <div id="permNameDiv" class="form-group">
			                <label class="col-sm-4 control-label">权限名</label>
			                <div class="col-sm-6">
			                	<input type="text" name="permName" id="permName" class="form-control" placeholder="请输入权限名称 ">              
			                </div>
			            </div>
			            

			            <div id="setBtn" class="form-group">
			            	<div class="col-sm-2 col-sm-offset-8">
			            		<button id="permSet" class="btn btn-primary btn-block">修改</button> 		            		
			                </div>
			            </div>
			            <div id="addBtn" class="form-group">
			            	<div class="col-sm-2 col-sm-offset-8">
			            		<button id="permAdd" class="btn btn-primary btn-block">新增</button> 		            		
			                </div>
			            </div>
		        	</div>  
                </div>
            </div>
        </div> 
    </div>
	
</body>

</html>