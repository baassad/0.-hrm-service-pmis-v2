package com.cokreates.grp.data.helper;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class DataHelper {

    public JSONArray formatEmployeeDoc(JSONArray employeeDoc) {
        for (int i = 0; i < employeeDoc.length(); i++) {
            JSONArray employeeOfficeList = employeeDoc.getJSONObject(i).getJSONArray("nodes");

            JSONArray items = new JSONArray();
            for (int j = 0; j < employeeOfficeList.length(); j++) {
                if (employeeOfficeList.getJSONObject(j).get("responsibilityType").equals("Main")) {
                    items.put(employeeOfficeList.getJSONObject(j));
                }
            }
            employeeDoc.getJSONObject(i).getJSONObject("general").put("oid", employeeDoc.getJSONObject(i).getString("oid"));
            employeeDoc.getJSONObject(i).remove("nodes");
            employeeDoc.getJSONObject(i).put("nodes", items);
            employeeDoc.getJSONObject(i).remove("oid");
        }

        return employeeDoc;
    }
}
