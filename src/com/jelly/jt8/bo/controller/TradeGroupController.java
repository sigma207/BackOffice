package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.TradeGroup;
import com.jelly.jt8.bo.service.TradeGroupService;
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
@RequestMapping("/tradeGroup")
public class TradeGroupController extends BaseController{
    @Autowired
    @Qualifier("tradeGroupService")
    private TradeGroupService service;

    @RequestMapping( method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList() {
        Gson gson = new Gson();
        List<TradeGroup> list = null;
        String payload = "";
        try {
            list = service.select();
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping( params = {"category","ownerId"},method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="category") String category,@RequestParam(value="ownerId") int ownerId) {
        Gson gson = new Gson();
        List<TradeGroup> list = null;
        String payload = "";
        try {
            list = service.select(category,ownerId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping( params = {"ownerId"},method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="ownerId") int ownerId) {
        Gson gson = new Gson();
        List<TradeGroup> list = null;
        String payload = "";
        try {
            list = service.select(ownerId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> add(@RequestBody TradeGroup object) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.insert(object);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody TradeGroup object) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.update(object);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<String> delete(@PathVariable("id") int id, @RequestBody TradeGroup object) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.delete(object);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }
}
