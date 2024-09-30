package dev.mhzars.projects.commons.resumeapidockercompose.domain.experience;


import dev.mhzars.projects.commons.resumeapidockercompose.podam.GenerateUUIDStrategy;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStrategyValue;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDomain {
    @PodamStrategyValue(GenerateUUIDStrategy.class)
    private String resumeId;
    @PodamStrategyValue(GenerateUUIDStrategy.class)
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String company;
    private boolean currentJob;
    @NotBlank
    private String description;
    @Past
    private LocalDate startDate;
    @FutureOrPresent
    private LocalDate endDate;
    private LocalDateTime creationDate;
}
