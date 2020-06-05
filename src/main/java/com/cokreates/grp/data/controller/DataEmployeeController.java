package com.cokreates.grp.data.controller;
import java.util.Map;

import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.service.DataEmployeeService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DataEmployeeController {

    @Autowired
    DataEmployeeService dataEmployeeService;

    @Autowired
    DataCustomRepository repository;


    @RequestMapping("/hrm/pmis/get/v1/emp")
    @ResponseBody
    public String getEmployee() {
        return repository.getAllEmployees();
    }

    @RequestMapping("hrm/pmis/get/v1/node-in-emp-doc")
    @ResponseBody
    public String getNodeFromEmployeeDoc(@RequestBody Map<String, Object> requestBody) {
        JSONObject jsonBody = new JSONObject(requestBody).getJSONObject("body");
        String employeeOid = jsonBody.getString("employeeOid");
        JSONArray nodePath = jsonBody.getJSONArray("nodePath");
        System.out.println(nodePath);
        return repository.getEmployees(employeeOid);
    }
}
