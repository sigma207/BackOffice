package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.TradeLoginAccount;
import com.jelly.jt8.bo.service.TradeLoginInAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user on 2015/9/4.
 */
@Controller
@RequestMapping("/tradeLoginInAccounts")
public class TradeLoginAccountController extends BaseController{
    @Autowired
    @Qualifier("tradeLoginInAccountService")
    private TradeLoginInAccountService service;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> getList() {
        Gson gson = new Gson();
        List<TradeLoginAccount> list = null;
        String payload = "";
        try {
            list = service.select();
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "userId",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="userId") int userId) {
        Gson gson = new Gson();
        List<TradeLoginAccount> list = null;
        String payload = "";
        try {
            list = service.select(userId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> add(@RequestBody TradeLoginAccount object){
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

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<String> delete(@PathVariable("id") String id, @RequestBody TradeLoginAccount object){
        Gson gson = new Gson();
        String payload = "";
        try {
            service.delete(object);
        } catch (Exception e){
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody TradeLoginAccount object){
        Gson gson = new Gson();
        String payload = "";
        try {
            service.update(object);
        } catch (Exception e){
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }
}
