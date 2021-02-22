package com.cokreates.grp.entity.approvalHistory;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Entity
//@Table(name = "approval_history")
public class ApprovalHistory extends BaseEntity {

    @Column(columnDefinition="varchar(50)")
    private String changeType;

    @Column(columnDefinition="varchar(20)")
    private String status;

    @Column(columnDefinition="varchar(40)")
    private String nodeName;

    @Column(columnDefinition="varchar(50)")
    private String employeeOid;
    //private Comment comment;
    //private Change change;
    //private Map<String, String> oldValue;
    //private Map<String, String> newValue;
}
