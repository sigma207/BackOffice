package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.TradeAccount;
import com.jelly.jt8.bo.service.TradeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2015/9/16.
 */
@Controller
@RequestMapping("/tradeAccounts")
public class TradeAccountController extends BaseController {
    @Autowired
    @Qualifier("tradeAccountService")
    private TradeAccountService service;

    @RequestMapping(params = "loginId", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="loginId") String loginId) {
        Gson gson = new Gson();
        List<TradeAccount> list = null;
        String payload = "";
        try {
            list = service.select(loginId);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }
}
