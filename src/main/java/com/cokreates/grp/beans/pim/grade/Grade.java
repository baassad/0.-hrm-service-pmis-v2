package com.cokreates.grp.beans.pim.grade;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table
public class Grade {
    @Id
    private String oid;

    private String isDeleted;
    private String nameEn;
    private String nameBn;
    private BigDecimal sortOrder;
}
