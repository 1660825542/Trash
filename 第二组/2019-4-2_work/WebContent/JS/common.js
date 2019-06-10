$(function(){
	//index 大Banner
	if( $('.full-slider .banner')[0] ){
		var banner=$('.full-slider .banner').slick({
		    lazyLoad: 'ondemand',
		    dots:false,
		    infinite: true,
		    arrows: false,
		    waitForAnimate:false,
		    fade: true,
		    autoplay: true,
	  		autoplaySpeed: 15000,
		});
		banner.on('beforeChange',function (event, slick, currentSlide, nextSlide) {
		      var currentSlide = nextSlide;
		      $('.full-slider .slider-nav .item').removeClass('active').eq(currentSlide).addClass('active');
		});
		$('.full-slider .slider-nav .item').on('mouseenter',function () {
		      var index=$(this).index();
		      banner.slick('slickGoTo',index)
		}).eq(0).addClass('active');
	}

	app.login.ajax = function(){ 
		showWindow('您尚未登录', '/member/login/index', 500, 300);
	}

	//加载会员
	app.login.check(function(result){
		if( result.status ){
			$('.login').prop('outerHTML', '<a class="iconbounce" href="'+app.root+'/member/index/" title="'+result.aliasname+'"><i class="icon icon-login"></i>用户中心 ( '+result.aliasname+' )</a>');
			$('.userinfo').html('<a href="'+app.root+'/member/index/" title="'+result.aliasname+'"><i class="icon icon-user"></i>'+result.aliasname+'</a>');
			$('.bbs-userinfo').html('<a href="'+app.root+'/member/index/" title="'+result.aliasname+'">'+result.aliasname+'</a>');
		}
		else{ 
			$('.login').prop('outerHTML', '<a class="iconbounce login" href="javascript:;" onclick="login();" title="登录 / 注册"><i class="icon icon-login"></i>登录 / 注册</a>');
			$('.userinfo').html('<a href="javascript:;" onclick="login();"><i class="icon icon-user"></i>登录</a>');
			$('.bbs-userinfo').html('<a href="javascript:;" onclick="login();" title="会员登录">登录</a>|<a href="javascript:;" onclick="login();" title="会员注册">注册</a>');
		}
	});

	//加载购物
	app.shop.cart.read(function(result){
		$('.cart span').html( result.data.num );
		$('.cart2 span').html( '(' + result.data.num + ')' );
	});

	//筛选
	if( $('.filter-but')[0] ){
		$('.filter-but a').click(function(){
			var obj = $('.filter');
			if( obj.hasClass('filter-min') ){
				$(this).attr('title', '收起').html('收起<span>&gt;</span>');
				obj.removeClass('filter-min');
			}
			else{ 
				$(this).attr('title', '展开').html('展开<span>&lt;</span>');
				obj.addClass('filter-min');
			}
		});
	}

	//商品预览图
	if( $('.shop-preview-item')[0] ){
		$('.shop-preview-item ul li').click(function(){
			$(".shop-preview-item ul li").removeClass("active");
			$(this).addClass("active");
			var src = $(this).find("img").attr("jqimg");
			$(".shop-preview img").attr("src", src);
			$(".shop-preview img").attr("jqimg", src);
		});
		$(".shop-preview").jqueryzoom({
			xzoom: 468,
			yzoom: 468,
			yoffset: 0,
			position: "right",
			preload: 1
		});
	}

	//video
	if( $('.shop-video')[0] ){
		var dsvideo = false;
		var player = videojs("video-player", {
			"techOrder": ["flash","html"],
			"autoplay":false,
			control: {
				captionsButton: false,
				chaptersButton : false,
				liveDisplay:false,
				playbackRateMenuButton: false,
				subtitlesButton:false
			}
		},
		function(){
			this.on('play', function(){
				dsvideo = true;
				$(".shop-video").css('z-index', 100);
			});
			this.on('ended',function(){
				this.pause();
				dsvideo = false;
				$(".shop-video").css('z-index', 10);
			});
		});
			
		$('.vjs-play-my').click(function(){
			player.play();
		});

		$('.shop-preview-item ul li').click(function(){
			if( dsvideo ){
				player.pause();
				$(".shop-video").css('z-index', 10);
			}
		});
	};

	//配件
	if( $('.shop-part')[0] ){
		$('.shop-part dd').click(function(){ 
			$('.shop-part dd').removeClass("active");
			$(this).addClass("active");
		});
	}

	//服务保障
	if( $('.gwbz-menu')[0] ){
		$('.gwbz-menu ul li a').hover(function(){
			$(this).parent().parent().find('a').removeClass('active');
			$(this).addClass('active');
			var index = $(this).parent().index();
			var imgs = $(this).parent().parent().parent().parent().find('.gwbz-img ul li');
			imgs.removeClass('active').eq(index).addClass('active');
		});
	}


	//评论图
	$(document).on('click', '.bbs-list-item .bbs-img li', function(){
		if( $(this).hasClass('active') ){
			$(this).removeClass("active").parent().parent().find('.bbs-img-preivew').hide();
		}
		else{
			//$('.bbs-img-preivew').hide();
			$(this).siblings().removeClass("active");
			$(this).addClass("active");
			var src = $(this).find('img').attr("jqimg");
			$(this).parent().parent().find('.bbs-img-preivew').html('<img src="' + src + '" />').show();
		}
	}); 

	//评论上传
	$(document).on('change', '.bbs-up-item input', function(){
		if( !/\.(jpg|png|jpeg|gif)$/i.test($(this).val()) ) {
			app.tools.error('请选择图像文件！');
			$(this).val("");
			return false;
		}
		var url = app.tools.file2url(this.files[0]);
		var li = $(this).parent();
		var span = li.find('span');
		var ul = li.parent();
		li.addClass('selected');
		if(url)
			span.css({'background-image': 'url('+url+')'});
		if( ul.children().length<5 )
			ul.append('<li class="bbs-up-item"><span><i></i></span><input name="f[]" accept="image/*" type="file" /></li>');
	});
	$(document).on('click', '.bbs-up-item span', function(){
		if( $(this).parent().parent().children().length==5 && !$(this).parent().parent().last().hasClass('selected') )
			$(this).parent().parent().append('<li class="bbs-up-item"><span><i></i></span><input name="f[]" accept="image/*" type="file" /></li>');
		$(this).parent().remove();
	});
	$(document).on('change', '.bbs-con-textarea textarea', function(){
		$(this).css({'background-image': 'none'});
	});

	$(document).on('mousemove', '.bbs-con-star .star', function(){
		var pos = $(this).offset();
		var x =event.pageX - pos.left;
		var star = Math.ceil(x / (83 / 5)) * 20 + '%';
		$(this).find('span').width(star);
	}).on('mouseout', '.bbs-con-star .star', function(){
		$(this).find('span').width('');
	}).on('click', '.bbs-con-star .star', function(){
		var pos = $(this).offset();
		var x =event.pageX - pos.left;
		var star = Math.ceil(x / (83 / 5));
		$(this).find('span').attr('class', 'star-score star-'+star);
		$(this).parent().find('input').val( star );
	});
	$(document).on('click', '.bbs-submit', function(){
		var form = $(this).parent().parent().parent();
		app.login.exec(function(){
			app.article.comment.post( form, function(result){
				app.tools.info(result.msg, function(){
					if( result.status ) location.reload();
				});
			}, function( msg ){
				app.tools.error(msg);
			});
		});
	});
	$(document).on('click', '.order-bbs-submit', function(){
		var form = $(this).parent().parent().parent();
		app.login.exec(function(){
			app.shop.order.comment( form, function(result){
				app.tools.info(result.msg, function(){
					if( result.status ) location.reload();
				});
			}, function( msg ){
				app.tools.error(msg);
			});
		});
	});

	
	//显示侧边栏
	showRside();

	//表单
	$('input[pattern]').change(function(){
		var regExp = eval( $(this).attr('pattern') );
		if( regExp.test( $(this).val() ) ){
			$(this).css({borderColor:''});
		}
		else{ 
			$(this).css({borderColor:'#ff2f2f'});
		}
	});

	//ajax
	$('a[res=ajax]').click(function(){
		var _this = this;
		var method = $(_this).data('method') && $(_this).data('method').toLowerCase() =='post' ? 'post' : 'get';
		var url = $(_this).data('url') ? $(_this).data('url') : $(_this).attr('href');
		var option = {};
		if( $(_this).data('option') ){
			try{
				option = eval("(" + $(_this).data('option') + ")");
			}
			catch(e){}
		}
		var title = $(this).attr('title') ? $(this).attr('title') : $(this).text();
		showConfirm( '确信要 "' + title + '" 吗？', function(){
			$.ajax({
				url		: url,
				cache	: true,
				type	: method,
				data 	: option,
				dataType: 'json',
				error	: function( event, xhr ) {
					app.tools.info( xhr.responseText );
				},
				success: function(result) {
					if( result.status ){
						location.reload();
					}
					else{
						app.tools.info( result.msg );
					}
				}
			});
		});
		return false;
	});
	
	//dialog
	$('a[res=dialog]').click(function(){
		var url = $(this).data('url') ? $(this).data('url') : $(this).attr('href');
		var width = $(this).data('width') ? $(this).data('width') : 500;
		showWindow($(this).text(), url, width);
		return false;
	});
	
	//confirm
	$('a[res=confirm]').click(function(){
		var url = $(this).data('url') ? $(this).data('url') : $(this).attr('href');
		var width = $(this).data('width') ? $(this).data('width') : 500;
		var title = $(this).attr('title') ? $(this).attr('title') : $(this).text();
		showConfirm( '确信要 "' + title + '" 吗？', function(){
			window.location.href = url;
		});
		return false;
	});

	//favorite
	if( $('a[res="favorite"]')[0] ){
		$('a[res="favorite"]').click(function(){
			var _this = this;
			app.login.exec(function(){
				if( !$(_this).hasClass('favorited') ){ 
					app.article.favorite.add( $(_this).attr('aid'), function(result){
						if( result.status ){
							$(_this).addClass('favorited').find('span').text('已收藏');
						}
					});
				}
				else{  
					app.article.favorite.remove( $(_this).attr('aid'), function(result){
						if( result.status ){
							$(_this).removeClass('favorited').find('span').text('收藏');
						}
					});
				}
			});
		});
		
		//注册登录后过程
		app.login.register(function(){
			var aids = [];
			$('a[res="favorite"]').each(function(i){
				aids.push( $(this).attr('aid') );
			});
			app.article.favorite.checks(aids.join(','), function(result){
				$(result.aids).each(function(i,v){
					$('a[res="favorite"][aid='+v+']').addClass('favorited').find('span').text('已收藏');
				});
			});
		});
	}

    //fixed
    $(".autofixed").each(function (i, o) {
        var inside = false;
        var top = $(o).offset().top;
        var height = $(o).height();
        var fixed = false;
        if ($(o).attr("fixed")) {
            fixed = $($(o).attr("fixed"));
            $(o).css('overflow','hidden');
        }
        $(window).scroll(function () {
            var position = $(window).scrollTop();
            if (!inside) {
				top = Math.max(top, $(o).offset().top);
            }
            if ( position >= top) {
                if (!inside) {
                    $(o).addClass("fixed");
                    inside = true;
                }
                if (fixed) {
                	var h = $(o).height();
                	var h2 = fixed.offset().top + fixed.height() - position;
                	var mt = 0;
                	if( h2 < h )
                		mt = h2 - h;
                	$(o).css('margin-top', mt);
                }
            }
			else if (position < top) {
				$(o).removeClass("fixed");
                if (fixed) {
					//$(o).height(height);
					$(o).css('margin-top', 0);
				}
                inside = false;
            }
        });
    });


});

