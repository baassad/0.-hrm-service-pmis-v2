package com.cokreates.grp.beans.pim.pmis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PmisRepository extends JpaRepository<Pmis,String > {

    @Query(value = "select oid from hrm.pmis",nativeQuery = true)
    Set<String> getAllOidsFromPmis();

    @Query(value = "select elem->>'oid' from hrm.pmis p,jsonb_array_elements(p.employee_main -> 'qualification' -> 'trainingAndProfessionalCertification' -> 'training') elem where oid = ?1",nativeQuery = true)
    Set<String> getTrainingOids(String oid);

}
