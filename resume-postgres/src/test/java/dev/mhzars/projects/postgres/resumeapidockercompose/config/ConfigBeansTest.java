package dev.mhzars.projects.postgres.resumeapidockercompose.config;

import dev.mhzars.projects.commons.resumeapidockercompose.validator.CommonResumeValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ConfigBeansTest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CommonResumeValidator commonResumeValidator;

    @Test
    public void testRestTemplateBean() {
        assertNotNull(restTemplate);
    }

    @Test
    public void testPasswordEncoderBean() {
        assertNotNull(passwordEncoder);
    }

    @Test
    public void testCommonResumeValidatorBean() {
        assertNotNull(commonResumeValidator);
    }
}