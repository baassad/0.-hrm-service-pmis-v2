package com.cokreates.core;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;

@Data
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity {

    /*@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false)*/
    private String oid;

    private String nodeOid;

    //@Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    //@Column(name = "updated_by")
    private String updatedBy;

    //@Column(name = "row_status")
    private String rowStatus = Constant.STATUS_ACTIVE;


    /*@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", nullable = false, updatable = false)
    @CreatedDate*/
    private Date createdOn;

    /*@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", nullable = true)
    @LastModifiedDate
    @JsonIgnore*/
    private Date updatedOn;

    //@Column(name = "config", columnDefinition = "TEXT")
    private String config;
    private String dataStatus;

}