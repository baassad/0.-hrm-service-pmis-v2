package com.cokreates.grp.beans.pim.pmis;

import com.cokreates.grp.beans.employee.Employee;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<String> findByGradeAndEmployeeOidSet(@Param("oids") List<String> oids, @Param("grades")List<String> grades, Pageable pageable);

    @Query(value = "select oid,employee_main->'professional'->'professionalGeneral'->>'grade' as grade from hrm.pmis where oid in :oids",nativeQuery = true)
    Page<EmployeeGrade> findGradeByEmployeeOids(@Param("oids") List<String> oids,Pageable pageable);

    @Query(value = "select oid,employee_main->'professional'->'professionalGeneral'->>'grade' as grade from hrm.pmis where oid = :oid",nativeQuery = true)
    List<EmployeeGrade> findGradeByEmployeeOid(@Param("oid") String oid);

    @Query(value = "select oid,employee_office ->> 'nodes' as nodes  from hrm.pmis where oid in ?1",nativeQuery = true)
    List<EmployeeOfficeDetails> getEmployeeOffices(List<String> oids);

    @Query(value = "select oid , employee_office ->> 'nodes' as nodes from hrm.pmis where employee_office ->> 'nodes' similar to ?1",nativeQuery = true)
    List<EmployeeOfficeDetails> getEmployeeOfficeDetails(String oidList);

    @Query(value = "select oid,employee_main -> 'personal' -> 'general' ->> 'nameEn' as \"nameEn\"," +
            " employee_main -> 'personal' -> 'general' ->> 'nameBn' as \"nameBn\"," +
            " employee_main -> 'personal' -> 'general' ->> 'email' as \"email\",\n" +
            "employee_main -> 'personal' -> 'general' ->> 'phone' as \"mobileNo\" , employee_main -> 'professional' -> 'professionalGeneral' ->> 'grade' as \"grade\"" +
            " from hrm.pmis " +
            "where oid in :oids",nativeQuery = true)
    List<EmployeePersonalDetails> getEmployeePersonalInfoDetails(@Param("oids") List<String> oids);


}
