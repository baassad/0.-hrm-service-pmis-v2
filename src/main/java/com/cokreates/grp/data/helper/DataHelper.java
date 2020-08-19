package com.cokreates.grp.data.helper;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.cokreates.grp.data.constants.JsonSchemas;
import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.util.JsonUtil;
import com.cokreates.grp.data.util.JsonValidationUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class DataHelper {

    @Autowired
    JsonValidationUtil jsonValidationUtil;

    @Autowired
    JsonUtil jsonUtil;

    @Autowired
    DataCustomRepository repository;

    private JsonSchemas schemaValues = JsonSchemas.getInstance();


    public String pmisInsert(JSONObject inputNode, JSONArray nodePath, String employeeOid){

        // jsonValidationUtil.isValidJsonSchema(schemaValues.getPMISEmployeeSchemaV4(), inputNode);

        JSONObject employeeBlankSkeleton = schemaValues.getPmisEmployeeJsonSkeletonV4();

        JSONObject employeeDocSkeleton = new JSONObject(employeeBlankSkeleton.toString());

        jsonUtil.updateNode(employeeDocSkeleton, nodePath, inputNode);

        String query = repository.getQueryInsertPmis(employeeOid, employeeBlankSkeleton, employeeDocSkeleton);
        
        return query;
    }


    public String pmisImport(JSONArray nodePath, String employeeOid, JSONObject generalNode, JSONObject employeeOfficeNode){

        JSONObject employeeBlankSkeleton = schemaValues.getPmisEmployeeJsonSkeletonV4();

        JSONObject employeeDocSkeleton = new JSONObject(employeeBlankSkeleton.toString());

        jsonUtil.updateNode(employeeDocSkeleton, nodePath, generalNode);

        JSONObject pmis = new JSONObject();
        pmis.put("oid", employeeOid);
        pmis.put("employee_main", employeeDocSkeleton);
        pmis.put("employee_temp", employeeBlankSkeleton);

        JSONObject employeeOffice = new JSONObject();
        employeeOffice.put("nodes", new JSONArray().put(employeeOfficeNode));

        String query = repository.getQueryImportPmis(pmis, employeeOffice);

        return query;
    }

    public String pmisBulkImport(JSONArray nodePath, String employeeOid, JSONObject generalNode, List<JSONObject> employeeOfficeNodes){

        JSONObject employeeBlankSkeleton = schemaValues.getPmisEmployeeJsonSkeletonV4();

        JSONObject employeeDocSkeleton = new JSONObject(employeeBlankSkeleton.toString());

        jsonUtil.updateNode(employeeDocSkeleton, nodePath, generalNode);

        JSONObject pmis = new JSONObject();
        pmis.put("oid", employeeOid);
        pmis.put("employee_main", employeeDocSkeleton);
        pmis.put("employee_temp", employeeBlankSkeleton);

        JSONArray jsonArray = new JSONArray();
        for(JSONObject employeeOfficeNode:employeeOfficeNodes){
            jsonArray.put(employeeOfficeNode);
        }

        JSONObject employeeOffice = new JSONObject();
        employeeOffice.put("nodes", jsonArray);

        String query = repository.getQueryImportPmis(pmis, employeeOffice);

        return query;
    }


    public String approvalHistoryInsert(JSONObject inputNode, JSONObject mainNode, 
                            JSONArray nodePath, String employeeOid, String changeType){
        JSONObject commentNodeSkeleton = schemaValues.getApprovalHistoryCommentJsonSkeletonV1();
        JSONObject requesterNode = new JSONObject();
        requesterNode.put("dateAndTime", Instant.now().getEpochSecond());
        requesterNode.put("requesterOid", employeeOid);
        commentNodeSkeleton.put("requester", requesterNode);

        JSONObject changeNodeSkeleton = schemaValues.getApprovalHistoryChangeJsonSkeletonV1();
        changeNodeSkeleton.put("nodeName", nodePath.get(nodePath.length()-1));
        changeNodeSkeleton.put("nodePath", nodePath);
        changeNodeSkeleton.put("oldValue", mainNode);
        changeNodeSkeleton.put("newValue", inputNode);

        JSONObject queryParameters = new JSONObject();
        queryParameters.put("oid", UUID.randomUUID().toString());
        queryParameters.put("employee_oid", employeeOid);
        queryParameters.put("change", changeNodeSkeleton);
        queryParameters.put("comment", commentNodeSkeleton);
        queryParameters.put("change_type", changeType);

        String query = repository.getQueryInsertApprovalHistory(queryParameters);

        return query;
    }


    public JSONArray formatEmployeeDoc(JSONArray employeeDoc) {
        for (int i = 0; i < employeeDoc.length(); i++) {
            JSONArray employeeOfficeList = employeeDoc.getJSONObject(i).getJSONArray("nodes");

            JSONArray items = new JSONArray();
            for (int j = 0; j < employeeOfficeList.length(); j++) {
                if (employeeOfficeList.getJSONObject(j).get("responsibilityType").equals("Main")) {
                    items.put(employeeOfficeList.getJSONObject(j));
                }
            }
            employeeDoc.getJSONObject(i).getJSONObject("general").put("oid", employeeDoc.getJSONObject(i).getString("oid"));
            employeeDoc.getJSONObject(i).remove("nodes");
            employeeDoc.getJSONObject(i).put("nodes", items);
            employeeDoc.getJSONObject(i).remove("oid");
        }

        return employeeDoc;
    }


    public String updateEmpTempInPmis(JSONObject employeeDoc, JSONArray nodePath, JSONObject inputNode, String employeeOid){
        
        // JSONObject tempDoc = new JSONObject("{\"name\" : \"hello\"}");
        JSONObject tempDoc = employeeDoc.getJSONObject("employee_temp");


        // jsonValidationUtil.isValidJsonSchema(schemaValues.getPMISEmployeeSchemaV4(), inputNode);
        
        jsonUtil.updateNode(tempDoc, nodePath, inputNode);

        String query = repository.queryUpdateEmployeeTempInPmis(tempDoc, employeeOid);

        return query;
    }


    public String appendEmpMainListInPmis(JSONObject employeeDoc, JSONArray nodePath, JSONObject inputNode, String employeeOid){

        JSONObject mainDoc = employeeDoc.getJSONObject("employee_main");

        // jsonValidationUtil.isValidJsonSchema(schemaValues.getPMISEmployeeSchemaV4(), inputNode);

        jsonUtil.listAppendNode(mainDoc, nodePath, inputNode);

        String query = repository.queryUpdateEmployeeMainInPmis(mainDoc, employeeOid);

        return query;
    }

    public String updateEmpMainListInPmis(JSONObject employeeDoc, JSONArray nodePath, JSONObject inputNode, String employeeOid){

        JSONObject mainDoc = employeeDoc.getJSONObject("employee_main");

        // jsonValidationUtil.isValidJsonSchema(schemaValues.getPMISEmployeeSchemaV4(), inputNode);

        jsonUtil.listUpdateNode("oid",mainDoc, nodePath, inputNode);

        String query = repository.queryUpdateEmployeeMainInPmis(mainDoc, employeeOid);

        return query;
    }

    public String updateEmpTempListInPmis(JSONObject employeeDoc, JSONArray nodePath, JSONObject inputNode, String employeeOid){

        JSONObject tempDoc = employeeDoc.getJSONObject("employee_temp");
    
        // jsonValidationUtil.isValidJsonSchema(schemaValues.getPMISEmployeeSchemaV4(), inputNode);

        jsonUtil.listAppendNode(tempDoc, nodePath, inputNode);

        String query = repository.queryUpdateEmployeeTempInPmis(tempDoc, employeeOid);

        return query;
    }


    public String approvalHistoryInsertWithComment(JSONObject inputNode, JSONObject mainNode, JSONArray nodePath,
                                    String employeeOid, JSONObject requesterComment, String changeType){

        JSONObject commentNodeSkeleton = new JSONObject(schemaValues.getApprovalHistoryCommentJsonSkeletonV1().toString());
        JSONObject changeNodeSkeleton = new JSONObject(schemaValues.getApprovalHistoryChangeJsonSkeletonV1().toString());

        commentNodeSkeleton.put("requester", requesterComment);
        
        changeNodeSkeleton.put("nodeName", nodePath.get(nodePath.length()-1));
        changeNodeSkeleton.put("nodePath", nodePath);
        changeNodeSkeleton.put("oldValue", mainNode);
        changeNodeSkeleton.put("newValue", inputNode);

        JSONObject approvalHistory = new JSONObject();
        approvalHistory.put("oid", UUID.randomUUID().toString());
        approvalHistory.put("employee_oid", employeeOid);
        approvalHistory.put("change", changeNodeSkeleton);
        approvalHistory.put("comment", commentNodeSkeleton);
        approvalHistory.put("change_type", changeType);

        String query = repository.getQueryInsertApprovalHistory(approvalHistory);

        return query;
    
    }


    public JSONArray formatEmployeeNodes(JSONArray employeeDoc) {
        JSONArray items = new JSONArray();
        for (int i = 0; i < employeeDoc.length(); i++)
        {
            JSONObject node = employeeDoc.getJSONObject(i).getJSONObject("node");
            node.put("oid", employeeDoc.getJSONObject(i).getString("oid"));
            items.put(node);
        }
        return items;
    }

    public String updateEmployeeOfficeListInPmisByOid(JSONObject employeeOfficeDoc, JSONArray nodePath, JSONObject inputNode, String employeeOid) {
        JSONObject tempDoc = employeeOfficeDoc.getJSONObject("employee_office");
        jsonUtil.listUpdateNode("oid", tempDoc, nodePath, inputNode);
        JSONObject queryParams = new JSONObject();
        queryParams.put("employee_oid", employeeOid);
        queryParams.put("employee_office", tempDoc);
        return repository.queryUpdateEmployeeOfficeInPmis(queryParams);
    }


	public String updateEmployeeOfficeListInPmis(JSONObject employeeOfficeDoc, JSONArray nodePath, JSONObject inputNode, String employeeOid) {        
        JSONObject tempDoc = employeeOfficeDoc.getJSONObject("employee_office");
        jsonUtil.listAppendNode(tempDoc, nodePath, inputNode);
        JSONObject queryParams = new JSONObject();
        queryParams.put("employee_oid", employeeOid);
        queryParams.put("employee_office", tempDoc);
        return repository.queryUpdateEmployeeOfficeInPmis(queryParams);
	}

    public String updateCommentAndStatusInApprovalHistory
            (JSONObject commentFromApprovalHistory,
              JSONArray nodePath,
              JSONObject commentFromRequest,
              String approvalHistoryOid,
              String status) {

        jsonUtil.updateNode(commentFromApprovalHistory, nodePath, commentFromRequest);

        JSONObject queryParams = new JSONObject();
        queryParams.put("status", status);
        queryParams.put("comment", commentFromApprovalHistory);
        queryParams.put("oid", approvalHistoryOid);

        return repository.getQueryUpdateApprovalHistory(queryParams);
    }

    public String addTempDataToMain(JSONObject mainDataFromEmployeeDoc,
                                    JSONObject tempDataFromEmployeeDoc,
                                    JSONArray changeNodePath,
                                    String employeeOid) {
        JSONObject copyOfTempData = (JSONObject) jsonUtil.getJsonNode(tempDataFromEmployeeDoc, changeNodePath);
        jsonUtil.updateNode(mainDataFromEmployeeDoc, changeNodePath, copyOfTempData);
        jsonUtil.updateNode(tempDataFromEmployeeDoc, changeNodePath, new JSONObject());
        JSONObject queryParams = new JSONObject();
        queryParams.put("employee_oid", employeeOid);
        queryParams.put("employee_main", mainDataFromEmployeeDoc);
        queryParams.put("employee_temp", tempDataFromEmployeeDoc);

        return repository.getQueryUpdateEmployeeMainAndTempInPmis(queryParams);
    }

    public String addTempDataToMainObjectList(
            JSONObject mainDataFromEmployeeDoc,
            JSONObject tempDataFromEmployeeDoc,
            JSONArray changeNodePath,
            String employeeOid,
            String nodeToBeAddedOid
    ) {
        JSONObject objectToBeAdded = (JSONObject) jsonUtil.getNodeFromList("oid", nodeToBeAddedOid, tempDataFromEmployeeDoc, changeNodePath);
        JSONObject copyOfObjectToBeAdded = new JSONObject(objectToBeAdded.toString());
        jsonUtil.listAppendNode(mainDataFromEmployeeDoc, changeNodePath, copyOfObjectToBeAdded);
        jsonUtil.listRemoveNode("oid", nodeToBeAddedOid, tempDataFromEmployeeDoc, changeNodePath);

        JSONObject queryParams = new JSONObject();
        queryParams.put("employee_oid", employeeOid);
        queryParams.put("employee_main", mainDataFromEmployeeDoc);
        queryParams.put("employee_temp", tempDataFromEmployeeDoc);

        return repository.getQueryUpdateEmployeeMainAndTempInPmis(queryParams);
    }

    public String updateTempDataToMainObjectList(
            JSONObject mainDataFromEmployeeDoc,
            JSONObject tempDataFromEmployeeDoc,
            JSONArray changeNodePath,
            String employeeOid,
            String nodeToBeAddedOid
    ) {
        JSONObject objectToBeAdded = (JSONObject) jsonUtil.getNodeFromList("oid", nodeToBeAddedOid, tempDataFromEmployeeDoc, changeNodePath);
        JSONObject copyOfObjectToBeAdded = new JSONObject(objectToBeAdded.toString());
        jsonUtil.listUpdateNode("oid", mainDataFromEmployeeDoc, changeNodePath, copyOfObjectToBeAdded);
        jsonUtil.listRemoveNode("oid", nodeToBeAddedOid, tempDataFromEmployeeDoc, changeNodePath);

        JSONObject queryParams = new JSONObject();
        queryParams.put("employee_oid", employeeOid);
        queryParams.put("employee_main", mainDataFromEmployeeDoc);
        queryParams.put("employee_temp", tempDataFromEmployeeDoc);

        return repository.getQueryUpdateEmployeeMainAndTempInPmis(queryParams);
    }

    public String updateTempDataToMainObjectListForRemove(
            JSONObject mainDataFromEmployeeDoc,
            JSONObject tempDataFromEmployeeDoc,
            JSONArray changeNodePath,
            String employeeOid,
            String nodeToBeRemovedOid
    ) {
        jsonUtil.listRemoveNode("oid", nodeToBeRemovedOid, mainDataFromEmployeeDoc, changeNodePath);
        jsonUtil.listRemoveNode("oid", nodeToBeRemovedOid, tempDataFromEmployeeDoc, changeNodePath);

        JSONObject queryParams = new JSONObject();
        queryParams.put("employee_oid", employeeOid);
        queryParams.put("employee_main", mainDataFromEmployeeDoc);
        queryParams.put("employee_temp", tempDataFromEmployeeDoc);

        return repository.getQueryUpdateEmployeeMainAndTempInPmis(queryParams);
    }

    public String removeTempData(
            JSONObject tempDataFromEmployeeDoc,
            JSONArray changeNodePath,
            String employeeOid) {
        jsonUtil.updateNode(tempDataFromEmployeeDoc, changeNodePath, new JSONObject());

        return repository.queryUpdateEmployeeTempInPmis(tempDataFromEmployeeDoc, employeeOid);
    }

    public String removeTempDataFromList(
            JSONObject tempDataFromEmployeeDoc,
            JSONArray changeNodePath,
            String employeeOid,
            String nodeToBeRemovedOid) {
        jsonUtil.listRemoveNode("oid", nodeToBeRemovedOid, tempDataFromEmployeeDoc, changeNodePath);

        return repository.queryUpdateEmployeeTempInPmis(tempDataFromEmployeeDoc, employeeOid);
    }
}
