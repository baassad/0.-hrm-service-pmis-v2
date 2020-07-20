package com.cokreates.grp.beans.pim.employeeOfficePim;

import com.cokreates.grp.beans.pim.base.ServiceRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface EmployeeOfficeRepository extends ServiceRepository<EmployeeOffice> {

    List<EmployeeOffice> findByOfficeOidInAndStatusAndIsDeleted(Set<String> officeOid,String status,String answer);

    List<EmployeeOffice> findByOfficeUnitOidInAndStatusAndIsDeleted(Set<String> officeUnitOid,String status,String answer);

    List<EmployeeOffice> findByOfficeUnitPostOidInAndStatusAndIsDeleted(Set<String> officeUnitPostOid,String status,String answer);

    @Query(value = "select * from hrm.employee_office where employee_oid in ?1 and status = 'Active' and is_deleted = 'No'",nativeQuery = true)
    List<EmployeeOffice> findActiveEmployeeByEmployeeOid(Set<String> employeeOids);

}
