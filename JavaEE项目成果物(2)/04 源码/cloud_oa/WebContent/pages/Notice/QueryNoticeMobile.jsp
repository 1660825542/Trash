<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
</head>

<body>
	<div id="wholeNotice">
			<div class="panel-heading" style="text-align:center">
				${notice.noticeTitle }
			</div>
			<div>
				<div class="panel-collapse collapse in">
					<div class="panel-body">
						<fmt:formatDate value="${notice.time}" pattern="yyyy-MM-dd" />
						<br>${notice.content }
					</div>
				</div>
		</div>
	</div>
</body>

</html>