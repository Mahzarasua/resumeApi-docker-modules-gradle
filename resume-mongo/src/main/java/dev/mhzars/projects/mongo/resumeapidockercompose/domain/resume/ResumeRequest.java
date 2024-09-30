package dev.mhzars.projects.mongo.resumeapidockercompose.domain.resume;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.CommonResumeRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ResumeRequest")
public class ResumeRequest extends CommonResumeRequest {

    @JsonIgnoreProperties(value = "resumeId")
    private List<SkillDomain> skillList;
    @JsonIgnoreProperties(value = "resumeId")
    private List<EducationDomain> educationList;
    @JsonIgnoreProperties(value = "resumeId")
    private List<ExperienceDomain> experienceList;
}
