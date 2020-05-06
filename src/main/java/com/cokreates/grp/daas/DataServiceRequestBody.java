package com.cokreates.grp.daas;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.util.request.MiscellaneousRequestProperty;
import lombok.Data;

import java.util.List;

@Data
public class DataServiceRequestBody<T> {

    private T node;

    private Object general;

    private String employeeOid;

    private String reviewerOid;

    private String requesterOid;

    private String approverOid;

    private String approvalHistoryOid;

    private String status;

    private Object comment;

    private String nodeOid;

    private List<String> nodePath;

    private Class<T> dtoClass;

    private MiscellaneousRequestProperty miscellaneousRequestProperty;


}
