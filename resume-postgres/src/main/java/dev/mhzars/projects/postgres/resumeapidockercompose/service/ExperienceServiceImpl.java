package dev.mhzars.projects.postgres.resumeapidockercompose.service;


import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.postgres.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Experience;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.postgres.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringResumeRepo;
import dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.validateObjectId;

@Service
@AllArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    public static final String EXCEPTION_MSG = "No Experience record was found for resumeId %s";
    private final ResumeRepository repo;
    private final CustomMapper mapper;
    private final SpringResumeRepo checkResume;

    @Override
    public ExperienceResponse getListbyResumeId(String resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);
        List<Experience> list = resume.getExperienceList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        return new ExperienceResponse(mapper.mapAsList(list, ExperienceDomain.class));
    }

    @Override
    public ExperienceResponse saveList(ExperienceRequest request) {
        String resumeId = request.getExperienceList().get(0).getResumeId();
        Resume resume = checkResume.checkResumeId(resumeId);

        request.getExperienceList().forEach(e -> e.setResumeId(String.valueOf(resume.getId())));
        for (Experience e : mapper.mapAsList(request.getExperienceList(), Experience.class)) {
            if (!resume.getExperienceList().contains(e)) {
                resume.getExperienceList().add(e);
            }
        }

        repo.save(resume);

        return getListbyResumeId(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordsbyResumeId(String resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);

        List<Experience> list = resume.getExperienceList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        resume.getExperienceList().clear();
        repo.save(resume);

        return new GenericDeleteResponse(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordbyId(String resumeId, String id) {
        Resume resume = checkResume.checkResumeId(resumeId);
        checkResumeExperienceList(resume, id);
        SpringUtils.removeFromList(resume.getExperienceList(), row -> Objects.equals(row.getId(), validateObjectId(id)) && Objects.equals(resume.getId(), validateObjectId(resumeId)));
        repo.save(resume);
        return new GenericDeleteResponse(id, resumeId);
    }

    private void checkResumeExperienceList(Resume resume, String id) {
        boolean recNotFound = true;
        for (Experience e : resume.getExperienceList()) {
            if (Objects.equals(e.getId(), validateObjectId(id))) {
                recNotFound = false;
            }
        }
        if (recNotFound)
            throw new CustomNotFoundException(String.format("Experience with id %s was not found", id));
    }
}
