/**
 * 
 */
 		$(function () {
			//User
			$('.user_List').click(function () {
				$.ajax({
					url:'/user',
					type:'GET',
					dataType:'json',
					success: function (data) {
						console.log(data)
					}
				})
			})//list end
			
			$('.user_Register').click(function () {
				let jsonData = {
						"uid": "rest1",
						"pass": "1234",
						"name": "홍길동",
						"hp": "010-1212-1999",
						"age": 34
				}
				$.ajax({
					url:'/user',
					type:'POST',
					data: jsonData,
					dataType:'json',
					success: function (data) {
						console.log(data)
					}
				})
			})//register end
			
			$('.user_Modify').click(function () {
				let jsonData = {
						"name": "홍길동",
						"hp": "010-1212-2222",
						"age": 38,
				}
				$.ajax({
					url:'/user/rest1',
					//수정은 PUT
					type:'PUT',
					data: jsonData,
					dataType:'json',
					success: function (data) {
						console.log(data)
					}
				})
			})
			
			$('.user_Delete').click(function () {
				
				$.ajax({
					url:'/user/rest1',
					type:'DELETE',
					dataType:'json',
					success: function (data) {
						console.log(data)
					}
				})
			})
		})