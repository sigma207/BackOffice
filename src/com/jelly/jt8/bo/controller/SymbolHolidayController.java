package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.SymbolHoliday;
import com.jelly.jt8.bo.model.SystemMainSymbol;
import com.jelly.jt8.bo.service.HolidayExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user on 2015/8/30.
 */
@Controller
@RequestMapping("/symbolHoliday")
public class SymbolHolidayController extends BaseController {
    @Autowired
    @Qualifier("holidayExceptionService")
    private HolidayExceptionService service;

    @RequestMapping( params = {"exchangeId","mainSymbolId"}, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="exchangeId") String exchangeId,@RequestParam(value="mainSymbolId") String mainSymbolId) {
        Gson gson = new Gson();
        List<SymbolHoliday> list = null;
        String payload = "";
        SystemMainSymbol mainSymbol = new SystemMainSymbol();
        mainSymbol.setExchangeId(exchangeId);
        mainSymbol.setMainSymbolId(mainSymbolId);
        try {
            list = service.selectHoliday(mainSymbol);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> add(@RequestBody List<SymbolHoliday> list) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.insertHoliday(list);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody SymbolHoliday symbolHoliday) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.updateHoliday(symbolHoliday);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(symbolHoliday);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<String> delete(@PathVariable("id") int id, @RequestBody SymbolHoliday symbolHoliday) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.deleteHoliday(symbolHoliday);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(symbolHoliday);
        return getResponseEntity(payload);
    }
}
