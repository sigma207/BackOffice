/**
 * Created by user on 2015/8/5.
 */
var backOfficeApp = angular.module("backOfficeApp", ["pascalprecht.translate", "ngAnimate", "ngSanitize", "tmh.dynamicLocale", "ui.bootstrap", "ui.bootstrap.tpls", "ui.bootstrap.tabs", "mgcrea.ngStrap", "smart-table", "ngRoute", "ngResource", "restangular", "fiestah.money", "angular-directive-percentage", "requestFactory", "localeFactory"]);
backOfficeApp.factory('SymbolTradableDailyTempService', ['$resource', function ($resource) {
    return $resource('api/symbolTradableDailyTemp/exchange/:exchange_id/mainSymbol/:main_symbol_id');
}]);
backOfficeApp.factory('SymbolTradableDailyService', ['$resource', function ($resource) {
    return $resource('api/symbolTradableDaily/exchange/:exchange_id/mainSymbol/:main_symbol_id');
}]);
backOfficeApp.run(function(Restangular,$rootScope,$log) {
    Restangular.setBaseUrl("/BackOffice/api");
    //Restangular.setBaseUrl("http://jt8demobo.ja178.com:8080/BackOffice/api/");
    Restangular.setDefaultHeaders({'Content-Type': 'application/json'});
    //ajax的錯誤攔截處理
    Restangular.setErrorInterceptor(function(resp) {
        var status = resp.status;
        var errorJson = resp.data;
        $log.info(resp);
        if(resp.data.message){
            $rootScope.$broadcast("error",resp.data.message);
        } else{
            $rootScope.$broadcast("alert",resp.statusText);
        }
        return true; // 停止promise链
    });

    Restangular.addResponseInterceptor(function(data, operation, what, url, response, deferred) {
        // .. to look for getList operations
        if (operation === "getList") {
            // .. and handle the data and meta data
            //如果是巢狀資料,要將route設到children裡面,不然children的資料會因為沒有route而無法做restful操作
            angular.forEach(data, function (item) {
                if(item.children){
                    angular.forEach(item.children, function (child) {
                        child.route = what;
                    })
                }
            });
        }
        return data;
    });
});
backOfficeApp.config(function($modalProvider) {
    angular.extend($modalProvider.defaults, {
        //animation: 'am-flip-x'
    });
});
backOfficeApp.config(function($datepickerProvider) {
    angular.extend($datepickerProvider.defaults, {
        dateFormat: 'yyyy-MM-dd',
        modelDateFormat: 'yyyyMMdd',
        //dateType: "string",
        startWeek: 1
    });
});
backOfficeApp.config(function($timepickerProvider) {
    angular.extend($timepickerProvider.defaults, {
        timeFormat:"HH:mm:ss",
        modelTimeFormat:"HHmmss",
        timeType:"string",
        minuteStep: 1,
        secondStep: 1
    });
});

//設定載入angular locale檔位置
backOfficeApp.config(function (tmhDynamicLocaleProvider ) {
    tmhDynamicLocaleProvider.localeLocationPattern("js/angular/i18n/angular-locale_{{locale}}.js");
});

backOfficeApp.factory('ExchangeService', function (Restangular) {
    return Restangular.service('exchange');
});

backOfficeApp.factory('SystemCategoryService', function (Restangular) {
    return Restangular.service('systemCategories');
});

backOfficeApp.factory('UserService', function (Restangular) {
    return Restangular.service('boUsers');
});

backOfficeApp.factory('LoginService', function (Restangular) {
    return Restangular.service('login');
});

backOfficeApp.factory('RoleService', function (Restangular) {
    return Restangular.service('boRoles');
});
backOfficeApp.factory('OrganizationService', function (Restangular) {
    return Restangular.service('boOrganization');
});

backOfficeApp.factory('OrganizationMoveService', function (Restangular) {
    return Restangular.service('organization/move');
});

backOfficeApp.factory('PermissionService', function (Restangular) {
    return Restangular.service('boPermission');
});

backOfficeApp.factory('TradeHouseRuleService', function (Restangular) {
    return Restangular.service('tradeHouseRule');
});

backOfficeApp.factory('SystemTradeRuleService', function (Restangular) {
    return Restangular.service('systemTradeRules');
});

backOfficeApp.factory('TradeGroupService', function (Restangular) {
    return Restangular.service('tradeGroups');
});

backOfficeApp.factory('SymbolHolidayService', function (Restangular) {
    return Restangular.service('symbolHolidays');
});

backOfficeApp.factory('SymbolHolidayExceptionService', function (Restangular) {
    return Restangular.service('symbolHolidayExceptions');
});

backOfficeApp.factory('IbAccountService', function (Restangular) {
    return Restangular.service('boIbAccounts');
});

backOfficeApp.factory('LoginAccountService', function (Restangular) {
    return Restangular.service('tradeLoginInAccounts');
});

backOfficeApp.factory('TradeAccountService', function (Restangular) {
    return Restangular.service('tradeAccounts');
});

backOfficeApp.factory('BankbookService', function (Restangular) {
    return Restangular.service('tradeBankbooks');
});

backOfficeApp.factory('IpsTransService', function (Restangular) {
    return Restangular.service('tradeIpsTrans');
});

