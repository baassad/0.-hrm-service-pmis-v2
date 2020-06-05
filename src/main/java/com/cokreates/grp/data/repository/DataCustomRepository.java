package com.cokreates.grp.data.repository;

import com.cokreates.grp.data.util.DataUtil;
import com.google.gson.internal.$Gson$Preconditions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
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

    public String getEmployee(String oid) {
        String query = "select * from pmis where oid = '" + oid + "'";
        Map<String, Object> result = jdbcTemplate.queryForMap(query);

        return dataUtil.mapToJsonObject(result).toString();
    }
}
