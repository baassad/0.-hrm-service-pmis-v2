package com.cokreates.grp.data.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class DataUtil {

    public JSONArray listToJsonArray(List<Map<String, Object>> objectList) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < objectList.size(); i++) {
            jsonArray.put(mapToJsonObject(objectList.get(i)));
        }
        return jsonArray;
    }

    public JSONObject mapToJsonObject(Map<String, Object> objectMap) {
        JSONObject jsonObject = new JSONObject();
        for (Iterator<String> it = objectMap.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            if (objectMap.get(key) != null) {
                Object value = objectMap.get(key);
                if (value instanceof String) {
                    jsonObject.put(key, value.toString());
                } else {
                    jsonObject.put(key, new JSONObject(value.toString()));
                }
            }
        }
        return jsonObject;
    }

    public Object getNode(JSONObject srcObject, JSONArray nodePath) {
        JSONObject jsonObject = srcObject;
        for (int i = 0; i < nodePath.length() - 1; i++) {

            jsonObject = jsonObject.getJSONObject(nodePath.getString(i));
        }
        Object result = jsonObject.get(nodePath.getString(nodePath.length() - 1));
        return result;
    }
}
