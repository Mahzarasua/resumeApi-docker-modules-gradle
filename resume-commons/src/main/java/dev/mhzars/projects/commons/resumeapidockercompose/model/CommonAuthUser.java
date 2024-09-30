package dev.mhzars.projects.commons.resumeapidockercompose.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@JsonIgnoreProperties
public class CommonAuthUser implements Serializable {
    @Serial
    private static final long serialVersionUID = -220636052751713755L;
    private String username;
    private String password;
    private boolean active;
    private LocalDateTime creationDate;
    private List<CommonAuthRole> authRoles;
}
