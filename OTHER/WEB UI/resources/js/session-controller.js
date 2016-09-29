mainApp.controller("SessionController", function($scope, $sessionStorage) {
    if ($sessionStorage.user) {
      changeNav($sessionStorage.user.acc_type);
      changeAuthNav($sessionStorage.user.acc_type);
    }
    else {
      changeNav(null);
      changeAuthNav(null);
    }
});
