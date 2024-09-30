package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalDatePastStrategyTest {
    private static LocalDatePastStrategy strategy;

    @BeforeEach
    void init() {
        strategy = new LocalDatePastStrategy();
    }

    @Test
    void test() {
        LocalDate expectedDate = LocalDate.of(2023, 12, 25);
        LocalDate value = strategy.getValue(Object.class, Collections.emptyList());
        assertEquals(value, expectedDate);
    }

}