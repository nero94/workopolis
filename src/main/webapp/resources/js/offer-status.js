var statuses = {
        CREATED_BY_EMPLOYER : "Waiting for response",
        ACCEPTED_BY_CANDIDATE : "Accepted by Candidate",
        REJECTED_BY_CANDIDATE : "Rejected by Candidate",
        CANCELED_BY_EMPLOYER : "Canceled by Employer",
        CANDIDATE_ACCEPTED : "Candidate is Accepted"
    };

    $(".offer-state-option").each(function(){
      if ($(this).val() != "CREATED_BY_EMPLOYER") {
        $(this).parent().parent().parent().find("#candidate-reject").css("display", "none");
        $(this).parent().parent().parent().find("#candidate-accept").css("display", "none");
      }
      if ($(this).val() == "REJECTED_BY_CANDIDATE") {
        $(this).parent().parent().parent().find("#hired").css("display", "none");
      }
      if ($(this).val() == "CREATED_BY_EMPLOYER" || $(this).val() == "CANDIDATE_ACCEPTED") {
        $(this).parent().parent().parent().find("#hired").css("display", "none");
      }
      // if ($(this).val() == "ACCEPTED_BY_CANDIDATE") {
      //   $(this).parent().parent().parent().find("#cancel").css("display", "none");
      // }
      $(this).attr("label", statuses[$(this).val()]);
    });

    function ChangeVisibility(x){
    		var scope = $(x).parent();

        var xhttp = new XMLHttpRequest();
      	var url = "/delete_offer?id=" + $(scope).find("#hidden-offer-id").val();
      	console.log(url);
    		xhttp.open('GET', url, true);
    		xhttp.send();
        window.location.reload(true);
    }

    function SendOfferResult(x){
    	var scope = $(x).parent();

    	var xhttp = new XMLHttpRequest();
    	var url = "/employer/change-offer-state?id=" + $(scope).find("#hidden-offer-id").val() + "&state=CANDIDATE_ACCEPTED";
    	console.log(url);
  		xhttp.open('GET', url, true);
  		xhttp.send();

      window.location.reload(true);
  		window.location.reload(true);
    }

    function RespondToOffer(x){
        var scope = $(x).parent();

        var xhttp = new XMLHttpRequest();
        var url = "/candidate/response-offer-state?id=" + $(scope).find("#hidden-offer-id").val() + "&state=ACCEPTED_BY_CANDIDATE"
        xhttp.open('GET', url, true);
        xhttp.send();

        window.location.reload(true);
        window.location.reload(true);
    }

    function RejectOffer(x){
        var scope = $(x).parent();
        var xhttp = new XMLHttpRequest();
        var url = "/candidate/response-offer-state?id=" + $(scope).find("#hidden-offer-id").val() + "&state=REJECTED_BY_CANDIDATE";
        xhttp.open('GET', url, true);
        xhttp.send();

        window.location.reload(true);
        window.location.reload(true);
    }
