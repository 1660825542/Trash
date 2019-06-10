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
	<link rel="stylesheet" type="text/css" href="../../js/Validform/css/style.css">
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">

<script type="text/javascript">

	/*删除确认*/
	function del(roleId) {
		layer.load();
		var roleInfo = $('#'+roleId).parent().parent().parent().find('td').eq(1).text();
		$.get('<%=request.getContextPath()%>/role/queryIsToEmp.action?roleId='
			+ roleId,function(data,e){
			layer.closeAll('loading');
			if(data==0){
				layer.confirm('你确定要删除角色"'+roleInfo+'"吗?', {
					btn: ['确定','取消'], //按钮
					closeBtn:2,
					icon: 3,
					shade: false
				}, function(){
					layer.load();
					$.get( '<%=request.getContextPath()%>/role/delete.action?&roleId=' + roleId);
					$('#'+roleId).parent().parent().parent().remove();
					layer.closeAll('loading');
					layer.msg('删除成功', {icon: 1});
				});
			}
			
			if(data==1){
				layer.msg(roleInfo+'已经分配给员工，不可删除！', {icon: 0, shift:6});
			}
			
		});
			
	}

	function update(roleId){
		$('#roleNameDiv').removeClass("has-success has-feedback");
		$('#roleNameDiv').removeClass("has-error has-feedback");
		$('#roleInfoDiv').removeClass("has-success has-feedback");
		$('#roleInfoDiv').removeClass("has-error has-feedback");
		
		$('#roleInfo').modal({
				backdrop:'static', /*点击黑色区域不关闭*/
				keyboard:false     /*ESC键不关闭*/
			});
		
		$('#addBtn').hide();
		$('#setBtn').show();
		$('#roleInfo h4').text("修改角色信息");
		$('#roleId').val($('#'+roleId).parent().parent().parent().find("td").eq(0).text());
		$('#roleName').val($('#'+roleId).parent().parent().parent().find("td").eq(2).text());
		$('#roleInformation').val($('#'+roleId).parent().parent().parent().find("td").eq(1).text());
	
		
		$('#closeBtn').click(function(){
			$('#roleId').val('');
			$('#roleName').val('');
			$('#roleInformation').val('');
		});
		
		$('#roleSet').click(function(){
			layer.load();
			layer.load();
			if($('#roleName').val()==null || $('#roleName').val()=='') 
			{
				layer.closeAll('loading');
				$('#roleNameDiv').addClass("form-group has-error has-feedback");
			}else{
				$('#roleNameDiv').removeClass("has-error has-feedback");
				$('#roleNameDiv').addClass("form-group has-success has-feedback");
			}
			if($('#roleInformation').val()==null || $('#roleInformation').val()==''){
				layer.closeAll('loading');
				$('#roleInfoDiv').addClass("form-group has-error has-feedback");
			}else{
				$('#roleInfoDiv').removeClass("has-error has-feedback");
				$('#roleInfoDiv').addClass("form-group has-success has-feedback");
			}
			
			if($('#roleName').val()!=null && $('#roleName').val()!='' && $('#roleInformation').val()!=null && $('#roleInformation').val()!=''){
				
				$.post('<%=request.getContextPath()%>/role/edit.action',{roleId:$('#roleId').val(),roleName:$('#roleName').val(),roleInfo:$('#roleInformation').val()});
				var ID = $('#roleId').val();
				$('#'+ID).parent().parent().parent().find("td").eq(2).text($('#roleName').val());
				$('#'+ID).parent().parent().parent().find("td").eq(1).text($('#roleInformation').val());
				$('#closeBtn').click();
				layer.closeAll('loading');
				layer.msg('修改成功', {icon: 1});
			}
		});
			
	}
	
	function add(){
		$('#roleNameDiv').removeClass("has-success has-feedback");
		$('#roleNameDiv').removeClass("has-error has-feedback");
		$('#roleInfoDiv').removeClass("has-success has-feedback");
		$('#roleInfoDiv').removeClass("has-error has-feedback");
		$('#roleInfo').modal({
			backdrop:'static', /*点击黑色区域不关闭*/
			keyboard:false     /*ESC键不关闭*/
		});
	
		$('#roleInfo h4').text("增加新角色");
		$('#setBtn').hide();
		$('#addBtn').show();
		
		$('#closeBtn').click(function(){
			$('#roleId').val('');
			$('#roleName').val('');
			$('#roleInformation').val('');
		});
		
		$('#roleAdd').click(function(){
			layer.load();
			if($('#roleName').val()==null || $('#roleName').val()=='') 
			{
				layer.closeAll('loading');
				$('#roleNameDiv').addClass("form-group has-error has-feedback");
			}else{
				$('#roleNameDiv').removeClass("has-error has-feedback");
				$('#roleNameDiv').addClass("form-group has-success has-feedback");
			}
			if($('#roleInformation').val()==null || $('#roleInformation').val()==''){
				layer.closeAll('loading');
				$('#roleInfoDiv').addClass("form-group has-error has-feedback");
			}else{
				$('#roleInfoDiv').removeClass("has-error has-feedback");
				$('#roleInfoDiv').addClass("form-group has-success has-feedback");
			}
			
			if($('#roleName').val()!=null && $('#roleName').val()!='' && $('#roleInformation').val()!=null && $('#roleInformation').val()!=''){
				
				$('#roleNameDiv').addClass("form-group has-success has-feedback");
				$('#roleInfoDiv').addClass("form-group has-success has-feedback");
				var roleNameForm=$('#roleName').val();
				var roleInfoForm=$('#roleInformation').val();
				$.post('<%=request.getContextPath()%>/role/add.action',{roleName:roleNameForm,roleInfo:roleInfoForm},
						function(data){
					$('tbody').append('<tr><td>'+data+'</td><td>'+roleInfoForm+
							'</td><td>'+roleNameForm
							+'</td><td><div class="btn-group btn-xs"><button  type="button" class="btn btn-default  btn-xs drop'
							+'down-toggle  input-xs" id="dropdownMenu" data-toggle="dropdown">操作<span class="caret"></span></button>'
							+'<ul id="'+data+'" class="dropdown-menu"><li><a href="javascript:update('+data+')">修改信息</a></li>'
							+'<li><a href="javascript:setPerm('+data+')">设置权限</a></li><li><a href="javascript:del('
							+data+');">删除角色</a></li></ul></td></div> ');
				});
				layer.closeAll('loading');
				$('#closeBtn').click();
				layer.msg('增加成功', {icon: 1});
			}
		});
	}
	function setPerm(roleId){
		layer.open({
			type: 2,
			title: '权限设置',
			maxmin: false,
			shadeClose: false, //点击遮罩关闭层
			area : ['700px' , '480px'],
			shift: 5,
			content: ['<%=request.getContextPath()%>/perm/querySelectPerm.action?roleId='+roleId,'no']
		});
	}
