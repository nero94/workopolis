mainApp.controller("LoginController", function ($scope, $sessionStorage, $location, $http) {

          $scope.submitLogin = function()
          {
            $http.get('html/users.json').
                success(function(data) {
            for (var i = 0; i < data.length; i++){
              if (data[i].email == $scope.email && $scope.password == data[i].password){
              $sessionStorage.user = data[i];
              break;
            }
            }
            if (!$sessionStorage.user) alert("Wrong!!!");
            else {
              changeNav($sessionStorage.user.acc_type);
              changeAuthNav($sessionStorage.user);
              $location.path("/home");
            }
          });
}
          $scope.submitLogout = function(){
            delete $sessionStorage.user;
            changeNav(null);
            changeAuthNav($sessionStorage.user);
            $location.path("/home");
          }
});
