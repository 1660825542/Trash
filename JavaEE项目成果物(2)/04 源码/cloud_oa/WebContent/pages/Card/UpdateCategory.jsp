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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
    <script type="text/javascript">
	$(document).ready(function(e) {
	    
		//一行代码搞定，提交表单的时候会自动触发验证程序
		$('#form1').Validform({
			tiptype:3	
		});
		
	});
    </script>
    <title>名片夹修改</title>
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
						<li class="animate">联系人查询</li>
					</a>
					<a href="<%=request.getContextPath()%>/CardCategory/query1.action">
						<li class="animate actived">名片夹分类</li>
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
              修改名片夹       
        </h3>
        <form name="form1" id="form1" method="post" action="<%=request.getContextPath() %>/CardCategory/update.action">
                <table class="table table-striped" border="0" width="100%">
                  <tbody><tr>
                    <td>名片夹名称</td>
                    <td><input name="cataName" id="cataName" datatype="*" errormsg="不能为空！" type="text"><span class="Validform_checktip"></span></td>
                    <input type="hidden" name="cataId" value="${cardcategory.cataId}">
                  </tr>
                </tbody></table>
                <input value="保存分类" class="btn btn-primary btn-large" type="submit">
         </form>	    		    			    	
	 </div>  
	 <!--右侧内容完 -->
	
</div>	
</body>
<!--网页底部-->
<jsp:include page="/include/footer.jsp"></jsp:include>
<!--网页底部结束-->

</html>