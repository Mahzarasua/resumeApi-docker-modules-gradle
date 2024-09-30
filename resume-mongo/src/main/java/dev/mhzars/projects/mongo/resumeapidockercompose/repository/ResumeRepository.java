package dev.mhzars.projects.mongo.resumeapidockercompose.repository;

import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeRepository extends MongoRepository<Resume, ObjectId> {
    Optional<Resume> findFirstByFirstName(String firstName);
}
