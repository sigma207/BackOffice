package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.BoUser;
import com.jelly.jt8.bo.model.BoUserRole;
import com.jelly.jt8.bo.service.BoUserService;
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
@RequestMapping("/boUsers")
public class BoUserController extends BaseController {

    @Autowired
    @Qualifier("boUserService")
    private BoUserService service;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> getList() {
        Gson gson = new Gson();
        List<BoUser> list = null;
        String payload = "";
        try {
            list = service.select();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }


        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "parentUserId",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="parentUserId") int parentUserId) {
        Gson gson = new Gson();
        List<BoUser> list = null;
        String payload = "";
        try {
            list = service.selectChildren(parentUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> addRole(@RequestBody BoUser object){
        Gson gson = new Gson();
        String payload = "";
        try {
            service.insert(object);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<String> deleteRole(@PathVariable("id") String id, @RequestBody BoUser object){
        Gson gson = new Gson();
        String payload = "";
        try {
            service.delete(object);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}/userRoles", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> selectUserRole(@PathVariable("id") int id) {
        Gson gson = new Gson();
        List<BoUserRole> list = null;
        String payload = "";
        try {
            list = service.selectUserRole(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(value="{id}/userRoles",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> allocateUserRole(@PathVariable("id") String id, @RequestBody BoUser object){
        Gson gson = new Gson();
        String payload = "";
        try {
            service.allocateUserRole(object);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }
}
