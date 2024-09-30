package dev.mhzars.projects.commons.resumeapidockercompose.config;

import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomAuthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommonCustomAuthenticationManagerTest {

    private static final String username = "user";
    private static final String password = "password";
    private UserDetailsService userDetailsService;
    private UserDetails userDetails;
    private PasswordEncoder encoder;
    private CommonCustomAuthenticationManager authenticationManager;

    @BeforeEach
    void init() {
        userDetailsService = Mockito.mock(UserDetailsService.class);
        encoder = Mockito.mock(PasswordEncoder.class);
        userDetails = Mockito.mock(UserDetails.class);

        authenticationManager = new CommonCustomAuthenticationManager(userDetailsService, encoder);
    }

    @Test
    void testSuccessfulAuthentication() {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Mockito.when(encoder.matches(password, userDetails.getPassword())).thenReturn(true);
        Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        Mockito.when(userDetails.isEnabled()).thenReturn(true);

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        assertNotNull(authentication);
        assertEquals(username, authentication.getPrincipal());
        assertEquals(password, authentication.getCredentials());
    }

    @Test
    void testSuccessfulAuthenticationUserAndPassword() {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Mockito.when(encoder.matches(password, userDetails.getPassword())).thenReturn(true);
        Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        Mockito.when(userDetails.isEnabled()).thenReturn(true);

        UserDetails authentication = authenticationManager.authentication(username, password);

        assertNotNull(authentication);
//        assertEquals(username, authentication.getUsername());
//        assertEquals(password, authentication.getPassword());
    }

    @Test
    void testInvalidUsernameOrPassword() {
        Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        Mockito.when(encoder.matches(password, userDetails.getPassword())).thenReturn(false);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        assertThrows(CustomAuthException.class, () -> authenticationManager.authenticate(usernamePasswordAuthenticationToken));
    }

    @Test
    void testDisabledUser() {
        Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        Mockito.when(encoder.matches(password, userDetails.getPassword())).thenReturn(true);
        Mockito.when(userDetails.isEnabled()).thenReturn(false);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        assertThrows(CustomAuthException.class, () -> authenticationManager.authenticate(usernamePasswordAuthenticationToken));
    }
}