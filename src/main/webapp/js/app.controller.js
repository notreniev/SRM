angular.module('App').controller('AppController', function ($scope, $http, $location, ngToast, SRMService) {

    var path = $location.path();
});


angular.module('App').factory('SRMService', function ($location) {

    var _parseTextToXML = function (txt) {
        if (window.DOMParser) {
            parser = new DOMParser();
            xmlDoc = parser.parseFromString(txt, "text/xml");
        } else { // Internet Explorer
            xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
            xmlDoc.async = false;
            xmlDoc.loadXML(txt);
        }

        return xmlDoc;
    }

    var _urlBase = function (action, hash) {
        var server = "";
        var local = false;
        
        var producao = false;
        
        if ($location.host() != "localhost") {
            producao = true;
        }
        
        if (producao) {
            server = $location.protocol() + "://" + $location.host() + "/srm/api/v1/";
        } else {
            server = $location.protocol() + "://" + $location.host() + ":8080/srm/api/v1/";
        }

        return server.concat(action)
    };
    
    return {
        urlBase: _urlBase,
        parseTextToXML: _parseTextToXML
    };

});
