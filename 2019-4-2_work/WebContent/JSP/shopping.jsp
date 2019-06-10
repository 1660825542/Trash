<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/3
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>鲜花速递网站-好花网!网上订鲜花,百株好花只取一朵精华</title>
    <meta name="keywords" content="鲜花，鲜花网，鲜花速递，网上订花，好花网" />
    <meta name="description" content="好花网, 百株好花只取一朵精华！好花网深知每一张订单都蕴含重大的意义，视口碑为生命，多年来专注鲜花速递行业，以感恩心态服务每一位客户。好花网赋予鲜花以文化、艺术的魅力，不断推出有故事的创新花束；好花网已经设计出超100多款花束，我们会持续引领花艺潮流，确保产品品质稳定如一！" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/base.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/JS/jquery-2.1.4.js"></script>
    <link href="${pageContext.request.contextPath}/CSS/showDialog.css" rel="stylesheet" type="text/css" />

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
                        <input type="text" class="q" name="name" placeholder="请输入关键词搜索" autocomplete="off" value="" />
                        <button type="submit" class="s"><i class="icon icon-search"></i></button>
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
            <a class="service iconbounce" href="JSP/service.jsp" onclick="openChat();"><i class="icon icon-service2"></i>在线客服</a>
        </div>
		<div class="main-nav clearfix">
            <dl>
                <dt class="active">
                    <div class="dt" ><a ><strong><font size="6px"> &nbsp;&nbsp;&nbsp;&nbsp;详&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;情</font></strong></a></div>

                </dt>
                <dd><a href="${pageContext.request.contextPath}/User_Servlet?method=login&phonenum=${user}&password=${password}">首页</a></dd>
            


            </dl>
        </div>
    </div>
</div>

    <br/>
    <br/>
    <table  class="B2" style="margin: 0 auto 0">

        <tr colspan="4">
            <td>
                <img src="${product.picture}" width="300px" height="400px">
            </td>
            <td>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td style="line-height: 300%">
                <h3><strong><font size="5">${product.name}</font></strong></h3>
                <span>价格：${product.price}</span>

                <ul >
                    <li>访问：<span>902052</span></li>
                    <li>销量：<span>2487</span></li>
                    <li>评价：<span>522</span></li>
                    <li>配送：<span>全国1小时送达</span></li>
                </ul>

                <div class="shop-specs">

                    <div class="shop-num">
                        <dl class="clearfix">
                            <dt><strong>数量</strong></dt>
                            <dd ><a onclick="numberminus()" href="javascript:;">-</a></dd>
                            <dd><input  onblur="amount()" type="text" name="numbernu" id="numbernu" value="1"></dd>
                            <dd ><a onclick="myadd();" href="javascript:;">+</a></dd>
                            <span>件（库存${product.stock}件）</span>
                        </dl>
                    </div>
                    <input name="product_id" type="hidden" value="">
                    <input name="product_id" type="hidden" value="">
                    <input name="product_key" type="hidden" value="">
                    <div class="shop-but">

                        
                        <a onclick="shopcar()"  class="cart cartAdd" href="javascript:;">加入购物车</a>

                    </div>

                </div>
            </td>
        </tr>
    </table>

<div class="wrap">
    <div class="h60"></div>
    <div class="body-box yhpj">
        <div class="body-box-bar"><span class="title">用户评价(840)</span></div>

        <div class="body-haoping">

            <div class="left">
                <p>好评率</p>
                <div class="haoping-score">${percent}</div>

                <span class="star"><span class="star-score star-${percent1}"></span></span>
            </div>
            <div class="right">
                <dl class="clearfix">
                    <dt>大家都在说：</dt>

                    <dd>送货速度<span>(84)</span></dd>
                    <dd>花质量不错<span>(92)</span></dd>
                    <dd>包装很好<span>(92)</span></dd>
                    <dd>送花速度快<span>(92)</span></dd>
                    <dd>性价比高<span>(92)</span></dd>
                    <dd>值得推荐<span>(84)</span></dd>
                    <dd>花很漂亮<span>(84)</span></dd>
                    <dd>服务态度好<span>(128)</span></dd>
                </dl>
            </div>
            <div class="clear"></div>
        </div>

        <div class="bbs-ajax">

            <div class="bbs-list">
                <ul>
                    <c:forEach items="${remarks}" var="remark">
                    <li class="bbs-list-item">
                        <div class="bbs-user-photo"></div><div class="bbs-user-photo"></div>
                        <div class="bbs-con">
                            <div class="bbs-user clearfix">
                                <span class="bbs-user-name">${remark.phonenum}</span>
                                <span class="star"><span class="star-score star-${remark.rank}"></span></span>
                            </div>
                            <div class="bbs-body">
                                <p>${remark.text}</p>
                            </div>

                            <div class="bbs-img">
                                <ul class="clearfix">
                                    <c:if test="${remark.remark_src ne null}">
                                    <li><img src="${remark.remark_src}" jqimg="#" width="200" /></li>
                                    </c:if>
                                </ul>
                                <div class="bbs-img-preivew"></div>
                            </div>
                            <div class="bbs-post">
                                <span class="bbs-post-date">${remark.time}</span>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </li>

                    </c:forEach>


                </ul>
            </div>


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

</body>
<script type="text/javascript">
    
    function shopcar() {

        $.get("${pageContext.request.contextPath}/shopAction",{
            "method":"shopcar",
            "id":${product.goodid},
            "number": $("#numbernu").val()
        },function () {
            alert("成功加入购物车")
        })
    }

    function numberminus() {
        var  s=$("#numbernu");
        if (parseInt(s.val())>1)
        {
            $("#numbernu").val(parseInt(s.val())-1);
        }
        else
        {
            alert("数量应大于0");
        }
    };
    function amount() {
        var  s=$("#numbernu");
		alert("6666");
        if (parseInt(s.val())>${product.stock})
        {
            confirm("已经达库存上限")
            $("#numbernu").val(1);
        }
    };
    function myadd() {
       var  s=$("#numbernu");
       if (parseInt(s.val())<parseInt(${product.stock}))
       {
           $("#numbernu").val(parseInt(s.val())+1);
       }
       else
       {
           confirm("已经达库存上限");
       }
    }

</script>

</html>
