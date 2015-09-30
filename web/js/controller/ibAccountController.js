/**
 * Created by user on 2015/9/9.
 */
backOfficeApp.controller("IbAccountController", IbAccountController);
function IbAccountController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, IbAccountService, LoginAccountService, TradeAccountService, BankbookService) {
    $translatePartialLoader.addPart("account");
    $translatePartialLoader.addPart("user");
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

    $scope.queryClick = function () {
        $scope.getChildren();
    };

    $scope.getChildren = function () {
        $scope.getChildrenIb();
    };

    $scope.getChildrenIb = function () {
        IbAccountService.getList({userId: $scope.currentIb.userId}).then(function (data) {
            $log.info(data);
            $scope.rowCollection = data;
            $scope.getLoginAccounts();
        });
    };

    $scope.getLoginAccounts = function () {
        LoginAccountService.getList({userId: $scope.currentIb.userId}).then(function (data) {
            $scope.rowCollection.push.apply($scope.rowCollection, data);
            $log.info($scope.rowCollection);
        });
    };

    $scope.isIb = function (row) {
        return (row.boIbAccount);
    };

    $scope.removeClick = function (row) {
        if ($scope.isIb(row)) {
            $scope.removeIbClick(row);
        } else {
            $scope.removeLoginAccountClick(row);
        }
    };

    $scope.editClick = function (row) {
        if ($scope.isIb(row)) {
            $scope.editIbClick(row);
        } else {
            $scope.editLoginAccountClick(row);
        }
    };

    //IbEdit
    $scope.removeIbClick = function (row) {
        row.remove().then(function () {
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

        //$scope.editObj.userId = $scope.operatingIb.userId;
        $scope.editObj.boIbAccount = {};
        $scope.editObj.boIbAccount.parentIbUserId = $scope.operatingIb.userId;//指定新增的代理上層
        $scope.editObj.boIbAccount.isRoot = 0;
        $scope.editObj.boIbAccount.levelNo = $scope.operatingIb.boIbAccount.levelNo + 1;

        $scope.showIbEditModal();
    };

    $scope.editIbClick = function (ib) {
        $scope.currentAction = Action.Edit;
        $scope.operatingIb = ib;
        $scope.modalTitle = ib.loginId + ":" + $translate.instant("editIb");
        $scope.editObj = Restangular.copy(ib);
        //$scope.editObj.userId = $scope.editObj.userId;
        $scope.showIbEditModal();
    };

    //editModal
    $scope.ibEditModalClose = function () {
        $scope.getChildren();
        $scope.hideIbEditModal();
    };

    function IbEditModalController($scope) {
        $scope.init = function () {
        };
        $scope.save = function () {
            switch ($scope.currentAction) {
                case Action.Add:
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
        controller: IbEditModalController,
        templateUrl: "ibEdit.html",
        show: false
    });

    $scope.showIbEditModal = function () {
        ibEditModal.$promise.then(ibEditModal.show);
    };
    $scope.hideIbEditModal = function () {
        ibEditModal.$promise.then(ibEditModal.hide);
    };

    //LoginAccountEdit
    $scope.removeLoginAccountClick = function (row) {
        row.remove().then(function () {
            $scope.getChildren();
        });
    };

    $scope.addLoginAccountClick = function (row) {
        $scope.currentAction = Action.Add;
        $scope.operatingIb = (typeof row === typeof undefined) ? $scope.currentIb : row;
        $scope.modalTitle = $scope.operatingIb.loginId + ":" + $translate.instant("addLoginAccount");
        $scope.editObj = {};
        $scope.editObj.userId = $scope.operatingIb.userId;
        $scope.showLoginAccountEditModal();
    };

    $scope.editLoginAccountClick = function (row) {
        $scope.currentAction = Action.Edit;
        $scope.modalTitle = $scope.currentIb.loginId + ":" + $translate.instant("editLoginAccount");
        $scope.editObj = Restangular.copy(row);
        $scope.showLoginAccountEditModal();
    };

    $scope.loginAccountEditModalClose = function () {
        $scope.getChildren();
        $scope.hideLoginAccountEditModal();
    };

    function LoginAccountEditModalController($scope) {
        $scope.init = function () {
        };
        $scope.save = function () {
            switch ($scope.currentAction) {
                case Action.Add:
                    LoginAccountService.post($scope.editObj).then(function (data) {
                        $scope.loginAccountEditModalClose();
                    });
                    break;
                case Action.Edit:
                    $scope.editObj.put().then(function (data) {
                        $scope.loginAccountEditModalClose();
                    });
                    break;
            }
        };
    }

    var loginAccountEditModal = $modal({
        scope: $scope,
        controller: LoginAccountEditModalController,
        templateUrl: "loginAccountEdit.html",
        show: false
    });

    $scope.showLoginAccountEditModal = function () {
        loginAccountEditModal.$promise.then(loginAccountEditModal.show);
    };

    $scope.hideLoginAccountEditModal = function () {
        loginAccountEditModal.$promise.then(loginAccountEditModal.hide);
    };

    //
    $scope.withdrawalDepositClick = function (tradeLoginAccount,index) {
        $scope.editObj = tradeLoginAccount.tradeAccountList[index];
        $scope.modalTitle = $translate.instant("loginId")+ ":" +tradeLoginAccount.loginId+ $translate.instant("tradeAccount") + ":" + $scope.editObj.accountId;
        $scope.showTradeBankbookModal();
    };

    //editModal
    $scope.tradeBankbookModalClose = function () {
        $scope.hideTradeBankbookModal();
    };

    function TradeBankbookModalController($scope) {
        //BankbookService
        $scope.init = function () {
            $scope.amount = 0;
            $scope.condition = {};
            $scope.condition.begin_date = new Date();
            $scope.condition.end_date = new Date();
            $scope.initTradeBankbook();
        };

        $scope.initTradeBankbook = function () {
            $scope.tradeBankbook = {};
            $scope.tradeBankbook.accountId = $scope.editObj.accountId;
        };

        $scope.getBankbookList = function () {
            DateTool.dateToYyyyMMdd($scope.condition, "begin_date", "beginDate");
            DateTool.dateToYyyyMMdd($scope.condition, "end_date", "endDate");
            var params = {};
            params.accountId = $scope.editObj.accountId;
            params.beginDate = $scope.condition.beginDate;
            params.endDate = $scope.condition.endDate;
            BankbookService.getList(params).then(function (data) {
                angular.forEach(data, function (item) {
                    DateTool.yyyyMMddToDate(item, "tradeDate", "trade_date");
                });
                $scope.rowCollection = data;
            });
        };

        //更新tradeAccount的帳戶餘額
        $scope.getLastBankbook = function () {
            BankbookService.one().get({accountId:$scope.editObj.accountId}).then(function (data) {
                $scope.editObj.lastTradeBankbook = data;
            });
        };

        $scope.queryClick = function () {
            $scope.getBankbookList();
        };

        $scope.save = function () {
            BankbookService.post($scope.tradeBankbook).then(function (data) {
                $scope.initTradeBankbook();
                $scope.getLastBankbook();
                $scope.getBankbookList();
            });
        };

    }

    var tradeBankbookModal = $modal({
        scope: $scope,
        controller: TradeBankbookModalController,
        templateUrl: "tradeBankbook.html",
        show: false
    });

    $scope.showTradeBankbookModal = function () {
        tradeBankbookModal.$promise.then(tradeBankbookModal.show);
    };
    $scope.hideTradeBankbookModal = function () {
        tradeBankbookModal.$promise.then(tradeBankbookModal.hide);
    };
}