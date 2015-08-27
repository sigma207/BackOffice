/**
 * Created by user on 2015/8/27.
 */
backendApp.controller("HouseRuleController", HouseRuleController);
function HouseRuleController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, TradeHouseRuleService, TradeAccountGroupService) {

    $scope.getTradeHouseRuleList = function () {
        TradeHouseRuleService.getList().then(function (data) {
            $scope.rowCollection = data;
        });
    };
}