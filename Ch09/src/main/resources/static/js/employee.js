/**
 * 
 */
$(function(){
	$('.employee_List').click(function(){
		$.ajax({
			url:'/employee',
			type:'GET',
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
	
	$('.employee_Register').click(function(){
		let jsonData = {
			"uid":"rest",
			"name":"레스트",
			"gender":"1",
			"hp":"010-1212-1212",
			"email":"rest@rest.com",
			"pos":"과장",
			"dep": 103
		}
		
		$.ajax({
			url:'/employee',
			data: jsonData,
			type:'POST',
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
	$('.employee_Modify').click(function(){
		let jsonData = {
			"name":"레스트수정",
			"hp":"010-3434-3434",
			"email":"rest2@rest2.com",
			"pos":"차장",
			"dep":101
		}
		$.ajax({
			url:'/employee/rest',
			type:"PUT",
			data:jsonData,
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
	$('.employee_Delete').click(function(){
		$.ajax({
			url:'/employee/rest',
			type:'DELETE',
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
})
