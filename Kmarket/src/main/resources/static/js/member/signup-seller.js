/**
 * 
 */
 
 // 이용약관 동의
 $(function(){
	$('input[name=agree]').click(function(){
		
		let isChecked1 = $('input[name=agree1]').is(':checked');
		let isChecked2 = $('input[name=agree2]').is(':checked');
		let isChecked3 = $('input[name=agree3]').is(':checked');
		let isChecked4 = $('input[name=agree4]').is(':checked');
		
		if(!isChecked1){
			alert('이용약관에 동의 체크를 하셔야 합니다.');
			return false;
		}else if(!isChecked2){
			alert('전자금융거래 이용약관에 동의 체크를 하셔야 합니다.');
			return false;	
		}else if(!isChecked3){
			alert('개인정보 수집동의에 동의 체크를 하셔야 합니다.');
			return false;	
		}else if(!isChecked4){
			alert('전자세금계산서 이용약관에 동의 체크를 하셔야 합니다.');
			return false;	
		}else{
			return $(location).attr("href", "/member/register-seller?type=seller");
		}
		
	});
});