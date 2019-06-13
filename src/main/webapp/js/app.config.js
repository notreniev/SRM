angular
    .module('App')
    .config(['ngToastProvider', function (ngToast) {
        ngToast.configure({
            verticalPosition: 'top',
            horizontalPosition: 'center',
            additionalClasses: 'alert-dismissable'
        });
  }]);

angular.module('App').config(function ($routeProvider) {	

    $routeProvider.when('/', {
        templateUrl: 'www/views/cliente/clienteListaView.html',
        controller: 'clienteListaController'
    });
    $routeProvider.when('/clientes', {
        templateUrl: 'www/views/cliente/clienteListaView.html',
        controller: 'clienteListaController'
    });
    $routeProvider.when('/cliente', {
        templateUrl: 'www/views/cliente/clienteEdicaoView.html',
        controller: 'clienteEdicaoController'
    });
    $routeProvider.when('/cliente/:id', {
        templateUrl: 'www/views/cliente/clienteEdicaoView.html',
        controller: 'clienteEdicaoController'
    });
    $routeProvider.other
});