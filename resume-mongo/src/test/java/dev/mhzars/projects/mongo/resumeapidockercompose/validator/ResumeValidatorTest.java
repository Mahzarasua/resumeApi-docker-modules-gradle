package dev.mhzars.projects.mongo.resumeapidockercompose.validator;

import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.resume.ResumeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.manufacturedCustomPojo;
import static dev.mhzars.projects.mongo.resumeapidockercompose.TestUtils.setChildTables;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResumeValidatorTest {

    private static ResumeValidator validator;

    @BeforeEach
    void init() {
        validator = new ResumeValidator();
    }

    @Test
    void validate() {
        ResumeRequest request = manufacturedCustomPojo(ResumeRequest.class);

        request.setId(null);
        setChildTables(request);
        validator.validate(request);
    }

    @Test
    void validate_Negative_childTables() {
        ResumeRequest request = new ResumeRequest();
        request.setId("");
        request.setFirstName("");
        request.setLastName("");
        request.setTitle("");
        request.setCity("");
        request.setState("");
        request.setCountry("");
        request.setEmail("");
        request.setPhone("");
        request.setSummary("");
        request.setCreationDate(LocalDateTime.now());

        SkillDomain skillDomain = new SkillDomain();
        skillDomain.setResumeId("");
        skillDomain.setId("");
        skillDomain.setName("");
        skillDomain.setPercentage(-1);
        skillDomain.setType("");
        skillDomain.setCreationDate(LocalDateTime.now());
        List<SkillDomain> skillList = Collections.singletonList(skillDomain);
        request.setSkillList(skillList);

        EducationDomain e1 = new EducationDomain();
        e1.setResumeId("");
        e1.setId("");
        e1.setName("");
        e1.setCareer("");
        e1.setDegree("Not Enum");
        e1.setStartDate(null);
        e1.setEndDate(null);
        e1.setCreationDate(LocalDateTime.now());
        EducationDomain e2 = new EducationDomain();
        e2.setResumeId("");
        e2.setId("");
        e2.setName("");
        e2.setCareer("");
        e2.setDegree("");
        e2.setStartDate(LocalDate.now());
        e2.setEndDate(LocalDate.of(2023, 12, 25));
        e2.setCreationDate(LocalDateTime.now());
        List<EducationDomain> educationList = new ArrayList<>();
        educationList.add(e1);
        educationList.add(e2);
        request.setEducationList(educationList);
        ExperienceDomain x1 = new ExperienceDomain();
        x1.setResumeId("");
        x1.setId("");
        x1.setTitle("");
        x1.setCompany("");
        x1.setCurrentJob(false);
        x1.setDescription("");
        x1.setStartDate(null);
        x1.setEndDate(null);
        x1.setCreationDate(LocalDateTime.now());
        ExperienceDomain x2 = new ExperienceDomain();
        x2.setResumeId("");
        x2.setId("");
        x2.setTitle("");
        x2.setCompany("");
        x2.setCurrentJob(false);
        x2.setDescription("");
        x2.setStartDate(LocalDate.now());
        x2.setEndDate(LocalDate.of(2023, 12, 25));
        x2.setCreationDate(LocalDateTime.now());
        List<ExperienceDomain> experienceList = new ArrayList<>();
        experienceList.add(x1);
        experienceList.add(x2);


        request.setExperienceList(experienceList);

        assertThrows(CustomBadRequestException.class, () -> validator.validate(request));
    }
}