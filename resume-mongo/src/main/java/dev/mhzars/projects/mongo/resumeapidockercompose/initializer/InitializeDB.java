package dev.mhzars.projects.mongo.resumeapidockercompose.initializer;

import dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.AuthRole;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.AuthUser;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Resume;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.AuthUserRepository;
import dev.mhzars.projects.mongo.resumeapidockercompose.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils.readFile;

@Component
@Slf4j
public class InitializeDB implements CommandLineRunner {

    private static final LocalDateTime CREATION_DATE = LocalDateTime.now();
    private final AuthUserRepository userRepo;
    private final ResumeRepository resumeRepo;

    public InitializeDB(AuthUserRepository userRepo, ResumeRepository resumeRepo) {
        this.userRepo = userRepo;
        this.resumeRepo = resumeRepo;
    }

    @Override
    public void run(String... args) {
        try {
            readOrCreateDBUser();
            readOrCreateSampleResume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readOrCreateSampleResume() {
        List<Resume> resumes = readFile("resumeSample.json", Resume.class, InitializeDB.class);
        for (Resume resume : resumes) {
            log.info("Resume loaded: {}", resume);

            if (resumeRepo.findFirstByFirstName(resume.getFirstName()).isPresent()) {
                log.info("Resume found: {}", resume);
            } else {
                resume.setId(null);
                resume.getEducationList().forEach(e -> e.setId(null));
                resume.getSkillList().forEach(e -> e.setId(null));
                resume.getExperienceList().forEach(e -> e.setId(null));
                resumeRepo.save(resume);
            }
        }
    }

    private void readOrCreateDBUser() throws Exception {
        List<AuthUser> dbUsers = readFile("dbUser.json", AuthUser.class, InitializeDB.class);

        log.info("DbUser loaded: {}", dbUsers);
        for (AuthUser dbUser : dbUsers) {
            Optional<AuthUser> authUser = userRepo.findByUsername(dbUser.getUsername());
            if (authUser.isPresent()) {
                log.info("User found for: {}", CommonSpringUtils.OBJECT_MAPPER.writeValueAsString(authUser.get()));
            } else {
                List<AuthRole> authRoleList = new ArrayList<>();
                for (AuthRole r :
                        dbUser.getAuthRoles()) {
                    authRoleList.add(new AuthRole(r.getRole(), CREATION_DATE));
                }

                AuthUser user = new AuthUser(dbUser.getUsername(), dbUser.getPassword(), dbUser.isActive(), CREATION_DATE
                        , authRoleList);
                userRepo.save(user);
            }
        }
    }

}
