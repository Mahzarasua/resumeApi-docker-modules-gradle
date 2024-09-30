package dev.mhzars.projects.mongo.resumeapidockercompose.controller;


import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationResponse;
import dev.mhzars.projects.mongo.resumeapidockercompose.service.EducationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.RESUME_ID;
import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class EducationControllerTest {
    private static EducationController controller;

    @BeforeEach
    void init() {
        EducationResponse response = manufacturedPojo(EducationResponse.class);
        GenericDeleteResponse deleteResponse = manufacturedPojo(GenericDeleteResponse.class);
        EducationService service = Mockito.mock(EducationService.class);

        Mockito.doReturn(response)
                .when(service).getListbyResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(response)
                .when(service).saveList(ArgumentMatchers.any());
        Mockito.doReturn(deleteResponse)
                .when(service).deleteRecordbyId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        Mockito.when(service.deleteRecordsbyResumeId(ArgumentMatchers.anyString())).thenReturn(deleteResponse);

        controller = new EducationController(service);
    }

    @Test
    void givenValidResumeId_whenGetListbyResumeId_thenSuccess() {
        EducationResponse response = controller.getListbyResumeId(RESUME_ID);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidRequest_whenCreateList_thenSuccess() {
        EducationRequest request = manufacturedPojo(EducationRequest.class);
        EducationResponse response = controller.saveList(request);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidResumeId_whenDeleteList_thenSuccess() {
        GenericDeleteResponse response = controller.deleteRecords(RESUME_ID, null);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidResumeIdandId_whenDeleteList_thenSuccess() {
        GenericDeleteResponse response = controller.deleteRecords(RESUME_ID, "null");
        log.info("Response: {}", response);
        assertNotNull(response);
    }

}