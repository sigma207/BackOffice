/**
 * Created by user on 2015/9/9.
 */
backOfficeApp.controller("TradeGroupController", TradeGroupController);//交易帳號屬性群組
function TradeGroupController($scope, $modal, $log, $translatePartialLoader, $translate, $alert, Restangular, SystemCategoryService, TradeGroupService) {
    $translatePartialLoader.addPart("group");
    $translate.refresh();

    $scope.getTradeGroupList = function () {
        TradeGroupService.getList().then(function (data) {
            $scope.rowCollection = data;
        })
    };

    $scope.queryClick = function () {
        $scope.getTradeGroupList();
    };

    $scope.isActiveClick = function (row) {
        var tradeGroup = Restangular.copy(row);
        var isActive = (tradeGroup.isActive==1)?0:1;
        tradeGroup.put({isActive:isActive}).then(function (data) {
            $scope.getTradeGroupList();
        });
    };

    $scope.addClick = function () {
        $scope.currentAction = Action.Add;
        //新增trade_group是以system_trade_rule當作template
        $scope.editObj = {};
        $scope.editObj.isActive = 0;
        $scope.editObj.specialStockRule = 0;
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
        row.remove().then(function () {
            $scope.getTradeGroupList();
        });
    };

    function ModalController($scope) {
        $scope.init = function () {
            $scope.getSystemCategoryList();
            $scope.applyTradeGroupList = [];
            angular.forEach($scope.rowCollection, function (item) {
                if ($scope.currentAction == Action.Edit && item.groupName == $scope.editObj.groupName) {
                    //do not thing
                }else{
                    this.push(item);
                }
            },$scope.applyTradeGroupList);
        };

        $scope.getSystemCategoryList = function () {
            SystemCategoryService.getList().then(function (data) {
                if (data.length > 0) {
                    angular.forEach(data, function (item) {
                        if ($scope.editObj.category == item.category) {
                            $scope.editObj.systemCategory = item;//category下拉選單的預設值改成和editObj的一樣
                        }
                    });
                }
                $scope.systemCategoryList = data;
            });
        };

        $scope.systemCategoryChange = function () {
            $scope.editObj.category = $scope.editObj.systemCategory.category;
        };

        $scope.applyClick = function () {
            var isActive = $scope.editObj.isActive;
            var groupName = $scope.editObj.groupName;
            $scope.editObj = angular.copy($scope.editObj.selectedTradeGroup);
            $scope.editObj.isActive = isActive;
            $scope.editObj.groupName = groupName;
            angular.forEach($scope.systemCategoryList, function (item) {
                if(item.category==$scope.editObj.category){
                    $scope.editObj.systemCategory = item;
                }
            });
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

    if ($scope.tradeRuleList.length > 0) {
        $scope.selectedTradeRule = $scope.tradeRuleList[0];
    }
    $scope.getTradeGroupList();
}