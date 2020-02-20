package com.cokreates.grp.beans.professional.serviceHistory;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceHistoryDTO extends MasterDTO {

    private String post;
    private String type;
    private Date from;
    private Date to;
}
