/**
 * Created by user on 2015/9/3.
 */
backOfficeApp.controller("AccountController",AccountController);
function AccountController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, UserService){
    $translatePartialLoader.addPart("account");
    $translate.refresh();

}