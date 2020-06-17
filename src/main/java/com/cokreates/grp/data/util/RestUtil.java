package com.cokreates.grp.data.util;

import org.json.JSONObject;
import javassist.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RestUtil {
    public String getErrorMessage(String apiName, Exception ex){
        JSONObject error = new JSONObject();
        error.put("API" ,apiName);
        error.put("Exception", ex);
        return error.toString();
    }

    public JSONObject requestParsingFilter(JSONObject requestObject,
                                List<List<String>> requiredFields, 
                                List<List<String>> nonRequiredFields)throws Exception{
        JSONObject resultObject = new JSONObject();
        for(List<String> field : requiredFields){
            String fieldName = field.get(0);
            String fieldType = field.get(1);
            if (requestObject.has(fieldName)){
                String foundType = requestObject.get(fieldName).getClass().getName();
                if (!foundType.equals(fieldType)){
                    throw new Exception("expected TYPE of required field " + fieldName + " is " + fieldType+ " but found " + foundType);
                }
            }else{
                throw new NotFoundException("REQUIRED field " + fieldName + " NOT PRESENT in request");
            }
            resultObject.put(fieldName, requestObject.get(fieldName));
        }
        if(nonRequiredFields!=null){
            for(List<String> field : nonRequiredFields){
                String fieldName = field.get(0);
                String fieldType = field.get(1);
                if (requestObject.has(fieldName)){
                    String foundType = requestObject.get(fieldName).getClass().getName();
                    if (!foundType.equals(fieldType)){
                        throw new Exception("expected TYPE of non-required field " + fieldName + " is " + fieldType+ " but found " + foundType);
                    }
                }
                resultObject.put(fieldName, requestObject.get(fieldName));
            }
        }
        return resultObject;
    }
}