package dev.mhzars.projects.mongo.resumeapidockercompose.utils;

import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.validateObjectId;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpringUtilsTest {

    @Test
    void test() {
        ObjectId id = generateUniqueObjectId();
        assertNotNull(id);
        assertNotNull(validateObjectId(id.toString()));
    }

    @Test
    void testException() {
        assertThrows(CustomBadRequestException.class, () -> validateObjectId(null));
        assertThrows(CustomBadRequestException.class, () -> validateObjectId(""));
        assertThrows(CustomBadRequestException.class, () -> validateObjectId("aaa"));
    }
}