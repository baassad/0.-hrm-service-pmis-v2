package com.cokreates.grp.data.repository;

import com.cokreates.grp.data.util.DataUtil;
import com.cokreates.grp.data.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
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

    @Autowired
    JsonUtil jsonUtil;


    @Transactional
    public void performTransaction(List<String> queryList){
        for(int i = 0 ; i < queryList.size(); i++){
            try{
                Map<String, Object> output = jdbcTemplate.queryForMap(queryList.get(i));
            }
            catch(DataIntegrityViolationException e){
                // log.warn("No results returned by this query " + queryList.get(i));
                log.warn("No results returned by the query");
            }
        }
    }

    public String getQueryInsertPmis(String employeeOid, JSONObject employeeMain, JSONObject employeeTemp){
        String query = " INSERT \n"
                     + " INTO \n" 
                     + " hrm.pmis (\n" 
                     + " oid, \n" 
                     + " employee_main, \n" 
                     + " employee_temp) \n" 
                     + " VALUES (\n" 
                     + " '"+ employeeOid +"', \n" 
                     + " '"+ employeeMain.toString() +"', \n" 
                     + " '"+ employeeTemp.toString() +"') \n" ;

        return query;
    }


    public String getQueryImportPmis(JSONObject queryParams1, JSONObject queryParams2){
        String query = " INSERT \n"
                     + " INTO \n" 
                     + " hrm.pmis (\n" 
                     + " oid, \n" 
                     + " employee_main, \n" 
                     + " employee_temp, \n" 
                     + " employee_office) \n" 
                     + " VALUES (\n" 
                     + " '"+ queryParams1.getString("oid") +"', \n" 
                     + " '"+ queryParams1.getJSONObject("employee_main").toString() +"', \n" 
                     + " '"+ queryParams1.getJSONObject("employee_temp").toString() +"', \n" 
                     + " '"+ queryParams2.toString() +"') \n" ;

        return query;
    }



    public String getAllEmployees() {
        String query = "select * from hrm.pmis";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        return dataUtil.listToJsonArray(result).toString();
    }

    public JSONObject getEmployee(JSONObject queryParam) throws Exception {
        String query =
                "SELECT " +
                    "* " +
                "FROM " +
                    "hrm.pmis " +
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
                    "hrm.pmis p " +
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
                    "hrm.pmis p " +
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
                    "hrm.pmis p " +
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
                    "hrm.pmis p " +
                "WHERE " +
                    "p.oid in (" + employeeOidListString + ")";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        return dataUtil.listToJsonArray(result);
    }

    public JSONArray readNodeFromEmployeeDocByOidSet(JSONObject queryParam) throws Exception {

        JSONArray employeeOidList = queryParam.getJSONObject("miscellaneousRequestProperty").getJSONArray("employeeOidList");

        String employeeOidListString = "'" + employeeOidList.getString(0) + "'";
        for (int i = 1; i < employeeOidList.length(); i++) {
            employeeOidListString += ", '" + employeeOidList.getString(i) + "'";
        }

        JSONArray queryPath = queryParam.getJSONArray("nodePath");

        String queryPathString = "";
        for (int i = 0; i < queryPath.length(); i++) {
            queryPathString += "->'" + queryPath.getString(i) + "'";
        }

        String query =
            "SELECT " +
                "p.oid as oid, " +
                "p.employee_main" + queryPathString + " as node " +
            "FROM " +
                "hrm.pmis p " +
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
                    + " p.created_by as  createdBy, \n"
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
                    + " and p.status = '"+ checkingStatus +"') \n";
        }

        query += ") and p.is_deleted = 'No'\n";

        System.out.println("Query : " + query);

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query);

        System.out.println("Result is : " + resultList);
        
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

    public JSONArray readFromApprovalHistoryByOid(JSONObject queryParameters) throws Exception {

        String oid = queryParameters.getString("oid");

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
                + " p.oid = '" + oid + "'"
                + " and p.is_deleted = 'No'";

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query);

        JSONArray resultArray = dataUtil.listToJsonArray(resultList);

        int resultSize = resultArray.length();

        for (int i = 0; i < resultSize; i++) {
            resultArray.getJSONObject(i).put("employeeOid", resultArray.getJSONObject(i).get("employeeoid"));
            resultArray.getJSONObject(i).put("changeType", resultArray.getJSONObject(i).get("changetype"));
        }

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


    
	public JSONObject getEmployeeOffice(JSONObject queryParams) {
        String query = "SELECT p.employee_office->'nodes' as nodes "
                        + "from hrm.pmis p " 
                        + "where "
                        + "p.oid = '"
                        + queryParams.getString("employeeOid")
                        + "'";
        Map <String, Object> result = jdbcTemplate.queryForMap(query);
        return dataUtil.mapToJsonObject(result); 
	}

    public JSONObject getEmployeeOfficeDetails(JSONObject queryParams) {
        String query = "SELECT employee_office as employee_office "
                        + "FROM "  
                        + "hrm.pmis p "
                        + "WHERE p.oid = '"  
                        + queryParams.getString("employeeOid")
                        +"'";
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

        System.out.println("Employee oid : " + queryParams.getString("employeeOid"));

        String query = "SELECT  p.employee_office -> 'nodes' as office "
                        + "FROM hrm.pmis p "
                        + "WHERE "  
                        + "p.oid = '"
                        + queryParams.getString("employeeOid")
                        +"'";
        System.out.println(query);
        Map <String, Object> result = jdbcTemplate.queryForMap(query);
        return dataUtil.mapToJsonObject(result);
	}

	public JSONArray readEmployeeOfficeByOffice(JSONObject queryParams) {
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

    public JSONArray readEmployeeOfficeByEmployee(JSONObject queryParams) {
        String query = "SELECT  hrm.pmis.oid as oid, "
                + "hrm.pmis.employee_main->'personal'->'general' as general, "
                + "hrm.pmis.employee_office -> 'nodes' as employeeoffice "
                + "FROM hrm.pmis "
                + "WHERE "
                + "hrm.pmis.oid in (" + queryParams.getString("employeeOidList") + ")"
                ;

        List<Map <String, Object>> result = jdbcTemplate.queryForList(query);
        return dataUtil.listToJsonArray(result);
    }

    public String queryUpdateEmployeeTempInPmis(JSONObject employeeTemp, String employeeOid){
        String query = " UPDATE \n"
                     + " hrm.pmis p \n"
                     + " SET \n"
                     + " employee_temp = '" + employeeTemp.toString() + "'::jsonb \n"
                     + " WHERE \n"
                     + " p.oid = '"+ employeeOid +"' \n";

        return query;
    }

    public String queryUpdateEmployeeMainInPmis(JSONObject employeeTemp, String employeeOid){
        String query = " UPDATE \n"
                + " hrm.pmis p \n"
                + " SET \n"
                + " employee_main = '" + employeeTemp.toString() + "'::jsonb \n"
                + " WHERE \n"
                + " p.oid = '"+ employeeOid +"' \n";

        return query;
    }


    public String getQueryInsertApprovalHistory(JSONObject queryParams){
        String query = " INSERT \n"
                     + " INTO \n"
                     + " hrm.pmis_approval_history (\n"
                     + " oid, \n"
                     + " created_by, \n"
                     + " created_on, \n"
                     + " is_deleted, \n"
                     + " employee_oid, \n"
                     + " change, \n"
                     + " change_type,  \n"
                     + " comment, \n"
                     + " status) \n"
                     + " VALUES ( \n"
                     + " '"+ queryParams.getString("oid") +"', \n"
                     + " 'System', \n"
                     + " '"+ LocalDateTime.now().toString() +"', \n"
                     + " 'No', \n"
                     + " '"+ queryParams.getString("employee_oid") +"', \n"
                     + " '"+ queryParams.getJSONObject("change").toString() +"'::jsonb, \n"
                     + " '"+ queryParams.getString("change_type") +"', \n"
                     + " '"+ queryParams.getJSONObject("comment").toString() +"'::jsonb, \n"
                     + " 'REQUESTED') \n";

        return query;
    }

    public JSONObject getApprovalHistory(JSONObject queryParam) throws Exception{
        String query =
            "SELECT " +
                "p.oid as oid, " +
                "p.employee_oid as employeeOid, " +
                "p.status as status, " +
                "p.change as change, " +
                "p.change_type as changeType, " +
                "p.comment as comment, " +
                "p.created_by as createdBy, " +
                "p.created_on as createdOn, " +
                "p.updated_by as updatedBy, " +
                "p.updated_on as updatedOn, " +
                "p.is_deleted as isDeleted " +
            "FROM " +
                "hrm.pmis_approval_history p " +
            "WHERE " +
        "p.oid = '" + queryParam.getString("approvalHistoryOid") + "'";

        Map<String, Object> result = jdbcTemplate.queryForMap(query);
        return dataUtil.mapToJsonObject(result);
    }

    public String queryUpdateEmployeeOfficeInPmis(JSONObject queryParams) {
        String query = "update hrm.pmis p "
                + "set "
                + "employee_office = '"
                + queryParams.getJSONObject("employee_office").toString()
                + "'::jsonb WHERE "
                + "p.oid = '"
                + queryParams.getString("employee_oid")
                + "'";
        return query;
    }

    public String getQueryUpdateApprovalHistory(JSONObject queryParams) {
        String query =
            "UPDATE " +
                "hrm.pmis_approval_history p " +
            "SET " +
                "status = '" + queryParams.getString("status") + "', " +
                "comment = '" + queryParams.getJSONObject("comment") + "'::jsonb " +
            "WHERE " +
                "p.oid = '" + queryParams.getString("oid") + "'";

        return query;
    }

    public String getQueryUpdateEmployeeMainAndTempInPmis(JSONObject queryParams) {
        String query =
            "UPDATE " +
                "hrm.pmis p " +
            "SET " +
                "employee_main = '" + queryParams.getJSONObject("employee_main") + "'::jsonb, " +
                "employee_temp = '" + queryParams.getJSONObject("employee_temp") + "'::jsonb " +
            "WHERE " +
                "p.oid = '" + queryParams.getString("employee_oid") + "'";

        return query;
    }
}
