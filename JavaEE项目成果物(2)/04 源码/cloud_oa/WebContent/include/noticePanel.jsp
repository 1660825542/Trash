<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:forEach items="${list}" var="list">
        	<div class="panel panel-success isTop${list.isTop}">
      		<div class="panel-heading">
          		${list.noticeTitle }
			<a href="#two${list.noticeId }" data-toggle="collapse" class="panel-title pull-right">展开/折叠</a>
          	</div>
          	<div>
           	<div id="two${list.noticeId }" class="panel-collapse collapse in">
        		<div class="panel-body">
        			<fmt:formatDate value="${list.time}" pattern="yyyy-MM-dd"/>
        			<br>
        			
        			${list.content }
        			
	        		<!-- 通过shiro拦截 只有管理员才可以看到-->
        			<hr>
        			<div class="pull-right">	        			
	        			<a class="btn btn-default" href="javascript:void(0)" onclick="update(${list.noticeId});">修改</a>
	        			<a class="btn btn-default" href="javascript:void(0)" onclick="delNotice(${list.noticeId },this);">删除</a>
        			</div>
        			<!-- 通过shiro拦截 修改操作完成-->

        		</div>
       		</div>
       	</div>	
      	</div>  
</c:forEach>
