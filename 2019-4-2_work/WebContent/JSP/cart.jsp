
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>好花网 | 购物中心 &gt; 购物车</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/base.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        function myadd(x){
            $.get('${pageContext.request.contextPath}/StockServlet',{
                "cartid":""+x+""
            },function (data) {
            	
                var  s=$('input[cartid='+x+']');
                if (parseInt(s.val())<parseInt(data))
                {
                    $('input[cartid='+x+']').val(parseInt(s.val())+1)
                }
                else
                {
                    confirm("已经达库存上限")
                }

            },'json')
        }
    </script>
    
    <script type="text/javascript">
            function numberminus(x) {
                $.get('${pageContext.request.contextPath}/StockServlet2',{
                    "cartid":""+x+""
                },function (date) {
                	
                    var s = $('input[cartid='+x+']');
                    if (parseInt(s.val()) > 1) {
                        $('input[cartid='+x+']').val(parseInt(s.val()) - 1)
                    }
                    else {
                        alert("数量应大于0")
                    }
                },'json')
            };


    </script>
</head>
<body>

<div class="topper">

    <div class="right">
        <a class="iconbounce" href="https://www.haohua.com/why/" title="我的好花"><i class="icon icon-my"></i>我的好花</a>
        <a class="iconbounce" href="JSP/service.jsp" onclick="openChat();" title="联系客服"><i class="icon icon-service"></i>联系客服</a>
        <a class="iconbounce cart2" href="${pageContext.request.contextPath}/GetGoods" title="购物车"><i class="icon icon-cart"></i>购物车</a>
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
                <form action="LikeAction">
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
            <a class="service iconbounce" href="JSP/service.jsp"><i class="icon icon-service2"></i>在线客服</a>
        </div>
        <div class="main-nav clearfix">
            <dl>
                <dt class="active">
                    <div class="dt" ><a ><strong><font size="6px"> &nbsp;&nbsp;&nbsp;&nbsp;结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;算</font></strong></a></div>

                    <div class="menu-item">
                    </div>

                </dt>
                <dd><a href="${pageContext.request.contextPath}/User_Servlet?method=login&phonenum=${user}&password=${password}">首页</a></dd>
              	
            </dl>
        </div>
    </div>
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

<c:forEach items="${cartList}" var="cart">
        <div class="cart-list-item">
            <div class="cart-shop-name">
                <ul class="clearfix">
                    <li class="img"><img src="${cart.picture}" width="100" height="100" /></li>
                    <li class="txt">
                        <h5>${cart.name}</h5>
                        <input type="hidden" name="cartid" value="${cart.name}">
                    </li>
                </ul>
            </div>
            <div class="cart-shop-price">
                <p class="price" id="price_1">￥<span>${cart.price}</span></p>
            </div>
            <div class="cart-shop-num clearfix">
            <a onclick="numberminus('${cart.id}')"  href="">-</a>
            <input type="text"  cartid="${cart.id}" name="goods_num" value="${cart.num}" readonly="true" data-max="394" data-gid="1" data-pid="1" />
            <a onclick="myadd('${cart.id}')" href="">+</a>
            </div>
            <div>
                <span>库存:${cart.stock}</span>
            </div>
            <div class="cart-shop-active">
                <a href="${pageContext.request.contextPath }/DeleteCart?goodsId=${cart.id}">删除</a>
            </div>
            <div class="clear"></div>
        </div>
    </c:forEach>
    <div class="cart-list">
        <div class="cart-list-footer">
            <ul class="clearfix">
                <li class="total">总计：<label class="price">￥<span>${totalPrice}</span></label></li>
                <li class="active"><a href="${pageContext.request.contextPath }/TotalPriceServlet?method=total">结算</a></li>
            </ul>
        </div>
    </div>


    <div class="h60"></div>

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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mobile.js"></script>
    <div class="wrap fcopyright">
        <p class="note">好花科技有限公司荣誉出品 百株好花，只取一朵精华   蜀ICP备18023296号-1</p>
        <ul class="clearfix">
            <li class="img1">实名认证</li>
            <li class="img2">官方验证</li>
            <li class="img3">报警服务</li>

        </ul>
        <p><font size="3">Copyright ? 2019 本网页解释权归小七六人组 All Rights Reserved</font></p>
    </div>
</div>
<%--<script type="text/javascript" src="js/leyu.js"></script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/tongji.js"></script>--%>



</body>
</html>
