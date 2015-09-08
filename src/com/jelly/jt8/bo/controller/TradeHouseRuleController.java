package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.TradeHouseRule;
import com.jelly.jt8.bo.service.TradeHouseRuleService;
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
@RequestMapping("/tradeHouseRule")
public class TradeHouseRuleController extends BaseController {
    @Autowired
    @Qualifier("tradeHouseRuleService")
    private TradeHouseRuleService service;

    @RequestMapping( method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList() {
        Gson gson = new Gson();
        List<TradeHouseRule> list = null;
        String payload = "";
        try {
            list = service.select();
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping( params = "userId", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="userId") int userId) {
        Gson gson = new Gson();
        List<TradeHouseRule> list = null;
        String payload = "";
        try {
            list = service.select4HouseRule(userId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping( params = "ibUserId", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getAddList(@RequestParam(value="ibUserId") int ibUserId) {
        Gson gson = new Gson();
        List<TradeHouseRule> list = null;
        String payload = "";
        try {
            list = service.select4IbInsert(ibUserId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping( params = {"ibUserId","parentIbUserId"}, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getEditList(@RequestParam(value="ibUserId") int ibUserId,@RequestParam(value="parentIbUserId") int parentIbUserId) {
        Gson gson = new Gson();
        List<TradeHouseRule> list = null;
        String payload = "";
        try {
            list = service.select4IbUpdate(ibUserId, parentIbUserId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "userId",method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> add(@RequestParam(value="userId") int userId, @RequestBody TradeHouseRule object) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.insert(object,userId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "userId",value = "{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> update(@RequestParam(value="userId") int userId,@PathVariable("id") int id, @RequestBody TradeHouseRule object) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.update(object,userId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "userId",value = "{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<String> delete(@RequestParam(value="userId") int userId,@PathVariable("id") int id, @RequestBody TradeHouseRule object) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.delete(object,userId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }
}
