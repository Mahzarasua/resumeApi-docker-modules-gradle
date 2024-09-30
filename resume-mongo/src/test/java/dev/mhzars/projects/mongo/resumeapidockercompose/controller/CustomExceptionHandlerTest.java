package dev.mhzars.projects.mongo.resumeapidockercompose.controller;


import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomAuthException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomExceptionHandlerTest {

    private CustomExceptionHandler controller;
    private ServletWebRequest webRequest;

    @BeforeEach
    void init() {
        controller = new CustomExceptionHandler();
        webRequest = new ServletWebRequest(new MockHttpServletRequest());
    }

    @Test
    void handleNotFound() {
        CustomNotFoundException exception = manufacturedCustomPojo(CustomNotFoundException.class);
        ResponseEntity<Object> objectResponseEntity = controller.exceptionResolver(exception, webRequest);
        assertNotNull(objectResponseEntity);
    }

    @Test
    void handleMissingRequiredFields() {
        CustomBadRequestException exception = manufacturedCustomPojo(CustomBadRequestException.class);
        ResponseEntity<Object> objectResponseEntity = controller.exceptionResolver(exception, webRequest);
        assertNotNull(objectResponseEntity);
    }

    @Test
    void handleAuthException() {
        CustomAuthException exception = manufacturedCustomPojo(CustomAuthException.class);
        ResponseEntity<Object> objectResponseEntity = controller.exceptionResolver(exception, webRequest);
        assertNotNull(objectResponseEntity);
    }
}