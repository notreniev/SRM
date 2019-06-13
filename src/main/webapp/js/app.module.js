angular.module('App', ['ngRoute', 'ngToast']);

angular.module('App').config(function Config($httpProvider) {

})

angular.module('App').factory('MessagesAPI', function (ngToast) {
    var _mySuccess = function (messages) {
        ngToast.success({
            content: messages,
            timeout: 7000
        });
    };

    var _myError = function (messages) {
        var retorno = "";

        if (Array.isArray(messages)) {
            if (messages[0] != null) {
                if (messages.length > 0) {
                    retorno = '<ul class="left">';
                    for (i = 0; i < messages.length; i++) {
                        retorno += '<li>' + messages[i] + "</li>";
                    }
                    retorno += '</ul>';
                }
            }
        }

        ngToast.dismiss();
        ngToast.danger({
            content: Array.isArray(messages) ? retorno : messages,
            dismissOnTimeout: true,
            dismissOnClick: true,
            dismissButton: true,
            timeout: 5000,
            dismissButtonHtml: '&times;'
        });
    };

    return {
        success: _mySuccess,
        error: _myError
    };

});