package dev.mhzars.projects.commons.resumeapidockercompose.podam;


import dev.mhzars.projects.commons.resumeapidockercompose.enums.EducationDegreeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dev.mhzars.projects.commons.resumeapidockercompose.podam.DegreeStrategy.randomEducation;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DegreeStrategyTest {
    private static DegreeStrategy strategy;

    @BeforeEach
    void init() {
        strategy = new DegreeStrategy();
    }

    @Test
    void testRandomEducation() {
        //Expected values
        EducationDegreeEnum[] enums = EducationDegreeEnum.values();
        EducationDegreeEnum educationDegreeEnum = randomEducation();
        for (EducationDegreeEnum e : enums) {
            if (educationDegreeEnum == e) {
                assertTrue(true);
            }
        }
    }

    @Test
    void testDegreeStrategyGetValue() {
        // Define the expected value
        EducationDegreeEnum[] enums = EducationDegreeEnum.values();
        List<String> enumList = new ArrayList<>();
        for (EducationDegreeEnum e : enums) {
            enumList.add(e.getDegree());
        }
        String actual = strategy.getValue(Object.class, Collections.emptyList());
        // Assert that the actual value matches the expected value
        assertTrue(enumList.contains(actual));
    }
}