package dev.mhzars.projects.commons.resumeapidockercompose.exception;

import lombok.Getter;

import java.io.Serial;
import java.util.Collections;
import java.util.List;

@Getter
public class CustomErrorDetails extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -810308794990690692L;

    private final List<ExceptionBody.ErrorDetails> errorDetails;

    public CustomErrorDetails(String errorMessage, String fieldName) {
        super(errorMessage);
        this.errorDetails = Collections.singletonList(new ExceptionBody.ErrorDetails(fieldName, errorMessage));
    }

    public CustomErrorDetails(ExceptionBody.ErrorDetails errorDetails, String errorMessage) {
        super(errorMessage);
        this.errorDetails = Collections.singletonList(errorDetails);
    }

    public CustomErrorDetails(List<ExceptionBody.ErrorDetails> errorDetails, String errorMessage) {
        super(errorMessage);
        this.errorDetails = errorDetails;
    }
}
