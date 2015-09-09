package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.BoRole;
import com.jelly.jt8.bo.model.BoRolePermission;
import com.jelly.jt8.bo.service.BoRoleService;
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
@RequestMapping("/boRoles")
public class BoRoleController extends BaseController {
    @Autowired
    @Qualifier("boRoleService")
    private BoRoleService service;

    @RequestMapping( method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getRoleList() {
        Gson gson = new Gson();
        List<BoRole> list = null;
        String payload = "";
        try {
            list = service.select();
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping( method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> addRole(@RequestBody BoRole object) {
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
    ResponseEntity<String> updateRole(@PathVariable("id") String id, @RequestBody BoRole object) {
        System.out.println("updateRole");
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
    ResponseEntity<String> deleteRole(@PathVariable("id") String id, @RequestBody BoRole object) {
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

    @RequestMapping(value = "{id}/permission", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getRolePermissionList(@PathVariable("id") int id ) {
        System.out.println("selectRolePermission");
        Gson gson = new Gson();
        List<BoRolePermission> list = null;
        String payload = "";

        try {
            list = service.selectRolePermission(id);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}/permission", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> allocatePermission(@RequestBody BoRole object) {
        System.out.println("allocatePermission");
        Gson gson = new Gson();
        String payload = "";
        try {
            service.allocatePermission(object);
        } catch (Exception e) {
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(object);
        return getResponseEntity(payload);
    }
}
