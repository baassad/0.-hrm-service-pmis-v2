package com.cokreates.grp.util.dummyService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class EmployeeOfficeService {
    public Set<String> getEmployeeByOffice(Set<String> officeOidList) {
//        return new HashSet<>();
        return new HashSet<>(Arrays.asList("a1042f1f-589f-4ce9-90f9-e248eb21c6f8",
                             "7f0fb15c-1833-4143-b5b8-6d3bdc6b6ca8",
                             "5c2adcf3-5108-4df5-8e89-0fbef976b159"));
    }
}
