/**
 * 
 */
// 데이터 형식을 검사하기 위한 정규표현식(Regular Expression)
let reUid  = /^[a-z]+[a-z0-9]{3,9}$/;
let rePass = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{5,}$/;
let reName = /^[가-힣]{2,10}$/;
let reNick = /^[a-z가-힣0-9]{2,5}$/;

// 최종 유효성 검사에 사용될 상태변수
let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isEmailOk = false;
let isHpOk    = false;
let isZipOk    = false;
let isAddr1Ok    = false;
let isAddr2Ok    = false;
 
 $(function(){
	$('input[name=uid]').keyup(function(){
		
		let value = $(this).val();
		let jsonData = {"uid":value}
		let uid = $('input[name=uid]').val();
		
		$.ajax({
			url: '/member/checkUid',
			type: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				
				console.log(data);
				if(reUid.test(uid)){
					$('.msgId').css('color', 'green').text(' 사용 가능한 아이디입니다.');
						isUidOk = true;
					if(data.result > 0){
						$('.msgId').css('color', 'red').text('이미 사용중인 아이디 입니다.');
						isUidOk = false;
					}
				}else{
					$('.msgId').css('color', 'black').text('영문, 숫자로 4~12자까지 설정해 주세요.');
						isUidOk = false;
				}
			}
			
		});
	});
	
	$('input[name=pass], input[name=pass2]').keyup(function(){
		let pw1 = $('input[name=pass]').val(); 
		let pw2 = $('input[name=pass2]').val(); 
		
		
		if(rePass.test(pw1)){
			$('.msgPass').css('color', 'green').text('사용가능한 비밀번호입니다.');
			isPassOk = true;
			if(pw1 == pw2){
				$('.msgPass2').css('color', 'green').text('비밀번호가 일치합니다.');
				isPassOk = true;
			}else if(pw2.length == 0){
				$('.msgPass2').css('color', 'black').text('비밀번호 재입력');
			}else if(pw1 != pw2){	
				$('.msgPass2').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
			 	isPassOk = false;
			}
		}else{
			$('.msgPass').css('color', 'red').text('영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해 주세요.');
			isPassOk = false;
		}
	});
	
	$('input[name=name]').keyup(function(){
		let name = $('input[name=name]').val();
		
		if(reName.test(name)){
			$('.msgName').css('color', 'green').text('사용 가능한 이름입니다.');
			isNameOk = true;
		}else{
			$('.msgName').css('color', 'red').text('이름을 한글로 2자이상 입력하세요');
			isNameOk = false;
			
		}
	});
	
	$('input[name=email]').keyup(function(){
		
		let value = $(this).val();
		let jsonData = {"email":value}
		
		$.ajax({
			url: '/member/checkEmail',
			type: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				
				console.log(data);
				if(data.result > 0){
					$('.msgEmail').css('color', 'red').text('이미 사용중인 email입니다.');
					isEmailOk    = false;
				}else{
					$('.msgEmail').css('color', 'black').text('');
					isEmailOk    = true;
				}
				
			}
		});
	});
	
	$('input[name=hp]').keyup(function(){
		
		let value = $(this).val();
		let jsonData = {"hp":value}
		
		$.ajax({
			url: '/member/checkHp',
			type: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				
				console.log(data);
				if(data.result > 0){
					$('.msgHp').css('color', 'red').text('이미 사용중인 휴대폰번호입니다.');
					isHpOk = false;
				}else if(value.length <= 12){
					$('.msgHp').css('color', 'black').text('- 포함 13자리를 입력하세요.');
					isHpOk = false;
				}else{
					$('.msgHp').css('color', 'green').text('사용가능한 휴대폰번호입니다.');
					isHpOk = true;
				}
				
			}
		});
	});
	
	//주소 
	$('input[name=addr2]').focusout(function(){
		
		let value = $(":input[name=addr1][readonly='readonly']").val();
		let addr2 = $('input[name=addr2]').val();
		
		
		console.log(value)
		
		if(!value){
			alert('주소 검색을 하세요.')
			$('input[name=zip]').click()
		}
		
		
		console.log(addr2)
		
		if(!addr2){
			alert('상세 주소를 입력하세요.')
			isAddr2Ok = false;
		}else{
			isAddr2Ok = true;
		}
	
	
	});
	
	
	
});
	
$(function(){
	$('#member-register > form').submit(function(){
		// form 전송할때 실행되는 이벤트
		if(!isUidOk){
			alert('아이디를 확인하세요.');
			return false;
		}
		if(!isPassOk){
			alert('비밀번호를 확인하세요.');
			return false;
		}
		if(!isNameOk){
			alert('이름을 확인하세요.');
			return false;
		}
		if(!isEmailOk){
			alert('이메일을 확인하세요.');
			return false;
		}
		if(!isHpOk){
			alert('휴대폰번호를 확인하세요.');
			return false;
		}
		if(!isAddr2Ok){
			alert('상세주소를 확인하세요.');
			return false;
		}
		return true; // 최종 폼 전송
	});
	
});