/**
 * Created by user on 2015/8/13.
 */

/**
 * 用來綁定tr裡checkbox所勾選的所有資料到selectedItems
 * 需和下面的RowSelectCheckbox一起使用
 * 可參考User.html
 * @returns {{scope: {selectedItems: string}, controller: Function}}
 * @constructor
 */
function TableSelectCheckbox(){
    return {
        scope: {
            selectedItems: '='
        },
        controller: function ($scope, $compile) {
            //console.log("tableCheckbox controller");
            //console.log($scope);
            var ctrl = this;
            this.itemSelect = function (item,selected) {
                if(selected){
                    if(!ctrl.itemHasBeenSelected(item)){
                        $scope.selectedItems.push(item);
                    }
                } else {
                    var index = $scope.selectedItems.indexOf(item);
                    $scope.selectedItems.splice(index,1);
                }
            };
            this.itemHasBeenSelected = function (item) {
                return ($scope.selectedItems.indexOf(item)!=-1);
            }
        }
    }
}
/**
 * 用來勾選tr的資料
 * 需和TableSelectCheckbox一起使用
 * @returns {{template: string, require: string, scope: {row: string, index: string}, link: Function}}
 * @constructor
 */
function RowSelectCheckbox(){
    return {
        template: "<input ng-model='selected' type='checkbox'/>",
        require: "^tableSelectCheckbox",
        scope: {
            row:"=",
            index:"="
        },
        link: function (scope, element, attr, ctrl) {
            scope.selected = false;
            if(ctrl.itemHasBeenSelected(scope.row)){
                scope.selected = true;
            }
            element.on('change', function (event) {
                ctrl.itemSelect(scope.row,scope.selected);
            });
        }
    }
}
/**
 * 用來將table的某欄位全部勾選或取消勾選(變更property為1 or 0)
 * 需和下面的RowCheckbox一起使用
 * @returns {{restrict: string, require: string, template: string, scope: {collection: string}, link: Function}}
 * @constructor
 */
function HeadCheckbox(){
    return {
        restrict: 'E',
        require: '^stTable',
        template: "<input ng-model='selected' type='checkbox'/>",
        scope:{
            collection:"=collection"
        },
        link: function (scope, element, attr, ctrl) {
            var field = attr.field;
            scope.selected = false;
            element.on('change', function (event) {
                scope.$apply(function () {
                    for(var i= 0,count=scope.collection.length;i<count;i++){
                        scope.collection[i][field] = (scope.selected)?1:0;
                    }
                });
            });

            //scope.$watch(function () {
            //    console.log("HeadCheckbox scope.$watch ctrl.tableState().pagination");
            //    return ctrl.tableState().pagination;
            //}, function () {
            //
            //}, true);
        }
    }
}
/**
 * 用來變更及顯示tr資料的某屬性(0 or 1)
 * @returns {{restrict: string, template: string, scope: {selectValue: string}}}
 * @constructor
 */
function RowCheckbox(){
    return {
        restrict: 'E',
        template: "<input type='checkbox' ng-model='selectValue' parse-int  ng-true-value='1' ng-false-value='0' ng-checked='selectValue===1'/>",
        scope:{
            selectValue:"=selectValue"
        }
    }
}
/**
 * 同RowCheckbox但不能變更勾選
 * @returns {{restrict: string, template: string, scope: {selectValue: string}}}
 * @constructor
 */
function RowReadonlyCheckbox(){
    return {
        restrict: 'E',
        template: "<input type='checkbox' ng-model='selectValue' parse-int  ng-true-value='1' ng-false-value='0' ng-checked='selectValue===1' onclick='return false;'/>",
        scope:{
            selectValue:"=selectValue"
        }
    }
}
/**
 * 將modelValue轉換成int
 * @returns {{restrict: string, require: string, link: Function}}
 * @constructor
 */
function ParseInt(){
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, elem, attrs, controller) {
            //console.log("DEF");
            controller.$formatters.push(function (modelValue) {
                //console.log('model', modelValue, typeof modelValue);
                return '' + modelValue;
            });

            controller.$parsers.push(function (viewValue) {
                //console.log('view', viewValue, typeof viewValue);
                return parseInt(viewValue,10);
            });
        }
    }
}

function TextInput(){
    return {
        restrict:"E",
        replace:true,
        template:'<input type="text" class="form-control"/>'
    }
}

function PasswordInput(){
    return {
        restrict:"E",
        replace:true,
        template:'<input type="password" class="form-control"/>'
    }
}

function NumberInput(){
    return {
        restrict:"E",
        replace:true,
        template:'<input type="text" class="form-control" numbers-only/>'
    }
}

function DateInput(){
    //<button type="button" class="btn-xs btn-default" date-picker-open open-status="row.beginDateOpened"><i class="glyphicon glyphicon-calendar"></i></button>
    return {
        restrict:"E",
        replace:true,
        template:'<input type="text" size="10" bs-datepicker/>'
    }
}

function TimeInput(){
    return {
        restrict:"E",
        replace:true,
        template:'<input type="text" class="form-control" bs-timepicker/>'
    }
}

function ModalClose(){
    return {
        restrict:"E",
        replace:true,
        template:'<button type="button" class="close" aria-label="Close" ng-click="$hide()"><span aria-hidden="true">&times;</span></button>'
    }
}

