package com.cokreates.grp.beans.qualification.language;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LanguageDTO extends MasterDTO {

    private String languageName, isNativeLanguage, readingSkill, writingSkill, speakingSkill, listeningSkill;

}
