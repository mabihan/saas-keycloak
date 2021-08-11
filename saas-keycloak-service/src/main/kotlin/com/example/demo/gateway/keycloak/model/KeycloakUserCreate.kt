package com.example.demo.gateway.keycloak.model

import org.keycloak.common.util.Time

data class KeycloakUserCreate(
    val createdTimestamp: Long? = Time.currentTimeMillis(),
    val username: String,
    val enabled: Boolean,
    val totp: Boolean,
    val emailVerified: Boolean,
    val firstName: String,
    val lastName: String,
    val email: String,
    val credentials: List<KeycloakUserCredentials>
)
