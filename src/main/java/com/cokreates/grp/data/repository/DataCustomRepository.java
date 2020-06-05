package com.cokreates.grp.data.repository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

@Repository
public class DataCustomRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getAllEmployees() {
        String query = "select * from pmis";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        System.out.println(result.get(0));
        return result.toString();
    }

    public String getEmployees(String employeeOid) {
        // String query="insert into employee values('"+e.getId()+"','"+e.getName()+"','"+e.getSalary()+"')";  
        String query = "SELECT "
        + "p.employee_main as employee_main, "
        + "p.employee_temp as employee_temp "
        + "FROM "
        + "hrm.pmis p "
        + "WHERE "
        + "p.oid = '"
        + employeeOid
        + "'";
        
        // Map <String, Object> result = jdbcTemplate.queryForMap(query);
        // Map <String, Object> employeeMain = (Map<String, Object>) result.get("employee_main");
        // System.out.println(result.get("employee_main"));
        
        JSONObject result = new JSONObject(jdbcTemplate.queryForMap(query));
        JSONObject employeeMain =  new JSONObject(result.get("employee_main"));
        return employeeMain.toString();
    }
}
