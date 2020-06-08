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

import java.util.Map;

@Service
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
