mainApp.controller("ManagerProfileContr", function ($scope, $http) {
    $http.get('html/manager-profile/employers.json').
        success(function(data) {
            $scope.vacancies = data;
        });
});

