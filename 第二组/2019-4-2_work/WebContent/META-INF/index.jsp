
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>鲜花速递网站-好花网!网上订鲜花,百株好花只取一朵精华</title>
    <meta name="keywords" content="鲜花，鲜花网，鲜花速递，网上订花，好花网" />
    <meta name="description" content="好花网, 百株好花只取一朵精华！好花网深知每一张订单都蕴含重大的意义，视口碑为生命，多年来专注鲜花速递行业，以感恩心态服务每一位客户。好花网赋予鲜花以文化、艺术的魅力，不断推出有故事的创新花束；好花网已经设计出超100多款花束，我们会持续引领花艺潮流，确保产品品质稳定如一！" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/base.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style3.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/base.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-1.12.4.min.js"></script>
    
   

    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/app.min.js"></script>
   
</head>
<body>

<div class="topper">

    <div class="right">
        
        <a class="iconbounce" href="//www.haohua.com" title="我的好花"><i class="icon icon-my"></i>我的好花</a>
        <a class="iconbounce" href="${pageContext.request.contextPath}/JSP/service.jsp" onclick="openChat();" title="联系客服"><i class="icon icon-service"></i>联系客服</a>
        <a class="iconbounce cart2" href="${pageContext.request.contextPath}/JSP/jiesuan.jsp" title="购物车"><i class="icon icon-cart"></i>购物车<span>(0)</span></a>
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
                <form action="#" method="get" name="search">
                    <input type="hidden" name="o" value="0" />
                    <div class="search-frame">
                        <input type="text" class="q" name="q" placeholder="请输入关键词搜索" autocomplete="off" value="" />
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
            <a class="service iconbounce" href="${pageContext.request.contextPath}/JSP/service.jsp"><i class="icon icon-service2"></i>在线客服</a>
        </div>
        <div class="main-nav clearfix">
            <dl>
                <dt class="active">
                    <div class="dt" ><a ><strong><font size="6px"> &nbsp;&nbsp;&nbsp;&nbsp;花&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;语</font></strong></a></div>

                    <div class="menu">
                            <img src="${pageContext.request.contextPath}/img/11.jpg" width="307px" height="450px" ;>
        </div>
                        <div class="menu-item">
                        </div>

                </dt>
                <dd><a href="index.jsp">首页</a></dd>
                <dd><a href="#zxz" >爱情鲜花</a></dd>

                <dd><a href="#stt" >永生花</a></dd>
                <dd><a href="#wy" >问候长辈</a></dd>
                <dd><a href="#sxt" >礼盒鲜花</a></dd>
                <dd><a href="#zty" >商务鲜花</a></dd>


            </dl>
        </div>
    </div>
</div>


<div class="full-slider">
    <div class="banner">

        <a class="item" href="https://www.haohua.com/xianhua/aq/">
            <div class="img" style="background-image: url(//www.haohua.com/upload/system/2019-03/18/19be0_665a.jpg);" data-lazy="//www.haohua.com/upload/system/2019-03/18/19be0_665a.jpg"></div>
        </a>
        <a class="item" href="/xianhua/zs99/p41.html">
            <div class="img" style="background-image: url(//www.haohua.com/upload/system/2019-02/26/2bf48_1269c.jpg);" data-lazy="//www.haohua.com/upload/system/2019-02/26/2bf48_1269c.jpg"></div>
        </a>
        <a class="item" href="/s.html?o=2&q=ROSELOVE&p=1">
            <div class="img" style="background-image: url(//www.haohua.com/upload/system/2019-03/19/15fb7_48ba.jpg);" data-lazy="//www.haohua.com/upload/system/2019-03/19/15fb7_48ba.jpg"></div>
        </a>
    </div>
    <div class="slider-nav">

        <div class="item">
            <img src="//www.haohua.com/upload/system/2019-03/18/19be7_171b9.jpg" />
        </div>
        <div class="item">
            <img src="//www.haohua.com/upload/system/2019-02/26/2bf51_14dde.jpg" />
        </div>
        <div class="item">
            <img src="//www.haohua.com/upload/system/2019-03/19/15f9a_64c3.jpg" />
        </div>
    </div>
</div>

<fieldset>
	<legend>员工信息</legend>
	<table id="customers">
		<tr>
			<th>
				商品图片（以实物为准）
			</th>
			<th>
				商品名称
			</th>
			<th>
				商品价格
			</th>
			<th>
				查看详情
			</th>
		</tr>
	<c:forEach items="${emps}" var="emp">
        <tr>
            <td>${emp.empno}</td>
            <td>${emp.ename}</td>
            <td>${emp.job}</td>
            <td>${emp.marname}</td>
            <td>${emp.hiredate}</td>
            <td>${emp.sal}</td>
            <td>${emp.comm}</td>
            <td>${emp.dname}</td>
            <td>
               	<button id="update" name="update" value="查看详情" onclick="#">更新</button>
            </td>
        </tr>
     </c:forEach>
