package com.cokreates.grp.data.util;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JsonValidationUtil {

    

    public void isValidJsonSchema(JSONObject sourceSchema, JSONObject target) throws ValidationException{
        Schema schema = SchemaLoader.load(sourceSchema);
        schema.validate(target);
    }
    
}