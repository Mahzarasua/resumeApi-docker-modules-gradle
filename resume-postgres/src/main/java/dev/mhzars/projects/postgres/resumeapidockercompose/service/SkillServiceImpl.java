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
import dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.validateObjectId;

@Service
@AllArgsConstructor
public class SkillServiceImpl implements SkillService {
    public static final String EXCEPTION_MSG = "No Skill record was found for resumeId %s";
    private final ResumeRepository repo;
    private final CustomMapper mapper;
    private final SpringResumeRepo checkResume;

    @Override
    public SkillResponse getListbyResumeId(String resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);
        List<Skill> list = resume.getSkillList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        return new SkillResponse(mapper.mapAsList(list, SkillDomain.class));
    }

    @Override
    public SkillResponse saveList(SkillRequest request) {
        String resumeId = request.getSkillList().get(0).getResumeId();
        Resume resume = checkResume.checkResumeId(resumeId);

        request.getSkillList().forEach(e -> e.setResumeId(String.valueOf(resume.getId())));
        for (Skill e : mapper.mapAsList(request.getSkillList(), Skill.class)) {
            if (!resume.getSkillList().contains(e)) {
                resume.getSkillList().add(e);
            }
        }

        repo.save(resume);

        return getListbyResumeId(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordsbyResumeId(String resumeId) {
        Resume resume = checkResume.checkResumeId(resumeId);

        List<Skill> list = resume.getSkillList();

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(EXCEPTION_MSG, resumeId));

        resume.getSkillList().clear();
        repo.save(resume);

        return new GenericDeleteResponse(resumeId);
    }

    @Override
    public GenericDeleteResponse deleteRecordbyId(String resumeId, String id) {
        Resume resume = checkResume.checkResumeId(resumeId);
        checkResumeSkillList(resume, id);
        SpringUtils.removeFromList(resume.getSkillList(), row -> Objects.equals(row.getId(), validateObjectId(id)) && Objects.equals(resume.getId(), validateObjectId(resumeId)));
        repo.save(resume);
        return new GenericDeleteResponse(id, resumeId);
    }

    private void checkResumeSkillList(Resume resume, String id) {
        boolean recNotFound = true;
        for (Skill e : resume.getSkillList()) {
            if (Objects.equals(e.getId(), validateObjectId(id))) {
                recNotFound = false;
            }
        }
        if (recNotFound)
            throw new CustomNotFoundException(String.format("Experience with id %s was not found", id));
    }
}
