/**
 * Created by user on 2015/8/24.
 */

backOfficeApp.controller("LoginController", LoginController);
function LoginController($scope, $translatePartialLoader, $translate, $log, LoginService, UserService, OrganizationService) {
    //$scope.loginUser = {
    //    loginId:"superAdmin",
    //    password:"123"
    //};

    $scope.getUserList = function () {
        UserService.getList().then(function (data) {
            $scope.userList = data;
            angular.forEach($scope.userList, function(value) {
                if(value.loginId=="superAdmin"){
                    $scope.selectedUser = value;
                }
            });
        });
    };

    $scope.onFastLoginClick = function () {
        $scope.fastLogin();
    };

    $scope.fastLogin = function () {
        LoginService.post("abc",{loginId:$scope.selectedUser.loginId}).then($scope.loginSuccess);
    };

    $scope.login = function () {
        LoginService.post($scope.loginUser).then($scope.loginSuccess);
    };

    $scope.loginSuccess = function (user) {
        if(user.organizationId){
            OrganizationService.one(user.organizationId).get().then(function (organization) {
                user.organization = organization;
                $scope.onLogin(user);
            });
        }else{
            $scope.onLogin(user);
        }
    };
    //login auto for test
    $scope.getUserList();
    //$scope.login();
}