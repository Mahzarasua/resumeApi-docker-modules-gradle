package dev.mhzars.projects.commons.resumeapidockercompose.controller;


import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomAuthException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomErrorDetails;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomNotFoundException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CommonCustomExceptionHandler extends ResponseEntityExceptionHandler {
    protected static final String URI_LABEL = "uri=";

    private static final Map<String, HttpStatus> exceptionCodeMap = new HashMap<>();

    static {
        exceptionCodeMap.put(CustomBadRequestException.class.getName(), HttpStatus.BAD_REQUEST);
        exceptionCodeMap.put(CustomNotFoundException.class.getName(), HttpStatus.NOT_FOUND);
        exceptionCodeMap.put(CustomAuthException.class.getName(), HttpStatus.UNAUTHORIZED);
    }

    private ExceptionBody getExceptionBody(CustomErrorDetails exception, WebRequest request) {
        ExceptionBody body = new ExceptionBody();
        body.setTimestamp(LocalDateTime.now());
        body.setStatusCode(exceptionCodeMap.get(exception.getClass().getName()).value());
        body.setError(exceptionCodeMap.get(exception.getClass().getName()).getReasonPhrase());
        body.setMessage(exception.getMessage());
        body.setPath(request.getDescription(false).replace(URI_LABEL, ""));
        body.setDetails(exception.getErrorDetails());
        return body;
    }

    @ExceptionHandler(value = {CustomErrorDetails.class, CustomNotFoundException.class, CustomAuthException.class, CustomBadRequestException.class})
    @ResponseBody
    public ResponseEntity<Object> exceptionResolver(
            CustomErrorDetails exception, WebRequest request) {
        ExceptionBody body = getExceptionBody(exception, request);
        return ResponseEntity.status(body.getStatusCode())
                .body(body);
    }

//    @ExceptionHandler(value = {CustomBadRequestException.class})
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    protected ResponseEntity<Object> handleMissingRequiredFields(CustomBadRequestException exception, WebRequest request) {
//        ExceptionBody body = getExceptionBody(exception, request, HttpStatus.BAD_REQUEST);
//        return ResponseEntity.status(body.getStatusCode())
//                .body(body);
//    }
//
//    @ExceptionHandler(value = {CustomAuthException.class})
//    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
//    @ResponseBody
//    protected ResponseEntity<Object> handleAuthException(CustomAuthException exception, WebRequest request) {
//        ExceptionBody body = getExceptionBody(exception, request, HttpStatus.UNAUTHORIZED);
//        return ResponseEntity.status(body.getStatusCode())
//                .body(body);
//    }
}
