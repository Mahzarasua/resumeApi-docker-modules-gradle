package dev.mhzars.projects.mongo.resumeapidockercompose.controller;


import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceResponse;
import dev.mhzars.projects.mongo.resumeapidockercompose.service.ExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.RESUME_ID;
import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class ExperienceControllerTest {
    private static ExperienceController controller;

    @BeforeEach
    void init() {
        ExperienceResponse response = manufacturedPojo(ExperienceResponse.class);
        GenericDeleteResponse deleteResponse = manufacturedPojo(GenericDeleteResponse.class);
        ExperienceService service = Mockito.mock(ExperienceService.class);

        Mockito.doReturn(response)
                .when(service).getListbyResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(response)
                .when(service).saveList(ArgumentMatchers.any());
        Mockito.doReturn(deleteResponse)
                .when(service).deleteRecordbyId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        Mockito.when(service.deleteRecordsbyResumeId(ArgumentMatchers.anyString())).thenReturn(deleteResponse);

        controller = new ExperienceController(service);
    }

    @Test
    void givenValidResumeId_whenGetListbyResumeId_thenSuccess() {
        ExperienceResponse response = controller.getListbyResumeId(RESUME_ID);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidRequest_whenCreateList_thenSuccess() {
        ExperienceRequest request = manufacturedPojo(ExperienceRequest.class);
        ExperienceResponse response = controller.saveList(request);
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