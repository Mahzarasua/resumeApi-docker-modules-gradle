package dev.mhzars.projects.commons.resumeapidockercompose.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends CustomErrorDetails {
    @Serial
    private static final long serialVersionUID = -4637713924717607806L;
    private static final String FIELD_NAME = "Record not found";

    public CustomNotFoundException(String errorMessage) {
        super(errorMessage, FIELD_NAME);
    }
}
