package dev.mhzars.projects.commons.resumeapidockercompose.domain.resume;


import dev.mhzars.projects.commons.resumeapidockercompose.podam.EmailStrategy;
import dev.mhzars.projects.commons.resumeapidockercompose.podam.GenerateUUIDStrategy;
import dev.mhzars.projects.commons.resumeapidockercompose.podam.PhoneStrategy;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jemos.podam.common.PodamStrategyValue;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ResumeRequest")
public class CommonResumeRequest {
    @PodamStrategyValue(GenerateUUIDStrategy.class)
    private String id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String title;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String country;
    @NotBlank
    @PodamStrategyValue(EmailStrategy.class)
    @Email(regexp = "^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$")
    private String email;
    @NotBlank
    @PodamStrategyValue(PhoneStrategy.class)
    private String phone;
    @NotBlank
    private String summary;
    private LocalDateTime creationDate;
}
