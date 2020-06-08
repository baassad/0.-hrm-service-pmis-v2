package com.cokreates.grp.data.service;

import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.util.DataUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getNodeFromeDoc(JSONObject docObject){
        String employeeOid = docObject.getString("employeeOid");
        JSONArray nodePath = docObject.getJSONArray("nodePath");
        
        JSONObject employeeDoc = repository.getEmployeeDoc(employeeOid);
        Object employeeMain = dataUtil.getNode(employeeDoc.getJSONObject("employee_main"), nodePath);
        Object employeeTemp = dataUtil.getNode(employeeDoc.getJSONObject("employee_temp"), nodePath);
        
        JSONObject employeeBody = new JSONObject();
        employeeBody.put("main", employeeMain);
        employeeBody.put("temp", employeeTemp);

        JSONObject resultObject = new JSONObject();
        resultObject.put("body", employeeBody);
        return resultObject.toString();
    }
}
