angular.module('app').factory('apiEncryptionService', apiEncryptionService);

apiEncryptionService.$inject = ['$http'];

function apiEncryptionService($http) {

    var service = {
        getKeyPublic: getKeyPublic,
        encryptData: encryptData
    }

    return service;

    function getKeyPublic(callback) {
        $http.get('http://localhost:8801/security/generate-key-public')
            .success(function(response) {
                callback(response);
            });
    }

    function encryptData(data, keyPublic) {
        var rsa = new JSEncrypt();
        rsa.setPublicKey(keyPublic);
        var cipher = rsa.encrypt(data);
        return cipher;
    }

}
