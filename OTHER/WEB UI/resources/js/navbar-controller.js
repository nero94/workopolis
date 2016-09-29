mainApp.controller("NavBarController", function($scope) {
    // if ($scope.user) changeNav($scope.user.acc_type);
    // else changeNav(null);
});

var candidate_buttons = ["#home", "#about_us", "#candidate_profile", "#vacancies", "#contact_form", "#schedule"];
var recruiter_buttons = ["#home", "#recruiter_profile", "#recruiter_vacancies", "#recruiter_interview", "#candidate_search", "#schedule"];
var employer_buttons = ["#home", "#employer", "#add-vacancy", "#vacancies", "#candidate_search", "#contact_form", "#about_us", "#schedule"];
var default_buttons = ["#home", "#about_us", "#vacancies", "#contact_form", "#for_employer"];

function changeNav(acc_type) {
            switch (acc_type) {
        case "candidate":
            {
                $("#main-nav li").each(function() {
                    $(this).css("display", "none");
                });
                $(candidate_buttons.join(", ")).css("display", "block");
            }
            break;
        case "recruiter":
            {
                $("#main-nav li").each(function() {
                    $(this).css("display", "none");
                });
                $(recruiter_buttons.join(", ")).css("display", "block");
            }
            break;
        case "employer":
            {
                $("#main-nav li").each(function() {
                    $(this).css("display", "none");
                });
                $(employer_buttons.join(", ")).css("display", "block");
            }
            break;
        default:
            {
                    $("#main-nav li").each(function() {
                        $(this).css("display", "none");
                    });
                    $(default_buttons.join(", ")).css("display", "block")
            }
    }
}

function changeAuthNav(flag){
  if (flag){
    $("#logout").css("display", "block");
    $("#login").css("display", "none");
    $("#register").css("display", "none");
  }
  else {
    $("#logout").css("display", "none");
    $("#login").css("display", "block");
    $("#register").css("display", "block");
  }
}
