package dev.mhzars.projects.postgres.resumeapidockercompose.controller;

import dev.mhzars.projects.commons.resumeapidockercompose.config.CommonJwtTokenUtil;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.auth.JwtRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.auth.JwtResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.postgres.resumeapidockercompose.config.CustomAuthenticationManager;
import dev.mhzars.projects.postgres.resumeapidockercompose.validator.JwtRequestValidator;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication")
@CrossOrigin
@Validated
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema())}),
        @ApiResponse(responseCode = "400", description = "Bad Request",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ExceptionBody.class))}),
        @ApiResponse(responseCode = "404", description = "Not Found",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ExceptionBody.class))}),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ExceptionBody.class))})
})
//@Hidden
public class JwtAuthenticationController {

    private final CustomAuthenticationManager authenticationManager;

    private final CommonJwtTokenUtil jwtTokenUtil;

    private final JwtRequestValidator validator;

    public JwtAuthenticationController(CustomAuthenticationManager authenticationManager, CommonJwtTokenUtil jwtTokenUtil, JwtRequestValidator validator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.validator = validator;
    }

    @PostMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authRequest) {
        validator.validate(authRequest);
        return new JwtResponse(
                jwtTokenUtil.generateToken(authenticationManager.authentication(authRequest.getUsername(), authRequest.getPassword()))
        );
    }

}
