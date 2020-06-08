package com.cokreates.grp.data.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JsonUtil {

    public void updateNode(JSONObject destinationNode, JSONArray nodePath, JSONObject inputNode) {

        JSONObject value = destinationNode;
        int nodePathSize = nodePath.length();

        for (int i = 0; i < nodePathSize-1; i++) {
            value = value.getJSONObject(nodePath.getString(i));
        }

        value.put(nodePath.getString(nodePathSize-1), inputNode);

    }

    public void listUpdateNode(String searchKey, JSONObject destinationNode, JSONArray nodePath, JSONObject inputNode) {
        /**
         * The python version of this library method 
         * used to update the list members by reference. 
         * This version cannot do that because JSONArray
         * does not support inplace modifications
         */

        JSONObject value = destinationNode;
        String nodeId = (String) inputNode.get(searchKey);
        int nodePathSize = nodePath.length();

        this.listRemoveNode(searchKey, nodeId, destinationNode, nodePath);
        this.listAppendNode(destinationNode, nodePath, inputNode);
        
    }

    public void listAppendNode(JSONObject destinationNode, JSONArray nodePath, JSONObject inputNode) {

        JSONObject value = destinationNode;
        int nodePathSize = nodePath.length();

        for (int i = 0; i < nodePathSize-1; i++) {
            value = value.getJSONObject(nodePath.getString(i));
        }

        JSONArray listOfValues = value.getJSONArray(nodePath.getString(nodePathSize-1));
        listOfValues.put(inputNode);

    }

    public void listRemoveNode(String searchKey, String nodeId, JSONObject destinationNode, JSONArray nodePath) {

        JSONObject value = destinationNode;
        int nodePathSize = nodePath.length();

        for (int i = 0; i < nodePathSize-1; i++) {
            value = value.getJSONObject(nodePath.getString(i));
        }

        JSONArray listOfValues = value.getJSONArray(nodePath.getString(nodePathSize-1));
        int removeIndex = -1;

        for (int i = 0; i < listOfValues.length(); i++) {
            JSONObject lvalue = listOfValues.getJSONObject(i);
            if (lvalue.has(searchKey)) {
                if (lvalue.getString(searchKey).equals(nodeId)) {
                    removeIndex = i;
                    break;
                }
            }
        }

        if (removeIndex != -1) {
            listOfValues.remove(removeIndex);
        }

    }

    public Object getJsonNode(JSONObject destinationNode, JSONArray nodePath) {

        JSONObject value = destinationNode;
        int nodePathSize = nodePath.length();

        for (int i = 0; i < nodePathSize ; i++) {
            value = value.getJSONObject(nodePath.getString(i));
        }

        return (Object) value;
    }

    public Object getJsonArray(JSONObject destinationNode, JSONArray nodePath) {

        JSONObject value = destinationNode;
        int nodePathSize = nodePath.length();

        for (int i = 0; i < nodePathSize-1 ; i++) {
            value = value.getJSONObject(nodePath.getString(i));
        }
        
        return (Object) value.getJSONArray(nodePath.getString(nodePathSize-1));
    }

    public Object getNodeFromList(String searchKey, String nodeId, JSONObject destinationNode, JSONArray nodePath) {

        JSONObject value = destinationNode;
        int nodePathSize = nodePath.length();

        for (int i = 0; i < nodePathSize-1; i++) {
            value = value.getJSONObject(nodePath.getString(i));
        }

        JSONArray listOfValues = value.getJSONArray(nodePath.getString(nodePathSize - 1));
        Object returnNode = new JSONObject();

        for (int i = 0; i < listOfValues.length(); i++) {
            JSONObject lvalue = listOfValues.getJSONObject(i);
            if (lvalue.has(searchKey)) {
                if (lvalue.getString(searchKey).equals(nodeId)) {
                    returnNode = lvalue;
                    break;
                }
            }
        }

        return returnNode;
    }

}