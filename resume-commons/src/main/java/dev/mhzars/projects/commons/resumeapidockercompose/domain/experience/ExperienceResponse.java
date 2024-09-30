package dev.mhzars.projects.commons.resumeapidockercompose.domain.experience;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienceResponse {
    @JsonIgnoreProperties(value = "resumeId")
    private List<ExperienceDomain> experienceList;
}