function CommonButton(){
    return {
        restrict:"E",
        replace:true,
        template:'<button type="button" class="btn btn-default btn-xs"></button>'
    }
}

function ModalCancelButton(){
    return {
        restrict:"E",
        replace:true,
        template:'<button class="btn btn-default btn-xs" ng-click="$hide()" translate="cancel"></button>'
    }
}

function ModalSaveButton(){
    return {
        restrict:"E",
        replace:true,
        template:'<button class="btn btn-default btn-xs" ng-disabled="editForm.$invalid" ng-click="save()" translate="save"></button>'
    }
}

function ModelButtonFooter(){
    return {
        restrict:"E",
        replace:true,
        template:'<div class="modal-footer">\
        <modal-cancel-button></modal-cancel-button>\
        <modal-save-button></modal-save-button>\
        </div>'
    }
}

function NumbersOnly(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {
            modelCtrl.$parsers.push(function (inputValue) {
                // this next if is necessary for when using ng-required on your input.
                // In such cases, when a letter is typed first, this parser will be called
                // again, and the 2nd time, the value will be undefined
                if (inputValue == undefined) return '';
                //var transformedInput = inputValue.replace(/[^0-9]/g, '');
                var transformedInput = inputValue.replace(/[^?][^0-9]/g, '');//先可以允許輸入-
                //var transformedInput = inputValue.replace(/^[-]?[0-9]/g, '');
                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
}

/**
 * 用來比對二次輸入密碼是否相同
 * @returns {{restrict: string, require: string, scope: {match: string}, link: Function}}
 * @constructor
 */
function MatchValidate(){
    //http://ithelp.ithome.com.tw/question/10158385?tag=rss.qu
    return {
        restrict: 'A',
        // 必須使用 ngModel 取得 input value
        require: 'ngModel',
        scope: {
            // pass 想要比對的值
            match: '='
        },
        link: function (scope, element, attrs, ngModel) {
            // 假如沒有 ngModel 就不用檢查了..
            if (!ngModel) return;

            // $parsers 是檢查使用者輸入的 value
            ngModel.$parsers.unshift(checker);

            // $formatter 是檢查來自程式的改變
            ngModel.$formatters.unshift(checker);

            // watch 第一個 input value 的改變
            scope.$watch('match', function () {
                checker(ngModel.$viewValue);
            });

            function checker (value) {
                if (ngModel.$pristine && value) {
                    // 更新 input $dirty 跟 $pristine
                    // 因為非使用者變更不會影響到 $dirty 跟 $pristine
                    ngModel.$pristine = false;
                    ngModel.$dirty = true;
                }

                // 測試 value
                var valid = scope.match === value;

                // 重要：設定 validation 是  true 還是 false
                ngModel.$setValidity('matchValidate', valid);

                // return value anyway,
                // 因為我只要確保 input validation, 不會真正取用 input value
                return value;
            }

        }
    }
}
/**
 * 當勾選時只顯示某property(predicate)的value為1的tr(可參考DailyTemp.html)
 * @param stConfig
 * @param $log
 * @returns {{require: string, restrict: string, template: string, link: Function}}
 * @constructor
 */
function CheckboxFilter(stConfig, $log){
    return {
        require:"^stTable",
        restrict: 'E',
        template: "<input type='checkbox' ng-model='selected' parse-int  ng-true-value='1' ng-false-value='0' ng-checked='selected===1'/>",
        link: function (scope, element, attr, ctrl) {
            scope.selected = 0;
            var predicateName = attr.predicate;
            element.bind('change', function () {
                //$log.info(element.val());
                var query = {};
                query.selected = true;
                query.value = scope.selected;
                $log.info(query);
                scope.$apply(function () {
                    ctrl.search(query, predicateName)
                });
            });
        }
    }
}
/**
 * smart table用的startWith filter(現在沒使用)
 * @param stConfig
 * @param $timeout
 * @param $parse
 * @param $log
 * @returns {{require: string, link: Function}}
 * @constructor
 */
function TextStartWith(stConfig, $timeout, $parse, $log){
    return {
        require:"^stTable",
        link: function (scope, element, attr, ctrl) {
            var tableCtrl = ctrl;
            var inputs = element.find('input');
            var input = angular.element(inputs[0]);
            var predicateName = attr.predicate;
            element.bind('change', function () {
                var query = {};
                query.startWith = element.val();

                scope.$apply(function () {
                    ctrl.search(query, predicateName)
                });
            });
        }
    }
}

/**
 * angular-bootstrap的datepicker用的指令
 * 現在已經改用angularstrap的datepicker,所以沒有用處了
 * @param $log
 * @returns {{scope: {open: string}, link: Function}}
 * @constructor
 */
function DatePickerOpen($log){
    return{
        scope:{
            open:"=openStatus"
        },
        link: function (scope, element, attr) {
            element.on("click", function (event) {
                scope.open = true;
            });
        }
    }
}

function Focus($timeout){
    return {
        scope : {
            trigger : '@focus'
        },
        link : function(scope, element) {
            scope.$watch('trigger', function(value) {
                if (value === "true") {
                    $timeout(function() {
                        element[0].focus();
                    });
                }
            });
        }
    };
}