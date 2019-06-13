angular.module('App').controller('clienteEdicaoController', function ($scope, $rootScope, $http, $location, SRMService, $routeParams, $route, $sce, MessagesAPI, $timeout) {

    $scope.cliente = {};
    $scope.result = {};
    $scope.riscos = ["A", "B", "C"];

    var getCliente = function (id) {
        if (id) {
            $http.get(SRMService.urlBase("clientes/" + id)).success(function (data) {
                console.log(data);
                $scope.cliente.idCliente = id;
                $scope.cliente.nome_cliente = data.nomeCliente;
                $scope.cliente.limite_credito = data.limiteCredito;
                $scope.cliente.risco = data.risco;
            }).error(function (error) {
                MessagesAPI.error(error.erros);
            });
        }
    };
    
    $scope.gravarDados = function (cliente) {

        var clienteId = "?id_cliente=" + cliente.idCliente;
        var nome_cliente = "&nome_cliente=" + cliente.nome_cliente.toUpperCase();
        var limite_credito = "&limite_credito=" + cliente.limite_credito;
        var risco = "&risco=" + cliente.risco;

        var params = clienteId+nome_cliente+limite_credito+risco;

        console.log('cliente: ', cliente);
        console.log('params: ', params);
        
        if ($routeParams.id) { // edição
            var id = $routeParams.id || 0;

            $http.put(SRMService.urlBase("clientes/") + id, params, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            }).success(function (data) {
                MessagesAPI.success(data.mensagem);
                $location.path("cliente/" + id);
            }).error(function (data, error) {
                MessagesAPI.error(error.erros);
            });
        } else { // inclusão
            $http.post(SRMService.urlBase("clientes/"), params, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            }).success(function (data) {
                MessagesAPI.success(data.mensagem);
                $timeout(function () {
                    $scope.myHeader = "How are you today?";
                    $location.path("clientes/");
                  }, 2000);

            }).error(function (data, error) {
                MessagesAPI.error(data.erros);
            });
            
        }

    };

    $scope.excluir = function (cliente) {
    	console.log(cliente);
        if ($routeParams.id) {
            if (confirm("Excluir o cliente ?")) {
                $http.delete(SRMService.urlBase("clientes/") + $routeParams.id).success(function (data) {
                    MessagesAPI.success(data.mensagem);
                    $location.path("/clientes");
                }).error(function (data, error) {
                    MessagesAPI.error(data.erros);
                });
            }
        }
    }

    getCliente($routeParams.id);

});