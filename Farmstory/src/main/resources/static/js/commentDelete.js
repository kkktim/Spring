/**
 * 
 */
$(function(){
	//동적 생성 태그도 이벤트 적용
	$(document).on('click', '.comment > div > .del', function(e){
		e.preventDefault()
		
		let prevArticle = $(this).parent().parent()
		let no = $(this).attr('data-no')
		console.log(no)
		let parent = $(this).attr('data-ano')
		
		let jsonData = {
			"no":no,
			"parent":parent
		}
		
		let ctxRoot = "/Farmstory"
		let url = ctxRoot+"/comment/delete"
		
		$.get(url, jsonData, function(data){
				if(data > 0){
					alert('댓글이 삭제 되었습니다.')
				}
				prevArticle.remove()
		}, 'json')
	})
})