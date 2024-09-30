package dev.mhzars.projects.postgres.resumeapidockercompose.service;

import dev.mhzars.projects.commons.resumeapidockercompose.config.MyUserDetails;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomAuthException;
import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthUser;
import dev.mhzars.projects.postgres.resumeapidockercompose.mapper.CustomMapper;
import dev.mhzars.projects.postgres.resumeapidockercompose.model.AuthUser;
import dev.mhzars.projects.postgres.resumeapidockercompose.repository.AuthUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.RESUME_ID;
import static dev.mhzars.projects.postgres.resumeapidockercompose.TestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class MyUserDetailsServiceTest {

    private static MyUserDetailsService service;

    @BeforeEach
    void init() {
        AuthUserRepository authUserRepository = Mockito.mock(AuthUserRepository.class);
        CustomMapper mapper = Mockito.mock(CustomMapper.class);

        AuthUser user = manufacturedPojo(AuthUser.class);
        Optional<AuthUser> userOptional = Optional.ofNullable(user);
        Optional<AuthUser> userEmpty = Optional.empty();
        CommonAuthUser authUser = manufacturedPojo(CommonAuthUser.class);
        MyUserDetails userDetails = manufacturedPojo(MyUserDetails.class);

        Mockito.doReturn(userOptional)
                .when(authUserRepository).findByUsername(ArgumentMatchers.anyString());
        Mockito.doReturn(userEmpty)
                .when(authUserRepository).findByUsername(RESUME_ID);

        Mockito.when(mapper.map(ArgumentMatchers.eq(user), ArgumentMatchers.eq(CommonAuthUser.class)))
                .thenReturn(authUser);
        Mockito.when(mapper.map(ArgumentMatchers.eq(authUser), ArgumentMatchers.eq(MyUserDetails.class)))
                .thenReturn(userDetails);

        service = new MyUserDetailsService(authUserRepository, mapper);
    }

    @Test
    void loadUserByUsername() {
        UserDetails response = service.loadUserByUsername("Test");
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    void loadUserByUsername_Negative() {
        assertThrows(CustomAuthException.class, () -> service.loadUserByUsername(RESUME_ID));
    }
}