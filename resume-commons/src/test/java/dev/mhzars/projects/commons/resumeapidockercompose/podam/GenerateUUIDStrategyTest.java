package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenerateUUIDStrategyTest {
    private static GenerateUUIDStrategy strategy;

    @BeforeEach
    void init() {
        strategy = new GenerateUUIDStrategy();
    }

    @Test
    void testStrategy() {
        String actual = strategy.getValue(Object.class, Collections.emptyList());
        assertNotNull(actual);
    }

}