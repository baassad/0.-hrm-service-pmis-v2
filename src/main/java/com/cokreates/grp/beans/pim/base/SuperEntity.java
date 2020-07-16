package com.cokreates.grp.beans.pim.base;


import lombok.Data;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class SuperEntity {

    @Id
    private String oid;
    private String isDeleted;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
}
