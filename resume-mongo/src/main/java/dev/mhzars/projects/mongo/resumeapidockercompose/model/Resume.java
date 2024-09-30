package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonResume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume extends CommonResume {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;

    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<Skill> skillList;
}