</table>
</fieldset>

<div class="wrap indexbbs">
    <div class="h50"></div>
    <div class="ibbar"><h3>用户评论</h3></div>
    <div class="ib401">
        <ul class="clearfix">

            <li>
                <a class="info clearfix" >
                    <div class="img"><img src="//www.haohua.com/upload/image/2018-10/17/2f27e_36af.png" width="132" height="132" /></div>
                    <div class="con">
                        <p class="note">客服态度好的没话说，换了包装，在预定时间内准时到达了！女朋友生日没想到礼物，来临时决定送花给她，还好花还不错，挺好看的...</p>
                        <p class="msg">来自<span class="user">ywjcll_dq</span>的评价<span class="date">2019-03-28 15:09:09</span></p>
                    </div>
                </a>
            </li>
            <li>
                <a class="info clearfix">
                    <div class="img"><img src="//www.haohua.com/upload/image/2018-10/17/2f2b9_10c7f.png" width="132" height="132" /></div>
                    <div class="con">
                        <p class="note">花收到了，很漂亮很新鲜！跟图片没有色差。对于第一次在网上买花的我来说，真的很满意。客服的服务很好，改了几次送货时间，客...</p>
                        <p class="msg">来自<span class="user">daoyjw_dq</span>的评价<span class="date">2019-03-30 15:04:09</span></p>
                    </div>
                </a>
            </li>
        </ul>
    </div>
</div>
</div>




<div class="footer">
    <div class="wrap fservice">
        <ul class="clearfix">
            <li><span><i class="icon icon-fs1"></i>销量领先</span></li>
            <li><span><i class="icon icon-fs2"></i>百万客户信赖</span></li>
            <li><span><i class="icon icon-fs3"></i>时尚原创花艺</span></li>
            <li><span><i class="icon icon-fs5"></i>低价保障</span></li>
            <li><span><i class="icon icon-fs6"></i>3小时配送</span></li>
            <li><span><i class="icon icon-fs7"></i>200% 退赔承诺</span></li>
        </ul>
    </div>
    <div class="wrap fbody">
        <div class="h60"></div>
        <div class="fkehu">
            <h5>客户服务</h5>
            <dl>
                <dt>售后服务</dt>
                <dd>配送说明</dd>
                <dd>配送范围</dd>
                <dd>订单查询</dd>
                <dd>取消订单</dd>
                <dd>补交货款</dd>
            </dl>
            <dl>
                <dt>服务条款</dt>
                <dd>关于我们</dd>
                <dd>隐私条款</dd>
                <dd>安全条款</dd>
                <dd>支付说明</dd>
            </dl>
            <dl class="w150">
                <dt>热门咨询</dt>
                <dd>好花网购物流程</dd>
                <dd>能配送哪些城市？</dd>
                <dd>售后服务是怎么样的？</dd>
                <dd>提前多久预订鲜花？</dd>
            </dl>
            <div class="clear"></div>
        </div>
        <div class="ftongcheng">
            <h5>同城鲜花</h5>
            <ul class="clearfix">

                <li>北京市鲜花</li>

                <li>天津市鲜花</li>

                <li>河北省鲜花</li>

                <li>山西省鲜花</li>

                <li>内蒙省鲜花</li>

                <li>辽宁省鲜花</li>

                <li>吉林省鲜花</li>

                <li>上海市鲜花</li>

                <li>江苏省鲜花</li>

                <li>浙江省鲜花</li>

                <li>安徽省鲜花</li>

                <li>其他市鲜花</li>

            </ul>
        </div>
        <div class="flianxi">
            <h5>联系我们</h5>
            <p class="hotline">全国订购热线:<span>600-123-1234</span></p>

            <p class="worktime">客服工作时间：8:30-21:00</p><br>
            <p class="worktime">售后服务电话：18616666666</p><br>
        </div>

        <div class="clear"></div>
    </div>
    <!---->
    <script type="text/javascript" src="//www.haohua.com/theme/haohua.com/default/static/js/mobile.js"></script>
    <div class="wrap fcopyright">
        <p class="note">好花科技有限公司荣誉出品 百株好花，只取一朵精华   蜀ICP备18023296号-1</p>
        <ul class="clearfix">
            <li class="img1">实名认证</li>
            <li class="img2">官方验证</li>
            <li class="img3">报警服务</li>

        </ul>
        <p><font size="3">Copyright © 2019 本网页解释权归小七六人组 All Rights Reserved</font></p>
    </div>
</div>
<%--<script type="text/javascript" src="js/leyu.js"></script>--%>
<script type="text/javascript" src="//www.haohua.com/theme/haohua.com/default/static/js/tongji.js"></script>



</body>
</html>
