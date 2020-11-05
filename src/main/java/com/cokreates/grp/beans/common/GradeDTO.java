package com.cokreates.grp.beans.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GradeDTO {

    private String oid;

    private String nameEn;

    private String nameBn;

}