function openChat(){
	if( typeof(doyoo)!='undefined' ){
		doyoo.util.openChat('g=10080395');
	}
	return false;
}

function openQQ(){
	if( typeof(doyoo)!='undefined' ){
		doyoo.util.openChat('g=10080395');
	}
	return false;
}

function showRside(){ 
	$('.rside').fadeIn( 2000 );
}

function minRside(){
	$('.rside ul').addClass('expand');
}

function maxRside(){
	$('.rside ul').removeClass('expand');
}

function formVerify( form ){
	var status = true;
	$(form).find('input[pattern]:enabled,select[pattern]:enabled,textarea[pattern]:enabled').each(function(){
		if( !app.tools.regx( $(this).val(), $(this).attr('pattern') ) ){
			status = false;
			$(this).css({borderColor:'#ff2f2f'});
		}
	});
	return status;
}

function login( callback ){
	callback = callback ? callback : function(){location.reload();};
	app.login.ajaxcall = callback;
	showWindow('您尚未登录', '/member/login/index', 500, 300);
}

function  showCartTip( obj ){ 
	var cartHtml = '<div class="cart_tip_bd">';
	cartHtml	+= '<div class="head clearfix"><a class="J_Close close-btn" href="javascript:;" onclick="$(\'.cart_tip_bd\').remove();">关闭</a><span class="arrow_right"></span></div>';
	cartHtml	+= '<div class="module-cart-body"><div class="module-cart-box">';
	cartHtml	+= '<p class="mac-mb10"><span class="mac-success-txt module-cart-icons"><em></em>已将商品添加到购物车</span></p>';
	cartHtml	+= '<p><a class="mac-go-cart module-cart-icons" href="/shop/cart/">去购物车结算</a><a class="J_Close close-txt" href="javascript:" onclick="$(\'.cart_tip_bd\').remove();">继续购买</a></p>';
	cartHtml	+= '</div></div></div>';

	$(cartHtml).appendTo( $('body') ).css({
		top: $(obj).offset().top-80,
		left: $(obj).offset().left+10
	}).show().find('.mac-go-cart').attr('href', app.root+'/shop/cart/');
}