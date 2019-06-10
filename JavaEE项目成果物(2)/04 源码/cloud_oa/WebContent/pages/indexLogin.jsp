<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%session.invalidate();%>
<html>
<!DOCTYPE html>
<html class="no-js" http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <head>
        <meta charset="utf-8">
        <title>ICSS-OA登录</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="loginPage">
        <meta name="author" content="">
        
		<link rel="stylesheet" type="text/css" href="../css/common.css">
		<link rel="stylesheet" type="text/css" href="../js/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="../js/assets/css/reset.css">
        <link rel="stylesheet" href="../js/assets/css/supersized.css">
        <link rel="stylesheet" href="../js/assets/css/style.css">

		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

		<script type="text/javascript">	
		var returndata;
		
		
		function checkUUID(){
			
			$.ajax({
				url :"<%=request.getContextPath()%>/appuser/qrcodeLogin.do?uuid="
						+ returndata,
				context : document.body,
				success : function(data) {
					if (data == 'y') {
						var urlindex = '<%=request.getContextPath() %>/pages/index.jsp';
						location.href = urlindex;
						$('#btn2').button('reset');
					}
					if(data == 'nw'){
						layer.tips('用户名不存在！','#empNum',{tips: [2, '#5CA700']});
						$('#btn2').button('reset');
					}
					if(data == 'pw'){
						layer.tips('密码错误！', '#password' ,{tips: [2, '#5CA700']});
						$('#btn2').button('reset');
					}
					if(data == 'stillfalse'){
					}
					setTimeout(checkUUID, 1000);
				}
			});

		};
		
	    function qrcodeLogin(){
	    	
	    	 $('#content').fadeOut("fast");
	    	 
	    	 setTimeout(function() {
					$("#qrcode").fadeIn("slow");
			     }, 300)
			     
			     $.ajax({
						url :"<%=request.getContextPath()%>/appuser/getUUID.do",
						context : document.body,
						success : function(data) {
							returndata=data;
							$('#qrcodeimg').attr("src","<%=request.getContextPath()%>/appuser/getQRcode.do?uuid="+data);
							checkUUID();
						}
					});
	    }
	    
	    function inputLogin(){
	    	
	    	 $('#qrcode').hide();
	    	 
	    	 setTimeout(function() {
					$("#content").fadeIn("slow");
			     }, 300)
	    }
		
		$(document).ready(function(){
			
		    setTimeout(function() {
				$("#photo").fadeOut("fast");
		     }, 500);
			
		    $('#content').hide();
		    
		    setTimeout(function() {
				$("#content").fadeIn("slow");
		     }, 800);
		    
		    $('#qrcode').hide();
		    $('#fuckitall').popover();
			
			//一行代码搞定，提交表单的时候会自动触发验证程序
			$('#form1').Validform({
				tiptype:0,
				ajaxPost:true,
				beforeSubmit:function(curform){return false;}
			});
			
			//当前界面在模态层被打开则跳回原界面
			if(top.window.location.pathname!="<%=request.getContextPath()%>/pages/indexLogin.jsp")
			{
				top.window.location.pathname="<%=request.getContextPath()%>/pages/indexLogin.jsp";
			}
			
			$('#btn2').click(function(e){
				
				if ($('#empNum').val() == '') {
					layer.tips('请输入用户名！','#empNum',{tips: [2, '#5CA700']});
					return false;
				}
				if ($('#password').val() == '') {
					layer.tips('请输入密码！','#password',{tips: [2, '#5CA700']});
					return false;
				}
				
				$('#btn2').button('loading');		
				var url = '<%=request.getContextPath() %>/login/login.action';
				$.get(url,{empNum:$('#empNum').val(),password:$('#password').val()},function(data){
					if (data == 'y') {
						var urlindex = '<%=request.getContextPath() %>/pages/index.jsp';
						location.href = urlindex;
						$('#btn2').button('reset');
					}
					if(data == 'nw'){
						layer.tips('用户名不存在！','#empNum',{tips: [2, '#5CA700']});
						$('#btn2').button('reset');
					}
					if(data == 'pw'){
						layer.tips('密码错误！', '#password' ,{tips: [2, '#5CA700']});
						$('#btn2').button('reset');
					}
				});
			});
		});
		
		</script>
    </head>

    <body>
    	
    	<div class="container" id="photo" style="padding-top:15%;font-family:微软雅黑">
   			<img src="<%=request.getContextPath()%>/image/logo.png" width="300" height="300">
   		</div>
   		
        <div class="container" id="content" style="padding-top:15%;font-family:微软雅黑">
        	<div style="width:300px;margin:0 auto;">
	        <form id="form1"  method="post" action="<%=request.getContextPath() %>/login/login.action">
	            
            <div class="media">
                <a class="media-left">
                    <img src="<%=request.getContextPath()%>/image/default-transparent.png" width="130" height="40">
                </a>
                <div class="media-body">
                </div>
            </div>
	            
            <div class="form-group" style="margin-top:20px"> 
               	<input type="text" name="empNum" id="empNum" class="form-control" placeholder="请输入用户名">
       		</div>
		            
       		<div class="form-group">
             	<input type="password" name="password" id="password" class="form-control" placeholder="请输入密码 ">              
         	</div> 
         	
         	<div class="form-group">
            	<button type="submit" style="font-weight:bolder" id="btn2" data-loading-text="正在确认..." data-complete-text="重新登录" class="btn btn-primary btn-block">登录</button>
            </div>
            
            <div class="form-group">
            	<a id="fuckitall" class="pull-right" href="#" style="color:#fff" data-html="true" data-toggle="popover" data-content="<p style='color:black'>请联系管理员，tel：13800138000</p>" data-placement="bottom">
        					忘记密码
        				</a>
            	<a class="pull-right" href="#" style="color:#fff;margin-right:15px" onclick="qrcodeLogin()">二维码登录</a>
            	
            </div>
            
            </form>
	    	</div>
        </div>
        
        <div class="container" id="qrcode" style="padding-top:18%;font-family:微软雅黑">
       		<div  style="background-color:white;width:230;margin:0 auto;border-radius:5px">
       			
   				<img style="border-radius:5px" id="qrcodeimg" width="230" height="230">
   				<p style="color:black;font-size:1em">用云办公安卓版扫一扫登录</p>
   				<br>
   			</div>
   			<div class="form-group">
   			<br>
            		<a href="#" style="color:#fff" onclick="inputLogin()">返回普通登录</a>
            	</div>
   		</div>

		
        <!-- Javascript -->
        <script src="<%=request.getContextPath()%>/js/assets/js/supersized.3.2.7.min.js" ></script>
        <script src="<%=request.getContextPath()%>/js/assets/js/supersized-init.js" ></script>
        <script src="<%=request.getContextPath()%>/js/assets/js/scripts.js" ></script>

    </body>

</html>