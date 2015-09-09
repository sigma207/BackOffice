/**
 * Created by user on 2015/9/9.
 */
backOfficeApp.controller("TradeGroupController", TradeGroupController);//交易帳號屬性群組
function TradeGroupController($scope, $modal, $log, $translatePartialLoader, $translate, $alert, Restangular, SystemCategoryService, SystemTradeRuleService, TradeGroupService) {
    $translatePartialLoader.addPart("group");
    $translate.refresh();

    $scope.getTradeRuleList = function () {
        SystemTradeRuleService.getList().then(function (data) {
            $scope.tradeRuleList = data;
            if ($scope.tradeRuleList.length > 0) {
                $scope.selectedTradeRule = $scope.tradeRuleList[0];
            }
        });
    };

    $scope.getTradeGroupList = function () {
        TradeGroupService.getList().then(function (data) {
            $scope.rowCollection = data;
        })
    };

    $scope.queryClick = function () {
        $scope.getTradeGroupList();
    };

    $scope.addClick = function () {
        $scope.currentAction = Action.Add;
        $scope.editObj = angular.copy($scope.selectedTradeRule);
        //$scope.editObj.exchangeId = "*";
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
        //var myAlert = $alert({title: 'Holy guacamole!', content: 'Best check yo self, you\'re not looking too good.', placement: 'top', type: 'danger', keyboard: true, show: false});
        row.remove().then(
            function () {
                $scope.getTradeGroupList();
            }
        );
    };

    function ModalController($scope) {
        var i, count;
        $scope.getSystemCategoryList = function () {
            SystemCategoryService.getList().then(function (data) {
                $scope.systemCategoryList = data;
                if ($scope.systemCategoryList.length > 0) {
                    for (i = 0, count = $scope.systemCategoryList.length; i < count; i++) {
                        if ($scope.editObj.category == $scope.systemCategoryList[i].category) {
                            $scope.editObj.systemCategory = $scope.systemCategoryList[i];
                        }
                    }
                    switch ($scope.currentAction) {
                        case Action.Add:
                            $scope.systemCategoryChange();
                            break;
                        case Action.Edit:
                            break;
                    }
                }
            });
        };

        $scope.systemCategoryChange = function () {
            $scope.editObj.category = $scope.editObj.systemCategory.category;
        };

        $scope.save = function () {
            switch ($scope.currentAction) {
                case Action.Add:
                    TradeGroupService.post($scope.editObj).then(function (data) {
                        $scope.getTradeGroupList();
                        $scope.hideModal();
                    });
                    break;
                case Action.Edit:
                    $scope.editObj.put().then(function (data) {
                        $scope.getTradeGroupList();
                        $scope.hideModal();
                    });
                    break;
            }
        };

        $scope.getSystemCategoryList();
    }

    var modal = $modal({
        scope: $scope,
        controller: ModalController,
        templateUrl: "accountGroupEdit.html",
        show: false
    });

    $scope.showModal = function () {
        modal.$promise.then(modal.show);
    };
    $scope.hideModal = function () {
        modal.$promise.then(modal.hide);
    };

    $scope.getTradeRuleList();
    $scope.getTradeGroupList();
}