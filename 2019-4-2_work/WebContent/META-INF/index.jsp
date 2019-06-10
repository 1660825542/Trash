
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>鲜花速递网站-好花网!网上订鲜花,百株好花只取一朵精华</title>
    <meta name="keywords" content="鲜花，鲜花网，鲜花速递，网上订花，好花网" />
    <meta name="description" content="好花网, 百株好花只取一朵精华！好花网深知每一张订单都蕴含重大的意义，视口碑为生命，多年来专注鲜花速递行业，以感恩心态服务每一位客户。好花网赋予鲜花以文化、艺术的魅力，不断推出有故事的创新花束；好花网已经设计出超100多款花束，我们会持续引领花艺潮流，确保产品品质稳定如一！" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/base.css" />

    

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.showDialog.js"></script>
    <link href="${pageContext.request.contextPath}/CSS/showDialog.css" rel="stylesheet" type="text/css" />

   
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/slick.min.js"></script>
   
</head>

<body>

<div class="topper">

    <div class="right">
        <a class="iconbounce"  title="我的好花" href="https://www.haohua.com/why/"><i class="icon icon-my" ></i>我的好花</a>
        <a class="iconbounce" href="JSP/service.jsp" onclick="openChat();" title="联系客服"><i class="icon icon-service"></i>联系客服</a>
        <a class="iconbounce cart2" href="${pageContext.request.contextPath}/GetGoods" title="购物车"><i class="icon icon-cart"></i>购物车<span></span></a>
    </div>
</div>
</div>

<div class="header2brt">
    <div class="wrap header index-header">
        <div class="logobanner clearfix">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/img/logo.png" width="248" height="41" alt="好花网" />
            </div>
            <div class="search">
                <form action="LikeAction" >
                    <div class="search-frame">
                        <input type="text" class="q" name="name" placeholder="请输入关键词搜索" autocomplete="off" value="" />
                        <button type="submit" class="s" onclick="if($('.q').val()=='') return false;"><i class="icon icon-search"></i></button>
                    </div>
                </form>

            </div>
            <div class="tele400 clearfix">
                <div class="left">

                    <i class="icon icon-tel"></i>
                </div>
                <div class="left">
                    <label>111111111</label>
                    <p>咨询热线</p>
                </div>
            </div>
            <a class="service iconbounce" href="JSP/service.jsp"><i class="icon icon-service2"></i>在线客服</a>
        </div>
        <div class="main-nav clearfix">
            <dl>
                <dt class="active">
                    <div class="dt" ><a ><strong><font size="6px"> &nbsp;&nbsp;&nbsp;&nbsp;花&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;语</font></strong></a></div>

                    <div class="menu">
                        <img src="${pageContext.request.contextPath}/img/qaz.jpg" width="307px" height="450px" ;>
                    </div>
                    <div class="menu-item">
                    </div>

                </dt>
                <dd><a href="${pageContext.request.contextPath}/User_Servlet?method=login&phonenum=${user}&password=${password}">首页</a></dd>
                <dd><a href="#zxz" >爱情鲜花</a></dd>

                <dd><a href="#zxz" >永生花</a></dd>
                <dd><a href="#zxz" >问候长辈</a></dd>
                <dd><a href="#zxz" >礼盒鲜花</a></dd>
                <dd><a href="#zxz" >商务鲜花</a></dd>


            </dl>
        </div>
    </div>
</div>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/slick.css" />
<div class="full-slider">
    <div class="banner">
        <a class="item" >
            <div class="img" style="background-image: url(${pageContext.request.contextPath}/img/lb1.jpg);" data-lazy="${pageContext.request.contextPath}/img/2.jpg"></div>
        </a>
        <a class="item" >
            <div class="img" style="background-image: url(${pageContext.request.contextPath}/img/lb2.jpg);" data-lazy="${pageContext.request.contextPath}/img/3.jpg"></div>
        </a>
        <a class="item">
            <div class="img" style="background-image: url(${pageContext.request.contextPath}/img/lb3.jpg);" data-lazy="${pageContext.request.contextPath}/img/4.jpg"></div>
        </a>
    </div>
    <div class="slider-nav">

        <div class="item">
            <img src="${pageContext.request.contextPath}/img/22.jpg" />
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/33.jpg" />
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/44.jpg" />
        </div>
    </div>
</div>

<c:forEach items="${emps1}" var="aa">
    <div>
        <div class="wrap indexblock">

            <div class="h50"></div>
            <c:forEach items="${attrs}" var="attr">
             <c:if test="${attr.attri eq aa.key}">
            <div class="ibbar" >
                <h3><strong><font size="5">${attr.ename}</font></strong> <span>送·让你怦然心动的人</span></h3>
                    

            </div>

            <div class="ibcon clearfix">
                <div class="left ib201">
                <a class="info" href="#">
                    <div class="img"><img src="${pageContext.request.contextPath}/${attr.path}" height="412" /></div>
                    <h5>${attr.ename}专区</h5>
                    <p>进入专区</p>
                </a>
            </div>
             </c:if>
             	
                </c:forEach>
                <div class="left ib202">
                    <ul>
                          <c:forEach items="${aa.value}" var="aaa">
                                <li>
                                    <a class="info imghover" href="${pageContext.request.contextPath}/ProductInfoAction?Product_id=${aaa.goodid}">
                                  

                                        <div class="img"><img src="${aaa.picture}" height="242" onclick="lds() "/></div>
                                        <h5>${aaa.name}</h5>
                                        <p class="price b">
                                        <span>${aaa.price}</span>

                                        </p>
                                    </a>
                                </li>
                          </c:forEach>

                    </ul>
                </div>
            </div>
        </div>
</c:forEach>
</body>
</html>
