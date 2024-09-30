package dev.mhzars.projects.mongo.resumeapidockercompose;

import dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.resume.ResumeRequest;

import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;

public class TestUtils extends CommonTestUtils {
    public static void setChildTables(ResumeRequest request) {
        request.getEducationList().forEach(r -> r.setId(generateUniqueObjectId().toString()));
        request.getExperienceList().forEach(r -> r.setId(generateUniqueObjectId().toString()));
        request.getSkillList().forEach(r -> r.setId(generateUniqueObjectId().toString()));
    }


}
