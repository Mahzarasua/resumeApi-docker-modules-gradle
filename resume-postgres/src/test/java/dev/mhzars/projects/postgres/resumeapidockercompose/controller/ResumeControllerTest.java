package dev.mhzars.projects.postgres.resumeapidockercompose.controller;


import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeIdResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeResponse;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume.ResumeRequest;
import dev.mhzars.projects.postgres.resumeapidockercompose.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.RESUME_ID;
import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class ResumeControllerTest {

    private static ResumeController controller;

    @BeforeEach
    void init() {
        ResumeResponse response = manufacturedPojo(ResumeResponse.class);
        List<ResumeResponse> responseList = Collections.singletonList(response);
        ResumeIdResponse resumeIdResponse = manufacturedPojo(ResumeIdResponse.class);
        ResumeService service = Mockito.mock(ResumeService.class);

        Mockito.doReturn(responseList)
                .when(service).getAllResumes();
        Mockito.doReturn(response)
                .when(service).getResumeById(ArgumentMatchers.anyString());
        Mockito.when(service.saveResume(ArgumentMatchers.any())).thenReturn(resumeIdResponse);
        Mockito.doReturn(resumeIdResponse)
                .when(service).saveResume(ArgumentMatchers.any(), ArgumentMatchers.anyString());
        Mockito.doReturn(resumeIdResponse)
                .when(service).deleteResumeById(ArgumentMatchers.anyString());
        Mockito.doReturn(response)
                .when(service).getResumeByFirstName(ArgumentMatchers.anyString());

        controller = new ResumeController(service);
    }

    @Test
    void whenGetResumes_thenSuccess() {
        List<ResumeResponse> response = controller.getResumes();
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidResumeId_whenGetResumeById_thenSuccess() {
        ResumeResponse response = controller.getResumeById(RESUME_ID);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidRequest_whenCreateResume_thenSuccess() {
        ResumeRequest request = manufacturedPojo(ResumeRequest.class);
        ResumeIdResponse response = controller.createResume(request);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidRequest_whenUpdateResume_thenSuccess() {
        ResumeRequest request = manufacturedPojo(ResumeRequest.class);
        ResumeIdResponse response = controller.updateResume(RESUME_ID, request);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidRequest_whenDeleteResumeById_thenSuccess() {
        ResumeIdResponse response = controller.deleteResumeById(RESUME_ID);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidString_whenGetResumeByFirstName_thenSuccess() {
        ResumeResponse response = controller.getResumeByFirstName(RESUME_ID);
        log.info("Response: {}", response);
        assertNotNull(response);
    }


}