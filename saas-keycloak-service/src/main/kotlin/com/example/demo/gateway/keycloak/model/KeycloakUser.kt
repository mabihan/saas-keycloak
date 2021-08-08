package com.example.demo.gateway.keycloak.model

data class KeycloakUser(
    val id: String,
    val createdTimestamp: Long,
    val username: String,
    val enabled: Boolean,
    val totp: Boolean,
    val emailVerified: Boolean,
    val firstName: String,
    val lastName: String,
    val email: String,
    val disableableCredentialTypes: List<Any?>,
    val requiredActions: List<Any?>,
    val notBefore: Long,
    val access: KeycloakAccess
)
