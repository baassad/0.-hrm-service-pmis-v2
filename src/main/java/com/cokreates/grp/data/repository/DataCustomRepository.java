package com.cokreates.grp.data.repository;

import com.cokreates.grp.data.util.DataUtil;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class DataCustomRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataUtil dataUtil;


    public String getAllEmployees() {
        String query = "select * from pmis";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        return dataUtil.listToJsonArray(result).toString();
    }

    public Map<String, Object> getEmployee(JSONObject queryParam) {
        String query = "select * from pmis where oid = '" + queryParam.getString("employeeOid") + "'";
        Map<String, Object> result = jdbcTemplate.queryForMap(query);
        return result;
    }

    public JSONObject getEmployeeDoc(String employeeOid) {
        // String query="insert into employee values('"+e.getId()+"','"+e.getName()+"','"+e.getSalary()+"')";  
        String query = "SELECT "
        + "p.employee_main as employee_main, "
        + "p.employee_temp as employee_temp "
        + "FROM hrm.pmis p  WHERE "
        + "p.oid = '"
        + employeeOid
        + "'";

        Map <String, Object> result = jdbcTemplate.queryForMap(query);
        return dataUtil.mapToJsonObject(result);
    }
}
