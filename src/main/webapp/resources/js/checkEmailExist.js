$(document).ready(function () {
 			$("#email").blur(function () {
 				var email = $('#email').val();
 				$.ajax({
 					type: "POST",
 		            url : '/check_email_exist',
 		           data : "email=" + email,
 		            success : function(data) {
 						$('#result').html(data);
 		            }
 		        });
 				
 			});
 		});