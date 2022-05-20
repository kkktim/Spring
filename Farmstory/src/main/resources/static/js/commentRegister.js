/**
 * 
 */

$(function(){
	$('.commentForm > form').submit(function(){
		let inputUid = $(this).children('input[name=uid]')
		let inputNo =$(this).children('input[name=no]') 
		let textarea = $(this).children('textarea[name=content]')
		
		let uid = inputUid.val()
		let no = inputNo.val()
		let content = textarea.val()
		
		//공백 & null 체크
		if(content == "" || content == null){
			alert('내용을 입력해주세요')
			textarea.focus()
			return false;
		}
		
		let jsonData = {
			"uid":uid,
			"no":no,
			"content":content
		}
		
		let commentList = $('.commentList')
		let ctxRoot = "/Farmstory"
		$.ajax({
			url: ctxRoot+'/comment/register',
			type:'get',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				let html = `<article class='comment'>
			   				<span>
		    					<span class="nick">닉네임</span>
		        				<span class="rdate">22-03-16</span>
		        			</span>
		        			<textarea name='comment' readonly>댓글내용</textarea>
		        			<div>
		        				<a href="#" class="del">삭제</a>
                        		<a href="#" class="modify">수정</a>
                        		<a href="#" class="cancel">취소</a>
		        			</div>
		        		</article>`;
		                			
				let dom = $(html);
				
				dom.find('.nick').text(data.nick)
				dom.find('.rdate').text(data.rdate.substring(2, 10))
				dom.find('textarea').text(data.content)
				dom.find('.del').attr('data-no', data.no)
				dom.find('.del').attr('data-ano', data.parent)
				dom.find('.modify').attr('data-no', data.no)
				dom.find('.modify').attr('data-mode', "r")
				
				commentList.append(dom)
				
				//업로드 후 남아 있는 텍스트 지우기
				textarea.val("")
				//댓글 없다는 내용 지우기
				$('.empty').remove()
			}
		})
		return false;
	})
})