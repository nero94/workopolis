//
// $(document).on('ready',function() {
// 	$(document).on('click','.edit-interview-result',function(){
// 		$(".identifiedSkills").prop("disabled", false);
// 		$(".ResultComment").prop("disabled", false);
// 	});
// 	$(document).on('click','.delete-interview-result',function(){
// 		$(".identifiedSkills").prop("disabled", true);
// 		$(".ResultComment").prop("disabled", true);
// 	});
// });

function SendInterviewResult(x){
		var scope = $(x).parent();
    	var xhttp = new XMLHttpRequest();
    	var url = "/add_interview_result?id=" + $(scope).find("#hiddenIdResult").val() + "&identifiedSkills=" + $(scope).find("#identifiedSkills").val() + "&comment=" + $(scope).find("#interviewerComment").val();
      console.log(url);
    	xhttp.open('GET', url, true);
    	xhttp.send();
			$(scope).find(".identifiedSkills").prop("disabled", true);
			$(scope).find(".ResultComment").prop("disabled", true);
			$(scope).find("#edit-interview-result").css("display", "none");
			$(scope).find("#delete-interview").css("display", "none");
    	}

function editResult(x){
	scope = $(x).parent();
	$(scope).find(".identifiedSkills").prop("disabled", false);
	$(scope).find(".ResultComment").prop("disabled", false);
}
