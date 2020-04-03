package com.cokreates.grp.util.request;

import lombok.Data;

import java.util.List;

@Data
public class OidSetWithStrictnessRequestBodyDTO {

    private List<String> oids;

    private String strict;
}
