/**
 * Created by user on 2015/8/9.
 */
backendApp.controller("HolidayController", HolidayController);
function HolidayController($scope, $translatePartialLoader, $translate, $log, $modal, ExchangeService, Restangular, SymbolHolidayService, SymbolHolidayExceptionService) {
    $translatePartialLoader.addPart("holiday");
    $translate.refresh();

    $scope.exchangeChange = function () {
        $scope.selectedMainSymbol = $scope.selectedExchange.systemMainSymbolList[0];
        $scope.mainSymbolChange();
    };

    $scope.mainSymbolChange = function () {
        $scope.getHoliday();
        $scope.getException();
    };

    $scope.getExchangeList = function () {
        ExchangeService.getList().then(function (data) {
            $scope.exchangeList = data;
            $scope.selectedExchange = $scope.exchangeList[0];
            $scope.exchangeChange();
        });
    };

    $scope.getHoliday = function () {
        var params = {
            exchangeId:$scope.selectedMainSymbol.exchangeId,
            mainSymbolId:$scope.selectedMainSymbol.mainSymbolId
        };
        SymbolHolidayService.getList(params).then(function (data) {
            for(var i= 0,count=data.length;i<count;i++){
                DateTool.yyyyMMddToDate(data[i],"beginDate","begin_date");
                DateTool.yyyyMMddToDate(data[i],"endDate","end_date");
            }
            $scope.holidayCollection = data;
        });
    };

    $scope.getException = function () {
        var params = {
            exchangeId:$scope.selectedMainSymbol.exchangeId,
            mainSymbolId:$scope.selectedMainSymbol.mainSymbolId
        };
        SymbolHolidayExceptionService.getList(params).then(function (data) {
            for(var i= 0,count=data.length;i<count;i++){
                DateTool.yyyyMMddToDate(data[i],"calendar","calendarDate");
            }
            $scope.exceptionCollection = data;
        });
    };

    $scope.removeHolidayClick = function (row) {
        row.remove().then(function (data) {
            $scope.getHoliday();
        });
    };

    $scope.removeExceptionClick = function (row) {
        row.remove().then(function (data) {
            $scope.getException();
        });
    };

    $scope.editHolidayClick = function (row) {
        $scope.modalTitle = $translate.instant("editHoliday");
        $scope.editObj = Restangular.copy(row);
        $scope.showHolidayEditModal();
    };

    $scope.editExceptionClick = function (row) {
        $scope.modalTitle = $translate.instant("editException");
        $scope.editObj = Restangular.copy(row);
        $scope.showExceptionEditModal();
    };

    $scope.batchAddHolidayClick = function () {
        $scope.modalTitle = $translate.instant("addHoliday");
        $scope.showBatchHolidayModal();
    };

    $scope.batchAddExceptionClick = function () {
        $scope.modalTitle = $translate.instant("addException");
        $scope.showBatchExceptionModal();
    };

    //editHolidayModal
    $scope.editHolidayModalClose = function () {
        $scope.getHoliday();
        $scope.hideHolidayEditModal();
    };

    function EditHolidayModalController($scope){
        $scope.save = function () {
            DateTool.dateToYyyyMMdd($scope.editObj,"begin_date","beginDate");
            DateTool.dateToYyyyMMdd($scope.editObj,"end_date","endDate");

            $scope.editObj.put().then(function (data) {
                $scope.editHolidayModalClose();
            });
        };
    }

    var editHolidayModal = $modal({
        scope: $scope,
        controller: EditHolidayModalController,
        templateUrl:"holidayEdit.html",
        show:false
    });

    $scope.showHolidayEditModal = function() {
        editHolidayModal.$promise.then(editHolidayModal.show);
    };
    $scope.hideHolidayEditModal = function() {
        editHolidayModal.$promise.then(editHolidayModal.hide);
    };

    //editExceptionModal
    $scope.editExceptionModalClose = function () {
        $scope.getException();
        $scope.hideExceptionEditModal();
    };

    function EditExceptionModalController($scope){
        $scope.save = function () {
            DateTool.dateToYyyyMMdd($scope.editObj,"calendarDate","calendar");
            $scope.editObj.put().then(function (data) {
                $scope.editExceptionModalClose();
            });
        };
    }

    var editExceptionModal = $modal({
        scope: $scope,
        controller: EditExceptionModalController,
        templateUrl:"exceptionEdit.html",
        show:false
    });

    $scope.showExceptionEditModal = function() {
        editExceptionModal.$promise.then(editExceptionModal.show);
    };
    $scope.hideExceptionEditModal = function() {
        editExceptionModal.$promise.then(editExceptionModal.hide);
    };

    //batchHolidayModal
    $scope.batchHolidayModalClose = function () {
        $scope.getHoliday();
        $scope.hideBatchHolidayModal();
    };

    function BatchHolidayModalController($scope){
        $scope.init = function () {
            $scope.rowCollection = [];
            $scope.addRow();
        };

        $scope.getNewRow = function () {
            return {
                exchangeId:$scope.selectedMainSymbol.exchangeId,
                mainSymbolId:$scope.selectedMainSymbol.mainSymbolId
            };
        };

        $scope.addClick = function (index,row) {
            $scope.addRow();
        };

        $scope.deleteClick = function (index,row) {
            $scope.rowCollection.splice(index, 1);
            if($scope.rowCollection.length==0){
                $scope.addRow();
            }
        };

        $scope.addRow = function () {
            $scope.rowCollection.push($scope.getNewRow());
        };

        $scope.save = function () {
            for(var i= 0,count = $scope.rowCollection.length;i<count;i++){
                DateTool.dateToYyyyMMdd($scope.rowCollection[i],"begin_date","beginDate");
                DateTool.dateToYyyyMMdd($scope.rowCollection[i],"end_date","endDate");
            }
            SymbolHolidayService.post($scope.rowCollection).then(function (data) {
                $scope.batchHolidayModalClose();
            });
        };
    }

    var batchHolidayModal = $modal({
        scope: $scope,
        controller: BatchHolidayModalController,
        templateUrl:"batchHoliday.html",
        show:false
    });

    $scope.showBatchHolidayModal = function() {
        batchHolidayModal.$promise.then(batchHolidayModal.show);
    };
    $scope.hideBatchHolidayModal = function() {
        batchHolidayModal.$promise.then(batchHolidayModal.hide);
    };

    //batchExceptionModal
    $scope.batchExceptionModalClose = function () {
        $scope.getException();
        $scope.hideBatchExceptionModal();
    };

    function BatchExceptionModalController($scope){
        $scope.init = function () {
            $scope.rowCollection = [];
            $scope.addRow();
        };

        $scope.getNewRow = function () {
            return {
                exchangeId:$scope.selectedMainSymbol.exchangeId,
                mainSymbolId:$scope.selectedMainSymbol.mainSymbolId
            };
        };

        $scope.addClick = function (index,row) {
            $scope.addRow();
        };

        $scope.deleteClick = function (index,row) {
            $scope.rowCollection.splice(index, 1);
            if($scope.rowCollection.length==0){
                $scope.addRow();
            }
        };

        $scope.addRow = function () {
            $scope.rowCollection.push($scope.getNewRow());
        };

        $scope.save = function () {
            for(var i= 0,count = $scope.rowCollection.length;i<count;i++){
                DateTool.dateToYyyyMMdd($scope.rowCollection[i],"calendarDate","calendar");
            }
            SymbolHolidayExceptionService.post($scope.rowCollection).then(function (data) {
                $scope.batchExceptionModalClose();
            });
        };
    }

    var batchExceptionModal = $modal({
        scope: $scope,
        controller: BatchExceptionModalController,
        templateUrl:"batchException.html",
        show:false
    });

    $scope.showBatchExceptionModal = function() {
        batchExceptionModal.$promise.then(batchExceptionModal.show);
    };
    $scope.hideBatchExceptionModal = function() {
        batchExceptionModal.$promise.then(batchExceptionModal.hide);
    };

    $scope.getExchangeList();
}