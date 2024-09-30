package dev.mhzars.projects.postgres.resumeapidockercompose;


import dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils;
import dev.mhzars.projects.postgres.resumeapidockercompose.domain.resume.ResumeRequest;

import static dev.mhzars.projects.postgres.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;


public class TestUtils extends CommonTestUtils {
    public static void setChildTables(ResumeRequest request) {
        request.getEducationList().forEach(r -> r.setId(generateUniqueObjectId().toString()));
        request.getExperienceList().forEach(r -> r.setId(generateUniqueObjectId().toString()));
        request.getSkillList().forEach(r -> r.setId(generateUniqueObjectId().toString()));
    }


}
