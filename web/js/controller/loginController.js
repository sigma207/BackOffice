/**
 * Created by user on 2015/8/24.
 */

backendApp.controller("LoginController", LoginController);
function LoginController($scope, $translatePartialLoader, $translate, $log, LoginService, OrganizationService) {
    $scope.loginUser = {
        login_id:"superAdmin",
        password:"123"
    };
    $scope.login = function () {
        LoginService.post($scope.loginUser).then(function (user) {
            if(user.organizationId){
                OrganizationService.one(user.organizationId).get().then(function (organization) {
                    user.organization = organization;
                });
            }else{
                $log.info("onLogin!!");
                $scope.onLogin(user);
            }
        });
    };
    //login auto for test
    $scope.login();
}