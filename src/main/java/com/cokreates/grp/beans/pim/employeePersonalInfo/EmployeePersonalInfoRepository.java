package com.cokreates.grp.beans.pim.employeePersonalInfo;

import com.cokreates.grp.beans.pim.base.ServiceRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface EmployeePersonalInfoRepository extends ServiceRepository<EmployeePersonalInfo> {

    @Query(value = "select * from hrm.employee_personal_info where employee_oid in ?1 and is_deleted = 'No'",nativeQuery = true)
    List<EmployeePersonalInfo> getEmployeePersonalInfoList(Set<String> employeeOids);

}
