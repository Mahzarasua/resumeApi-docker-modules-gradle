package dev.mhzars.projects.commons.resumeapidockercompose.config;


import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomAuthException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import static dev.mhzars.projects.commons.resumeapidockercompose.config.WebConfigWhiteList.getAuthWhitelist;

@Slf4j
public class CommonJwtRequestFilter extends OncePerRequestFilter {
    public static final String REVIEW_MSG = "Authentication failed, please review";
    public static final String URI_STR = "uri=";
    public static final String AUTH_HEADER = "Authorization";
    public static final String UNABLE_TO_GET_JWT_TOKEN = "Unable to get JWT Token";
    public static final String TOKEN_HAS_EXPIRED = "JWT Token has expired";
    private static final String[] WHITELISTED_PATHS = {
            // -- Swagger UI v2
            "/swagger",
            "/openapi",
            "/admin"};
    private final UserDetailsService jwtUserDetailsService;
    private final CommonJwtTokenUtil jwtTokenUtil;

    public CommonJwtRequestFilter(UserDetailsService jwtUserDetailsService, CommonJwtTokenUtil jwtTokenUtil) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public static void filterException(String requestUri, HttpServletResponse response, CustomAuthException e) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ExceptionBody body = new ExceptionBody();
        body.setTimestamp(LocalDateTime.now());
        body.setStatusCode(response.getStatus());
        body.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        body.setMessage(REVIEW_MSG);
        body.setPath(requestUri.replace(URI_STR, ""));
        body.setDetails(e.getErrorDetails());
        response.getWriter().write(CommonSpringUtils.OBJECT_MAPPER.writeValueAsString(body));
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(AUTH_HEADER);

        try {
            if (checkWhiteList(request.getRequestURI()))
                log.debug("Path {} is white listed", request.getRequestURI());
            else {
                //If path is Whitelisted, it can be accessed without a token
                log.debug("Path {} needs to be authenticated", request.getRequestURI());
                validateRequestAndToken(request, requestTokenHeader);
            }
            assert chain != null;
            chain.doFilter(request, response);
        } catch (CustomAuthException e) {
            assert response != null;
            filterException(request.getRequestURI(), response, e);
        }
    }

    private boolean checkWhiteList(String uri) {
        return Arrays.stream(WHITELISTED_PATHS).anyMatch(path -> StringUtils.contains(uri, path)) || Arrays.stream(getAuthWhitelist()).anyMatch(path -> StringUtils.contains(path, uri));
    }

    private void validateRequestAndToken(HttpServletRequest request, String requestTokenHeader) {
        String username = null;
        String jwtToken = null;

        log.debug("Path to validate {}", request.getRequestURI());
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.error(UNABLE_TO_GET_JWT_TOKEN);
                throw new CustomAuthException(UNABLE_TO_GET_JWT_TOKEN);
            } catch (ExpiredJwtException e) {
                log.error(TOKEN_HAS_EXPIRED);
                throw new CustomAuthException(TOKEN_HAS_EXPIRED);
            } catch (Exception e) {
                log.error("Generic exception");
                throw new CustomAuthException(e.getMessage());
            }
        } else {
            log.warn("JWT Token does not include Bearer word");
        }

        // Once we get the token validated it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set authentication
            if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(jwtToken, userDetails))) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null
                                , userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify that the current user is authenticated.
                // So it passes Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

    }
}
