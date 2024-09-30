package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreRemove;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class AuthRole {
    @Id
    @GeneratedValue
    private UUID id;
    private String role;
    private LocalDateTime creationDate;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private AuthUser user;

    public AuthRole(String role, LocalDateTime creationDate) {
        this.role = role;
        this.creationDate = creationDate;
    }

    @PreRemove
    public void preRemoveRole() {
        user.getAuthRoles().remove(this);
    }
}
