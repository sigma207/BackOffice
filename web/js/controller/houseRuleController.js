/**
 * Created by user on 2015/8/27.
 */
backendApp.controller("HouseRuleController", HouseRuleController);
function HouseRuleController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, TradeHouseRuleService, TradeAccountGroupService) {
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

    function MyController($scope){

    }
    MyController.$inject = ['$scope'];
    var myModal = $modal({
        controller: MyController,
        templateUrl:"houseRuleEdit.html",
        show:false
    });

    $scope.showModal = function() {
        myModal.$promise.then(myModal.show);
    };
    $scope.hideModal = function() {
        myModal.$promise.then(myModal.hide);
    };

    $scope.openHouseRule = function () {

        $scope.editSize = "xg";
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'houseRuleEdit.html',
            controller: 'houseRuleEditCtrl',
            size: $scope.editSize,
            resolve: {
                editObj: function () {
                    return $scope.editObj;
                },
                title: function () {
                    return $scope.modalTitle;
                },
                currentAction: function () {
                    return $scope.currentAction;
                }
            }
        });

        modalInstance.result.then(function (editNode) {
            $scope.getTradeHouseRuleList();
        }, function () {
            //$log.info('Modal dismissed at: ' + new Date());
        });
    };

    $scope.getTradeHouseRuleList();
}

backendApp.controller('houseRuleEditCtrl', function ($scope, $modalInstance, $log, TradeHouseRuleService, title, editObj, currentAction) {
    $scope.title = title;
    $scope.editObj = editObj;
    $scope.save = function () {
        switch (currentAction) {
            case Action.Add:
                TradeHouseRuleService.post( $scope.editObj).then(function (data) {
                    $modalInstance.close($scope.editObj);
                });
                break;
            case Action.Edit:
                $scope.editObj.put().then(function (data) {
                    $modalInstance.close();
                });
                break;
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});