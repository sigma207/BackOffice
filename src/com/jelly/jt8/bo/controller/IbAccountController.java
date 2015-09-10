package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.BoUser;
import com.jelly.jt8.bo.service.IbAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user on 2015/9/9.
 */
@Controller
@RequestMapping("/ibAccounts")
public class IbAccountController extends BaseController {
    @Autowired
    @Qualifier("ibAccountService")
    private IbAccountService service;

    @RequestMapping(value = "{userId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> get(@PathVariable("userId") int userId) {
        Gson gson = new Gson();
        BoUser ib =null;
        String payload = "";
        try {
            ib = service.selectIb(userId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(ib);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "userId",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="userId") int userId) {
        Gson gson = new Gson();
        List<BoUser> list = null;
        String payload = "";
        try {
            list = service.selectIbChildren(userId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> add(@RequestBody BoUser object){
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
    ResponseEntity<String> delete(@PathVariable("id") String id, @RequestBody BoUser object){
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
    ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody BoUser object){
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
