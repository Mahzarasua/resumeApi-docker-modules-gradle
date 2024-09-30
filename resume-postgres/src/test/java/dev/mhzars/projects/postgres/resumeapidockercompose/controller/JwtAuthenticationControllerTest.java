package dev.mhzars.projects.postgres.resumeapidockercompose.controller;

import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonJwtTokenUtil;
import dev.mhzars.projects.commons.resumeapidockercompose.config.MyUserDetails;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.auth.JwtRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.auth.JwtResponse;
import dev.mhzars.projects.postgres.resumeapidockercompose.config.CustomAuthenticationManager;
import dev.mhzars.projects.postgres.resumeapidockercompose.validator.JwtRequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class JwtAuthenticationControllerTest {
    private static JwtAuthenticationController controller;

    @BeforeEach
    void init() {
        CustomAuthenticationManager authenticationManager = Mockito.mock(CustomAuthenticationManager.class);
        CommonJwtTokenUtil jwtTokenUtil = Mockito.mock(CommonJwtTokenUtil.class);
        JwtRequestValidator validator = Mockito.mock(JwtRequestValidator.class);

        MyUserDetails userDetails = manufacturedPojo(MyUserDetails.class);

        Mockito.doNothing()
                .when(validator).validate(ArgumentMatchers.any());
        Mockito.doReturn(userDetails)
                .when(authenticationManager).authentication(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        Mockito.doReturn("jwtToken")
                .when(jwtTokenUtil).generateToken(ArgumentMatchers.any());

        controller = new JwtAuthenticationController(authenticationManager, jwtTokenUtil, validator);
    }

    @Test
    void givenValidRequest_whenCreateAuthenticationToken_thenSuccess() {
        JwtRequest request = manufacturedPojo(JwtRequest.class);
        JwtResponse response = controller.createAuthenticationToken(request);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

}