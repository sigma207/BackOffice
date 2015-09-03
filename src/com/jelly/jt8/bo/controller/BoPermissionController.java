package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jelly.jt8.bo.model.BoPermission;
import com.jelly.jt8.bo.model.BoRolePermission;
import com.jelly.jt8.bo.model.PermissionMoveSetting;
import com.jelly.jt8.bo.service.BoPermissionService;
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
@RequestMapping("/boPermission")
public class BoPermissionController extends BaseController{
    @Autowired
    @Qualifier("boPermissionService")
    private BoPermissionService service;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> getList(){
        Gson gson = new Gson();

        List<BoPermission> list = null;
        String payload = "";
        try {
            list = service.select();
        } catch (Exception e){
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(params = "filter",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> getList(@RequestParam(value="filter") String filter, @RequestBody List<BoRolePermission> boRolePermissionList){
        Gson gson = new Gson();

        List<BoPermission> list = null;
        String payload = "";
        try {
            list = service.select(boRolePermissionList);
        } catch (Exception e){
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> add(@RequestBody BoPermission object){
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

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody BoPermission object){
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

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<String> remove(@PathVariable("id") int id, @RequestBody BoPermission object){
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

    @RequestMapping( params = "move", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> move(@RequestParam(value="move") String move, @RequestBody PermissionMoveSetting permissionMoveSetting){
        Gson gson = new Gson();

        String payload = "";
        try {
            service.move(permissionMoveSetting);
        } catch (Exception e){
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(permissionMoveSetting.getMoveNodes());
        return getResponseEntity(payload);
    }
}
