package com.cokreates.core;

import com.cokreates.grp.util.components.ResultBuildingComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collections;
import java.util.stream.Collectors;

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
    public ResponseModel<D> getSelected(RequestModel<String> dto) {

        return resultBuildingComponent.retrieveResult(dto.getHeader(),cklServiceInterface.getSelected(dto.getBody().getData()));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_CREATE)
    public ResponseModel<D> create(RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), Collections.singletonList(cklServiceInterface.convertToDto(cklServiceInterface.create(requestDTO.getBody().getData().get(0)))));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_CREATE_ALL)
    public ResponseModel<D> createAll(RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),cklServiceInterface.createAll(requestDTO.getBody().getData()).stream()
                .map(o -> cklServiceInterface.convertToDto(o)).collect(Collectors.toList()));
    }
    
    @Override
    @PostMapping(Constant.ENDPOINT_APPEND)
    public ResponseModel<D> append(@RequestBody RequestModel<D> requestDTO) {
    	return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), Collections.singletonList(cklServiceInterface.append(requestDTO.getBody().getData().get(0))));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_UPDATE)
    public ResponseModel<D> update(RequestModel<D> requestDTO) {

        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),Collections.singletonList(cklServiceInterface.convertToDto(cklServiceInterface.update(requestDTO.getBody().getData().get(0)))));

    }

    @Override
    @PostMapping(Constant.ENDPOINT_UPDATE_ALL)
    public ResponseModel<D> updateAll(RequestModel<D> requestDTO) {

        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),cklServiceInterface.updateAll(requestDTO.getBody().getData())
                .stream().map(o->cklServiceInterface.convertToDto(o)).collect(Collectors.toList()));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_DELETE)
    public ResponseModel<D> delete(RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),Collections.singletonList(cklServiceInterface.delete(requestDTO.getBody().getData().get(0).getOid())));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET_LIST)
    public ResponseModel<D> getAll(RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),cklServiceInterface.getList());
    }

    @Override
    @PostMapping(Constant.ENDPOINT_DELETE_ALL)
    public ResponseModel<D> deleteAll(RequestModel<String> requestDTO) {

        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),cklServiceInterface.deleteAll(requestDTO.getBody().getData()));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET)
    public ResponseModel<D>
    get(@Valid @RequestBody RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),Collections.singletonList(cklServiceInterface.getNode(requestDTO.getBody().getData().get(0).getOid())));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET_FROM_LIST)
    public ResponseModel<D>
    getFromList(@Valid @RequestBody RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),Collections.singletonList(cklServiceInterface.getNodeFromList(requestDTO.getBody().getData().get(0).getOid(), requestDTO.getBody().getData().get(0).getNodeOid())));
    }

    @Override
    public boolean hasPermission(String actionTag) {
        return false;
    }
}
