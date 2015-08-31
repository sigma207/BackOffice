package com.jelly.jt8.bo.controller;

import com.google.gson.Gson;
import com.jelly.jt8.bo.model.BoOrganization;
import com.jelly.jt8.bo.service.BoOrganizationService;
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
@RequestMapping("/boOrganization")
public class BoOrganizationController extends BaseController{
    @Autowired
    @Qualifier("boOrganizationService")
    private BoOrganizationService service;

    @RequestMapping( method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList() {
        Gson gson = new Gson();
        List<BoOrganization> list = null;
        String payload = "";
        try {
            list = service.select();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}",  method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> get(@PathVariable("id") int id) {
        Gson gson = new Gson();
        BoOrganization organization = null;
        String payload = "";
        try {
            organization = service.select(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(organization);
        return getResponseEntity(payload);
    }

    @RequestMapping(value = "{id}/with_children",  method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> getList(@PathVariable("id") int id) {
        Gson gson = new Gson();
        List<BoOrganization> list = null;
        String payload = "";
        try {
            list = service.selectWithChildren(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson(exceptionToJson(e)), HttpStatus.SERVICE_UNAVAILABLE);
        }

        payload = gson.toJson(list);
        return getResponseEntity(payload);
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> add(@RequestBody BoOrganization object) {
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
    ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody BoOrganization object) {
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
    ResponseEntity<String> delete(@PathVariable("id") int id, @RequestBody BoOrganization object) {
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
