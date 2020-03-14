package com.cokreates.core;

import com.cokreates.grp.util.request.RequestBodyDTO;
import lombok.Data;

@Data
public class MasterDTO implements RequestBodyDTO {

    private String oid;

    private String nodeOid;

    private String config;

    private MasterDTO main;

    private MasterDTO temp;
}
