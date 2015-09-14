/**
 * Created by user on 2015/9/14.
 */
backOfficeApp.controller("TradeLoginAccountController", TradeLoginAccountController);
function TradeLoginAccountController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, LoginAccountService, SystemCategoryService, SystemTradeRuleService, TradeGroupService) {
    $translatePartialLoader.addPart("account");
    $translate.refresh();

    $scope.init = function () {
        $scope.getList();
    };

    $scope.queryClick = function () {
        $scope.getList();
    };

    $scope.getList = function () {
        LoginAccountService.getList().then(function (data) {
            $scope.rowCollection = data;
        });
    }
}