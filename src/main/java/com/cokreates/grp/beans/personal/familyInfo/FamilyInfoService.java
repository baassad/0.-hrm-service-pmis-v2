package com.cokreates.grp.beans.personal.familyInfo;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FamilyInfoService extends MasterService<FamilyInfoDTO,FamilyInfo> {
     public FamilyInfoService(RequestBuildingComponent<FamilyInfoDTO> requestBuildingComponent,
                              DataServiceRestTemplateClient< FamilyInfoDTO, FamilyInfo> dataServiceRestTemplateClient){
          super(requestBuildingComponent, dataServiceRestTemplateClient);
          this.setNodePath(Arrays.asList("personal", "familyInfo"));
     }
     
     @Override
     public Class getDtoClass() {
         return FamilyInfoDTO.class;
     }

}
