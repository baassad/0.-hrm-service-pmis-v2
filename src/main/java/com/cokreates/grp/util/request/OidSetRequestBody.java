package com.cokreates.grp.util.request;

import lombok.Data;

import java.util.Set;

@Data
public class OidSetRequestBody implements RequestBodyDTO{
    Set<String> oids;
}
