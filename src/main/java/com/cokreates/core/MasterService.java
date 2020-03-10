package com.cokreates.core;

import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceRequestBody;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class MasterService<Dto extends MasterDTO,Entity extends BaseEntity> implements CklServiceInterface<Dto,Entity>{

    DataServiceClient<Dto> dataServiceClient;

    RequestBuildingComponent<Dto> requestBuildingComponent;

    protected MasterService(DataServiceClient<Dto> dataServiceClient,RequestBuildingComponent<Dto> requestBuildingComponent) {
        this.dataServiceClient = dataServiceClient;
        this.requestBuildingComponent = requestBuildingComponent;
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
    public Dto get(String oid) {

        List<String> nodePath = new ArrayList<>();
        nodePath.add("personal");
        nodePath.add("general");

        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null,oid);

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
