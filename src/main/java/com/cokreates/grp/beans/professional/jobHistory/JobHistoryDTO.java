package com.cokreates.grp.beans.professional.jobHistory;


import java.sql.Date;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JobHistoryDTO extends MasterDTO {

	private String post, type, officeName, officeAddress;
	private Date from, to;

}
