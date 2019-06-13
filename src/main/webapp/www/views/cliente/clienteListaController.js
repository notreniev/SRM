angular.module("App").controller("clienteListaController", function ($rootScope, $scope, $http, $routeParams, $location, SRMService, MessagesAPI) {

    $scope.clientes = [];

    $scope.error = {
        'hasError': false
    };

    var getLista = function () {
        $http.get(SRMService.urlBase("clientes/")).success(function (data) {
            $scope.clientes = data;
        }).error(function (data, error) {
            MessagesAPI.error(error.erros);
        });
    };

    $scope.checkAll = function () {
        if ($scope.selectedAll) {
            $scope.selectedAll = true;
        } else {
            $scope.selectedAll = false;
        }
        angular.forEach($scope.clientes, function (cliente) {
            cliente.selected = $scope.selectedAll;
        });
    };

    $scope.excluir = function () {
        if (confirm("Excluir cliente(s) selecionado(s)?")) {
            var ids = $("input[type=checkbox]:checked").serializeArray();
            if (ids.length > 0) {
                for (var i = 0; i < ids.length; i++) {
                    ids[i]["id"] = ids[i]["value"];
                    delete ids[i]["value"];
                    delete ids[i]["name"];
                }

                var params = SON.stringify(ids);

                console.log("IDs selecionados 1: " + params);

                $http.delete(SRMService.urlBase("clientes/"), params).success(function (data) {
                    MessagesAPI.success(data.mensagem);
                    $location.path("/clientes");
                }).error(function (data, error) {
                    MessagesAPI.error(error.erros);
                });

            } else {
                MessagesAPI.error("Selecione 1 ou mais registros");
            }
        }
    };

    getLista();
});