package dev.mhzars.projects.postgres.resumeapidockercompose.utils;


import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.postgres.resumeapidockercompose.repository.ResumeRepository;
import org.springframework.stereotype.Component;

@Component
public class SpringResumeRepo {

    private static final String RESUME_ID_NOT_FOUND = "Resume with id %s was not found";
    private final ResumeRepository resumeRepo;

    public SpringResumeRepo(ResumeRepository resumeRepo) {
        this.resumeRepo = resumeRepo;
    }

    public Resume checkResumeId(String id) {
        return resumeRepo.findById(SpringUtils.validateObjectId(id))
                .orElseThrow(() -> new CustomNotFoundException(String.format(RESUME_ID_NOT_FOUND, id)));
    }
}
