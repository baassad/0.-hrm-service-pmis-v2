package com.cokreates.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public abstract class MasterService<Dto extends MasterDTO,Entity extends BaseEntity> implements CklServiceInterface<Dto,Entity>{

//    @Autowired
//    DataServiceClient dataServiceClient;

    @Autowired
    private ModelMapper modelMapper;


    DataServiceRestTemplateClient<Dto, Entity> dataServiceRestTemplateClient;

    RequestBuildingComponent<Dto> requestBuildingComponent;

    private List<String> nodePath;

    private String type;

    @Value("${spring.application.gdata_end_point_url}")
    private String gdata;

    protected MasterService(RequestBuildingComponent<Dto> requestBuildingComponent,
                            DataServiceRestTemplateClient<Dto, Entity> dataServiceRestTemplateClient) {
        this.requestBuildingComponent = requestBuildingComponent;
        this.dataServiceRestTemplateClient = dataServiceRestTemplateClient;
    }

    public Class<Dto> getDtoClass() {
        return (Class<Dto>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<Entity> getEntityClass() {
        return (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public List<String> getNodePath() {
        return this.nodePath;
    }

    public String getGData() { return this.gdata; }

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
    public Dto create(Dto dto) {
        return null;
//        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestToCreateEmployee(dto);
//
//        String gDataEndPointUrl = gdata + Constant.GDATA_CREATE + Constant.VERSION_1 + Constant.ENDPOINT_EMPLOYEE;
//
//        return dataServiceRestTemplateClient.getRestTemplateResponseForCreation(request,gDataEndPointUrl);
    }

    @Override
    public Dto append(Dto dto) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String element = gson.toJson(dto);
        HashMap<String, LinkedTreeMap> gsonMap = gson.fromJson(element, HashMap.class);

        LinkedTreeMap<String, Object> mainMap = gsonMap.get("node");

        String mainString = gson.toJson(mainMap);
        Dto updateNode = (Dto)gson.fromJson(mainString, this.getDtoClass());
    	
    	Dto main = this.parseBeforeUpdate(updateNode);
        
    	DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,main, dto.getOid(), this.getDtoClass());

        String gDataEndPointUrl = gdata+Constant.GDATA_APPEND+Constant.VERSION_1+Constant.GDATA_LIST_NODE_REQUEST;

        return dataServiceRestTemplateClient.update(nodePath, request, gDataEndPointUrl);
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
    public Entity update(Dto node,String employeeOid) {

        Entity entity = null;

        if(this.getType().equalsIgnoreCase("Node")) {

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String element = gson.toJson(node);
            HashMap<String, LinkedTreeMap> gsonMap = gson.fromJson(element, HashMap.class);

            LinkedTreeMap<String, Object> mainMap = gsonMap.get("node");

            String mainString = gson.toJson(mainMap);
            Dto updateNode = (Dto)gson.fromJson(mainString, this.getDtoClass());

            Dto main = this.parseBeforeUpdate(updateNode);

            DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath, main, node.getOid(),
                    null, null, null, null,
                    null, null, null, this.getDtoClass());

            String gDataEndPointUrl = gdata + Constant.GDATA_UPDATE + Constant.VERSION_1 + Constant.GDATA_NODE_REQUEST;


            entity =  convertToEntity(dataServiceRestTemplateClient.update(nodePath, request, gDataEndPointUrl));

        }else if(this.getType().equalsIgnoreCase("List")){

            DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,node,employeeOid,
                    node.getOid(),null,null,null,
                    null,null,null,this.getDtoClass());

            String gDataEndPointUrl = gdata + Constant.GDATA_UPDATE + Constant.VERSION_1 + Constant.GDATA_LIST_NODE_REQUEST;

            entity = convertToEntity(dataServiceRestTemplateClient.updateInList(this.nodePath, request, gDataEndPointUrl));
            //return convertToEntity(dataServiceRestTemplateClient.updateInList(this.nodePath, request, gDataEndPointUrl));
        }

        return entity;
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
        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null, employeeOid,
                null, null, null, null,
                null, null, null, this.getDtoClass());

        String gDataEndPointUrl = gdata+Constant.GDATA_GET+Constant.VERSION_1+Constant.GDATA_NODE;
        log.debug("==== gDataEndPointUrl ==== "+gDataEndPointUrl);

        return dataServiceRestTemplateClient.getRestTemplateResponse(nodePath, request, gDataEndPointUrl);

    }


    @Override
    public Dto getNodeFromList(String employeeOid, String nodeOid) {
        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null, employeeOid,
                nodeOid, null, null, null,
                null, null, null, this.getDtoClass());

        String gDataEndPointUrl = gdata+Constant.GDATA_GET+Constant.VERSION_1+Constant.GDATA_LIST_NODE;
        log.debug("==== gDataEndPointUrl ==== " + gDataEndPointUrl);

        return dataServiceRestTemplateClient.getRestTemplateResponse(nodePath, request, gDataEndPointUrl);
    }

    @Override
    public Dto get(String employeeOid) {
        return null;
    }

    @Override
    public List<Dto> getSelected(List<String> oids) {
        return null;
    }

    @Override
    public List<Dto> getList(String employeeOid) {


        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null, employeeOid,
                null, null, null, null,
                null, null, null, this.getDtoClass());

        String gDataEndPointUrl = gdata+Constant.GDATA_GET+Constant.VERSION_1+Constant.GDATA_NODE;
        log.debug("==== gDataEndPointUrl ==== "+gDataEndPointUrl);

        return dataServiceRestTemplateClient.getRestTemplateResponseList(nodePath, request, gDataEndPointUrl);

    }

    @Override
    public List<Dto> getList(Dto dto) {

        return null;
    }

    @Override
    public Dto convertToDto(Entity entity) {

        return modelMapper.map(entity, getDtoClass());
    }

    @Override
    public Entity convertToEntity(Dto dto) {

        return modelMapper.map(dto, getEntityClass());
    }

    public Dto parseBeforeUpdate(Dto dto) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String element = gson.toJson(dto);

        LinkedTreeMap<String, Object> mainMap = gson.fromJson(element, LinkedTreeMap.class);

        String mainString = gson.toJson(mainMap);
        Dto main = (Dto)gson.fromJson(mainString, this.getDtoClass());

        try {
            for (Field field : main.getClass().getDeclaredFields()) {
                field.setAccessible(true); // You might want to set modifier to public first.
                Object value = field.get(main);
                if (value == null && field.getGenericType() == String.class) {
                    mainMap.put(field.getName(), "");
                }
            }

            MasterDTO masterDTO = new MasterDTO();

            for (Field field : masterDTO.getClass().getDeclaredFields()) {
                field.setAccessible(true); // You might want to set modifier to public first.
                Object value = field.get(main);
                if (value == null && field.getType() == String.class) {
                    mainMap.put(field.getName(), "");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        mainString = gson.toJson(mainMap);
        main = (Dto)gson.fromJson(mainString, this.getDtoClass());

        return main;
    }

}
