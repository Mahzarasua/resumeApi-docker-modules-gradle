package dev.mhzars.projects.commons.resumeapidockercompose.domain.auth;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class JwtRequestTest {

    private static JwtRequest r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(JwtRequest.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        JwtRequest tmp = manufacturedPojo(JwtRequest.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

}