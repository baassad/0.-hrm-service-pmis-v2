package com.cokreates.grp.beans.pim.pmis;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
@TypeDef(name = "jsonb",typeClass = JsonBinaryType.class)
public class Pmis {

   @Id
   String oid;

}
