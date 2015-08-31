/**
 * Created by user on 2015/8/19.
 */
backendApp.controller("DailyController", DailyController);
function DailyController($scope, $translatePartialLoader, $translate, $log, SymbolTradableDailyService, ExchangeService) {
    $translatePartialLoader.addPart("daily");
    $translate.refresh();

    $scope.exchangeChange = function () {
        $scope.selectedMainSymbol = $scope.selectedExchange.systemMainSymbolList[0];
        $scope.mainSymbolChange();
    };

    $scope.mainSymbolChange = function () {
        $scope.getList();
    };

    $scope.getList = function () {
        var params = {
            exchange_id: $scope.selectedMainSymbol.exchangeId,
            main_symbol_id: $scope.selectedMainSymbol.mainSymbolId
        };
        SymbolTradableDailyService.query(params, {}, function (data) {
            $scope.rowCollection = data;
            $scope.displayedCollection = [].concat($scope.rowCollection);
        });
    };

    $scope.getExchangeList = function () {
        ExchangeService.getList().then(function (data) {
            $scope.exchangeList = data;
            $scope.selectedExchange = $scope.exchangeList[0];
            $scope.exchangeChange();
        });
    };

    $scope.getExchangeList();
}