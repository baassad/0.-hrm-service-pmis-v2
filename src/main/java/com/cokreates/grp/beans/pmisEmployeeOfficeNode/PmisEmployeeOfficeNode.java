package com.cokreates.grp.beans.pmisEmployeeOfficeNode;

import com.cokreates.core.BaseEntity;
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

    @Column(columnDefinition = "varchar(255) default 'No'")
    private String isApprover;

    @Column(columnDefinition = "varchar(255) default 'No'")
    private String isReviewer;

    @Column(columnDefinition = "varchar(255) default 'No'")
    private String isAttendanceAdmin;

    @Column(columnDefinition = "varchar(255) default 'No'")
    private String isAttendanceDataEntryOperator;

    @Column(columnDefinition = "varchar(255) default 'No'")
    private String isAwardAdmin;



}
