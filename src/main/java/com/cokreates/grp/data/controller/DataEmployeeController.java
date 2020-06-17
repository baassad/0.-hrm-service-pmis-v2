package com.cokreates.grp.data.controller;


import java.util.Map;
import com.cokreates.grp.data.constants.Api;
import com.cokreates.grp.data.service.DataEmployeeService;
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


    @RequestMapping(value = Api.CREATE_EMP,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> createEmployee(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParameters = new JSONObject(requestBody).getJSONObject("body");
        
        JSONObject inputNode = requestParameters.getJSONObject("general");
        JSONArray path = new JSONArray("[\"personal\", \"general\"]");
        ResponseEntity<?> response = dataEmployeeService.createEmployee(inputNode, path, requestParameters);
        return response;
    }


    @RequestMapping(value = Api.IMPORT_EMP,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> importEmployee(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParameters = new JSONObject(requestBody).getJSONObject("body");

        JSONObject inputNode = requestParameters.getJSONObject("node");
        JSONArray path = new JSONArray("[\"personal\", \"general\"]");
        
        ResponseEntity<?> response = dataEmployeeService.importEmployee(inputNode, path, requestParameters);
        return response;
    }


    @RequestMapping(value = Api.GET_EMPLOYEE,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> getEmployee(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.getEmployee(requestParam);
    }

    @RequestMapping(value = Api.READ_EMPLOYEE_DETAILS,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> readEmployeeDetails(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readEmployeeDetails(requestParam);
    }

    @RequestMapping(value = Api.READ_MAIN_EMPLOYEE_BY_OFFICE_OFFICE_UNIT,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> readMainEmployeeByOfficeOfficeUnit(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readMainEmployeeByOfficeOfficeUnit(requestParam);
    }

    @RequestMapping(value = Api.READ_MAIN_EMPLOYEE_BY_OFFICE,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> readMainEmployeeByOffice(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readMainEmployeeByOffice(requestParam);
    }

    @RequestMapping(value = Api.READ_MAIN_EMPLOYEE_BY_OID_SET,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> readMainEmployeeByOidSet(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readMainEmployeeByOidSet(requestParam);
    }

    @RequestMapping(value = Api.READ_NODE_FROM_EMPLOYEE_DOC_BY_OID_SET,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> readNodeFromEmployeeDocByOidSet(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readNodeFromEmployeeDocByOidSet(requestParam);
    }

    @RequestMapping(value = Api.READ_NODE_FROM_EMPLOYEE_DOC, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readNodeFromEmployeeDoc(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readNodeFromEmployeeDoc(jsonBody);
    }

    @RequestMapping(value = Api.READ_NODE_IN_LIST_FROM_EMPLOYEE_DOC, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readNodeInListFromEmployeeDoc(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readNodeInListFromEmployeeDoc(jsonBody);
    }

    @RequestMapping(value = Api.GET_EMPLOYEE_OFFICE, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getEmployeeOffice(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.getEmployeeOffice(jsonBody);
    }

    @RequestMapping(value = Api.READ_EMPLOYEE_BY_OFFICE, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readEmployeeByOffice(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readEmployeeByOffice(jsonBody);
    }

    @RequestMapping(value = Api.READ_OFFICE_BY_EMPLOYEE, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readOfficeByEmployee(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readOfficeByEmployee(jsonBody);
    }

    @RequestMapping(value = Api.READ_EMPLOYEE_OFFICE_BY_OFFICE, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readEmployeeOfficeByOffice(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.readEmployeeOfficeByOffice(jsonBody);
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


    @RequestMapping(value = Api.UPDATE_NODE_IN_DOCUMENT_FOR_REQUEST, 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateNodeInDocumentForRequest(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.updateNodeInDocumentForRequest(jsonBody);
        return response;
    }

    @RequestMapping(value = Api.UPDATE_NODE_EMPLOYEE_OFFICE, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateNodeEmployeeOffice(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.updateNodeEmployeeOffice(jsonBody);
    }

    @RequestMapping(value = Api.REMOVE_NODE_IN_DOCUMENT_FOR_REQUEST, 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> removeNodeInDocumentForRequest(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        jsonBody.put("node", new JSONObject("{}"));
        ResponseEntity<?> response = dataEmployeeService.updateNodeInDocumentForRequest(jsonBody);
        return response;
    }


    @RequestMapping(value = Api.APPEND_NODE_IN_LIST_FOR_REQUEST, 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> appendNodeInListForRequest(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.appendNodeInListForRequest(jsonBody);
        return response;
    }

    @RequestMapping(value = Api.REMOVE_NODE_IN_LIST_FOR_REQUEST,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> removeNodeInListForRequest(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.removeNodeInListForRequest(jsonBody);
        return response;
    }


    @RequestMapping(value = Api.UPDATE_NODE_IN_LIST_FOR_REQUEST, 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_JSON_VALUE }, 
                    produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateNodeInListForRequest(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.updateNodeInListForRequest(jsonBody);
        return response;
    }
    @RequestMapping(value = Api.UPDATE_APPROVAL_HISTORY_FOR_REVIEW,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateApprovalHistoryForReview(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.updateApprovalHistoryForReview(requestParam);
        return response;
    }

    @RequestMapping(value = Api.APPEND_NODE_EMPLOYEE_OFFICE, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> appendNodeEmployeeOffice(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        return dataEmployeeService.appendNodeEmployeeOffice(jsonBody);
    }
    @RequestMapping(value = Api.UPDATE_APPROVAL_HISTORY_FOR_APPROVE,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateApprovalHistoryForApprove(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.updateApprovalHistoryForApprove(requestParam);
        return response;
    }


    @RequestMapping(value = Api.UPDATE_APPROVAL_HISTORY_FOR_REJECT,
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> updateApprovalHistoryForReject(@RequestBody Map<String, Object> requestBody) {
        JSONObject requestParam = new JSONObject(requestBody).getJSONObject("body");
        ResponseEntity<?> response = dataEmployeeService.updateApprovalHistoryForReject(requestParam);
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
