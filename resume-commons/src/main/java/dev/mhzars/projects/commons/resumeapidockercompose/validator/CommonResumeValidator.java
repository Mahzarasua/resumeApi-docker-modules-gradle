package dev.mhzars.projects.commons.resumeapidockercompose.validator;

import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.CommonResumeRequest;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;

import java.util.ArrayList;
import java.util.List;

import static dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody.newErrorDetail;


public class CommonResumeValidator implements CustomValidator<CommonResumeRequest> {
    @Override
    public void validate(CommonResumeRequest request) {
        List<ExceptionBody.ErrorDetails> errorDetails = new ArrayList<>();
        validateRequiredField(request, errorDetails);

        if (!errorDetails.isEmpty())
            throw new CustomBadRequestException(errorDetails, CustomValidationUtils.CUSTOM_ERROR_MSG);
    }

    private void validateRequiredField(CommonResumeRequest request, List<ExceptionBody.ErrorDetails> errorDetails) {
        if (!CustomValidationUtils.isValidString(request.getFirstName()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "First name"));
        if (!CustomValidationUtils.isValidString(request.getLastName()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Second name"));
        if (!CustomValidationUtils.isValidString(request.getEmail()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Email"));
        if (!CustomValidationUtils.isValidString(request.getPhone()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Phone number"));
        if (!CustomValidationUtils.isValidString(request.getCity()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "City"));
        if (!CustomValidationUtils.isValidString(request.getCountry()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.REQ_FIELD, "Country"));
        if (!CustomValidationUtils.isValidEmail(request.getEmail()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.INVALID_FORMAT, "Email"));
        if (!CustomValidationUtils.isValidPhoneNumber(request.getPhone()))
            errorDetails.add(newErrorDetail(CustomValidationUtils.INVALID_FORMAT, "Phone number"));
    }
}
