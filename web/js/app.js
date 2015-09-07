/**
 * Created by user on 2015/8/5.
 */
var backOfficeApp = angular.module("backOfficeApp", ["pascalprecht.translate", "ngAnimate", "ngSanitize", "ui.bootstrap", "ui.bootstrap.tpls", "ui.bootstrap.tabs", "mgcrea.ngStrap", "smart-table", "ngRoute", "ngResource", "restangular", "fiestah.money", "angular-directive-percentage", "requestFactory", "localeFactory"]);
backOfficeApp.factory('SymbolTradableDailyTempService', ['$resource', function ($resource) {
    return $resource('api/symbolTradableDailyTemp/exchange/:exchange_id/mainSymbol/:main_symbol_id');
}]);
backOfficeApp.factory('SymbolTradableDailyService', ['$resource', function ($resource) {
    return $resource('api/symbolTradableDaily/exchange/:exchange_id/mainSymbol/:main_symbol_id');
}]);

backOfficeApp.constant("HostUrl", "http://localhost:8080/BackOffice/api");
//backOfficeApp.config(function (RestangularProvider) {
//    RestangularProvider.setBaseUrl("/BackOffice/api");
//    RestangularProvider.setDefaultHeaders({'Content-Type': 'application/json'});
//    RestangularProvider.setErrorInterceptor(function(resp) {
//        var status = resp.status;
//        var errorJson = resp.data;
//        console.log(resp);
//        return true; // 停止promise链
//    });
//});
backOfficeApp.run(function(Restangular,$rootScope,$log,$modal,$alert) {
    Restangular.setBaseUrl("/BackOffice/api");
    Restangular.setDefaultHeaders({'Content-Type': 'application/json'});
    //var myAlert = $alert({title: 'Holy guacamole!', content: 'Best check yo self, you\'re not looking too good.', placement: 'top', type: 'danger', keyboard: true, show: false});
    Restangular.setErrorInterceptor(function(resp) {
        var status = resp.status;
        var errorJson = resp.data;
        //myAlert.show();
        $log.info(resp);
        if(resp.data.message){
            $rootScope.$broadcast("alert",resp.data.message);
        } else{
            $rootScope.$broadcast("alert",resp.statusText);
        }
        return true; // 停止promise链
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
    return Restangular.service('boRole');
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

backOfficeApp.factory('TradeGroupService', function (Restangular) {
    return Restangular.service('tradeGroup');
});

backOfficeApp.factory('SymbolHolidayService', function (Restangular) {
    return Restangular.service('symbolHoliday');
});

backOfficeApp.factory('SymbolHolidayExceptionService', function (Restangular) {
    return Restangular.service('symbolHolidayException');
});

backOfficeApp.factory('IbService', function (Restangular) {
    return Restangular.service('ibs');
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
        when("/B2", {
            templateUrl: "symbolManage/holiday/Holiday.html"
        }).
        when("/B4", {
            templateUrl: "symbolManage/stock/DailyTemp.html"
        }).
        when("/B5", {
            templateUrl: "symbolManage/stock/Daily.html"
        }).
        when("/C1", {
            templateUrl: "userManage/user/User.html"
        }).
        when("/C2", {
            templateUrl: "userManage/organization/Organization.html"
        }).
        when("/C3", {
            templateUrl: "userManage/account/Account.html"
        }).
        when("/D1", {
            templateUrl: "systemManage/houseRule/HouseRule.html"
        }).
        when("/D2", {
            templateUrl: "systemManage/accountGroup/AccountGroup.html"
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
backOfficeApp.directive('datePickerOpen', DatePickerOpen);
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

