package dev.mhzars.projects.postgres.resumeapidockercompose.utils;

import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils;

import java.util.Collections;
import java.util.UUID;

public class SpringUtils extends CommonSpringUtils {

    private SpringUtils() {
        super();
    }

    public static UUID generateUniqueObjectId() {
        return getRandomId();
    }

    public static UUID validateObjectId(String id) {
        try {
            return UUID.fromString((id == null || id.isEmpty()) ? "" : id);
        } catch (IllegalArgumentException e) {
            ExceptionBody.ErrorDetails errorDetails =
                    new ExceptionBody.ErrorDetails("id",
                            String.format("Value provided: %s cannot be converted to UUID", id));
            throw new CustomBadRequestException(Collections.singletonList(errorDetails), "Conversion Error");
        }
    }
}
