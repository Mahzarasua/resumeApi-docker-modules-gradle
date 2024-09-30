package dev.mhzars.projects.mongo.resumeapidockercompose;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = ResumeMongo.class)
class ResumeMongoTest {
    @Test
    void contextLoads() {
        assertTrue(true);
    }
}