/**
 * Created by user on 2015/8/27.
 */
backendApp.controller("HouseRuleController", HouseRuleController);
function HouseRuleController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, TradeHouseRuleService, TradeAccountGroupService) {
    $translatePartialLoader.addPart("houseRule");
    $translate.refresh();
    $scope.getTradeHouseRuleList = function () {
        var params = {
            houseId:"H001"
        };
        TradeHouseRuleService.getList(params).then(function (data) {
            $scope.rowCollection = data;
        });
    };

    $scope.getTradeHouseRuleList();
}