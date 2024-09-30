package dev.mhzars.projects.commons.resumeapidockercompose.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Field;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.openMocks;

class CommonJwtTokenUtilTest {
    private final String expectedSubject = "testSubject";

    private CommonJwtTokenUtil jwtTokenUtil;
    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setup() throws NoSuchFieldException, IllegalAccessException {
        openMocks(this);

        userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userDetails.getUsername())
                .thenReturn(expectedSubject);


        jwtTokenUtil = new CommonJwtTokenUtil();

        Field secret = jwtTokenUtil.getClass().getDeclaredField("secret");
        secret.setAccessible(true);
        secret.set(jwtTokenUtil, "testSecret");
        Field validity = jwtTokenUtil.getClass().getDeclaredField("jwtTokenValidity");
        validity.setAccessible(true);
        validity.set(jwtTokenUtil, (long) 3600);
    }

    @Test
    void getUsernameFromToken_shouldReturnSubject() {
        String username = jwtTokenUtil.getUsernameFromToken(jwtTokenUtil.generateToken(userDetails));

        assertEquals(expectedSubject, username);
    }

    @Test
    void getExpirationDateFromToken_shouldReturnExpirationDate() {
        Date actualExpirationDate = jwtTokenUtil.getExpirationDateFromToken(jwtTokenUtil.generateToken(userDetails));

        assertTrue(actualExpirationDate.after(new Date()));
    }

    @Test
    void getClaimFromToken_shouldReturnClaimValue() {
        String token = jwtTokenUtil.generateToken(userDetails);
        String username = jwtTokenUtil.getClaimFromToken(token, Claims::getSubject);

        assertEquals(expectedSubject, username);
    }

    @Test
    void generateToken_shouldGenerateToken() {
        String token = jwtTokenUtil.generateToken(userDetails);

        assertNotNull(token);
    }

    @Test
    void validateToken_shouldReturnTrueForValidToken() {
        String token = jwtTokenUtil.generateToken(userDetails);

        assertTrue(jwtTokenUtil.validateToken(token, userDetails));
    }

    @Test
    void validateToken_shouldReturnFalseForExpiredToken() throws IllegalAccessException, NoSuchFieldException {
        //Setting an expired value
        Field expirationField = jwtTokenUtil.getClass().getDeclaredField("jwtTokenValidity");
        expirationField.setAccessible(true);
        long originalValue = (long) expirationField.get(jwtTokenUtil);
        expirationField.set(jwtTokenUtil, -originalValue);

        String token = jwtTokenUtil.generateToken(userDetails);

        assertThrows(ExpiredJwtException.class, () -> jwtTokenUtil.validateToken(token, userDetails));

        //returning it to the original value
        expirationField.setAccessible(true);
        expirationField.set(jwtTokenUtil, originalValue);

    }

    // Add more test cases as needed for different scenarios

}