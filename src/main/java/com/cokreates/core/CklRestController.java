package com.cokreates.core;

public interface CklRestController<T> {

    public ResponseModel<T> getSelected( RequestModel<String> dto);
    public ResponseModel<T> createAll( RequestModel<T> dto);
    public ResponseModel<T> updateAll( RequestModel<T> dto);
    public ResponseModel<T> deleteAll( RequestModel<String> oids);

    public ResponseModel<T> create(RequestModel<T> dto);
    public ResponseModel<T> update(RequestModel<T> dto);
    public ResponseModel<T> delete(RequestModel<T> dto);
    public ResponseModel<T> get(RequestModel<T> dto);
    public ResponseModel<T> getFromList(RequestModel<T> dto);
    public ResponseModel<T> getAll(RequestModel<T> dto);

    public boolean hasPermission(String actionTag);

}
