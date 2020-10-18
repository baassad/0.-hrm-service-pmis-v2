package com.cokreates.grp.beans.qualification.training;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.pim.pmis.PmisRepository;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceRequestBody;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

@Service
public class TrainingService extends MasterService<TrainingDTO, Training> {

    @Autowired
    PmisRepository pmisRepository;

    public TrainingService(RequestBuildingComponent<TrainingDTO> requestBuildingComponent,
                           DataServiceRestTemplateClient< TrainingDTO, Training> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification","trainingAndProfessionalCertification", "training"));
        this.setType("List");
    }

    @Override
    public TrainingDTO appendApprovedNode(TrainingDTO dto,String employeeOid){

        dto = super.validateTheDto(dto);

        String gDataEndPointUrl;

        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });

        Gson gson = builder.create();
        String element = gson.toJson(dto);
        HashMap<String, LinkedTreeMap> gsonMap = gson.fromJson(element, HashMap.class);

        LinkedTreeMap<String, Object> mainMap = gsonMap.get("node");

        String mainString = gson.toJson(mainMap);
        System.out.println(mainString + "Main string");


        TrainingDTO updateNode = (TrainingDTO) gson.fromJson(mainString, this.getDtoClass());

        TrainingDTO main = this.parseBeforeUpdate(updateNode);

        Set<String> employeeTrainingOids = pmisRepository.getTrainingOids(employeeOid);

        boolean previousTraining = false;

        for (String trainingOid : employeeTrainingOids){
            if(trainingOid.equals(main.getOid())){
                previousTraining = true;
                break;
            }
        }

        DataServiceRequest<TrainingDTO> request = this.getRequestBuildingComponent().getRequestForRead(this.getNodePath(), main, employeeOid, this.getDtoClass());
        DataServiceRequestBody dataServiceRequestBody = request.getBody();
        request.setBody(dataServiceRequestBody);

        if(!previousTraining) {
            gDataEndPointUrl = this.getGData() + Constant.GDATA_APPEND + Constant.VERSION_1 + Constant.GDATA_LIST_APPROVED_NODE;
        }else {
            gDataEndPointUrl = this.getGData() + Constant.GDATA_UPDATE + Constant.VERSION_1 + Constant.GDATA_LIST_APPROVED_NODE_FOR_UPDATE;
        }
        return this.getDataServiceRestTemplateClient().update(this.getNodePath(), request, gDataEndPointUrl);


    }



    @Override
    public Class getDtoClass() {
        return TrainingDTO.class;
    }

    @Override
    public Class getEntityClass() {return Training.class;}
}
