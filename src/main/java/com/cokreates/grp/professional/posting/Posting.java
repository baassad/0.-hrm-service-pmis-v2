package com.cokreates.grp.professional.posting;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Posting extends BaseEntity {

    private String post, organization, location, from, to, paySacle, payLastDrawn;
}
