package dev.mhzars.projects.commons.resumeapidockercompose.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class GenericDeleteResponseTest {

    private static GenericDeleteResponse r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(GenericDeleteResponse.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        GenericDeleteResponse tmp = manufacturedPojo(GenericDeleteResponse.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void test_Constructor() {
        GenericDeleteResponse tmp = new GenericDeleteResponse("resumeId");
        assertNotNull(tmp);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

}