package com.cokreates.grp.data.repository;

import com.cokreates.grp.data.util.DataUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
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

        return dataUtil.mapToJsonObject(result);
    }


    public JSONArray readMainEmployeeByOfficeOfficeUnit(JSONObject queryParam) throws Exception {

        JSONArray officeOidList = queryParam.getJSONObject("miscellaneousRequestProperty").getJSONArray("officeOidList");

        String officeOidListString = "\"" + officeOidList.getString(0) + "\"";
        for (int i = 1; i < officeOidList.length(); i++) {
            officeOidListString += " | \"" + officeOidList.getString(i) + "\"";
        }

        JSONArray officeUnitOidList = queryParam.getJSONObject("miscellaneousRequestProperty").getJSONArray("officeUnitOidList");

        String officeUnitOidListString = "\"" + officeUnitOidList.getString(0) + "\"";
        for (int i = 1; i < officeUnitOidList.length(); i++) {
            officeUnitOidListString += " | \"" + officeUnitOidList.getString(i) + "\"";
        }
        String query =
        "SELECT " +
            "p.oid as oid, " +
            "p.employee_main->'personal'->'general' as general, " +
            "p.employee_office  -> 'nodes' as nodes " +
        "FROM " +
            "pmis p " +
        "WHERE " +
            "p.employee_office  ->> 'nodes' similar  to '%%\"officeOid\" *: *(" + officeOidListString + ")%%' " +
            "AND " +
            "p.employee_office  ->> 'nodes' similar  to '%%\"officeUnitOid\" *: *(" + officeUnitOidListString + ")%%'";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        return dataUtil.listToJsonArray(result);
    }

    public JSONArray readMainEmployeeByOffice(JSONObject queryParam) throws Exception {

        JSONArray officeOidList = queryParam.getJSONObject("miscellaneousRequestProperty").getJSONArray("officeOidList");

        String officeOidListString = "\"" + officeOidList.getString(0) + "\"";
        for (int i = 1; i < officeOidList.length(); i++) {
            officeOidListString += " | \"" + officeOidList.getString(i) + "\"";
        }

        String query =
                "SELECT " +
                    "p.oid as oid, " +
                    "p.employee_main->'personal'->'general' as general, " +
                    "p.employee_office  -> 'nodes' as nodes " +
                "FROM " +
                    "pmis p " +
                "WHERE " +
                    "p.employee_office  ->> 'nodes' similar  to '%%\"officeOid\" *: *(" + officeOidListString + ")%%'";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        return dataUtil.listToJsonArray(result);
    }

    public JSONArray readMainEmployeeByOidSet(JSONObject queryParam) throws Exception {

        JSONArray employeeOidList = queryParam.getJSONObject("miscellaneousRequestProperty").getJSONArray("employeeOidList");

        String employeeOidListString = "'" + employeeOidList.getString(0) + "'";
        for (int i = 1; i < employeeOidList.length(); i++) {
            employeeOidListString += ", '" + employeeOidList.getString(i) + "'";
        }

        String query =
                "SELECT " +
                        "p.oid as oid, " +
                        "p.employee_main->'personal'->'general' as general, " +
                        "p.employee_office  -> 'nodes' as nodes " +
                        "FROM " +
                        "pmis p " +
                        "WHERE " +
                        "p.oid in (" + employeeOidListString + ")";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        return dataUtil.listToJsonArray(result);
    }

    public JSONObject readNodeFromEmployeeDoc(JSONObject queryParams) throws Exception {
        
        String employeeOid = queryParams.getString("employeeOid");
        
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

    public JSONArray readFromApprovalHistoryByActor(JSONObject queryParameters, String actor, String checkingStatus,
            String employeeOids) throws Exception {

        String query = "SELECT \n"
                    + " p.oid as oid, \n"
                    + " p.employee_oid as employeeOid, \n"
                    + " p.status as status, \n"
                    + " p.change as change, \n"
                    + " p.change_type as changeType, \n"
                    + " p.comment as comment, \n"
                    + " p.created_by as createdBy, \n"
                    + " p.created_on as createdOn, \n"
                    + " p.updated_by as updatedBy, \n"
                    + " p.updated_on as updatedOn, \n"
                    + " p.is_deleted as isDeleted \n"
                    + " FROM \n"
                    + " hrm.pmis_approval_history p \n"
                    + " WHERE \n"
                    + " (p.comment -> '" + actor + "' @> '"+ queryParameters.toString() +"'::jsonb \n";

        if (!employeeOids.equals("()") && !checkingStatus.equals("NOT ANY")){
            query += " or (p.employee_oid in \n" 
                    + employeeOids 
                    + " and p.status = '"+ checkingStatus +"')) \n";
        }

        query += " and p.is_deleted = 'No'\n";

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query);
        
        JSONArray resultArray = dataUtil.listToJsonArray(resultList);

        return resultArray;
    }


    public JSONArray readFromApprovalHistoryByEmployee(JSONObject queryParameters) throws Exception {

        String employeeOid = queryParameters.getString("employeeOid");

        String query = "SELECT \n"
                    + " p.oid as oid, \n"
                    + " p.employee_oid as employeeOid, \n"
                    + " p.status as status, \n"
                    + " p.change as change, \n"
                    + " p.change_type as changeType, \n"
                    + " p.comment as comment, \n"
                    + " p.created_by as createdBy, \n"
                    + " p.created_on as createdOn, \n"
                    + " p.updated_by as updatedBy, \n"
                    + " p.updated_on as updatedOn, \n"
                    + " p.is_deleted as isDeleted \n"
                    + " FROM \n"
                    + " hrm.pmis_approval_history p \n"
                    + " WHERE \n"
                    + " p.employee_oid = '" + employeeOid + "'"
                    + " and p.is_deleted = 'No'";

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query);
        
        JSONArray resultArray = dataUtil.listToJsonArray(resultList);

        return resultArray;
    }


    public JSONArray readFromApprovalHistoryByStatus(JSONObject queryParameters) throws Exception {

        String status = queryParameters.getString("status");

        String query = "SELECT \n"
                    + " p.oid as oid, \n"
                    + " p.employee_oid as employeeOid, \n"
                    + " p.status as status, \n"
                    + " p.change as change, \n"
                    + " p.change_type as changeType, \n"
                    + " p.comment as comment, \n"
                    + " p.created_by as createdBy, \n"
                    + " p.created_on as createdOn, \n"
                    + " p.updated_by as updatedBy, \n"
                    + " p.updated_on as updatedOn, \n"
                    + " p.is_deleted as isDeleted \n"
                    + " FROM \n"
                    + " hrm.pmis_approval_history p \n"
                    + " WHERE \n"
                    + " p.status = '" + status + "'"
                    + " and p.is_deleted = 'No'";

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query);
        
        JSONArray resultArray = dataUtil.listToJsonArray(resultList);

        return resultArray;
    }

    public JSONArray readFromApprovalHistoryByEmployeeAndStatus(JSONObject queryParameters) throws Exception {

        String employeeOid = queryParameters.getString("employeeOid");
        String status = queryParameters.getString("status");

        String query = "SELECT \n"
                    + " p.oid as oid, \n"
                    + " p.employee_oid as employeeOid, \n"
                    + " p.status as status, \n"
                    + " p.change as change, \n"
                    + " p.change_type as changeType, \n"
                    + " p.comment as comment, \n"
                    + " p.created_by as createdBy, \n"
                    + " p.created_on as createdOn, \n"
                    + " p.updated_by as updatedBy, \n"
                    + " p.updated_on as updatedOn, \n"
                    + " p.is_deleted as isDeleted \n"
                    + " FROM \n"
                    + " hrm.pmis_approval_history p \n"
                    + " WHERE \n"
                    + " p.employee_oid = '" + employeeOid + "'"
                    + " and p.status = '" + status + "'"
                    + " and p.is_deleted = 'No'";

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query);
        
        JSONArray resultArray = dataUtil.listToJsonArray(resultList);

        return resultArray;
    }


    public JSONArray getQuerySearchByOfficeOrOfficeUnitOrOfficeUnitPost(JSONObject queryParameters, String category) throws Exception {
        String fieldFromCategory = null;

        if (category.equals("OFFICE")){
            fieldFromCategory = "officeOid";
        }
        else if (category.equals("OFFICE_UNIT")){
            fieldFromCategory = "officeUnitOid";
        }
        else if (category.equals("OFFICE_UNIT_POST")){
            fieldFromCategory = "officeUnitPostOid";
        }

        JSONArray listOfOid = queryParameters.getJSONArray("listOfOid");
        List<String> conditionElemets = new ArrayList<>();
        String aggregatedCondition = "";
        
        for(int i = 0; i < listOfOid.length(); i++){
            String oid = listOfOid.getString(i);
            String condition = " employee_office->'nodes' @> '[{\"" + fieldFromCategory + "\":\"" + oid + "\",\"status\":\"Active\"}]' \n";
            conditionElemets.add(condition);
        }

        aggregatedCondition = String.join(" or ", conditionElemets);

        String query = " SELECT oid,employee_main->'personal'->'general' as personal_general,employee_office \n"
                    +  " FROM hrm.pmis \n"
                    +  " WHERE \n"
                    +  aggregatedCondition;

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query);

        JSONArray resultArray = dataUtil.listToJsonArray(resultList);

        return resultArray;
    }


    
	public JSONObject getEmployeeOfice(JSONObject queryParams) {
        String query = "SELECT p.employee_office->'nodes' as nodes "
                        + "from hrm.pmis p " 
                        + "where "
                        + "p.oid = '"
                        + queryParams.getString("employeeOid")
                        + "'";
        Map <String, Object> result = jdbcTemplate.queryForMap(query);
        
        return dataUtil.mapToJsonObject(result); 
	}

    public JSONArray readEmployeeByOffice(JSONObject queryParams) {
        String query = "SELECT hrm.pmis.oid as oid "
                        + "FROM hrm.pmis "
                        + "WHERE " 
                        + "hrm.pmis.employee_office  ->> 'nodes' similar  to '%%\"officeOid\" *: *("
                        + queryParams.getString("officeOidList")
                        + ")%%'";
        List<Map <String, Object>> result = jdbcTemplate.queryForList(query);
        
        return dataUtil.listToJsonArray(result); 
	}

	public JSONObject readOfficeByEmployee(JSONObject queryParams, String permissionType) {
        String query = "SELECT  p.employee_office -> 'nodes' as office "
                        + "FROM hrm.pmis p "
                        + "WHERE "  
                        + "p.oid = '"
                        + queryParams.getString("employeeOid")
                        +"'";
        Map <String, Object> result = jdbcTemplate.queryForMap(query);
        return dataUtil.mapToJsonObject(result);
	}

	public JSONArray getEmployeeOficeByOffice(JSONObject queryParams) {
        String query = "SELECT  hrm.pmis.oid as oid, "
                     + "hrm.pmis.employee_main->'personal'->'general' as general, "
                     + "hrm.pmis.employee_office -> 'nodes' as employeeoffice "
                     + "FROM hrm.pmis "   
                     + "WHERE "  
                     + "hrm.pmis.employee_office  ->> 'nodes' similar  to '%%\"officeOid\" *: *("
                     + queryParams.getString("officeOidList")
                     + ")%%'";
        List<Map <String, Object>> result = jdbcTemplate.queryForList(query);
        return dataUtil.listToJsonArray(result);
	}
}
