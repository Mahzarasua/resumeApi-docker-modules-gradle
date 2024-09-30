package dev.mhzars.projects.commons.resumeapidockercompose.validator;

public interface CustomValidator<E> {
    void validate(E request);
}
