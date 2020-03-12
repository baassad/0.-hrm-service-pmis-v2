package com.cokreates.core;

import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class MasterService<Dto extends MasterDTO,Entity extends BaseEntity> implements CklServiceInterface<Dto,Entity>{

    @Autowired
    DataServiceClient dataServiceClient;

    RequestBuildingComponent<Dto> requestBuildingComponent;

    private List<String> nodePath;

    protected MasterService(RequestBuildingComponent<Dto> requestBuildingComponent) {
        this.requestBuildingComponent = requestBuildingComponent;
    }

    public List<String> getNodePath() {
        return this.nodePath;
    }

    public void setNodePath(List<String> nodePath) {
        this.nodePath = nodePath;
    }

    public Entity preCreate(Dto dto){
        return  null;

    }

    public Entity postCreate(Entity entity){
        return null;
    }

    @Override
    public Entity create(Dto dto) {
        return null;
    }


    @Override
    public List<Entity> createAll(List<Dto> dtos) {
        return null;
    }

    @Override
    public boolean isValid(List<Dto> dto) {
        return false;
    }

    public Entity preUpdate(Dto dto){
        return  null;

    }

    public Entity postUpdate(Entity entity){

        return null;
    }

    @Override
    public Entity update(Dto dto) {
        return null;
    }

    @Override
    public List<Entity> updateAll(List<Dto> dtos) {
        return null;
    }

    @Override
    public Dto delete(String oid) {
        return null;
    }

    @Override
    public List<Dto> deleteAll(List<String> oids) {
        return null;
    }

    @Override
    public Dto getNode(String employeeOid) {

        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null, employeeOid, null);

        DataServiceResponse<Dto> response = dataServiceClient.getDataFromParticularNode(request);

        return response.getResponseBody().getTemp();
    }

    @Override
    public Dto getNodeFromList(String employeeOid, String nodeOid) {

        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null, employeeOid, nodeOid);

        DataServiceResponse<Dto> response = dataServiceClient.getDataFromParticularNode(request);

        return response.getResponseBody().getTemp();
    }

    @Override
    public Dto get(String employeeOid) {

        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null,employeeOid, null);

        DataServiceResponse<Dto> response = dataServiceClient.getDataFromParticularNode(request);

        return response.getResponseBody().getTemp();
    }

    @Override
    public List<Dto> getSelected(List<String> oids) {
        return null;
    }

    @Override
    public List<Dto> getList() {
        return null;
    }

    @Override
    public Dto convertToDto(Entity entity) {
        return null;
    }
}
