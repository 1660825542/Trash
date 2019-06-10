
//全选
function selectAll(e){
	if($(e).prop("checked") == true){
		$('input[name="selected"]').prop("checked", true);
	}else{
		$('input[name="selected"]').prop("checked", false);
	}
}
//检查是不是全选
function checkAll(){
	if($('input[name="selected"]').length==$('input[name="selected"]:checked').length){
		$('input[name="selectAll"]').prop("checked", true);
	}else{
		$('input[name="selectAll"]').prop("checked", false);
	}
}

//ajax异步，提交列表内容checkBox
function submitList(e){
	//遍历循环所有已选项目
	$('input[name="selected"]:checked').each(function(i){
		var key = this;//ajax的get请求中无法访问this，只能这样传进去了
		$.get(
			e,
			{messageId:$(this).parent().next($('td[name="messageId"]')).html()},
			function(data){
				if(data==1){								
					//成功后该条淡出
					$(key).parent().parent().fadeOut("slow",function(){
						layer.msg('成功');
						$(this).remove();//淡出之后彻底删除
						if($('tr').length<5){
							window.location.reload();
						}
					});
				}else{
					layer.msg('出现错误');
				}					
			}
		);
	});
}

//补齐表格空列
$(document).ready(function(e){
	while ($('tr').length<10)
	{
		$('tr').last().after('<tr><th>&nbsp;</th><th></th><th></th><th></th></tr>');
	}
	$('td[name="empName"').each(function(){
		var key = this;
		$.get("getEmpName.action",{recipientId:$(this).prev().text()},
				function(data){
					if(data==0){
						$(key).text($(this).prev().text());
					}else{
						$(key).text(data);
					}
				}
		);
	});
});
