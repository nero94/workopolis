$(document).ready(function () {
 			$("#target").click(function () {
 			console.log("qqq");
 				var email = $('#email').val();
 				$.ajax({
 					type: "POST",
 		            url : '/manager/check_email_exist_btn',
 		           data : "email=" + email,
 		            success : function(data) {
 						$('#result').html(data);
 		            }
 		        });
 				
 			});
 		});