package dev.mhzars.projects.postgres.resumeapidockercompose;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = ResumePostgres.class)
class ResumePostgresTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

}
