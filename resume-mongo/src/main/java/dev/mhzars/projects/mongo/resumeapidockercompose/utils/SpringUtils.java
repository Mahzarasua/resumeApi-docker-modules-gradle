package dev.mhzars.projects.mongo.resumeapidockercompose.utils;

import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils;
import org.bson.types.ObjectId;

import java.security.DrbgParameters;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Random;

import static java.security.DrbgParameters.Capability.RESEED_ONLY;

public class SpringUtils extends CommonSpringUtils {

    private static Random random;

    static {
        try {
            random = SecureRandom.getInstance("DRBG",
                    DrbgParameters.instantiation(128, RESEED_ONLY, null));
        } catch (NoSuchAlgorithmException e) {
            random = new SecureRandom();
        }
    }

    private SpringUtils() {
        super();
    }

    public static ObjectId generateUniqueObjectId() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int time = (int) timestamp.getTime() + random.nextInt(200);
        return new ObjectId(time, random.nextInt(100));
    }

    public static ObjectId validateObjectId(String id) {
        try {
            return new ObjectId((id == null || id.isEmpty()) ? "" : id);
        } catch (IllegalArgumentException e) {
            ExceptionBody.ErrorDetails errorDetails =
                    new ExceptionBody.ErrorDetails("id",
                            String.format("Value provided: %s cannot be converted to ObjectId", id));
            throw new CustomBadRequestException(Collections.singletonList(errorDetails), "Conversion Error");
        }
    }
}
