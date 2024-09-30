package dev.mhzars.projects.mongo.resumeapidockercompose.utils;


import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.ResumeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SpringResumeRepo {

    private static final String RESUME_ID_NOT_FOUND = "Resume with id %s was not found";
    private final ResumeRepository resumeRepo;

    public Resume checkResumeId(String id) {
        return resumeRepo.findById(SpringUtils.validateObjectId(id))
                .orElseThrow(() -> new CustomNotFoundException(String.format(RESUME_ID_NOT_FOUND, id)));
    }
}
