/**
 * Created by user on 2015/9/9.
 */
backOfficeApp.controller("IbAccountController", IbAccountController);
function IbAccountController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, IbAccountService) {
    $translatePartialLoader.addPart("account");
    $translate.refresh();
    $scope.ibList = [];
    $scope.ibRootLoginId = "ibRoot";

    $scope.init = function () {
        IbAccountService.one($scope.loginUser.userId).get().then(function (ibUser) {
            $scope.addIb(ibUser);
        });
    };

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
        IbAccountService.getList({userId:$scope.currentIb.userId}).then(function (data) {
            $log.info(data);
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
        $scope.showPrefixLoginId = $scope.operatingIb.loginId!=$scope.ibRootLoginId;
        if($scope.showPrefixLoginId){
            $scope.editObj.prefixLoginId = $scope.operatingIb.loginId;
        }

        //$scope.editObj.userId = $scope.operatingIb.userId;
        $scope.editObj.boIbAccount = {};
        $scope.editObj.boIbAccount.parentIbUserId = $scope.operatingIb.userId;//指定新增的代理上層
        $scope.editObj.boIbAccount.isRoot = 0;
        $scope.editObj.boIbAccount.levelNo = $scope.operatingIb.boIbAccount.levelNo+1;

        $scope.showIbEditModal();
    };

    //editModal
    $scope.ibEditModalClose = function () {
        $scope.getChildren();
        $scope.hideIbEditModal();
    };

    function ibEditModalController($scope) {

        $scope.init = function () {
        };

        $scope.save = function () {

            switch ($scope.currentAction) {
                case Action.Add:
                    if($scope.showPrefixLoginId){
                        $scope.editObj.loginId = $scope.editObj.prefixLoginId + $scope.editObj.loginId;
                    }
                    IbAccountService.post($scope.editObj).then(function (data) {
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


}