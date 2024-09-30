package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedCustomPojo;
import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class AuthRoleTest {
    private static AuthRole r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(AuthRole.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        AuthRole tmp = manufacturedPojo(AuthRole.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void test_Constructor() {
        AuthRole tmp = new AuthRole("role", LocalDateTime.now());
        assertNotNull(tmp);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void testPrepareRemoval() {
        AuthUser tmp = manufacturedCustomPojo(AuthUser.class);
        List<AuthRole> list = tmp.getAuthRoles();
        int originalCount = list.size();

        list.get(0).setUser(tmp);
        list.get(0).preRemoveRole();

        assertEquals(originalCount - 1, list.size());
    }


}