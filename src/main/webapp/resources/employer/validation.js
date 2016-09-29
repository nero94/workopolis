function validateForm(inputvalue) {
	var password1 = document.forms["frm"]["password1"].value;
	var password2 = document.forms["frm"]["password2"].value;	
	
	valid=true;	
	
	if (password1.length == 0) {
		var input = document.getElementById('password1');
		input.style.border = "thin solid red";
		valid= false;
	}	
	
	if (password2.length == 0) {
		var input = document.getElementById('password2');
		input.style.border = "thin solid red";	
		valid= false;
	}	
	
	
	if (password1.length != 0) {
		var input = document.getElementById('password1');
		input.style.border = "";
	}
	if (password2.length != 0) {
		var input = document.getElementById('password2');
		input.style.border = "";
	}
	
		if (password1 != password2) {
		var input = document.getElementById('password1');
		var input2 = document.getElementById('password2');
		input.style.border = "thin solid red";
		input2.style.border = "thin solid red";
		valid= false;
	}
	
	if ((password1 == password2) && (password1.length != 0) && (password2.length != 0)) {
		var input = document.getElementById('password1');
		var input2 = document.getElementById('password2');
		input.style.border = "";
		input2.style.border = "";
	}
		
	
	return valid;	

}