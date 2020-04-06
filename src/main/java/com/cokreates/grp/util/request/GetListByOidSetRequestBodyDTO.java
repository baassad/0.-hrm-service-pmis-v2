package com.cokreates.grp.util.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
public class GetListByOidSetRequestBodyDTO {

    @NotEmpty
    private List<@NotBlank String> oids;

    private String strict;
}
