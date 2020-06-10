package com.cokreates.grp.data.constants;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonSchemas {
    /**
     * A singleton class which contains the 
     * Json Schemas variables of PIMS
     */

    private static final JsonSchemas instance = null;
    private static JSONObject PMIS_EMPLOYEE_JSON_SCHEMA_V_4;
    private static JSONObject APPROVAL_HISTORY_COMMENT_JSON_SKELETON_V_1;
    private static JSONObject APPROVAL_HISTORY_CHANGE_JSON_SKELETON_V_1;
    private File file;

    private JsonSchemas(){
        this.readJsonSchemasFromFile();
    }

    public static JsonSchemas getInstance(){
        if (instance == null){
            return new JsonSchemas();
        }
        return instance;
    }

    private void readJsonSchemasFromFile(){
        try{
            file = ResourceUtils.getFile("classpath:json/pmis_employee_json_schema_v_4.json");
            PMIS_EMPLOYEE_JSON_SCHEMA_V_4 = new JSONObject(FileUtils.readFileToString(file));   
            log.warn(PMIS_EMPLOYEE_JSON_SCHEMA_V_4.toString());

            file = ResourceUtils.getFile("classpath:json/approval_history_comment_json_skeleton_v_1.json");
            APPROVAL_HISTORY_COMMENT_JSON_SKELETON_V_1 = new JSONObject(FileUtils.readFileToString(file));   
            log.warn(APPROVAL_HISTORY_COMMENT_JSON_SKELETON_V_1.toString());

            file = ResourceUtils.getFile("classpath:json/approval_history_change_json_skeleton_v_1.json");
            APPROVAL_HISTORY_CHANGE_JSON_SKELETON_V_1 = new JSONObject(FileUtils.readFileToString(file));   
            log.warn(APPROVAL_HISTORY_CHANGE_JSON_SKELETON_V_1.toString());
        }
        catch(Exception e){
            log.error("The json schema file could not be read");
        }
    }

    public static JSONObject getPMISEmployeeSchemaV4(){
        return PMIS_EMPLOYEE_JSON_SCHEMA_V_4;
    }

    public static JSONObject getApprovalHistoryCommentJsonSkeletonV1(){
        return APPROVAL_HISTORY_COMMENT_JSON_SKELETON_V_1;
    }

    public static JSONObject getApprovalHistoryChangeJsonSkeletonV1(){
        return APPROVAL_HISTORY_CHANGE_JSON_SKELETON_V_1;
    }

    
}