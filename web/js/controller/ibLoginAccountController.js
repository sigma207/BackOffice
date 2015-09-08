/**
 * Created by user on 2015/9/3.
 */
backOfficeApp.controller("IbLoginAccountController", IbLoginAccountController);
function IbLoginAccountController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, TradeHouseRuleService, IbService) {
    $translatePartialLoader.addPart("account");
    $translate.refresh();
    $scope.ibList = [];

    $scope.backToIb = function (ib) {
        for (var i = $scope.ibList.length - 1; i >= 0; i--) {
            if ($scope.ibList[i].loginId == ib.loginId) {
                break;
            }
            $scope.ibList.pop();
        }
        $scope.changeIb(ib);
    };

    $scope.addIb = function (ib) {
        $scope.ibList.push(ib);
        $scope.changeIb(ib);
    };

    $scope.changeIb = function (ib) {
        $scope.currentIb = ib;
        $scope.getChildren();
    };

    $scope.getChildren = function () {
        IbService.getList({parentUserId: $scope.currentIb.userId}).then(function (data) {
            $scope.rowCollection = data;
        });
    };

    $scope.editClick = function (ib) {
        $scope.currentAction = Action.Edit;
        $scope.operatingIb = ib;
        $scope.modalTitle = ib.loginId + ":" + $translate.instant("editIb");
        $scope.editObj = Restangular.copy(ib);
        //$scope.editObj.userId = $scope.editObj.userId;
        $scope.showIbEditModal();
    };

    $scope.removeClick = function (ib) {
        ib.remove().then(function () {
            $scope.getChildren();
        });
    };

    $scope.addIbClick = function (ib) {
        $scope.currentAction = Action.Add;
        //操作的代理,可能為當前層級的ib 如H001/H0011 的H0011
        //也有可能是H0011下面一層的某個代理(顯示在下面table列表)
        $scope.operatingIb = (typeof ib === typeof undefined) ? $scope.currentIb : ib;
        $scope.modalTitle = $scope.operatingIb.loginId + ":" + $translate.instant("addIb");
        $scope.editObj = {};
        $scope.editObj.prefixLoginId = $scope.operatingIb.loginId;
        $scope.editObj.userId = $scope.operatingIb.userId;
        $scope.editObj.parentUserId = $scope.operatingIb.userId;//指定新增的代理上層
        if ($scope.operatingIb.houseId) {
            $scope.editObj.houseId = $scope.operatingIb.houseId;
        } else {
            $scope.editObj.houseId = $scope.operatingIb.loginId;
        }
        $scope.showIbEditModal();
    };

    //editModal
    $scope.ibEditModalClose = function () {
        $scope.getChildren();
        $scope.hideIbEditModal();
    };

    function ibEditModalController($scope) {

        $scope.init = function () {
            $scope.getTradeHouseRuleList();
        };

        $scope.getTradeHouseRuleList = function () {
            var params;
            switch ($scope.currentAction) {
                case Action.Add:
                    params = {ibUserId: $scope.editObj.userId};
                    break;
                case Action.Edit:
                    params = {ibUserId: $scope.editObj.userId,parentIbUserId: $scope.editObj.parentUserId};
                    break;
            }
            TradeHouseRuleService.getList(params).then($scope.onTradeHouseRuleData);
        };

        $scope.onTradeHouseRuleData = function (data) {
            $scope.tradeHouseRuleList = data;
            if ( $scope.tradeHouseRuleList.length > 0) {
                var i, j,tradeHouseRule,selectedGroupIdList;
                for (i = 0; i < data.length; i++) {
                    tradeHouseRule = data[i];
                    selectedGroupIdList = [];
                    for (j = 0; j < tradeHouseRule.tradeIbGroupList.length; j++) {
                        selectedGroupIdList.push(tradeHouseRule.tradeIbGroupList[j].groupId);
                    }
                    for (j = 0; j < tradeHouseRule.tradeGroupList.length; j++) {
                        if(selectedGroupIdList.indexOf(tradeHouseRule.tradeGroupList[j].groupId)!=-1){
                            tradeHouseRule.tradeGroupList[j].selected = true;
                        }
                    }
                }
                $scope.selectedTradeHouseRule =  $scope.tradeHouseRuleList[0];
            }
        };

        $scope.save = function () {
            $scope.editObj.tradeGroupIdList = [];
            var data = $scope.tradeHouseRuleList;
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < data[i].tradeGroupList.length; j++) {
                    if (data[i].tradeGroupList[j].selected) {
                        $scope.editObj.tradeGroupIdList.push(data[i].tradeGroupList[j].groupId);
                    }
                }
            }

            switch ($scope.currentAction) {
                case Action.Add:
                    $scope.editObj.loginId = $scope.editObj.prefixLoginId + $scope.editObj.loginId;
                    IbService.post($scope.editObj).then(function (data) {
                        $scope.ibEditModalClose();
                    });
                    break;
                case Action.Edit:
                    $scope.editObj.put().then(function (data) {
                        $scope.ibEditModalClose();
                    });
                    break;
            }
        };

    }

    var ibEditModal = $modal({
        scope: $scope,
        controller: ibEditModalController,
        templateUrl: "ibEdit.html",
        show: false
    });

    $scope.showIbEditModal = function () {
        ibEditModal.$promise.then(ibEditModal.show);
    };
    $scope.hideIbEditModal = function () {
        ibEditModal.$promise.then(ibEditModal.hide);
    };

    $scope.addIb($scope.loginUser);
}