package dev.mhzars.projects.commons.resumeapidockercompose.config;

import dev.mhzars.projects.commons.resumeapidockercompose.validator.CommonResumeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

public class CommonConfigBeans {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommonResumeValidator getCommonResumeValidator() {
        return new CommonResumeValidator();
    }
}
