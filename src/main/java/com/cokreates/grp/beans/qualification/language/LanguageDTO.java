package com.cokreates.grp.beans.qualification.language;

import com.cokreates.core.MasterDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LanguageDTO extends MasterDTO {

    private String languageName, isNativeLanguage, readingSkill, writingSkill, speakingSkill, listeningSkill;
    
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private String createdOn;
    private String updatedOn;
    private String dataStatus;
}
