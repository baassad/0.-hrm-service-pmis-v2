package com.cokreates.grp.beans.qualification.language;

import java.util.Date;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LanguageDTO extends MasterDTO {

    private String languageName, isNativeLanguage, readingSkill, writingSkill, speakingSkill, listeningSkill;
    
    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
