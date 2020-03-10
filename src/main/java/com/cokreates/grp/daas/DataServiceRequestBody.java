package com.cokreates.grp.daas;

import com.cokreates.core.MasterDTO;
import lombok.Data;

import java.util.List;

@Data
public class DataServiceRequestBody<T extends MasterDTO> {

    private T node;

    private String employeeOid;

    private List<String> nodePath;
}
