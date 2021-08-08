package com.example.demo.config.properties

import lombok.Getter
import lombok.Setter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@Getter
@Setter
@ConstructorBinding
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false, ignoreInvalidFields = true)
data class ApplicationProperties(
    val keycloak: KeycloakProperties
)

