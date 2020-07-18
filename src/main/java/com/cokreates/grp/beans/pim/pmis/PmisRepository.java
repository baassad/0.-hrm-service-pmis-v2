package com.cokreates.grp.beans.pim.pmis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PmisRepository extends JpaRepository<Pmis,String > {

    @Query(value = "select oid from hrm.pmis",nativeQuery = true)
    Set<String> getAllOidsFromPmis();

}
