/**
 * Created by user on 2015/8/9.
 */
backendApp.controller("HolidayController", HolidayController);
function HolidayController($scope, $translatePartialLoader, $translate, $log, $modal, ExchangeService, Restangular, SymbolHolidayService, SymbolHolidayExceptionService) {
    $translatePartialLoader.addPart("holiday");
    $translate.refresh();

    $scope.exchangeChange = function () {
        $scope.selectedMainSymbol = $scope.selectedExchange.mainSymbolList[0];
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
            exchangeId:$scope.selectedMainSymbol.exchange_id,
            mainSymbolId:$scope.selectedMainSymbol.main_symbol_id
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
            exchangeId:$scope.selectedMainSymbol.exchange_id,
            mainSymbolId:$scope.selectedMainSymbol.main_symbol_id
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
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'holidayEdit.html',
            controller: 'holidayEditCtrl',
            size: $scope.editSize,
            resolve: {
                title: function () {
                    return $scope.modalTitle;
                },
                editObj: function () {
                    return Restangular.copy(row);
                }
            }
        });

        modalInstance.result.then(
            function () {
                $scope.getHoliday();
            },
            function () {
            }
        )
    };

    $scope.editExceptionClick = function (row) {
        $scope.modalTitle = $translate.instant("editException");
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'exceptionEdit.html',
            controller: 'exceptionEditCtrl',
            size: $scope.editSize,
            resolve: {
                title: function () {
                    return $scope.modalTitle;
                },
                editObj: function () {
                    return Restangular.copy(row);
                }
            }
        });

        modalInstance.result.then(
            function () {
                $scope.getException();
            },
            function () {
            }
        )
    };

    $scope.batchAddHolidayClick = function () {
        $scope.modalTitle = $translate.instant("addHoliday");
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'batchHoliday.html',
            controller: 'batchHolidayCtrl',
            size: $scope.editSize,
            resolve: {
                title: function () {
                    return $scope.modalTitle;
                },
                mainSymbol: function () {
                    return $scope.selectedMainSymbol;
                }
            }
        });

        modalInstance.result.then(
            function () {
                $scope.getHoliday();
            },
            function () {
            }
        )
    };

    $scope.batchAddExceptionClick = function () {
        $scope.modalTitle = $translate.instant("addException");
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'batchException.html',
            controller: 'batchExceptionCtrl',
            size: $scope.editSize,
            resolve: {
                title: function () {
                    return $scope.modalTitle;
                },
                mainSymbol: function () {
                    return $scope.selectedMainSymbol;
                }
            }
        });

        modalInstance.result.then(
            function () {
                $scope.getException();
            },
            function () {
            }
        )
    };

    $scope.getExchangeList();
}

backendApp.controller("holidayEditCtrl", function ($scope, $modalInstance, $log, title, editObj) {
    $scope.title = title;
    $scope.editObj = editObj;
    $scope.save = function () {
        DateTool.dateToYyyyMMdd($scope.editObj,"begin_date","beginDate");
        DateTool.dateToYyyyMMdd($scope.editObj,"end_date","endDate");

        $scope.editObj.put().then(function (data) {
            $modalInstance.close(data);
        });
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

backendApp.controller("exceptionEditCtrl", function ($scope, $modalInstance, $log, title, editObj) {
    $scope.title = title;
    $scope.editObj = editObj;
    $scope.save = function () {
        DateTool.dateToYyyyMMdd($scope.editObj,"calendarDate","calendar");
        $scope.editObj.put().then(function (data) {
            $modalInstance.close(data);
        });
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

backendApp.controller('batchHolidayCtrl', function ($scope, $modalInstance, $log, title, mainSymbol, SymbolHolidayService) {
    $scope.title = title;

    $scope.init = function () {
        $scope.rowCollection = [];
        $scope.addRow();
    };

    $scope.getNewRow = function () {
        return {
            exchangeId:mainSymbol.exchange_id,
            mainSymbolId:mainSymbol.main_symbol_id
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
            $modalInstance.close(data);
        });
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

backendApp.controller('batchExceptionCtrl', function ($scope, $modalInstance, $log, title, mainSymbol, SymbolHolidayExceptionService) {
    $scope.title = title;

    $scope.init = function () {
        $scope.rowCollection = [];
        $scope.addRow();
    };

    $scope.getNewRow = function () {
        return {
            exchangeId:mainSymbol.exchange_id,
            mainSymbolId:mainSymbol.main_symbol_id
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
            $modalInstance.close(data);
        });
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});