package dev.mhzars.projects.commons.resumeapidockercompose.domain.resume;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ResumeIdResponse")
public class ResumeIdResponse {
    private String resumeId;
}
