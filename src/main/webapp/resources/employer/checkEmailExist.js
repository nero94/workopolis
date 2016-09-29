$(document).ready(function () {
 			$("#target").click(function () {
 			if (confirm("If you change the email you will have to login again")) {
 			
 				var email = $('#email').val();
 				$.ajax({
 					type: "POST",
 		            url : '/employer/check_email_exist_btn',
 		           data : "email=" + email,
 		            success : function(data) {
 						$('#result').html(data);
 						window.setTimeout(function() {
    					window.location.href = '';
}, 5000);
 		            }
 		        });
 				
 				} else {
  
}
 				
 				
 			});
 		});