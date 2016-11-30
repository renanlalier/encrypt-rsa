angular.module('app').controller('HomeController', HomeController);

HomeController.$inject = ['$http', 'apiEncryptionService'];

function HomeController($http, apiEncryptionService) {

    var vm = this;

    vm.encryptData = encryptData;
    vm.number;

    function encryptData() {

        apiEncryptionService.getKeyPublic(function(response) {

            vm.keyPublic = response;
            var cipher = apiEncryptionService.encryptData(vm.number, vm.keyPublic.encoded);

            $http({
                url: 'http://localhost:8801/card/register-card',
                method: 'POST',
                data: {'cipher' : cipher}
            });
        })
    }


}
