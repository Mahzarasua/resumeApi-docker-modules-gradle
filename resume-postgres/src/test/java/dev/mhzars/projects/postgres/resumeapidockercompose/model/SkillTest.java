package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class SkillTest {
    private static Skill r;

    @BeforeAll
    static void init() {
        log.info("Starting init");
        r = manufacturedPojo(Skill.class);
        log.info("{}", r);
        log.info("Ending init");
    }

    @Test
    void test_ObjectIsNotNull() {
        assertNotNull(r);
        Skill tmp = manufacturedPojo(Skill.class);
        assertThat(r).usingRecursiveComparison().isNotEqualTo(tmp);
        log.info("{}", tmp);
    }

    @Test
    void testSetCreationDate() {
        // Call the method
        r.setCreationDate();

        // Check that the creation date is set to the current time
        LocalDateTime now = LocalDateTime.now();
        assertEquals(now, r.getCreationDate());
    }

    @Test
    void testPreRemoval() {
        Resume resume = manufacturedCustomPojo(Resume.class);
        List<Skill> list = resume.getSkillList();
        int originalCount = list.size();

        list.get(0).setResume(resume);
        list.get(0).preRemoveSkill();

        assertEquals(originalCount - 1, list.size());
    }
}