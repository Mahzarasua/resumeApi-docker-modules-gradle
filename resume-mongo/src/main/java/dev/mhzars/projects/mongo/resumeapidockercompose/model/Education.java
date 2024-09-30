package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonEducation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education extends CommonEducation {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;
}
