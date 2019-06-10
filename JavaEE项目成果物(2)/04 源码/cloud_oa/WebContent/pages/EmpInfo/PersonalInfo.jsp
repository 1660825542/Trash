<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>

<%@ page import="com.icss.oa.system.pojo.*" %>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
	<script type="text/javascript">
	
	$(document).ready(function(e) {        
				
		/*单击按钮弹出模态层*/
		$('#changePwd').click(function(e){
			
			$('#password1').val('');
			
			$('#loginModal').modal
			(
				{
					backdrop:'static', /*点击黑色区域不关闭*/
					keyboard:false    /*ESC键不关闭*/
				}
			);

			$('#navigation').fadeOut("fast").addClass("fixed-top-changed");
		});
			
			$('#loginform2').Validform({
				tiptype:3,
				ajaxPost:true,
				callback:function(data){
					
					$.Hidemsg();
					
					if (data.status == 'y') {
						$('#closeLogin').click();
						savePwd();												
					}
					else{
						$('#btn3').button('reset');
					}
				}
			});
			
			function savePwd(){
				
				$('#newPwd').val('');
				$('#confirm').val('');
				
				$('#savePwdModal').modal
				(
					{
						backdrop:'static', /*点击黑色区域不关闭*/
						keyboard:false     /*ESC键不关闭*/
					}
				);

				$('#navigation').fadeOut("fast").addClass("fixed-top-changed");
			}
		
		$('#form').Validform({
			tiptype:3,
			ajax:true,
			datatype:{'confirm':function(gets,obj,curform,regxp){				
					
					var newPwd = $('#newPwd').val();
					if (gets != newPwd) {
						return false;									
					}
					
					return true;
				}
			},
			callback:function(data){										
				$('#closeConfirm').click();
			}
		});
	});
	</script>
	<title>个人信息</title>
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
						<li class="animate actived">员工信息维护<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath()%>/empInfo/queryAll.action">
						<li class="animate">检索他人信息<i class="fa fa-code float-right"></i></li>
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
					个人信息查询
				</h3>
	    		<div class="col-md-11">
		    	<table class="table table-striped">	      	      	  
  	    	  		<tbody>
  	    	  			<tr>
			                <td style="text-align:center" width="40%"><label class="control-label">登录名</label></td>
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
	      	    			<td style="text-align:center"><label class="control-label">性别</label></td>
	      	    			<td>
	      	    				<label class="control-label">${gender}</label>
	   	      				</td>
	   	      			</tr>
	   	      			<tr>
	      	    			<td style="text-align:center"><label class="control-label">生日</label></td>
	      	    			<td>
	      	    				<label class="control-label">${emp.birthday}</label>
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
	            <div class="form-group">
	            	<div class="col-sm-offset-2 col-sm-10">
	            		<a href="<%=request.getContextPath()%>/empInfo/changeInfo.action?empId=${emp.empId}" id="changeInfo" class="btn btn-primary col-md-4">修改信息</a>
	            		<button type="button" class="btn btn-primary col-md-4 col-sm-offset-2" id="changePwd">密码修改</button>
	            		<!-- <a href="javascript:changePwd()" id="changePwd" class="btn btn-primary col-md-4 col-sm-offset-2">密码修改</a> -->
	            		<!-- 响应式布局，移动端才会有返回主页的按钮 -->
	            		<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
	            		<!-- / --> 
	                </div>
	            </div>                 		      		
		     </div>
		 <!--  修改密码 登录验证 -->    
	<div class="modal fade" id="loginModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" id="closeLogin" data-dismiss="modal">&times;</button>
					<h4 class="text-center">登录验证</h4>
				</div>

				<div class="modal-body">
					<form id="loginform2" class="form-horizontal" method="post" action="<%=request.getContextPath() %>/empInfo/login.action">
			        	<div class="form-group"> 			                
			                <div class="col-sm-8 col-sm-offset-2">
			                	<input type="text" name="empNum" id="empNum" class="form-control" value="<%=((Employee)request.getSession().getAttribute("queryemp")).getEmpNum()%>" readonly>               
			                </div>
			            </div>
			            
			            <div class="form-group">			                
			                <div class="col-sm-8 col-sm-offset-2">
			                	<input datatype="*" type="password" name="password" id="password1" class="form-control" placeholder="请输入密码 ">              
			                </div>
			            </div>

			            <div class="form-group">
			            	<div class="col-sm-8 col-sm-offset-2">
			            		<input type="submit" id="btn3" class="btn btn-primary btn-block" data-loading-text="正在确认..." value="登录">
			            		<input type="hidden" id="closeLogin" data-dismiss="modal"> 		            		
			                </div>
			            </div>
		        	</form>  
				</div>
			</div>
		</div>
	</div>
	
	<!-- 设置新密码 -->
	
	<div class="modal fade" id="savePwdModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" id="closePwd" data-dismiss="modal">&times;</button>
					<h4 class="text-center">设置新密码</h4>
				</div>

				<div class="modal-body">
					<form id="form" class="form-horizontal" method="post" action="<%=request.getContextPath() %>/empInfo/savePwd.action">
			        	<div class="form-group"> 			               
			                <div class="col-sm-8 col-sm-offset-2">
			                	<input type="password" datatype="*" name="newPwd" id="newPwd" class="form-control" placeholder="请输入新密码 ">               
			                </div>
			            </div>
			            
			            <div class="form-group">			          
			                <div class="col-sm-8 col-sm-offset-2">
			                	<input type="password" datatype="confirm" errormsg="两次密码不相同" name="confirm" class="form-control" placeholder="确认新密码 ">              
			                </div>
			            </div>

			            <div class="form-group">
			            	<div class="col-sm-8 col-sm-offset-2">
			            		<input type="submit" id="btn2" class="btn btn-primary btn-block" value="确认修改"> 	
			            		<input type="hidden" id="closeConfirm" data-dismiss="modal">	            		
			                </div>
			            </div>
		        	</form>  
				</div>
			</div>
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