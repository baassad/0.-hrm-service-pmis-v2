package com.cokreates.core;

import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceRequestBody;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

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

    public Class<Dto> getDtoClass() {return null;}

    public Class<Entity> getEntityClass() {return null;}

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
        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestToCreateEmployee(dto);

        String gDataEndPointUrl = gdata + Constant.GDATA_CREATE + Constant.VERSION_1 + Constant.ENDPOINT_EMPLOYEE;

        return dataServiceRestTemplateClient.getRestTemplateResponseForCreation(request,gDataEndPointUrl);
    }

    @Override
    public Dto append(Dto dto) {
    	
    	Dto main = this.parseBeforeUpdate(dto);
        
    	DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,main, dto.getOid(), this.getDtoClass());

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
    public Entity update(Dto node,String employeeOid) {

        if(this.getType().equalsIgnoreCase("Node")) {
            Dto main = this.parseBeforeUpdate(node);

            DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath, main, node.getOid(),
                    null, null, null, null,
                    null, null, null, this.getDtoClass());

            String gDataEndPointUrl = gdata + Constant.GDATA_UPDATE + Constant.VERSION_1;


            return convertToEntity(dataServiceRestTemplateClient.updateSingleObject(nodePath, request, gDataEndPointUrl));

        }else if(this.getType().equalsIgnoreCase("List")){
            DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,node,employeeOid,
                    null,null,null,null,null,null,null,this.getDtoClass());

            String gDataEndPointUrl = gdata + Constant.GDATA_UPDATE + Constant.VERSION_1 + Constant.GDATA_LIST_NODE_REQUEST;


            dataServiceRestTemplateClient.updateInList(this.nodePath, request, gDataEndPointUrl);
        }

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
        log.debug("==== gDataEndPointUrl ==== "+gDataEndPointUrl);

        return dataServiceRestTemplateClient.getRestTemplateResponse(nodePath, request, gDataEndPointUrl);
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

//        String gDataEndPointUrl = gdata+Constant.GDATA_UPDATE+Constant.VERSION_1;
//
//        DataServiceRequest<Dto> request = requestBuildingComponent.getRequestForRead(nodePath,null, dto.getOid(), null, this.getDtoClass());
//
//        return dataServiceRestTemplateClient.getList(nodePath, request, gDataEndPointUrl);
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
        Gson gson = new Gson();
        String element = gson.toJson(dto);
        HashMap<String, LinkedTreeMap> gsonMap = gson.fromJson(element, HashMap.class);

        LinkedTreeMap<String, Object> mainMap = gsonMap.get("node");

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

    public DataServiceRequestBody parseBeforeApprovalUpdate(DataServiceRequestBody dto) {
        Gson gson = new Gson();

        String mainString = gson.toJson(dto);
        LinkedTreeMap<String, Object> gsonMap = gson.fromJson(mainString, LinkedTreeMap.class);

        DataServiceRequestBody main = (DataServiceRequestBody)gson.fromJson(mainString, DataServiceRequestBody.class);

        try {
            for (Field field : main.getClass().getDeclaredFields()) {
                field.setAccessible(true); // You might want to set modifier to public first.
                Object value = field.get(main);
                if (value == null&& field.getType() == String.class) {
                    gsonMap.put(field.getName(), "");
                };
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        mainString = gson.toJson(gsonMap);
        main = (DataServiceRequestBody)gson.fromJson(mainString, DataServiceRequestBody.class);

        return main;
    }

}
