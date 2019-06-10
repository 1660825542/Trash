<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style1.css" >
</head>
<body>
<fieldset>
	<legend>查看信息</legend>
    <div id="box1">
        <form action="${pageContext.request.contextPath}/User_Servlet" method="get" target="_blank">
            <table>
            	<tr>
            	<td>
            		<input type="hidden" name="method" value="login1">
            	</td>
                <td><button style="width:400px; height:40px; background-color:#9AFF9A; border-radius:15px" id="color1">查看招聘职位最多的公司（前10名）</button></td>
                </tr>
            </table>
         </form>
         <form action="${pageContext.request.contextPath}/User_Servlet" method="get" target="_blank">
         	<table>
            	<tr>
            		<td>
            			<input type="hidden" name="method" value="login2">
            		</td>
            		<td id="style">
                    <button style="width:400px; height:40px; background-color:#97FFFF; border-radius:15px" id="color2">查看数量最多的招聘岗位（前5名）</button>
                	</td>
                </tr>
         	</table>
         </form>
         <form action="${pageContext.request.contextPath}/User_Servlet" method="get" target="_blank">
         	<table>
            	<tr>
            		<td>
            			<input type="hidden" name="method" value="login3">
            		</td>
            		<td id="style">
                    	<button style="width:400px; height:40px; background-color:#EE82EE; border-radius:15px" id="color3">查看工资最高的职位及工资（前3名）</button>
                	</td>
                </tr>
         	</table>
         </form>
     </div>
</fieldset>  
</body>
</html>