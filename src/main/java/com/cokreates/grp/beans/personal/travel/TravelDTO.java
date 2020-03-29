package com.cokreates.grp.beans.personal.travel;

import java.sql.Date;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TravelDTO extends MasterDTO {

	private String countryNameEn;
    private String countryNameBn;
    private String purpose;
    private Date from, to, govtOrderDate;
    private String govtOrderNo;

}
