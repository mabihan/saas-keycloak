package com.example.demo.gateway.keycloak.model

data class KeycloakUserCredentials(
    val type: String,
    val value: String,
    val temporary: Boolean
)
