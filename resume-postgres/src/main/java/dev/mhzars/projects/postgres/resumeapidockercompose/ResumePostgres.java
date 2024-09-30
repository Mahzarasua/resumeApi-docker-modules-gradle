package dev.mhzars.projects.postgres.resumeapidockercompose;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "jwtAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP)
public class ResumePostgres {

    public static void main(String[] args) {
        SpringApplication.run(ResumePostgres.class, args);
    }

}
