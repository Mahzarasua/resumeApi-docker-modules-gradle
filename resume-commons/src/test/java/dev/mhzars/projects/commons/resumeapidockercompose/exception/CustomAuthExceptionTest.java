package dev.mhzars.projects.commons.resumeapidockercompose.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class CustomAuthExceptionTest {
    private static CustomAuthException r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(CustomAuthException.class);
        log.info("{}", r.toString());
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        CustomAuthException tmp = manufacturedPojo(CustomAuthException.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp.toString());
    }

    @Test
    void test_Constructor() {
        ExceptionBody.ErrorDetails errorDetails = manufacturedPojo(ExceptionBody.ErrorDetails.class);
        assertThrows(CustomAuthException.class, () -> {
                    throw new CustomAuthException(errorDetails, "string");
                }
        );
    }
}