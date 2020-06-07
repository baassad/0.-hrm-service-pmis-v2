package com.cokreates.grp.data.service;

import com.cokreates.grp.data.repository.DataCustomRepository;
import com.cokreates.grp.data.util.DataUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataEmployeeService {
    @Autowired
    DataCustomRepository repository;

    @Autowired
    DataUtil dataUtil;

    public String getEmployee(JSONObject requestParam) {
        Map<String, Object> queryResult = repository.getEmployee(requestParam);
        return dataUtil.mapToJsonObject(queryResult).toString();
    }
}
