package com.cokreates.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String oid;

    @Column(name = "created_by", nullable = false, updatable = false, columnDefinition="varchar(100)")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", nullable = false, updatable = false)
    @CreatedDate
    @JsonIgnore
    private Date createdOn;

    @Column(name = "updated_by", columnDefinition="varchar(100)")
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", nullable = true)
    @LastModifiedDate
    @JsonIgnore
    private Date updatedOn;

    @Column(name = "is_deleted", columnDefinition="varchar(5)")
    private String isDeleted = Constant.NO;

    @Column(name = "status")
    private String rowStatus = Constant.STATUS_DRAFT;

    @Column(name = "config", columnDefinition = "TEXT")
    private String config;
    private String dataStatus;

}