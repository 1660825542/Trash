<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="list-group">
        	<a href="#" class="list-group-item active" style="font-size:15px;font-weight:bolder">会议管理模块</a>
        	<a href="<%=request.getContextPath() %>
						/room/query.action" class="list-group-item">会议室信息</a>
            <a href="<%=request.getContextPath() %>
						/meeting/query.action" class="list-group-item">会议预约情况</a>
            <a href="<%=request.getContextPath() %>
						/meeting/queryByEmpId.action" class="list-group-item ">我要参加的会议</a>
			<a href="<%=request.getContextPath() %>
						/meeting/queryByOriginator.action" class="list-group-item">我发布的会议
			</a>
						
        	</div>