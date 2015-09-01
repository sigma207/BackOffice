/**
 * Created by user on 2015/8/27.
 */
backendApp.controller("AccountGroupController", AccountGroupController);//交易帳號屬性群組
function AccountGroupController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, TradeHouseRuleService, TradeGroupService) {
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
        TradeGroupService.getList().then(function (data) {
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
        $scope.editObj.groupName = "ABC";
        $scope.editObj.specialStockRule = 0;
        $scope.editObj.initialMargin = 100;
        $scope.editObj.maintainMargin = 200;
        $scope.editObj.buyCommission = 10;
        $scope.editObj.sellCommission = 20;
        $scope.editObj.marginRate = 5;
        $scope.editObj.maxLots = 3;
        $scope.editObj.ownerId = $scope.loginUser.userId;
        $scope.modalTitle = $translate.instant("tradeAccountGroup");
        $scope.showModal();
    };

    $scope.editClick = function (row) {
        $scope.currentAction = Action.Edit;
        $scope.editObj = Restangular.copy(row);
        $scope.modalTitle = $translate.instant("tradeAccountGroup");
        $scope.showModal();
    };

    $scope.deleteClick = function (row) {
        row.remove().then(function () {
            $scope.getTradeAccountGroupList();
        });
    };

    function ModalController($scope){
        $scope.save = function () {
            switch ($scope.currentAction) {
                case Action.Add:
                    TradeGroupService.post( $scope.editObj).then(function (data) {
                        $scope.getTradeAccountGroupList();
                        $scope.hideModal();
                    });
                    break;
                case Action.Edit:
                    $scope.editObj.put().then(function (data) {
                        $scope.getTradeAccountGroupList();
                        $scope.hideModal();
                    });
                    break;
            }
        };
    }

    var modal = $modal({
        scope: $scope,
        controller: ModalController,
        templateUrl:"accountGroupEdit.html",
        show:false
    });

    $scope.showModal = function() {
        modal.$promise.then(modal.show);
    };
    $scope.hideModal = function() {
        modal.$promise.then(modal.hide);
    };

    $scope.getTradeHouseRuleList();
    $scope.getTradeAccountGroupList();
}