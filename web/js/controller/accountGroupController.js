/**
 * Created by user on 2015/8/27.
 */
backendApp.controller("AccountGroupController", AccountGroupController);//交易帳號屬性群組
function AccountGroupController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, TradeHouseRuleService, TradeAccountGroupService) {
    $translatePartialLoader.addPart("accountGroup");
    $translate.refresh();

    $scope.getTradeHouseRuleList = function () {
        TradeHouseRuleService.getList().then(function (data) {
            $scope.tradeHouseRuleList = data;
            if($scope.tradeHouseRuleList.length>0){
                $scope.selectedTradeHouseRule = $scope.tradeHouseRuleList[0];
            }
        });
    };

    $scope.getTradeAccountGroupList = function () {
        TradeAccountGroupService.getList().then(function (data) {
            $scope.rowCollection = data;
        })
    };

    $scope.queryClick = function () {
        $scope.getTradeAccountGroupList();
    };

    $scope.addClick = function () {
        $scope.currentAction = Action.Add;
        $scope.editObj = angular.copy($scope.selectedTradeHouseRule);
        //$scope.editObj.uplimit = 0.05;
        $scope.editObj.specialStockRule = 0;
        $scope.modalTitle = $translate.instant("tradeAccountGroup");
        $scope.open();
    };

    $scope.open = function () {
        $scope.editSize = "xg";
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'accountGroupEdit.html',
            controller: 'accountGroupEditCtrl',
            size: $scope.editSize,
            resolve: {
                editObj: function () {
                    return $scope.editObj;
                },
                title: function () {
                    return $scope.modalTitle;
                },
                currentAction: function () {
                    return $scope.currentAction;
                }
            }
        });
    };

    $scope.getTradeHouseRuleList();
    $scope.getTradeAccountGroupList();
}


backendApp.controller('accountGroupEditCtrl', function ($scope, $modalInstance, $log, TradeAccountGroupService, title, editObj, currentAction) {
    $scope.title = title;
    $scope.editObj = editObj;
    $scope.save = function () {
        switch (currentAction) {
            case Action.Add:
                TradeAccountGroupService.post( $scope.editObj).then(function (data) {
                    $modalInstance.close($scope.editObj);
                });
                break;
            case Action.Edit:
                $scope.editObj.put().then(function (data) {
                    $modalInstance.close();
                });
                break;
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});