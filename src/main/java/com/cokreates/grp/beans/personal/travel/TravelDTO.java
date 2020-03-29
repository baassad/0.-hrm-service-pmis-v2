package com.cokreates.grp.beans.personal.travel;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TravelDTO extends MasterDTO {

	private String countryNameEn;
    private String countryNameBn;
    private String purpose;
    private String from;
    private String to;
    private String govtOrderNo, govtOrderDate;

}
