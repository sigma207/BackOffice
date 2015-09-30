package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.BoRole;
import com.jelly.jt8.bo.model.TradeIpsTrans;
import com.jelly.jt8.bo.service.TradeIpsTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user on 2015/9/24.
 */
@Controller
@RequestMapping("/tradeIpsTrans")
public class TradeIpsTransController extends BaseController {
    @Autowired
    @Qualifier("tradeIpsTransService")
    private TradeIpsTransService service;

    @RequestMapping(params = {"accountId", "boStatus", "ipsStatus", "beginDate", "endDate"}, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value = "accountId") String accountId, @RequestParam(value = "boStatus") String boStatus, @RequestParam(value = "ipsStatus") String ipsStatus,
                                   @RequestParam(value = "beginDate") String beginDate, @RequestParam(value = "endDate") String endDate) {
        Gson gson = new Gson();
        List<TradeIpsTrans> list = null;
        String payload = "";
        try {
            list = service.select(accountId, boStatus, ipsStatus, beginDate, endDate);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "pass", value = "{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> pass(@PathVariable("id") String id, @RequestParam(value = "pass") String pass, @RequestBody TradeIpsTrans object) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.pass(object);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "reject", value = "{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> reject(@PathVariable("id") String id, @RequestParam(value = "reject") String reject, @RequestBody TradeIpsTrans object) {
        Gson gson = new Gson();
        String payload = "";
        try {
            service.reject(object);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }

}
