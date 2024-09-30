package dev.mhzars.projects.mongo.resumeapidockercompose.utils;


import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class SpringResumeRepoTest {
    private final ObjectId objectId = generateUniqueObjectId();
    private SpringResumeRepo springResumeRepo;

    @BeforeEach
    void init() {
        ResumeRepository resumeRepo = Mockito.mock(ResumeRepository.class);
        Resume resume = manufacturedCustomPojo(Resume.class);
        Optional<Resume> optionalResume = Optional.ofNullable(resume);
        Optional<Resume> emptyResume = Optional.empty();

        Mockito.doReturn(optionalResume)
                .when(resumeRepo).findById(ArgumentMatchers.any(ObjectId.class));
        Mockito.doReturn(emptyResume)
                .when(resumeRepo).findById(objectId);

        springResumeRepo = new SpringResumeRepo(resumeRepo);
    }

    @Test
    void checkResumeId() {
        springResumeRepo.checkResumeId(generateUniqueObjectId().toString());
    }

    @Test
    void checkResumeId_throwsException() {
        String objectIdString = objectId.toString();
        Exception e = assertThrows(CustomNotFoundException.class, () -> springResumeRepo.checkResumeId(objectIdString));

        assertTrue(e.getMessage().contains("Resume with id"));
        log.info("{}", e.getMessage());
    }
}