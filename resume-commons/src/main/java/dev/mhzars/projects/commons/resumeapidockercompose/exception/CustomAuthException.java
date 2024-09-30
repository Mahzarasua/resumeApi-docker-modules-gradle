package dev.mhzars.projects.commons.resumeapidockercompose.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CustomAuthException extends CustomErrorDetails {
    @Serial
    private static final long serialVersionUID = 8390616249364923286L;
    private static final String FIELD_NAME = "Authentication error";

    public CustomAuthException(String errorMessage) {
        super(errorMessage, FIELD_NAME);
    }

    public CustomAuthException(ExceptionBody.ErrorDetails errorDetail, String errorMessage) {
        super(errorDetail, errorMessage);
    }
}
