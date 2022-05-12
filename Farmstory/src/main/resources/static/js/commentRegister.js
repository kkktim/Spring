/**
 * 
 */

$(function(){
	$('.commentForm > form').submit(function(){
		
		let uid = $(this).children('input[name=uid]').val()
		let no = $(this).children('input[name=no]').val()
		let content = $(this).children('textarea[name=content]').val()
		
		let jsonData = {
			"uid":uid,
			"no":no,
			"content":content
		}
		
		console.log(jsonData)
		
		let ctxRoot = '/Farmstory'
		
		$.ajax({
			url: '/board/comment',
			type:'post',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data)
				//화면 랜더링
				let html = `<article>
								<span>
									<span class="nick">닉네임</span>
									<span class="rdate">22-05-20</span>
								</span>
								<textarea name="comment" readonly>댓글 샘플입니다.</textarea>
                    			<div>
			                        <a href="#" class="del">삭제</a>
			                        <a href="#" class="modify">수정</a>
                    			</div>
							</article>`
				let dom = $(html)
				
				dom.find('.nick').text(data.nick)
				dom.find('.rdate').text(data.rdate)
				dom.find('.textarea').text(data.content)
				dom.find('.del').attr('data-no', data.no)
				dom.find('.modify').attr('data-no', data.no)
				
				$('.commentList').append(dom)
				
				textarea.val("")
				$('.empty').remove()
			}
			
		})
		return false;
	})
})