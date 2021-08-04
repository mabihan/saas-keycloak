package com.example.demo.gateway.keycloak.model

data class KeycloakUser(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String?
)
