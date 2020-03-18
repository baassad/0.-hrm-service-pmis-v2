package com.cokreates.core;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

import org.modelmapper.spi.PropertyInfo;
import org.springframework.beans.factory.annotation.Value;

import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MasterService<Dto extends MasterDTO,Entity extends BaseEntity> implements CklServiceInterface<Dto,Entity>{

//    @Autowired
//    DataServiceClient dataServiceClient;

//    @Autowired
    DataServiceRestTemplateClient<Dto, Entity> dataServiceRestTemplateClient;

    RequestBuildingComponent<Dto> requestBuildingComponent;

    private List<String> nodePath;

    @Value("${spring.application.gdata_end_point_url}")
    private String gdata;

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
    public Dto append(Dto dto) {
    	DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,dto, dto.getOid(), this.getDtoClass());

        String gDataEndPointUrl = gdata+Constant.GDATA_APPEND+Constant.VERSION_1+Constant.GDATA_LIST_NODE_REQUEST;

        return dataServiceRestTemplateClient.getRestTemplateResponse(nodePath, request, gDataEndPointUrl);
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

        LinkedTreeMap<String, Object> mainMap = gsonMap.get("node");

//        //iterate through TreeMap values iterator
//        for (Map.Entry<String,Object> entry : mainMap.entrySet()) {
//            Object value = entry.getValue();
//            String key = entry.getKey();
//            System.out.println(key + " " + value);
//        }

        String mainString = gson.toJson(mainMap);
//        mainString = mainString.replaceAll(null,"\"\"");
        Dto main = (Dto)gson.fromJson(mainString, this.getDtoClass());

        try {
            for (Field field : main.getClass().getDeclaredFields()) {
                field.setAccessible(true); // You might want to set modifier to public first.
                Object value = field.get(main);
                if (value == null) {
                    mainMap.put(field.getName(), "");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

       mainString = gson.toJson(mainMap);
//        mainString = mainString.replaceAll(null,"\"\"");
         main = (Dto)gson.fromJson(mainString, this.getDtoClass());

//        mainString = gson.toJson(main);
//
//        mainString.replaceAll("null" ,"\"\"");
//
//        main = (Dto)gson.fromJson(mainString, this.getDtoClass());
//
        System.out.println(" main ");
        System.out.println(main);

        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,main, node.getOid(), null, this.getDtoClass());

        String gDataEndPointUrl = gdata+Constant.GDATA_UPDATE+Constant.VERSION_1;


        dataServiceRestTemplateClient.updateSingleObject(nodePath, request, gDataEndPointUrl);

        return null;
    }

    @Override
    public Entity updateApprovalHistory(MasterApprovalDTO node) {

        String gDataEndPointUrl = gdata+Constant.GDATA_UPDATE+Constant.VERSION_1;

        DataServiceRequest<MasterApprovalDTO> request = requestBuildingComponent.getRequestForApprovalHistory(node);

        dataServiceRestTemplateClient.updateApprovalHistory(nodePath, request, gDataEndPointUrl);

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

        String gDataEndPointUrl = gdata+Constant.GDATA_GET+Constant.VERSION_1+Constant.GDATA_NODE;
        log.debug("==== gDataEndPointUrl ==== "+gDataEndPointUrl);

        return dataServiceRestTemplateClient.getRestTemplateResponse(nodePath, request, gDataEndPointUrl);
        //return dataServiceRestTemplateClient.getSingleObject(nodePath, request, gDataEndPointUrl);
//        dataServiceRestTemplateClient.getDataFromParticularNode(nodePath, request);

    }


    @Override
    public List<MasterApprovalDTO> getApprovalHistory(MasterApprovalDTO node) {

        String gDataEndPointUrl = gdata+Constant.GDATA_UPDATE+Constant.VERSION_1;

        DataServiceRequest<MasterApprovalDTO> request = requestBuildingComponent.getRequestForApprovalHistory(node);

        return dataServiceRestTemplateClient.getApprovalHistory(nodePath, request, gDataEndPointUrl);

    }

    @Override
    public Dto getNodeFromList(String employeeOid, String nodeOid) {
        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null, employeeOid, nodeOid, this.getDtoClass());

        String gDataEndPointUrl = gdata+Constant.GDATA_GET+Constant.VERSION_1+Constant.GDATA_LIST_NODE;
        log.debug("==== gDataEndPointUrl ==== "+gDataEndPointUrl);

        return dataServiceRestTemplateClient.getRestTemplateResponse(nodePath, request, gDataEndPointUrl);
        //return dataServiceRestTemplateClient.getListSingleObject(nodePath, request, gDataEndPointUrl);
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
    public List<Dto> getList(Dto dto) {

        return null;

//        String gDataEndPointUrl = gdata+Constant.GDATA_UPDATE+Constant.VERSION_1;
//
//        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null, dto.getOid(), null, this.getDtoClass());
//
//        return dataServiceRestTemplateClient.getList(nodePath, request, gDataEndPointUrl);
    }

    @Override
    public Dto convertToDto(Entity entity) {
        return null;
    }
}
