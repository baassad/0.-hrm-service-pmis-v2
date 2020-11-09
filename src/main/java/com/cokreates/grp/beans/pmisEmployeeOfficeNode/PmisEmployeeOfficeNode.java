package com.cokreates.grp.beans.pmisEmployeeOfficeNode;

import com.cokreates.core.BaseEntity;
import com.cokreates.core.Constant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmisEmployeeOfficeNode extends BaseEntity {
    private String pmisOid;
    private String employeeOfficeOid;

    private String isApprover = Constant.NO;
    private String isReviewer = Constant.NO;
    private String isOfficeAdmin = Constant.NO;
    private String isAttendanceAdmin = Constant.NO;
    private String isAttendanceDataEntryOperator = Constant.NO;
    private String isAwardAdmin = Constant.NO;



}
