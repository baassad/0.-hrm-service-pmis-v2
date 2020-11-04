package com.cokreates.grp.beans.pmisEmployeeOfficeNode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmisEmployeeOfficeNodeRepository extends
        JpaRepository<PmisEmployeeOfficeNode, String>,
        JpaSpecificationExecutor<PmisEmployeeOfficeNode> {


    List<PmisEmployeeOfficeNode> findAllByPmisOidAndRowStatus(String pmisOid, String rowStatus);


}
