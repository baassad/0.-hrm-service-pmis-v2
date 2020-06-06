package com.cokreates.grp.data.controller;

import java.util.Iterator;
import java.util.Map;

import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.service.DataEmployeeService;
import com.cokreates.grp.data.util.DataUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataEmployeeController {

    @Autowired
    DataEmployeeService dataEmployeeService;

    @Autowired
    DataCustomRepository repository;

    @Autowired
    DataUtil dataUtil;

    @RequestMapping("/hrm/pmis/get/v1/all-emp")
    @ResponseBody
    public String getAllEmployee() {
        return repository.getAllEmployees();
    }

    @RequestMapping("/hrm/pmis/get/v1/emp")
    @ResponseBody
    public String getEmployee() {
        String oid = "6e2637fa-cf8a-489d-92ab-18caaff9e3dd";
        return repository.getEmployee(oid);
    }

    @RequestMapping("hrm/pmis/get/v1/node-in-emp-doc")
    @ResponseBody
    public String getNodeFromEmployeeDoc(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        String employeeOid = jsonBody.getString("employeeOid");
        JSONArray nodePath = jsonBody.getJSONArray("nodePath");
        
        JSONObject employeeDoc = repository.getEmployeeDoc(employeeOid);
        Object employeeMain = dataUtil.getNode(employeeDoc.getJSONObject("employee_main"), nodePath);
        Object employeeTemp = dataUtil.getNode(employeeDoc.getJSONObject("employee_temp"), nodePath);
        JSONObject resultObject = new JSONObject();
        resultObject.put("main", employeeMain);
        resultObject.put("temp", employeeTemp);

        return resultObject.toString();
    }
}
