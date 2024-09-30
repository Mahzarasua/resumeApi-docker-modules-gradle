package dev.mhzars.projects.postgres.resumeapidockercompose.service;


import dev.mhzars.projects.commons.resumeapidockercompose.domain.GenericDeleteResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillResponse;

public interface SkillService {
    SkillResponse getListbyResumeId(String resumeId);

    SkillResponse saveList(SkillRequest request);

    GenericDeleteResponse deleteRecordsbyResumeId(String resumeId);

    GenericDeleteResponse deleteRecordbyId(String resumeId, String id);
}
