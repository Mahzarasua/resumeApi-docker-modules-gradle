package dev.mhzars.projects.postgres.resumeapidockercompose.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class AuthUser {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String password;
    private boolean active;
    private LocalDateTime creationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AuthRole> authRoles;

    public AuthUser(String username, String password, boolean active, LocalDateTime creationDate, List<AuthRole> authRoles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.creationDate = creationDate;
        this.authRoles = authRoles;
    }

    @PrePersist
    @PreUpdate
    public void prepareRole() {
        authRoles.forEach(r -> r.setUser(this));
    }
}
