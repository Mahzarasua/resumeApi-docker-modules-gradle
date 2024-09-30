package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class AuthUserTest {

    private static AuthUser r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(AuthUser.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        AuthUser tmp = manufacturedPojo(AuthUser.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void test_Constructor() {
        AuthRole tmpRole = manufacturedPojo(AuthRole.class);
        AuthUser tmp = new AuthUser("username", "passowrd", true, LocalDateTime.now(), Collections.singletonList(tmpRole));
        assertNotNull(tmp);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

}