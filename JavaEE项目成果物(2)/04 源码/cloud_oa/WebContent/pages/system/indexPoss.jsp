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
	function del(posId) {
		layer.load();
		var posName = $('#'+posId).parent().parent().parent().find('td').eq(1).text();
		$.get('<%=request.getContextPath()%>/pos/queryAjaxHaveChild.action?posId='
			+ posId,function(data,e){
			layer.closeAll('loading');
			if(data==0){
				
				layer.confirm('你确定要删除职务"'+posName+'"吗?', {
					btn: ['确定','取消'], //按钮
					icon: 3,
					shade:false
				}, function(){
					layer.load();
					$.get( '<%=request.getContextPath()%>/pos/delete.action?pageNum=${pageNum}&posId=' + posId);
					$('#'+posId).parent().parent().parent().remove();
					layer.closeAll('loading');
					layer.msg('删除成功', {icon: 1});
				});
			}
			
			if(data==1){
				layer.msg(posName+'有下属员工，不可删除！', {icon: 0, shift:6});
			}
			
		});
	}


	function update(posId){
		layer.load();
		$('#posNameDiv').removeClass("has-success has-feedback");
		$('#posNameDiv').removeClass("has-error has-feedback");
		$.get('<%=request.getContextPath()%>/pos/queryAjaxHaveChild.action?posId='
			+ posId,function(data,e){
		
			layer.closeAll('loading');
			$('#posInfo').modal({
					backdrop:'static', /*点击黑色区域不关闭*/
					keyboard:false     /*ESC键不关闭*/
				});
			
			$('#addBtn').hide();
			$('#setBtn').show();
			$('#posInfo h4').text("修改职务信息");
			$('#posId').val($('#'+posId).parent().parent().parent().find("td").eq(0).text());
			$('#posName').val($('#'+posId).parent().parent().parent().find("td").eq(1).text());
			$('#posInformation').val($('#'+posId).parent().parent().parent().find("td").eq(2).text());
		
			
			$('#closeBtn').click(function(){
				$('#posId').val('');
				$('#posName').val('');
				$('#posInformation').val('');
			});
			
			$('#posSet').click(function(){
				layer.load();
				if($('#posName').val()==null || $('#posName').val()=='')
				{
					layer.closeAll('loading');
					$('#posNameDiv').addClass("form-group has-error has-feedback");
				}
				else if($('#posName').val()!=null){
					$('#posNameDiv').addClass("form-group has-success has-feedback");
					$.post('<%=request.getContextPath()%>/pos/update.action',{posId:$('#posId').val(),posName:$('#posName').val(),posInfo:$('#posInformation').val()});
					var ID = $('#posId').val();
					$('#'+ID).parent().parent().parent().find("td").eq(1).text($('#posName').val());
					$('#'+ID).parent().parent().parent().find("td").eq(2).text($('#posInformation').val());
					$('#closeBtn').click();
					layer.closeAll('loading');
					layer.msg('修改成功', {icon: 1});
					
				}
				
			});
			
		});
	}
	/*分页显示*/
	var pageNum =${pager.pageNum};//第几页
	var recordCount = ${queryResult.recordCount};//总记录数
	var pageSize = ${pager.pageSize };//每页多少条
	var pageCount = parseInt((recordCount - 1) / pageSize) + 1;//共几页
	
	$(document).ready(function(){
		
		$('#index').submit(function(){			
			if ($('#keyword').val().replace(/\s/g, "") == '') {
				layer.msg("请输入检索关键字",{icon: 2});
				return false;
			}			
		});
		
		$('#indexBtn').click(function(){	
			
			$('#pageNum').val(1);
			$('#index').submit();				
		});
			
		$('#prevBtn').click(function(){	
			if (pageNum <= 1 ) {
				layer.msg("已经是第一页了",{icon: 0, shift:6});
				return false;
			}
			
			$('#pageNum').val(pageNum - 1);
			$('#index').submit();				
		});
		
		$('#nextBtn').click(function(){
			
			if (pageNum >= pageCount) {
				layer.msg("已经是最后一页了",{icon: 0, shift:6});
				return false;
			}
			
			$('#pageNum').val(pageNum + 1);						
			$('#index').submit();
		});
		
	});
