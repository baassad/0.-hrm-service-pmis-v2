package com.cokreates.grp.util.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaritalStatusDTO {

    private String oid;

    private String nameEn;
    private String nameBn;
}
