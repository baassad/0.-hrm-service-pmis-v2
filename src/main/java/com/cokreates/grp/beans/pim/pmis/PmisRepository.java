package com.cokreates.grp.beans.pim.pmis;

import com.cokreates.grp.beans.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PmisRepository extends JpaRepository<Pmis,String > {

    @Query(value = "select oid from hrm.pmis",nativeQuery = true)
    Set<String> getAllOidsFromPmis();

    @Query(value = "select oid , employee_main ->'professional' -> 'professionalGeneral' ->> 'govtId' as govtId from hrm.pmis where oid in ?1 ",nativeQuery = true)
    List<EmployeeGovtId> findGovtIdByEmployeeOid(List<String> oids);

    @Query(value = "select elem->>'oid' from hrm.pmis p,jsonb_array_elements(p.employee_main -> 'qualification' -> 'trainingAndProfessionalCertification' -> 'training') elem where oid = ?1",nativeQuery = true)
    Set<String> getTrainingOids(String oid);

    @Query(value = "select oid from hrm.pmis where employee_main->'professional'->'professionalGeneral'->>'grade' in :grades and oid in :oids",nativeQuery = true)
    Set<String> findByGradeAndEmployeeOidSet(@Param("oids") Set<String> oids,@Param("grades")Set<String> grades);

}
