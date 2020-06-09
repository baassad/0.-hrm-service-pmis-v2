package com.cokreates.grp.data.service;

import com.cokreates.grp.data.constants.Api;
import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.util.DataUtil;
import com.cokreates.grp.data.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DataEmployeeService {
    @Autowired
    DataCustomRepository repository;

    @Autowired
    DataUtil dataUtil;

    @Autowired
    JsonUtil jsonUtil;

    

    public String getEmployee(JSONObject requestParam) {
        Map<String, Object> queryResult = repository.getEmployee(requestParam);
        return dataUtil.mapToJsonObject(queryResult).toString();
    }

    public ResponseEntity<?> readNodeFromEmployeeDoc(JSONObject requestParam){
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.readNodeFromEmployeeDoc(requestParam);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "Error at API: " + Api.READ_NODE_FROM_EMPLOYEE_DOC
                      + " Raised Exception: " + ex;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        Object mainNode = jsonUtil.getJsonArray(employeeDoc.getJSONObject("employee_main"), requestParam.getJSONArray("nodePath"));
        Object tempNode = jsonUtil.getJsonArray(employeeDoc.getJSONObject("employee_temp"), requestParam.getJSONArray("nodePath"));
        
        JSONObject responseBody = new JSONObject();
        responseBody.put("main", mainNode);
        responseBody.put("temp", tempNode);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
                                
    }

    public ResponseEntity<?> readFromApprovalHistoryByActor(JSONObject requestParameters){
        String actor = null;
        String checkingStatus = null;

        if (requestParameters.has("approverOid")){
            actor = "approver";
            checkingStatus = "REVIEWED";
        }
        else if (requestParameters.has("reviewerOid")){
            actor = "reviewer";
            checkingStatus = "REQUESTED";
        }
        else{
            actor = "requester";
            checkingStatus = "NOT ANY";
        }

        JSONArray response = null;
        JSONArray employeeOidList = requestParameters.getJSONObject("miscellaneousRequestProperty").getJSONArray("employeeOidList");
        List employeeOidArrayList = new ArrayList<>();
        String employeeOids = null;

        for(int i = 0 ; i < employeeOidList.length() ; i++){
            String employeeOid = "'" + employeeOidList.getString(i) + "'";
            employeeOidArrayList.add(employeeOid);
        }

        employeeOids = String.join(",", employeeOidArrayList);
        employeeOids = "(" + employeeOids + ")";

        requestParameters = (JSONObject) requestParameters.remove("miscellaneousRequestProperty");

        try {
            response = repository.readFromApprovalHistoryByActor(requestParameters, actor, checkingStatus, employeeOids);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }     

        return null;
        // ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);  
        // return responseObject;
    }


    public ResponseEntity<?> readFromApprovalHistoryByEmployee(JSONObject requestParameters){
        JSONArray response = null;

        try {
            response = repository.readFromApprovalHistoryByEmployee(requestParameters);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }     

        return null;
        // ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);  
        // return responseObject;
    }


    public ResponseEntity<?> readFromApprovalHistoryByStatus(JSONObject requestParameters){
        JSONArray response = null;

        try {
            response = repository.readFromApprovalHistoryByStatus(requestParameters);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }     

        return null;
        // ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);  
        // return responseObject;
    }


    public ResponseEntity<?> readFromApprovalHistoryByEmployeeAndStatus(JSONObject requestParameters){
        JSONArray response = null;

        try {
            response = repository.readFromApprovalHistoryByEmployeeAndStatus(requestParameters);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }     

        return null;
        // ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);  
        // return responseObject;
    }


    public ResponseEntity<?> getEmployees(JSONObject requestParameters){
        JSONArray response = null;
        String category = requestParameters.getString("category");
        JSONArray listOfOid = requestParameters.getJSONArray("listOfOid");
        int totalNumberOfOids = listOfOid.length();

        try {
            if((category.equals("OFFICE") || category.equals("OFFICE_UNIT") || category.equals("OFFICE_UNIT_POST")) 
                && totalNumberOfOids > 0){
                response = repository.getQuerySearchByOfficeOrOfficeUnitOrOfficeUnitPost(requestParameters, category);
            }        
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }     

        return null;
        // ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);  
        // return responseObject;
    }



	public ResponseEntity<?> readNodeInListFromEmployeeDoc(JSONObject requestParam) {
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.readNodeFromEmployeeDoc(requestParam);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "Error at API: "+ Api.READ_NODE_IN_LIST_FROM_EMPLOYEE_DOC 
                        + " Raised Exception: " + ex;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        Object mainNode = jsonUtil.getNodeFromList("oid", requestParam.getString("nodeOid"), employeeDoc.getJSONObject("employee_main"), requestParam.getJSONArray("nodePath"));
        Object tempNode = jsonUtil.getNodeFromList("oid", requestParam.getString("nodeOid"), employeeDoc.getJSONObject("employee_temp"), requestParam.getJSONArray("nodePath"));
        
        JSONObject responseBody = new JSONObject();
        responseBody.put("main", mainNode);
        responseBody.put("temp", tempNode);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
	}
}
