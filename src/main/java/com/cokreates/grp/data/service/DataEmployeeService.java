package com.cokreates.grp.data.service;

import com.cokreates.grp.data.constants.Api;
import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataEmployeeService {
    @Autowired
    DataCustomRepository repository;

    @Autowired
    JsonUtil jsonUtil;

    public ResponseEntity<?> getEmployee(JSONObject requestParams) {

        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.getEmployee(requestParams);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject employeeBody = new JSONObject();
        employeeBody.put("oid", employeeDoc.getString("oid"));
        employeeBody.put("personal", employeeDoc.getJSONObject("employee_main").getJSONObject("personal"));
        employeeBody.put("professional", employeeDoc.getJSONObject("employee_main").getJSONObject("professional"));
        employeeBody.put("qualification", employeeDoc.getJSONObject("employee_main").getJSONObject("qualification"));

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", employeeBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> readEmployeeDetails(JSONObject requestParams) {

        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.readEmployeeDetails(requestParams);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("main", employeeDoc);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> readNodeFromEmployeeDoc(JSONObject requestParams){
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.readNodeFromEmployeeDoc(requestParams);
        } catch (Exception ex) {
            JSONObject error = new JSONObject();
            error.put("API" ,Api.READ_NODE_FROM_EMPLOYEE_DOC);
            error.put("Exception", ex);
            return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Object mainNode = jsonUtil.getJsonArray(employeeDoc.getJSONObject("employee_main"), requestParams.getJSONArray("nodePath"));
        Object tempNode = jsonUtil.getJsonArray(employeeDoc.getJSONObject("employee_temp"), requestParams.getJSONArray("nodePath"));
        
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

        JSONObject response = null;

        try {
            response = repository.readFromApprovalHistoryByActor(requestParameters);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }     
        return new ResponseEntity<> (response.toString(), HttpStatus.OK);  
    }

	public ResponseEntity<?> readNodeInListFromEmployeeDoc(JSONObject requestParams) {
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.readNodeFromEmployeeDoc(requestParams);
        } catch (Exception ex) {
            JSONObject error = new JSONObject();
            error.put("API" ,Api.READ_NODE_FROM_EMPLOYEE_DOC);
            error.put("Exception", ex);
            return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Object mainNode = jsonUtil.getNodeFromList("oid", requestParams.getString("nodeOid"), employeeDoc.getJSONObject("employee_main"), requestParams.getJSONArray("nodePath"));
        Object tempNode = jsonUtil.getNodeFromList("oid", requestParams.getString("nodeOid"), employeeDoc.getJSONObject("employee_temp"), requestParams.getJSONArray("nodePath"));
        
        JSONObject responseBody = new JSONObject();
        responseBody.put("main", mainNode);
        responseBody.put("temp", tempNode);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
	}

	public ResponseEntity<?> getEmployeeOfice(JSONObject requestParams) {
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.getEmployeeOfice(requestParams);
        } catch (Exception ex) {
            JSONObject error = new JSONObject();
            error.put("API" ,Api.READ_NODE_FROM_EMPLOYEE_DOC);
            error.put("Exception", ex);
            return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("main", employeeDoc.get("nodes"));

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
    }
    
    public ResponseEntity<?> readEmployeeByOffice(JSONObject requestParams) {
        
        JSONArray officeOidList = requestParams.getJSONObject("miscellaneousRequestProperty").getJSONArray("officeOidList");
        requestParams.remove("miscellaneousRequestProperty");
        String officeOidListFormatted = "";
        for(int i =0; i< officeOidList.length() ; i++){
            officeOidListFormatted = officeOidListFormatted + "|" + "\"" + officeOidList.getString(i) + "\"";
        }
        if (officeOidListFormatted.length() > 0){
            officeOidListFormatted = officeOidListFormatted.substring(1);
        }
        else{
            return new ResponseEntity<>("{\"body\":{\"data\": []}}", HttpStatus.OK);
        }
        requestParams.put("officeOidList", officeOidListFormatted);

		JSONArray oidList = null;
        try {
            oidList = repository.readEmployeeByOffice(requestParams);
        } catch (Exception ex) {
            JSONObject error = new JSONObject();
            error.put("API" ,Api.READ_NODE_FROM_EMPLOYEE_DOC);
            error.put("Exception", ex);
            return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", oidList);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
    }
    
	public ResponseEntity<?> readOfficeByEmployee(JSONObject requestParams) {
        JSONObject requestParamsOid = new JSONObject();
        String permissionType = null;
        if (requestParams.has("reviewerOid")){
            requestParamsOid.put("employeeOid", requestParams.get("reviewerOid"));
            permissionType = "reviewerOfOffice";
        }
        else if (requestParams.has("approverOid")){
            requestParamsOid.put("employeeOid", requestParams.get("approverOid"));
            permissionType = "approverOfOffice";
        }
        else{
            return new ResponseEntity<>("{\"body\":{\"data\": []}}", HttpStatus.OK);
        }
        
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.readOfficeByEmployee(requestParamsOid, permissionType);
        } catch (Exception ex) {
            JSONObject error = new JSONObject();
            error.put("API" ,Api.READ_NODE_FROM_EMPLOYEE_DOC);
            error.put("Exception", ex);
            return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", employeeDoc.get("office"));

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
	}
	
}
