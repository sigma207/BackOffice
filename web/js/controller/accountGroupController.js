/**
 * Created by user on 2015/8/27.
 *  此程式是代理可以選擇下層代理可用group的版本,現在暫時已用不到
 */
backOfficeApp.controller("AccountGroupController", AccountGroupController);//交易帳號屬性群組
function AccountGroupController($scope, $modal, $log, $translatePartialLoader, $translate, $alert, Restangular, SystemCategoryService, TradeHouseRuleService, TradeGroupService) {
    $translatePartialLoader.addPart("group");
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
        var params = {
            ownerId:$scope.getUserId()
        };
        TradeGroupService.getList(params).then(function (data) {
            $scope.rowCollection = data;
        })
    };

    $scope.queryClick = function () {
        $scope.getTradeAccountGroupList();
    };

    $scope.addClick = function () {
        $scope.currentAction = Action.Add;
        $scope.editObj = angular.copy($scope.selectedTradeHouseRule);
        $scope.editObj.exchangeId = "*";
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
        //var myAlert = $alert({title: 'Holy guacamole!', content: 'Best check yo self, you\'re not looking too good.', placement: 'top', type: 'danger', keyboard: true, show: false});
        row.remove().then(
            function () {
                $scope.getTradeAccountGroupList();
            }
        );
    };

    function ModalController($scope){
        var i,count;
        $scope.getSystemCategoryList = function () {
            SystemCategoryService.getList().then(function (data) {
                $scope.systemCategoryList = data;
                if($scope.systemCategoryList.length>0){
                    for(i= 0,count=$scope.systemCategoryList.length;i<count;i++){
                        if($scope.editObj.category==$scope.systemCategoryList[i].category){
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

        $scope.getSystemCategoryList();
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