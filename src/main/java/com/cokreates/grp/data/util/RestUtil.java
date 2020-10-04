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
                String[] splitString = requestObject.get(fieldName).getClass().getName().split("[.]");
                String foundType = splitString[splitString.length - 1];
                if (!foundType.equals(fieldType)){
                    throw new Exception("expected TYPE of required field " + fieldName + " is " + fieldType+ " but got type " + foundType);
                }
                resultObject.put(fieldName, requestObject.get(fieldName));
            }else{
                throw new NotFoundException("REQUIRED field " + fieldName + " NOT PRESENT in request");
            }
            
        }
        for(List<String> field : nonRequiredFields){
            String fieldName = field.get(0);
            String fieldType = field.get(1);
            if (requestObject.has(fieldName)){
                String[] splitString = requestObject.get(fieldName).getClass().getName().split("[.]");
                String foundType = splitString[splitString.length - 1];
                if (!foundType.equals(fieldType)){
                    throw new Exception("expected TYPE of non-required field " + fieldName + " is " + fieldType+ " but got type " + foundType);
                }
                resultObject.put(fieldName, requestObject.get(fieldName));
            }
        }
        return resultObject;
    }
    

    public JSONObject requestParsingFilterCheckOr(JSONObject requestObject,
                                    List<List<String>> requiredFields, 
                                    List<List<String>> nonRequiredFields)throws Exception{

        JSONObject resultObject = new JSONObject();
        boolean foundOneRequired = requiredFields.size() > 0 ? false : true;
        for(List<String> field : requiredFields){
            String fieldName = field.get(0);
            String fieldType = field.get(1);
            if (requestObject.has(fieldName) && fieldType != null){
                foundOneRequired = true;
                String[] splitString = requestObject.get(fieldName).getClass().getName().split("[.]");
                String foundType = splitString[splitString.length - 1];
                /*if (!foundType.equals(fieldType)){
                    throw new Exception("expected TYPE of required field " + fieldName + " is " + fieldType+ " but got type " + foundType);
                }*/
                resultObject.put(fieldName, requestObject.get(fieldName));
            }
        }
        if (!foundOneRequired){
            throw new NotFoundException("No expected field PRESENT in request");
        }

        for(List<String> field : nonRequiredFields){
            String fieldName = field.get(0);
            String fieldType = field.get(1);
            if (requestObject.has(fieldName) && fieldType != null){
                String[] splitString = requestObject.get(fieldName).getClass().getName().split("[.]");
                String foundType = splitString[splitString.length - 1];
                if (!foundType.equals(fieldType)){
                    throw new Exception("expected TYPE of non-required field " + fieldName + " is " + fieldType+ " but got type " + foundType);
                }
                resultObject.put(fieldName, requestObject.get(fieldName));
            }
        }
        return resultObject;
    }
}