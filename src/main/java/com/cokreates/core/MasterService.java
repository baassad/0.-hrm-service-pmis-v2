package com.cokreates.core;

import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

@Slf4j
public abstract class MasterService<Dto extends MasterDTO,Entity extends BaseEntity> implements CklServiceInterface<Dto,Entity>{

//    @Autowired
//    DataServiceClient dataServiceClient;

//    @Autowired
    DataServiceRestTemplateClient<Dto, Entity> dataServiceRestTemplateClient;

    RequestBuildingComponent<Dto> requestBuildingComponent;

    private List<String> nodePath;

    protected MasterService(RequestBuildingComponent<Dto> requestBuildingComponent,
                            DataServiceRestTemplateClient<Dto, Entity> dataServiceRestTemplateClient) {
        this.requestBuildingComponent = requestBuildingComponent;
        this.dataServiceRestTemplateClient = dataServiceRestTemplateClient;
    }

    public Class<Dto> getDtoClass() {return null;}

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
    public Entity update(Dto node) {

        Gson gson = new Gson();
        String element = gson.toJson(node);
        HashMap<String, LinkedTreeMap> gsonMap = gson.fromJson(element, HashMap.class);

        LinkedTreeMap mainMap = gsonMap.get("node");
        String mainString = gson.toJson(mainMap);
        Dto main = (Dto)gson.fromJson(mainString, this.getDtoClass());


        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,main, node.getOid(), null, this.getDtoClass());

        dataServiceRestTemplateClient.updateSingleObject(nodePath, request);

        return null;
    }

    @Override
    public Entity updateApprovalHistory(Dto node) {

        Gson gson = new Gson();
        String element = gson.toJson(node);
        HashMap<String, LinkedTreeMap> gsonMap = gson.fromJson(element, HashMap.class);

        LinkedTreeMap commentMap = gsonMap.get("comment");
        String commentMapString = gson.toJson(commentMap);
        Dto comment = (Dto)gson.fromJson(commentMapString, this.getDtoClass());


        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForApprovalHistoryUpdate(node);

        dataServiceRestTemplateClient.updateSingleObject(nodePath, request);

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


        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null, employeeOid, null, this.getDtoClass());

         return dataServiceRestTemplateClient.getSingleObject(nodePath, request);
//        dataServiceRestTemplateClient.getDataFromParticularNode(nodePath, request);

    }

    @Override
    public Dto getNodeFromList(String employeeOid, String nodeOid) {

        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null, employeeOid, nodeOid, this.getDtoClass());

        return dataServiceRestTemplateClient.getSingleObject(nodePath, request);


//        DataServiceResponse<Dto> response = dataServiceClient.getDataFromParticularNode(request);
//
//        return response.getResponseBody().getTemp();
    }

    @Override
    public Dto get(String employeeOid) {
        return null;

//        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null,employeeOid, null);
//
//        DataServiceResponse<Dto> response = dataServiceClient.getDataFromParticularNode(request);
//
//        return response.getResponseBody().getTemp();
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
