mainApp.controller("VacanciesController", function ($scope, $http, $location, $routeParams) {
    $http.get('html/vacancies/vacancies.json').
        success(function(data) {
            $scope.vacancies = data;
        });
          if ($routeParams.title) {
            $scope.title = $routeParams.title;
          }
          if ($routeParams.country) {
            $scope.country = $routeParams.country;
          }

        $scope.showVacancy = function(id){
          $location.path("/vacancy-description").search("id", id);
        }
});

mainApp.controller("VacancyController", function ($scope, $http, $routeParams) {
    $http.get('html/vacancy_description/vacancy.json').
        success(function(data) {
            $scope.vacancy = data[$routeParams.id];
        });
});