</script>
<title>职务检索</title>
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
						<li class="animate actived">职务管理<i class="fa fa-code float-right"></i></li>
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
					职务检索结果
				</h3>	        
				
				<form id="index" action="<%=request.getContextPath() %>/pos/queryByIndex.action" method="post">
					<div class="input-group">                	
	                	<input type="text" value="${keyword }" id="keyword" name="keyword" placeholder="请输入关键字" class="form-control">
	                    <span class="input-group-btn">
	                    	<input type="button" class="btn btn-primary" id="indexBtn" value="检索职务">
	                    </span>
	                </div>
          	        <input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum }">
                </form>
                
                <br />
                <c:if test="${queryResult.list.size()>0 }">
                <table class="table table-striped table-bordered table-condensed table-hover">
                    <thead>
                    <tr>
                        <th width="80">
                        	职务编号
                        </th>
                        <th width="100">
                        	职务名称
                        </th>
                        <th>
                         	 职务描述
                        </th>					
                        <th width="88">
                        	操作
                        </th>
                    </tr>	
                    </thead>	
                    <tbody>	
                    <c:forEach items="${queryResult.list }" var="pos">									
		                <tr>
		                  	<td>${pos.posId }</td>
		                  	<td>${pos.posName }</td>
		                  	<td>${pos.posInfo }</td>
		                  	<td>
		                  		<a href="javascript:update(${pos.posId });">
		                  			<input class="btn btn-default btn-xs" type="button" value="修改" >
		                  		</a>
		                  		<c:if test="${pos.posId!=0 }">
		                  		<a href="javascript:del(${pos.posId });">
		                  			<input id="${pos.posId }" class="btn btn-default btn-xs" type="button" value="删除">
		                  		</a>
		                  		</c:if>
		                  	</td>
		                </tr>		                
		                </c:forEach> 
                     </tbody>
                </table>
                <!-- 页码 -->
		    	<nav class="text-center">
		    		<ul class="pagination">
		    			<li><a id="prevBtn" href="#">上页</a></li>
		    			<li><a>共检索出${queryResult.recordCount }记录，每页${pager.pageSize }条，当前是第${pager.pageNum }页</a></li>
		    			<li><a id="nextBtn" href="#">下页</a></li>
		    		</ul>
		    	</nav>	
		    	</c:if>
		    	<c:if test="${queryResult.list.size()<=0 }">
		    		<div class="alert alert-info" role="alert">
		        		没有检索到职务！
		        	</div>
		    	</c:if>
			    <div class="col-sm-offset-9">
			    	<a href="<%=request.getContextPath()%>/pos/query.action">
		    			<input type="button" class="btn btn-success col-sm-4 btn-block" value="返回职务管理" >
		    		</a>
	       			<!-- 响应式布局，移动端才会有返回主页的按钮 -->
	       			<input type="button" class="btn btn-success btn-block visible-xs" value="返回主页" onclick="javascript:document.location.href='<%=request.getContextPath() %>/pages/index.jsp'">
	       			<!-- / --> 			    		
	       		</div>
       		
       					   		    		    	  
		     </div>  
		 <!--右侧内容完 -->
	</div>

	<!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
	
	<!--模态层-->
    <div class="modal fade" id="posInfo" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog">
        	<div class="modal-content">
            	<div class="modal-header">
                	<button id="closeBtn" type="button" class="close" data-dismiss="modal">&times;</button>
                	<h4 class="text-center">修改职务信息</h4>
                </div>
            	<div class="modal-body">
                	<div id="posForm" class="form-horizontal">
			        	<div class="form-group"> 
			                <div class="col-sm-6">
			                	<input type="hidden" datatype="*" name="posId" id="posId" class="form-control">               
			                </div>
			            </div>
			            
			            <div id="posNameDiv" class="form-group">
			                <label class="col-sm-4 control-label">职务名称</label>
			                <div class="col-sm-6">
			                	<input datatype="*" type="text" name="posName" id="posName" class="form-control" placeholder="请输入职务名称 ">              
			                </div>
			            </div>
			            
			            <div class="form-group">
			                <label class="col-sm-4 control-label">职务描述</label>
			                <div class="col-sm-6">
			                	<textarea ignore="ignore" type="text" name="posInfo" id="posInformation" class="form-control" placeholder="请输入职务描述 " ></textarea>              
			                </div>
			            </div>

			            <div id="setBtn" class="form-group">
			            	<div class="col-sm-2 col-sm-offset-8">
			            		<button id="posSet" class="btn btn-primary btn-block">修改</button> 		            		
			                </div>
			            </div>
		        	</div>  
                </div>
            </div>
        </div> 
    </div>
	
</body>

</html>