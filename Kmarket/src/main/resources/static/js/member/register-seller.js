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
let isCompanyOk  = false;
let isBizRegNumOk  = false;
let isCommRepNumOk = false;
let isTelOk    = false;
let isFaxOk    = false;
let isEmailOk    = false;
let isManagerHpOk    = false;
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
					$('.msgSId').css('color', 'green').text(' 사용 가능한 아이디 입니다.');
						isUidOk = true;
					if(data.result > 0){
						$('.msgSId').css('color', 'red').text('이미 사용중인 아이디 입니다.');
						isUidOk = false;
					}
				}else{
					$('.msgSId').css('color', 'black').text('영문, 숫자로 4~12자까지 설정해 주세요.');
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
				$('.msgPass2').css('color', 'green').text('비밀번호가 일치 합니다.');
				isPassOk = true;
			}else if(pw2.length == 0){
				$('.msgPass2').css('color', 'black').text('비밀번호 재입력');
			}else if(pw1 != pw2){	
				$('.msgPass2').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
			 	isPassOk = false;
			}
		}else{
			$('.msgPass').css('color', 'black').text('영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해 주세요.');
			isPassOk = false;
		}
	});
	
	$('input[name=company]').keyup(function(){

		let value = $(this).val();
		let jsonData = {"company":value}
		
		$.ajax({
			url: '/member/checkCompany',
			type: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				
				console.log(data);
				if(data.result > 0){
					$('.msgCompany').css('color', 'red').text('이미 등록된 회사명입니다.');
					isCompanyOk = false;
				}else{
					$('.msgCompany').css('color', 'black').text('(주)포함 입력, 예) (주)케이마켓');
					isCompanyOk = true;
				}
				
			}
		});
	});
	
	$('input[name=ceo]').keyup(function(){
		let ceo = $('input[name=ceo]').val();
		
		if(reName.test(ceo)){
			$('.msgCeo').css('color', 'green').text('사용 가능한 이름입니다.');
			isNameOk = true;
		}else{
			$('.msgCeo').css('color', 'red').text('이름을 한글로 2자이상 입력하세요');
			isNameOk = false;
			
		}
	});
	
	$('input[name=bizRegNum]').keyup(function(){
		
		let value = $(this).val();
		let jsonData = {"bizRegNum":value}
		
		$.ajax({
			url: '/member/checkBizRegNum',
			type: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				
				console.log(data);
				if(data.result > 0){
					$('.msgCorp').css('color', 'red').text('이미 등록된 사업자등록번호입니다.');
					isBizRegNumOk  = false;
				}else{
					$('.msgCorp').css('color', 'black').text('- 표시 포함 12자리 입력, 예) 123-45-67890');
					isBizRegNumOk  = true;
				}
				
			}
		});
	});
	
	$('input[name=commRepNum]').keyup(function(){
		
		let value = $(this).val();
		let jsonData = {"commRepNum":value}
		
		$.ajax({
			url: '/member/checkCommRepNum',
			type: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				
				console.log(data);
				if(data.result > 0){
					$('.msgOnline').css('color', 'red').text('이미 등록된 통신판매업신고번호입니다.');
					isCommRepNumOk = false;
				}else{
					$('.msgOnline').css('color', 'black').text(' - 표시 포함, 예) 강남-12345, 제 1-01-23-4567호, 2017-경기성남-0011');
					isCommRepNumOk = true;
				}
				
			}
		});
	});
	
	$('input[name=tel]').keyup(function(){
		
		let value = $(this).val();
		let jsonData = {"tel":value}
		
		$.ajax({
			url: '/member/checkTel',
			type: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				
				console.log(data);
				if(data.result > 0){
					$('.msgTel').css('color', 'red').text('이미 사용중인 전화번호 입니다.');
					isTelOk    = false;
				}else{
					$('.msgTel').css('color', 'black').text('- 표시 포함, 지역번호 포함, 예) 02-234-1234');
					isTelOk    = true;
				}
				
			}
		});
	});
	
	$('input[name=fax]').keyup(function(){
		
		let value = $(this).val();
		let jsonData = {"fax":value}
		
		$.ajax({
			url: '/member/checkFax',
			type: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				
				console.log(data);
				if(data.result > 0){
					$('.msgFax').css('color', 'red').text('이미 사용중인 팩스번호 입니다.');
					isFaxOk    = false;
				}else{
					$('.msgFax').css('color', 'black').text(' - 표시 포함, 지역번호 포함, 예) 02-234-1234');
					isFaxOk    = true;
				}
				
			}
		});
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
					$('.msgEmail').css('color', 'red').text('이미 사용중인 email 입니다.');
					isEmailOk    = false;
				}else{
					$('.msgEmail').css('color', 'black').text('');
					isEmailOk    = true;
				}
				
			}
		});
	});
	
	$('input[name=managerHp]').keyup(function(){
		
		let value = $(this).val();
		let jsonData = {"managerHp":value}
		
		$.ajax({
			url: '/member/checkManagerHp',
			type: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				
				console.log(data);
				if(data.result > 0){
					$('.msgHp').css('color', 'red').text('이미 사용중인 휴대폰번호 입니다.');
					isManagerHpOk    = false;
				}else{
					$('.msgHp').css('color', 'black').text('- 포함 13자리를 입력하세요.');
					isManagerHpOk    = true;
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
			isAddr2Ok=true;
		}
	
	});
	
	
	
});
	
$(function(){
	$('#member-register-seller > form').submit(function(){
		// form 전송할때 실행되는 이벤트
		
		if(!isUidOk){
			alert('아이디를 확인하세요.');
			return false;
		}
		
		if(!isPassOk){
			alert('비밀번호를 확인하세요.');
			return false;
		}
		
		if(!isCompanyOk){
			alert('회사명을 확인하세요.');
			return false;
		}
		
		if(!isBizRegNumOk){
			alert('사업자등록번호를 확인하세요.');
			return false;
		}
		
		if(!isCommRepNumOk){
			alert('통신판매업신고번호를 확인하세요.');
			return false;
		}
		if(!isTelOk){
			alert('전화번호를 확인하세요.');
			return false;
		}
		if(!isFaxOk){
			alert('팩스번호를 확인하세요.');
			return false;
		}
		
		if(!isEmailOk){
			alert('이메일을 다시 확인하세요.');
			return false;
		}
		
		if(!isManagerHpOk){
			alert('담당자 휴대폰번호를 다시 확인하세요.');
			return false;
		}
		
		if(!isAddr2Ok){
			alert('상세주소를 확인하세요.');
			return false;
		}
		
		return true; // 최종 폼 전송
	});
	
});