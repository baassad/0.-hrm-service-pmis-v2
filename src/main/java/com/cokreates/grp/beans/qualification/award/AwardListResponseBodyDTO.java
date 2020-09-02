package com.cokreates.grp.beans.qualification.award;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.qualification.publication.PublicationListResponseBodyDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AwardListResponseBodyDTO extends PublicationListResponseBodyDTO {

    private boolean hasGo;

}
