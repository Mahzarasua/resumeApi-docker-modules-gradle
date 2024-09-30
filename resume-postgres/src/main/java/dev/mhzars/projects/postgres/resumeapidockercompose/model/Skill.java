package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"resume"})
public class Skill {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Resume resume;

    private String name;
    private int percentage;
    private String type;
    private LocalDateTime creationDate;

    @PrePersist
    public void setCreationDate() {
        creationDate = LocalDateTime.now();
    }

    @PreRemove
    public void preRemoveSkill() {
        resume.getSkillList().remove(this);
    }
}
