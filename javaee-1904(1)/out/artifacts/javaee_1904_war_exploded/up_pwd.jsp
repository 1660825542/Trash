<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/21
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        span{
            color: red;
        }
    </style>
    <script type="text/javascript" src="comm/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('input[type="button"]').click(function () {
                var password=$('input[name="password"]').val();
                var newpwd=$('#newpwd').val();
                location.href='UpdatePwdAction?password='+password+"&newpwd="+newpwd;
            });
        });
    </script>
</head>
<body>
    <fieldset>
        <legend>修改密码</legend>
        <div>
            请输入原密码：<input type="text" name="password">
            <span>${error}</span>
        </div>
        <div>
            请输入新密码：<input type="text" name="newpwd" id="newpwd">
        </div>
        <div>
            <input type="button" value="【确认修改】">
        </div>
    </fieldset>
</body>
</html>
