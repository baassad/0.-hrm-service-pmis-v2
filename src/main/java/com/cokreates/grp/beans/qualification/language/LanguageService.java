package com.cokreates.grp.beans.qualification.language;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;

@Service
public class LanguageService extends MasterService<LanguageDTO, Language> {
    public LanguageService(RequestBuildingComponent<LanguageDTO> requestBuildingComponent,
                           DataServiceRestTemplateClient< LanguageDTO, Language> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification", "language"));
    }
    
    @Override
    public Class getDtoClass() {
        return LanguageDTO.class;
    }
}
