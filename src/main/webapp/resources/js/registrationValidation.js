function validateForm(inputvalue) {
	var password1 = document.forms["frm"]["password1"].value;
	var password2 = document.forms["frm"]["password2"].value;	
	var email = document.forms["frm"]["email"].value;	
	
	valid=true;	
	
	if (password1.length == 0) {
		var input = document.getElementById('password1');
		input.style.borderBottom = "2px solid red";
		valid= false;
	}	
	
	if (password2.length == 0) {
		var input = document.getElementById('password2');
		input.style.borderBottom = "2px solid red";	
		valid= false;
	}
	
	if (email.length == 0) {
		var input = document.getElementById('email');
		input.style.borderBottom = "2px solid red";	
		valid= false;
	}	
	
	
	if (password1.length != 0) {
		var input = document.getElementById('password1');
		input.style.borderBottom = "2px solid #ebebeb";
	}
	if (password2.length != 0) {
		var input = document.getElementById('password2');
		input.style.borderBottom = "2px solid #ebebeb";
	}
	if (email.length != 0) {
		var input = document.getElementById('email');
		input.style.borderBottom = "2px solid #ebebeb";
	}
	
	if (password1 != password2) {
		var input = document.getElementById('password1');
		var input2 = document.getElementById('password2');
		input.style.borderBottom = "2px solid red";
		input2.style.borderBottom = "2px solid red";
		valid= false;
	}
	
	if ((password1 == password2)  && (password1.length != 0) && (password1.length != 0)) {
		var input = document.getElementById('password1');
		var input2 = document.getElementById('password2');
		input.style.borderBottom = "2px solid #ebebeb";
		input2.style.borderBottom = "2px solid #ebebeb";
	}
		
	
	return valid;	

}