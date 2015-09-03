package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.BoUser;
import com.jelly.jt8.bo.service.BoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2015/8/24.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Autowired
    @Qualifier("boUserService")
    private BoUserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> login( @RequestBody BoUser user,HttpServletRequest request){
        String payload = "";
        BoUser loginUser = null;
        System.out.println(request.getSession().getId());
        Gson gson = new Gson();
        try {
            loginUser = userService.login(user.getLoginId(),user.getPassword());
        } catch (Exception e){
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(loginUser);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "loginId",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> login( @RequestParam(value="loginId") String loginId){
        String payload = "";
        BoUser loginUser = null;
        Gson gson = new Gson();
        try {
            loginUser = userService.fastLogin(loginId);
        } catch (Exception e){
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(loginUser);
        return getResponseEntity(payload);
    }
}
