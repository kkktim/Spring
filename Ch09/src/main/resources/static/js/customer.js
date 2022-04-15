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
			"name":"레스트",
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
	$('.customer_Modify').click(function(){
		
		let custid = 0;		
		let customer = null;
		$.ajax({
			url:'/customer',
			type:'GET',
			dataType:'json',
			success: function(data){
				let customers = data
				customer = customers[customers.length - 1]
				custid = customer['custid']
				console.log(custid)
				
				let jsonData = {
					"name":"강감찬",
					"address":"대한민국 테스트111",
					"phone":"010-6297-1117"
				}
				
				$.ajax({
					url:'/customer/'+custid,
					type:'PUT',
					data:jsonData,
					dataType:'json',
					success: function(data){
						console.log(data)
					}
				})
				
			}
		})
		
		
		
	})
	$('.customer_Delete').click(function(){
		let custid = 16;
		$.ajax({
			url:'/customer/'+custid,
			type:'DELETE',
			dataType:'json',
			success: function(data){
				console.log(data)
			}
		})
	})
})
