package dev.mhzars.projects.commons.resumeapidockercompose.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomNotFoundExceptionTest {

    @Test
    void test_Constructor() {
        assertThrows(CustomNotFoundException.class, () -> {
                    throw new CustomNotFoundException("string");
                }
        );
    }

}