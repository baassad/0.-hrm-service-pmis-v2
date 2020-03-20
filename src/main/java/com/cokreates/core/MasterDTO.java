package com.cokreates.core;

import com.cokreates.grp.util.request.RequestBodyDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MasterDTO implements RequestBodyDTO {

    private String oid;

    private String nodeOid;

    private String config;

    private Object main;

    private Object temp;

    private Object node;
}
