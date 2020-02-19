package com.cokreates.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@CrossOrigin
@Slf4j
@Component
public class MasterRestController<D extends MasterDTO,E extends BaseEntity> implements CklRestController<D>{

    protected CklServiceInterface<D,E> cklServiceInterface;

    public MasterRestController(CklServiceInterface<D,E> serviceInterface){
        this.cklServiceInterface = serviceInterface;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET_LIST_BY_OID_SET)
    public ResponseModel<D> getSelected(RequestModel<D> dto) {
        return null;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_CREATE)
    public ResponseModel<D> create(RequestModel<D> dto) {
        return null;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_CREATE_ALL)
    public ResponseModel<D> createAll(RequestModel<D> dto) {
        return null;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_UPDATE)
    public ResponseModel<D> update(RequestModel<D> dto) {
        return null;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_UPDATE_ALL)
    public ResponseModel<D> updateAll(RequestModel<D> dto) {
        return null;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_DELETE)
    public ResponseModel<D> deleteAll(List<String> oids) {
        return null;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET_LIST)
    public ResponseModel<D> getAll(RequestModel<D> dto) {
        return null;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_DELETE_ALL)
    public ResponseModel<D> delete(RequestModel<D> dto) {
        return null;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET)
    public ResponseModel<D> get(RequestModel<D> dto) {
        return null;
    }

    @Override
    public boolean hasPermission(String actionTag) {
        return false;
    }
}
