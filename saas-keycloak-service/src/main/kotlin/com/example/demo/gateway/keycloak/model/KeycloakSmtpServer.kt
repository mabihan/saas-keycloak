package com.example.demo.gateway.keycloak.model

import kotlin.String

data class KeycloakSmtpServer(
    val password: String,
    val replyToDisplayName: String,
    val starttls: Boolean,
    val auth: Boolean,
    val port: Int,
    val host: String,
    val replyTo: String,
    val from: String,
    val fromDisplayName: String,
    val ssl: Boolean,
    val user: String
    )
