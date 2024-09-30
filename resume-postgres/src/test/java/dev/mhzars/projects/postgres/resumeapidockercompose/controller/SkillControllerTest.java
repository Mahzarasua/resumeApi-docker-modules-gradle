package dev.mhzars.projects.postgres.resumeapidockercompose.controller;


import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillResponse;
import dev.mhzars.projects.postgres.resumeapidockercompose.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.RESUME_ID;
import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class SkillControllerTest {
    private static SkillController controller;

    @BeforeEach
    void init() {
        SkillResponse response = manufacturedPojo(SkillResponse.class);
        GenericDeleteResponse deleteResponse = manufacturedPojo(GenericDeleteResponse.class);
        SkillService service = Mockito.mock(SkillService.class);

        Mockito.doReturn(response)
                .when(service).getListbyResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(response)
                .when(service).saveList(ArgumentMatchers.any());
        Mockito.doReturn(deleteResponse)
                .when(service).deleteRecordbyId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        Mockito.when(service.deleteRecordsbyResumeId(ArgumentMatchers.anyString())).thenReturn(deleteResponse);

        controller = new SkillController(service);
    }

    @Test
    void givenValidResumeId_whenGetListbyResumeId_thenSuccess() {
        SkillResponse response = controller.getListbyResumeId(RESUME_ID);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void givenValidRequest_whenCreateList_thenSuccess() {
        SkillRequest request = manufacturedPojo(SkillRequest.class);
        SkillResponse response = controller.saveList(request);
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