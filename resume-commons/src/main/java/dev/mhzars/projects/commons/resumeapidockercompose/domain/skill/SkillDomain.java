package dev.mhzars.projects.commons.resumeapidockercompose.domain.skill;


import dev.mhzars.projects.commons.resumeapidockercompose.podam.GenerateUUIDStrategy;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStrategyValue;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDomain {
    @PodamStrategyValue(GenerateUUIDStrategy.class)
    private String resumeId;
    @PodamStrategyValue(GenerateUUIDStrategy.class)
    private String id;
    private String name;
    @Min(0)
    @Max(99)
    private int percentage;
    private String type;
    private LocalDateTime creationDate;
}
