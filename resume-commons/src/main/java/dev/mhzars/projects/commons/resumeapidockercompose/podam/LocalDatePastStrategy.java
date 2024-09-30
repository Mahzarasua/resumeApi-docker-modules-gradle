package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.util.List;

public class LocalDatePastStrategy implements AttributeStrategy<LocalDate> {
    @Override
    public LocalDate getValue(Class<?> aClass, List<Annotation> list) {
        return LocalDate.of(2023, 12, 25);
    }
}
