package com.cokreates.grp.data.helper;

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

    
    public String approvalHistoryInsertWithComment(JSONObject inputNode, JSONObject mainNode, JSONArray nodePath,
                                    String employeeOid, JSONObject requesterComment, String changeType){
        String query = "";

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
}