backOfficeApp.config(["$routeProvider", function ($routeProvider) {
    $routeProvider.
        when("/Login", {
            templateUrl: "Login.html"
        }).
        when("/A1", {
            templateUrl: "permissionManage/permission/Permission.html"
        }).
        when("/A2", {
            templateUrl: "permissionManage/role/Role.html"
        }).
        when("/A3", {
            templateUrl: "userManage/user/User.html"
        }).
        when("/B2", {
            templateUrl: "symbolManage/holiday/Holiday.html"
        }).
        when("/B4", {
            templateUrl: "symbolManage/stock/DailyTemp.html"
        }).
        when("/B5", {
            templateUrl: "symbolManage/stock/Daily.html"
        }).
        when("/C2", {
            templateUrl: "userManage/organization/Organization.html"
        }).
        when("/C3", {
            templateUrl: "userManage/ibAccount/IbAccount.html"
        }).
        when("/C4", {
            templateUrl: "userManage/loginAccount/LoginAccount.html"
        }).
        when("/C5", {
            templateUrl: "userManage/ipsTrans/IpsTrans.html"
        }).
        when("/D1", {
            templateUrl: "systemManage/tradeRule/TradeRule.html"
        }).
        when("/D2", {
            templateUrl: "systemManage/tradeGroup/TradeGroup.html"
        }).
        otherwise({redirectTo: '/'})
}]);
backOfficeApp.config(function ($translateProvider, $translatePartialLoaderProvider) {
    $translatePartialLoaderProvider.addPart('main');
    $translatePartialLoaderProvider.addPart('common');
    $translateProvider.useLoader('$translatePartialLoader', {
        urlTemplate: 'i18n/{part}/{lang}.json'
    });
    $translateProvider.preferredLanguage("zh-TW");
});


backOfficeApp.run(function ($rootScope, $translate, $log) {
    $rootScope.$on('$translatePartialLoaderStructureChanged', function () {
        $rootScope.dateDisplayFormat = $translate.instant("format.display.date");
        $rootScope.dateInputFormat = $translate.instant("format.input.date");
        //日期資料的顯示和輸入格式設定
        $translate.refresh();
    });
});
backOfficeApp.filter("customFilter", ['$filter', function ($filter) {
    var filterFilter = $filter('filter');
    var standardComparator = function standardComparator(obj, text) {
        text = ('' + text).toLowerCase();
        return ('' + obj).toLowerCase().indexOf(text) > -1;
    };
    var startWithComparator = function (actual, expected) {
        var lowerStr = (actual + "").toLowerCase();
        return lowerStr.indexOf(expected.toLowerCase()) === 0;
    };
    var selectedComparator = function (actual, expected) {
        if (expected === 1) {//匹配有勾的
            return (actual === expected);
        } else {
            return true;//不匹配
        }
    };
    return function (array, expression) {
        //console.log(array);
        console.log(expression);
        function customComparator(actual, expected) {
            //console.log("actual=%s",actual);
            //console.log("expected=%s",expected);
            if (angular.isObject(expected)) {
                //console.log("expected=%s",expected.startWith);
                if (expected.startWith && expected.value != "") {
                    return startWithComparator(actual, expected.value);
                } else if (expected.selected) {
                    return selectedComparator(actual, expected.value);
                }
                return true;
            } else {
                return standardComparator(actual, expected);
            }
        }

        return filterFilter(array, expression, customComparator);
    }
}]);

backOfficeApp.filter('time', function(){
    return function(str){
        if(str&&str.length==6){
            var hh = str.substr(0,2);
            var mm = str.substr(2,2);
            var ss = str.substr(4,2);
            return hh+":"+mm+":"+ss;
        }else {
            return str;
        }
    };
});
backOfficeApp.directive("tableSelectCheckbox", TableSelectCheckbox);
backOfficeApp.directive("rowSelectCheckbox", RowSelectCheckbox);
backOfficeApp.directive("headCheckbox", HeadCheckbox);
backOfficeApp.directive("rowCheckbox", RowCheckbox);
backOfficeApp.directive("rowReadonlyCheckbox", RowReadonlyCheckbox);
backOfficeApp.directive("checkboxFilter", CheckboxFilter);
backOfficeApp.directive("textStartWith", TextStartWith);
// Common directive for Focus
backOfficeApp.directive('focus', Focus);
backOfficeApp.directive('parseInt', ParseInt);
backOfficeApp.directive('numbersOnly', NumbersOnly);
backOfficeApp.directive('textInput', TextInput);
backOfficeApp.directive('passwordInput', PasswordInput);
backOfficeApp.directive('numberInput', NumberInput);
backOfficeApp.directive('dateInput', DateInput);
backOfficeApp.directive('timeInput', TimeInput);
backOfficeApp.directive('modalClose', ModalClose);
backOfficeApp.directive('commonButton', CommonButton);
backOfficeApp.directive('modalCancelButton', ModalCancelButton);
backOfficeApp.directive('modalSaveButton', ModalSaveButton);
backOfficeApp.directive('modelButtonFooter', ModelButtonFooter);
backOfficeApp.directive('datePickerOpen', DatePickerOpen);

//validate
backOfficeApp.directive('matchValidate', MatchValidate);
//backOfficeApp.directive('dateLowerThan',DateLowerThan);
//backOfficeApp.directive('dateGreaterThan', DateGreaterThan);


var Action = {
    NewNode: "newNode",
    NewChildNode: "newChildNode",
    MoveUp: "moveUp",
    MoveDown: "moveDown",
    MoveFirst: "moveFirst",
    MoveLast: "moveLast",
    Add: "add",
    Edit: "edit",
    Remove: "remove"
};

