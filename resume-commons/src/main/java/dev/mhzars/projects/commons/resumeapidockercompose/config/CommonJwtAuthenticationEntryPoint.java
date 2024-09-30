package dev.mhzars.projects.commons.resumeapidockercompose.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public class CommonJwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    public static final String UNAUTHORIZED_ERROR = "Unauthorized";
    @Serial
    private static final long serialVersionUID = -8606894235005320274L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORIZED_ERROR);
    }
}