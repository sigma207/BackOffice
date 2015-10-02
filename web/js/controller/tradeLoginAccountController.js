/**
 * Created by user on 2015/9/14.
 */
backOfficeApp.controller("TradeLoginAccountController", TradeLoginAccountController);
function TradeLoginAccountController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, LoginAccountService, TradeGroupService, TradeAccountService) {
    $translatePartialLoader.addPart("account");
    $translatePartialLoader.addPart("group");
    $translate.refresh();
    var groupMap = {};

    $scope.init = function () {
        $scope.getTradeGroupList();
    };

    $scope.queryClick = function () {
        $scope.getLoginAccountList();
    };

    $scope.getLoginAccountList = function () {
        LoginAccountService.getList().then(function (data) {
            $scope.rowCollection = data;
        });
    };

    $scope.getTradeGroupList = function () {
        TradeGroupService.getList().then(function (data) {
            angular.forEach(data, function (item) {
                if(item.isActive){
                    var temp = groupMap[item.category+item.exchangeId];
                    if(typeof temp === typeof undefined){
                        temp = [];
                        groupMap[item.category+item.exchangeId] = temp;
                    }
                    temp.push(item);
                }
            });
            $scope.getLoginAccountList();
        });
    };

    $scope.isActiveClick = function (row) {
        var tradeGroup = Restangular.copy(row);
        var isActive = (tradeGroup.isActive==1)?0:1;
        tradeGroup.put({isActive:isActive}).then(function (data) {
            $scope.getLoginAccountList();
        });
    };

    $scope.editClick = function (row) {
        $scope.editObj = Restangular.copy(row);
        $scope.showEditModal();
    };

    //editModal
    $scope.editModalClose = function () {
        $scope.getLoginAccountList();
        $scope.hideEditModal();
    };

    function EditModalController($scope) {
        $scope.init = function () {
            TradeAccountService.getList({loginId:$scope.editObj.loginId}).then(function (data) {
                angular.forEach(data, function (item) {
                    item.rule = $scope.ruleMap[item.category+item.exchangeId];
                    item.tradeGroupList = groupMap[item.category+item.exchangeId];
                });
                $scope.editObj.tradeAccountList = data;
            });
        };
        $scope.save = function () {
            $scope.editObj.put({tradeAccount:true}).then(function (data) {
                $scope.editModalClose();
            });
        };
    }

    var editModal = $modal({
        scope: $scope,
        controller: EditModalController,
        templateUrl: "loginAccountGroupEdit.html",
        show: false
    });

    $scope.showEditModal = function () {
        editModal.$promise.then(editModal.show);
    };
    $scope.hideEditModal = function () {
        editModal.$promise.then(editModal.hide);
    };
}