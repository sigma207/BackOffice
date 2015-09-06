/**
 * Created by user on 2015/9/3.
 */
backOfficeApp.controller("AccountController",AccountController);
function AccountController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, TradeHouseRuleService, UserService){
    $translatePartialLoader.addPart("account");
    $translate.refresh();

    $scope.getChildren = function () {
        UserService.getList({parentUserId:$scope.loginUser.userId}).then(function (data) {
            $log.info(data);
            $scope.rowCollection = data;
        });
    };

    $scope.getTradeHouseRuleList = function () {
        var params = {ibUserId:$scope.getUserId()};
        TradeHouseRuleService.getList(params).then(function (data) {
            $scope.tradeHouseRuleList = data;
            if($scope.tradeHouseRuleList.length>0){
                $scope.selectedTradeHouseRule = $scope.tradeHouseRuleList[0];
            }
            //console.table($scope.houseRuleList);
        });
    };

    $scope.getGroup = function () {

    };

    $scope.addIbClick = function () {
        $scope.modalTitle = $translate.instant("addIb");
        $scope.showIbEditModal();
    };

    //editModal
    $scope.ibEditModalClose = function () {
        //$scope.getUserList();
        $scope.hideIbEditModal();
    };

    $scope.stopPropagation = function(e) {
        // to make sure it is a checkbox
        if (angular.element(e.currentTarget).prop('tagName') === 'INPUT') {
            e.stopPropagation();
        }
    };

    function ibEditModalController($scope){

        $scope.save = function () {
        };
    }

    var ibEditModal = $modal({
        scope: $scope,
        controller: ibEditModalController,
        templateUrl:"ibEdit.html",
        show:false
    });

    $scope.showIbEditModal = function() {
        ibEditModal.$promise.then(ibEditModal.show);
    };
    $scope.hideIbEditModal = function() {
        ibEditModal.$promise.then(ibEditModal.hide);
    };

    $scope.getTradeHouseRuleList();
    $scope.getChildren();
}