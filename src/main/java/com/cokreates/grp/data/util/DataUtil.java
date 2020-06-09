package com.cokreates.grp.data.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import jdk.internal.jline.internal.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DataUtil {

    public JSONArray listToJsonArray(List<Map<String, Object>> objectList) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < objectList.size(); i++) {
            JSONObject object = mapToJsonObject(objectList.get(i));
            jsonArray.put(object);
        }
        return jsonArray;
    }

    public JSONObject mapToJsonObject(Map<String, Object> objectMap) {
        JSONObject jsonObject = new JSONObject();
        for (Iterator<String> it = objectMap.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            if (objectMap.get(key) != null) {
                String value = objectMap.get(key).toString();
                if (value.charAt(0) == '{') {
                    jsonObject.put(key, new JSONObject(value));
                }
                else if (value.charAt(0) == '[') {
                    jsonObject.put(key, new JSONArray(value));
                }
                else {
                    jsonObject.put(key, value);
                }
            }
        }
        return jsonObject;
    }

}
