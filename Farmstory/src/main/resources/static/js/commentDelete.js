/**
 * 
 */
$(function(){
	let del = $('.comment > div > .del')
	del.click(function(e){
		e.preventDefault()
		
		let prevArticle = $(this).parent().parent()
		let no = $(this).attr('data-no')
		let parent = $(this).attr('data-ano')
		
		let jsonData = {
			"no":no,
			"parent":parent
		}
		
		let ctxRoot = "/Farmstory"
		let url = ctxRoot+"/comment/delete"
		
		$.get(url, jsonData, function(data){
				console.log(data)
				if(data > 0){
					alert('댓글이 삭제 되었습니다.')
				}
				prevArticle.remove()
		}, 'json')
	})
})