package com.cokreates.grp.data.controller;


import java.util.Map;
import com.cokreates.grp.data.constants.Api;
import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.service.DataEmployeeService;
import com.cokreates.grp.data.util.DataUtil;
import com.cokreates.grp.data.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DataEmployeeController {

    
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

    @RequestMapping(value = Api.GET_EMP, method = RequestMethod.POST)
    @ResponseBody
    public String getEmployee(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        String response = dataEmployeeService.getEmployee(requestParam);
        return response;
    }

    @RequestMapping(value = Api.READ_NODE_FROM_EMPLOYEE_DOC, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readNodeFromEmployeeDoc(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readNodeFromEmployeeDoc(jsonBody);
    }

    @RequestMapping(value = Api.READ_NODE_IN_LIST_FROM_EMPLOYEE_DOC, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readNodeInListFromEmployeeDoc(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        System.out.println(jsonBody.toString());
        return dataEmployeeService.readNodeInListFromEmployeeDoc(jsonBody);
    }

    @RequestMapping(value = Api.READ_FROM_APPROVAL_HISTORY_BY_ACTOR, 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readFromApprovalHistoryByActor(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.readFromApprovalHistoryByActor(jsonBody);
        return response;
    }

    @RequestMapping(value = Api.READ_FROM_APPROVAL_HISTORY_BY_EMPLOYEE, 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readFromApprovalHistoryByEmployee(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.readFromApprovalHistoryByEmployee(jsonBody);
        return response;
    }

    @RequestMapping(value = Api.READ_FROM_APPROVAL_HISTORY_BY_STATUS, 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readFromApprovalHistoryByStatus(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.readFromApprovalHistoryByStatus(jsonBody);
        return response;
    }

    @RequestMapping(value = Api.READ_FROM_APPROVAL_HISTORY, 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readFromApprovalHistory(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = null;
        if (jsonBody.has("employeeOid") && jsonBody.has("status")){
            response = this.readFromApprovalHistoryByEmployeeAndStatus(requestBody);
        }
        else if (jsonBody.has("employeeOid")){
            response = this.readFromApprovalHistoryByEmployee(requestBody);
        }
        else if (jsonBody.has("status")){
            response = this.readFromApprovalHistoryByStatus(requestBody);
        }
        
        return response;
    }

    @RequestMapping(value = Api.READ_FROM_APPROVAL_HISTORY_BY_EMPLOYEE_AND_STATUS, 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readFromApprovalHistoryByEmployeeAndStatus(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.readFromApprovalHistoryByEmployeeAndStatus(jsonBody);
        return response;
    }

    @RequestMapping(value = Api.GET_EMPLOYEES, 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getEmployees(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.getEmployees(jsonBody);
        return response;
    }


    



    @PostMapping("/testing")
    @ResponseBody
    public String testController(@RequestBody Map<String, Object> requestBody) {

        /**
         * { "body" : { "nodePath" : [ "1", "5", "7" ], "input" : { "id" : "12" ,
         * "value" : "12310231" } } }
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
        // log.warn(jsonUtil.getNodeFromList("id", "6", destinationNode,
        // nodePath).toString());

        return destinationNode.toString();
    }
}
