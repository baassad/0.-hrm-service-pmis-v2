package com.cokreates.core;

import com.cokreates.grp.util.components.ResultBuildingComponent;
import com.cokreates.grp.util.request.OidSetRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@CrossOrigin
@Slf4j
@Component
public class MasterRestController<D extends MasterDTO,E extends BaseEntity> implements CklRestController<D>{

    protected CklServiceInterface<D,E> cklServiceInterface;

    @Autowired
    protected ResultBuildingComponent<D> resultBuildingComponent;

    public MasterRestController(CklServiceInterface<D,E> serviceInterface){
        this.cklServiceInterface = serviceInterface;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET_LIST_BY_OID_SET)
    public ResponseModel<D> getSelected(RequestModel<OidSetRequestBody> dto) {

        return resultBuildingComponent.retrieveResult(dto.getHeader(),cklServiceInterface.getSelected(dto.getBody().));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_CREATE)
    public ResponseModel<D> create(RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),cklServiceInterface.create());
    }

    @Override
    @PostMapping(Constant.ENDPOINT_CREATE_ALL)
    public ResponseModel<D> createAll(RequestModel<D> dto) {
        return resultBuildingComponent.retrieveResult(requestDTO.)
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
