package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class ResumeTest {
    private static Resume r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(Resume.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        Resume tmp = manufacturedPojo(Resume.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void testSetCreationDate() {
        // Call the method
        r.setCreationDate();

        // Check that the creation date is set to the current time
        LocalDateTime now = LocalDateTime.now();
        assertTrue(now.isAfter(r.getCreationDate()) || now.isEqual(r.getCreationDate()));
    }

}