package com.cokreates.grp.data.service;


import com.cokreates.core.Constant;
import com.cokreates.grp.beans.common.EmployeeOfficeMasterDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.employeeOfficeV2.EmployeeOfficeV2DTO;
import com.cokreates.grp.beans.employeeOfficeV2.EmployeeOfficeV2Service;
import com.cokreates.grp.beans.pim.employeeMasterInfo.EmployeeMasterInfo;
import com.cokreates.grp.beans.pim.employeeOfficePim.EmployeeOffice;
import com.cokreates.grp.beans.pim.employeePersonalInfo.EmployeePersonalInfo;
import com.cokreates.grp.data.constants.Api;
import com.cokreates.grp.data.helper.DataHelper;
import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.util.JsonUtil;
import com.cokreates.grp.data.util.RestUtil;

import com.cokreates.grp.util.components.MasterDataComponent;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DataEmployeeService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DataCustomRepository repository;

    @Autowired
    JsonUtil jsonUtil;

    @Autowired
    DataHelper dataHelper;

    @Autowired
    RestUtil restUtil;

    @Autowired
    MasterDataComponent masterDataComponent;

    @Autowired
    EmployeeOfficeV2Service pmisEmployeeOfficeNodeService;

    public ResponseEntity<?> createEmployee(JSONObject inputNode, JSONArray nodePath, JSONObject requestParameters) {
        String employeeOid = UUID.randomUUID().toString();

        String pmisInsertQuery = dataHelper.pmisInsert(inputNode, nodePath, employeeOid);
        String approvalHistoryInsertQuery = dataHelper.approvalHistoryInsert(inputNode, new JSONObject("{}"), nodePath,
                                                                                  employeeOid, "UPDATE_NODE_IN_DOC");
        
        List<String> queryList = new ArrayList<>();
        queryList.add(pmisInsertQuery);
        queryList.add(approvalHistoryInsertQuery);
        
        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            String errorMessage = restUtil.getErrorMessage(Api.UPDATE_NODE_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // getting employee main info from getEmployee Service
        requestParameters.put("employeeOid", employeeOid);
        ResponseEntity<?> responseObject = this.getEmployee(requestParameters);

        return responseObject;
    }

    public String importEmployees(JSONArray nodePath,EmployeeMasterInfo employeeMasterInfo, EmployeePersonalInfo employeePersonalInfo,List<EmployeeOffice> employeeOffices){

        JSONObject generalNode = new JSONObject();
        generalNode.put("oid",employeeMasterInfo.getOid());
        if(employeeMasterInfo.getNameEn() != null) {
            generalNode.put("nameEn", employeeMasterInfo.getNameEn());
        }
        if(employeeMasterInfo.getNameBn() != null) {
            generalNode.put("nameBn", employeeMasterInfo.getNameBn());
        }
        if(employeePersonalInfo.getGender() != null) {
            generalNode.put("gender", masterDataComponent.getGender(employeePersonalInfo.getGender()));
        }
        if(employeePersonalInfo.getMaritalStatus() != null) {
            generalNode.put("maritalStatus", masterDataComponent.getMaritalStatus(employeePersonalInfo.getMaritalStatus()));
        }
        if(employeePersonalInfo.getDateOfBirth() != null) {
            generalNode.put("dateOfBirth", employeePersonalInfo.getDateOfBirth().getTime());
        }
        if(employeePersonalInfo.getPhoneNo() != null) {
            generalNode.put("phone", employeePersonalInfo.getPhoneNo());
        }
        if(employeePersonalInfo.getEmailAddress() != null) {
            generalNode.put("email", employeePersonalInfo.getEmailAddress());
        }
        generalNode.put("rowStatus", "Active");
        if(employeeMasterInfo.getCreatedBy() != null) {
            generalNode.put("createdBy", employeeMasterInfo.getCreatedBy());
        }
        if(employeeMasterInfo.getCreatedOn() != null) {
            generalNode.put("createdOn", employeeMasterInfo.getCreatedOn().getTime());
        }
        if(employeeMasterInfo.getUpdatedBy() != null) {
            generalNode.put("updatedBy", employeeMasterInfo.getUpdatedBy());
        }
        if(employeeMasterInfo.getUpdatedOn() != null) {
            generalNode.put("updatedOn", employeeMasterInfo.getUpdatedOn().getTime());
        }
        generalNode.put("config", "");
        generalNode.put("dataStatus", "Active");

        List<JSONObject> jsonObjectList = new ArrayList<>();

        /*
        for(EmployeeOffice employeeOffice:employeeOffices){
            JSONObject employeeOfficeNode = new JSONObject();

            employeeOfficeNode.put("oid", employeeOffice.getOid());
            if(employeeOffice.getOfficeOid() != null) {
                employeeOfficeNode.put("officeOid", employeeOffice.getOfficeOid());
            }
            if(employeeOffice.getOfficeUnitOid() != null) {
                employeeOfficeNode.put("officeUnitOid", employeeOffice.getOfficeUnitOid());
            }
            if(employeeOffice.getOfficeUnitPostOid() != null) {
                employeeOfficeNode.put("officeUnitPostOid", employeeOffice.getOfficeUnitPostOid());
            }
            if(employeeOffice.getEmploymentType() != null) {
                employeeOfficeNode.put("employmentTypeOid", employeeOffice.getEmploymentType().getOid());
            }
            if(employeeOffice.getResponsibilityType() != null) {
                employeeOfficeNode.put("responsibilityType", employeeOffice.getResponsibilityType());
            }
            if(employeeOffice.getJoiningDate() != null) {
                employeeOfficeNode.put("joiningDate", employeeOffice.getJoiningDate().getTime());
            }
            if(employeeOffice.getIsOfficeAdmin() != null) {
                employeeOfficeNode.put("isOfficeAdmin", employeeOffice.getIsOfficeAdmin());
            }
            if(employeeOffice.getIsOfficeHead() != null) {
                employeeOfficeNode.put("isOfficeHead", employeeOffice.getIsOfficeHead());
            }
            employeeOfficeNode.put("isOfficeUnitHead", "No");

            employeeOfficeNode.put("isAttendanceDataEntryOperator","No");

            employeeOfficeNode.put("isAttendanceAdmin","No");

            employeeOfficeNode.put("isApprover", "No");

            employeeOfficeNode.put("isReviewer", "No");

            employeeOfficeNode.put("status", "Active");

            if(employeeOffice.getCreatedBy() != null) {
                employeeOfficeNode.put("createdBy", employeeOffice.getCreatedBy());
            }
            if(employeeOffice.getCreatedOn() != null) {
                employeeOfficeNode.put("createdOn", employeeOffice.getCreatedOn().getTime());
            }
            if(employeeOffice.getUpdatedBy() != null) {
                employeeOfficeNode.put("updatedBy", employeeOffice.getUpdatedBy());
            }
            if(employeeOffice.getUpdatedOn() != null) {
                employeeOfficeNode.put("updatedOn",employeeOffice.getUpdatedOn().getTime());
            }
            employeeOfficeNode.put("config", "");
            employeeOfficeNode.put("dataStatus", "Active");

            jsonObjectList.add(employeeOfficeNode);

        }
		*/

        String pmisImportQuery = dataHelper.pmisBulkImport(nodePath, employeeMasterInfo.getOid(), generalNode, jsonObjectList);

        List<String> queryList = new ArrayList<>();
        queryList.add(pmisImportQuery);

        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            return "Failed";
        }

        return employeeMasterInfo.getOid();

    }


    public ResponseEntity<?> importEmployee(JSONObject inputNode, JSONArray nodePath, JSONObject requestParameters){
        String employeeOid = inputNode.getString("oid");

        JSONObject generalNode = new JSONObject();

        if(inputNode.has("nameEn")) {
            generalNode.put("nameEn", inputNode.getString("nameEn"));
        }
        if(inputNode.has("nameBn")) {
            generalNode.put("nameBn", inputNode.getString("nameBn"));
        }
        if(inputNode.has("religion")) {
            generalNode.put("religion", inputNode.getString("religion"));
        }
        if(inputNode.has("gender")) {
            generalNode.put("gender", inputNode.getString("gender"));
        }
        if(inputNode.has("maritalStatus")) {
            generalNode.put("maritalStatus", inputNode.getString("maritalStatus"));
        }
        if(inputNode.has("nameEn")) {
            generalNode.put("dateOfBirth", inputNode.getLong("dateOfBirth"));
        }
        if(inputNode.has("phone")) {
            generalNode.put("phone", inputNode.getString("phone"));
        }
        if(inputNode.has("email")) {
            generalNode.put("email", inputNode.getString("email"));
        }
        generalNode.put("rowStatus", "Active");
        if(inputNode.has("createdBy")) {
            generalNode.put("createdBy", inputNode.getString("createdBy"));
        }
        if(inputNode.has("createdOn")) {
            generalNode.put("createdOn", inputNode.getLong("createdOn"));
        }
        if(inputNode.has("updatedBy")) {
            generalNode.put("updatedBy", inputNode.getString("updatedBy"));
        }
        if(inputNode.has("updatedOn")) {
            System.out.println("Working on updated on");
            generalNode.put("updatedOn", inputNode.getLong("updatedOn"));
        }
        if(inputNode.has("config")) {
            generalNode.put("config", inputNode.getString("config"));
        }
        generalNode.put("dataStatus", "Active");

        JSONObject employeeOfficeNode = new JSONObject();

        if(inputNode.has("employeeOfficeOid")) {
            employeeOfficeNode.put("oid", inputNode.getString("employeeOfficeOid"));
        }
        if(inputNode.has("officeOid")) {
            employeeOfficeNode.put("officeOid", inputNode.getString("officeOid"));
        }
        if(inputNode.has("officeUnitOid")) {
            employeeOfficeNode.put("officeUnitOid", inputNode.getString("officeUnitOid"));
        }
        if(inputNode.has("officeUnitPostOid")) {
            employeeOfficeNode.put("officeUnitPostOid", inputNode.getString("officeUnitPostOid"));
        }
        if(inputNode.has("employeeTypeOid")) {
            employeeOfficeNode.put("employmentTypeOid", inputNode.getString("employeeTypeOid"));
        }
        if(inputNode.has("responsibilityType")) {
            employeeOfficeNode.put("responsibilityType", inputNode.getString("responsibilityType"));
        }
        if(inputNode.has("joiningDate")) {
            employeeOfficeNode.put("joiningDate", inputNode.getLong("joiningDate"));
        }
        if(inputNode.has("isOfficeAdmin")) {
            employeeOfficeNode.put("isOfficeAdmin", inputNode.getString("isOfficeAdmin"));
        }
        if(inputNode.has("isOfficeHead")) {
            employeeOfficeNode.put("isOfficeHead", inputNode.getString("isOfficeHead"));
        }
        if(inputNode.has("isOfficeUnitHead")) {
            employeeOfficeNode.put("isOfficeUnitHead", inputNode.getString("isOfficeUnitHead"));
        }

        employeeOfficeNode.put("isAttendanceDataEntryOperator","No");

        employeeOfficeNode.put("isAttendanceAdmin","No");

        employeeOfficeNode.put("isApprover", "No");

        employeeOfficeNode.put("isReviewer", "No");

        employeeOfficeNode.put("status", "Active");

        if(inputNode.has("createdBy")) {
            employeeOfficeNode.put("createdBy", inputNode.getString("createdBy"));
        }
        if(inputNode.has("createdOn")) {
            employeeOfficeNode.put("createdOn", inputNode.getLong("createdOn"));
        }
        if(inputNode.has("updatedBy")) {
            employeeOfficeNode.put("updatedBy", inputNode.getString("updatedBy"));
        }
        if(inputNode.has("updatedOn")) {
            System.out.println("Working on updated on");
            employeeOfficeNode.put("updatedOn", inputNode.getLong("updatedOn"));
        }
        if(inputNode.has("config")) {
            employeeOfficeNode.put("config", inputNode.getString("config"));
        }
        employeeOfficeNode.put("dataStatus", "Active");

        String pmisImportQuery = dataHelper.pmisImport(nodePath, employeeOid, generalNode, employeeOfficeNode);
    
        List<String> queryList = new ArrayList<>();
        queryList.add(pmisImportQuery);

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
            String errorMessage = restUtil.getErrorMessage(Api.READ_NODE_FROM_EMPLOYEE_DOC, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Object mainNode = jsonUtil.getNode(employeeDoc.getJSONObject("employee_main"), requestParams.getJSONArray("nodePath"));
        Object tempNode = jsonUtil.getNode(employeeDoc.getJSONObject("employee_temp"), requestParams.getJSONArray("nodePath"));
        
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

        requestParameters.remove("miscellaneousRequestProperty");


        try {
            response = repository.readFromApprovalHistoryByActor(requestParameters, actor, checkingStatus, employeeOids);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", response);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
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

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", response);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> readFromApprovalHistoryByOid(JSONObject requestParameters){
        JSONArray response = null;

        try {
            response = repository.readFromApprovalHistoryByOid(requestParameters);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", response);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
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

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", response);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
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

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", response);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
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

        JSONObject responseBody = new JSONObject();
        responseBody.put("data", response);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<> (resultObject.toString(), HttpStatus.OK);
    }

	public ResponseEntity<?> readNodeInListFromEmployeeDoc(JSONObject requestParams) {
        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.readNodeFromEmployeeDoc(requestParams);
        } catch (Exception ex) {
            String errorMessage = restUtil.getErrorMessage(Api.READ_NODE_IN_LIST_FROM_EMPLOYEE_DOC, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
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
            String errorMessage = restUtil.getErrorMessage(Api.GET_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject responseBody = new JSONObject();
        if (employeeDoc == null || employeeDoc.length() == 0) {
        	responseBody.put("main", convertPmisEmployeeOfficeListToEmployeeOfficeList(pmisEmployeeOfficeNodeService.getPmisEmployeeOfficeNodes(requestParams.getString("employeeOid"))));
		} else {
	        responseBody.put("main", employeeDoc.get("nodes"));
		}

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
    }
    
	public List<EmployeeOfficeDTO> convertPmisEmployeeOfficeListToEmployeeOfficeList(List<EmployeeOfficeV2DTO> dtoList) {
		List<EmployeeOfficeDTO> resultList = new ArrayList<EmployeeOfficeDTO>();
		for (EmployeeOfficeV2DTO nodeDTO : dtoList) {
        	EmployeeOfficeDTO officeDTO = new EmployeeOfficeDTO();
        	officeDTO.setOid(nodeDTO.getEmployeeOfficeOid());
        	officeDTO.setEmploymentTypeOid(nodeDTO.getEmploymentTypeOid());
        	officeDTO.setIsApprover(nodeDTO.getIsApprover());
        	officeDTO.setIsOfficeAdmin(nodeDTO.getIsOfficeAdmin());
        	officeDTO.setIsOfficeHead(nodeDTO.getIsOfficeHead());
        	officeDTO.setIsReviewer(nodeDTO.getIsReviewer());
            officeDTO.setJoiningDate(nodeDTO.getJoiningDate());
            officeDTO.setOfficeOid(nodeDTO.getOfficeOid());
            officeDTO.setOfficeUnitOid(nodeDTO.getOfficeUnitOid());
            officeDTO.setOfficeUnitPostOid(nodeDTO.getOfficeUnitPostOid());
            officeDTO.setStatus(nodeDTO.getStatus());
            officeDTO.setIsOfficeUnitHead(nodeDTO.getIsOfficeUnitHead());
            officeDTO.setResponsibilityType(nodeDTO.getResponsibilityType());
            officeDTO.setIsAttendanceDataEntryOperator(nodeDTO.getIsAttendanceDataEntryOperator());
            officeDTO.setIsAttendanceAdmin(nodeDTO.getIsAttendanceAdmin());
            officeDTO.setIsAwardAdmin(nodeDTO.getIsAwardAdmin());
            officeDTO.setNodeOid(nodeDTO.getNodeOid());
            officeDTO.setConfig(nodeDTO.getConfig());
            officeDTO.setMain(nodeDTO.getMain());
            officeDTO.setTemp(nodeDTO.getTemp());
            officeDTO.setNode(nodeDTO.getNode());
            officeDTO.setDataStatus(nodeDTO.getDataStatus());
            officeDTO.setRowStatus(nodeDTO.getRowStatus());
            officeDTO.setCreatedBy(nodeDTO.getCreatedBy());
            officeDTO.setUpdatedBy(nodeDTO.getUpdatedBy());
            //officeDTO.setCreatedOn(new Timestamp(nodeDTO.getCreatedOn().getTime()));
           //officeDTO.setUpdatedOn(nodeDTO.getUpdatedOn()==null?null:new Timestamp(nodeDTO.getUpdatedOn().getTime()));
        	
            resultList.add(officeDTO);
		}
		
		return resultList;
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
            String errorMessage = restUtil.getErrorMessage(Api.READ_EMPLOYEE_BY_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
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
            String errorMessage = restUtil.getErrorMessage(Api.READ_OFFICE_BY_EMPLOYEE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
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
            String errorMessage = restUtil.getErrorMessage(Api.READ_EMPLOYEE_OFFICE_BY_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
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

    public ResponseEntity<?> readEmployeeByOfficeAndEmployeeType(JSONObject requestParams, String employeeType) {
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
            String errorMessage = restUtil.getErrorMessage(Api.READ_EMPLOYEE_OFFICE_BY_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        JSONArray resultData = new JSONArray();
        for(int i = 0; i < totalEmployeeOfficeList.length(); i++){
            JSONObject innerTotalEmployeeOfficeList = totalEmployeeOfficeList.getJSONObject(i);
            JSONArray employeeOfficeList = innerTotalEmployeeOfficeList.getJSONArray("employeeoffice");
            for(int j = 0; j < employeeOfficeList.length(); j++){
                JSONObject employeeOffice = employeeOfficeList.getJSONObject(j);
                String employeeOfficeString = employeeOffice.toString();

                try {
                    EmployeeOfficeMasterDTO employeeOfficeFromJson = objectMapper.readValue(employeeOfficeString, EmployeeOfficeMasterDTO.class);

                    if (employeeType.equals(Constant.ADMIN)) {
                        if (employeeOfficeFromJson.getIsOfficeAdmin() == null) {
                            continue;
                        } else if (!employeeOfficeFromJson.getIsOfficeAdmin().equals("Yes")) {
                            continue;
                        }
                    } else if (employeeType.equals(Constant.APPROVER)) {
                        if (employeeOfficeFromJson.getIsApprover() == null) {
                            continue;
                        } else if (!employeeOfficeFromJson.getIsApprover().equals("Yes")) {
                            continue;
                        }
                    } else if (employeeType.equals(Constant.REVIEWER)) {
                        if (employeeOfficeFromJson.getIsReviewer() == null) {
                            continue;
                        } else if (!employeeOfficeFromJson.getIsReviewer().equals("Yes")) {
                            continue;
                        }
                    }

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

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

    public ResponseEntity<?> readImproperResponsibilityType(JSONObject requestParams) {
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
            String errorMessage = restUtil.getErrorMessage(Api.READ_IMPROPER_RESPONSIBILITY_TYPE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        JSONArray resultData = new JSONArray();
        for(int i = 0; i < totalEmployeeOfficeList.length(); i++){
            boolean hasMain = false;
            JSONObject innerTotalEmployeeOfficeList = totalEmployeeOfficeList.getJSONObject(i);
            JSONArray employeeOfficeList = innerTotalEmployeeOfficeList.getJSONArray("employeeoffice");
            for(int j = 0; j < employeeOfficeList.length(); j++){
                boolean improper = false;
                JSONObject employeeOffice = employeeOfficeList.getJSONObject(j);
                employeeOffice.put("employeeOfficeOid", employeeOffice.get("oid"));
                employeeOffice.put("oid", innerTotalEmployeeOfficeList.get("oid"));

                String employeeOfficeString = employeeOffice.toString();

                if (!employeeOfficeString.contains("responsibilityType")) {
                    improper = true;
                }

                try {
                    EmployeeOfficeMasterDTO employeeOfficeFromJson = objectMapper.readValue(employeeOfficeString, EmployeeOfficeMasterDTO.class);

                    if (employeeOfficeFromJson.getResponsibilityType() == null) {
                        improper = true;
                    } else if (employeeOfficeFromJson.getResponsibilityType().equals("")) {
                        improper = true;
                    } else if (employeeOfficeFromJson.getResponsibilityType().equals("Main")) {
                        hasMain = true;
                    }

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                if(!improper) {
                    if (hasMain || (j != employeeOfficeList.length() - 1))continue;
                    else employeeOffice.put("responsibilityType", "No main responsibility");
                }

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

    public ResponseEntity<?> readImproperResponsibilityTypeByEmployee(JSONObject requestParams) {
        JSONArray employeeOidList = requestParams.getJSONObject("miscellaneousRequestProperty").getJSONArray("employeeOidList");
        requestParams.remove("miscellaneousRequestProperty");
        String employeeOidListString = "'" + employeeOidList.getString(0) + "'";
        for (int i = 1; i < employeeOidList.length(); i++) {
            employeeOidListString += ", '" + employeeOidList.getString(i) + "'";
        }

        requestParams.put("employeeOidList", employeeOidListString);

        JSONArray totalEmployeeOfficeList = null;

        try {
            totalEmployeeOfficeList = repository.readEmployeeOfficeByEmployee(requestParams);
        } catch (Exception ex) {
            String errorMessage = restUtil.getErrorMessage(Api.READ_IMPROPER_RESPONSIBILITY_TYPE_BY_EMPLOYEE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        JSONArray resultData = new JSONArray();
        for(int i = 0; i < totalEmployeeOfficeList.length(); i++){
            boolean hasMain = false;
            JSONObject innerTotalEmployeeOfficeList = totalEmployeeOfficeList.getJSONObject(i);
            JSONArray employeeOfficeList = innerTotalEmployeeOfficeList.getJSONArray("employeeoffice");
            for(int j = 0; j < employeeOfficeList.length(); j++){
                boolean improper = false;
                JSONObject employeeOffice = employeeOfficeList.getJSONObject(j);
                employeeOffice.put("employeeOfficeOid", employeeOffice.get("oid"));
                employeeOffice.put("oid", innerTotalEmployeeOfficeList.get("oid"));

                String employeeOfficeString = employeeOffice.toString();

                if (!employeeOfficeString.contains("responsibilityType")) {
                    improper = true;
                }

                try {
                    EmployeeOfficeMasterDTO employeeOfficeFromJson = objectMapper.readValue(employeeOfficeString, EmployeeOfficeMasterDTO.class);

                    if (employeeOfficeFromJson.getResponsibilityType() == null) {
                        improper = true;
                    } else if (employeeOfficeFromJson.getResponsibilityType().equals("")) {
                        improper = true;
                    } else if (employeeOfficeFromJson.getResponsibilityType().equals("Main")) {
                        hasMain = true;
                    }

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                if(!improper) {
                    if (hasMain || (j != employeeOfficeList.length() - 1)) continue;
                    else employeeOffice.put("responsibilityType", "No main responsibility");
                }

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
//                if(employeeOidList.toString().contains(employeeOffice.getString("officeOid"))){
                resultData.put(employeeOffice);
//                }
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

    public ResponseEntity<?> appendApprovedNodeInListForRequest(JSONObject requestParameters){
        JSONObject inputNode        = requestParameters.getJSONObject("node");
        JSONArray nodePath          = requestParameters.getJSONArray("nodePath");
        String employeeOid          = requestParameters.getString("employeeOid");

        //String inputNodeOid = UUID.randomUUID().toString();
        //inputNode.put("oid", inputNodeOid);
        String inputNodeOid = inputNode.getString("oid");

        JSONObject employeeDoc      = null;

        try {
            employeeDoc = repository.getEmployee(requestParameters);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        String queryNodeUpdate = dataHelper.appendEmpMainListInPmis(employeeDoc, nodePath, inputNode, employeeOid);

        List<String> queryList = new ArrayList<>();
        queryList.add(queryNodeUpdate);
        

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

    public ResponseEntity<?> updateApprovedNodeInList(JSONObject requestParameters){
        JSONObject inputNode        = requestParameters.getJSONObject("node");
        String nodeOid              = inputNode.getString("oid");
        JSONArray nodePath          = requestParameters.getJSONArray("nodePath");
        String employeeOid          = requestParameters.getString("employeeOid");

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

        String queryNodeUpdate = dataHelper.updateEmpMainListInPmis(employeeDoc, nodePath, inputNode, employeeOid);

        List<String> queryList = new ArrayList<>();
        queryList.add(queryNodeUpdate);

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


    public ResponseEntity<?> removeNodeInListForRequest(JSONObject requestParameters){
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
                employeeOid, requesterComment, "REMOVE_NODE_IN_LIST");
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
            //employeeOfficeDoc = repository.getEmployeeOfficeDetails(requestParams);
        } catch (Exception ex) {
            String errorMessage = restUtil.getErrorMessage(Api.UPDATE_NODE_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONArray nodePath = new JSONArray().put("nodes");
        String queryNodeUpdate = null;
        System.out.println("Input Node : " + inputNode);
        try {
            queryNodeUpdate = dataHelper.updateEmployeeOfficeListInPmisByOid(employeeOfficeDoc, nodePath, inputNode, employeeOid);
        } catch (Exception ex) {
            String errorMessage = restUtil.getErrorMessage(Api.UPDATE_NODE_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        List<String> queryList = new ArrayList<>();
        queryList.add(queryNodeUpdate);
        
        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            String errorMessage = restUtil.getErrorMessage(Api.UPDATE_NODE_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        //pmisEmployeeOfficeNodeService.parseJsonAndUpdateEmployeeOffice(inputNode);
        
        JSONObject responseBody = new JSONObject();
        responseBody.put("oid", requestParams.get("employeeOid"));

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
    }


	public ResponseEntity<?> appendNodeEmployeeOffice(JSONObject requestParams) {
        JSONObject inputNode = requestParams.getJSONObject("node");
        String employeeOid   = requestParams.getString("employeeOid");

        JSONObject employeeOfficeDoc = null;
        try {
            //employeeOfficeDoc = repository.getEmployeeOfficeDetails(requestParams);
        } catch (Exception ex) {
            String errorMessage = restUtil.getErrorMessage(Api.APPEND_NODE_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        JSONArray nodePath = new JSONArray().put("nodes");
        String queryNodeUpdate = null;
        try {
            queryNodeUpdate = dataHelper.updateEmployeeOfficeListInPmis(employeeOfficeDoc, nodePath, inputNode, employeeOid);
        } catch (Exception ex) {
            String errorMessage = restUtil.getErrorMessage(Api.APPEND_NODE_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<String> queryList = new ArrayList<>();
        queryList.add(queryNodeUpdate);
        
        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            String errorMessage = restUtil.getErrorMessage(Api.APPEND_NODE_EMPLOYEE_OFFICE, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        JSONObject responseBody = new JSONObject();
        responseBody.put("oid", requestParams.get("employeeOid"));
        responseBody.put("nodeOid", inputNode.get("oid"));
        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);
        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
	}

    public ResponseEntity<?> updateApprovalHistoryForReview(JSONObject requestParams) {

        JSONObject approvalHistoryInfo = null;
        try {
            approvalHistoryInfo = repository.getApprovalHistory(requestParams);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String approvalHistoryOid = requestParams.getString("approvalHistoryOid");
        JSONObject commentFromRequest = requestParams.getJSONObject("comment");

        String employeeOid = approvalHistoryInfo.getString("employeeoid");
        JSONObject commentFromApprovalHistory = approvalHistoryInfo.getJSONObject("comment");
        JSONArray nodePath = new JSONArray();
        nodePath.put("reviewer");

        String updateCommentAndStatusInApprovalHistoryQuery = dataHelper.updateCommentAndStatusInApprovalHistory(
                commentFromApprovalHistory,
                nodePath,
                commentFromRequest,
                approvalHistoryOid,
                "REVIEWED");

        List<String> queryList = new ArrayList<>();
        queryList.add(updateCommentAndStatusInApprovalHistoryQuery);

        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("oid", approvalHistoryOid);
        responseBody.put("employeeOid", employeeOid);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> updateApprovalHistoryForApprove(JSONObject requestParams) {

        JSONObject approvalHistoryInfo = null;
        try {
            approvalHistoryInfo = repository.getApprovalHistory(requestParams);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String approvalHistoryOid = requestParams.getString("approvalHistoryOid");
        JSONObject commentFromRequest = requestParams.getJSONObject("comment");

        String employeeOid = approvalHistoryInfo.getString("employeeoid");
        String changeType = approvalHistoryInfo.getString("changetype");
        JSONArray changedNodePath = approvalHistoryInfo.getJSONObject("change").getJSONArray("nodePath");
        JSONObject commentFromApprovalHistory = approvalHistoryInfo.getJSONObject("comment");

        JSONObject queryParamsForEmployeeDoc = new JSONObject().put("employeeOid", employeeOid);

        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.getEmployee(queryParamsForEmployeeDoc);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject mainDataFromEmployeeDoc = employeeDoc.getJSONObject("employee_main");
        JSONObject tempDataFromEmployeeDoc = employeeDoc.getJSONObject("employee_temp");

        JSONArray nodePath = new JSONArray();
        nodePath.put("approver");

        String updateCommentAndStatusInApprovalHistoryQuery = dataHelper.updateCommentAndStatusInApprovalHistory(
                commentFromApprovalHistory,
                nodePath,
                commentFromRequest,
                approvalHistoryOid,
                "APPROVED");

        String addTempDataToMainQuery = null;
        if (changeType.equals("UPDATE_NODE_IN_DOC")) {
            addTempDataToMainQuery = dataHelper.addTempDataToMain(
                    mainDataFromEmployeeDoc, tempDataFromEmployeeDoc, changedNodePath, employeeOid
            );
        }
        else if (changeType.equals("APPEND_NODE_IN_LIST")) {
            String nodeToBeAddedOid = approvalHistoryInfo.getJSONObject("change").getJSONObject("newValue").getString("oid");
            addTempDataToMainQuery = dataHelper.addTempDataToMainObjectList(
                    mainDataFromEmployeeDoc, tempDataFromEmployeeDoc, changedNodePath, employeeOid, nodeToBeAddedOid
            );
        }
        else if (changeType.equals("UPDATE_NODE_IN_LIST")) {
            String nodeToBeAddedOid = approvalHistoryInfo.getJSONObject("change").getJSONObject("newValue").getString("oid");
            addTempDataToMainQuery = dataHelper.updateTempDataToMainObjectList(
                    mainDataFromEmployeeDoc, tempDataFromEmployeeDoc, changedNodePath, employeeOid, nodeToBeAddedOid
            );
        }
        else if (changeType.equals("REMOVE_NODE_IN_LIST")) {
            String nodeToBeRemovedOid = approvalHistoryInfo.getJSONObject("change").getJSONObject("newValue").getString("oid");
            addTempDataToMainQuery = dataHelper.updateTempDataToMainObjectListForRemove(
                    mainDataFromEmployeeDoc, tempDataFromEmployeeDoc, changedNodePath, employeeOid, nodeToBeRemovedOid
            );
        }
        else {
            String errorMessage = "INVALID CHANGE TYPE - OPERATION NOT SUPPORTED";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<String> queryList = new ArrayList<>();
        queryList.add(updateCommentAndStatusInApprovalHistoryQuery);
        queryList.add(addTempDataToMainQuery);

        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("oid", approvalHistoryOid);
        responseBody.put("employeeOid", employeeOid);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> updateApprovalHistoryForReject(JSONObject requestParams) {

        JSONObject approvalHistoryInfo = null;
        try {
            approvalHistoryInfo = repository.getApprovalHistory(requestParams);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String approvalHistoryOid = requestParams.getString("approvalHistoryOid");
        JSONObject commentFromRequest = requestParams.getJSONObject("comment");

        String employeeOid = approvalHistoryInfo.getString("employeeoid");
        String changeType = approvalHistoryInfo.getString("changetype");
        JSONArray changedNodePath = approvalHistoryInfo.getJSONObject("change").getJSONArray("nodePath");
        JSONObject commentFromApprovalHistory = approvalHistoryInfo.getJSONObject("comment");
        String status = approvalHistoryInfo.getString("status");

        String actor = null;
        if (status.equals("REQUESTED")) {
            actor = "reviewer";
        }
        else {
            actor = "approver";
        }

        JSONObject queryParamsForEmployeeDoc = new JSONObject().put("employeeOid", employeeOid);

        JSONObject employeeDoc = null;
        try {
            employeeDoc = repository.getEmployee(queryParamsForEmployeeDoc);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "EXPECTED EXACTLY ONE, FOUND ZERO OR MULTIPLE RESULT FROM DATABASE";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        JSONObject tempDataFromEmployeeDoc = employeeDoc.getJSONObject("employee_temp");

        JSONArray nodePath = new JSONArray();
        nodePath.put(actor);

        String updateCommentAndStatusInApprovalHistoryQuery = dataHelper.updateCommentAndStatusInApprovalHistory(
                commentFromApprovalHistory,
                nodePath,
                commentFromRequest,
                approvalHistoryOid,
                "REJECTED");

        String removeTempDataQuery = null;
        if (changeType.equals("UPDATE_NODE_IN_DOC")) {
            removeTempDataQuery = dataHelper.removeTempData(
                    tempDataFromEmployeeDoc, changedNodePath, employeeOid
            );
        }
        else if (changeType.equals("APPEND_NODE_IN_LIST") || changeType.equals("UPDATE_NODE_IN_LIST") || changeType.equals("REMOVE_NODE_IN_LIST")) {
            String nodeToBeRemovedOid = approvalHistoryInfo.getJSONObject("change").getJSONObject("newValue").getString("oid");
            removeTempDataQuery = dataHelper.removeTempDataFromList(
                    tempDataFromEmployeeDoc, changedNodePath, employeeOid, nodeToBeRemovedOid
            );
        }
        else {
            String errorMessage = "INVALID CHANGE TYPE - OPERATION NOT SUPPORTED";
            return new ResponseEntity<>(new JSONObject().put("body", new JSONObject().put("error_message", errorMessage)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<String> queryList = new ArrayList<>();
        queryList.add(updateCommentAndStatusInApprovalHistoryQuery);
        queryList.add(removeTempDataQuery);

        try {
            repository.performTransaction(queryList);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex.toString();
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        JSONObject responseBody = new JSONObject();
        responseBody.put("oid", approvalHistoryOid);
        responseBody.put("employeeOid", employeeOid);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", responseBody);

        return new ResponseEntity<>(resultObject.toString(), HttpStatus.OK);
    }
}
