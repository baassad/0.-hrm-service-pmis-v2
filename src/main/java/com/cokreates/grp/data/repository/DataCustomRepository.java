package com.cokreates.grp.data.repository;

import com.cokreates.grp.data.util.DataUtil;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
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

    public JSONObject getEmployee(JSONObject queryParam) throws Exception {
        String query =
                "SELECT " +
                    "* " +
                "FROM " +
                    "pmis " +
                "WHERE " +
                     "oid = '" + queryParam.getString("employeeOid") + "'";
        Map<String, Object> result = jdbcTemplate.queryForMap(query);
        return dataUtil.mapToJsonObject(result);
    }

    public JSONObject readEmployeeDetails(JSONObject queryParam) throws Exception {
        String query =
                "SELECT " +
                    "p.employee_main->'personal'->'general' as general, " +
                    "p.employee_office->'nodes' as nodes " +
                "FROM " +
                    "pmis p " +
                "WHERE " +
                    "p.oid = '" +
                        queryParam.getString("employeeOid") +
                    "'";
        Map<String, Object> result = jdbcTemplate.queryForMap(query);

//        for (Iterator<String> it = result.keySet().iterator(); it.hasNext();) {
//            String key = it.next();
//            System.out.println(result.get(key));
//            System.out.println(result.get(key).toString().charAt(0));
//        }
        return dataUtil.mapToJsonObject(result);
    }

    public JSONObject readNodeFromEmployeeDoc(JSONObject requestParam) throws Exception {
        
        String employeeOid = requestParam.getString("employeeOid");
        
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

	public JSONObject getEmployeeOfice(JSONObject requestParam) {
        String query = "SELECT p.employee_office->'nodes' as nodes "
                        + "from hrm.pmis p " 
                        + "where "
                        + "p.oid = '"
                        + requestParam.getString("employeeOid")
                        + "'";
        Map <String, Object> result = jdbcTemplate.queryForMap(query);
        
        return dataUtil.mapToJsonObject(result); 
	}
}
