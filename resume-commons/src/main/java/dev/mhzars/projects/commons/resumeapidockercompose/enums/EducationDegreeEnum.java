package dev.mhzars.projects.commons.resumeapidockercompose.enums;

import lombok.Getter;

@Getter
public enum EducationDegreeEnum {
    BACHELOR("Bachelor"),
    MASTER("Master"),
    PH("Ph");

    final String degree;

    EducationDegreeEnum(String degree) {
        this.degree = degree;
    }
}
