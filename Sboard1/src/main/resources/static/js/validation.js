/**
 * 
 */
let ctxRoot = '/Sboard1'
$(function(){
	$('input[name=uid]').keyup(function(){
		let value = $(this).val();
		let jsonData = {"uid":value}
		if(value.length < 4){
			return ;
		}
				
		$.ajax({
			url:ctxRoot+'/user/checkUid',
			type:'GET',
			data:jsonData,
			dataType:'json',
			success: function (data) {
				console.log(data)
				if(data.result > 0){
					//이미 사용중인 아이디 입니다.
					$('.resultId').css('color','red').text('이미 사용중인 아이디 입니다.')
				}else{
					//사용 가능한 아이디 입니다.
					$('.resultId').css('color', 'green').text('사용 가능한 아이디 입니다.')
				}
			}
		})
	})/* input uid end */
	
	$('input[name=nick]').focusout(function(){
		let value = $(this).val()
		let jsonData = {"nick":value}
		
		$.ajax({
			url:ctxRoot+'/user/checkNick',
			type:'GET',
			data:jsonData,
			dataType:'json',
			success: function (data){
				console.log(data)
				if(data.result > 0){
					//이미 사용 중
					$('.resultNick').css('color', 'red').text('사용 중인 닉네임 입니다.')
				}else{
					//사용 가능
					$('.resultNick').css('color', 'green').text('사용 가능한 닉네임 입니다.')
				}
			}
		})
	})
})