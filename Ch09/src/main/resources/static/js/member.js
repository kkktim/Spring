/**
 * 
 */
$(function(){
	$('.member_List').click(function(){
		$.ajax({
			url:'/member',
			type:'GET',
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
	
	$('.member_Register').click(function(){
		
		let jsonData = {
			"uid":"restcon",
			"name":"레스트",
			"hp":"010-1212-0909",
			"pos":"차장",
			"dep":103
		}
		
		console.log(jsonData)
		
		$.ajax({
			url:'/member',
			type:'POST',
			data: jsonData,
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
	
	$('.member_Modify').click(function(){
		let jsonData = {
			"name":'레스트테스트',
			"hp":"010-1313-0808",
			"pos":"사장",
			"dep":102
		}
		$.ajax({
			url:'/member/restcon',
			type:'PUT',
			data: jsonData,
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
	
	$('.member_Delete').click(function(){
		$.ajax({
			url:'/member/restcon',
			type:'DELETE',
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
})