package dev.mhzars.projects.commons.resumeapidockercompose.validator;

import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.CommonResumeRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedCustomPojo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommonResumeValidatorTest {

    private static CommonResumeValidator validator;

    @BeforeEach
    void init() {
        validator = new CommonResumeValidator();
    }

    @Test
    void validate() {
        CommonResumeRequest request = manufacturedCustomPojo(CommonResumeRequest.class);

        request.setId(null);
        validator.validate(request);
    }

    @Test
    void validate_Negative() {
        CommonResumeRequest request = new CommonResumeRequest();
        request.setId("");
        request.setFirstName("");
        request.setLastName("");
        request.setTitle("");
        request.setCity("");
        request.setState("");
        request.setCountry("");
        request.setEmail("aaaaaaaaaaa");
        request.setPhone("TEST_PHONE");
        request.setSummary("");
        request.setCreationDate(LocalDateTime.now());

        assertThrows(CustomBadRequestException.class, () -> validator.validate(request));
    }

    @Test
    void validate_Negative_null() {
        CommonResumeRequest request = new CommonResumeRequest();
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

        assertThrows(CustomBadRequestException.class, () -> validator.validate(request));
    }

}