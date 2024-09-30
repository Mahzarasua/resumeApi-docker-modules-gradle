package dev.mhzars.projects.commons.resumeapidockercompose.config;

public class WebConfigWhiteList {
    protected static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration**",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui**",
            "/openapi**",
            "/openapi/**",
            "/admin**",
            "/admin/**"
            // other public endpoints of your API may be appended to this array
            , "/authenticate"
    };

    private WebConfigWhiteList() {
    }

    public static String[] getAuthWhitelist() {
        return AUTH_WHITELIST;
    }
}
