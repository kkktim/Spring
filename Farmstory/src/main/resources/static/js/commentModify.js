/**
 * 
 */
$(function(){
	
	//동적 생성 태그도 같이 선택
	$(document).on('click', '.commentList > .comment > div > .modify', function(e){
		e.preventDefault();
		
		let comment = $(this).parent().prev().val();
		let commentList = $('.commentList > article')
		
		let mode = $(this).attr('data-mode')
		
		//data-mode = r 로 들어올 때 수정모드전환
		if(mode == "r"){
			//this로 선택하지 않고 mod로 선택하면 댓글 전체가 선택됩니다.
			$(this).attr('data-mode', "w")
			
			//전체 댓글 수정,삭제 버튼 HIDE
			
			commentList.find('.del').hide()
			commentList.find('.modify').hide()
			
			//삭제버튼 HIDE
			$(this).prev().hide()
			//수정버튼 활성화 & 수정완료로 변경
			$(this).show()
			$(this).text('수정완료')
			//취소버튼 활성화
			$(this).next().show()
			//textarea 활성화	
			let textarea = $(this).parent().parent().find('textarea[name=comment]')
			textarea.attr('readonly', false).focus()
			textarea.css({
				'background':'white',
				'outline':'1px solid gray'
			})
					
			//취소버튼 클릭 - 동적 생성 태그도 이벤트 적용
			$(document).on('click', '.commentList > .comment > div > .cancel', function(e){
				e.preventDefault()
				
				//원래 댓글 복원
				$(this).parent().prev().val(comment);
				
				//전체 댓글 수정,삭제 버튼 HIDE				
				commentList.find('.del').show()
				commentList.find('.modify').show()
				
				//data-mode = r 로 변경
				$(this).prev().attr('data-mode', "r")
				
				//수정완료 버튼 수정으로 변경
				$(this).prev().text('수정')
				
				//취소 버튼 hide()
				$(this).hide()
				
				//삭제버튼 활성화
				$(this).prev().prev().show()
				//textarea 비활성화
				textarea.attr('readonly', true)
				textarea.css({
					'background':'transparent',
					'outline':'none'
				})
			})
		}else{
			//data-mode = w 로 들어올 때 수정완료
			let mod = $(this) 
			mod.attr('data-mode', "r")
			
			let no = $(this).attr('data-no')
			let textarea = $(this).parent().prev()
			let content = textarea.val()
			console.log(no)
			console.log(content)
			
			let jsonData = {
				"no":no,
				"content":content
			}
			
			let ctxRoot = "/Farmstory"
			//수정
			$.ajax({
				url:ctxRoot+'/comment/modify',
				type:'get',
				data:jsonData,
				dataType:'json',
				success:function(data){
					if(data > 0){
						//수정완료 버튼 수정으로 변경
						mod.text('수정')
						//취소 버튼 hide()
						mod.next().hide()
						//삭제버튼 활성화
						mod.prev().show()
						//textarea 비활성화
						textarea.attr('readonly', true)
						textarea.css({
							'background':'transparent',
							'outline':'none'
						})
						//전체 댓글 수정,삭제 버튼 HIDE
						commentList.find('.del').show()
						commentList.find('.modify').show()
					}//if end...
				}//success end...
			 })//ajax end...
		}//else end...
	})
})