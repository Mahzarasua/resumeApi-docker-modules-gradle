package dev.mhzars.projects.commons.resumeapidockercompose.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonSkill {

    private String name;
    private int percentage;
    private String type;
    private LocalDateTime creationDate;
}
