package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.TradeAccountGroup;
import com.jelly.jt8.bo.service.TradeAccountGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user on 2015/8/26.
 */
@Controller
@RequestMapping("/tradeAccountGroup")
public class TradeAccountGroupController extends BaseController{
    @Autowired
    @Qualifier("tradeAccountGroupService")
    private TradeAccountGroupService service;

    @RequestMapping( method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList() {
        Gson gson = new Gson();
        List<TradeAccountGroup> list = null;
        String payload = "";
        try {
            list = service.select();
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> add(@RequestBody TradeAccountGroup tradeAccountGroup) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.insert(tradeAccountGroup);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(tradeAccountGroup);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody TradeAccountGroup tradeAccountGroup) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.update(tradeAccountGroup);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(tradeAccountGroup);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<String> delete(@PathVariable("id") int id, @RequestBody TradeAccountGroup tradeAccountGroup) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.delete(tradeAccountGroup);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(tradeAccountGroup);
        return getResponseEntity(payload);
    }
}
