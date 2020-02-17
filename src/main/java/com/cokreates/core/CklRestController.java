package com.cokreates.core;

import org.springframework.web.bind.annotation.RequestBody;

public interface CklRestController<T extends MasterDTO> {

    public ResponseModel<T> getSelected(@RequestBody RequestModel<T> dto);
    public ResponseModel<T> createAll(@RequestBody RequestModel<T> dto);
    public ResponseModel<T> updateAll(@RequestBody RequestModel<T> dto);
    public ResponseModel<T> deleteAll(@RequestBody RequestModel<T> dto);

}
