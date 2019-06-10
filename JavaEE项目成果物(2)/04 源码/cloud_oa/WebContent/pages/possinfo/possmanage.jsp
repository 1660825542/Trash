<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="com.icss.oa.possession.pojo.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办公用品消耗</title>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/popwin.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/sb-admin.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/style-bar.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	
	<script type="text/javascript">
	function del(possId) {
		var possId=$("#possId").val();
		var flag = window.confirm("确定要删除此项吗");
		
		if (flag) {
			
			location.href=  "delete.action?possId="+possId+"&pageNum=${param.pageNum}";
			
		}
	}

	function exportE() {
		
		var flag = window.confirm("确定要导出报表吗");
		
		if (flag) {
			
		
			window.open("${pageContext.request.contextPath}/log/exportExcel.action");
			
		}
	}


			
	$(document).ready(function(e) {
		$('#form9').Validform({
			tiptype:3,
			ajaxPost:false,
			datatype:{'returnNum':function(gets,obj,curform,regxp){
				var reg1=/^\d+$/
				if(!reg1.test(gets)){
					return "请输入正确的归还数量";
				}
				if(parseInt(gets)>$("#possUse").val()){
					return false;
				}
				return true;
			},
			callback:function(data){
				
				$.Showmsg("提交成功");
				
				setTimeout(function(){
				
				},2000);				
			}
		}
			
		});
		
		$('#form4').Validform({
			tiptype:3,
			
			ajaxPost:false,
			
		});
		
		$('#form5').Validform({
			tiptype:3,
			
			ajaxPost:false,
			
		});
		
		
		
		$('#insertBtn').click(function(){		
		
	   window.location="../pages/possinfo/AddLog.jsp"
		});
		  if($('tr[name="addtr"]').length==0){
		    	$('tr[name="trHead"]').last().after('<tr name="addtr"><td>&nbsp</td> <td></td><td></td><td></td> <td></td></tr>');
		    }
			while ($('tr[name="addtr"]').length<8)
				
			{
				$('tr[name="addtr"]').last().after('<tr name="addtr"><td>&nbsp</td><td></td><td></td><td></td> <td></td></tr>');
				
			}
        
		//增加数据
		$('#insertBtn2').click(function(e) {
			
            $('#form1').attr('action','#');
			$('#form1').submit();
        });
		
		//导出Excel
		$('#exportBtn').click(function(e){
			$('#form1').attr('action','#');
			$('#form1').submit();			
		});

		
		
		
		//删除数据
		$('input[name=searchType]').click(function(){
			if(this.id == 'radioBtn1') {
				$('#form4').show();
				$('#form5').hide();
				$('#form6').hide();
				
			} else if(this.id == 'radioBtn2') {
				$('#form4').hide();
				$('#form5').show();
				$('#form6').hide();
				
			}	
			else{
				$('#form4').hide();
				$('#form6').show();
				$('#form5').hide();
				
			}
				
			});	
		
		//清空表单
		$('#clearBtn').click(function(e) {
            $('#possId').val('');
		    $('#possName').val('');
		    $('#possCate').val('');
			$('#possType').val('');
			$('#possPrice').val('');
			$('#possUnuse').val('');
			$('#possUse').val('');
		    $('input[name="ids"]').prop('checked',false);		
			$('#updateBtn').prop('disabled',true);
		    $('#deleteBtn').prop('disabled',true);
		    $('#clearBtn').prop('disabled',true);
        });
		
		//选择单选设置表单数据

		$('input[name="ids"]').click(function(e) {
			
	           var possId = $(this).parent().parent().find('td').eq(1).text();
			   var possName = $(this).parent().parent().find('td').eq(2).text();
			   var possCate = $(this).parent().parent().find('td').eq(3).text();
			   var possUse = $(this).parent().parent().find('td').eq(4).text();
			   
			   $('#possId').val($.trim(possId));
			   $('#possName').val($.trim(possName));
			   $('#possCate').val($.trim(possCate));
			   $('#possUse').val($.trim(possUse));
			   
				
			   
			   $('#updateBtn').prop('disabled',false);
			   $('#deleteBtn').prop('disabled',false);
			   $('#clearBtn').prop('disabled',false);
	        });

		
		
		$('#newcount').blur(function(){
			
			var qty = parseInt($('#oldcount').val())-parseInt($('#newcount').val());
			qty =  parseInt($('#loss').val())+(qty>0?qty:0);
			
			$('#loss').val(qty);
				
		});
		
		$('#form4').submit(function(){
			var reg = /^\d{0,6}$/;
			if ($('#possId1').val() == '') {
				layer.msg("请输入检索关键字",{icon: 2});
				return false;
			}
			if(!reg.test($('#possId1').val())){
				layer.msg("请输入0-6位数字",{icon: 2});
				return false;
			}
		});
		
		$('#form5').submit(function(){			
			if ($('#possName1').val() == '') {
				layer.msg("请输入检索关键字",{icon: 2});
				return false;
			}
			var reg = /^[\w\W]{0,10}$/
				if (!reg.test($('#possName1').val())) {
					layer.msg("输入个数不能大于10个",{icon: 2});
					return false;
				}			
		});
		
    });
</script>
</head>
<body>

<!-- 网页头部-->
<jsp:include page="/include/header.jsp"></jsp:include>
<!-- 网页头部完成-->

