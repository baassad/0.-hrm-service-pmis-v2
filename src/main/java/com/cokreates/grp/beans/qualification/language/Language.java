package com.cokreates.grp.beans.qualification.language;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Language extends BaseEntity {

    private String languageName, isNativeLanguage, readingSkill, writingSkill, speakingSkill, listeningSkill;
}
