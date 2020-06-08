package com.cokreates.grp.data.repository;

import com.cokreates.grp.data.util.DataUtil;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
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

    public JSONObject readNodeFromEmployeeDoc(String employeeOid) throws Exception {
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

    public JSONObject readFromApprovalHistoryByActor(JSONObject queryParameters) throws Exception {
        String actor = null;
        String query = "SELECT "
        + "p.oid as oid,"
        + "p.employee_oid as employeeOid,"
        + "p.status as status,"
        + "p.change as change,"
        + "p.change_type as changeType,"
        + "p.comment as comment,"
        + "p.created_by as createdBy,"
        + "p.created_on as createdOn,"
        + "p.updated_by as updatedBy,"
        + "p.updated_on as updatedOn,"
        + "p.is_deleted as isDeleted"
        + "FROM"
        + "hrm.pmis p"
        + "WHERE"
        + "(p.comment -> '{actor}' @> '{json.dumps(query_params)}'::jsonb";

        Map <String, Object> result = jdbcTemplate.queryForMap(query);
        return dataUtil.mapToJsonObject(result);        
    }
}
