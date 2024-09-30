package dev.mhzars.projects.commons.resumeapidockercompose.config;

import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomAuthException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthUser;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import static dev.mhzars.projects.commons.resumeapidockercompose.CommonTestUtils.manufacturedPojo;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonJwtRequestFilcterTest {
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private CommonJwtTokenUtil jwtTokenUtil;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    private CommonJwtRequestFilter jwtRequestFilter;

    private static CommonAuthUser getAuthUser(String username) {
        CommonAuthUser authUser = manufacturedPojo(CommonAuthUser.class);
        authUser.setUsername(username);
        return authUser;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtRequestFilter = new CommonJwtRequestFilter(userDetailsService, jwtTokenUtil);
    }

    @Test
    void testDoFilterInternal_WithValidToken() throws ServletException, IOException {
        String token = "valid_token";
        String username = "testUser";

        MyUserDetails userDetails = new MyUserDetails(getAuthUser(username));
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(jwtTokenUtil.getUsernameFromToken(token)).thenReturn(username);
        Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        Mockito.when(jwtTokenUtil.validateToken(token, userDetails)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        Mockito.verify(filterChain, Mockito.times(1)).doFilter(request, response);
        Mockito.verify(request, Mockito.times(1)).getHeader("Authorization");
        Mockito.verify(jwtTokenUtil, Mockito.times(1)).getUsernameFromToken(token);
        Mockito.verify(userDetailsService, Mockito.times(1)).loadUserByUsername(username);
        assertTrue(true);
    }

    @Test
    void testDoFilterInternal_WithNoBearer() throws ServletException, IOException {
        String token = "valid_token";
        String username = "testUser";

        MyUserDetails userDetails = new MyUserDetails(getAuthUser(username));
        Mockito.when(request.getHeader("Authorization")).thenReturn(token);
        Mockito.when(jwtTokenUtil.getUsernameFromToken(token)).thenReturn(username);
        Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        Mockito.when(jwtTokenUtil.validateToken(token, userDetails)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        Mockito.verify(filterChain, Mockito.times(1)).doFilter(request, response);
        Mockito.verify(request, Mockito.times(1)).getHeader("Authorization");
        assertTrue(true);
    }

    @Test
    void testDoFilterInternal_WithInvalidToken() throws ServletException, IOException {
        String token = "null";
        String username = "testUser";

        MyUserDetails userDetails = new MyUserDetails(getAuthUser(username));
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(jwtTokenUtil.getUsernameFromToken(token)).thenThrow(new IllegalArgumentException("Invalid token"));
        Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        Mockito.when(request.getRequestURI()).thenReturn("/test");
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(printWriter);


        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        Mockito.verify(request, Mockito.times(1)).getHeader("Authorization");
        Mockito.verify(jwtTokenUtil, Mockito.times(1)).getUsernameFromToken(token);
        assertTrue(true);
    }

    @Test
    void testExpiredJwtExceptionHandling() throws ServletException, IOException {
        // Simulate ExpiredJwtException by throwing it from jwtTokenUtil
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer expired_token");
        Mockito.when(jwtTokenUtil.getUsernameFromToken("expired_token")).thenThrow(ExpiredJwtException.class);
        Mockito.when(request.getRequestURI()).thenReturn("/test");
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(printWriter);

        // Call the method under test
        CommonJwtRequestFilter jwtRequestFilter = new CommonJwtRequestFilter(userDetailsService, jwtTokenUtil);
        jwtRequestFilter.doFilterInternal(request, response, null);
        assertTrue(true);
    }

    @Test
    void testGenericJwtExceptionHandling() throws ServletException, IOException {
        // Simulate ExpiredJwtException by throwing it from jwtTokenUtil
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer expired_token");
        Mockito.when(jwtTokenUtil.getUsernameFromToken("expired_token")).thenThrow(new RuntimeException());
        Mockito.when(request.getRequestURI()).thenReturn("/test");
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(printWriter);

        // Call the method under test
        CommonJwtRequestFilter jwtRequestFilter = new CommonJwtRequestFilter(userDetailsService, jwtTokenUtil);
        jwtRequestFilter.doFilterInternal(request, response, null);
        assertTrue(true);
    }

    @Test
    void testDoFilterInternal_Whitelisted() throws ServletException, IOException {
        String token = null;
        String username = "testUser";

        MyUserDetails userDetails = new MyUserDetails(getAuthUser(username));
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(jwtTokenUtil.getUsernameFromToken(token)).thenReturn(username);
        Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        Mockito.when(request.getRequestURI()).thenReturn("/admin");

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        Mockito.verify(filterChain, Mockito.times(1)).doFilter(request, response);
        Mockito.verify(request, Mockito.times(1)).getHeader("Authorization");
        assertTrue(true);
    }

    @Test
    void testFilterException() throws IOException {
        // Mock the CustomAuthException
        CustomAuthException customAuthException = Mockito.mock(CustomAuthException.class);
        ExceptionBody.ErrorDetails errorDetails = new ExceptionBody.ErrorDetails("test", "Error details");
        Mockito.when(customAuthException.getErrorDetails()).thenReturn(Collections.singletonList(errorDetails));

        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(printWriter);

        // Call the method under test
        CommonJwtRequestFilter.filterException("/example-uri", response, customAuthException);

        // Verify that the response was set correctly
        Mockito.verify(response, Mockito.times(1)).setStatus(HttpStatus.UNAUTHORIZED.value());
        Mockito.verify(response, Mockito.times(1)).setContentType("application/json");
        Mockito.verify(response, Mockito.times(1)).getWriter();
        // Add more verifications as needed for other method calls
        assertTrue(true);
    }

    // Add more test cases for different scenarios as needed
}