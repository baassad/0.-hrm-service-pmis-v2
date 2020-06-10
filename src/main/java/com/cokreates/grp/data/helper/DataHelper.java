package com.cokreates.grp.data.helper;

import java.util.UUID;

import com.cokreates.grp.data.constants.JsonSchemas;
import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.util.JsonUtil;
import com.cokreates.grp.data.util.JsonValidationUtil;

import org.everit.json.schema.ValidationException;
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

}
