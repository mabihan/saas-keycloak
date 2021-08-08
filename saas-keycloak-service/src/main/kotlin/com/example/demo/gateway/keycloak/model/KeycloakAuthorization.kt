package com.example.demo.gateway.keycloak.model

import kotlin.String

data class KeycloakAuthorization (
    val access_token: String,
    val expires_in: Long,
    val refresh_expires_in: Long,
    val refresh_token: String,
    val token_type: String,
    val session_state: String,
    val scope: String,
    )



