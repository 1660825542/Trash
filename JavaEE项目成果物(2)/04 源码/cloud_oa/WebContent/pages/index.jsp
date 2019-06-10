<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" charset="width=device-width, initial-scale=1" user-scalable="no";>
	<!--手机设备显示-->
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	
	
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
	
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <meta name="description" content="Original Hover Effects with CSS3" />
    <meta name="keywords" content="css3, transitions, thumbnail, animation, hover, effect, description, caption" />
    <meta name="author" content="Alessio Atzeni for Codrops" />
    
    <link rel="shortcut icon" href="../favicon.ico"> 
    <link rel="stylesheet" type="text/css" href="../css/OriginalHoverEffects/css/style_common.css" />
    <link rel="stylesheet" type="text/css" href="../css/OriginalHoverEffects/css/style1.css" />
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" rel="stylesheet" type="text/css">
    
	<title>中软云OA</title>
</head>

<body>
	<jsp:include page="/include/header.jsp"></jsp:include>

        <div class="container">
			
			<div style="margin-left: 30px">
                <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/person.jpg"/>
                    <div class="mask">
                        <h2>个人中心</h2>
                        <p>员工信息检索维护，以及个人相关申请管理中心。</p>
                        <a href="<%=request.getContextPath()%>/empInfo/query.action" class="info">点击进入</a>
                    </div>
                </div>  
                <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/info.jpg"/>
                    <div class="mask">
                        <h2>在线信息</h2>
                        <p>员工之间在线收发消息的平台。</p>
                        <a href="<%=request.getContextPath()%>/message/toMyMessage.action" class="info">点击进入</a>
                    </div>
                </div>  
                <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/cfolder.jpg" />
                    <div class="mask">
                        <h2>云文件夹</h2>
                        <p>中软云OA提供大量的云存储共享空间，是办公的首选网络云盘。</p>
                        <a href="<%=request.getContextPath()%>/pages/cloud/cloudfolders.jsp" class="info">点击进入</a>
                    </div>
                </div>  
                <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/card.jpg" />
                    <div class="mask">
                        <h2>名片夹</h2>
                        <p>员工自己的名片夹，以及公司的通讯录。</p>
                        <a href="<%=request.getContextPath()%>/Card/query.action" class="info">点击进入</a>
                    </div>
                </div>
                <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/notice.jpg" />
                    <div class="mask">
                        <h2>系统公告</h2>
                        <p>点击查看系统公告。</p>
                        <a href="<%=request.getContextPath()%>/notice/queryByPager.action" class="info">点击进入</a>
                    </div>
                </div>
				<shiro:hasRole name="assignadmin">
                <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/assign.jpg" />
                    <div class="mask">
                        <h2>外派信息</h2>
                        <p>管理查看外派人员，公司以及对应派遣记录。</p>
                        <a href="<%=request.getContextPath()%>/assemp/query.action" class="info">点击进入</a>
                    </div>
                </div>
				</shiro:hasRole>
               <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/work.jpg" />
                    <div class="mask">
                        <h2>工作安排</h2>
						<p>查看自己的日程，日历，待办审批以及安排工作。</p>                        
						<a href="<%=request.getContextPath()%>/pages/Work/task.jsp" class="info">点击进入</a>
                    </div>
                </div>
                <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/meeting.jpg" />
                    <div class="mask">
                        <h2>会议管理</h2>
                        <p>会议相关申请和管理。</p>
                        <a href="<%=request.getContextPath() %>
						/meeting/queryByEmpId.action" class="info">点击进入</a>
                    </div>
                </div>
				<shiro:hasRole name="caradmin">
                <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/car.jpg" />
                    <div class="mask">
                        <h2>车辆管理</h2>
						<p>车队的管理以及派遣车辆。</p>
                        <a href="<%=request.getContextPath()%>/car/query.action" class="info">点击进入</a>
                    </div>
                </div>
				</shiro:hasRole>
				<shiro:hasRole name="adminstration">
                <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/poss.jpg" />
                    <div class="mask">
                        <h2>资产管理</h2>
                        <p>公司资产项目使用管理。</p>
                        <a href="<%=request.getContextPath()%>/house/query.action" class="info">点击进入</a>
                    </div>
                </div>
				</shiro:hasRole>
				<shiro:hasRole name="superadmin">
                <div class="view view-first">
                    <img src="<%=request.getContextPath()%>/image/setting.jpg" />
                    <div class="mask">
                        <h2>系统管理</h2>
                        <p>系统员工部门职务角色相关管理。</p>
                        <a href="<%=request.getContextPath()%>/dept/query.action" class="info">点击进入</a>
                    </div>
                </div>
				</shiro:hasRole>
                </div>
            </div>
	<!--网页底部-->
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<!--网页底部结束-->
	
	</body>
	</html>