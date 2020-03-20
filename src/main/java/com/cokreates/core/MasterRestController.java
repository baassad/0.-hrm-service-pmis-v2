package com.cokreates.core;

import java.util.Collections;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cokreates.grp.util.components.ResultBuildingComponent;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@Component
public class MasterRestController<D extends MasterDTO,E extends BaseEntity> implements CklRestController<D>{

    protected MasterService<D,E> service;

    @Autowired
    protected ResultBuildingComponent<D> resultBuildingComponent;

    public MasterRestController(MasterService<D,E> service){
        this.service = service;
    }

    @Override
    @PostMapping(Constant.ENDPOINT_CREATE)
    public ResponseModel<D> create(@Valid @RequestBody RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), Collections.singletonList(service.convertToDto(service.create(requestDTO.getBody().getData().get(0)))));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_CREATE_ALL)
    public ResponseModel<D> createAll(@Valid @RequestBody RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),service.createAll(requestDTO.getBody().getData()).stream()
                .map(o -> service.convertToDto(o)).collect(Collectors.toList()));
    }
    
    @Override
    @PostMapping(Constant.ENDPOINT_APPEND)
    public ResponseModel<D> append(@RequestBody RequestModel<D> requestDTO) {
    	return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), Collections.singletonList(service.append(requestDTO.getBody().getData().get(0))));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_UPDATE)
    public ResponseModel<D>
    update( @RequestBody RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), Collections.singletonList(service.convertToDto(service.update(requestDTO.getBody().getData().get(0)))));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_UPDATE_ALL)
    public ResponseModel<D> updateAll(@RequestBody RequestModel<D> requestDTO) {

        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),service.updateAll(requestDTO.getBody().getData())
                .stream().map(o->service.convertToDto(o)).collect(Collectors.toList()));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_DELETE)
    public ResponseModel<D> delete(@RequestBody RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),Collections.singletonList(service.delete(requestDTO.getBody().getData().get(0).getOid())));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_DELETE_ALL)
    public ResponseModel<D> deleteAll(@RequestBody RequestModel<String> requestDTO) {

        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),service.deleteAll(requestDTO.getBody().getData()));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET)
    public ResponseModel<D>
    get(@Valid @RequestBody RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),Collections.singletonList(service.getNode(requestDTO.getBody().getData().get(0).getOid())));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET_FROM_LIST)
    public ResponseModel<D>
    getFromList(@Valid @RequestBody RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),Collections.singletonList(service.getNodeFromList(requestDTO.getBody().getData().get(0).getOid(), requestDTO.getBody().getData().get(0).getNodeOid())));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET_LIST_BY_OID_SET)
    public ResponseModel<D> getSelected(@RequestBody RequestModel<String> dto) {

        return resultBuildingComponent.retrieveResult(dto.getHeader(),service.getSelected(dto.getBody().getData()));
    }

    @Override
    @PostMapping(Constant.ENDPOINT_GET_LIST)
    public ResponseModel<D> getAll(@RequestBody RequestModel<D> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(),(service.getList(requestDTO.getBody().getData().get(0).getOid())));
    }

    @Override
    public boolean hasPermission(String actionTag) {
        return false;
    }
}
