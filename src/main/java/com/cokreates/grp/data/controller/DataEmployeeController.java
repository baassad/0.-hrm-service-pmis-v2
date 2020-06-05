package com.cokreates.grp.data.controller;

import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.service.DataEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataEmployeeController {

    @Autowired
    DataEmployeeService dataEmployeeService;

    @Autowired
    DataCustomRepository repository;

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
}
