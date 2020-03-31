package com.cokreates.grp.daas;

import lombok.Data;

import java.util.List;

@Data
public class DataServiceResponseBodyForList<T> {

    List<T> main;

    List<T> temp;

}
