package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.mongo.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Education;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringResumeRepo;
import dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EducationServiceImpl implements EducationService {

    public static final String EXCEPTION_MSG = "No Education record was found for resumeId %s";
    private final ResumeRepository repo;
    private final CustomMapper mapper;
    private final SpringResumeRepo checkResume;

    @Override
    public EducationResponse getListbyResumeId(String resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);
        List<Education> list = resume.getEducationList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        return new EducationResponse(mapper.mapAsList(list, EducationDomain.class));
    }

    @Override
    public EducationResponse saveList(EducationRequest request) {
        String resumeId = request.getEducationList().get(0).getResumeId();
        Resume resume = checkResume.checkResumeId(resumeId);

        request.getEducationList().forEach(e -> e.setResumeId(String.valueOf(resume.getId())));
        for (Education e : mapper.mapAsList(request.getEducationList(), Education.class)) {
            if (e.getId() == null) e.setId(SpringUtils.generateUniqueObjectId());
            if (e.getCreationDate() == null) e.setCreationDate(LocalDateTime.now());
            if (!resume.getEducationList().contains(e)) {
                resume.getEducationList().add(e);
            }
        }

        repo.save(resume);

        return getListbyResumeId(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordsbyResumeId(String resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);

        List<Education> list = resume.getEducationList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        resume.getEducationList().clear();
        repo.save(resume);

        return new GenericDeleteResponse(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordbyId(String resumeId, String id) {
        Resume resume = checkResume.checkResumeId(resumeId);
        checkResumeEducationList(resume, id);
        SpringUtils.removeFromList(resume.getEducationList(), row -> Objects.equals(row.getId(), SpringUtils.validateObjectId(id)) && Objects.equals(resume.getId(), SpringUtils.validateObjectId(resumeId)));
        repo.save(resume);
        return new GenericDeleteResponse(id, resumeId);
    }

    private void checkResumeEducationList(Resume resume, String id) {
        boolean recNotFound = true;
        for (Education e : resume.getEducationList()) {
            if (Objects.equals(e.getId(), SpringUtils.validateObjectId(id))) {
                recNotFound = false;
            }
        }
        if (recNotFound)
            throw new CustomNotFoundException(String.format("Education with id %s was not found", id));
    }
}
