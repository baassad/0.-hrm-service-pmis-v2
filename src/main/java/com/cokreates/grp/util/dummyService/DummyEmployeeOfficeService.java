package com.cokreates.grp.util.dummyService;

import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class DummyEmployeeOfficeService {
    public Set<String> getEmployeeByOffice(List<String> officeOidList) {
//        return new HashSet<>();
        return new HashSet(Arrays.asList("f1613e8b-bb23-414f-b82d-4973edc6f15a",
                             "db74efdb-afe5-45d7-82da-43727378b755"));
    }
}
