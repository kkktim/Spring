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
		
		let commentList = $('.commentList')
		
		$.ajax({
			url: '/comment/register',
			type:'get',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data)
				
				let html = `<article class='comment'>
			   				<span>
		    					<span class="nick">닉네임</span>
		        				<span class="rdate">22-03-16</span>
		        			</span>
		        			<textarea name='comment' readonly>댓글내용</textarea>
		        			<div>
		        				<a href='#' class="del">삭제</a>
		        				<a href='#' class="modify" data-mode="r">수정</a>
		        			</div>
		        		</article>`;
		                			
				let dom = $(html);
				
				dom.find('.nick').text(data.nick)
				dom.find('.rdate').text(data.rdate)
				dom.find('textarea').text(data.content)
				dom.find('.del').attr('th:data-no', data.no)
				dom.find('.del').attr('th:data-ano', data.parent)
				dom.find('.modify').attr('th:data-no', data.no)
				
				commentList.append(dom)
			}
		})
		return false;
	})
})