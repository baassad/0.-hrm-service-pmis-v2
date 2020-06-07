package com.cokreates.grp.data.controller;

import java.util.Iterator;
import java.util.Map;

import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.service.DataEmployeeService;
import com.cokreates.grp.data.util.DataUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataEmployeeController {

    @Autowired
    DataEmployeeService dataEmployeeService;

    @Autowired
    DataCustomRepository repository;

    @Autowired
    DataUtil dataUtil;

    @RequestMapping("/hrm/pmis/get/v1/all-emp")
    @ResponseBody
    public String getAllEmployee() {
        return repository.getAllEmployees();
    }

    @RequestMapping(value = "/hrm/pmis/get/v1/emp", method = RequestMethod.POST)
    @ResponseBody
    public String getEmployee(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        String response = dataEmployeeService.getEmployee(requestParam);
        return response;
    }

    @RequestMapping(value = "hrm/pmis/get/v1/node-in-emp-doc", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?>  getNodeFromEmployeeDoc(@RequestBody Map<String, Object> requestBody) {
        try{
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        String response =  dataEmployeeService.getNodeFromeDoc(jsonBody);
        return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception ex){
            String errorMessage;
            errorMessage = "Error at API: hrm/pmis/get/v1/node-in-emp-doc, " + ex;
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
