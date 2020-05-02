package com.cokreates.grp.util.request;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class OidSetRequestBody implements RequestBodyDTO{
    List<String> oids;
}
