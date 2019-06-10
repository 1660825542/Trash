<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/4
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>好花网 | 购物中心 &gt; 购物车</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/base.css" />

    <%--<script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>--%>
    <%--<script type="text/javascript" src="../js/jquery.lazyload.min.js"></script>--%>

    <%--<script type="text/javascript" src="../js/jquery.showDialog.js"></script>--%>
    <%--<link href="css/showDialog.css" rel="stylesheet" type="text/css" />--%>

    <%--<script type="text/javascript" src="../js/app.min.js"></script>--%>
    <%--<script type="text/javascript" src="../js/common.js"></script>--%>
    <%--<script type="text/javascript">app.uri='';app.root='//www.haohua.com';</script>--%>
    <head>
        <title>鲜花速递网站-好花网!网上订鲜花,百株好花只取一朵精华</title>
        <meta name="keywords" content="鲜花，鲜花网，鲜花速递，网上订花，好花网" />
        <meta name="description" content="好花网, 百株好花只取一朵精华！好花网深知每一张订单都蕴含重大的意义，视口碑为生命，多年来专注鲜花速递行业，以感恩心态服务每一位客户。好花网赋予鲜花以文化、艺术的魅力，不断推出有故事的创新花束；好花网已经设计出超100多款花束，我们会持续引领花艺潮流，确保产品品质稳定如一！" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/base.css" />

        <%--<script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>--%>
        <%--<script type="text/javascript" src="../js/jquery.lazyload.min.js"></script>--%>

        <%--<script type="text/javascript" src="../js/jquery.showDialog.js"></script>--%>
        <%--<link href="//www.haohua.com/theme/haohua.com/default/static/css/showDialog.css" rel="stylesheet" type="text/css" />--%>

        <%--<script type="text/javascript" src="../js/app.min.js"></script>--%>
        <%--<script type="text/javascript" src="../js/common.js"></script>--%>
        <%--<script type="text/javascript">app.uri='';app.root='//www.haohua.com';</script>--%>
        <%--<meta name="360-site-verification" content="" />--%>
        <%--<meta name="baidu-site-verification" content="50fLQsZc5e" />--%>
        <%--<meta property="qc:admins" content="" />--%>
    </head>
<body>

<div class="topper">

    <div class="right">
        <a class="iconbounce login" href="#" title="登录 / 注册"><i class="icon icon-login"></i>登录 / 注册</a>
        <a class="iconbounce"  title="我的好花"><i class="icon icon-my"></i>我的好花</a>
        <a class="iconbounce" href="javscript:;" onclick="openChat();" title="联系客服"><i class="icon icon-service"></i>联系客服</a>
        <a class="iconbounce cart2" href="jiesuan.jsp" title="购物车"><i class="icon icon-cart"></i>购物车<span>(0)</span></a>
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
            <a class="service iconbounce" href="liuyan.jsp"><i class="icon icon-service2"></i>在线客服</a>
        </div>
        <div class="main-nav clearfix">
            <dl>
                <dt class="active">
                    <div class="dt" ><a ><strong><font size="6px"> &nbsp;&nbsp;&nbsp;&nbsp;结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;算</font></strong></a></div>

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

<div class="wrap nav">
    <a href="index.jsp">好花首页</a><span>&gt;</span>购物车
</div>

<div class="wrap">



    <div class="cart-list">

        <div class="cart-list-bar">
            <ul class="clearfix">
                <li class="cart-shop-name">商品名称</li>
                <li class="cart-shop-price">单价</li>
                <li class="cart-shop-num">数量</li>
                <li class="cart-shop-active">操作</li>
            </ul>
        </div>


        <div class="cart-list-item">
            <div class="cart-shop-name">
                <ul class="clearfix">
                    <li class="ipt"><label class="checkbox"><input type="checkbox" name="chk" value="1" checked /><span></span></label></li>
                    <li class="img"><img src="#" width="100" height="100" /></li>
                    <li class="txt">
                        <h5>Cherish珍爱鲜花系列99枝红玫瑰</h5>
                        <p>商品类型: 99枝红玫瑰</p>
                    </li>
                </ul>
            </div>
            <div class="cart-shop-price">
                <p class="price" id="price_1">¥<span>498.00</span></p>
            </div>
            <%--<div class="cart-shop-num clearfix">--%>
                <%--<a href="javascript:;">-</a><input type="text" name="goods_num" value="1" readonly="true" data-max="394" data-gid="1" data-pid="1" /><a href="javascript:;">+</a>--%>
            <%--</div>--%>
            <div class="cart-shop-active">
                <a href="#">删除</a><br />
                <a href="#"><span>移到我的收藏夹</span></a>
            </div>
            <div class="clear"></div>
        </div>


    </div>

    <div class="cart-list">
        <div class="cart-list-footer">
            <ul class="clearfix">
                <li class="ipt"><label class="checkbox"><input type="checkbox" name="chkall" value="1" /><span></span>全选</label></li>
                <li class="num">已选商品<span>1</span>件</li>
                <li class="total">总计：<label class="price">¥<span>498.00</span></label></li>
                <li class="active"><a href="Success.jsp">结算</a></li>
            </ul>
        </div>
    </div>


    <div class="h60"></div>
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

<div class="rside" >
    <div class="cservice rotate">
        <a href="liuyan.jsp" title="好花顾问" >
            <i class="icon-rside icon-cservice"></i>
            <span>好花顾问</span>
        </a>
    </div>

    <ul>
        <li class="rside-btn rotate">
            <a href="javascript:;" onclick="openChat();" title="今日特价">
                <i class="icon-rside icon-special"></i>
                <span>今日特价</span>
            </a>
        </li>
        <li class="rside-btn rotate">
            <a href="zhangbei.jsp#zhangbei" title="长辈鲜花">
                <i class="icon-rside icon-birth"></i>
                <span>长辈鲜花</span>
            </a>
        </li>
        <li class="rside-btn rotate">
            <a href="chunai.jsp#chunai" title="爱情鲜花">
                <i class="icon-rside icon-love"></i>
                <span>爱情鲜花</span>
            </a>
        </li>
        <li class="rside-btn rotate">
            <a href="shangwu.jsp#shangwu" title="商务定制">
                <i class="icon-rside icon-business"></i>
                <span>商务定制</span>
            </a>
        </li>

    </ul>

    <div class="rside-cart iconbounce cart">
        <a href="jiesuan.jsp" title="购物车">
            <i class="icon-rside icon-cart2 icon"></i>
            <span></span>
        </a>
        <p>购物车</p>
    </div>
</div>

</body>
</html>
