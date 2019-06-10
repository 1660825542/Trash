
$(function(){
	$('#phonenum').on('blur',function(){
		var pObj=$('#error');
		$.post('User_Servlet',{
			'phonenum':$('#phonenum').val(),
			'method':"proving"
		},function(data){
			$.each(data,function(key,value){
				pObj.html("");
				pObj.append(value);
			});
		},'json');
	});
});
$(function(){
	$('#repassword').on('blur',function(){
		var pObj=$('#question');
		$.post('User_lostPassword',{
			'phonenum':$('#repassword').val(),
			'method':"repassword"
		},function(data){
			$.each(data,function(key,value){
				pObj.html("");
				pObj.append(value);
			});
		},'json');
	});
});


$(function(){

	$('#number').on('blur',function(){
		var pObj=$('#td1');
		$.post('Goodselect_Servlet',{
			'number':$('#number').val(),
			'method':"selectnumber"
		},function(data){
			$.each(data,function(key,value){
				pObj.html("");
				pObj.append(value);
			});
		},'json');
	});
});



























