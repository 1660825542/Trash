<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<script type="text/javascript">
	$(document).ready(function(e) {	
		var photo=$("#photo").text();
	//	$("#view").css("background-image","url(data:image/jpeg;base64,"+photo+")");
		$("#viewnotbg").find("img").attr("src","data:image/jpeg;base64,"+photo);
	  
	  });
	</script>

	<input type="hidden" value="${car.carId}"/>
	<div id="photo" style="display:none">${photo}</div>
<!--	<div id="view" style="width:1920px;height:1080px"></div>	  -->
	<div id="viewnotbg">
		<img src="" alt="这张图片崩溃了0.0" class="img-rounded img-responsive">		
	</div>
