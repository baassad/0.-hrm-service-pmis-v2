package com.cokreates.grp.beans.qualification.language;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LanguageService extends MasterService<LanguageDTO, Language> {
    public LanguageService(RequestBuildingComponent<LanguageDTO> requestBuildingComponent,
                           DataServiceRestTemplateClient< LanguageDTO, Language> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification", "language"));
        this.setType("List");
    }
    
    @Override
    public Class getDtoClass() {
        return LanguageDTO.class;
    }

    @Override
    public Class getEntityClass() {return Language.class;}
}
