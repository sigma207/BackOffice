package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.MainSymbol;
import com.jelly.jt8.bo.model.SymbolHoliday;
import com.jelly.jt8.bo.model.SymbolHolidayException;
import com.jelly.jt8.bo.service.HolidayExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user on 2015/8/31.
 */
@Controller
@RequestMapping("/symbolHolidayException")
public class SymbolHolidayExceptionController extends BaseController{
    @Autowired
    @Qualifier("holidayExceptionService")
    private HolidayExceptionService service;

    @RequestMapping( params = {"exchangeId","mainSymbolId"}, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="exchangeId") String exchangeId,@RequestParam(value="mainSymbolId") String mainSymbolId) {
        Gson gson = new Gson();
        List<SymbolHolidayException> list = null;
        String payload = "";
        MainSymbol mainSymbol = new MainSymbol();
        mainSymbol.setExchange_id(exchangeId);
        mainSymbol.setMain_symbol_id(mainSymbolId);
        try {
            list = service.selectHolidayException(mainSymbol);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> add(@RequestBody List<SymbolHolidayException> list) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.insertHolidayException(list);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody SymbolHolidayException symbolHolidayException) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.updateHolidayException(symbolHolidayException);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(symbolHolidayException);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<String> delete(@PathVariable("id") int id, @RequestBody SymbolHolidayException symbolHolidayException) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.deleteHolidayException(symbolHolidayException);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(symbolHolidayException);
        return getResponseEntity(payload);
    }
}
