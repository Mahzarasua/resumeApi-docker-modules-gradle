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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"resume"})
public class Experience {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Resume resume;

    private String title;
    private String company;
    private boolean currentJob;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime creationDate;

    @PrePersist
    public void setCreationDate() {
        creationDate = LocalDateTime.now();
    }

    @PreRemove
    public void preRemoveWorkExp() {
        resume.getExperienceList().remove(this);
    }
}
