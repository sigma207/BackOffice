/**
 * Created by user on 2015/8/27.
 */
backendApp.controller("HouseRuleController", HouseRuleController);
function HouseRuleController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, TradeHouseRuleService, TradeGroupService) {
    $translatePartialLoader.addPart("houseRule");
    $translate.refresh();
    $log.info("HouseRuleController");
    $scope.getTradeHouseRuleList = function () {
        var params = {
            houseId:"H001"
        };
        TradeHouseRuleService.getList(params).then(function (data) {
            $scope.rowCollection = data;
        });
    };

    $scope.queryClick = function () {
        $scope.getTradeHouseRuleList();
    };

    $scope.addClick = function () {
        $scope.currentAction = Action.Add;
        $scope.editObj = {};
        $scope.editObj.openTime1 = "231021";
        $scope.editObj.closeTime1 = "095500";
        $scope.houseId = $scope.loginUser.userId;

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
            $scope.getTradeHouseRuleList();
        });
    };

    function ModalController($scope){
        $scope.save = function () {
            switch ($scope.currentAction) {
                case Action.Add:
                    TradeHouseRuleService.post( $scope.editObj).then(function (data) {
                        $scope.getTradeHouseRuleList();
                        $scope.hideModal();
                    });
                    break;
                case Action.Edit:
                    $scope.editObj.put().then(function (data) {
                        $scope.getTradeHouseRuleList();
                        $scope.hideModal();
                    });
                    break;
            }
        };
        $scope.test = function () {
            $log.info($scope.editObj);
        }
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
