package com.cokreates.grp.beans.common;

import com.cokreates.grp.beans.qualification.award.AwardDTO;
import com.cokreates.grp.beans.qualification.education.EducationDTO;
import com.cokreates.grp.beans.qualification.language.LanguageDTO;
import com.cokreates.grp.beans.qualification.professionalQualification.ProfessionalQualificationDTO;
import com.cokreates.grp.beans.qualification.publication.PublicationDTO;

import java.util.HashMap;
import java.util.List;

public class QualificationDTO {
    List<AwardDTO> award;
    List<LanguageDTO> language;
    List<EducationDTO> education;
    List<PublicationDTO> publication;
    List<ProfessionalQualificationDTO> professionalQualification;
    HashMap<String, Object> trainingAndProfessionalCertification;
}
