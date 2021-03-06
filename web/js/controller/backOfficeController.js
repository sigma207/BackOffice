/**
 * Created by user on 2015/9/3.
 */
backOfficeApp.controller("BackOfficeController", BackOfficeController);
function BackOfficeController($scope, $translate, $location, $log, $modal, PermissionService, locale, tmhDynamicLocale, SystemTradeRuleService) {
    //預設語系
    locale.changeLang(locale.zh_TW);
    tmhDynamicLocale.set(locale.zh_TW);
    $scope.isLogin = false;
    var tree = $("#menuTree");
    var zTreeObj;
    var treeSetting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function (event, treeId, treeNode) {
                $scope.$apply(function () {
                    $location.path("/" + treeNode.permissionCode);
                });
            }
        }
    };

    function AlertModalController($scope) {

    }

    var alertModal = $modal({
        scope: $scope,
        controller: AlertModalController,
        //templateUrl:"roleEdit.html",
        show: false
    });

    $scope.showAlertModal = function () {
        alertModal.$promise.then(alertModal.show);
    };
    $scope.hideAlertModal = function () {
        alertModal.$promise.then(alertModal.hide);
    };

    $scope.$on("alert", function (e, d) {
        $scope.alert(d);
    });

    $scope.$on("error", function (e, d) {
        $scope.error(d);
    });

    $scope.alert = function (msg) {
        $scope.content = $translate.instant(msg);
        $scope.showAlertModal();
    };

    $scope.error = function (code) {
        $scope.content = $translate.instant("error."+code);
        $scope.showAlertModal();
    };

    /**
     * 初始化選單
     */
    $scope.initMenuTree = function () {
        $.fn.zTree.init(tree, treeSetting, $scope.menuList);
        zTreeObj = $.fn.zTree.getZTreeObj("menuTree");
        zTreeObj.expandAll(true);
        $scope.initData();
    };

    $scope.changeLanguage = function (langKey) {
        $translate.use(langKey);//頁面html元素的translate
        tmhDynamicLocale.set(langKey);//ui的語系變更(datepicker)
        locale.changeLang(langKey);
        if(zTreeObj){
            //選單的語系需特別處理
            var nodes = zTreeObj.getNodes();
            locale.changeMenuLang(nodes);
            zTreeObj.refresh();
            $scope.$broadcast("langChange");//向下廣播讓權限設定也變更語系
        }
    };

    $location.path("/Login");//預設頁面為登入頁

    $scope.onLogin = function (user) {
        $scope.isLogin = true;
        $location.path("/");
        $scope.loginUser = user;

        //登入成功後取得登入帳號的權限
        PermissionService.post($scope.loginUser.boRolePermissionList, {filter: "true"}).then(function (data) {
            $scope.menuList = data;
            locale.formatPermissionList($scope.menuList);
            $scope.initMenuTree();
        });
    };

    $scope.onLogoutClick = function () {
        $scope.logout();
    };

    /**
     * 登出
     */
    $scope.logout = function () {
        $scope.isLogin = false;
        $location.path("/Login");
        $scope.loginUser = undefined;
    };

    $scope.getLoginId = function () {
        return $scope.loginUser.loginId;
    };

    $scope.getUserId = function () {
        return $scope.loginUser.userId;
    };

    $scope.initData = function () {
        $scope.getTradeRuleList();
    };

    $scope.ruleMap = {};
    $scope.getTradeRuleList = function () {
        SystemTradeRuleService.getList().then(function (data) {
            angular.forEach(data, function (item) {
                $scope.ruleMap[item.category+item.exchangeId] = item;
            });
            $scope.tradeRuleList = data;
        });
    };
}