package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import dev.mhzars.projects.commons.resumeapidockercompose.enums.EducationDegreeEnum;
import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.security.SecureRandom;
import java.util.List;

public class DegreeStrategy implements AttributeStrategy<String> {

    private static final SecureRandom r = new SecureRandom();

    public static EducationDegreeEnum randomEducation() {
        EducationDegreeEnum[] enums = EducationDegreeEnum.values();
        return enums[r.nextInt(enums.length)];
    }

    @Override
    public String getValue(Class<?> aClass, List<Annotation> list) {
        return randomEducation().getDegree();
    }
}
