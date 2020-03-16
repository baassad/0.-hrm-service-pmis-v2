package com.cokreates.grp.daas;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.common.Comment;
import lombok.Data;

import java.util.List;

@Data
public class DataServiceRequestBody<T extends MasterDTO> {

    private T node;

    private String employeeOid;

    private String approvalHistoryOid;

    private String approvalStatus;

    private Comment comment;

    private String nodeOid;

    private List<String> nodePath;

    private Class<T> dtoClass;


}
