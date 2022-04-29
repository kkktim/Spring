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
			url:ctxRoot+'/board/comment',
			type:'post',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data)
			}
			
		})
	})
})