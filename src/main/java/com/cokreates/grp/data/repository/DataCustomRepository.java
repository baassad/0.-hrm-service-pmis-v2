package com.cokreates.grp.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
}
