function FindCandidates(x){
	var scope = $(x).parent().parent();
	var xhttp = new XMLHttpRequest();
	var url = "/recruiter/find-candidates?id=" + $(scope).find("#hiddenIdResult").val();
	alert(url);
	 console.log(url);
    xhttp.open('GET', url, true);
    xhttp.send();
}
