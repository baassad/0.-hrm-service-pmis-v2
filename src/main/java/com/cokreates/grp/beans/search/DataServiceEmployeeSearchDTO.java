package com.cokreates.grp.beans.search;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class DataServiceEmployeeSearchDTO {

    private Set<String> listOfOid;

    private String category;

    private String name;

    private int limit;

    private int offset;

    
}
