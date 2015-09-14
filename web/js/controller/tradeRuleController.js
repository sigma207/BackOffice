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
        $scope.modalTitle = $translate.instant("houseRule");
        $scope.showModal();
    };

    $scope.editClick = function (row) {
        $scope.currentAction = Action.Edit;
        $scope.editObj = Restangular.copy(row);
        $scope.modalTitle = $translate.instant("houseRule");
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
                var item;
                $scope.editObj.selectedTradeGroup = [];
                for(i= 0,count=data.length;i<count;i++){
                    item = data[i];
                    item.label = item.groupName;
                    if (item.isActive==1) {
                        $scope.editObj.selectedTradeGroup.push(item);
                    }
                }
                $scope.editObj.tradeGroupList = data;
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
            $log.info($scope.editObj.selectedTradeGroup);
        };

        $scope.save = function () {
            $scope.editObj.tradeIbGroupList = [];
            var i,count;

            for(i= 0,count=$scope.editObj.tradeGroupList.length;i<count;i++){
                $scope.editObj.tradeGroupList[i].isActive = 0;
            }
            for(i= 0,count=$scope.editObj.selectedTradeGroup.length;i<count;i++){
                 $scope.editObj.selectedTradeGroup[i].isActive = 1;
            }
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
