package com.cokreates.grp.beans.common;

import com.cokreates.grp.beans.qualification.award.AwardDTO;
import com.cokreates.grp.beans.qualification.education.EducationDTO;
import com.cokreates.grp.beans.qualification.language.LanguageDTO;
import com.cokreates.grp.beans.qualification.professionalQualification.ProfessionalQualificationDTO;
import com.cokreates.grp.beans.qualification.publication.PublicationDTO;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class QualificationDTO {
    List<EducationDTO> education;
    List<LanguageDTO> language;
    List<AwardDTO> award;
    List<PublicationDTO> publication;
    List<ProfessionalQualificationDTO> professionalQualification;
    HashMap<String, Object> trainingAndProfessionalCertification;
}
