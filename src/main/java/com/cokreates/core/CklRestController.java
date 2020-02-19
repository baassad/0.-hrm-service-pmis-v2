package com.cokreates.core;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface CklRestController<T extends MasterDTO> {

    public ResponseModel<T> getSelected( RequestModel<T> dto);
    public ResponseModel<T> createAll( RequestModel<T> dto);
    public ResponseModel<T> updateAll( RequestModel<T> dto);
    public ResponseModel<T> deleteAll( List<String> oids);

    public ResponseModel<T> create(RequestModel<T> dto);
    public ResponseModel<T> update(RequestModel<T> dto);
    public ResponseModel<T> delete(RequestModel<T> dto);
    public ResponseModel<T> get(RequestModel<T> dto);
    public ResponseModel<T> getAll(RequestModel<T> dto);

    public boolean hasPermission(String actionTag);

}
