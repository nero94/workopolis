mainApp.controller("MainPageButtonController", function ($scope, $location) {
  $scope.find = function(){
  $location.path("/vacancies").search("title", $scope.jobTitle);
  $location.path("/vacancies").search("country", $scope.country);
}
});
