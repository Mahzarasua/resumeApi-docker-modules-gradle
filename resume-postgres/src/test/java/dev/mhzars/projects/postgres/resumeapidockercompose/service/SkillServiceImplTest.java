package dev.mhzars.projects.postgres.resumeapidockercompose.service;


import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.postgres.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Skill;
import dev.mhzars.projects.postgres.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringResumeRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.RESUME_ID;
import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class SkillServiceImplTest {
    private static SkillService service;
    private static Resume resume;

    @BeforeAll
    static void start() {
        resume = manufacturedPojo(Resume.class);
    }

    @BeforeEach
    void init() {
        List<Skill> entityList = new ArrayList<>();
        List<SkillDomain> domainList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Skill e = manufacturedCustomPojo(Skill.class);
            entityList.add(e);
            SkillDomain d = manufacturedCustomPojo(SkillDomain.class);
            domainList.add(d);
        }
        resume.setSkillList(entityList);


        Resume resume1 = manufacturedPojo(Resume.class);
        resume1.getSkillList().clear();

        ResumeRepository repository = Mockito.mock(ResumeRepository.class);
        SpringResumeRepo checkResume = Mockito.mock(SpringResumeRepo.class);
        CustomMapper mapper = Mockito.mock(CustomMapper.class);

        Mockito.doReturn(resume)
                .when(checkResume).checkResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(resume1)
                .when(checkResume).checkResumeId(RESUME_ID);
        Mockito.doReturn(resume)
                .when(repository).save(ArgumentMatchers.any());

        Mockito.when(mapper.mapAsList(ArgumentMatchers.eq(entityList), ArgumentMatchers.eq(SkillDomain.class)))
                .thenReturn(domainList);

        service = new SkillServiceImpl(repository, mapper, checkResume);
    }

    @Test
    void getListbyResumeId() {
        SkillResponse response = service.getListbyResumeId("RESUME_ID");
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void saveList() {
        SkillRequest request = manufacturedPojo(SkillRequest.class);
        request.getSkillList().forEach(e -> e.setId(generateUniqueObjectId().toString()));
        SkillResponse response = service.saveList(request);
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
        List<Skill> SkillList = resume.getSkillList();
        String id = SkillList.get(0).getId().toString();
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