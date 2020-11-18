package com.cokreates.grp.beans.employeeOfficeV2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeOfficeV2Repository extends
        JpaRepository<EmployeeOfficeV2, String>,
        JpaSpecificationExecutor<EmployeeOfficeV2> {


	List<EmployeeOfficeV2> findAllByOfficeOidAndRowStatus(String officeOid, String rowStatus);
    List<EmployeeOfficeV2> findAllByEmployeeOidAndRowStatus(String employeeOid, String rowStatus);
    List<EmployeeOfficeV2> findAllByEmployeeOidAndOfficeOidAndRowStatus(String employeeOid, String officeOid, String rowStatus);
    List<EmployeeOfficeV2> findAllByOfficeOidAndOfficeUnitOidAndRowStatus(String officeOid, String officeUnitOid, String rowStatus);
    List<EmployeeOfficeV2> findAllByEmployeeOidAndOfficeUnitPostOidAndRowStatusAndStatus(String employeeOid, String officeUnitPostOid, String rowStatus, String status);
    EmployeeOfficeV2 findByEmployeeOidAndEmployeeOfficeOidAndRowStatus(String pmisOid, String employeeOfficeOid, String rowStatus);

}
