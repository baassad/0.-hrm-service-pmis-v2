package com.cokreates.grp.beans.qualification.language;

import com.cokreates.core.MasterService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceClient;

public class LanguageService extends MasterService<LanguageDTO, Language> {
    public LanguageService(RequestBuildingComponent<LanguageDTO> requestBuildingComponent){
        super(requestBuildingComponent);
    }
}
