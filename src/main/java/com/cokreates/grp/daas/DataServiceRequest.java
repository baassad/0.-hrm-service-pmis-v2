package com.cokreates.grp.daas;

import com.cokreates.core.MasterDTO;
import lombok.Data;

@Data
public class DataServiceRequest<T> {

     private DataServiceRequestBody<T> body;

}
