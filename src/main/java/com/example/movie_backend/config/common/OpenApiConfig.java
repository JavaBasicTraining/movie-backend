package com.example.movie_backend.config.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@SecuritySchemes(value = {
    @SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "Bearer [token]",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER,
        description = "Access token"
    )
})
@OpenAPIDefinition(
    info = @Info(title = "Movie API", version = "1.0.0", description = "Documentation Movie v1.0.0"),
    security = @SecurityRequirement(name = "Authorization")
)
public class OpenApiConfig {
}
