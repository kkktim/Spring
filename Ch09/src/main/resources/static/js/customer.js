/**
 * 
 */
$(function(){
	$('.customer_List').click(function(){
		$.ajax({
			url:'/customer',
			type:'GET',
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
	$('.customer_Register').click(function(){
		let jsonData = {
			"name":"레스트제이피에이",
			"address":"대한민국 부산",
			"phone":"010-6297-4592"
		}
		$.ajax({
			url:'/customer',
			type:'POST',
			data:jsonData,
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
	$('.customer_Modify').click(function(){})
	$('.customer_Delete').click(function(){})
})
