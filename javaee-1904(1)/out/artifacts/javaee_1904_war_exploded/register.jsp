<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/20
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fieldset>
    <legend>2、欢迎注册</legend>
    <!--method:get|post   &#45;&#45;   默认是get方式-->
    <form action="RegisterAction" id="registerForm">
        <div>
            登录ＩＤ:<input type="text" name="userid" id="userid"/>
            <span id="useridInfo"></span>
            ${error}
        </div>
        <div>
            登录密码:<input type="password" name="password" id="password"/>
        </div>
        <div>
            确认密码:<input type="password" name="repwd" id="repassword"/>
        </div>
        <div>
            用户性别:
            <input type="radio" name="sex" value="女" checked="checked"/>女
            <input type="radio" name="sex" value="男"/>男
        </div>
        <div>
            个人爱好:
            <input type="checkbox" name="hobby" value="体育" checked="checked"/>体育
            <input type="checkbox" name="hobby" value="音乐"/>音乐
            <input type="checkbox" name="hobby" value="旅游"/>旅游
            <input type="checkbox" name="hobby" value="看书" checked="checked"/>看书
        </div>
        <div>
            <fieldset>
                <legend>密保设置</legend>
                密保问题：
                <select name="qid">
                    <option value="1">你最喜欢的老师是谁</option>
                    <option value="2">你的女神是谁</option>
                    <option value="3">你的男神是谁</option>
                    <option value="4">你最喜欢的图示是</option>
                </select>
                <br/>
                密保答案：
                <input type="text" name="answer" >
            </fieldset>
        </div>
        <div>
            用户学历:
            <!--下拉列表框：select，option是选项，option有一个selected属性，设置默认选中项-->
            <select name="degree">
                <option value="博士">博士</option>
                <option value="硕士">硕士</option>
                <option value="xueshi" selected="selected">学士</option>
                <option value="other">其他</option>
            </select>
        </div>

        <div>
            个人备注：
            <!--文本区-->
            <textarea cols="60" rows="5" name="dis"></textarea>
        </div>
        <div>
            <input type="submit" value="【 注 册 】"/>
            <input type="reset" value="【 重 置 】"/>
        </div>

    </form>
</fieldset>
</body>
</html>
