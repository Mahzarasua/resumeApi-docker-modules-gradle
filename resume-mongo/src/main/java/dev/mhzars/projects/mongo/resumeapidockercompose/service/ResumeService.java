package dev.mhzars.projects.mongo.resumeapidockercompose.service;


import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeIdResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.resume.ResumeResponse;
import dev.mhzars.projects.mongo.resumeapidockercompose.domain.resume.ResumeRequest;

import java.util.List;

public interface ResumeService {
    List<ResumeResponse> getAllResumes();

    ResumeResponse getResumeById(String id);

    ResumeIdResponse saveResume(ResumeRequest request);

    ResumeIdResponse saveResume(ResumeRequest request, String id);

    ResumeIdResponse deleteResumeById(String id);

    ResumeResponse getResumeByFirstName(String firstName);
}
