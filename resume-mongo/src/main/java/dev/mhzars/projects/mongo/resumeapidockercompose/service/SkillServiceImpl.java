package dev.mhzars.projects.mongo.resumeapidockercompose.service;

import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.mongo.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Skill;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.ResumeRepository;
import dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringResumeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.removeFromList;
import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.validateObjectId;

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
            if (e.getId() == null) e.setId(generateUniqueObjectId());
            if (e.getCreationDate() == null) e.setCreationDate(LocalDateTime.now());
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
        removeFromList(resume.getSkillList(), row -> Objects.equals(row.getId(), validateObjectId(id)) && Objects.equals(resume.getId(), validateObjectId(resumeId)));
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
            throw new CustomNotFoundException(String.format("Skill with id %s was not found", id));
    }
}
