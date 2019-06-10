<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.meeting.pojo.*" %>
<%@ page import="com.icss.oa.system.pojo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html>
<head>
	<meta charset="utf-8">


	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/Validform/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-bar.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popwin.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>

	<script type="text/javascript">
	$(document).ready(function(e) {
		
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#form2').Validform({
			tiptype:3,
			datatype:{'end':function(gets,obj,curform,regxp){
				var reg1=/[\w\W]+/;
				if(!reg1.test(gets)){
					return "请选择会议结束时间";
				}
				if(gets<$("#meetDateBegin").val()||gets==$("#meetDateBegin").val()){
					return false;
				}
			}	
			}
	
			
		});
		
		//选择会议室模态窗口
		$('#roomBtn').click(function(){			
			popWin.showWin("500","600","选择会议室","<%=request.getContextPath()%>/room/querySmall.action");
			
		});
		$('#roomBtn1').click(function(){
			if($('#roomId').val()==''){
				alert("请选择会议室");
			}
			else{	
			popWin.showWin("500","600","会议室预约情况",'<%=request.getContextPath()%>/meeting/queryByRoomId.action?roomId=' + $("#roomId").val());	
			}
		});
		//选择员工模态窗口
		$('#empBtn').click(function(){			
			popWin.showWin("500","600","选择参会人","<%=request.getContextPath()%>/meeting/queryByDept.action");				
		});
		//日历框
		$('#meetDateBegin').datetimepicker({		
			lang:'ch',	
			step:10,
			format:'Y-m-d H:i:00',
			minDate:0
			
		});	
		$('#meetDateEnd').datetimepicker({		
			lang:'ch',	
			step:10,
			format:'Y-m-d H:i:00',
			minDate:0
		});	
		
		//ajax异步查询会议室预约情况
		$("#theme").focus(function(){		
			$.post("<%=request.getContextPath()%>/meeting/queryResult.action",
					{roomId:$("#roomId").val(),meetDateBegin:$("#meetDateBegin").val(), meetDateEnd:$("#meetDateEnd").val()},
					function(data){
						if (data==1)
							alert("会议室被占用");
					}
			);
			if($("#parentIds").text()==''){
				alert("请选择参会员工");
			}
		});
	});
</script>
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
				<label for="toggle1"class="animate">会议管理<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath() %>/room/query.action">
						<li class="animate">会议室信息<i class="fa fa-code float-right"></i></li>
					</a>
					<a href="<%=request.getContextPath() %>/meeting/query.action">
						<li class="animate actived">会议预约情况<i class="fa fa-code float-right"></i></li>
					</a>
	                <a href="<%=request.getContextPath() %>/meeting/queryByEmpId.action">
	                	<li class="animate">我要参加的会议<i class="fa fa-code float-right"></i></li>
	                </a>
	                <a href="<%=request.getContextPath() %>/meeting/queryByOriginator.action">
	                	<li class="animate">我发布的会议<i class="fa fa-code float-right"></i></li>
	                </a>
				</ul>
			</dropdown>
		</div>           
         <!-- 侧边栏完成 -->
         		<div class="room1 col-md-7" >
					<h3 class="page-header">
						请填写预约会议相关信息
					</h3>		
					<form name="form2" id="form2" method="post" action="<%=request.getContextPath()%>/meeting/insert.action">
							<table width="100%" border="0" class="table table-striped">
				                  <tr>
				                    <td width="24%">会议发起人</td>
				                    <td width="76%">
				                    <input type="hidden" name="originator" value="<%=((Employee)request.getSession().getAttribute("queryemp")).getEmpId()%>">
				                    <input style="background-color:#EEEEEE" type="text" value="<%=((Employee)request.getSession().getAttribute("queryemp")).getEmpName()%>" id="originator" readonly ></td>
				                  </tr>                
				                  <tr>
				                    <td>会议开始时间</td>
				                    <td><input type="text" datatype="*" errormsg="请选择会议开始时间"name="meetDateBegin" id="meetDateBegin"></td>
				                  </tr>
				                  <tr>
				                    <td>会议结束时间</td>
				                    <td><input type="text" datatype="end" errormsg="请选择正确会议结束时间"name="meetDateEnd" id="meetDateEnd"></td>
				                  </tr>
				                  <tr>
				                    <td>会议室</td>
				                    <td><input type="text" datatype="*" errormsg="请选择会议室"name="roomName" id="roomName"readonly>
				                      <input type="hidden" name="roomId" id="roomId">
				                      &nbsp;&nbsp;&nbsp;&nbsp;
				                      <input type="button" name="button" id="roomBtn" value="选择会议室" class="btn btn-primary" >
				                      &nbsp;&nbsp;&nbsp;<input type ="button" name ="button" id="roomBtn1" value="查询该会议室预约情况" class="btn btn-primary">
				                      </td>
				                  </tr>        
				                  <tr>
				                  	<td>参会人</td>
				                  	<td>				                  						                  		
					                  	<input type="button"  name="button" id="empBtn" value="选择参加会议员工" class="btn btn-primary">
					                  	<span id="parentIds"></span>
					                </td>
				                  </tr>   
				                  <tr>
				                    <td>会议主题</td>
				                    <td><textarea name="theme" rows="7" datatype="*" errormsg="请填写会议主题" id="theme" style="width:100%;"></textarea></td>
				                   
				                  </tr>

		               		 </table>
		               		 <input type="hidden" name="meetState" value="0">
							<input type="submit" value="点击预约会议" class="btn btn-primary btn-large">
					</form>
					
	    	</div>
	    	

	
       		<!-- 响应式布局，移动端才会有返回主页的按钮 -->
       		<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
       		<!-- / --> 	    	

		 <!--右侧内容完 -->
	</div>

	<!--网页底部-->
		<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
</body>

</html>