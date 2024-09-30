package dev.mhzars.projects.commons.resumeapidockercompose.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "DeleteResponse")
public class GenericDeleteResponse {
    private String id;
    private String resumeId;

    public GenericDeleteResponse(String resumeId) {
        this.resumeId = resumeId;
    }
}
