/**
 * Created by user on 2015/8/27.
 * 此程式是代理可以選擇下層代理可用group的版本,現在暫時已用不到
 */
backOfficeApp.controller("HouseRuleController", HouseRuleController);
function HouseRuleController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, SystemCategoryService, TradeHouseRuleService, TradeGroupService) {
    $translatePartialLoader.addPart("houseRule");
    $translate.refresh();
    var userIdParams = {
        userId:$scope.getUserId()
    };
    $scope.getTradeHouseRuleList = function () {
        TradeHouseRuleService.getList(userIdParams).then(function (data) {
            $scope.rowCollection = data;
        });
    };

    $scope.queryClick = function () {
        $scope.getTradeHouseRuleList();
    };

    $scope.addClick = function () {
        $scope.currentAction = Action.Add;
        $scope.editObj = {};
        $scope.editObj.houseId = $scope.getLoginId();
        $scope.editObj.exchangeId = "*";

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
        row.remove(userIdParams).then(function () {
            $scope.getTradeHouseRuleList();
        });
    };

    function ModalController($scope){
        var i,count;
        var groupMap = {};
        if($scope.editObj.tradeIbGroupList){
            for(i= 0,count=$scope.editObj.tradeIbGroupList.length;i<count;i++){
                groupMap[$scope.editObj.tradeIbGroupList[i].groupId] = 0;
            }
        }

        $scope.getTradeGroupList = function () {
            var params = {
                category:$scope.editObj.category,
                ownerId:$scope.getUserId()
            };
            TradeGroupService.getList(params).then(function (data) {
                $scope.editObj.selectedTradeGroup = [];
                for(i= 0,count=data.length;i<count;i++){
                    var item = data[i];
                    item.label = item.groupName;
                    if (typeof groupMap[item.groupId] !== typeof undefined) {
                        $scope.editObj.selectedTradeGroup.push(item);
                    }
                }
                $scope.tradeGroupList = data;
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
            for(var i= 0,count=$scope.editObj.selectedTradeGroup.length;i<count;i++){
                $scope.editObj.tradeIbGroupList.push({
                    houseId:$scope.editObj.houseId,
                    category:$scope.editObj.category,
                    exchangeId:$scope.editObj.exchangeId,
                    groupId:$scope.editObj.selectedTradeGroup[i].groupId
                });
            }
            switch ($scope.currentAction) {
                case Action.Add:
                    TradeHouseRuleService.post( $scope.editObj,userIdParams).then(function (data) {
                        $scope.getTradeHouseRuleList();
                        $scope.hideModal();
                    });
                    break;
                case Action.Edit:
                    $scope.editObj.put(userIdParams).then(function (data) {
                        $scope.getTradeHouseRuleList();
                        $scope.hideModal();
                    });
                    break;
            }
        };
        $scope.test = function () {
            $log.info($scope.editObj);
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

    $scope.getTradeHouseRuleList();
}
