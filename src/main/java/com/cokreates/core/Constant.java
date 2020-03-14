package com.cokreates.core;

public class Constant {

	public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_DELETE = "delete";
    public static final String ENDPOINT_CREATE = "/create";
    public static final String ENDPOINT_CREATE_ALL = "/create-all";
    public static final String ENDPOINT_SEARCH = "/search";
    public static final String ENDPOINT_UPDATE = "/update";
    public static final String ENDPOINT_UPDATE_ALL = "/update-all";
    public static final String ENDPOINT_GET = "/get";
    public static final String ENDPOINT_GET_FROM_LIST = "/get-from-list";
    public static final String ENDPOINT_GET_LIST = "/get-list";
    public static final String ENDPOINT_GET_LIST_BY_OID_SET = "/get-list-by-oid-set";
    public static final String ENDPOINT_DELETE = "/delete";
    public static final String ENDPOINT_DELETE_ALL = "/delete-all";

    public static final String VERSION_1 = "v1/";
    
    public static final String GDATA_GET 		= "get/";
    public static final String GDATA_CREATE 	= "create/";
    public static final String GDATA_UPDATE 	= "update/";
    public static final String GDATA_APPEND 	= "append/";
    public static final String GDATA_REMOVE		= "remove/";
    
    public static final String GDATA_NODE 						= "node-in-emp-doc";
    public static final String GDATA_LIST_NODE 					= "list-node-in-emp-doc";
    public static final String GDATA_NODE_REQUEST 				= "node-in-doc-for-request";
    public static final String GDATA_LIST_NODE_REQUEST 			= "list-node-in-doc-for-request";
    public static final String GDATA_APPROVAL_HISTORY_REVIEW 	= "approval-history-for-review";
    public static final String GDATA_NODE_REJECT 				= "node-in-doc-for-reject";
    public static final String GDATA_LIST_NODE_REJECT 			= "list-node-in-doc-for-reject";
    public static final String GDATA_APPROVE 					= "node-in-doc-for-approve";
    public static final String GDATA_LIST_NODE_APPROVE 			= "list-node-in-doc-for-approve";
    public static final String GDATA_APPROVAL_HISTORY_STATUS 	= "approval-history-by-status";
    public static final String GDATA_APPROVAL_HISTORY_EMP_STATUS= "approval-history-by-employee-and-status";
    
    //http://localhost:5000/hrm/create/v1/emp
    
    //http://localhost:5000/hrm/get/v1/node-in-emp-doc
    //http://localhost:5000/hrm/get/v1/list-node-in-emp-doc
    //http://localhost:5000/hrm/get/v1/approval-history-by-status
    //http://localhost:5000/hrm/get/v1/approval-history-by-employee-and-status
    
    //http://localhost:5000/hrm/append/v1/list-node-in-doc-for-request
    //http://localhost:5000/hrm/append/v1/list-node-in-doc-for-approve
    
    //http://localhost:5000/hrm/update/v1/node-in-doc-for-request
    //http://localhost:5000/hrm/update/v1/list-node-in-doc-for-request
    //http://127.0.0.1:5000/hrm/update/v1/approval-history-for-review
    //http://localhost:5000/hrm/update/v1/node-in-doc-for-reject
    //http://localhost:5000/hrm/update/v1/node-in-doc-for-approve
    //http://127.0.0.1:5000/hrm/update/v1/list-node-in-doc-for-approve
    
    //http://localhost:5000/hrm/remove/v1/list-node-in-doc-for-reject
    	
}