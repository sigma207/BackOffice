package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.TradeBankbook;
import com.jelly.jt8.bo.service.TradeBankbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user on 2015/9/22.
 */
@Controller
@RequestMapping("/tradeBankbooks")
public class TradeBankbookController extends BaseController {
    @Autowired
    @Qualifier("tradeBankbookService")
    private TradeBankbookService service;

    @RequestMapping(params = {"accountId","beginDate","endDate"},method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="accountId") String accountId,@RequestParam(value="beginDate") String beginDate,@RequestParam(value="endDate") String endDate) {
        Gson gson = new Gson();
        List<TradeBankbook> list = null;
        String payload = "";
        try {
            list = service.select(accountId,beginDate,endDate);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "accountId" ,method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="accountId") String accountId) {
        Gson gson = new Gson();
        TradeBankbook object = null;
        String payload = "";
        try {
            object = service.selectLast(accountId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }

    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> add(@RequestBody TradeBankbook object){
        Gson gson = new Gson();
        String payload = "";
        try {
            service.insert(object);
        } catch (Exception e){
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }
}
