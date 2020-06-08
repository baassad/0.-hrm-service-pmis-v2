package com.cokreates.grp.data.constants;

public interface Api {

    String CREATE_EMP = "/hrm/pmis/create/v1/emp";
    String IMPORT_EMP = "/hrm/pmis/import/v1/emp";

    String GET_EMP = "/hrm/pmis/get/v1/emp";
    String GET_EMPLOYEE_OFFICE = "/hrm/pmis/get/v1/employee-office";
    String READ_NODE_FROM_EMPLOYEE_DOC = "/hrm/pmis/get/v1/node-in-emp-doc";
    String READ_NODE_FROM_EMPLOYEE_DOC_BY_OID_SET = "/hrm/pmis/get/v1/node-in-emp-doc-by-oid-set";
    String READ_EMPLOYEE_DETAILS = "/hrm/pmis/get/v1/employee-details";
    String READ_OFFICE_BY_EMPLOYEE = "/hrm/pmis/get/v1/office-by-employee";
    String READ_EMPLOYEE_BY_OFFICE = "/hrm/pmis/get/v1/employee-by-office";
    String READ_MAIN_EMPLOYEE_BY_OID_SET = "/hrm/pmis/get/v1/main-employee-by-oid-set";
    String READ_MAIN_EMPLOYEE_BY_OFFICE = "/hrm/pmis/get/v1/main-employee-by-office";
    String READ_MAIN_EMPLOYEE_BY_OFFICE_OFFICE_UNIT = "/hrm/pmis/get/v1/main-employee-by-office-office-unit";
    String READ_EMPLOYEE_OFFICE_BY_OFFICE = "/hrm/pmis/get/v1/employee-office-by-office";
    String READ_NODE_IN_LIST_FROM_EMPLOYEE_DOC = "/hrm/pmis/get/v1/list-node-in-emp-doc";
    String READ_FROM_APPROVAL_HISTORY_BY_STATUS = "/hrm/pmis/get/v1/approval-history-by-status";
    String READ_FROM_APPROVAL_HISTORY_BY_EMPLOYEE = "/hrm/pmis/get/v1/approval-history-by-employee";
    String READ_FROM_APPROVAL_HISTORY_BY_ACTOR = "/hrm/pmis/get/v1/approval-history-by-actor";
    String READ_FROM_APPROVAL_HISTORY_BY_EMPLOYEE_AND_STATUS = "/hrm/pmis/get/v1/approval-history-by-employee-and-status";
    String READ_FROM_APPROVAL_HISTORY = "/hrm/pmis/get/v1/approval-history";

    String UPDATE_NODE_IN_DOCUMENT_FOR_REQUEST = "/hrm/pmis/update/v1/node-in-doc-for-request";
    String REMOVE_NODE_IN_DOCUMENT_FOR_REQUEST = "/hrm/pmis/remove/v1/node-in-doc-for-request";
    String APPEND_NODE_IN_LIST_FOR_REQUEST = "/hrm/pmis/append/v1/list-node-in-doc-for-request";
    String UPDATE_NODE_IN_LIST_FOR_REQUEST = "/hrm/pmis/update/v1/list-node-in-doc-for-request";
    String REMOVE_NODE_IN_LIST_FOR_REQUEST = "/hrm/pmis/remove/v1/list-node-in-doc-for-request";
    String UPDATE_APPROVAL_HISTORY_FOR_REVIEW = "/hrm/pmis/update/v1/approval-history-for-review";
    String UPDATE_APPROVAL_HISTORY_FOR_REJECT = "/hrm/pmis/update/v1/approval-history-for-reject";
    String UPDATE_APPROVAL_HISTORY_FOR_APPROVE = "/hrm/pmis/update/v1/approval-history-for-approve";
    String APPEND_NODE_EMPLOYEE_OFFICE = "/hrm/pmis/append/v1/employee-office";
    String UPDATE_NODE_EMPLOYEE_OFFICE = "/hrm/pmis/update/v1/employee-office";
    String GET_EMPLOYEES = "/hrm/pmis/v1/get-employees-by-name";

    // String CREATE_EMPLOYEE = "";

    // String GET_EMP = "";
    // String READ_EMPLOYEE_DETAILS = "";
    // String READ_NODE_FROM_EMPLOYEE_DOC = "";
    // String READ_NODE_IN_LIST_FROM_EMPLOYEE_DOC = "";
    // String GET_EMPLOYEE_OFFICE = "";
    // String READ_EMPLOYEE_BY_OFFICE = "";
    // String READ_OFFICE_BY_EMPLOYEE = "";
    // String READ_EMPLOYEE_OFFICE_BY_OFFICE = "";
    // String READ_MAIN_EMPLOYEE_BY_OFFICE_OFFICE_UNIT = "";
    // String READ_MAIN_EMPLOYEE_BY_OFFICE = "";
    // String READ_MAIN_EMPLOYEE_BY_OID_SET = "";
    // String READ_NODE_FROM_EMPLOYEE_DOC_BY_OID_SET = "";
    // String READ_FROM_APPROVAL_HISTORY_BY_ACTOR = "";
    // String READ_FROM_APPROVAL_HISTORY_BY_EMPLOYEE = "";
    // String READ_FROM_APPROVAL_HISTORY_BY_STATUS = "";
    // String READ_FROM_APPROVAL_HISTORY = "";
    // String READ_FROM_APPROVAL_HISTORY_BY_EMPLOYEE_AND_STATUS = "";
    // String GET_EMPLOYEES = "";


    // String UPDATE_NODE_IN_DOCUMENT_FOR_REQUEST = "";
    // String REMOVE_NODE_IN_DOCUMENT_FOR_REQUEST = "";
    // String APPEND_NODE_IN_LIST_FOR_REQUEST = "";
    // String UPDATE_NODE_IN_LIST_FOR_REQUEST = "";
    // String REMOVE_NODE_IN_LIST_FOR_REQUEST = "";
    // String UPDATE_APPROVAL_HISTORY_FOR_REVIEW = "";
    // String UPDATE_APPROVAL_HISTORY_FOR_APPROVE = "";
    // String UPDATE_APPROVAL_HISTORY_FOR_REJECT = "";
    // String UPDATE_NODE_EMPLOYEE_OFFICE = "";
    // String APPEND_EMPLOYEE_OFFICE = "";


    // String IMPORT_EMP = "";
}