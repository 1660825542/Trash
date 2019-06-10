<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style1.css" >
</head>
<body>
<fieldset>
	<legend>用户登录</legend>
    <div id="box1">
        <form action="${pageContext.request.contextPath}/User_Servlet" method="post">
            <table id="table">
                <tr>
                    <th> 请输入管理员账号：</th>
                    <td>
                    	<input type="hidden" name="method" value="suplogin">
                        <input type="text" name="rootId" autocomplete="off">
                    </td>
                    <td rowspan="2"> ${error} </td>
                </tr>
                
                <tr>
                    <th> 请输入管理员密码：</th>
                    <td>
                        <input type="password" name="password" autocomplete="off">
                    </td>
                    
                </tr>
                <tr>
                <td><button id="color1">这是一个登录按钮</button></td>
                <td id="style">
                    <input type="reset" id="color2"name="复位按钮" value="复位按钮">
                </td>
                </tr>
              </table>
         </form>
     </div>
</fieldset>   
</body>
</html>