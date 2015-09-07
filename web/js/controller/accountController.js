/**
 * Created by user on 2015/9/3.
 */
backOfficeApp.controller("AccountController", AccountController);
function AccountController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, TradeHouseRuleService, IbService) {
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
        $scope.modelIb = ib;
        $scope.modalTitle = ib.loginId + ":" + $translate.instant("editIb");
        $scope.editObj = Restangular.copy(ib);
        $scope.editObj.ibUserId = $scope.editObj.userId;
        $scope.showIbEditModal();
    };

    $scope.removeClick = function (ib) {
        ib.remove().then(function () {
            $scope.getChildren();
        });
    };

    $scope.addIbClick = function (ib) {
        $scope.currentAction = Action.Add;
        $scope.modelIb = (typeof ib === typeof undefined) ? $scope.currentIb : ib;
        $scope.modalTitle = $scope.modelIb.loginId + ":" + $translate.instant("addIb");
        $scope.editObj = {};
        $scope.editObj.prefixLoginId = $scope.modelIb.loginId;
        $scope.editObj.ibUserId = $scope.modelIb.userId;
        if ($scope.modelIb.houseId) {
            $scope.editObj.houseId = $scope.modelIb.houseId;
        } else {
            $scope.editObj.houseId = $scope.modelIb.loginId;
        }
        $scope.showIbEditModal();
    };

    //editModal
    $scope.ibEditModalClose = function () {
        $scope.getChildren();
        $scope.hideIbEditModal();
    };

    function ibEditModalController($scope) {
        $scope.checkboxTest = function () {
            var data = $scope.tradeHouseRuleList;
            for (var i = 0; i < data.length; i++) {
                var ok = false;
                for (var j = 0; j < data[i].tradeGroupList.length; j++) {
                    if (data[i].tradeGroupList[j].selected) {
                        ok = true;
                        break;
                    }
                }
                if (!ok) {
                    return false;
                }
            }
            return true;
        };

        $scope.init = function () {
            $scope.getTradeHouseRuleList();
        };

        $scope.getTradeHouseRuleList = function () {
            var params = {ibUserId: $scope.editObj.ibUserId};
            TradeHouseRuleService.getList(params).then(function (data) {
                $scope.tradeHouseRuleList = data;
                if ( $scope.tradeHouseRuleList.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        for (var j = 0; j < data[i].tradeGroupList.length; j++) {
                            data[i].tradeGroupList[j].selected = true;
                        }
                    }
                    $scope.selectedTradeHouseRule =  $scope.tradeHouseRuleList[0];
                }
            });
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