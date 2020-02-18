package com.cokreates.core;

import java.util.List;

public class MasterRestController<D extends MasterDTO,E extends BaseEntity> implements CklRestController<D>{

    protected CklServiceInterface<D,E> cklServiceInterface;

    @Override
    public ResponseModel<D> getSelected(RequestModel<D> dto) {
        return null;
    }

    @Override
    public ResponseModel<D> createAll(RequestModel<D> dto) {
        return null;
    }

    @Override
    public ResponseModel<D> updateAll(RequestModel<D> dto) {
        return null;
    }

    @Override
    public ResponseModel<D> deleteAll(List<String> oids) {
        return null;
    }

    @Override
    public ResponseModel<D> create(RequestModel<D> dto) {
        return null;
    }

    @Override
    public ResponseModel<D> update(RequestModel<D> dto) {
        return null;
    }

    @Override
    public ResponseModel<D> delete(RequestModel<D> dto) {
        return null;
    }

    @Override
    public ResponseModel<D> get(RequestModel<D> dto) {
        return null;
    }

    @Override
    public boolean hasPermission(String actionTag) {
        return false;
    }
}
