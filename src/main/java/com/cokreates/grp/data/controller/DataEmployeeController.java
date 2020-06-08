package com.cokreates.grp.data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.service.DataEmployeeService;
import com.cokreates.grp.data.util.DataUtil;
import com.cokreates.grp.data.util.JsonUtil;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DataEmployeeController<T> {

    @Autowired
    DataEmployeeService dataEmployeeService;

    @Autowired
    DataCustomRepository repository;

    @Autowired
    DataUtil dataUtil;

    @Autowired
    JsonUtil jsonUtil;

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

    @PostMapping("/testing")
    @ResponseBody
    public String testController(@RequestBody Map<String, Object> requestBody){

        /**
         * {
            "body"	 : {
                "nodePath" : [
                    "1", "5", "7"
                ],
                "input" : {
                    "id" : "12" ,
                    "value" : "12310231"
                }
            }
        }
         */

        String jsonString = "{\"1\" : {\"2\" : {\"3\" : 4}    , \"5\" : {\"6\" : [\"q\", \"s\"], \"7\" : [{\"id\" : \"2\", \"value\" : \"200\"},{\"id\" : \"3\", \"value\" : \"300\"},{\"id\" : \"6\", \"value\" : \"300\"}]} }}";
        JSONObject destinationNode = new JSONObject(jsonString);

        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        JSONObject inputNode = jsonBody.getJSONObject("input");
        JSONArray nodePath = jsonBody.getJSONArray("nodePath");

        // jsonUtil.updateNode(destinationNode, nodePath, inputNode);
        // jsonUtil.listUpdateNode("id", destinationNode, nodePath, inputNode);
        // jsonUtil.listAppendNode(destinationNode, nodePath, inputNode);
        // jsonUtil.listRemoveNode("id", "6", destinationNode, nodePath);
        // log.warn(jsonUtil.getJsonNode(destinationNode, nodePath).toString());
        // log.warn(jsonUtil.getJsonArray(destinationNode, nodePath).toString());
        // log.warn(jsonUtil.getNodeFromList("id", "6", destinationNode, nodePath).toString());

        return destinationNode.toString();
    }
}
