/**
 * Created by user on 2015/9/9.
 */
backOfficeApp.controller("TradeRuleController", TradeRuleController);
function TradeRuleController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, SystemCategoryService, SystemTradeRuleService, TradeGroupService) {
    $translatePartialLoader.addPart("tradeRule");
    $translate.refresh();

    $scope.getTradeRuleList = function () {
        SystemTradeRuleService.getList().then(function (data) {
            $scope.rowCollection = data;
        });
    };

    $scope.queryClick = function () {
        $scope.getTradeRuleList();
    };

    $scope.addClick = function () {
        $scope.currentAction = Action.Add;
        $scope.editObj = {};
        $scope.editObj.exchangeId = "*";
        $scope.editObj.specialStockRule = 0;
        $scope.modalTitle = $translate.instant("tradeRule");
        $scope.showModal();
    };

    $scope.editClick = function (row) {
        $scope.currentAction = Action.Edit;
        $scope.editObj = Restangular.copy(row);
        $scope.modalTitle = $translate.instant("tradeRule");
        $scope.showModal();
    };

    $scope.deleteClick = function (row) {
        row.remove().then(function () {
            $scope.getTradeRuleList();
        });
    };

    function ModalController($scope){
        var i,count;

        $scope.getTradeGroupList = function () {
            var params = {
                category:$scope.editObj.category
            };
            TradeGroupService.getList(params).then(function (data) {
                $scope.editObj.tradeGroupList = [];
                angular.forEach(data, function (item) {
                    if (item.isActive==1) {
                        item.label = item.groupName;
                        this.push(item);
                    }
                }, $scope.editObj.tradeGroupList);
            })
        };

        $scope.getSystemCategoryList = function () {
            SystemCategoryService.getList().then(function (data) {
                $scope.systemCategoryList = data;
                if($scope.systemCategoryList.length>0){
                    switch ($scope.currentAction) {
                        case Action.Add:
                            $scope.editObj.systemCategory = $scope.systemCategoryList[0];
                            $scope.systemCategoryChange();
                            break;
                        case Action.Edit:
                            for(var i= 0,count=$scope.systemCategoryList.length;i<count;i++){
                                if($scope.editObj.category==$scope.systemCategoryList[i].category){
                                    $scope.editObj.systemCategory = $scope.systemCategoryList[i];
                                    $scope.getTradeGroupList();
                                }
                            }
                            break;
                    }
                }
            });
        };

        $scope.systemCategoryChange = function () {
            $scope.editObj.category = $scope.editObj.systemCategory.category;
            $scope.getTradeGroupList();
        };

        $scope.useTradeGroupChange = function () {
            $log.info($scope.editObj.groupId);
        };

        $scope.save = function () {
            switch ($scope.currentAction) {
                case Action.Add:
                    SystemTradeRuleService.post().then(function (data) {
                        $scope.getTradeRuleList();
                        $scope.hideModal();
                    });
                    break;
                case Action.Edit:
                    $scope.editObj.put().then(function (data) {
                        $scope.getTradeRuleList();
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
        templateUrl:"houseRuleEdit.html",
        show:false
    });

    $scope.showModal = function() {
        modal.$promise.then(modal.show);
    };
    $scope.hideModal = function() {
        modal.$promise.then(modal.hide);
    };

    $scope.getTradeRuleList();
}
