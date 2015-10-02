/**
 * Created by user on 2015/9/24.
 */
backOfficeApp.controller("IpsTransController", IpsTransController);
function IpsTransController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, IpsTransService) {
    $translatePartialLoader.addPart("account");
    $translate.refresh();
    //
    $scope.init = function () {
        $scope.condition = {};
        //杳詢條件的下拉選單options
        $scope.boStatusList = [];
        $scope.boStatusList.push("");
        $scope.boStatusList.push("P");
        $scope.boStatusList.push("R");
        $scope.boStatusList.push("A");
        $scope.boStatusList.push("I");
        $scope.condition.boStatus = $scope.boStatusList[4];
        $scope.ipsStatusList = [];
        $scope.ipsStatusList.push("");
        $scope.ipsStatusList.push("S");
        $scope.ipsStatusList.push("F");
        $scope.ipsStatusList.push("U");
        $scope.ipsStatusList.push("I");
        $scope.condition.ipsStatus = $scope.ipsStatusList[4];
        $scope.condition.accountId = "";
        $scope.condition.beginDate = "";
        $scope.condition.endDate = "";
        $scope.getIpsTransList();
    };

    $scope.queryClick = function () {
        $scope.getIpsTransList();
    };

    $scope.getIpsTransList = function () {
        DateTool.dateToYyyyMMdd($scope.condition, "begin_date", "beginDate");
        DateTool.dateToYyyyMMdd($scope.condition, "end_date", "endDate");
        IpsTransService.getList($scope.condition).then(function (data) {
            angular.forEach(data, function (item) {
                DateTool.yyyyMMddToDate(item, "tradeDate", "trade_date");
            });
            $scope.rowCollection = data;
        });
    };

    $scope.passClick = function (row) {
        row.put({pass: true}).then(function (data) {
            $scope.getIpsTransList();
        });
    };

    $scope.rejectClick = function (row) {
        $scope.editObj = Restangular.copy(row);
        $scope.modalTitle =  $translate.instant("billNo")+":"+row.billNo;
        $scope.showEditModal();
    };

    $scope.editModalClose = function () {
        $scope.getIpsTransList();
        $scope.hideEditModal();
    };

    function EditModalController($scope){
        $scope.save = function () {
            $scope.editObj.put({reject: true}).then(function (data) {
                $scope.editModalClose();
            });
        };
    }

    var editModal = $modal({
        scope: $scope,
        controller: EditModalController,
        templateUrl:"reject.html",
        show:false
    });

    $scope.showEditModal = function() {
        editModal.$promise.then(editModal.show);
    };

    $scope.hideEditModal = function() {
        editModal.$promise.then(editModal.hide);
    };
}