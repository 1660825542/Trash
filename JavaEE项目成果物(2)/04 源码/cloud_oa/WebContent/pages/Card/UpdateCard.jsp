<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.card.pojo.*" %>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimepicker/jquery.datetimepicker.js"></script>
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
   <script type="text/javascript">
	$(document).ready(function(){
		
		
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#addForm').Validform({
			tiptype:3
		});
		
		$('#chooseDept').on('click', function(){
			layer.open({
				type: 2,
				title: '选择部门',
				maxmin: false,
				closeBtn:2,
				shadeClose: false, //点击遮罩关闭层
				area : ['700px' , '500px'],
				shift: 5,
				content: '<%=request.getContextPath()%>/dept/querySelectDept.action'
			});
		});	
		
		$('#choosePos').on('click', function(){
			layer.open({
				type: 2,
				title: '选择职务',
				maxmin: false,
				closeBtn:2,
				shadeClose: false, //点击遮罩关闭层
				area : ['700px' , '500px'],
				shift: 5,
				content: '<%=request.getContextPath()%>/pos/querySelectPos.action'
			});
		});	
		
		$('#chooseCate').on('click', function(){
			layer.open({
				type: 2,
				title: '选择类别',
				maxmin: false,
				closeBtn:2,
				shadeClose: false, //点击遮罩关闭层
				area : ['700px' , '500px'],
				shift: 5,
				content: '<%=request.getContextPath()%>/CardCategory/querySelectCate.action'
			});
		});	
		
	});
</script>
<title>名片修改</title>
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
				<label for="toggle1"class="animate">名片夹系统<i class="fa fa-bars float-right"></i></label>
				<ul class="animate">
					<a href="<%=request.getContextPath()%>/Card/query.action">
						<li class="animate actived">联系人查询</li>
					</a>
					<a href="<%=request.getContextPath()%>/CardCategory/query1.action">
						<li class="animate">名片夹分类</li>
					</a>
	                <a href="<%=request.getContextPath()%>/Card/queryAll.action">
	                	<li class="animate">公司通讯录</li>
	                </a>
				</ul>
			</dropdown>
		</div>     
         <!-- 侧边栏完成 -->
    	
		 <div class="col-md-7">
        <h3 class="page-header">
                    修改名片信息
            </h3>
        <form id="addForm" name="form1" method="post" action="<%=request.getContextPath()%>/Card/update.action">
				<table class="table table-striped table-condensed">
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">姓名<span style="color:red">*</span></label>
			       				<div class="container col-md-4">
			       					<input class="form-control col-xs-4" name="cardName" id="cardName" value="${card.cardName }" placeholder="请输入姓名" nullmsg="请输入联系人姓名！" type="text">
			       				    <input name="cardId" id="cardId" value=${card.cardId } type="hidden">
			       				</div>
			   				</div>
		   				</td>
		   			</tr>
	   			
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">电话<span style="color:red">*</span></label>
			       				<div class="col-md-4">
			                    	<input class="form-control" name="cardPhone" id="cardPhone" value="${card.cardPhone }" placeholder="请输入电话"  errormsg="电话必须为数字！" nullmsg="请输入电话！" type="text">
			       				</div>
			   				</div>
		   				</td>
		   			</tr>   				   				
	   					<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">职务<span style="color:red">*</span></label>
			       				<div class="col-md-4">
									<input class="form-control" name="cardPosition" id="posName" value="${card.cardPosition }" placeholder="点击选择职务"  nullmsg="请选择职务！" type="text" readonly>
			                        
			                    </div>
			                    <div class="col-md-2">
			                       	<input id="choosePos" value="选择职务" class="btn btn-primary" type="button">
			                    </div>
			   				</div>
		   				</td>
		   			</tr>
	   				<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">所属部门<span style="color:red">*</span></label>
			       				<div class="col-md-4">
			 						<input class="form-control" name="cardDept" id="deptName" value="${card.cardDept }" placeholder="点击选择部门"  nullmsg="请选择部门！" type="text" readonly>
			                        
			                    </div>
			                    <div class="col-md-2">
			                    	<input id="chooseDept" value="选择部门" class="btn btn-primary" type="button">
			       				</div>
			   				</div>
		   				</td>
		   			</tr>
		   			  			<tr>
		   				<td>
			   				<div class="form-group"> 
			       				<label class="col-md-offset-2 col-xs-2 control-label float-left">名片类别<span style="color:red">*</span></label>
			       				<div class="col-md-4">
			 						<input class="form-control" name="cataName" id="cataName" value="${card.cataId }" placeholder="点击选择名片类别"  nullmsg="请选择类别！" type="text" readonly>
			                        <input name="cataId" id="cataId" type="hidden">
			                    </div>
			                    <div class="col-md-2">
			                    	<input id="chooseCate" value="选择类别" class="btn btn-primary" type="button">
			       				</div>
			   				</div>
		   				</td>
		   			</tr>
				</table>
                    
                <div >
                    <input type="submit" class="btn btn-primary btn-block" value="修改" >
                    <input type="button" class="btn btn-success btn-block" value="返回" onclick="history.back();">
                <!-- 响应式布局，移动端才会有返回主页的按钮 -->
                    <input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
                <!-- / --> 			    		
            	</div>
         	</form> 
		    	
	</div>  
		 <!--右侧内容完 -->
	
</div>
	
</body>
   <!--网页底部-->
		<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->

</html>