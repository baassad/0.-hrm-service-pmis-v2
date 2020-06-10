package com.cokreates.grp.data.service;

import com.cokreates.grp.data.constants.Api;
import com.cokreates.grp.data.helper.DataHelper;
import com.cokreates.grp.data.repository.DataCustomRepository;
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
import java.util.UUID;

@Service
@Slf4j
public class DataEmployeeService {
    @Autowired
    DataCustomRepository repository;

    @Autowired
    JsonUtil jsonUtil;

    @Autowired
    DataHelper dataHelper;

    private static String getErrorMessage(String apiName, Exception ex){
        JSONObject error = new JSONObject();
        error.put("API" ,apiName);
        error.put("Exception", ex);
        return error.toString();
    }

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

    public ResponseEntity<?> readMainEmployeeByOfficeOfficeUnit(JSONObject requestParam) {
        if (requestParam.getJSONObject("miscellaneousRequestProperty").getJSONArray("officeOidList").length() == 0
                ||
            requestParam.getJSONObject("miscellaneousRequestProperty").getJSONArray("officeUnitOidList").length() == 0
        )
        {
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("data", new JSONObject())).toString(), HttpStatus.OK);
        }

        JSONArray employeeDoc = null;
        try {
            employeeDoc = repository.readMainEmployeeByOfficeOfficeUnit(requestParam);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", dataHelper.formatEmployeeDoc(employeeDoc));

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> readMainEmployeeByOffice(JSONObject requestParam) {
        if (requestParam.getJSONObject("miscellaneousRequestProperty").getJSONArray("officeOidList").length() == 0)
        {
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("data", new JSONObject())).toString(), HttpStatus.OK);
        }

        JSONArray employeeDoc = null;
        try {
            employeeDoc = repository.readMainEmployeeByOffice(requestParam);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", dataHelper.formatEmployeeDoc(employeeDoc));

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> readMainEmployeeByOidSet(JSONObject requestParam) {
        if (requestParam.getJSONObject("miscellaneousRequestProperty").getJSONArray("employeeOidList").length() == 0)
        {
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("data", new JSONObject())).toString(), HttpStatus.OK);
        }

        JSONArray employeeDoc = null;
        try {
            employeeDoc = repository.readMainEmployeeByOidSet(requestParam);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", dataHelper.formatEmployeeDoc(employeeDoc));

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> readNodeFromEmployeeDocByOidSet(JSONObject requestParam) {
        if (requestParam.getJSONObject("miscellaneousRequestProperty").getJSONArray("employeeOidList").length() == 0) {
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("data", new JSONObject())).toString(), HttpStatus.OK);
        }

        JSONArray employeeDoc = null;
        try {
            employeeDoc = repository.readNodeFromEmployeeDocByOidSet(requestParam);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", dataHelper.formatEmployeeNodes(employeeDoc));

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> readNodeFromEmployeeDoc(JSONObject requestParams){
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.readNodeFromEmployeeDoc(requestParams);
        } catch (Exception ex) {
            String errorMessage = getErrorMessage(Api.READ_NODE_FROM_EMPLOYEE_DOC, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
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

        ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);  
        return responseObject;
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

        ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);  

        return responseObject;
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

        ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);  

        return responseObject;
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

        ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);  

        return responseObject;
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

        ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);  

        return responseObject;
    }

	public ResponseEntity<?> readNodeInListFromEmployeeDoc(JSONObject requestParams) {
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.readNodeFromEmployeeDoc(requestParams);
        } catch (Exception ex) {
            JSONObject error = new JSONObject();
            error.put("API" ,Api.READ_NODE_IN_LIST_FROM_EMPLOYEE_DOC);
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

	public ResponseEntity<?> getEmployeeOffice(JSONObject requestParams) {
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.getEmployeeOffice(requestParams);
        } catch (Exception ex) {
            JSONObject error = new JSONObject();
            error.put("API" ,Api.GET_EMPLOYEE_OFFICE);
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
            error.put("API" ,Api.READ_EMPLOYEE_BY_OFFICE);
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
            error.put("API" ,Api.READ_OFFICE_BY_EMPLOYEE);
            error.put("Exception", ex);
            return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", employeeDoc.get("office"));

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);

    }

	public ResponseEntity<?> readEmployeeOfficeByOffice(JSONObject requestParams) {
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

        JSONArray totalEmployeeOfficeList = null;

        try {
            totalEmployeeOfficeList = repository.readEmployeeOfficeByOffice(requestParams);
        } catch (Exception ex) {
            JSONObject error = new JSONObject();
            error.put("API" ,Api.READ_EMPLOYEE_OFFICE_BY_OFFICE);
            error.put("Exception", ex);
            return new ResponseEntity<>(error.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        JSONArray resultData = new JSONArray();
        for(int i = 0; i < totalEmployeeOfficeList.length(); i++){
            JSONObject innerTotalEmployeeOfficeList = totalEmployeeOfficeList.getJSONObject(i); 
            JSONArray employeeOfficeList = innerTotalEmployeeOfficeList.getJSONArray("employeeoffice");
            for(int j = 0; j < employeeOfficeList.length(); j++){
                JSONObject employeeOffice = employeeOfficeList.getJSONObject(j);
                employeeOffice.put("employeeOfficeOid", employeeOffice.get("oid"));
                employeeOffice.put("oid", innerTotalEmployeeOfficeList.get("oid"));
                if (innerTotalEmployeeOfficeList.get("general") != null){
                    if(innerTotalEmployeeOfficeList.getJSONObject("general").has("nameEn")){
                        employeeOffice.put("nameEn", innerTotalEmployeeOfficeList.getJSONObject("general").get("nameEn"));
                    }
                    if(innerTotalEmployeeOfficeList.getJSONObject("general").has("nameBn")){
                        employeeOffice.put("nameBn", innerTotalEmployeeOfficeList.getJSONObject("general").get("nameBn"));
                    }
                    if(innerTotalEmployeeOfficeList.getJSONObject("general").has("phone")){
                        employeeOffice.put("phone", innerTotalEmployeeOfficeList.getJSONObject("general").get("phone"));
                    }
                    if(innerTotalEmployeeOfficeList.getJSONObject("general").has("email")){
                        employeeOffice.put("email", innerTotalEmployeeOfficeList.getJSONObject("general").get("email"));
                    }
                }
                if(officeOidList.toString().contains(employeeOffice.getString("officeOid"))){
                    resultData.put(employeeOffice);
                }
            }
        }
        JSONObject responseBody = new JSONObject();
        responseBody.put("data", resultData);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
	}


    public ResponseEntity<?> updateNodeInDocumentForRequest(JSONObject requestParameters){
        JSONObject inputNode        = requestParameters.getJSONObject("node");
        JSONArray nodePath          = requestParameters.getJSONArray("nodePath");
        String employeeOid          = requestParameters.getString("employeeOid");
        JSONObject requesterComment = requestParameters.getJSONObject("comment");

        JSONObject employeeDoc      = null;

        try {
            employeeDoc = repository.getEmployee(requestParameters);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }     

        JSONObject mainDoc = employeeDoc.getJSONObject("employee_main");
        JSONObject mainNode = (JSONObject) jsonUtil.getJsonNode(mainDoc, nodePath);

        String queryNodeUpdate = dataHelper.updateEmpTempInPmis(employeeDoc, nodePath, inputNode, employeeOid);
        String queryApprovalHistoryInsert = dataHelper.approvalHistoryInsertWithComment(inputNode, mainNode, nodePath,
                                                                                employeeOid, requesterComment, "UPDATE_NODE_IN_DOC");

        List<String> queryList = new ArrayList<>();
        queryList.add(queryNodeUpdate);
        queryList.add(queryApprovalHistoryInsert);

        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }     

        JSONObject response = new JSONObject();
        response.put("body", new JSONObject().put("oid", employeeOid));
        ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);
        
        return responseObject;
    }

    public ResponseEntity<?> appendNodeInListForRequest(JSONObject requestParameters){
        JSONObject inputNode        = requestParameters.getJSONObject("node");
        JSONArray nodePath          = requestParameters.getJSONArray("nodePath");
        String employeeOid          = requestParameters.getString("employeeOid");
        JSONObject requesterComment = requestParameters.getJSONObject("comment");

        String inputNodeOid = UUID.randomUUID().toString(); 
        inputNode.put("oid", inputNodeOid);

        JSONObject employeeDoc      = null;

        try {
            employeeDoc = repository.getEmployee(requestParameters);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }  

        String queryNodeUpdate = dataHelper.updateEmpTempListInPmis(employeeDoc, nodePath, inputNode, employeeOid);
        String queryApprovalHistoryInsert = dataHelper.approvalHistoryInsertWithComment(inputNode, new JSONObject("{}"), nodePath,
                                                                                  employeeOid, requesterComment, "APPEND_NODE_IN_LIST");
        List<String> queryList = new ArrayList<>();
        queryList.add(queryNodeUpdate);
        queryList.add(queryApprovalHistoryInsert);

        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }     

        JSONObject response = new JSONObject();
        JSONObject responseBody = new JSONObject();
        responseBody.put("oid", employeeOid) ;
        responseBody.put("nodeOid", inputNodeOid) ;
        response.put("body", responseBody);
        ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);
        
        return responseObject;
    }

    public ResponseEntity<?> updateNodeInListForRequest(JSONObject requestParameters){
        JSONObject inputNode        = requestParameters.getJSONObject("node");
        String nodeOid              = inputNode.getString("oid");
        JSONArray nodePath          = requestParameters.getJSONArray("nodePath");
        String employeeOid          = requestParameters.getString("employeeOid");
        JSONObject requesterComment = requestParameters.getJSONObject("comment");

        JSONObject employeeDoc      = null;

        try {
            employeeDoc = repository.getEmployee(requestParameters);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }  

        JSONObject mainDoc = employeeDoc.getJSONObject("employee_main");
        JSONObject mainNode = (JSONObject) jsonUtil.getNodeFromList("oid", nodeOid, mainDoc, nodePath);

        String queryNodeUpdate = dataHelper.updateEmpTempListInPmis(employeeDoc, nodePath, inputNode, employeeOid);
        String queryApprovalHistoryInsert = dataHelper.approvalHistoryInsertWithComment(inputNode, mainNode, nodePath,
                                                                                  employeeOid, requesterComment, "UPDATE_NODE_IN_LIST");
        List<String> queryList = new ArrayList<>();
        queryList.add(queryNodeUpdate);
        queryList.add(queryApprovalHistoryInsert);

        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }     

        JSONObject response = new JSONObject();
        JSONObject responseBody = new JSONObject();
        responseBody.put("oid", employeeOid);
        response.put("body", responseBody); 
        ResponseEntity<?> responseObject = new ResponseEntity<> (response.toString(), HttpStatus.OK);
        
        return responseObject;
    }

	public ResponseEntity<?> updateNodeEmployeeOffice(JSONObject requestParams) {
        JSONObject inputNode = requestParams.getJSONObject("node");
        String employeeOid   = requestParams.getString("employeeOid");
        
        JSONObject employeeOfficeDoc = null;
        try {
            employeeOfficeDoc = repository.getEmployeeOfficeDetails(requestParams);
        } catch (Exception ex) {
            String errorMessage = getErrorMessage(Api.UPDATE_NODE_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject mainDoc = employeeOfficeDoc.getJSONObject("employee_office");
        JSONArray nodePath = new JSONArray().put("nodes");
        String queryNodeUpdate = null;
        try {
            queryNodeUpdate = dataHelper.updateEmployeeOfficeListInPmisByOid(mainDoc, nodePath, inputNode, employeeOid);
        } catch (Exception ex) {
            String errorMessage = getErrorMessage(Api.UPDATE_NODE_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        List<String> queryList = new ArrayList<>();
        queryList.add(queryNodeUpdate);
        
        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            String errorMessage = getErrorMessage(Api.UPDATE_NODE_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        JSONObject responseBody = new JSONObject();
        responseBody.put("oid", requestParams.get("employeeOid"));

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
    }

}
