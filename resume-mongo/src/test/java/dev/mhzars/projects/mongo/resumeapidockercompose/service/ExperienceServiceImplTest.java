package dev.mhzars.projects.mongo.resumeapidockercompose.service;


import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.mongo.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Experience;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringResumeRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.RESUME_ID;
import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedPojo;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class ExperienceServiceImplTest {
    private static ExperienceService service;
    private static Resume resume;

    @BeforeAll
    static void start() {
        resume = manufacturedPojo(Resume.class);
    }

    @BeforeEach
    void init() {
        List<Experience> entityList = new ArrayList<>();
        List<ExperienceDomain> domainList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Experience e = manufacturedPojo(Experience.class);
            entityList.add(e);
            ExperienceDomain d = manufacturedCustomPojo(ExperienceDomain.class);
            domainList.add(d);
        }
        resume.setExperienceList(entityList);

        Resume resume1 = manufacturedPojo(Resume.class);
        resume1.getExperienceList().clear();

        ResumeRepository repository = Mockito.mock(ResumeRepository.class);
        SpringResumeRepo checkResume = Mockito.mock(SpringResumeRepo.class);
        CustomMapper mapper = Mockito.mock(CustomMapper.class);

        Mockito.doReturn(resume)
                .when(checkResume).checkResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(resume1)
                .when(checkResume).checkResumeId(RESUME_ID);
        Mockito.doReturn(resume)
                .when(repository).save(ArgumentMatchers.any());
        Mockito.when(mapper.mapAsList(ArgumentMatchers.eq(entityList), ArgumentMatchers.eq(ExperienceDomain.class)))
                .thenReturn(domainList);

        service = new ExperienceServiceImpl(repository, mapper, checkResume);
    }

    @Test
    void getListbyResumeId() {
        ExperienceResponse response = service.getListbyResumeId("RESUME_ID");
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void saveList() {
        ExperienceRequest request = manufacturedPojo(ExperienceRequest.class);
        request.getExperienceList().forEach(e -> e.setId(generateUniqueObjectId().toString()));
        ExperienceResponse response = service.saveList(request);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void deleteRecordsbyResumeId() {
        GenericDeleteResponse response = service.deleteRecordsbyResumeId("RESUME_ID");
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void deleteRecordbyId() {
        List<Experience> ExperienceList = resume.getExperienceList();
        String id = ExperienceList.get(0).getId().toString();
        GenericDeleteResponse response = service.deleteRecordbyId(resume.getId().toString(), id);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    //Negative
    @Test
    void getListbyResumeId_Negative() {
        assertThrows(CustomNotFoundException.class, () -> service.getListbyResumeId(RESUME_ID));
    }

    @Test
    void deleteRecordsbyResumeId_Negative() {
        assertThrows(CustomNotFoundException.class, () -> service.deleteRecordsbyResumeId(RESUME_ID));
    }

    @Test
    void deleteRecordbyId_Negative() {
        String resumeId = resume.getId().toString();
        String id = generateUniqueObjectId().toString();
        assertThrows(CustomNotFoundException.class, () -> service.deleteRecordbyId(resumeId, id));
    }
}