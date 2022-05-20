/**
 * 
 */
// Validation 수행(유효성 검증)
// 데이터 형식을 검사하기 위한 정규표현식(Regular Expression) <-- 검색 활용
let reUid  = /^[a-z]+[a-z0-9]{3,9}$/;
let rePass = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{5,}$/;
let reName = /^[가-힣]{2,10}$/;
let reNick = /^[a-z가-힣0-9]{2,5}$/;
let reEmail = /^[a-zA-Z0-9_!#$%&'\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$/;
let reHp = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;

// 최종 유효성 검사에 사용될 상태변수
let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isNickOk  = false;
let isEmailOk = false;
let isHpOk    = false;

$(function(){			
	// 아이디 중복체크
	let ctxRoot = "/Farmstory"
	
	$('input[name=uid]').focusout(function () {
		let uid = $(this).val()
		let jsonData = {"uid":uid}
				
		if(uid.length< 4){
			return false;
		}
				
		$.ajax({
			url: ctxRoot+'/user/checkUid',
			type:'get',
			data: jsonData,
			dataType: 'json',
			success: function (data) {
				if(data.result > 0){
					$('.resultId').css('color', 'red').text('이미 사용중인 아이디 입니다.');
					isUidOk = false;
				}else{
					// 아이디 유효성 검사
					if(reUid.test(uid)){
						$('.resultId').css('color', 'green').text('사용 가능한 아이디 입니다.');
						isUidOk = true;
					}else{
						$('.resultId').css('color', 'red').text('아이디는 영문 소문자, 숫자 조합 4 ~ 10자까지 입니다.');
						isUidOk = false;
					}
				}
			}
		})
	})
	
	// 비밀번호 유효성 검사
	$('input[name=pass2]').focusout(function(){
		
		let pw1 = $('input[name=pass]').val(); 
		let pw2 = $('input[name=pass2]').val(); 
		
		if(pw1 == pw2){
			
			if(rePass.test(pw2)){
				$('.resultPass').css('color', 'green').text('비밀번호가 일치 합니다.');
				isPassOk = true;
			}else{
				$('.resultPass').css('color', 'red').text('비밀번호는 영문, 숫자, 특수문자 최소 4이상 이어야 합니다.');
				isPassOk = false;
			}
		}else{
			$('.resultPass').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
			isPassOk = false;
		}
	});
	
	// 이름 유효성 검사
	$('input[name=name]').focusout(function(){
		
		let name = $(this).val();
		
		if(reName.test(name)){
			$('.resultName').text('');
			isNameOk = true;
		}else{
			$('.resultName').css('color', 'red').text('이름은 한글로 2자 이상이어야 합니다.');
			isNameOk = false;
		}
	});
	
	// 닉네임 중복체크
	$('input[name=nick]').focusout(function(){
		
		let nick = $(this).val();
		let jsonData = {"nick": nick};
		
		$.get(ctxRoot+'/user/checkNick', jsonData, function(data){
			if(data.result > 0){
				$('.resultNick').css('color', 'red').text('이미 사용중인 별명입니다.');
				isNickOk = false;
			}else{
				// 닉네임 유효성 검사
				if(reNick.test(nick)){
					$('.resultNick').css('color', 'green').text('사용 가능한 별명입니다.');
					isNickOk = true;
				}else{
					$('.resultNick').css('color', 'red').text('별명은 영문 소문자, 숫자, 한글 조합 2 ~ 5자까지 입니다.');
					isNickOk = false;
				}
			}
		});
	});
	
	
	// 이메일 중복체크
	$('input[name=email]').focusout(function(){
		
		let email = $(this).val();
		let jsonData = {"email": email};
		
		$.get(ctxRoot+'/user/checkEmail', jsonData, function(data){
			console.log(data.result)
			if(data.result > 0){
				$('.resultEmail').css('color', 'red').text('이미 사용중인 이메일입니다.');
				isEmailOk = false;
			}else{
				if(reEmail.test(email)){
					$('.resultEmail').css('color', 'green').text('사용 가능한 이메일입니다.');
					isEmailOk = true;
				}else{
					$('.resultEmail').css('color', 'red').text('형식에 맞지 않는 이메일 입니다.');
					isEmailOk = false;
				}
				
			}
		});
	});
	
	
	// 휴대폰 중복체크
	$('input[name=hp]').focusout(function(){
		
		let hp = $(this).val();
		let jsonData = {"hp": hp};
		
		$.get(ctxRoot+'/user/checkHp', jsonData, function(data){
			
			if(data.result > 0){
				$('.resultHp').css('color', 'red').text('이미 사용중인 휴대폰번호 입니다.');
				isHpOk = false;
			}else{
				if(reHp.test(hp)){
					$('.resultHp').css('color', 'green').text('사용 가능한 휴대폰번호 입니다.');
				isHpOk = true;	
				}else{
					$('.resultHp').css('color', 'red').text('형식에 맞지 않는 번호 입니다.');
				isHpOk = true;
				}
				
			}
		});
	});
	
});
 
 
 	
 $(function(){
	$('.register > form').submit(function(){
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
			alert('이름은 한글 최소 2자 이어야 합니다.');
			return false;
		}
		
		if(!isNickOk){
			alert('별명을 다시 확인하시기 바랍니다.');
			return false;
		}
		
		if(!isEmailOk){
			alert('이메일을 다시 확인하시기 바랍니다.');
			return false;
		}
		
		if(!isHpOk){
			alert('휴대폰을 다시 확인하시기 바랍니다.');
			return false;
		}
		
		return true; // 최종 폼 전송
	});
	
 });