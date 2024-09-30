package dev.mhzars.projects.mongo.resumeapidockercompose.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

import static dev.mhzars.projects.mongo.resumeapidockercompose.utils.SpringUtils.generateUniqueObjectId;

@Document
@Data
@NoArgsConstructor
public class AuthUser {
    @Id
    @BsonId
    @BsonProperty("_id")
    private ObjectId id;
    private String username;
    private String password;
    private boolean active;
    private LocalDateTime creationDate;

    private List<AuthRole> authRoles;

    public AuthUser(String username, String password, boolean active, LocalDateTime creationDate, List<AuthRole> authRoles) {
        this.id = generateUniqueObjectId();
        this.username = username;
        this.password = password;
        this.active = active;
        this.creationDate = creationDate;
        this.authRoles = authRoles;
    }

}