</script>
<title>角色管理</title>

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
	                	<li class="animate actived">角色管理<i class="fa fa-code float-right"></i></li>
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
				角色管理
			</h3>	 
			
			 <table class="table table-striped table-bordered table-condensed table-hover">
                    <thead>
                    <tr>
                        <th width="80">
                        	角色编号
                        </th>
                        <th>
                        	角色描述
                        </th>
                        <th>
                         	 角色名
                        </th>					
                        <th width="50">
                        	操作
                        </th>
                    </tr>	
                    </thead>	
                    <tbody>		
                    <c:forEach items="${list}" var="role">									
		                <tr>
		                  	<td>${role.roleId }</td>
		                  	<td>${role.roleInfo }</td>
		                  	<td>${role.roleName }</td>
		                  	<td>
		                  		<div class="btn-group btn-xs">
					            	<button  type="button" class="btn btn-default  btn-xs dropdown-toggle  input-xs" id="dropdownMenu" data-toggle="dropdown">
					                   	 操作
					                    <span class="caret"></span>
					                </button>
					                <ul id="${role.roleId }" class="dropdown-menu">
					                    <li><a href="javascript:update(${role.roleId })">修改信息</a></li>
					                    <li><a href="javascript:setPerm(${role.roleId })">设置权限</a></li>
					                   	<li><a href="javascript:del(${role.roleId });">删除角色</a></li>			                    
					                </ul>
					            </div> 
		                  	</td>
		                </tr>		                
		                </c:forEach>
                     </tbody>
                </table>
                
			    <div class="col-sm-offset-9">
			    	<a href="javascript:add();" class="btn btn-primary btn-block">
		    			增加新角色
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
    <div class="modal fade" id="roleInfo" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-header">
                	<button id="closeBtn" type="button" class="close" data-dismiss="modal">&times;</button>
                	<h4 class="text-center"></h4>
                </div>
            	<div class="modal-body">
                	<div id="roleForm" class="form-horizontal">
			        	<div class="form-group"> 
			                <div class="col-sm-6">
			                	<input type="hidden" name="roleId" id="roleId" class="form-control">               
			                </div>
			            </div>
			            
			            <div id="roleInfoDiv" class="form-group">
			                <label class="col-sm-4 control-label">角色描述</label>
			                <div class="col-sm-6">
			                	<input type="text" name="roleInfo" id="roleInformation" class="form-control" placeholder="请输入角色中文描述 " >            
			                </div>
			            </div>
			            
			            <div id="roleNameDiv" class="form-group">
			                <label class="col-sm-4 control-label">角色名</label>
			                <div class="col-sm-6">
			                	<input type="text" name="roleName" id="roleName" class="form-control" placeholder="请输入角色名称 ">              
			                </div>
			            </div>

			            <div id="setBtn" class="form-group">
			            	<div class="col-sm-2 col-sm-offset-8">
			            		<button id="roleSet" class="btn btn-primary btn-block">修改</button> 		            		
			                </div>
			            </div>
			            <div id="addBtn" class="form-group">
			            	<div class="col-sm-2 col-sm-offset-8">
			            		<button id="roleAdd" class="btn btn-primary btn-block">新增</button> 		            		
			                </div>
			            </div>
		        	</div>  
                </div>
            </div>
        </div> 
    </div>
	
</body>

</html>