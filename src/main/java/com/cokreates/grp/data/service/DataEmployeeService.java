package com.cokreates.grp.data.service;

import org.springframework.stereotype.Service;

@Service
public class DataEmployeeService {

    public String makeSmallerCase(String sentence) {
        return sentence.toLowerCase();
    }
}
