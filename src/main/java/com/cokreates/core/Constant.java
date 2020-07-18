package com.cokreates.core;

public class Constant {

	public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_DELETE = "delete";
    public static final String ENDPOINT_CREATE = "/create";
    public static final String ENDPOINT_CREATE_EMPLOYEE_OFFICE = "/create-employee-office";
    public static final String ENDPOINT_UPDATE_EMPLOYEE_OFFICE = "/update-employee-office";
    public static final String ENDPOINT_CREATE_ALL = "/create-all";
    public static final String ENDPOINT_APPEND = "/append";
    public static final String ENDPOINT_SEARCH_V1_GET_DETAILS = "/search/v1/get-details";
    public static final String ENDPOINT_UPDATE = "/update";
    public static final String ENDPOINT_GET_APPROVAL_HISTORY 	= "/get-approval-history";
    public static final String ENDPOINT_GET_APPROVAL_HISTORY_BY_ACTOR 	= "/get-approval-history-by-actor";
    public static final String ENDPOINT_UPDATE_APPROVAL_HISTORY = "/update-approval-history";
    public static final String ENDPOINT_UPDATE_ALL = "/update-all";
    public static final String ENDPOINT_GET = "/get";
    public static final String ENDPOINT_GET_FROM_LIST = "/get-from-list";
    public static final String ENDPOINT_GET_LIST = "/get-list";
    public static final String ENDPOINT_GET_LIST_BY_OID_SET = "/get-list-by-oid-set";
    public static final String ENDPOINT_DELETE = "/delete";
    public static final String ENDPOINT_DELETE_ALL = "/delete-all";
    public static final String ENDPOINT_EMPLOYEE = "emp";
    public static final String ENDPOINT_CREATE_EMPLOYEE = "/create-employee";
    public static final String ENDPOINT_IMPORT_EMPLOYEE = "/import-employee";
    public static final String ENDPOINT_IMPORT_EMPLOYEE_FROM_HRM_V1 = "/import-employee-from-hrm-v1";
    public static final String ENDPOINT_GET_DETAILS = "/v1/get-details";
    public static final String EMPLOYEE_DETAILS = "employee-details";
    public static final String ENDPOINT_EMPLOYEE_OFFICE = "/get-employee-office";
    public static final String ENDPOINT_GET_PROFILE = "/get-profile";
    public static final String ENDPOINT_SEARCH_EMPLOYEES = "/search-employees";
    public static final String ENDPOINT_SEARCH_EMPLOYEES_NOT_IMPORTED = "/search-employees-not-imported";

    public static final String ENDPOINT_DOWNLOAD_FILE = "/api/v1/downloadFile";
    public static final String ENDPOINT_GET_EMPLOYEE_PROFILE_INFO = "/search/v1/get-list-by-oid-set-pmis-new";
    public static final String ENDPOINT_GET_BY_OFFICE_OID_LIST = "/get-details-by-office-oid-list";
    public static final String ENDPOINT_GET_MAIN_BY_EMPLOYEE_OID_LIST = "/get-main-by-employee-oid-list";
    public static final String ENDPOINT_GET_MAIN_BY_OFFICE_OFFICE_UNIT_OID_LIST = "/get-main-by-office-office-unit-oid-list";

    public static final String VERSION_1 = "v1/";
    
    public static final String GDATA_GET 		= "pmis/get/";
    public static final String GDATA_CREATE 	= "pmis/create/";
    public static final String GDATA_UPDATE 	= "pmis/update/";
    public static final String GDATA_APPEND 	= "pmis/append/";
    public static final String GDATA_REMOVE		= "pmis/remove/";
    
    public static final String GDATA_EMP 						= "emp";
    public static final String GDATA_NODE 						= "node-in-emp-doc";
    public static final String GDATA_NODE_BY_OID_SET 			= "node-in-emp-doc-by-oid-set";
    public static final String GDATA_LIST_NODE 					= "list-node-in-emp-doc";
    
    public static final String GDATA_NODE_REQUEST 				= "node-in-doc-for-request";
    public static final String GDATA_NODE_REJECT 				= "node-in-doc-for-reject";
    public static final String GDATA_APPROVE 					= "node-in-doc-for-approve";

    public static final String GDATA_LIST_NODE_REQUEST 			= "list-node-in-doc-for-request";
    public static final String GDATA_LIST_NODE_APPROVE 			= "list-node-in-doc-for-approve";
    public static final String GDATA_LIST_NODE_REJECT 			= "list-node-in-doc-for-reject";

    public static final String GDATA_APPROVAL_HISTORY = "approval-history";

    public static final String GDATA_APPROVAL_HISTORY_REQUEST	= "approval-history-for-request";
    public static final String GDATA_APPROVAL_HISTORY_REVIEW 	= "approval-history-for-review";
    public static final String GDATA_APPROVAL_HISTORY_APPROVE	= "approval-history-for-approve";
    public static final String GDATA_APPROVAL_HISTORY_REJECT	= "approval-history-for-reject";

    public static final String GDATA_OFFICE_BY_EMPLOYEE		    = "office-by-employee";
    public static final String GDATA_EMPLOYEE_BY_OFFICE	        = "employee-by-office";
    public static final String GDATA_EMPLOYEE_OFFICE_BY_OFFICE	        = "employee-office-by-office";

    public static final String GDATA_MAIN_EMPLOYEE_BY_OID_SET	        = "main-employee-by-oid-set";
    public static final String GDATA_MAIN_EMPLOYEE_BY_OFFICE	        = "main-employee-by-office";
    public static final String GDATA_MAIN_EMPLOYEE_BY_OFFICE_OFFICE_UNIT	        = "main-employee-by-office-office-unit";

    public static final String GDATA_APPROVAL_HISTORY_BY_STATUS 	= "approval-history-by-status";
    public static final String GDATA_APPROVAL_HISTORY_BY_EMPLOYEE	= "approval-history-by-employee";
    public static final String GDATA_APPROVAL_HISTORY_BY_ACTOR	= "approval-history-by-actor";
    public static final String GDATA_APPROVAL_HISTORY_BY_EMPLOYEE_AND_STATUS	= "approval-history-by-employee-and-status";


    public static final String OFFICE = "OFFICE";
    public static final String OFFICE_UNIT = "OFFICE_UNIT";
    public static final String OFFICE_UNIT_POST = "OFFICE_UNIT_POST";
    public static final String COUNT = "COUNT";
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