<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%
if(session.getAttribute("queryemp")==null){
	session.invalidate();
}
%>
<script type="text/javascript">
//全局的AJAX访问，处理AJAX清求时SESSION超时,shiro的错误页跳转等
jQuery(function($){
    // 备份jquery的ajax方法  
    var _ajax=$.ajax;
    // 重写ajax方法，先判断登录在执行success函数 
    $.ajax=function(opt){
    	var _success = opt && opt.success || function(a, b){};
        var _opt = $.extend(opt, {
        	success:function(data, textStatus){
        		var data2 = data.toString();
        		// 如果后台将请求重定向到了登录页，则data里面存放的就是登录页的源码，这里需要找到data是登录页的证据(标记)
        		if(data2.indexOf('loginPage')!=-1){
    				window.location.href="<%=request.getContextPath()%>/pages/indexLogin.jsp";
    				return;
    			}else if(data2.indexOf("myErrorPage")!=-1){
    				window.location.href="<%=request.getContextPath()%>/pages/error.jsp";
    				return;
    			}else if(data2.indexOf("myFailedPage")!=-1){
    				alert("对不起，您没有相关权限");
    				return;
    			}
    			
        		_success(data, textStatus);  
            }  
        });
        return _ajax(_opt);
    };
});

//得到未阅消息数量,并设置badge提示
function getNotReadCount(){
	$.get(
		"<%=request.getContextPath()%>/message/getNotReadCount.action",
		function(data){
			if(data<0){								
				alert('新消息读取失败');
			}else if(data==0){
				$('#infonotice').text(data);
				$('#infonotice').css("background-color","#777");
				$('span[name="count"]').text(data);
			}else{
				$('#infonotice').text(data);
				$('#infonotice').css("background-color","#F20000");
				$('span[name="count"]').text(data);
			}			
		}
	);
}

	//查询未阅消息时间间隔设置,测试阶段，那就半个小时一次吧，反正每次刷新网页都会读取的
	$(document).ready(function(e) {
		getNotReadCount();
		setInterval(function(){
			getNotReadCount();
		}, 180000);
    });
	//轮播时间间隔设置
	$(document).ready(function(e) {
		$('.carousel').carousel({
        interval: 4000
    });
    $(document).ready(function(e) {
        //初始化工具提示
		$('a,button[data-toggle="tooltip"]').tooltip();		
		$('#fuckhtml').popover();
    	});
	});

	//消息提示整体亮度控制
	$(document).ready(function(e) {		
		//鼠标移上
		$('#notice').mouseover(function(){
			if($('#infonotice').text()!="0"){
				$('#infonotice').addClass('noticeBg');	
			}else{
				$('#infonotice').css("background-color","#999");
			}
				
		});		
		//鼠标离开
		$('#notice').mouseout(function(){
			if($('#infonotice').text()!="0"){
				$('#infonotice').removeClass('noticeBg');
			}else{
				$('#infonotice').css("background-color","#777");
			}
			
		});						
	});

	//登陆界面模态层
	$(document).ready(function(e) 
	{        
	/*单击按钮弹出模态层*/
	$('#modalBtn').click(function(e){
		$('#myModal').modal
		(
			{
				backdrop:'static', /*点击黑色区域不关闭*/
				keyboard:false     /*ESC键不关闭*/
			}
		);

		$('#navigation').fadeOut("fast").addClass("fixed-top-changed");
	});	


    /*关闭时回到原来尺寸*/
	$('#closemodal').click(function(e){
		$('#navigation').removeClass("fixed-top-changed").delay(300).fadeIn("fast");
		});
    });
	
	$(document).ready(function(){
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#loginform').Validform({
			tiptype:0,
			ajaxPost:true,
			beforeSubmit:function(curform){return false;}
		});
		
		$('#btn2').click(function(e){			
			$('#btn2').button('loading');		
			var url = '<%=request.getContextPath() %>/login/login.action';
			$.get(url,{empNum:$('#empNum').val(),password:$('#password').val()},function(data){
				if (data == 'y') {
					var urlindex = '<%=request.getContextPath() %>/pages/index.jsp';
					location.href = urlindex;
					$('#btn2').button('reset');
				}
				if(data == 'nw'){
					layer.tips('用户名不存在！','#empNum',{tips: [1, '#BF7C00']});
					$('#btn2').button('reset');
				}
				if(data == 'pw'){
					layer.tips('密码错误！', '#password' ,{tips: [3, '#BF7C00']});
					$('#btn2').button('reset');
				}
			});
		});
	});
	</script>
	<!--登陆界面模态层-->
	<div class="modal fade" id="myModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" id="closemodal" data-dismiss="modal">&times;</button>
					<h4 class="text-center">切换用户</h4>
				</div>

				<div class="modal-body">
					<form id="loginform" class="form-horizontal" method="post" action="<%=request.getContextPath() %>/login/login.action">
			        	<div class="form-group"> 
			                <label for="userName" class="col-sm-4 control-label">账号</label>
			                <div class="col-sm-6">
			                	<input type="text" datatype="*" name="empNum" id="empNum" class="form-control" placeholder="请输入账号">               
			                </div>
			            </div>
			            
			            <div class="form-group">
			                <label for="userPwd" class="col-sm-4 control-label">密码</label>
			                <div class="col-sm-6">
			                	<input datatype="*" type="password" name="password" id="password" class="form-control" placeholder="请输入密码 ">              
			                </div>
			            </div>

			            <div class="form-group">
			            	<div class="col-sm-3 col-sm-offset-7">
			            		<input type="submit" id="btn2" class="btn btn-primary btn-block" data-loading-text="正在确认..." value="登录"> 		            		
			                </div>
			            </div>
		        	</form>  
				</div>


			</div>
		</div>
	</div>
	<!--/登陆界面模态层-->

	<!--导航条-->
	<nav id="navigation" class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">

			<div class="navbar-header" >
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#myCollapse1">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a href="<%=request.getContextPath()%>/pages/index.jsp" class="navbar-brand">
					<img src="<%=request.getContextPath()%>/image/default-transparent.png" height="20"/>
				</a>
			</div>
			<!--/.navbar-header-->

			<form if="userform" action="">
			<div class="collapse navbar-collapse navbar-right" id="myCollapse1">
				<ul class="nav navbar-nav nav-right">
					<li class="dropdown">
						<a href="<%=request.getContextPath() %>/empInfo/query.action" data-toggle="dropdown">
							当前用户：${queryemp.empName}
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu ">
							<li>
								<a href="#" id="modalBtn">切换用户</a>
							</li>
							<li>
								<a href="<%=request.getContextPath() %>/pages/indexLogin.jsp">退出</a>
							</li>
						</ul>
					</li>
					<li>
						<a id="notice" href="<%=request.getContextPath()%>/message/showNotRead.action">
							新消息：
							<span id="infonotice" class="badge">0</span>
						</a>
					</li>
					<li>
						<a href="#" id="fuckhtml" data-html="true" data-toggle="popover" data-content="<center><img width='30' src='<%=request.getContextPath()%>/image/ic_launcher.png'></img>&nbsp;<a style='font-size:1.1em;color:black;text-decoration:none' href='<%=request.getContextPath()%>/app/cloud.apk'>点击直接下载</a><br><img width='200' src='<%=request.getContextPath()%>/appuser/getApkQRcode.do'></img><br>扫一扫二维码，下载安装客户端</center>"data-placement="bottom"> <i class="fa fa-android"></i> 客户端</a></li>
					
				</ul>

			</div>
			<!--/.collapse-->
			</form>

		</div>
		<!--/.container-fluid-->
	</nav>
	<!--/导航条-->

	<!--轮播容器-->
	<header id="myCarousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		
		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<div class="fill" style="background-image:url('<%=request.getContextPath()%>/image/100.jpg');"></div>
				<div class="carousel-caption">
					<h2>学JAVA开发，到中软国际。</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill" style="background-image:url('<%=request.getContextPath()%>/image/101.jpg');"></div>
				<div class="carousel-caption">
					<h2>学iOS/Android开发，到中软国际。</h2>
				</div>
			</div>
			<div class="item">
				<div class="fill" style="background-image:url('<%=request.getContextPath()%>/image/102.jpg');"></div>
				<div class="carousel-caption">
					<h2>学C++开发，到中软国际。</h2>
				</div>
			</div>
		</div>
		<!-- Controls -->
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">
			<span class="icon-prev"></span>
		</a>
		<a class="right carousel-control" href="#myCarousel" data-slide="next">
			<span class="icon-next"></span>
		</a>
	</header>
	<!--/轮播容器-->
	
	<!--网页分类-->
	<nav class="navbar navbar-default hidden-sm hidden-xs">
		<div class="container">
			<div class="collapse navbar-collapse" id="myCollapse2">
			
				<ul class="nav navbar-nav">
				
					<li>
						<a href="<%=request.getContextPath()%>/pages/index.jsp">系统首页</a>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							个人中心
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu ">
							<li>
								<a href="<%=request.getContextPath()%>/empInfo/query.action">员工信息维护</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/empInfo/queryAll.action">检索他人信息</a>
							</li>
							<shiro:lacksRole name="manager">
							<li>
								<a href="<%=request.getContextPath()%>/pages/EmpInfo/apply.jsp">个人申请中心</a>
							</li>
							</shiro:lacksRole>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							工作安排
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="<%=request.getContextPath()%>/pages/Work/task.jsp">日程安排</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/work/queryAll.action">综合查询</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/pages/Work/calendar.jsp">日历查看</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/pages/Work/agent.jsp">代读设置</a>
							</li>
							<shiro:hasAnyRoles name="superadmin,caradmin,deptadmin,manager,money,adminstration">
							<li>
								<a href="<%=request.getContextPath()%>/pages/Work/approvetask.jsp">待办审批</a>
							</li>
							</shiro:hasAnyRoles>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							名片夹
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="<%=request.getContextPath()%>/Card/query.action">联系人查询</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/CardCategory/query1.action">名片夹分类</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/Card/queryAll.action">公司通讯录</a>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							消息收发
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="<%=request.getContextPath()%>/message/toSendMessage.action">发送消息</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/message/showDraft.action">草稿箱</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/message/showSendedMessage.action">已发送消息</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/message/toMyMessage.action">收到的消息</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/message/showNotRead.action">未阅的消息(<span name="count">0</span>)</a>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							云文件存储
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="<%=request.getContextPath()%>/pages/cloud/cloudfolders.jsp">云文件目录</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/pages/cloud/cloudshare.jsp">我的云共享</a>
							</li>
						</ul>
					</li>

					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							会议管理
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="<%=request.getContextPath() %>
						/room/query.action">会议室信息</a>
							</li>
							<li>
								<a href="<%=request.getContextPath() %>
						/meeting/query.action">会议预约情况</a>
							</li>
							<li>
								<a href="<%=request.getContextPath() %>
						/meeting/queryByEmpId.action">我要参加的会议</a>
							</li>
							<shiro:hasPermission name="apply">
							<li>
								<a href="<%=request.getContextPath() %>
						/meeting/queryByOriginator.action">我发布的会议</a>
							</li>
							</shiro:hasPermission>
						</ul>
					</li>

					<shiro:hasRole name="superadmin">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							系统管理
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="<%=request.getContextPath()%>/dept/query.action">部门管理</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/pos/query.action">职务管理</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/emp/query.action">员工管理</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/role/query.action">角色管理</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/perm/query.action">权限管理</a>
							</li>
						</ul>
					</li>
					</shiro:hasRole>
					<shiro:hasRole name="adminstration">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							资产管理
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="<%=request.getContextPath()%>/house/query.action">库存管理</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/poss/query.action">办公用品消耗</a>
							</li>
						</ul>
					</li>
					</shiro:hasRole>
					<shiro:hasRole name="caradmin">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							车辆管理
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="<%=request.getContextPath()%>/car/query.action">车辆查看</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/processcar/queryUserAllot.action">派车待办</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/carrecord/query.action">派车记录</a>
							</li>
						</ul>
					</li>
					</shiro:hasRole>
					<shiro:hasRole name="assignadmin">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							外派人才
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="<%=request.getContextPath()%>/assemp/query.action">外派人才列表</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/asscom/query.action">合作公司列表</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/comreq/query.action">公司需求列表</a>
							</li>
							<li>
								<a href="<%=request.getContextPath()%>/empcom/query.action">外派记录显示</a>
							</li>
						</ul>
					</li>
					</shiro:hasRole>
					<li>
						<a href="<%=request.getContextPath()%>/notice/queryByPager.action">
							系统公告
						</a>
					</li>
				</ul>
				
			</div>
			<!--/.collapse-->
		</div>
	</nav>