package com.example.demo.config.keycloak

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "keycloak-admin", ignoreUnknownFields = false)
data class KeycloakAdministrationProperties(
    val authServerUrl: String,
    val clientId: String,
    val username: String,
    val password: String,
    val realm: String
)
