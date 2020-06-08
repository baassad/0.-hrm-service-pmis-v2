package com.cokreates.grp.data.service;

import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.util.DataUtil;

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

    public String getEmployee(JSONObject requestParam) {
        Map<String, Object> queryResult = repository.getEmployee(requestParam);
        return dataUtil.mapToJsonObject(queryResult).toString();
    }

    public ResponseEntity<?> readNodeFromEmployeeDoc(JSONObject docObject){
        String employeeOid = docObject.getString("employeeOid");
        JSONArray nodePath = docObject.getJSONArray("nodePath");
        
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.readNodeFromEmployeeDoc(employeeOid);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "Error at API: hrm/pmis/get/v1/node-in-emp-doc, " + ex;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        Object employeeMain = dataUtil.getNode(employeeDoc.getJSONObject("employee_main"), nodePath);
        Object employeeTemp = dataUtil.getNode(employeeDoc.getJSONObject("employee_temp"), nodePath);
        
        JSONObject employeeBody = new JSONObject();
        employeeBody.put("main", employeeMain);
        employeeBody.put("temp", employeeTemp);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", employeeBody);
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
}
