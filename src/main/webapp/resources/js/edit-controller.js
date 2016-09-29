function edit(){
$("#employer_profile input").attr("readonly", null);
$("#change-logo").css("display", "block");
}

function save(){
$("#employer_profile input").attr("readonly", "");
$("#change-logo").css("display", "none");
}