<div class="container">


<div class="col-md-3" id="myScrollSpy" style="margin-top:30px;">
	<dropdown class="col-xs-12" style="margin-bottom:30px;"> 
		<input id="toggle1" type="checkbox" checked>
		<label for="toggle1"class="animate">资产管理<i class="fa fa-bars float-right"></i></label>
		<ul class="animate">
			<a href="<%=request.getContextPath()%>/house/query.action">
				<li class="animate">库存管理</li>
			</a>
            <a href="<%=request.getContextPath()%>/poss/query.action">
            	<li class="animate actived">办公用品消耗</li>
            </a>	              
		</ul>
	</dropdown>
</div>
<!-- 左侧边栏内容完！ -->

<!-- 右侧内容-->
<div class="col-md-9" style="margin-top:30px">
	<div style="margin-bottom: 10px">
		<form>
			<input type="radio" id="radioBtn1" name="searchType" checked >
			编号搜索
			<input type="radio" id="radioBtn2" name="searchType" >
			名称搜索
			<input type="radio" id="radioBtn3" name="searchType" >类别搜索</form>
	</div>

	<div>
		<div style="margin-bottom: 10px">
			<form name="form4" id="form4"  action="<%=request.getContextPath()%>
				/poss/queryByConditionId.action" 
					method="get" style="display:block" >
			<div class="input-group">
				<input type="text" name="possId" id="possId1" class="form-control" placeholder="请输入资产编号" value="${house.possId }">
				<span class="input-group-btn">
					<input type="submit" class="btn btn-primary" value="编号搜索"></span>
			</div>
			</form>

			<form name="form5" id="form5"  action="<%=request.getContextPath()%>
				/poss/queryByCondition.action" 
					method="get" style="display:none">			
				<div class="input-group">
				<input type="text" name="possName" id="possName1" errormsg="请输入正确的名称" class="form-control" placeholder="请输入资产名称" value="${house.possName }">
				<span class="input-group-btn">
					<input type="submit" class="btn btn-primary" value="名称搜索"></span>
				</div>

			</form>

			<form name="form6" id="form6"  action="<%=request.getContextPath() %>
				/house/queryByConditionCate.action" 
					method="get" style="display:none">
				<div class="input-group">
					<select  name="possCate" id="possCate1" class="form-control " placeholder="请选择库存类别" value="${house.possCate }">
						<option value="电器类" >电器类</option>
						<option value="文具类" >文具类</option>
						<option value="家具类" >家具类</option>
					</select>
					<span class="input-group-btn">
						<input type="submit" class="btn btn-primary" value="类别查询">
					</span>
				</div>
			</form>
		</div>
	</div>

	<table class= "table table-striped table-bordered table-condensed table-hover">
		<thead>
			<tr name="trHead">
				<th>选择</th>
				<th>编号</th>
				<th>办公用品名称</th>
				<th>类别</th>
				<th>已用数量</th>
			</tr>
		</thead>

		<c:forEach items="${requestScope.list }" var="poss">
			<c:if test="${poss.possUse>0}">
				<tr name="addtr">
					<td>
						<input name="ids" type="radio" ></td>
					<td height="24" >${poss.possId }</td>
					<td height="24">
						<c:out value="${poss.possName }"/>
					</td>
					<td height="24">
						<c:out value="${poss.possCate }"/>
					</td>
					<td height="24">
						<c:out value="${poss.possUse }"/>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<jsp:include page="/pages/possinfo/pagers.jsp"></jsp:include>
</div>

<div class="col-md-9 col-md-offset-3">
	<form id="form9" name="form9" method="post" action="../poss/update.action?pageNum=${param.pageNum}">
		<table class="table table-bordered table-striped">
			<tbody>
				<tr>
					<td>编号</td>
					<td>
						<input name="possId" id="possId" readonly type="text" class="form-control"></td>
					<td>办公用品名称</td>
					<td>
						<input name="possName" id="possName" readonly type="text" class="form-control"></td>
				</tr>
				<tr>
					<td>类别</td>
					<td>
						<input name="possCate" id="possCate" readonly type="text" class="form-control"></td>
					<td>消耗数量</td>
					<td>
						<input name=" possUse" id="possUse"  readonly type="text" class="form-control"></td>

				</tr>
				<tr>
					<td>归还数量</td>
					<td>
						<input name="possBack" datatype="returnNum" errormsg="请输入正确的归还数量" id="possBack" type="text" class="form-control"></td>

				</tr>

				<tr>
					<td colspan="5" style="text-align: center">
						<input name="button" id="insertBtn" value="增加记录"  class="btn btn-primary" type="button">

						<input name="button2" id="updateBtn" value="修改" class="btn btn-primary" disabled="disabled" type="submit">
						<input name="button3" id="deleteBtn" value="删除" class="btn btn-primary" disabled="disabled" onclick="del()"
                        type="button" >
						<input name="button4" id="clearBtn" value="清空" class="btn btn-primary" disabled="disabled" type="button">
						<input name="button5" id="exportBtn" value="导出报表" class="btn btn-success" type="button" onclick="exportE()"></td>
				</tr>
			</tbody>

			</table>
		</form>
	</div>
</div>

<!--网页底部-->

<jsp:include page="/include/footer.jsp"></jsp:include>

<!--网页底部结束-->

</body>
</html>