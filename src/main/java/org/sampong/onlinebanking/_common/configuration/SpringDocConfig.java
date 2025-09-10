package org.sampong.onlinebanking._common.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@OpenAPIDefinition
class SpringDocConfig {
    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    private final SpringApiGroupProperties apiProperties;
    private final Environment environment;

    @Bean
    public OpenAPI openAPIConfig() {
        String activeProfile = environment.getActiveProfiles().length > 0
                ? environment.getActiveProfiles()[0]
                : "default";

        String serverUrl;
        String description;

        switch (activeProfile) {
            case "dev" -> {
                serverUrl = "http://localhost:8080";
                description = "Local Dev";
            }
            case "staging" -> {
                serverUrl = "https://staging.myapp.com";
                description = "Staging";
            }
            case "prod" -> {
                serverUrl = "https://api.myapp.com";
                description = "Production";
            }
            default -> {
                serverUrl = "http://localhost:8080";
                description = "Default Local";
            }
        }
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .specVersion(SpecVersion.V31)
                .servers(List.of(new Server().url(serverUrl).description(description)))
                .info(new Info()
                        .title(apiProperties.getTitle() + " REST API")
                        .termsOfService("Terms and conditions")
                        .version(apiProperties.getVersion()))
                .components(new Components()
                        .addSecuritySchemes(
                                SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }

    @Bean
    public List<GroupedOpenApi> groupedOpenApis() {
        return apiProperties.getGroups().stream()
                .map(group -> GroupedOpenApi.builder()
                        .group(group.getName())
                        .packagesToScan(group.getPackageName())
                        .build()).toList();
    }
}