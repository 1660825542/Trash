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
	/*删除确认*/
	function del(cataId) {		
		var flag = window.confirm("你确定要删除此项数据吗?");
		
		if (flag) {
			location.href = '<%=request.getContextPath()%>/CardCategory/delete.action?cataId=' + cataId;
		}		
	}
    </script>

<title>名片夹分类</title>



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
        <h3 class="page-header">
				名片夹分类列表
		</h3>	
		<div class="col-md-9" >
							        
		    	<table  class="table table-striped table-bordered table-condensed table-hover">
		    		<thead>
					<tr>
						<th>
							名片夹分类编号
						</th>
						<th>
							名片夹分类名称
						</th>
						<th>
							操作
						</th>
					</tr>	
					</thead>	
					<tbody>		
						<!-- 遍历输出数据 -->
						<c:forEach items="${list}" var="card_category">									
		                <tr>
		                  <td>${card_category.cataId}</td>
		                  <td>${card_category.cataName}</td>
		                  <td>
		                  	<div class="btn-group btn-xs">
				            	<button type="button" class="btn btn-default  btn-xs dropdown-toggle" id="dropdownMenu" data-toggle="dropdown">
				                   	 操作
				                    <span class="caret"></span>
				                </button>
				                <ul class="dropdown-menu">
				                        <li><a href="<%=request.getContextPath()%>/Card/queryCatecard.action?cataId=${card_category.cataId}">查看详细</a></li>				                	    
				                    	<c:if test="${card_category.canDelete==1}">
				                        <li><a href="<%=request.getContextPath()%>/CardCategory/toUpdate.action?cataId=${card_category.cataId}">修改</a></li>				                   
				                   		<li><a href="javascript:del(${card_category.cataId});">删除</a></li>
				                   		</c:if>	                    
				                </ul>
				            </div>                  			                  
		                  </td>
		                </tr>		                
		                </c:forEach>		                                
		             </tbody>
		    	</table>
		    	
		    		    	
		    	<!-- 包含分页 -->
		    	<jsp:include page="/include/pager.jsp"/>   
		    	
		    	
		    	<a href="<%=request.getContextPath()%>/pages/Card/AddCardCategory.jsp" class="btn btn-primary btn-block">增加新名片夹</a>     
		    	
		     </div>  
		 <!--右侧内容完 -->
	
</div>
	
</body>
   <!--网页底部-->
		<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->

</html>