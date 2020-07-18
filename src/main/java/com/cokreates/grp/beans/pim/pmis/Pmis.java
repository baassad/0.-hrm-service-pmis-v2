package com.cokreates.grp.beans.pim.pmis;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class Pmis {

   @Id
   String oid;


}
